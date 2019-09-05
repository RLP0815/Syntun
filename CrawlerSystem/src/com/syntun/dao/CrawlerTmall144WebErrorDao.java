package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144WebError;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144WebErrorDao")
public interface CrawlerTmall144WebErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144WebError record);

    int insertSelective(CrawlerTmall144WebError record);

    CrawlerTmall144WebError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144WebError record);

    int updateByPrimaryKey(CrawlerTmall144WebError record);

    List<CrawlerTmall144WebError> selectCrawlerTmall144WebError();
    
    List<CrawlerTmall144WebError> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144WebError> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144WebError> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
