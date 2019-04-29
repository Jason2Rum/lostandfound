package com.jianghuling.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LostStuCard {
    private String id;

    private String name;

    private String college;

    @JsonProperty("time")
    private Date releaseTime;

    private String stuId;

    @JsonIgnore
    private Date takeTime;

    @JsonIgnore
    private String takerId;

    @JsonIgnore
    private Byte state;

    private String takePlace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
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

    public String getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(String takePlace) {
        this.takePlace = takePlace == null ? null : takePlace.trim();
    }
}