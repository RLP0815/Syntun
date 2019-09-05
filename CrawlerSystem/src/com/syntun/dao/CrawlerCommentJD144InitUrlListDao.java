package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144InitUrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144InitUrlListDao")
public interface CrawlerCommentJD144InitUrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144InitUrlList record);

    int insertSelective(CrawlerCommentJD144InitUrlList record);

    CrawlerCommentJD144InitUrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144InitUrlList record);

    int updateByPrimaryKey(CrawlerCommentJD144InitUrlList record);

    List<CrawlerCommentJD144InitUrlList> selectCrawlerCommentJD144InitUrlList();
    
    List<CrawlerCommentJD144InitUrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144InitUrlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144InitUrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
