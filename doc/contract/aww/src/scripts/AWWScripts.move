address 0x16a8bf4d0c3718518d81f132801e4aaa {
module AWWScripts {

    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARMMarket;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWWGame;

    // ******************** Config ********************
    // init
    public(script) fun init_config(
        sender: signer,
        creator_fee: u128,
        platform_fee: u128
    ) {
        ARMMarket::init_config(&sender, creator_fee, platform_fee);
    }

    public(script) fun update_config(
        sender: signer,
        creator_fee: u128,
        platform_fee: u128
    ) {
        ARMMarket::update_config(&sender, creator_fee, platform_fee);
    }

    // ******************** Initial Offering ********************
    public(script) fun init_market(
        sender: signer,
    ) {
        ARMMarket::init_market(&sender);
    }

    // ******************** AWW GAME Transaction ********************

    public(script) fun init_game(account: signer) {
        AWWGame::init_game(&account);
    }

    public(script) fun arm_mint(account: signer) {
        AWWGame::arm_mint(&account);
    }

    public(script) fun fight(
        account: signer,
        id: u64,
        level: u8
    ) {
        AWWGame::fight(&account, id, level);
    }

    public(script) fun harvest_reward(
        account: signer
    ) {
        AWWGame::harvest_reward(&account);
    }


    // ******************** ARM Transaction ********************

    // ARM place order
    public(script) fun arm_place_order(
        account: signer,
        id: u64,
        selling_price: u128
    ) {
        ARMMarket::arm_place_order(&account, id,selling_price);
    }

    // ARM cancel order
    public(script) fun arm_cancel_order(
        account: signer,
        id: u64,
    ) {
        ARMMarket::arm_cancel_order(&account, id);
    }

    // ARM buy
    public(script) fun arm_take_order(
        account: signer,
        id: u64
    ) {
        ARMMarket::arm_take_order(&account, id);
    }

}
}