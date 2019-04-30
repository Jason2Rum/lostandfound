package com.jianghuling.lostandfound.service;

import com.jianghuling.lostandfound.dao.LostItemMapper;
import com.jianghuling.lostandfound.dao.SelfDefMapper;
import com.jianghuling.lostandfound.model.LostItem;
import com.jianghuling.lostandfound.model.LostItemExample;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    public LostItemService(LostItemMapper lostItemMapper, SelfDefMapper selfDefMapper) {
        this.lostItemMapper = lostItemMapper;
        this.selfDefMapper = selfDefMapper;
    }

    public List<LostItem> getLostItem(int pageNo, int pageSize) {
        return selfDefMapper.selectLostItem(pageNo * pageSize, pageSize);
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
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            picFile.transferTo(dest);
            LostItem lostItem = new LostItem();

            lostItem.setItemId(UUID.randomUUID().toString());
            lostItem.setCategory(category);
            lostItem.setTakePlace(claimMethod);
            lostItem.setItemDesc(desc);
            lostItem.setReleaserId(userId);
            //发布时间数据库有默认的now()

            lostItemMapper.insert(lostItem);
            return true;
        }
    }
}
