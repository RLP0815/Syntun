package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.TAdmin;

/**
 * 
 */
public interface TAdminService {
    public List<TAdmin> login();
    
	public HashMap<String, String> getLogin(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);
	
	public void changePwd(HashMap<String, Object> params);
	
	public List<TAdmin> getAllList(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);
}
