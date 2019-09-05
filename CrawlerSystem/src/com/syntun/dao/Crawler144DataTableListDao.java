package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144DataTableList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144DataTableListDao")
public interface Crawler144DataTableListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144DataTableList record);

    int insertSelective(Crawler144DataTableList record);

    Crawler144DataTableList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144DataTableList record);

    int updateByPrimaryKey(Crawler144DataTableList record);

    List<Crawler144DataTableList> selectCrawler144DataTable();
    
    List<Crawler144DataTableList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144DataTableList> getAllList(HashMap<String, Object> params);
	
    List<Crawler144DataTableList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
