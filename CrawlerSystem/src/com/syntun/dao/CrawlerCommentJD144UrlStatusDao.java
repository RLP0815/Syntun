package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144UrlStatus;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144UrlStatusDao")
public interface CrawlerCommentJD144UrlStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144UrlStatus record);

    int insertSelective(CrawlerCommentJD144UrlStatus record);

    CrawlerCommentJD144UrlStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144UrlStatus record);

    int updateByPrimaryKey(CrawlerCommentJD144UrlStatus record);

    List<CrawlerCommentJD144UrlStatus> selectCrawlerCommentJD144UrlStatus();
    
    List<CrawlerCommentJD144UrlStatus> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144UrlStatus> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144UrlStatus> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
