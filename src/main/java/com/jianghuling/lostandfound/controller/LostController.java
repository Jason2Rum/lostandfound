package com.jianghuling.lostandfound.controller;


import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.result.LostCardListResultMessage;
import com.jianghuling.lostandfound.result.LostItemListResultMessage;
import com.jianghuling.lostandfound.result.ResultMessage;
import com.jianghuling.lostandfound.service.LostCardService;
import com.jianghuling.lostandfound.service.LostItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/lost")
public class LostController {

    private LostItemService lostItemService;

    private LostCardService lostCardService;

    private LostCardListResultMessage lostCardListResultMessage;

    private LostItemListResultMessage lostItemListResultMessage;

    @RequestMapping("/showInfo")
    @ResponseBody
    public ResultMessage getLost(String category,int pageNo,int pageSize){
        if(category.equals("校园卡")){
            lostCardListResultMessage.setCode(Constant.SUCCESS);
            lostCardListResultMessage.setLostStuCardList(lostCardService.getLostCardList(pageNo,pageSize));
            return lostCardListResultMessage;
        }else{
            lostItemListResultMessage.setCode(Constant.SUCCESS);
            lostItemListResultMessage.setLostItemList(lostItemService.getLostItem(pageNo,pageSize));
            return lostItemListResultMessage;
        }

    }


}
