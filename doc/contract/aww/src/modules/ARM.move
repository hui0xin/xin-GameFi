address 0x16a8bf4d0c3718518d81f132801e4aaa {
module ARM {
    use 0x1::Signer;
    use 0x1::Event;
    use 0x1::Block;
    use 0x1::Vector;
    use 0x1::Account;
    use 0x1::Math;
    use 0x1::Timestamp;
    use 0x1::NFT::{Self, NFT};
    use 0x1::NFTGallery;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWW::AWW;
    use 0x5b876a58b0e1cff855b6489cd8cf3bec::DummyToken::STC;
    use 0x5b876a58b0e1cff855b6489cd8cf3bec::DummyToken::USDT;
    use 0xc9097c917625f3b01d59b375e0630b07::SwapLibrary;
    use 0xc9097c917625f3b01d59b375e0630b07::SwapPair;

    const ARM_ADDRESS: address = @0x16a8bf4d0c3718518d81f132801e4aaa;

    const SWAP_ADDRESS: address = @0xc9097c917625f3b01d59b375e0630b07;

    const PERMISSION_DENIED: u64 = 100001;

    const INSUFFICIENT_STAMINA: u64 = 100002;

    const SWAP_PAIR_NOT_EXISTS: u64 = 100003;

    const STOP_SELLING: u64 = 100004;

    const DAY_FACTOR: u64 = 86400;

    // ******************** ARM ********************
    // ARM extra meta
    struct ARMMeta has copy, store, drop {
        rarity: u8,
        stamina: u8,
        win_rate_bonus: u8
    }

    // ARM body
    struct ARMBody has copy, store, drop {
        time: u64,
        used_stamina: u8,
    }

    // ARM extra type info
    struct ARMTypeInfo has copy, store, drop {}

    struct ARMCapability has key {
        mint: NFT::MintCapability<ARMMeta>,
        update: NFT::UpdateCapability<ARMMeta>,
    }

    // init nft with image data
    fun init_arm(
        sender: &signer,
        metadata: NFT::Metadata,
    ) {
        NFT::register<ARMMeta, ARMTypeInfo>(sender, ARMTypeInfo {}, metadata);
        let mint = NFT::remove_mint_capability<ARMMeta>(sender);
        let update = NFT::remove_update_capability<ARMMeta>(sender);
        move_to(sender, ARMCapability { mint, update });
    }

    // mint nft
    fun mint_arm(
        sender: &signer,
        metadata: NFT::Metadata,
        rarity: u8,
        stamina: u8,
        win_rate_bonus: u8,
    ) acquires ARMCapability, ARMGallery {
        let sender_address = Signer::address_of(sender);
        let cap = borrow_global_mut<ARMCapability>(sender_address);
        let nft = NFT::mint_with_cap<ARMMeta, ARMBody, ARMTypeInfo>(
            sender_address,
            &mut cap.mint,
            metadata,
            ARMMeta {
                rarity,
                stamina,
                win_rate_bonus
            },
            ARMBody {
                time: 0u64,
                used_stamina: 0u8,
            }
        );
        let gallery = borrow_global_mut<ARMGallery>(sender_address);
        let id = NFT::get_id<ARMMeta, ARMBody>(&nft);
        Vector::push_back(&mut gallery.items, nft);
        Event::emit_event<ARMMintEvent>(&mut gallery.arm_mint_events,
            ARMMintEvent {
                creator: sender_address,
                id: id,
            },
        );
    }

    // ******************** NFT Gallery ********************
    // arm gallery
    struct ARMGallery has key, store {
        sell_open: bool,
        items: vector<NFT<ARMMeta, ARMBody>>,
        arm_mint_events: Event::EventHandle<ARMMintEvent>,
        arm_get_events: Event::EventHandle<ArmGetEvent>,
    }

    // arm mint event
    struct ARMMintEvent has drop, store {
        creator: address,
        id: u64,
    }

    // arm get event
    struct ArmGetEvent has drop, store {
        owner: address,
        id: u64,
    }

    // init arm gallery
    fun init_gallery(sender: &signer) {
        if (!exists<ARMGallery>(Signer::address_of(sender))) {
            let gallery = ARMGallery {
                sell_open: true,
                items: Vector::empty<NFT<ARMMeta, ARMBody>>(),
                arm_mint_events: Event::new_event_handle<ARMMintEvent>(sender),
                arm_get_events: Event::new_event_handle<ArmGetEvent>(sender),
            };
            move_to(sender, gallery);
        }
    }

    // Count all NFTs assigned to an owner
    public fun count_of(owner: address): u64
    acquires ARMGallery {
        let gallery = borrow_global_mut<ARMGallery>(owner);
        Vector::length(&gallery.items)
    }

    fun f_update_selling_state(sender: &signer, sell_open: bool) acquires ARMGallery {
        let sender_address = Signer::address_of(sender);
        let gallery = borrow_global_mut<ARMGallery>(sender_address);
        gallery.sell_open = sell_open;
    }

    // ******************** NFT public function ********************

    // init nft and box with image
    public fun f_init_with_image(
        sender: &signer,
        name: vector<u8>,
        image: vector<u8>,
        description: vector<u8>,
    ) {
        assert(Signer::address_of(sender) == ARM_ADDRESS, PERMISSION_DENIED);
        let metadata = NFT::new_meta_with_image(name, image, description);
        init_arm(sender, metadata);
        init_gallery(sender);
        NFTGallery::accept<ARMMeta, ARMBody>(sender);
    }

    // init nft and box with image data
    public fun f_init_with_image_data(
        sender: &signer,
        name: vector<u8>,
        image_data: vector<u8>,
        description: vector<u8>,
    ) {
        assert(Signer::address_of(sender) == ARM_ADDRESS, PERMISSION_DENIED);
        let metadata = NFT::new_meta_with_image_data(name, image_data, description);
        init_arm(sender, metadata);
        init_gallery(sender);
        NFTGallery::accept<ARMMeta, ARMBody>(sender);
    }

    // mint ARM
    public fun f_mint_with_image(
        sender: &signer,
        name: vector<u8>,
        image: vector<u8>,
        description: vector<u8>,
        rarity: u8,
        stamina: u8,
        win_rate_bonus: u8,
    ) acquires ARMCapability, ARMGallery {
        let sender_address = Signer::address_of(sender);
        assert(sender_address == ARM_ADDRESS, PERMISSION_DENIED);
        let metadata = NFT::new_meta_with_image(name, image, description);
        mint_arm(sender, metadata, rarity, stamina, win_rate_bonus);
    }

    // mint ARM
    public fun f_mint_with_image_data(
        sender: &signer,
        name: vector<u8>,
        image_data: vector<u8>,
        description: vector<u8>,
        rarity: u8,
        stamina: u8,
        win_rate_bonus: u8,
    ) acquires ARMCapability, ARMGallery {
        let sender_address = Signer::address_of(sender);
        assert(sender_address == ARM_ADDRESS, PERMISSION_DENIED);
        let metadata = NFT::new_meta_with_image_data(name, image_data, description);
        mint_arm(sender, metadata, rarity, stamina, win_rate_bonus);
    }

    public fun f_get_win_rate_bonus(arm: & NFT<ARMMeta, ARMBody>): u8 {
        let arm_meta = NFT::get_type_meta<ARMMeta, ARMBody>(arm);
        arm_meta.win_rate_bonus
    }

    // deduction ARM stamina
    public fun f_deduction_stamina(arm: &mut NFT<ARMMeta, ARMBody>) acquires ARMCapability {
        let cap = borrow_global_mut<ARMCapability>(ARM_ADDRESS);
        let arm_meta = NFT::get_type_meta<ARMMeta, ARMBody>(arm);
        let stamina = arm_meta.stamina;
        let arm_body = NFT::borrow_body_mut_with_cap<ARMMeta, ARMBody>(&mut cap.update, arm);

        let now = Timestamp::now_seconds();
        if ((arm_body.time + DAY_FACTOR) < now) {
            arm_body.time = now / DAY_FACTOR * DAY_FACTOR;
            arm_body.used_stamina = 0u8;
        };
        assert(stamina > arm_body.used_stamina, INSUFFICIENT_STAMINA);

        arm_body.used_stamina = arm_body.used_stamina + 1u8;
    }

    public fun random(range: u64): u64 {
        // get hash last 64 bit and mod nft_size
        let hash = Block::get_parent_hash();
        let k = 0u64;
        let i = 0;
        while (i < 8) {
            let tmp = (Vector::pop_back<u8>(&mut hash) as u128);
            k = (tmp << (i * 8) as u64) + k;
            i = i + 1;
        };
        k % range
    }

    public fun airdrop_arm(sender: &signer, reciver: address) acquires ARMGallery {
        assert(Signer::address_of(sender) == ARM_ADDRESS, PERMISSION_DENIED);
        let idx = random(count_of(ARM_ADDRESS));
        // get a arm by idx
        let gallery = borrow_global_mut<ARMGallery>(ARM_ADDRESS);
        let nft = Vector::remove<NFT<ARMMeta, ARMBody>>(&mut gallery.items, idx);
        let id = NFT::get_id<ARMMeta, ARMBody>(&nft);
        NFTGallery::deposit_to<ARMMeta, ARMBody>(reciver, nft);
        // emit event
        Event::emit_event<ArmGetEvent>(&mut gallery.arm_get_events,
            ArmGetEvent {
                owner: reciver,
                id: id,
            },
        );
    }

    // get a random ARM
    public fun get_arm(sender: &signer)
    acquires ARMGallery {
        let aww_amount = f_get_aww_amount(20000000000u128);
        Account::pay_from<AWW>(sender, ARM_ADDRESS, aww_amount);
        Account::pay_from<STC>(sender, ARM_ADDRESS, 100000000000u128);

        let idx = random(count_of(ARM_ADDRESS));
        // get a arm by idx
        let sender_address = Signer::address_of(sender);
        let gallery = borrow_global_mut<ARMGallery>(ARM_ADDRESS);
        assert(gallery.sell_open, STOP_SELLING);
        let nft = Vector::remove<NFT<ARMMeta, ARMBody>>(&mut gallery.items, idx);
        let id = NFT::get_id<ARMMeta, ARMBody>(&nft);
        NFTGallery::accept<ARMMeta, ARMBody>(sender);
        NFTGallery::deposit<ARMMeta, ARMBody>(sender, nft);
        // emit event
        Event::emit_event<ArmGetEvent>(&mut gallery.arm_get_events,
            ArmGetEvent {
                owner: sender_address,
                id: id,
            },
        );
    }

    fun f_get_aww_amount(usdt_amount: u128): u128 {
        // order x and y to avoid duplicates
        let order = SwapLibrary::get_token_order<USDT, AWW>();
        let (reserve_usdt, reserve_aww): (u128, u128);
        if (order == 1) {
            (reserve_usdt, reserve_aww) = SwapPair::get_reserves<USDT, AWW>();
        } else {
            (reserve_aww, reserve_usdt) = SwapPair::get_reserves<AWW, USDT>();
        };

        Math::mul_div(usdt_amount, reserve_aww, reserve_usdt)
    }

    fun f_get_reserves<X: store, Y: store>(): (u128, u128) {
        let pair_exists = SwapPair::pair_exists<X, Y>(SWAP_ADDRESS);
        assert(pair_exists, SWAP_PAIR_NOT_EXISTS);
        let (reserve_x, reserve_y) = SwapPair::get_reserves<X, Y>();
        (reserve_x, reserve_y)
    }

    // ******************** NFT script function ********************

    public(script) fun init_with_image(
        sender: signer,
        name: vector<u8>,
        image: vector<u8>,
        description: vector<u8>,
    ) {
        f_init_with_image(&sender, name, image, description);
    }

    public(script) fun init_with_image_data(
        sender: signer,
        name: vector<u8>,
        image_data: vector<u8>,
        description: vector<u8>,
    ) {
        f_init_with_image_data(&sender, name, image_data, description);
    }

    public(script) fun mint_with_image(
        sender: signer,
        name: vector<u8>,
        image: vector<u8>,
        description: vector<u8>,
        rarity: u8,
        stamina: u8,
        win_rate_bonus: u8,
    ) acquires ARMCapability, ARMGallery {
        f_mint_with_image(&sender, name, image, description, rarity, stamina, win_rate_bonus);
    }

    public(script) fun mint_with_image_data(
        sender: signer,
        name: vector<u8>,
        image_data: vector<u8>,
        description: vector<u8>,
        rarity: u8,
        stamina: u8,
        win_rate_bonus: u8,
    ) acquires ARMCapability, ARMGallery {
        f_mint_with_image_data(&sender, name, image_data, description, rarity, stamina, win_rate_bonus);
    }

    public(script) fun update_selling_state(sender: signer, sell_open: bool) acquires ARMGallery {
        f_update_selling_state(&sender, sell_open);
    }
}
}
