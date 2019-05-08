package com.jianghuling.lostandfound.controller;


import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.model.ESLostItem;
import com.jianghuling.lostandfound.model.LostItem;
import com.jianghuling.lostandfound.model.LostStuCard;
import com.jianghuling.lostandfound.model.LostTemplate;
import com.jianghuling.lostandfound.result.LostCardListResultMessage;
import com.jianghuling.lostandfound.result.LostItemListResultMessage;
import com.jianghuling.lostandfound.result.LostResultMessage;
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
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/lost")
@ResponseBody
public class LostController {

    private LostItemService lostItemService;

    private LostCardService lostCardService;

    private LostCardListResultMessage lostCardListResultMessage;

    private LostItemListResultMessage lostItemListResultMessage;

    private ResultMessage resultMessage;

    private LostResultMessage lostResultMessage;

    @Autowired
    public LostController(LostItemService lostItemService, LostCardService lostCardService, LostCardListResultMessage lostCardListResultMessage, LostItemListResultMessage lostItemListResultMessage, ResultMessage resultMessage, LostResultMessage lostResultMessage) {
        this.lostItemService = lostItemService;
        this.lostCardService = lostCardService;
        this.lostCardListResultMessage = lostCardListResultMessage;
        this.lostItemListResultMessage = lostItemListResultMessage;
        this.resultMessage = resultMessage;
        this.lostResultMessage = lostResultMessage;
    }


    @RequestMapping("/showInfo")
    public ResultMessage getLost(String category, int pageNo, int pageSize) {
        if (category.equals("校园卡")) {
            lostCardListResultMessage.setCode(Constant.SUCCESS);
            lostCardListResultMessage.setLostStuCardList(lostCardService.getLostCardList(pageNo, pageSize));
            return lostCardListResultMessage;
        } else {
            lostItemListResultMessage.setCode(Constant.SUCCESS);
            lostItemListResultMessage.setLostItemList(lostItemService.getLostItem(category, pageNo, pageSize));
            return lostItemListResultMessage;
        }

    }

    @RequestMapping("/searchcard")
    public LostCardListResultMessage searchCard(String name) {
        List<LostStuCard> lostStuCards = lostCardService.search(name);
        lostCardListResultMessage.setCode(Constant.SUCCESS);
        lostCardListResultMessage.setLostStuCardList(lostStuCards);
        return lostCardListResultMessage;
    }

    @RequestMapping("/claim/item")
    public ResultMessage claimItem(String userId, String itemId) {
        if (lostItemService.claim(userId, itemId)) {
            resultMessage.setCode(Constant.SUCCESS);
        } else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("已被其他人领取");
        }
        return resultMessage;
    }

    @RequestMapping("/claim/card")
    public ResultMessage claimCard(String userId, String cardId) {
        lostCardService.claimCard(userId, cardId);
        resultMessage.setCode(Constant.SUCCESS);
        return resultMessage;
    }


    @RequestMapping("/item")
    public ResultMessage publishNewLostItem(String desc, String category, String claimMethod, MultipartFile image, String userId) {
        try {
            if (lostItemService.publishNewLostItem(category, desc, claimMethod, image, userId)) {
                resultMessage.setCode(Constant.SUCCESS);
            } else {
                resultMessage.setCode(Constant.FAIL);
                resultMessage.setMessage("图片为空");
            }

        } catch (Exception e) {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("发布异常");
            e.printStackTrace();
        }
        return resultMessage;
    }

    @RequestMapping("/card")
    public ResultMessage publishNewLostCard(String name, String userId, String stuId, String takePlace, String college) {
        lostCardService.publishNewCardLost(userId, name, college, stuId, takePlace);
        resultMessage.setCode(Constant.SUCCESS);
        return resultMessage;
    }

    @RequestMapping("/searchItem")
    public LostItemListResultMessage searchItem(String description) {
        List<LostItem> lostItemList = new ArrayList<>();
        List<ESLostItem> searchList = lostItemService.search(description);
        for (ESLostItem esLostItem : searchList) {
            LostItem lostItem = new LostItem();
            lostItem.setItemId(esLostItem.getItemId());
            lostItem.setItemPicture(esLostItem.getItemPicture());
            lostItem.setItemDesc(esLostItem.getItemDesc());
            lostItem.setTakePlace(esLostItem.getTakePlace());
            lostItemList.add(lostItem);
        }
        lostItemListResultMessage.setLostItemList(lostItemList);
        lostItemListResultMessage.setCode(Constant.SUCCESS);
        return lostItemListResultMessage;
    }

    @RequestMapping("/cancelPub")
    public ResultMessage cancelPublish(String id, String category) {
        boolean b;
        if (category.equals("校园卡")) {
            b = lostCardService.cancelPublish(id);
        } else {
            b = lostItemService.cancelPublish(id);
        }
        if (b) {
            resultMessage.setCode(Constant.SUCCESS);
        } else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("取消失败");
        }
        return resultMessage;
    }

    @RequestMapping("/myPick")
    public LostResultMessage myPick(String userId, int pageNo, int pageSize) {

        List<LostItem> lostItems = lostItemService.myLostItem(userId);
        List<LostStuCard> lostStuCards = lostCardService.myLostCard(userId);
        return dataResort(lostItems, lostStuCards, pageNo, pageSize);
    }

    @RequestMapping("/myLost")
    public LostResultMessage myLost(String userId, int pageNo, int pageSize) {
        List<LostItem> lostItems = lostItemService.myPickItems(userId);
        List<LostStuCard> lostStuCards = lostCardService.myPickCard(userId);

        return dataResort(lostItems, lostStuCards, pageNo, pageSize);
    }

    private LostResultMessage dataResort(List<LostItem> lostItems, List<LostStuCard> lostStuCards, int pageNo, int pageSize) {

        List<LostTemplate> lostList = new ArrayList<>();

        for (LostItem o : lostItems) {
            LostTemplate temp = new LostTemplate();
            temp.setCategory(o.getCategory());
            temp.setId(o.getItemId());
            temp.setDescription(o.getItemDesc());
            temp.setTakePlace(o.getTakePlace());
            temp.setReleaseTime(o.getReleaseTime());
            lostList.add(temp);
        }
        for (LostStuCard o : lostStuCards) {
            LostTemplate temp = new LostTemplate();
            temp.setCategory("校园卡");
            temp.setId(o.getId());
            temp.setDescription(o.getCollege() + " " + o.getName());
            temp.setTakePlace(o.getTakePlace());
            temp.setReleaseTime(o.getReleaseTime());
            lostList.add(temp);
        }
        Collections.sort(lostList);
        if (lostList.size()>= pageSize * (pageNo + 1)) {
            lostResultMessage.setLostList(lostList.subList(pageNo * pageSize, pageSize * (pageNo + 1)));

        } else if(lostList.size()>pageSize*pageNo) {
            lostResultMessage.setLostList(lostList.subList(pageNo * pageSize,lostList.size()));
        }else{
            lostList = new ArrayList<>();
            lostResultMessage.setLostList(lostList);
        }
        lostResultMessage.setCode(Constant.SUCCESS);
        return lostResultMessage;
    }

    @RequestMapping("/cancelClm")
    public ResultMessage cancelClaim(String id, String category) {
        boolean b;
        if (category.equals("校园卡")) {
            b = lostCardService.cancelClaim(id);
        } else {
            b = lostItemService.cancelClaim(id);
        }
        if (b) {
            resultMessage.setCode(Constant.SUCCESS);
        } else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("取消失败");
        }
        return resultMessage;
    }

}
