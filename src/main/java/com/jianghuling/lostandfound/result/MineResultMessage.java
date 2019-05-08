package com.jianghuling.lostandfound.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MineResultMessage extends ResultMessage{
    @JsonProperty("lost_num")
    private long lostNum;
    @JsonProperty("find_num")
    private long findNum;

    public long getLostNum() {
        return lostNum;
    }

    public void setLostNum(long lostNum) {
        this.lostNum = lostNum;
    }

    public long getFindNum() {
        return findNum;
    }

    public void setFindNum(long findNum) {
        this.findNum = findNum;
    }
}
