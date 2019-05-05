package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.ESLostStuCardRepository;
import com.jianghuling.lostandfound.dao.LostStuCardMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;
import com.jianghuling.lostandfound.model.ESLostStuCard;
import com.jianghuling.lostandfound.model.LostStuCard;
import com.jianghuling.lostandfound.model.LostStuCardExample;
import com.jianghuling.lostandfound.result.LostCardListResultMessage;
import com.jianghuling.lostandfound.result.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.jianghuling.lostandfound.Constant.HAS_CLAIMED;

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

    @Transactional
    public boolean publishNewCardLost(String userId,String name,String college,String stuId,String takePlace){
        LostStuCard lostStuCard = new LostStuCard();
        lostStuCard.setId(UUID.randomUUID().toString());
        lostStuCard.setCollege(college);
        lostStuCard.setReleaserId(userId);
        lostStuCard.setTakePlace(takePlace);
        lostStuCard.setName(name);
        lostStuCard.setStuId(stuId);
        lostStuCard.setReleaseTime(new Time(new Date().getTime()));
        lostStuCardMapper.insert(lostStuCard);

//        ESLostStuCard esLostStuCard = new ESLostStuCard(name,college,userId,lostStuCard.getId(),lostStuCard.getReleaseTime(),stuId,takePlace);
//        esLostStuCardRepository.save(esLostStuCard);//存到索引中
        return true;
    }

    /**
     *
     * @param name 失主姓名
     * @return
     */
    @Transactional
    public List<LostStuCard> search(String name){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andNameEqualTo(name);
        return lostStuCardMapper.selectByExample(lostStuCardExample);
    }

    @Transactional
    public boolean claimCard(String userId,String cardId){
        LostStuCard lostStuCard = new LostStuCard();
        lostStuCard.setId(cardId);
        lostStuCard.setTakeTime(new Timestamp(new Date().getTime()));
        lostStuCard.setTakerId(userId);
        lostStuCard.setState(HAS_CLAIMED);
        lostStuCardMapper.updateByPrimaryKeySelective(lostStuCard);

        return true;
    }
}
