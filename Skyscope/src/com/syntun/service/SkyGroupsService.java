package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.SkyGroups;

/**
 * 
 */
public interface SkyGroupsService {

	public int getCount(HashMap<String, Object> params);
    
    public List<SkyGroups> getAllList();
    
	public List<SkyGroups> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);
	
	public void delRecordUser(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);
	
	public void delAllRecordUser(List<String> delList);
}
