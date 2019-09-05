package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerCommentJD144DataTableFiledList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerCommentJD144DataTableFiledListDao")
public interface CrawlerCommentJD144DataTableFiledListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerCommentJD144DataTableFiledList record);

    int insertSelective(CrawlerCommentJD144DataTableFiledList record);

    CrawlerCommentJD144DataTableFiledList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerCommentJD144DataTableFiledList record);

    int updateByPrimaryKey(CrawlerCommentJD144DataTableFiledList record);

    List<CrawlerCommentJD144DataTableFiledList> selectCrawlerCommentJD144DataTableFiledList();
    
    List<CrawlerCommentJD144DataTableFiledList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerCommentJD144DataTableFiledList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerCommentJD144DataTableFiledList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
