package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerTmall144UrlStatus;

/**
 * 
 */
public interface CrawlerTmall144UrlStatusService {
    public List<CrawlerTmall144UrlStatus> login();
    
    public List<CrawlerTmall144UrlStatus> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerTmall144UrlStatus> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerTmall144UrlStatus> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
