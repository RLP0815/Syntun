package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerTmall144UrlList;

/**
 * 
 */
public interface CrawlerTmall144UrlListService {
    public List<CrawlerTmall144UrlList> login();
    
    public List<CrawlerTmall144UrlList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerTmall144UrlList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerTmall144UrlList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
