package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerCommentJD144UrlList;

/**
 * 
 */
public interface CrawlerCommentJD144UrlListService {
    public List<CrawlerCommentJD144UrlList> login();
    
    public List<CrawlerCommentJD144UrlList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerCommentJD144UrlList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerCommentJD144UrlList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
