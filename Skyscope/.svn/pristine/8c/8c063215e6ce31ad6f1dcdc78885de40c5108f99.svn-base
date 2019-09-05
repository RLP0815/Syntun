package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.TAdmin;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("tAdminDao")
public interface TAdminDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdmin record);

    int insertSelective(TAdmin record);

    TAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAdmin record);

    int updateByPrimaryKey(TAdmin record);

    List<TAdmin> selectTAdmin();
    
	public HashMap<String, String> getLogin(HashMap<String, Object> params);
	
	int addRecord(HashMap<String, Object> params);
	
	void changePwd(HashMap<String, Object> params);
	
	List<TAdmin> getAllList(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
}
