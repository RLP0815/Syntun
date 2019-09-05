package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ModuleOne;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Repository("moduleOneDao")
public interface ModuleOneDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleOne record);

    int insertSelective(ModuleOne record);

    ModuleOne selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleOne record);

    int updateByPrimaryKey(ModuleOne record);

    List<ModuleOne> selectModuleOne();
    
    List<ModuleOne> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ModuleOne> getAllList(HashMap<String, Object> params);
	
    List<ModuleOne> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);

	List<Map<String, String>> getDataBase();

	List<Map<String, String>> getTableName();
	
}
