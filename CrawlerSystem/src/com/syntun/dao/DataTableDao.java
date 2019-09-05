package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.DataTable;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("dataTableDao")
public interface DataTableDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataTable record);

    int insertSelective(DataTable record);

    DataTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataTable record);

    int updateByPrimaryKey(DataTable record);

    List<DataTable> selectDataTable();
    
    List<DataTable> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<DataTable> getAllList(HashMap<String, Object> params);
	
    List<DataTable> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
