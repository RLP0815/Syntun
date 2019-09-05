package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerTmall144UrlCookie;

/**
 * 
 */
public interface CrawlerTmall144UrlCookieService {
    public List<CrawlerTmall144UrlCookie> login();
    
    public List<CrawlerTmall144UrlCookie> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerTmall144UrlCookie> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerTmall144UrlCookie> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
