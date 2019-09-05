package com.syntun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syntun.entity.ModuleThree;

/**
 * 
 */
public interface ModuleThreeService {
    public List<ModuleThree> login();
    
    public List<ModuleThree> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<ModuleThree> getAllList(HashMap<String, Object> params);
    
	public List<ModuleThree> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

	public List<Map<String, String>> getDataBase();

	public List<Map<String, String>> getTableName();
	
	public List<ModuleThree> getIllegalChar();
	
	public void editIllegalChar(HashMap<String, Object> params);

}
