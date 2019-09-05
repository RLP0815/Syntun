package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerJD144UrlCookie;

/**
 * 
 */
public interface CrawlerJD144UrlCookieService {
    public List<CrawlerJD144UrlCookie> login();
    
    public List<CrawlerJD144UrlCookie> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerJD144UrlCookie> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerJD144UrlCookie> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
