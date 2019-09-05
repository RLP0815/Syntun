package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.WebError;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("webErrorDao")
public interface WebErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WebError record);

    int insertSelective(WebError record);

    WebError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebError record);

    int updateByPrimaryKey(WebError record);

    List<WebError> selectWebError();
    
    List<WebError> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<WebError> getAllList(HashMap<String, Object> params);
	
    List<WebError> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
