address 0x16a8bf4d0c3718518d81f132801e4aaa {
module ARMMarket {

    use 0x1::Event;
    use 0x1::Account;
    use 0x1::Option::{Self, Option};
    use 0x1::Signer;
    use 0x1::Token;
    use 0x1::Vector;
    use 0x1::Timestamp;
    use 0x1::NFT::{Self, NFT};
    use 0x1::NFTGallery;

    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWW::AWW;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM::{ARMMeta, ARMBody};

    const NFT_MARKET_ADDRESS: address = @0x16a8bf4d0c3718518d81f132801e4aaa;

    //error
    const PERMISSION_DENIED: u64 = 200001;
    const OFFERING_NOT_EXISTS: u64 = 200002;
    const OFFERING_NOT_ON_SALE: u64 = 200003;
    const INSUFFICIENT_BALANCE: u64 = 200004;
    const ID_NOT_EXIST: u64 = 200005;
    const BID_FAILED: u64 = 200006;
    const NFT_SELL_INFO_NOT_EXISTS: u64 = 200007;
    const EXCESSIVE_FEE_RATE: u64 = 200008;
    const BOX_SELLING_NOT_EXIST: u64 = 200009;
    const BOX_SELLING_IS_EMPTY: u64 = 200010;
    const BOX_SELLING_PRICE_SMALL: u64 = 200011;
    const BOX_SELLING_INDEX_OUT_BOUNDS: u64 = 200012;
    const PRICE_TOO_LOW: u64 = 200013;

    // ******************** Config ********************
    struct Config has key, store {
        // creator fee, 10 mean 1%
        creator_fee: u128,
        // platform fee
        platform_fee: u128
    }

    // init
    public fun init_config(sender: &signer, creator_fee: u128, platform_fee: u128) {
        assert(Signer::address_of(sender) == NFT_MARKET_ADDRESS, PERMISSION_DENIED);
        assert(creator_fee < 1000 && platform_fee < 1000, EXCESSIVE_FEE_RATE);

        move_to<Config>(sender, Config {
            creator_fee: creator_fee,
            platform_fee: platform_fee,
        });
    }

    // update
    public fun update_config(sender: &signer, creator_fee: u128, platform_fee: u128)
    acquires Config {
        assert(Signer::address_of(sender) == NFT_MARKET_ADDRESS, PERMISSION_DENIED);
        assert(creator_fee < 1000 && platform_fee < 1000, EXCESSIVE_FEE_RATE);

        let config = borrow_global_mut<Config>(NFT_MARKET_ADDRESS);
        config.creator_fee = creator_fee;
        config.platform_fee = platform_fee;
    }

    // get fee
    public fun get_fee(amount: u128): (u128, u128) acquires Config {
        let config = borrow_global<Config>(NFT_MARKET_ADDRESS);
        (amount * config.creator_fee / 1000, amount * config.platform_fee / 1000)
    }

    // ******************** Initial market ********************
    // init market resource
    public fun init_market (
        sender: &signer,
    ) {
        let sender_address = Signer::address_of(sender);
        assert(sender_address == NFT_MARKET_ADDRESS, PERMISSION_DENIED);
        if (!exists<ARMSelling>(sender_address)) {
            move_to(sender, ARMSelling {
                items: Vector::empty(),
                place_order_events: Event::new_event_handle<ARMPlaceOrderEvent>(sender),
                cancel_order_events: Event::new_event_handle<ARMCancelOrderEvent>(sender),
                take_order_events: Event::new_event_handle<ARMTakeOrderEvent>(sender),
            });
        };
        // auto accept token
        Account::set_auto_accept_token(sender, true);
    }

    // ******************** NFT Transaction ********************
    // ARM selling list
    struct ARMSelling has key, store {
        // arm selling list
        items: vector<ARMSellInfo>,
        place_order_events: Event::EventHandle<ARMPlaceOrderEvent>,
        cancel_order_events: Event::EventHandle<ARMCancelOrderEvent>,
        take_order_events: Event::EventHandle<ARMTakeOrderEvent>,
    }

    // ARM extra sell info
    struct ARMSellInfo has store {
        seller: address,
        // arm item
        nft: Option<NFT<ARMMeta, ARMBody>>,
        // arm id
        id: u64,
        // selling price
        selling_price: u128,
    }

    // ARM place order event
    struct ARMPlaceOrderEvent has drop, store {
        seller: address,
        id: u64,
        pay_token_code: Token::TokenCode,
        selling_price: u128,
        time: u64,
    }

    // ARM cancel order event
    struct ARMCancelOrderEvent has drop, store {
        seller: address,
        id: u64,
        pay_token_code: Token::TokenCode,
        selling_price: u128,
        time: u64,
    }

    // ARM match order event
    struct ARMTakeOrderEvent has drop, store {
        seller: address,
        buyer: address,
        id: u64,
        pay_token_code: Token::TokenCode,
        selling_price: u128,
        platform_fee: u128,
        time: u64,
    }

    // ARM place order
    public fun arm_place_order(account: &signer, id: u64, selling_price: u128) acquires ARMSelling {
        // ARMSelling exists
        assert(exists<ARMSelling>(NFT_MARKET_ADDRESS), OFFERING_NOT_EXISTS);
        assert(selling_price > 0, PRICE_TOO_LOW);

        let nft_selling = borrow_global_mut<ARMSelling>(NFT_MARKET_ADDRESS);
        let owner_address = Signer::address_of(account);
        // Withdraw one NFT token from your account
        let option_nft = NFTGallery::withdraw(account, id);
        assert(Option::is_some<NFT<ARMMeta, ARMBody>>(&option_nft), ID_NOT_EXIST);
        let nft_sell_info = ARMSellInfo {
            seller: owner_address,
            nft: option_nft,
            id: id,
            selling_price: selling_price,
        };
        // arm_sell_info add Vector
        Vector::push_back(&mut nft_selling.items, nft_sell_info);
        // accept PayToken
        if (!Account::is_accepts_token<AWW>(owner_address)){
            Account::do_accept_token<AWW>(account);
        };
        Event::emit_event(&mut nft_selling.place_order_events,
            ARMPlaceOrderEvent {
                seller: owner_address,
                id: id,
                pay_token_code: Token::token_code<AWW>(),
                selling_price: selling_price,
                time: Timestamp::now_milliseconds(),
            },
        );
    }

    // ARM cancel order
    public fun arm_cancel_order(account: &signer, id: u64) acquires ARMSelling {
        assert(exists<ARMSelling>(NFT_MARKET_ADDRESS), OFFERING_NOT_EXISTS);
        let nft_selling = borrow_global_mut<ARMSelling>(NFT_MARKET_ADDRESS);
        let nft_sell_info = find_arm_sell_info_by_id(&mut nft_selling.items, id);
        // check seller
        let user_address = Signer::address_of(account);
        assert(user_address == nft_sell_info.seller, PERMISSION_DENIED);
        let nft = Option::extract(&mut nft_sell_info.nft);
        NFTGallery::deposit_to<ARMMeta, ARMBody>(nft_sell_info.seller, nft);
        Event::emit_event(&mut nft_selling.cancel_order_events,
            ARMCancelOrderEvent {
                seller: nft_sell_info.seller,
                id: nft_sell_info.id,
                pay_token_code: Token::token_code<AWW>(),
                selling_price: nft_sell_info.selling_price,
                time: Timestamp::now_milliseconds(),
            },
        );
        // destory
        let ARMSellInfo {
            seller: _,
            nft,
            id: _,
            selling_price: _,
        } = nft_sell_info;
        Option::destroy_none(nft);
    }

    // ARM take order
    public fun arm_take_order(account: &signer,id: u64) acquires ARMSelling, Config {
        let nft_selling = borrow_global_mut<ARMSelling>(NFT_MARKET_ADDRESS);
        let nft_sell_info = find_arm_sell_info_by_id(&mut nft_selling.items, id);
        f_arm_take_order<>(account, nft_sell_info);
    }

    // ARM buy private
    fun f_arm_take_order(
        account: &signer,
        nft_sell_info: ARMSellInfo
    ) acquires ARMSelling, Config {
        let user_address = Signer::address_of(account);
        let nft_selling = borrow_global_mut<ARMSelling>(NFT_MARKET_ADDRESS);
        let selling_price = nft_sell_info.selling_price;
        let token_balance = Account::balance<AWW>(user_address);
        assert(token_balance >= selling_price, INSUFFICIENT_BALANCE);
        let nft = Option::extract(&mut nft_sell_info.nft);

        let (creator_fee, platform_fee) = get_fee(selling_price);

        if (creator_fee > 0) {
            let creator_address = NFT::get_creator<ARMMeta, ARMBody>(&nft);
            let creator_fee_token = Account::withdraw<AWW>(account, creator_fee);
            Account::deposit<AWW>(creator_address, creator_fee_token);
        };

        let platform_fee_token = Account::withdraw<AWW>(account, platform_fee);
        Account::deposit<AWW>(NFT_MARKET_ADDRESS, platform_fee_token);

        let surplus_amount = selling_price - creator_fee - platform_fee;
        let surplus_amount_token = Account::withdraw<AWW>(account, surplus_amount);
        Account::deposit<AWW>(nft_sell_info.seller, surplus_amount_token);

        // accept
        NFTGallery::accept<ARMMeta, ARMBody>(account);
        // arm transer Own
        NFTGallery::deposit<ARMMeta, ARMBody>(account, nft);

        //send NFTSellEvent event
        Event::emit_event(&mut nft_selling.take_order_events,
            ARMTakeOrderEvent {
                seller: nft_sell_info.seller,
                buyer: user_address,
                id: nft_sell_info.id,
                pay_token_code: Token::token_code<AWW>(),
                selling_price: selling_price,
                platform_fee: platform_fee,
                time: Timestamp::now_milliseconds(),
            },
        );
        let ARMSellInfo {
            seller: _,
            nft,
            id: _,
            selling_price: _,
        } = nft_sell_info;
        Option::destroy_none(nft);
    }

    //get nft_sell_info by id
    fun find_arm_sell_info_by_id(c: &mut vector<ARMSellInfo>, id: u64): ARMSellInfo {
        let len = Vector::length(c);
        assert(len > 0, ID_NOT_EXIST);
        let i = len - 1;
        loop {
            // ARMSellInfo
            let nftSellInfo = Vector::borrow(c, i);
            let nft = Option::borrow(&nftSellInfo.nft);
            if (NFT::get_id(nft) == id) {
                return Vector::remove(c, i)
            };
            assert(i > 0, ID_NOT_EXIST);
            i = i - 1;
        }
    }

}
}