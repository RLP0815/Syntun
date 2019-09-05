package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ApiDoc;

@Repository("apiDocDao")
public interface ApiDocDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApiDoc record);
    
    void insertByBatch(List<ApiDoc> p);

    public ApiDoc findone(int params);
    
    public int getTotal(Map params);
    
    public List<ApiDoc> find(Map params);
    
    public List<ApiDoc> findInfo(Map params);
    
}