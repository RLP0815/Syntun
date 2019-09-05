package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144PurlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144PurlListDao")
public interface CrawlerJD144PurlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144PurlList record);

    int insertSelective(CrawlerJD144PurlList record);

    CrawlerJD144PurlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144PurlList record);

    int updateByPrimaryKey(CrawlerJD144PurlList record);

    List<CrawlerJD144PurlList> selectCrawlerJD144PurlList();
    
    List<CrawlerJD144PurlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144PurlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144PurlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
