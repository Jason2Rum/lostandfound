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
import org.springframework.web.multipart.MultipartFile;

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
    public ResultMessage publicNewLostItem(String desc, String category,String claimMethod, MultipartFile image,String userId){
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




}
