package com.xin.gameFi.aww.core.wrapDDL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AwwMarketDDL {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AwwMarketDDL() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChainIdIsNull() {
            addCriterion("chain_id is null");
            return (Criteria) this;
        }

        public Criteria andChainIdIsNotNull() {
            addCriterion("chain_id is not null");
            return (Criteria) this;
        }

        public Criteria andChainIdEqualTo(Long value) {
            addCriterion("chain_id =", value, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdNotEqualTo(Long value) {
            addCriterion("chain_id <>", value, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdGreaterThan(Long value) {
            addCriterion("chain_id >", value, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("chain_id >=", value, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdLessThan(Long value) {
            addCriterion("chain_id <", value, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdLessThanOrEqualTo(Long value) {
            addCriterion("chain_id <=", value, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdIn(List<Long> values) {
            addCriterion("chain_id in", values, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdNotIn(List<Long> values) {
            addCriterion("chain_id not in", values, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdBetween(Long value1, Long value2) {
            addCriterion("chain_id between", value1, value2, "chainId");
            return (Criteria) this;
        }

        public Criteria andChainIdNotBetween(Long value1, Long value2) {
            addCriterion("chain_id not between", value1, value2, "chainId");
            return (Criteria) this;
        }

        public Criteria andAwwIdIsNull() {
            addCriterion("aww_id is null");
            return (Criteria) this;
        }

        public Criteria andAwwIdIsNotNull() {
            addCriterion("aww_id is not null");
            return (Criteria) this;
        }

        public Criteria andAwwIdEqualTo(Long value) {
            addCriterion("aww_id =", value, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdNotEqualTo(Long value) {
            addCriterion("aww_id <>", value, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdGreaterThan(Long value) {
            addCriterion("aww_id >", value, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdGreaterThanOrEqualTo(Long value) {
            addCriterion("aww_id >=", value, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdLessThan(Long value) {
            addCriterion("aww_id <", value, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdLessThanOrEqualTo(Long value) {
            addCriterion("aww_id <=", value, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdIn(List<Long> values) {
            addCriterion("aww_id in", values, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdNotIn(List<Long> values) {
            addCriterion("aww_id not in", values, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdBetween(Long value1, Long value2) {
            addCriterion("aww_id between", value1, value2, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwIdNotBetween(Long value1, Long value2) {
            addCriterion("aww_id not between", value1, value2, "awwId");
            return (Criteria) this;
        }

        public Criteria andAwwNameIsNull() {
            addCriterion("aww_name is null");
            return (Criteria) this;
        }

        public Criteria andAwwNameIsNotNull() {
            addCriterion("aww_name is not null");
            return (Criteria) this;
        }

        public Criteria andAwwNameEqualTo(String value) {
            addCriterion("aww_name =", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameNotEqualTo(String value) {
            addCriterion("aww_name <>", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameGreaterThan(String value) {
            addCriterion("aww_name >", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameGreaterThanOrEqualTo(String value) {
            addCriterion("aww_name >=", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameLessThan(String value) {
            addCriterion("aww_name <", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameLessThanOrEqualTo(String value) {
            addCriterion("aww_name <=", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameLike(String value) {
            addCriterion("aww_name like", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameNotLike(String value) {
            addCriterion("aww_name not like", value, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameIn(List<String> values) {
            addCriterion("aww_name in", values, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameNotIn(List<String> values) {
            addCriterion("aww_name not in", values, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameBetween(String value1, String value2) {
            addCriterion("aww_name between", value1, value2, "awwName");
            return (Criteria) this;
        }

        public Criteria andAwwNameNotBetween(String value1, String value2) {
            addCriterion("aww_name not between", value1, value2, "awwName");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andRarityIsNull() {
            addCriterion("rarity is null");
            return (Criteria) this;
        }

        public Criteria andRarityIsNotNull() {
            addCriterion("rarity is not null");
            return (Criteria) this;
        }

        public Criteria andRarityEqualTo(Integer value) {
            addCriterion("rarity =", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityNotEqualTo(Integer value) {
            addCriterion("rarity <>", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityGreaterThan(Integer value) {
            addCriterion("rarity >", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityGreaterThanOrEqualTo(Integer value) {
            addCriterion("rarity >=", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityLessThan(Integer value) {
            addCriterion("rarity <", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityLessThanOrEqualTo(Integer value) {
            addCriterion("rarity <=", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityIn(List<Integer> values) {
            addCriterion("rarity in", values, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityNotIn(List<Integer> values) {
            addCriterion("rarity not in", values, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityBetween(Integer value1, Integer value2) {
            addCriterion("rarity between", value1, value2, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityNotBetween(Integer value1, Integer value2) {
            addCriterion("rarity not between", value1, value2, "rarity");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNull() {
            addCriterion("sell_price is null");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNotNull() {
            addCriterion("sell_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellPriceEqualTo(BigDecimal value) {
            addCriterion("sell_price =", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotEqualTo(BigDecimal value) {
            addCriterion("sell_price <>", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThan(BigDecimal value) {
            addCriterion("sell_price >", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_price >=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThan(BigDecimal value) {
            addCriterion("sell_price <", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_price <=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIn(List<BigDecimal> values) {
            addCriterion("sell_price in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotIn(List<BigDecimal> values) {
            addCriterion("sell_price not in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_price between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_price not between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andStaminaIsNull() {
            addCriterion("stamina is null");
            return (Criteria) this;
        }

        public Criteria andStaminaIsNotNull() {
            addCriterion("stamina is not null");
            return (Criteria) this;
        }

        public Criteria andStaminaEqualTo(Integer value) {
            addCriterion("stamina =", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaNotEqualTo(Integer value) {
            addCriterion("stamina <>", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaGreaterThan(Integer value) {
            addCriterion("stamina >", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaGreaterThanOrEqualTo(Integer value) {
            addCriterion("stamina >=", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaLessThan(Integer value) {
            addCriterion("stamina <", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaLessThanOrEqualTo(Integer value) {
            addCriterion("stamina <=", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaIn(List<Integer> values) {
            addCriterion("stamina in", values, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaNotIn(List<Integer> values) {
            addCriterion("stamina not in", values, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaBetween(Integer value1, Integer value2) {
            addCriterion("stamina between", value1, value2, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaNotBetween(Integer value1, Integer value2) {
            addCriterion("stamina not between", value1, value2, "stamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaIsNull() {
            addCriterion("used_stamina is null");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaIsNotNull() {
            addCriterion("used_stamina is not null");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaEqualTo(Integer value) {
            addCriterion("used_stamina =", value, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaNotEqualTo(Integer value) {
            addCriterion("used_stamina <>", value, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaGreaterThan(Integer value) {
            addCriterion("used_stamina >", value, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaGreaterThanOrEqualTo(Integer value) {
            addCriterion("used_stamina >=", value, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaLessThan(Integer value) {
            addCriterion("used_stamina <", value, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaLessThanOrEqualTo(Integer value) {
            addCriterion("used_stamina <=", value, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaIn(List<Integer> values) {
            addCriterion("used_stamina in", values, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaNotIn(List<Integer> values) {
            addCriterion("used_stamina not in", values, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaBetween(Integer value1, Integer value2) {
            addCriterion("used_stamina between", value1, value2, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andUsedStaminaNotBetween(Integer value1, Integer value2) {
            addCriterion("used_stamina not between", value1, value2, "usedStamina");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusIsNull() {
            addCriterion("win_rate_bonus is null");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusIsNotNull() {
            addCriterion("win_rate_bonus is not null");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusEqualTo(Integer value) {
            addCriterion("win_rate_bonus =", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusNotEqualTo(Integer value) {
            addCriterion("win_rate_bonus <>", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusGreaterThan(Integer value) {
            addCriterion("win_rate_bonus >", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusGreaterThanOrEqualTo(Integer value) {
            addCriterion("win_rate_bonus >=", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusLessThan(Integer value) {
            addCriterion("win_rate_bonus <", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusLessThanOrEqualTo(Integer value) {
            addCriterion("win_rate_bonus <=", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusIn(List<Integer> values) {
            addCriterion("win_rate_bonus in", values, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusNotIn(List<Integer> values) {
            addCriterion("win_rate_bonus not in", values, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusBetween(Integer value1, Integer value2) {
            addCriterion("win_rate_bonus between", value1, value2, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusNotBetween(Integer value1, Integer value2) {
            addCriterion("win_rate_bonus not between", value1, value2, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}