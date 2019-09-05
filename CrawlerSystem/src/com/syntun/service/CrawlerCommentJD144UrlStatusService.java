package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerCommentJD144UrlStatus;

/**
 * 
 */
public interface CrawlerCommentJD144UrlStatusService {
    public List<CrawlerCommentJD144UrlStatus> login();
    
    public List<CrawlerCommentJD144UrlStatus> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerCommentJD144UrlStatus> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerCommentJD144UrlStatus> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
