//! new-transaction
//! account: bob
//! sender: bob
address bob = {{bob}};
script {
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM::{ARMMeta, ARMBody};
    use 0x1::NFTGallery;
    //    use 0x1::Signer;

    fun acceptArm(sender: signer) {
        NFTGallery::accept<ARMMeta, ARMBody>(&sender);
    }
}

//! new-transaction
//! account: alice
//! sender: alice
address alice = {{alice}};
script {
    use 0x1::Account;

    fun acceptArm(sender: signer) {
        Account::set_auto_accept_token(&sender, true);
    }
}

//! new-transaction
//! account: aww, 0x16a8bf4d0c3718518d81f132801e4aaa
//! sender: aww
address aww = {{aww}};
script {
    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWW;
    use 0x1::Account;

    fun init_aww(sender: signer) {
        AWW::initialize(&sender);
        Account::set_auto_accept_token(&sender, true);
        AWW::mint_to(&sender, 100000000000000u128, @aww);
    }
}

//! new-transaction
//! sender: aww
address aww = {{aww}};
address bob = {{bob}};
address alice = {{alice}};
script {
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWWGame;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARMMarket;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWW::AWW;
    use 0x1::Signer;
    use 0x1::NFTGallery;
    use 0x1::Account;

    fun get_arm(sender: signer) {
        ARM::f_init_with_image(&sender, b"aww arm", b"www.baidu.com", b"this is a arm");
        ARM::f_mint_with_image(&sender, b"aww arm", b"www.baidu.com", b"this is a arm", 1u8, 10u8, 30u8);
        ARM::f_mint_with_image(&sender, b"aww arm", b"www.baidu.com", b"this is a arm", 1u8, 10u8, 30u8);
        ARM::f_mint_with_image(&sender, b"aww arm", b"www.baidu.com", b"this is a arm", 1u8, 10u8, 30u8);
        assert(ARM::count_of(Signer::address_of(&sender))==3, 10001);
        AWWGame::init_game(&sender);

        assert(NFTGallery::is_accept<ARM::ARMMeta, ARM::ARMBody>(@bob), 10002);
        ARM::airdrop_arm(&sender, @bob);
        ARM::airdrop_arm(&sender, @bob);
        ARM::airdrop_arm(&sender, @bob);

        Account::pay_from<AWW>(&sender, @alice, 300000000000u128);
        assert(Account::balance<AWW>(@alice)==300000000000u128, 10003);

        ARMMarket::init_config(&sender, 0u128, 50u128);
        ARMMarket::init_market(&sender);
    }
}

//! new-transaction
//! sender: bob
address bob = {{bob}};
script {
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM::{ARMMeta, ARMBody};
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARMMarket;
    use 0x1::Signer;
    use 0x1::NFTGallery;

    fun sell_arm(sender: signer) {
        let sender_addr = Signer::address_of(&sender);
        let count = NFTGallery::count_of<ARMMeta, ARMBody>(sender_addr);
        assert(count == 3, 10004);

        ARMMarket::arm_place_order(&sender, 1u64, 300000000000u128);

        count = NFTGallery::count_of<ARMMeta, ARMBody>(sender_addr);
        assert(count == 2, 10005);

        ARMMarket::arm_place_order(&sender, 2u64, 300000000000u128);

        count = NFTGallery::count_of<ARMMeta, ARMBody>(sender_addr);
        assert(count == 1, 10006);
    }
}


//! new-transaction
//! sender: alice
address alice = {{alice}};
script {
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM::{ARMMeta, ARMBody};
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARMMarket;
    use 0x1::Signer;
    use 0x1::NFTGallery;

    fun buy_arm(sender: signer) {
        ARMMarket::arm_take_order(&sender, 1u64);

        let sender_addr = Signer::address_of(&sender);
        let count = NFTGallery::count_of<ARMMeta, ARMBody>(sender_addr);
        assert(count == 1, 10006);
    }
}


//! new-transaction
//! sender: bob
address bob = {{bob}};
script {
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM::{ARMMeta, ARMBody};
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARMMarket;
    use 0x1::Signer;
    use 0x1::NFTGallery;

    fun sell_arm(sender: signer) {
        let sender_addr = Signer::address_of(&sender);
        let count = NFTGallery::count_of<ARMMeta, ARMBody>(sender_addr);
        assert(count == 1, 10007);

        ARMMarket::arm_cancel_order(&sender, 2u64);

        count = NFTGallery::count_of<ARMMeta, ARMBody>(sender_addr);
        assert(count == 2, 10008);

    }
}