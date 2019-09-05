package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.TableUniqueKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Repository("tableUniqueKeyDao")
public interface TableUniqueKeyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TableUniqueKey record);

    int insertSelective(TableUniqueKey record);

    TableUniqueKey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TableUniqueKey record);

    int updateByPrimaryKey(TableUniqueKey record);

    List<TableUniqueKey> selectTableUniqueKey();
    
    List<TableUniqueKey> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<TableUniqueKey> getAllList(HashMap<String, Object> params);
	
    List<TableUniqueKey> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);

	List<Map<String, String>> getDataBase();

	List<Map<String, String>> getTableName();
	
}
