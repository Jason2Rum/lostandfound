package com.jianghuling.lostandfound.controller;

import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.model.UserComment;
import com.jianghuling.lostandfound.result.ResultMessage;
import com.jianghuling.lostandfound.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cst")
public class CustomerController {
    private CustomerService customerService;
    private ResultMessage resultMessage;

    public CustomerController(CustomerService customerService, ResultMessage resultMessage) {
        this.customerService = customerService;
        this.resultMessage = resultMessage;
    }

    @ResponseBody
    @RequestMapping("/comment")
    public ResultMessage comment(String content, String phone) {
        try {
            if (customerService.comment(content, phone)) {
                resultMessage.setCode(Constant.SUCCESS);
            }
        } catch (Exception e) {
            resultMessage.setCode(Constant.FAIL);
            resultMessage.setMessage("评论异常");
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping("/allComment")
    public List<UserComment> allComment(){
        return customerService.getAllComment();
    }
}
