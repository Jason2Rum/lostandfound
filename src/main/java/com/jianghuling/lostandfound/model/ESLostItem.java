package com.jianghuling.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "lost_item_index", type = "doc")
public class ESLostItem implements Serializable  {

    @Id
    private String itemId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String takePlace;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String itemPicture;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String itemDesc;

    @JsonFormat(timezone = "GMT+8", pattern = "MM-dd HH:mm")
    private Date releaseTime;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(String takePlace) {
        this.takePlace = takePlace;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}
