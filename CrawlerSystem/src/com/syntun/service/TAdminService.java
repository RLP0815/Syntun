package com.syntun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syntun.entity.TAdmin;

/**
 * 
 */
public interface TAdminService {
    public List<TAdmin> login();
    
	public Map getLogin(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);
	
	public void changePwd(HashMap<String, Object> params);
	
	public List<TAdmin> getAllList(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);
	
    //新增方法
	public int update(TAdmin record);
    public int getCount(HashMap<String, Object> params);
    List<TAdmin> getList(HashMap<String, Object> params);
    
    int getRoidCount(HashMap<String, Object> params);
	
    List<TAdmin> getRoidList(HashMap<String, Object> params);
}