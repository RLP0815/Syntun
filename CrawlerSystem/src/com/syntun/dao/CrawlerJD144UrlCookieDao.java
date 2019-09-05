package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144UrlCookie;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144UrlCookieDao")
public interface CrawlerJD144UrlCookieDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144UrlCookie record);

    int insertSelective(CrawlerJD144UrlCookie record);

    CrawlerJD144UrlCookie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144UrlCookie record);

    int updateByPrimaryKey(CrawlerJD144UrlCookie record);

    List<CrawlerJD144UrlCookie> selectCrawlerJD144UrlCookie();
    
    List<CrawlerJD144UrlCookie> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144UrlCookie> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144UrlCookie> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
