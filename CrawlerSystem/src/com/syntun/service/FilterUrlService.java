package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.FilterUrl;

/**
 * 
 */
public interface FilterUrlService {
    public List<FilterUrl> login();
    
    public List<FilterUrl> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<FilterUrl> getAllList(HashMap<String, Object> params);
    
	public List<FilterUrl> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

	public int getKeyValueCount(HashMap<String, Object> params);
    
	public List<FilterUrl> getKeyValueList(HashMap<String, Object> params);

	public int addKeyValueRecord(HashMap<String, Object> params);

}
