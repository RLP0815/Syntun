package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144UrlCookie;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144UrlCookieDao")
public interface Crawler144UrlCookieDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144UrlCookie record);

    int insertSelective(Crawler144UrlCookie record);

    Crawler144UrlCookie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144UrlCookie record);

    int updateByPrimaryKey(Crawler144UrlCookie record);

    List<Crawler144UrlCookie> selectCrawler144UrlCookie();
    
    List<Crawler144UrlCookie> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144UrlCookie> getAllList(HashMap<String, Object> params);
	
    List<Crawler144UrlCookie> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
