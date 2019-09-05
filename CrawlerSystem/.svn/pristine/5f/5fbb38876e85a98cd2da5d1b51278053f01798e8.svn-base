package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144UrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144UrlListDao")
public interface CrawlerCommentJD144UrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144UrlList record);

    int insertSelective(CrawlerCommentJD144UrlList record);

    CrawlerCommentJD144UrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144UrlList record);

    int updateByPrimaryKey(CrawlerCommentJD144UrlList record);

    List<CrawlerCommentJD144UrlList> selectCrawlerCommentJD144UrlList();
    
    List<CrawlerCommentJD144UrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144UrlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144UrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
