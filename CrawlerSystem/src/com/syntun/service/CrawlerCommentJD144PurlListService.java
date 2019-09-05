package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerCommentJD144PurlList;

/**
 * 
 */
public interface CrawlerCommentJD144PurlListService {
    public List<CrawlerCommentJD144PurlList> login();
    
    public List<CrawlerCommentJD144PurlList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerCommentJD144PurlList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerCommentJD144PurlList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
