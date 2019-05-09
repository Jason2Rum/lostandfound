package com.jianghuling.lostandfound.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jianghuling.lostandfound.Constant;

import java.util.Date;

public class LostTemplate implements Comparable {
    private String category;
    @JsonProperty("take_place")
    private String takePlace;
    private String description;
    private String id;
    @JsonIgnore
    private Date releaseTime;
    private Byte state;

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(String takePlace) {
        this.takePlace = takePlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        LostTemplate temp = (LostTemplate) o;
//        if (temp.getState().equals(Constant.CANCEL) && state.equals(Constant.NO_CLAIM)) {
//            return 1;
//        }
        if (temp.getReleaseTime().getTime() > releaseTime.getTime()) return -1;
        else if (temp.getReleaseTime().getTime() == releaseTime.getTime()) return 0;
        else return 1;
    }
}
