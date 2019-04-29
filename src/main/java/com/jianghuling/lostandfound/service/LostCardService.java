package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.LostStuCardMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;
import com.jianghuling.lostandfound.model.LostStuCard;
import com.jianghuling.lostandfound.model.LostStuCardExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class LostCardService {

    private LostStuCardMapper lostStuCardMapper;
    private SelfDefMapper selfDefMapper;

    @Autowired
    public LostCardService(LostStuCardMapper lostStuCardMapper, SelfDefMapper selfDefMapper) {
        this.lostStuCardMapper = lostStuCardMapper;
        this.selfDefMapper = selfDefMapper;
    }

    @Transactional
    public List<LostStuCard> getLostCardList(int pageNo, int pageSize) {
        return selfDefMapper.selectLostCard(pageNo * pageSize, pageSize);
    }
}
