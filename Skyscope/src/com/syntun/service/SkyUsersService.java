package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.SkyUsers;

/**
 * 
 */
public interface SkyUsersService {

	public int getCount(HashMap<String, Object> params);
    
    public List<SkyUsers> getAllList();
    
	public List<SkyUsers> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);
	
	public int getHkCount(HashMap<String, Object> params);
    
	public List<SkyUsers> getHkList(HashMap<String, Object> params);
	
	public int addHkRecord(HashMap<String, Object> params);

	public void delHkRecord(HashMap<String, Object> params);

	public void delAllHkRecord(List<String> delList);
	
	public int getNoHkCount(HashMap<String, Object> params);
    
	public List<SkyUsers> getNoHkList(HashMap<String, Object> params);
}