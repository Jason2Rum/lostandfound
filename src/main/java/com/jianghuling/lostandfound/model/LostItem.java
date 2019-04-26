package com.jianghuling.lostandfound.model;

import java.util.Date;

public class LostItem {
    private String itemId;

    private Byte category;

    private String takePlace;

    private String finderPhone;

    private String itemPicture;

    private String itemDesc;

    private Date releaseTime;

    private Date claimTime;

    private String takerId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
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
}