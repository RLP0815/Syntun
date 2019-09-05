package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.InitUrl;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("initUrlDao")
public interface InitUrlDao {
    int deleteByPrimaryKey(Integer id);

    int insert(InitUrl record);

    int insertSelective(InitUrl record);

    InitUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InitUrl record);

    int updateByPrimaryKey(InitUrl record);

    List<InitUrl> selectInitUrl();
    
    List<InitUrl> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<InitUrl> getAllList(HashMap<String, Object> params);
	
    List<InitUrl> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
