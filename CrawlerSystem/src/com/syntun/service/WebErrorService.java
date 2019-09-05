package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.WebError;

/**
 * 
 */
public interface WebErrorService {
    public List<WebError> login();
    
    public List<WebError> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<WebError> getAllList(HashMap<String, Object> params);
    
	public List<WebError> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
