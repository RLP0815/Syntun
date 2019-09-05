package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144DataTableList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144DataTableListDao")
public interface CrawlerTmall144DataTableListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144DataTableList record);

    int insertSelective(CrawlerTmall144DataTableList record);

    CrawlerTmall144DataTableList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144DataTableList record);

    int updateByPrimaryKey(CrawlerTmall144DataTableList record);

    List<CrawlerTmall144DataTableList> selectCrawlerTmall144DataTable();
    
    List<CrawlerTmall144DataTableList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144DataTableList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144DataTableList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
