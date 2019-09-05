package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144InitUrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144InitUrlListDao")
public interface CrawlerTmall144InitUrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144InitUrlList record);

    int insertSelective(CrawlerTmall144InitUrlList record);

    CrawlerTmall144InitUrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144InitUrlList record);

    int updateByPrimaryKey(CrawlerTmall144InitUrlList record);

    List<CrawlerTmall144InitUrlList> selectCrawlerTmall144InitUrlList();
    
    List<CrawlerTmall144InitUrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144InitUrlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144InitUrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
