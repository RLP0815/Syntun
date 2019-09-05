package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ApiName;

@Repository("apiNameDao")
public interface ApiNameDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApiName record);
    
    void insertByBatch(List<ApiName> p);

    public ApiName findone(int params);
    
    public int getTotal(Map params);
    
    public List<ApiName> find(Map params);
    
    public List<ApiName> findInfo(Map params);
    
}