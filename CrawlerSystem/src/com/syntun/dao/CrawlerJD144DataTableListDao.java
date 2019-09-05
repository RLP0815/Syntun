package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerJD144DataTableList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerJD144DataTableListDao")
public interface CrawlerJD144DataTableListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerJD144DataTableList record);

    int insertSelective(CrawlerJD144DataTableList record);

    CrawlerJD144DataTableList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerJD144DataTableList record);

    int updateByPrimaryKey(CrawlerJD144DataTableList record);

    List<CrawlerJD144DataTableList> selectCrawlerJD144DataTable();
    
    List<CrawlerJD144DataTableList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerJD144DataTableList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerJD144DataTableList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
