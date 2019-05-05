package com.jianghuling.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "lost_card_index",type = "doc")
public class ESLostStuCard implements Serializable {
    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Text)
    private String college;

    @Id
    private String id;

    @Field(type = FieldType.Date)
    @JsonProperty("release_time")
    private Date  releaseTime;

    @Field(type = FieldType.Keyword)
    @JsonProperty("stu_id")
    private String stuId;

    @JsonProperty("take_place")
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String takePlace;

    public ESLostStuCard(String name, String college, String id, Date releaseTime, String stuId, String takePlace) {
        this.name = name;
        this.college = college;
        this.id = id;
        this.releaseTime = releaseTime;
        this.stuId = stuId;
        this.takePlace = takePlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.stuId = stuId;
    }

    public String getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(String takePlace) {
        this.takePlace = takePlace;
    }
}
