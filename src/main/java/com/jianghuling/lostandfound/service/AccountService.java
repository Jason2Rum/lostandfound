package com.jianghuling.lostandfound.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jianghuling.lostandfound.Constant;
import com.jianghuling.lostandfound.dao.UserInfoMapper;
import com.jianghuling.lostandfound.model.UserInfo;
import com.jianghuling.lostandfound.result.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.jianghuling.lostandfound.Constant.*;

@Slf4j
@Service
public class AccountService {

    private UserInfoMapper userInfoMapper;
    private ResultMessage resultMessage;

    @Autowired
    public AccountService(UserInfoMapper userInfoMapper, ResultMessage resultMessage) {
        this.userInfoMapper = userInfoMapper;
        this.resultMessage = resultMessage;
    }

    /**
     * @param jsCode
     * @return 用户id 与 是否首次登录flag
     * @throws Exception
     */

    @Transactional
    public ResultMessage login(String jsCode) throws Exception {
        String result;
        result = getOpenidAndSessionKey(jsCode);

        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.getString("openid");

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(openid);
        if (userInfo != null) {//非第一次登录
            resultMessage.setCode(SUCCESS);
        } else {//第一次登录
            userInfo = new UserInfo();
            userInfo.setUserId(openid);
            userInfoMapper.insertSelective(userInfo);
            resultMessage.setCode(FIRST_LOG_IN);
        }
        resultMessage.setMessage(openid);
        return resultMessage;
    }

    private String getOpenidAndSessionKey(String resCode) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APPID + "&secret=" + Constant.SECRET + "&js_code=" + resCode + "&grant_type=authorization_code";

            URL url = new URL(urlNameString);
            URLConnection connection = url.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return result;
    }

    @Transactional
    public boolean bindUniversity(String userId, String university) {
        UserInfo u = new UserInfo();
        u.setUserId(userId);
        u.setUniversity(university);
        if (userInfoMapper.updateByPrimaryKeySelective(u) == 1)
            return true;
        else return false;
    }

    @Transactional
    public boolean bindCollege(String userId ,String college ){
        UserInfo u = new UserInfo();
        u.setCollege(college);
        u.setUserId(userId);
        if (userInfoMapper.updateByPrimaryKeySelective(u) == 1)
            return true;
        else return false;
    }

    @Transactional
    public boolean bindStuId(String userId,String stuId){
        UserInfo u = new UserInfo();
        u.setUserId(userId);
        u.setStuId(stuId);
        if (userInfoMapper.updateByPrimaryKeySelective(u) == 1)
            return true;
        else return false;
    }

    @Transactional
    public boolean bindPhone(String userId,String phone){
        UserInfo u = new UserInfo();
        u.setPhone(phone);
        u.setUserId(userId);
        if (userInfoMapper.updateByPrimaryKeySelective(u) == 1)
            return true;
        else return false;
    }

    public UserInfo getUserInfo(String userId){
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    public boolean bindName(String userId,String name){
        UserInfo u = new UserInfo();
        u.setName(name);
        u.setUserId(userId);
        if (userInfoMapper.updateByPrimaryKeySelective(u) == 1)
            return true;
        else return false;
    }



}
