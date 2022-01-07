package com.xin.gameFi.aww.core.wrapDDL;

import java.util.ArrayList;
import java.util.List;

public class AwwArmInfoDDL {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AwwArmInfoDDL() {
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

        public Criteria andArmIdIsNull() {
            addCriterion("arm_id is null");
            return (Criteria) this;
        }

        public Criteria andArmIdIsNotNull() {
            addCriterion("arm_id is not null");
            return (Criteria) this;
        }

        public Criteria andArmIdEqualTo(Long value) {
            addCriterion("arm_id =", value, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdNotEqualTo(Long value) {
            addCriterion("arm_id <>", value, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdGreaterThan(Long value) {
            addCriterion("arm_id >", value, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("arm_id >=", value, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdLessThan(Long value) {
            addCriterion("arm_id <", value, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdLessThanOrEqualTo(Long value) {
            addCriterion("arm_id <=", value, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdIn(List<Long> values) {
            addCriterion("arm_id in", values, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdNotIn(List<Long> values) {
            addCriterion("arm_id not in", values, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdBetween(Long value1, Long value2) {
            addCriterion("arm_id between", value1, value2, "armId");
            return (Criteria) this;
        }

        public Criteria andArmIdNotBetween(Long value1, Long value2) {
            addCriterion("arm_id not between", value1, value2, "armId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andRarityEqualTo(Byte value) {
            addCriterion("rarity =", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityNotEqualTo(Byte value) {
            addCriterion("rarity <>", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityGreaterThan(Byte value) {
            addCriterion("rarity >", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityGreaterThanOrEqualTo(Byte value) {
            addCriterion("rarity >=", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityLessThan(Byte value) {
            addCriterion("rarity <", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityLessThanOrEqualTo(Byte value) {
            addCriterion("rarity <=", value, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityIn(List<Byte> values) {
            addCriterion("rarity in", values, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityNotIn(List<Byte> values) {
            addCriterion("rarity not in", values, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityBetween(Byte value1, Byte value2) {
            addCriterion("rarity between", value1, value2, "rarity");
            return (Criteria) this;
        }

        public Criteria andRarityNotBetween(Byte value1, Byte value2) {
            addCriterion("rarity not between", value1, value2, "rarity");
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

        public Criteria andStaminaEqualTo(Byte value) {
            addCriterion("stamina =", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaNotEqualTo(Byte value) {
            addCriterion("stamina <>", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaGreaterThan(Byte value) {
            addCriterion("stamina >", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaGreaterThanOrEqualTo(Byte value) {
            addCriterion("stamina >=", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaLessThan(Byte value) {
            addCriterion("stamina <", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaLessThanOrEqualTo(Byte value) {
            addCriterion("stamina <=", value, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaIn(List<Byte> values) {
            addCriterion("stamina in", values, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaNotIn(List<Byte> values) {
            addCriterion("stamina not in", values, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaBetween(Byte value1, Byte value2) {
            addCriterion("stamina between", value1, value2, "stamina");
            return (Criteria) this;
        }

        public Criteria andStaminaNotBetween(Byte value1, Byte value2) {
            addCriterion("stamina not between", value1, value2, "stamina");
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

        public Criteria andWinRateBonusEqualTo(Byte value) {
            addCriterion("win_rate_bonus =", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusNotEqualTo(Byte value) {
            addCriterion("win_rate_bonus <>", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusGreaterThan(Byte value) {
            addCriterion("win_rate_bonus >", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusGreaterThanOrEqualTo(Byte value) {
            addCriterion("win_rate_bonus >=", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusLessThan(Byte value) {
            addCriterion("win_rate_bonus <", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusLessThanOrEqualTo(Byte value) {
            addCriterion("win_rate_bonus <=", value, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusIn(List<Byte> values) {
            addCriterion("win_rate_bonus in", values, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusNotIn(List<Byte> values) {
            addCriterion("win_rate_bonus not in", values, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusBetween(Byte value1, Byte value2) {
            addCriterion("win_rate_bonus between", value1, value2, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andWinRateBonusNotBetween(Byte value1, Byte value2) {
            addCriterion("win_rate_bonus not between", value1, value2, "winRateBonus");
            return (Criteria) this;
        }

        public Criteria andImageLinkIsNull() {
            addCriterion("image_link is null");
            return (Criteria) this;
        }

        public Criteria andImageLinkIsNotNull() {
            addCriterion("image_link is not null");
            return (Criteria) this;
        }

        public Criteria andImageLinkEqualTo(String value) {
            addCriterion("image_link =", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkNotEqualTo(String value) {
            addCriterion("image_link <>", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkGreaterThan(String value) {
            addCriterion("image_link >", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkGreaterThanOrEqualTo(String value) {
            addCriterion("image_link >=", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkLessThan(String value) {
            addCriterion("image_link <", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkLessThanOrEqualTo(String value) {
            addCriterion("image_link <=", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkLike(String value) {
            addCriterion("image_link like", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkNotLike(String value) {
            addCriterion("image_link not like", value, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkIn(List<String> values) {
            addCriterion("image_link in", values, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkNotIn(List<String> values) {
            addCriterion("image_link not in", values, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkBetween(String value1, String value2) {
            addCriterion("image_link between", value1, value2, "imageLink");
            return (Criteria) this;
        }

        public Criteria andImageLinkNotBetween(String value1, String value2) {
            addCriterion("image_link not between", value1, value2, "imageLink");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Boolean value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Boolean value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Boolean value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Boolean value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Boolean value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Boolean> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Boolean> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Boolean value1, Boolean value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("created not between", value1, value2, "created");
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