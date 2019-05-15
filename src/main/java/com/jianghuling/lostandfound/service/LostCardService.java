package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.LostStuCardMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;

import com.jianghuling.lostandfound.model.LostStuCard;
import com.jianghuling.lostandfound.model.LostStuCardExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.jianghuling.lostandfound.Constant.*;

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
        lostStuCard.setState(NO_CLAIM);
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
        lostStuCard.setTakeTime(new Timestamp(new Date().getTime()));
        lostStuCard.setTakerId(userId);
        lostStuCard.setState(HAS_CLAIMED);

        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andIdEqualTo(cardId).andStateEqualTo(NO_CLAIM);
        if(lostStuCardMapper.updateByExampleSelective(lostStuCard,lostStuCardExample)==1){
            return true;
        }else return false;
    }

    @Transactional
    public boolean cancelPublish(String cardId){
        LostStuCard lostStuCard = new LostStuCard();
        lostStuCard.setState(CANCEL);
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andIdEqualTo(cardId).andStateNotEqualTo(HAS_CLAIMED);
        if(lostStuCardMapper.updateByExampleSelective(lostStuCard,lostStuCardExample)==1){
            return true;
        }else{
            return false;
        }
    }

    public List<LostStuCard> myPickCard(String userId){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andReleaserIdEqualTo(userId);
        return lostStuCardMapper.selectByExample(lostStuCardExample);
    }

    public List<LostStuCard> myPickCardSpecState(String userId,Byte state){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andReleaserIdEqualTo(userId).andStateEqualTo(state);
        return lostStuCardMapper.selectByExample(lostStuCardExample);
    }


    public List<LostStuCard> myLostCard(String userId){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andTakerIdEqualTo(userId);
        return lostStuCardMapper.selectByExample(lostStuCardExample);
    }

    public List<LostStuCard> myLostCardSpecState(String userId,Byte state){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andTakerIdEqualTo(userId).andStateEqualTo(state);
        return lostStuCardMapper.selectByExample(lostStuCardExample);
    }

    public long myLostCardCount(String userId){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andTakerIdEqualTo(userId);
        return lostStuCardMapper.countByExample(lostStuCardExample);
    }

    public long myPickCardCount(String userId){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andReleaserIdEqualTo(userId).andStateNotEqualTo(CANCEL);
        return lostStuCardMapper.countByExample(lostStuCardExample);
    }

    @Transactional
    public boolean cancelClaim(String cardId){
        LostStuCard lostStuCard = new LostStuCard();
        lostStuCard.setId(cardId);
        lostStuCard.setState(NO_CLAIM);
        if(lostStuCardMapper.updateByPrimaryKeySelective(lostStuCard)==1){
            return true;
        }else return false;
    }

    public long countAllLostCards(){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andStateNotEqualTo(CANCEL);
        return lostStuCardMapper.countByExample(lostStuCardExample);
    }

    public long countAllFindLostCards(){
        LostStuCardExample lostStuCardExample = new LostStuCardExample();
        lostStuCardExample.createCriteria().andStateNotEqualTo(HAS_CLAIMED);
        return lostStuCardMapper.countByExample(lostStuCardExample);
    }
}


