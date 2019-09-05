package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.ProductState;


/**
 * 
 */
public interface ProductStateService {
    public List<ProductState> login();
    
    public List<ProductState> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<ProductState> getAllList();
    
	public List<ProductState> getList(HashMap<String, Object> params);
	
	public int addFiled(HashMap<String, Object> params);

	public void delFiled(HashMap<String, Object> params);

	public void editFiled(HashMap<String, Object> params);

	public void delAllFiled(List<String> delList);

}
