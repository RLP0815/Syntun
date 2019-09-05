package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.EditRemark;


/**
 * 
 */
public interface EditRemarkService {
    public List<EditRemark> login();
    
    public List<EditRemark> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<EditRemark> getAllList(HashMap<String, Object> params);
    
	public List<EditRemark> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
