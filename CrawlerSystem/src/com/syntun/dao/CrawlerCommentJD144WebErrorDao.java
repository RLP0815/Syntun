package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144WebError;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144WebErrorDao")
public interface CrawlerCommentJD144WebErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144WebError record);

    int insertSelective(CrawlerCommentJD144WebError record);

    CrawlerCommentJD144WebError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144WebError record);

    int updateByPrimaryKey(CrawlerCommentJD144WebError record);

    List<CrawlerCommentJD144WebError> selectCrawlerCommentJD144WebError();
    
    List<CrawlerCommentJD144WebError> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144WebError> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144WebError> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
