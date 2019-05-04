package com.jianghuling.lostandfound.controller;


import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.model.ESLostItem;
import com.jianghuling.lostandfound.model.LostItem;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/lost")
public class LostController {

    private LostItemService lostItemService;

    private LostCardService lostCardService;

    private LostCardListResultMessage lostCardListResultMessage;

    private LostItemListResultMessage lostItemListResultMessage;

    private ResultMessage resultMessage;
    @Autowired
    public LostController(LostItemService lostItemService, ResultMessage resultMessage,LostCardService lostCardService, LostCardListResultMessage lostCardListResultMessage, LostItemListResultMessage lostItemListResultMessage) {
        this.lostItemService = lostItemService;
        this.lostCardService = lostCardService;
        this.lostCardListResultMessage = lostCardListResultMessage;
        this.lostItemListResultMessage = lostItemListResultMessage;
        this.resultMessage = resultMessage;
    }

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

    @RequestMapping("/claim/item")
    @ResponseBody
    public ResultMessage claimItem(String userId,String itemId){
        if (lostItemService.claim(userId,itemId)) {
            resultMessage.setCode(Constant.SUCCESS);
        }else{
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("已被其他人领取");
        }
        return resultMessage;
    }



    @RequestMapping("/item")
    @ResponseBody
    public ResultMessage publishNewLostItem(String desc, String category,String claimMethod, MultipartFile image,String userId){
        try{
            if (lostItemService.publishNewLostItem(category,desc,claimMethod,image,userId)) {
                resultMessage.setCode(Constant.SUCCESS);
            }else{
                resultMessage.setCode(Constant.FAIL);
                resultMessage.setMessage("图片为空");
            }

        }catch (Exception e){
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("发布异常");
            e.printStackTrace();
        }
        return resultMessage;
    }

    @RequestMapping("/card")
    @ResponseBody
    public ResultMessage publishNewLostCard(String name,String userId,String stuId,String takePlace,String college){
        lostCardService.publishNewCardLost(userId,name,college,stuId,takePlace);
        resultMessage.setCode(Constant.SUCCESS);
        return resultMessage;
    }

    @RequestMapping("/searchitem")
    @ResponseBody
    public LostItemListResultMessage searchItem(String description){
        List<LostItem> lostItemList = new ArrayList<>();
        List<ESLostItem> searchList = lostItemService.search(description);
        for(ESLostItem esLostItem: searchList){
            LostItem lostItem = new LostItem();
            lostItem.setItemId(esLostItem.getItemId());
            lostItem.setItemPicture(esLostItem.getItemPicture());
            lostItem.setItemDesc(esLostItem.getItemPicture());
            lostItem.setTakePlace(esLostItem.getTakePlace());
            lostItemList.add(lostItem);
        }
        lostItemListResultMessage.setLostItemList(lostItemList);
        lostItemListResultMessage.setCode(Constant.SUCCESS);
        return lostItemListResultMessage;
    }


}
