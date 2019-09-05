package com.syntun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syntun.entity.TableUniqueKey;

/**
 * 
 */
public interface TableUniqueKeyService {
    public List<TableUniqueKey> login();
    
    public List<TableUniqueKey> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<TableUniqueKey> getAllList(HashMap<String, Object> params);
    
	public List<TableUniqueKey> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

	public List<Map<String, String>> getDataBase();

	public List<Map<String, String>> getTableName();

}
