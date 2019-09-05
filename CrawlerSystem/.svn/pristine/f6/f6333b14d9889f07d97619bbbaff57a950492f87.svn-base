package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144UrlCookie;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144UrlCookieDao")
public interface CrawlerTmall144UrlCookieDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144UrlCookie record);

    int insertSelective(CrawlerTmall144UrlCookie record);

    CrawlerTmall144UrlCookie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144UrlCookie record);

    int updateByPrimaryKey(CrawlerTmall144UrlCookie record);

    List<CrawlerTmall144UrlCookie> selectCrawlerTmall144UrlCookie();
    
    List<CrawlerTmall144UrlCookie> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144UrlCookie> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144UrlCookie> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
