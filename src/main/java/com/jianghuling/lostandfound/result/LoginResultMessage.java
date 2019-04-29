package com.jianghuling.lostandfound.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jianghuling.lostandfound.model.LostStuCard;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Scope("prototype")
@Component
public class LoginResultMessage extends ResultMessage {

    @JsonProperty(value = "lost_card_list")
    private List<LostStuCard> lostCardList;

    private String userId;

    public List<LostStuCard> getLostCardList() {
        return lostCardList;
    }

    public void setLostCardList(List<LostStuCard> lostCardList) {
        this.lostCardList = lostCardList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
