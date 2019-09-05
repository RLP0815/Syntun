package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerCommentJD144DataTableFiledList;

/**
 * 
 */
public interface CrawlerCommentJD144DataTableFiledListService {
    public List<CrawlerCommentJD144DataTableFiledList> login();
    
    public List<CrawlerCommentJD144DataTableFiledList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerCommentJD144DataTableFiledList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerCommentJD144DataTableFiledList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
