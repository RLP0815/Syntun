package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.DataTable;

/**
 * 
 */
public interface DataTableService {
    public List<DataTable> login();
    
    public List<DataTable> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<DataTable> getAllList(HashMap<String, Object> params);
    
	public List<DataTable> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
