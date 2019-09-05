package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144WebError;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144WebErrorDao")
public interface Crawler144WebErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144WebError record);

    int insertSelective(Crawler144WebError record);

    Crawler144WebError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144WebError record);

    int updateByPrimaryKey(Crawler144WebError record);

    List<Crawler144WebError> selectCrawler144WebError();
    
    List<Crawler144WebError> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144WebError> getAllList(HashMap<String, Object> params);
	
    List<Crawler144WebError> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
