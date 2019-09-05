package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144Url302List;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144Url302ListDao")
public interface CrawlerTmall144Url302ListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144Url302List record);

    int insertSelective(CrawlerTmall144Url302List record);

    CrawlerTmall144Url302List selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144Url302List record);

    int updateByPrimaryKey(CrawlerTmall144Url302List record);

    List<CrawlerTmall144Url302List> selectCrawlerTmall144Url302List();
    
    List<CrawlerTmall144Url302List> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144Url302List> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144Url302List> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
