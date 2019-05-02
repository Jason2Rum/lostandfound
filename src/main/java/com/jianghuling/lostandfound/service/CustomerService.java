package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.UserCommentMapper;
import com.jianghuling.lostandfound.model.UserComment;
import com.jianghuling.lostandfound.model.UserCommentExample;
import com.jianghuling.lostandfound.result.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CustomerService {
    private UserCommentMapper userCommentMapper;

    public CustomerService(UserCommentMapper userCommentMapper) {
        this.userCommentMapper = userCommentMapper;
    }

    public boolean comment(String content, String phone)throws  Exception{
        UserComment userComment = new UserComment();
        userComment.setCommentContent(content);
        userComment.setPhone(phone);
        userCommentMapper.insert(userComment);
        return true;
    }

    public List<UserComment> getAllComment(){
        UserCommentExample userCommentExample = new UserCommentExample();
        return userCommentMapper.selectByExample(userCommentExample);
    }

}
