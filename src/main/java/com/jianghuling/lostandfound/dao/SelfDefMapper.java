package com.jianghuling.lostandfound.dao;

import com.jianghuling.lostandfound.model.LostItem;
import com.jianghuling.lostandfound.model.LostStuCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SelfDefMapper {

    List<LostStuCard> selectLostCard(@Param("startIndex")int startIndex,@Param("pageSize") int pageSize);

    List<LostItem> selectLostItem(@Param("startIndex")int startIndex, @Param("PageSize") int pageSize);

}
