package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144UrlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144UrlListDao")
public interface Crawler144UrlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144UrlList record);

    int insertSelective(Crawler144UrlList record);

    Crawler144UrlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144UrlList record);

    int updateByPrimaryKey(Crawler144UrlList record);

    List<Crawler144UrlList> selectCrawler144UrlList();
    
    List<Crawler144UrlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144UrlList> getAllList(HashMap<String, Object> params);
	
    List<Crawler144UrlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
