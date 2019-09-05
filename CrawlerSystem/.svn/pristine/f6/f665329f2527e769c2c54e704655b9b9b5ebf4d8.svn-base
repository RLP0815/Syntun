package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PurlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("purlListDao")
public interface PurlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PurlList record);

    int insertSelective(PurlList record);

    PurlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurlList record);

    int updateByPrimaryKey(PurlList record);

    List<PurlList> selectPurlList();
    
    List<PurlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<PurlList> getAllList(HashMap<String, Object> params);
	
    List<PurlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
