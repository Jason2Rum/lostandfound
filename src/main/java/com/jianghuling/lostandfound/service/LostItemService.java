package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.LostItemMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;
import com.jianghuling.lostandfound.model.LostItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LostItemService {
    @Autowired
    private LostItemMapper lostItemMapper;
    @Autowired
    private SelfDefMapper selfDefMapper;

    public List<LostItem> getLostItem(int pageNo,int pageSize){
        return selfDefMapper.selectLostItem(pageNo*pageSize,pageSize);
    }
}
