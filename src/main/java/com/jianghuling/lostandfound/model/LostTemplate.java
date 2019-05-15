package com.jianghuling.lostandfound.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import static com.jianghuling.lostandfound.Constant.NO_CLAIM;


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

    /**
     * 返回-1：把“我”排到前面
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        LostTemplate temp = (LostTemplate) o;
//        if (state.equals(NO_CLAIM) && !((LostTemplate) o).getState().equals(NO_CLAIM)) {
//            return -1;
//        } else {
            if (releaseTime.getTime() > temp.getReleaseTime().getTime())
                return -1;//当前发布时间比较大，应该把当前记录放到前面，因为默认是升序排序，返回-1则是相当于欺骗系统当前记录小
            else if (temp.getReleaseTime().getTime() == releaseTime.getTime()) return 0;
            else return 1;
//        }

    }
}
