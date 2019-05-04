package com.jianghuling.lostandfound.dao;

import com.jianghuling.lostandfound.model.ESLostItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESLostItemRepository extends ElasticsearchRepository<ESLostItem,String> {
    List<ESLostItem> findByItemDesc(String desc);
}
