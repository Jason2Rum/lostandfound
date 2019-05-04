package com.jianghuling.lostandfound.dao;

import com.jianghuling.lostandfound.model.LostItem;
import com.jianghuling.lostandfound.model.LostItemExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Mapper
@Repository
public interface LostItemMapper {
    long countByExample(LostItemExample example);

    int deleteByExample(LostItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(LostItem record);

    int insertSelective(LostItem record);

    List<LostItem> selectByExample(LostItemExample example);

    LostItem selectByPrimaryKey(String itemId);

    int updateByExampleSelective(@Param("record") LostItem record, @Param("example") LostItemExample example);

    int updateByExample(@Param("record") LostItem record, @Param("example") LostItemExample example);

    int updateByPrimaryKeySelective(LostItem record);

    int updateByPrimaryKey(LostItem record);
}