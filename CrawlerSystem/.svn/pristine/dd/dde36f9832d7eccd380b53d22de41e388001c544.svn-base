package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144UrlStatus;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144UrlStatusDao")
public interface Crawler144UrlStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144UrlStatus record);

    int insertSelective(Crawler144UrlStatus record);

    Crawler144UrlStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144UrlStatus record);

    int updateByPrimaryKey(Crawler144UrlStatus record);

    List<Crawler144UrlStatus> selectCrawler144UrlStatus();
    
    List<Crawler144UrlStatus> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144UrlStatus> getAllList(HashMap<String, Object> params);
	
    List<Crawler144UrlStatus> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
