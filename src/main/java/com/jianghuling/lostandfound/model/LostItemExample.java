package com.jianghuling.lostandfound.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LostItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LostItemExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(Byte value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(Byte value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(Byte value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(Byte value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(Byte value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(Byte value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<Byte> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<Byte> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(Byte value1, Byte value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(Byte value1, Byte value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andTakePlaceIsNull() {
            addCriterion("take_place is null");
            return (Criteria) this;
        }

        public Criteria andTakePlaceIsNotNull() {
            addCriterion("take_place is not null");
            return (Criteria) this;
        }

        public Criteria andTakePlaceEqualTo(String value) {
            addCriterion("take_place =", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotEqualTo(String value) {
            addCriterion("take_place <>", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceGreaterThan(String value) {
            addCriterion("take_place >", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("take_place >=", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceLessThan(String value) {
            addCriterion("take_place <", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceLessThanOrEqualTo(String value) {
            addCriterion("take_place <=", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceLike(String value) {
            addCriterion("take_place like", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotLike(String value) {
            addCriterion("take_place not like", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceIn(List<String> values) {
            addCriterion("take_place in", values, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotIn(List<String> values) {
            addCriterion("take_place not in", values, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceBetween(String value1, String value2) {
            addCriterion("take_place between", value1, value2, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotBetween(String value1, String value2) {
            addCriterion("take_place not between", value1, value2, "takePlace");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneIsNull() {
            addCriterion("finder_phone is null");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneIsNotNull() {
            addCriterion("finder_phone is not null");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneEqualTo(String value) {
            addCriterion("finder_phone =", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneNotEqualTo(String value) {
            addCriterion("finder_phone <>", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneGreaterThan(String value) {
            addCriterion("finder_phone >", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("finder_phone >=", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneLessThan(String value) {
            addCriterion("finder_phone <", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneLessThanOrEqualTo(String value) {
            addCriterion("finder_phone <=", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneLike(String value) {
            addCriterion("finder_phone like", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneNotLike(String value) {
            addCriterion("finder_phone not like", value, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneIn(List<String> values) {
            addCriterion("finder_phone in", values, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneNotIn(List<String> values) {
            addCriterion("finder_phone not in", values, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneBetween(String value1, String value2) {
            addCriterion("finder_phone between", value1, value2, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andFinderPhoneNotBetween(String value1, String value2) {
            addCriterion("finder_phone not between", value1, value2, "finderPhone");
            return (Criteria) this;
        }

        public Criteria andItemPictureIsNull() {
            addCriterion("item_picture is null");
            return (Criteria) this;
        }

        public Criteria andItemPictureIsNotNull() {
            addCriterion("item_picture is not null");
            return (Criteria) this;
        }

        public Criteria andItemPictureEqualTo(String value) {
            addCriterion("item_picture =", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureNotEqualTo(String value) {
            addCriterion("item_picture <>", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureGreaterThan(String value) {
            addCriterion("item_picture >", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureGreaterThanOrEqualTo(String value) {
            addCriterion("item_picture >=", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureLessThan(String value) {
            addCriterion("item_picture <", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureLessThanOrEqualTo(String value) {
            addCriterion("item_picture <=", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureLike(String value) {
            addCriterion("item_picture like", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureNotLike(String value) {
            addCriterion("item_picture not like", value, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureIn(List<String> values) {
            addCriterion("item_picture in", values, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureNotIn(List<String> values) {
            addCriterion("item_picture not in", values, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureBetween(String value1, String value2) {
            addCriterion("item_picture between", value1, value2, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemPictureNotBetween(String value1, String value2) {
            addCriterion("item_picture not between", value1, value2, "itemPicture");
            return (Criteria) this;
        }

        public Criteria andItemDescIsNull() {
            addCriterion("item_desc is null");
            return (Criteria) this;
        }

        public Criteria andItemDescIsNotNull() {
            addCriterion("item_desc is not null");
            return (Criteria) this;
        }

        public Criteria andItemDescEqualTo(String value) {
            addCriterion("item_desc =", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotEqualTo(String value) {
            addCriterion("item_desc <>", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescGreaterThan(String value) {
            addCriterion("item_desc >", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescGreaterThanOrEqualTo(String value) {
            addCriterion("item_desc >=", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLessThan(String value) {
            addCriterion("item_desc <", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLessThanOrEqualTo(String value) {
            addCriterion("item_desc <=", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLike(String value) {
            addCriterion("item_desc like", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotLike(String value) {
            addCriterion("item_desc not like", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescIn(List<String> values) {
            addCriterion("item_desc in", values, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotIn(List<String> values) {
            addCriterion("item_desc not in", values, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescBetween(String value1, String value2) {
            addCriterion("item_desc between", value1, value2, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotBetween(String value1, String value2) {
            addCriterion("item_desc not between", value1, value2, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNull() {
            addCriterion("release_time is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("release_time is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(Date value) {
            addCriterion("release_time =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(Date value) {
            addCriterion("release_time <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(Date value) {
            addCriterion("release_time >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("release_time >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(Date value) {
            addCriterion("release_time <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("release_time <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<Date> values) {
            addCriterion("release_time in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<Date> values) {
            addCriterion("release_time not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("release_time between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("release_time not between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeIsNull() {
            addCriterion("claim_time is null");
            return (Criteria) this;
        }

        public Criteria andClaimTimeIsNotNull() {
            addCriterion("claim_time is not null");
            return (Criteria) this;
        }

        public Criteria andClaimTimeEqualTo(Date value) {
            addCriterion("claim_time =", value, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeNotEqualTo(Date value) {
            addCriterion("claim_time <>", value, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeGreaterThan(Date value) {
            addCriterion("claim_time >", value, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("claim_time >=", value, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeLessThan(Date value) {
            addCriterion("claim_time <", value, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeLessThanOrEqualTo(Date value) {
            addCriterion("claim_time <=", value, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeIn(List<Date> values) {
            addCriterion("claim_time in", values, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeNotIn(List<Date> values) {
            addCriterion("claim_time not in", values, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeBetween(Date value1, Date value2) {
            addCriterion("claim_time between", value1, value2, "claimTime");
            return (Criteria) this;
        }

        public Criteria andClaimTimeNotBetween(Date value1, Date value2) {
            addCriterion("claim_time not between", value1, value2, "claimTime");
            return (Criteria) this;
        }

        public Criteria andTakerIdIsNull() {
            addCriterion("taker_id is null");
            return (Criteria) this;
        }

        public Criteria andTakerIdIsNotNull() {
            addCriterion("taker_id is not null");
            return (Criteria) this;
        }

        public Criteria andTakerIdEqualTo(String value) {
            addCriterion("taker_id =", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotEqualTo(String value) {
            addCriterion("taker_id <>", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdGreaterThan(String value) {
            addCriterion("taker_id >", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdGreaterThanOrEqualTo(String value) {
            addCriterion("taker_id >=", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdLessThan(String value) {
            addCriterion("taker_id <", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdLessThanOrEqualTo(String value) {
            addCriterion("taker_id <=", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdLike(String value) {
            addCriterion("taker_id like", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotLike(String value) {
            addCriterion("taker_id not like", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdIn(List<String> values) {
            addCriterion("taker_id in", values, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotIn(List<String> values) {
            addCriterion("taker_id not in", values, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdBetween(String value1, String value2) {
            addCriterion("taker_id between", value1, value2, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotBetween(String value1, String value2) {
            addCriterion("taker_id not between", value1, value2, "takerId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Byte value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Byte value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Byte value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Byte value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Byte value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Byte> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Byte> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Byte value1, Byte value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Byte value1, Byte value2) {
            addCriterion("state not between", value1, value2, "state");
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