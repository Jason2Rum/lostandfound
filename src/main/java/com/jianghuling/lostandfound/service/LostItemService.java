package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.ESLostItemRepository;
import com.jianghuling.lostandfound.dao.LostItemMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;
import com.jianghuling.lostandfound.model.*;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.jianghuling.lostandfound.Constant.*;

@Service
@Slf4j
public class LostItemService {
    private LostItemMapper lostItemMapper;
    private SelfDefMapper selfDefMapper;
    private ESLostItemRepository esLostItemRepository;

    @Autowired
    public LostItemService(LostItemMapper lostItemMapper, SelfDefMapper selfDefMapper, ESLostItemRepository esLostItemRepository) {
        this.lostItemMapper = lostItemMapper;
        this.selfDefMapper = selfDefMapper;
        this.esLostItemRepository = esLostItemRepository;
    }

    @Transactional
    public List<LostItem> getLostItem(String category,int pageNo, int pageSize) {
        List<LostItem> list = selfDefMapper.selectLostItem(pageNo * pageSize, pageSize,category);
        for(LostItem item:list){
            item.setItemPicture(item.getItemPicture());
        }
        return list;
    }

    /**
     * 认领其他物品
     *
     * @param userId 认领者id
     * @param itemId 丢失物品id
     * @return 利用state充当乐观锁标记
     */
    @Transactional
    public boolean claim(String userId, String itemId) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        LostItem claimItem = new LostItem();
        claimItem.setTakerId(userId);
        claimItem.setState(HAS_CLAIMED);
        claimItem.setClaimTime(timestamp);

        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.createCriteria().andStateEqualTo(NO_CLAIM).andItemIdEqualTo(itemId);
        lostItemMapper.updateByExampleSelective(claimItem, lostItemExample);

        if (lostItemMapper.updateByPrimaryKeySelective(claimItem) == 1)
            return true;
        else return false;
    }

    @Transactional
    public boolean publishNewLostItem(String category, String desc, String claimMethod, MultipartFile picFile,String userId) throws Exception {
        if (picFile.isEmpty()) {
            return false;
        } else {
            String namePrefix = UUID.randomUUID().toString().substring(0,7);
            String oriFileName = picFile.getOriginalFilename();
            String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = time + oriFileName.substring(oriFileName.lastIndexOf("."));
            File dest = new File(UPLOAD_IMG_LOC + namePrefix+fileName);
            File temp = new File(UPLOAD_IMG_LOC +"temp"+UUID.randomUUID().toString().substring(0,6)+ fileName);
            picFile.transferTo(temp);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            Thumbnails.of(temp)
                    .scale(1f)
                    .outputQuality(0.5f)
                    .toFile(dest);//图片压缩

            temp.delete();
            LostItem lostItem = new LostItem();

            lostItem.setItemId(UUID.randomUUID().toString());
            lostItem.setCategory(category);
            lostItem.setTakePlace(claimMethod);
            lostItem.setState(NO_CLAIM);
            lostItem.setItemDesc(desc);
            lostItem.setItemPicture("http://jianghuling.top/lostimages/"+ dest.getName());
            lostItem.setReleaseTime(new Timestamp(new Date().getTime()));
            lostItem.setReleaserId(userId);
            //发布时间数据库有默认的now()

            /*------插入到索引------------*/
            ESLostItem esLostItem = new ESLostItem();
            esLostItem.setItemDesc(desc);
            esLostItem.setItemId(lostItem.getItemId());
            esLostItem.setReleaseTime(lostItem.getReleaseTime());
            esLostItem.setItemPicture(lostItem.getItemPicture());
            esLostItem.setTakePlace(lostItem.getTakePlace());

            lostItemMapper.insertSelective(lostItem);
            esLostItemRepository.save(esLostItem);

            return true;
        }
    }

    @Transactional
    public List<ESLostItem> search(String description){

        return esLostItemRepository.findTop5ByItemDesc(description);
    }

    @Transactional
    public boolean cancelPublish(String itemId){
        LostItem lostItem = new LostItem();
        lostItem.setState(CANCEL);
        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.createCriteria().andItemIdEqualTo(itemId).andStateNotEqualTo(HAS_CLAIMED);
        if(lostItemMapper.updateByExampleSelective(lostItem,lostItemExample)==1){
            esLostItemRepository.deleteById(itemId);//从es中删除索引
            return true;
        }else{
            return false;
        }
    }

    /**
     *  我捡到的
     * @param userId
     * @return
     */
    public List<LostItem> myPickItems(String userId){
        LostItemExample lostItemExample =new LostItemExample();
        lostItemExample.createCriteria().andReleaserIdEqualTo(userId);//也显示取消的
        return lostItemMapper.selectByExample(lostItemExample);
    }

    public List<LostItem> myPickItemsSpecState(String userId,Byte state){
        LostItemExample lostItemExample =new LostItemExample();
        lostItemExample.createCriteria().andReleaserIdEqualTo(userId).andStateEqualTo(state);
        return lostItemMapper.selectByExample(lostItemExample);
    }

    public List<LostItem> myLostItem(String userId){
        LostItemExample lostItemExample =new LostItemExample();
        lostItemExample.createCriteria().andTakerIdEqualTo(userId).andStateEqualTo(NO_CLAIM);
        return lostItemMapper.selectByExample(lostItemExample);
    }

    public List<LostItem> myLostItemSpecState(String userId,Byte state){
        LostItemExample lostItemExample =new LostItemExample();
        lostItemExample.createCriteria().andTakerIdEqualTo(userId).andStateNotEqualTo(NO_CLAIM).andStateEqualTo(state);
        return lostItemMapper.selectByExample(lostItemExample);
    }

    public long myLostItemCount(String userId){
        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.createCriteria().andTakerIdEqualTo(userId).andStateNotEqualTo(NO_CLAIM);
        return lostItemMapper.countByExample(lostItemExample);
    }

    public long myPickItemCount(String userId){
        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.createCriteria().andReleaserIdEqualTo(userId).andStateNotEqualTo(CANCEL);
        return lostItemMapper.countByExample(lostItemExample);

    }

    @Transactional
    public boolean cancelClaim(String itemId){
        LostItem lostItem = lostItemMapper.selectByPrimaryKey(itemId);
        lostItem.setItemId(itemId);
        lostItem.setState(NO_CLAIM);

        ESLostItem esLostItem = new ESLostItem();
        esLostItem.setItemDesc(lostItem.getItemDesc());
        esLostItem.setItemId(lostItem.getItemId());
        esLostItem.setReleaseTime(lostItem.getReleaseTime());
        esLostItem.setItemPicture(lostItem.getItemPicture());
        esLostItem.setTakePlace(lostItem.getTakePlace());
        esLostItemRepository.save(esLostItem);
        if(lostItemMapper.updateByPrimaryKey(lostItem)==1){
            return true;
        }else{
            return false;
        }
    }

    public long countAllLostItems(){
        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.createCriteria().andStateNotEqualTo(CANCEL);
        return lostItemMapper.countByExample(lostItemExample);
    }
    public long countAllFindItems(){
        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.createCriteria().andStateNotEqualTo(HAS_CLAIMED);
        return lostItemMapper.countByExample(lostItemExample);
    }
}
