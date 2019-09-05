package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerJD144WebError;

/**
 * 
 */
public interface CrawlerJD144WebErrorService {
    public List<CrawlerJD144WebError> login();
    
    public List<CrawlerJD144WebError> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerJD144WebError> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerJD144WebError> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
