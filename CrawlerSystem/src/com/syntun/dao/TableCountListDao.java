package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.TableCountList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("tableCountListDao")
public interface TableCountListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TableCountList record);

    int insertSelective(TableCountList record);

    TableCountList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TableCountList record);

    int updateByPrimaryKey(TableCountList record);

    List<TableCountList> selectTableCountList();
    
    List<TableCountList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<TableCountList> getAllList(HashMap<String, Object> params);
	
    List<TableCountList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);

	List<TableCountList> getEmailList(HashMap<String, Object> params);
	
}
