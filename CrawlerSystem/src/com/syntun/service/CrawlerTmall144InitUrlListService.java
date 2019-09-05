package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerTmall144InitUrlList;

/**
 * 
 */
public interface CrawlerTmall144InitUrlListService {
    public List<CrawlerTmall144InitUrlList> login();
    
    public List<CrawlerTmall144InitUrlList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerTmall144InitUrlList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerTmall144InitUrlList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
