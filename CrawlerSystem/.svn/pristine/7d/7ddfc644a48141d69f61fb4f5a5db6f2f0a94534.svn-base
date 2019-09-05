package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.UrlLimitList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("urlLimitListDao")
public interface UrlLimitListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlLimitList record);

    int insertSelective(UrlLimitList record);

    UrlLimitList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlLimitList record);

    int updateByPrimaryKey(UrlLimitList record);

    List<UrlLimitList> selectUrlLimitList();
    
    List<UrlLimitList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<UrlLimitList> getAllList(HashMap<String, Object> params);
	
    List<UrlLimitList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
