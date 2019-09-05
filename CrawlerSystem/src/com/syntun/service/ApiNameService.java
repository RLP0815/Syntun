package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.ApiName;

/**
 * api类
 */
public interface ApiNameService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApiName record);
    
    void insertByBatch(List<ApiName> p);

    public ApiName findone(int params);
    
    public int getTotal(Map params);
    
    public List<ApiName> find(Map params);
    
    public List<ApiName> findInfo(Map params);
}
