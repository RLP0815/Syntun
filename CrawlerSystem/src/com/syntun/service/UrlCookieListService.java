package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.UrlCookieList;

/**
 * 
 */
public interface UrlCookieListService {
    public List<UrlCookieList> login();
    
    public List<UrlCookieList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<UrlCookieList> getAllList(HashMap<String, Object> params);
    
	public List<UrlCookieList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
