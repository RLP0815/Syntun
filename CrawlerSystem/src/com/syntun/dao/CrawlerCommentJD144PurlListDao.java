package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144PurlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144PurlListDao")
public interface CrawlerCommentJD144PurlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144PurlList record);

    int insertSelective(CrawlerCommentJD144PurlList record);

    CrawlerCommentJD144PurlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144PurlList record);

    int updateByPrimaryKey(CrawlerCommentJD144PurlList record);

    List<CrawlerCommentJD144PurlList> selectCrawlerCommentJD144PurlList();
    
    List<CrawlerCommentJD144PurlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144PurlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144PurlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
