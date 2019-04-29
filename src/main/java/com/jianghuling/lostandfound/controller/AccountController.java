package com.jianghuling.lostandfound.controller;

import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.result.LoginResultMessage;
import com.jianghuling.lostandfound.result.ResultMessage;
import com.jianghuling.lostandfound.service.AccountService;
import com.jianghuling.lostandfound.service.LostCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private LostCardService lostCardService;
    private ResultMessage resultMessage;
    private LoginResultMessage loginResultMessage;

    @Autowired
    public AccountController(AccountService accountService, LostCardService lostCardService, ResultMessage resultMessage, LoginResultMessage loginResultMessage) {
        this.accountService = accountService;
        this.lostCardService = lostCardService;
        this.resultMessage = resultMessage;
        this.loginResultMessage = loginResultMessage;
    }

    @ResponseBody
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


}
