package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PlatformClassify;
import com.syntun.entity.WebsiteTable;

@Repository("platformClassifyDao")
public interface PlatformClassifyDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(PlatformClassify record);
    
    void insertByBatch(List<PlatformClassify> p);

    int insertSelective(PlatformClassify record);

    PlatformClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformClassify record);

    int updateByPrimaryKey(PlatformClassify record);
    
    public List<PlatformClassify> find(Map params);
    
    public int getTotal(Map params);
    
    public List<WebsiteTable> findWebsiteTable(Map params);
    
    public List<String> getBydatabase();
    public List<String> getBytable(String database);
}