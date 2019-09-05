package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144UrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144UrlListDao")
public interface CrawlerTmall144UrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144UrlList record);

    int insertSelective(CrawlerTmall144UrlList record);

    CrawlerTmall144UrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144UrlList record);

    int updateByPrimaryKey(CrawlerTmall144UrlList record);

    List<CrawlerTmall144UrlList> selectCrawlerTmall144UrlList();
    
    List<CrawlerTmall144UrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144UrlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144UrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
