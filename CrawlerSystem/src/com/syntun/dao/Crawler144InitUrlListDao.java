package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144InitUrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144InitUrlListDao")
public interface Crawler144InitUrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144InitUrlList record);

    int insertSelective(Crawler144InitUrlList record);

    Crawler144InitUrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144InitUrlList record);

    int updateByPrimaryKey(Crawler144InitUrlList record);

    List<Crawler144InitUrlList> selectCrawler144InitUrlList();
    
    List<Crawler144InitUrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144InitUrlList> getAllList(HashMap<String, Object> params);
	
    List<Crawler144InitUrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
