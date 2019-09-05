package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ReplaceFiled;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("replaceFiledDao")
public interface ReplaceFiledDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ReplaceFiled record);

    int insertSelective(ReplaceFiled record);

    ReplaceFiled selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReplaceFiled record);

    int updateByPrimaryKey(ReplaceFiled record);

    List<ReplaceFiled> selectReplaceFiled();
    
    List<ReplaceFiled> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ReplaceFiled> getAllList();
	
    List<ReplaceFiled> getList(HashMap<String, Object> params);

    int addFiled(HashMap<String, Object> params);
    
    void delFiled(HashMap<String, Object> params);
    
    void delAllFiled(List<String> delList);
    
	void editFiled(HashMap<String, Object> params);
}
