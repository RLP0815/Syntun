package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144DataTableFiledList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144DataTableFiledListDao")
public interface CrawlerJD144DataTableFiledListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144DataTableFiledList record);

    int insertSelective(CrawlerJD144DataTableFiledList record);

    CrawlerJD144DataTableFiledList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144DataTableFiledList record);

    int updateByPrimaryKey(CrawlerJD144DataTableFiledList record);

    List<CrawlerJD144DataTableFiledList> selectCrawlerJD144DataTableFiledList();
    
    List<CrawlerJD144DataTableFiledList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144DataTableFiledList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144DataTableFiledList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
