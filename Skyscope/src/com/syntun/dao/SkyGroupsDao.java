package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.SkyGroups;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("skyGroupsDao")
public interface SkyGroupsDao {

	int getCount(HashMap<String, Object> params);
    
    List<SkyGroups> getAllList();
	
    List<SkyGroups> getList(HashMap<String, Object> params);
	
	int addRecord(HashMap<String, Object> params);
	
	void changePwd(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
    void delRecordUser(HashMap<String, Object> params);
    
    void delAllRecordUser(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
}
