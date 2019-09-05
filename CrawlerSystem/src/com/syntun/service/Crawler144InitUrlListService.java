package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.Crawler144InitUrlList;

/**
 * 
 */
public interface Crawler144InitUrlListService {
    public List<Crawler144InitUrlList> login();
    
    public List<Crawler144InitUrlList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<Crawler144InitUrlList> getAllList(HashMap<String, Object> params);
    
	public List<Crawler144InitUrlList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
