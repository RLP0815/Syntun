package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerCommentJD144Url302List;

/**
 * 
 */
public interface CrawlerCommentJD144Url302ListService {
    public List<CrawlerCommentJD144Url302List> login();
    
    public List<CrawlerCommentJD144Url302List> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerCommentJD144Url302List> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerCommentJD144Url302List> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
