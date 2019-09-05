package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.ProductUrl;

/**
 * 
 */
public interface ProductUrlService {
    public List<ProductUrl> login();
    
    public List<ProductUrl> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<ProductUrl> getAllList(HashMap<String, Object> params);
    
	public List<ProductUrl> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);

}
