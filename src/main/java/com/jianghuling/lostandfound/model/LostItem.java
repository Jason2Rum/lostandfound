package com.jianghuling.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LostItem {
    @JsonProperty(value = "item_id")
    private String itemId;

    @JsonIgnore
    private String category;

    private String takePlace;

    @JsonIgnore
    private String finderPhone;

    @JsonProperty(value = "pic_url")
    private String itemPicture;

    @JsonProperty(value = "desc")
    private String itemDesc;

    @JsonFormat(timezone = "GMT+8", pattern = "MM-dd HH:mm")
    private Date releaseTime;

    @JsonIgnore
    private Date claimTime;

    @JsonIgnore
    private String takerId;

    @JsonIgnore
    private Byte state;

    @JsonIgnore
    private String releaserId;

    @JsonIgnore
    private Date updateTime;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(String takePlace) {
        this.takePlace = takePlace == null ? null : takePlace.trim();
    }

    public String getFinderPhone() {
        return finderPhone;
    }

    public void setFinderPhone(String finderPhone) {
        this.finderPhone = finderPhone == null ? null : finderPhone.trim();
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture == null ? null : itemPicture.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    public String getTakerId() {
        return takerId;
    }

    public void setTakerId(String takerId) {
        this.takerId = takerId == null ? null : takerId.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getReleaserId() {
        return releaserId;
    }

    public void setReleaserId(String releaserId) {
        this.releaserId = releaserId == null ? null : releaserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}