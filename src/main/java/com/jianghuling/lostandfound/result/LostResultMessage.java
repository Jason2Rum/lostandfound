package com.jianghuling.lostandfound.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jianghuling.lostandfound.model.LostTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Scope("prototype")
public class LostResultMessage extends ResultMessage{
    @JsonProperty("lost_list")
    private List<LostTemplate> lostList;

    public List<LostTemplate> getLostList() {
        return lostList;
    }

    public void setLostList(List<LostTemplate> lostList) {
        this.lostList = lostList;
    }
}
