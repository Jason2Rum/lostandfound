package com.jianghuling.lostandfound.dao;

import com.jianghuling.lostandfound.model.ESLostStuCard;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @Param: 实体类与主键类型
 *
 */
public interface ESLostStuCardRepository extends ElasticsearchRepository<ESLostStuCard,String> {
        List<ESLostStuCard> findByNameOrCollege(String name,String college);
}
