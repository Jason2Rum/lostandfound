package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.ESLostItemRepository;
import com.jianghuling.lostandfound.dao.LostItemMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;
import com.jianghuling.lostandfound.model.ESLostItem;
import com.jianghuling.lostandfound.model.LostItem;
import com.jianghuling.lostandfound.model.LostItemExample;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<LostItem> getLostItem(String category,int pageNo, int pageSize) {
        List<LostItem> list = selfDefMapper.selectLostItem(pageNo * pageSize, pageSize,category);
        for(LostItem item:list){
            item.setItemPicture(PIC_ACCESS_PREFIX+item.getItemPicture());
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

    public boolean publishNewLostItem(String category, String desc, String claimMethod, MultipartFile picFile,String userId) throws Exception {
        if (picFile.isEmpty()) {
            return false;
        } else {
            String oriFileName = picFile.getOriginalFilename();
            String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = time + oriFileName.substring(oriFileName.lastIndexOf("."));
            File dest = new File(UPLOAD_IMG_LOC + fileName);
            File temp = new File(UPLOAD_IMG_LOC +"temp"+ fileName);
            picFile.transferTo(temp);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
//            picFile.transferTo(dest);
            Thumbnails.of(temp)
                    .scale(1f)
                    .outputFormat("jpg")
                    .outputQuality(0.5f)
                    .toFile(dest);//图片压缩

            temp.delete();
            LostItem lostItem = new LostItem();

            lostItem.setItemId(UUID.randomUUID().toString());
            lostItem.setCategory(category);
            lostItem.setTakePlace(claimMethod);
            lostItem.setState(NO_CLAIM);
            lostItem.setItemDesc(desc);
            lostItem.setItemPicture("http://jianghuling.top/lostimages/"+dest.getName());
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

            lostItemMapper.insert(lostItem);
            esLostItemRepository.save(esLostItem);

            return true;
        }
    }

    public List<ESLostItem> search(String description){
        return esLostItemRepository.findTop5ByItemDesc(description);
    }
}
