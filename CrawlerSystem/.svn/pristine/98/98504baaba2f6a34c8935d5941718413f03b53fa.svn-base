package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ReplaceStr;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("replaceStrDao")
public interface ReplaceStrDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ReplaceStr record);

    int insertSelective(ReplaceStr record);

    ReplaceStr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReplaceStr record);

    int updateByPrimaryKey(ReplaceStr record);

    List<ReplaceStr> selectReplaceStr();
    
    List<ReplaceStr> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ReplaceStr> getAllList(HashMap<String, Object> params);
	
    List<ReplaceStr> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
