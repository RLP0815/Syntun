package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.ApiProduct;

/**
 * api类
 */
public interface ApiProductService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApiProduct record);
    
    void insertByBatch(List<ApiProduct> p);

    public ApiProduct findone(int params);
    
    public int getTotal(Map params);
    
    public List<ApiProduct> find(Map params);
    
    public List<ApiProduct> findInfo(Map params);
}
