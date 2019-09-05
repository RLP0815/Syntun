package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.Crawler144UrlCookie;

/**
 * 
 */
public interface Crawler144UrlCookieService {
    public List<Crawler144UrlCookie> login();
    
    public List<Crawler144UrlCookie> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<Crawler144UrlCookie> getAllList(HashMap<String, Object> params);
    
	public List<Crawler144UrlCookie> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
