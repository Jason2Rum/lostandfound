package com.jianghuling.lostandfound.controller;

import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.model.UserInfo;
import com.jianghuling.lostandfound.result.LoginResultMessage;
import com.jianghuling.lostandfound.result.MineResultMessage;
import com.jianghuling.lostandfound.result.PrivateInfoResultMessage;
import com.jianghuling.lostandfound.result.ResultMessage;
import com.jianghuling.lostandfound.service.AccountService;
import com.jianghuling.lostandfound.service.LostCardService;
import com.jianghuling.lostandfound.service.LostItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/account")
@ResponseBody
public class AccountController {

    private AccountService accountService;
    private LostCardService lostCardService;
    private ResultMessage resultMessage;
    private LoginResultMessage loginResultMessage;
    private LostItemService lostItemService;
    private MineResultMessage mineResultMessage;

    @Autowired
    public AccountController(AccountService accountService, LostCardService lostCardService, ResultMessage resultMessage, LoginResultMessage loginResultMessage, LostItemService lostItemService, MineResultMessage mineResultMessage) {
        this.accountService = accountService;
        this.lostCardService = lostCardService;
        this.resultMessage = resultMessage;
        this.loginResultMessage = loginResultMessage;
        this.lostItemService = lostItemService;
        this.mineResultMessage = mineResultMessage;
    }



    @RequestMapping("/login")
    public LoginResultMessage login(String jscode) {
        try {
            ResultMessage tempMsg = accountService.login(jscode);
            loginResultMessage.setUserId(tempMsg.getMessage());//service中将userid存储在message中
            loginResultMessage.setCode(Constant.SUCCESS);

            if (tempMsg.getCode() == Constant.SUCCESS) {//不是第一次登录
                loginResultMessage.setLostCardList(lostCardService.getLostCardList(0, 10));
            }

        } catch (Exception e) {

            log.info("登陆异常");
            e.printStackTrace();
            loginResultMessage.setCode(Constant.FAIL);
            loginResultMessage.setMessage("login异常");

        }
        return loginResultMessage;

    }

    @RequestMapping("/firstEntrance")
    public LoginResultMessage firstEntrance(String userId,String university){

        if (accountService.bindUniversity(userId,university)) {
            loginResultMessage.setCode(Constant.SUCCESS);
            loginResultMessage.setLostCardList(lostCardService.getLostCardList(0,10));
        }else{
            loginResultMessage.setCode(Constant.FAIL);
            loginResultMessage.setMessage("绑定学校错误");
        }
        return loginResultMessage;
    }

    @RequestMapping("/bindUniv")
    public ResultMessage bindUniv(String userId,String university){
        if (accountService.bindUniversity(userId,university)) {
            resultMessage.setCode(Constant.SUCCESS);

        }else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("绑定学校失败");
        }
        return resultMessage;
    }

    @RequestMapping("/bindCollege")
    public ResultMessage bindCollege(String userId,String college){
        if (accountService.bindCollege(userId,college)) {
            resultMessage.setCode(Constant.SUCCESS);

        }else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("绑定学院失败");
        }
        return resultMessage;
    }

    @RequestMapping("/bindPhone")
    public ResultMessage bindPhone(String userId,String phone){
        if (accountService.bindPhone(userId,phone)) {
            resultMessage.setCode(Constant.SUCCESS);

        }else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("绑定手机失败");
        }
        return resultMessage;
    }

    @RequestMapping("/bindStuId")
    public ResultMessage bindStuId(String userId,String stuId){
        if (accountService.bindStuId(userId,stuId)) {
            resultMessage.setCode(Constant.SUCCESS);

        }else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("绑定学号失败");
        }
        return resultMessage;
    }

    @RequestMapping("/privateInfo")
    public PrivateInfoResultMessage getUserInfo(String userId){
        UserInfo u = accountService.getUserInfo(userId);
        PrivateInfoResultMessage res = new PrivateInfoResultMessage();
        res.setCollege(u.getCollege());
        res.setName(u.getName());
        res.setPhone(u.getPhone());
        res.setStuId(u.getStuId());
        res.setUniversity(u.getUniversity());
        res.setCode(Constant.SUCCESS);
        return res;
    }

    @RequestMapping("/mine")
    public MineResultMessage getMine(String userId){
        mineResultMessage.setFindNum(lostCardService.myPickCardCount(userId)+lostItemService.myPickItemCount(userId));
        mineResultMessage.setLostNum(lostCardService.myLostCardCount(userId)+lostItemService.myLostItemCount(userId));
        mineResultMessage.setCode(Constant.SUCCESS);
        return mineResultMessage;
    }

    @RequestMapping("/bindName")
    public ResultMessage bindName(String userId,String name){
        if (accountService.bindName(userId,name)) {
            resultMessage.setCode(Constant.SUCCESS);

        }else {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("绑定姓名失败");
        }
        return resultMessage;
    }

}
