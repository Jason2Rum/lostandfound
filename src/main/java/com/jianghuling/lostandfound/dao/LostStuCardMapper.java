package com.jianghuling.lostandfound.dao;

import com.jianghuling.lostandfound.model.LostStuCard;
import com.jianghuling.lostandfound.model.LostStuCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LostStuCardMapper {
    long countByExample(LostStuCardExample example);

    int deleteByExample(LostStuCardExample example);

    int deleteByPrimaryKey(String id);

    int insert(LostStuCard record);

    int insertSelective(LostStuCard record);

    List<LostStuCard> selectByExample(LostStuCardExample example);

    LostStuCard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LostStuCard record, @Param("example") LostStuCardExample example);

    int updateByExample(@Param("record") LostStuCard record, @Param("example") LostStuCardExample example);

    int updateByPrimaryKeySelective(LostStuCard record);

    int updateByPrimaryKey(LostStuCard record);
}