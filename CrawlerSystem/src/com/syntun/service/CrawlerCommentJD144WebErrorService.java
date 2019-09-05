package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerCommentJD144WebError;

/**
 * 
 */
public interface CrawlerCommentJD144WebErrorService {
    public List<CrawlerCommentJD144WebError> login();
    
    public List<CrawlerCommentJD144WebError> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerCommentJD144WebError> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerCommentJD144WebError> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
