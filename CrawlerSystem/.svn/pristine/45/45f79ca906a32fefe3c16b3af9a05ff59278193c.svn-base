package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144UrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144UrlListDao")
public interface CrawlerJD144UrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144UrlList record);

    int insertSelective(CrawlerJD144UrlList record);

    CrawlerJD144UrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144UrlList record);

    int updateByPrimaryKey(CrawlerJD144UrlList record);

    List<CrawlerJD144UrlList> selectCrawlerJD144UrlList();
    
    List<CrawlerJD144UrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144UrlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144UrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
