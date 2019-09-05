package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144DataTableList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144DataTableListDao")
public interface CrawlerCommentJD144DataTableListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144DataTableList record);

    int insertSelective(CrawlerCommentJD144DataTableList record);

    CrawlerCommentJD144DataTableList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144DataTableList record);

    int updateByPrimaryKey(CrawlerCommentJD144DataTableList record);

    List<CrawlerCommentJD144DataTableList> selectCrawlerCommentJD144DataTable();
    
    List<CrawlerCommentJD144DataTableList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144DataTableList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144DataTableList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
