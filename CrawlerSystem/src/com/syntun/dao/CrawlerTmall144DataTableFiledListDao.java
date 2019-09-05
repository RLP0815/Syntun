package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144DataTableFiledList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144DataTableFiledListDao")
public interface CrawlerTmall144DataTableFiledListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144DataTableFiledList record);

    int insertSelective(CrawlerTmall144DataTableFiledList record);

    CrawlerTmall144DataTableFiledList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144DataTableFiledList record);

    int updateByPrimaryKey(CrawlerTmall144DataTableFiledList record);

    List<CrawlerTmall144DataTableFiledList> selectCrawlerTmall144DataTableFiledList();
    
    List<CrawlerTmall144DataTableFiledList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144DataTableFiledList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144DataTableFiledList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
