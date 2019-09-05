package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.ApiDoc;

/**
 * api类
 */
public interface ApiDocService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApiDoc record);
    
    void insertByBatch(List<ApiDoc> p);

    public ApiDoc findone(int params);
    
    public int getTotal(Map params);
    
    public List<ApiDoc> find(Map params);
    
    public List<ApiDoc> findInfo(Map params);
}
