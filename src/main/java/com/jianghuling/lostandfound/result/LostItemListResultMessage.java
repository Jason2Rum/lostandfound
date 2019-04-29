package com.jianghuling.lostandfound.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jianghuling.lostandfound.model.LostItem;

import java.util.List;

public class LostItemListResultMessage extends ResultMessage {

    @JsonProperty(value = "lost_item_list")
    private List<LostItem> lostItemList;

    public List<LostItem> getLostItemList() {
        return lostItemList;
    }

    public void setLostItemList(List<LostItem> lostItemList) {
        this.lostItemList = lostItemList;
    }
}
