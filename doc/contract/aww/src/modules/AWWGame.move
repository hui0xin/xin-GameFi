address 0x16a8bf4d0c3718518d81f132801e4aaa {
module AWWGame {
    use 0x1::Signer;
    use 0x1::Event;
    use 0x1::Token;
    use 0x1::Account;
    use 0x1::Timestamp;
    use 0x1::Option;
    use 0x1::Math;
    use 0x1::NFTGallery;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::ARM;
    use 0x16a8bf4d0c3718518d81f132801e4aaa::AWW::{Self, AWW};

    const ARM_ADDRESS: address = @0x16a8bf4d0c3718518d81f132801e4aaa;

    const PERMISSION_DENIED: u64 = 100001;

    const LEVEL_ERROR: u64 = 100002;

    const DAY_FACTOR: u64 = 86400;

    struct FightEvent has drop, store {
        player: address,
        arm_id: u64,
        level: u8,
        victory: bool,
        reward: u128,
    }

    struct HarvestRewardEvent has drop, store {
        player: address,
        taxes_amount: u128,
        reward_amount: u128,
    }

    struct PlayerRewardPool has key, store {
        time: u64,
        reward: Token::Token<AWW>,
        fight_events: Event::EventHandle<FightEvent>,
        harvest_reward_events: Event::EventHandle<HarvestRewardEvent>,
    }

    struct SharedMintCapability has key, store {
        cap: Token::MintCapability<AWW>
    }

    public fun init_game(account: &signer) {
        assert(Signer::address_of(account) == ARM_ADDRESS, PERMISSION_DENIED);
        let cap = AWW::remove_mint_capability(account);
        move_to(account, SharedMintCapability{
            cap
        });
    }

    public fun mint_aww_to(
        account: &signer,
        amount: u128,
        address: address
    ) acquires SharedMintCapability {
        assert(Signer::address_of(account) == ARM_ADDRESS, PERMISSION_DENIED);
        let cap = borrow_global_mut<SharedMintCapability>(ARM_ADDRESS);
        let aww_token = AWW::mint_with_capability(&cap.cap, amount);
        Account::deposit<AWW>(address, aww_token);
    }

    public fun arm_mint(
        account: &signer
    ) {
        ARM::get_arm(account);
    }

    public fun fight(
        account: &signer,
        arm_id: u64,
        level: u8
    ) acquires PlayerRewardPool, SharedMintCapability {
        assert(level < 3, LEVEL_ERROR);

        let player = Signer::address_of(account);
        if (!exists<PlayerRewardPool>(player)) {
            move_to(account, PlayerRewardPool{
                time: Timestamp::now_seconds(),
                reward: Token::zero<AWW>(),
                fight_events: Event::new_event_handle<FightEvent>(account),
                harvest_reward_events: Event::new_event_handle<HarvestRewardEvent>(account),
            })
        };
        let user_reward_pool = borrow_global_mut<PlayerRewardPool>(player);
        let cap = borrow_global_mut<SharedMintCapability>(ARM_ADDRESS);

        // Withdraw one ARM token from your account
        let option_arm = NFTGallery::withdraw<ARM::ARMMeta, ARM::ARMBody>(account, arm_id);
        let arm = Option::destroy_some(option_arm);
        ARM::f_deduction_stamina(&mut arm);

        // fight arguments
        let reward_amount = 100000000000u128;
        let win_rate = ARM::f_get_win_rate_bonus(&arm);
        if (level == 0u8) {
            win_rate = win_rate + 80u8;
        } else if (level == 1u8) {
            reward_amount = Math::mul_div(reward_amount, 15u128, 10u128);
            win_rate = win_rate + 40u8;
        } else if (level == 2u8) {
            reward_amount = reward_amount * 2u128;
            win_rate = win_rate + 20u8;
        };
        let result = ARM::random(100u64);

        // victory
        if (result < (win_rate as u64)) {
            let reward = AWW::mint_with_capability(&cap.cap, reward_amount);
            Token::deposit<AWW>(&mut user_reward_pool.reward, reward);
            Event::emit_event(&mut user_reward_pool.fight_events,
                FightEvent {
                    player,
                    arm_id,
                    level,
                    victory: true,
                    reward: reward_amount,
                }
            );
        } else {
            Event::emit_event(&mut user_reward_pool.fight_events,
                FightEvent {
                    player,
                    arm_id,
                    level,
                    victory: false,
                    reward: 0u128,
                }
            );
        };

        NFTGallery::deposit_to<ARM::ARMMeta, ARM::ARMBody>(player, arm);
    }

    public fun harvest_reward(
        account: &signer
    ) acquires PlayerRewardPool {
        let player = Signer::address_of(account);
        let user_reward_pool = borrow_global_mut<PlayerRewardPool>(player);
        let now = Timestamp::now_seconds();
        let tax_rate = 30 - (now - user_reward_pool.time) / DAY_FACTOR * 2;

        let taxes_amount = Token::value<AWW>(&user_reward_pool.reward) * (tax_rate as u128) / 100;
        let reward_amount = Token::value<AWW>(&user_reward_pool.reward) - taxes_amount;

        let reward = Token::withdraw<AWW>(&mut user_reward_pool.reward, reward_amount);
        let taxes = Token::withdraw<AWW>(&mut user_reward_pool.reward, taxes_amount);

        let is_accept_token = Account::is_accepts_token<AWW>(player);
        if (!is_accept_token) {
            Account::do_accept_token<AWW>(account);
        };
        Account::deposit<AWW>(player, reward);
        Account::deposit<AWW>(ARM_ADDRESS, taxes);

        Event::emit_event(&mut user_reward_pool.harvest_reward_events,
            HarvestRewardEvent {
                player,
                taxes_amount,
                reward_amount,
            }
        );
    }
}
}
