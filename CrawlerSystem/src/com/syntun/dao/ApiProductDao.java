package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ApiProduct;

@Repository("apiProductDao")
public interface ApiProductDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApiProduct record);
    
    void insertByBatch(List<ApiProduct> p);

    public ApiProduct findone(int params);
    
    public int getTotal(Map params);
    
    public List<ApiProduct> find(Map params);
    
    public List<ApiProduct> findInfo(Map params);
    
}