package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.DataTableFiled;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("dataTableFiledDao")
public interface DataTableFiledDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataTableFiled record);

    int insertSelective(DataTableFiled record);

    DataTableFiled selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataTableFiled record);

    int updateByPrimaryKey(DataTableFiled record);

    List<DataTableFiled> selectDataTableFiled();
    
    List<DataTableFiled> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<DataTableFiled> getAllList(HashMap<String, Object> params);
	
    List<DataTableFiled> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
