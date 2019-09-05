package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.SkyUsers;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("skyUsersDao")
public interface SkyUsersDao {

	int getCount(HashMap<String, Object> params);
    
    List<SkyUsers> getAllList();
	
    List<SkyUsers> getList(HashMap<String, Object> params);
	
	int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
	int getHkCount(HashMap<String, Object> params);
	
    List<SkyUsers> getHkList(HashMap<String, Object> params);
	
	int addHkRecord(HashMap<String, Object> params);
    
    void delHkRecord(HashMap<String, Object> params);
    
    void delAllHkRecord(List<String> delList);
    
    int getNoHkCount(HashMap<String, Object> params);
	
    List<SkyUsers> getNoHkList(HashMap<String, Object> params);
}
