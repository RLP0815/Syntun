package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144UrlStatus;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144UrlStatusDao")
public interface CrawlerTmall144UrlStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144UrlStatus record);

    int insertSelective(CrawlerTmall144UrlStatus record);

    CrawlerTmall144UrlStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144UrlStatus record);

    int updateByPrimaryKey(CrawlerTmall144UrlStatus record);

    List<CrawlerTmall144UrlStatus> selectCrawlerTmall144UrlStatus();
    
    List<CrawlerTmall144UrlStatus> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144UrlStatus> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144UrlStatus> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
