package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Url302List;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("url302ListDao")
public interface Url302ListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Url302List record);

    int insertSelective(Url302List record);

    Url302List selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Url302List record);

    int updateByPrimaryKey(Url302List record);

    List<Url302List> selectUrl302List();
    
    List<Url302List> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Url302List> getAllList(HashMap<String, Object> params);
	
    List<Url302List> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
