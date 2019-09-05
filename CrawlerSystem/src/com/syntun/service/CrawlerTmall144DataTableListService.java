package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerTmall144DataTableList;

/**
 * 
 */
public interface CrawlerTmall144DataTableListService {
    public List<CrawlerTmall144DataTableList> login();
    
    public List<CrawlerTmall144DataTableList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerTmall144DataTableList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerTmall144DataTableList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
