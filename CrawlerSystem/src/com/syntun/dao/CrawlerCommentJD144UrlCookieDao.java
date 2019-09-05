package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144UrlCookie;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144UrlCookieDao")
public interface CrawlerCommentJD144UrlCookieDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144UrlCookie record);

    int insertSelective(CrawlerCommentJD144UrlCookie record);

    CrawlerCommentJD144UrlCookie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144UrlCookie record);

    int updateByPrimaryKey(CrawlerCommentJD144UrlCookie record);

    List<CrawlerCommentJD144UrlCookie> selectCrawlerCommentJD144UrlCookie();
    
    List<CrawlerCommentJD144UrlCookie> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144UrlCookie> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144UrlCookie> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
