package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.CrawlerTmall144PurlList;

/**
 * 
 */
public interface CrawlerTmall144PurlListService {
    public List<CrawlerTmall144PurlList> login();
    
    public List<CrawlerTmall144PurlList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<CrawlerTmall144PurlList> getAllList(HashMap<String, Object> params);
    
	public List<CrawlerTmall144PurlList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
