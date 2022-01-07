address 0x16a8bf4d0c3718518d81f132801e4aaa {
module AWW {
    use 0x1::Token::{Self, Token};
    use 0x1::Account;

    /// AWW token marker.
    struct AWW has copy, drop, store { }

    /// precision of AWW token.
    const PRECISION: u8 = 9;

    /// Burn capability of AWW.
    struct SharedBurnCapability has key, store {
        cap: Token::BurnCapability<AWW>,
    }

    /// AWW initialization.
    public fun initialize(
        account: &signer) {
        Token::register_token<AWW>(account, PRECISION);

        let burn_cap = Token::remove_burn_capability<AWW>(account);
        move_to(account, SharedBurnCapability { cap: burn_cap });
    }

    spec initialize {
        include Token::RegisterTokenAbortsIf<AWW>{precision: PRECISION};
    }

    /// Returns true if `TokenType` is `AWW::AWW`
    public fun is_aww<TokenType: store>(): bool {
        Token::is_same_token<AWW, TokenType>()
    }

    spec is_aww {
    }

    public fun mint_to(
        account: &signer,
        amount: u128,
        address: address
    ) {
        let aww_token = Token::mint<AWW>(account, amount);
        Account::deposit<AWW>(address, aww_token);
    }

    public fun mint_with_capability(
        capability: &Token::MintCapability<AWW>,
        amount: u128,
    ): Token<AWW> {
        Token::mint_with_capability<AWW>(capability, amount)
    }

    public fun remove_mint_capability(account: &signer): Token::MintCapability<AWW> {
        Token::remove_mint_capability<AWW>(account)
    }

    /// Burn AWW tokens.
    /// It can be called by anyone.
    public fun burn(token: Token<AWW>) acquires SharedBurnCapability {
        let cap = borrow_global<SharedBurnCapability>(token_address());
        Token::burn_with_capability(&cap.cap, token);
    }

    spec burn {
        aborts_if Token::spec_abstract_total_value<AWW>() - token.value < 0;
        aborts_if !exists<SharedBurnCapability>(Token::SPEC_TOKEN_TEST_ADDRESS());
    }

    /// Return AWW token address.
    public fun token_address(): address {
        Token::token_address<AWW>()
    }

    spec token_address {
    }
}
}