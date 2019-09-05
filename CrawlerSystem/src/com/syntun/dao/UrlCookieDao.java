package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.UrlCookie;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("urlCookieDao")
public interface UrlCookieDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlCookie record);

    int insertSelective(UrlCookie record);

    UrlCookie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlCookie record);

    int updateByPrimaryKey(UrlCookie record);

    List<UrlCookie> selectUrlCookie();
    
    List<UrlCookie> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<UrlCookie> getAllList(HashMap<String, Object> params);
	
    List<UrlCookie> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
