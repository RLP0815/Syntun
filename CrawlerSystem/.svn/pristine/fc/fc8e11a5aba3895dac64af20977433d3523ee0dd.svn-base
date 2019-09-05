package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144WebError;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144WebErrorDao")
public interface CrawlerJD144WebErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144WebError record);

    int insertSelective(CrawlerJD144WebError record);

    CrawlerJD144WebError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144WebError record);

    int updateByPrimaryKey(CrawlerJD144WebError record);

    List<CrawlerJD144WebError> selectCrawlerJD144WebError();
    
    List<CrawlerJD144WebError> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144WebError> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144WebError> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
