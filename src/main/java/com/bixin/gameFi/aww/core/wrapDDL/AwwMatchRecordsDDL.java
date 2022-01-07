package com.bixin.gameFi.aww.core.wrapDDL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AwwMatchRecordsDDL {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AwwMatchRecordsDDL() {
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

        public Criteria andSellAddressIsNull() {
            addCriterion("sell_address is null");
            return (Criteria) this;
        }

        public Criteria andSellAddressIsNotNull() {
            addCriterion("sell_address is not null");
            return (Criteria) this;
        }

        public Criteria andSellAddressEqualTo(String value) {
            addCriterion("sell_address =", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotEqualTo(String value) {
            addCriterion("sell_address <>", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressGreaterThan(String value) {
            addCriterion("sell_address >", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressGreaterThanOrEqualTo(String value) {
            addCriterion("sell_address >=", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLessThan(String value) {
            addCriterion("sell_address <", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLessThanOrEqualTo(String value) {
            addCriterion("sell_address <=", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLike(String value) {
            addCriterion("sell_address like", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotLike(String value) {
            addCriterion("sell_address not like", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressIn(List<String> values) {
            addCriterion("sell_address in", values, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotIn(List<String> values) {
            addCriterion("sell_address not in", values, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressBetween(String value1, String value2) {
            addCriterion("sell_address between", value1, value2, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotBetween(String value1, String value2) {
            addCriterion("sell_address not between", value1, value2, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressIsNull() {
            addCriterion("buy_address is null");
            return (Criteria) this;
        }

        public Criteria andBuyAddressIsNotNull() {
            addCriterion("buy_address is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAddressEqualTo(String value) {
            addCriterion("buy_address =", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotEqualTo(String value) {
            addCriterion("buy_address <>", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressGreaterThan(String value) {
            addCriterion("buy_address >", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("buy_address >=", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressLessThan(String value) {
            addCriterion("buy_address <", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressLessThanOrEqualTo(String value) {
            addCriterion("buy_address <=", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressLike(String value) {
            addCriterion("buy_address like", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotLike(String value) {
            addCriterion("buy_address not like", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressIn(List<String> values) {
            addCriterion("buy_address in", values, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotIn(List<String> values) {
            addCriterion("buy_address not in", values, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressBetween(String value1, String value2) {
            addCriterion("buy_address between", value1, value2, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotBetween(String value1, String value2) {
            addCriterion("buy_address not between", value1, value2, "buyAddress");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andMatchTimeIsNull() {
            addCriterion("match_time is null");
            return (Criteria) this;
        }

        public Criteria andMatchTimeIsNotNull() {
            addCriterion("match_time is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTimeEqualTo(Long value) {
            addCriterion("match_time =", value, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeNotEqualTo(Long value) {
            addCriterion("match_time <>", value, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeGreaterThan(Long value) {
            addCriterion("match_time >", value, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("match_time >=", value, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeLessThan(Long value) {
            addCriterion("match_time <", value, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeLessThanOrEqualTo(Long value) {
            addCriterion("match_time <=", value, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeIn(List<Long> values) {
            addCriterion("match_time in", values, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeNotIn(List<Long> values) {
            addCriterion("match_time not in", values, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeBetween(Long value1, Long value2) {
            addCriterion("match_time between", value1, value2, "matchTime");
            return (Criteria) this;
        }

        public Criteria andMatchTimeNotBetween(Long value1, Long value2) {
            addCriterion("match_time not between", value1, value2, "matchTime");
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