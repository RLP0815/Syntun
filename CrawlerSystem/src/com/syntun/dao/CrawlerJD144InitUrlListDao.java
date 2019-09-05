package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144InitUrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144InitUrlListDao")
public interface CrawlerJD144InitUrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144InitUrlList record);

    int insertSelective(CrawlerJD144InitUrlList record);

    CrawlerJD144InitUrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144InitUrlList record);

    int updateByPrimaryKey(CrawlerJD144InitUrlList record);

    List<CrawlerJD144InitUrlList> selectCrawlerJD144InitUrlList();
    
    List<CrawlerJD144InitUrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144InitUrlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144InitUrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
