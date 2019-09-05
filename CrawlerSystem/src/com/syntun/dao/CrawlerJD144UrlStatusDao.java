package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144UrlStatus;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144UrlStatusDao")
public interface CrawlerJD144UrlStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144UrlStatus record);

    int insertSelective(CrawlerJD144UrlStatus record);

    CrawlerJD144UrlStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144UrlStatus record);

    int updateByPrimaryKey(CrawlerJD144UrlStatus record);

    List<CrawlerJD144UrlStatus> selectCrawlerJD144UrlStatus();
    
    List<CrawlerJD144UrlStatus> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144UrlStatus> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144UrlStatus> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
