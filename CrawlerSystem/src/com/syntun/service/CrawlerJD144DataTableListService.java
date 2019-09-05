package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerJD144DataTableList;

/**
 * 
 */
public interface CrawlerJD144DataTableListService {
    public List<CrawlerJD144DataTableList> login();
    
    public List<CrawlerJD144DataTableList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerJD144DataTableList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerJD144DataTableList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
