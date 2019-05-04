package com.jianghuling.lostandfound.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jianghuling.lostandfound.model.LostStuCard;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LostCardListResultMessage extends ResultMessage {

    @JsonProperty(value = "lost_card_list")
    private List<LostStuCard> lostStuCardList;

    public List<LostStuCard> getLostStuCardList() {
        return lostStuCardList;
    }

    public void setLostStuCardList(List<LostStuCard> lostStuCardList) {
        this.lostStuCardList = lostStuCardList;
    }
}
