package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144Url302List;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144Url302ListDao")
public interface Crawler144Url302ListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144Url302List record);

    int insertSelective(Crawler144Url302List record);

    Crawler144Url302List selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144Url302List record);

    int updateByPrimaryKey(Crawler144Url302List record);

    List<Crawler144Url302List> selectCrawler144Url302List();
    
    List<Crawler144Url302List> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144Url302List> getAllList(HashMap<String, Object> params);
	
    List<Crawler144Url302List> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
