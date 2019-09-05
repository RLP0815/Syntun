package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.ReplaceStr;

/**
 * 
 */
public interface ReplaceStrService {
    public List<ReplaceStr> login();
    
    public List<ReplaceStr> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<ReplaceStr> getAllList(HashMap<String, Object> params);
    
	public List<ReplaceStr> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
