package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.Crawler144DataTableFiledList;

/**
 * 
 */
public interface Crawler144DataTableFiledListService {
    public List<Crawler144DataTableFiledList> login();
    
    public List<Crawler144DataTableFiledList> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<Crawler144DataTableFiledList> getAllList(HashMap<String, Object> params);
    
	public List<Crawler144DataTableFiledList> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
