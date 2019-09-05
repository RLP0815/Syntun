package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.FilterUrl;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("filterUrlDao")
public interface FilterUrlDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FilterUrl record);

    int insertSelective(FilterUrl record);

    FilterUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilterUrl record);

    int updateByPrimaryKey(FilterUrl record);

    List<FilterUrl> selectFilterUrl();
    
    List<FilterUrl> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<FilterUrl> getAllList(HashMap<String, Object> params);
	
    List<FilterUrl> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
	int getKeyValueCount(HashMap<String, Object> params);
    
    List<FilterUrl> getKeyValueList(HashMap<String, Object> params);

	int addKeyValueRecord(HashMap<String, Object> params);
}
