package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144Url302List;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144Url302ListDao")
public interface CrawlerJD144Url302ListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144Url302List record);

    int insertSelective(CrawlerJD144Url302List record);

    CrawlerJD144Url302List selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144Url302List record);

    int updateByPrimaryKey(CrawlerJD144Url302List record);

    List<CrawlerJD144Url302List> selectCrawlerJD144Url302List();
    
    List<CrawlerJD144Url302List> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144Url302List> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144Url302List> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
