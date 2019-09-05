package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PatternList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("patternListDao")
public interface PatternListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PatternList record);

    int insertSelective(PatternList record);
    
    PatternList selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(PatternList record);

    int updateByPrimaryKey(PatternList record);

    List<PatternList> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<PatternList> getAllList();
	//查询部分记录
    List<PatternList> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
