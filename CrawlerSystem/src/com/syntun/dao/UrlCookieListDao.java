package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.UrlCookieList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("urlCookieListDao")
public interface UrlCookieListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlCookieList record);

    int insertSelective(UrlCookieList record);

    UrlCookieList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlCookieList record);

    int updateByPrimaryKey(UrlCookieList record);

    List<UrlCookieList> selectUrlCookieList();
    
    List<UrlCookieList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<UrlCookieList> getAllList(HashMap<String, Object> params);
	
    List<UrlCookieList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
