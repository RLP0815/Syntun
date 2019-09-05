package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ModuleTwo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Repository("moduleTwoDao")
public interface ModuleTwoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleTwo record);

    int insertSelective(ModuleTwo record);

    ModuleTwo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleTwo record);

    int updateByPrimaryKey(ModuleTwo record);

    List<ModuleTwo> selectModuleTwo();
    
    List<ModuleTwo> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ModuleTwo> getAllList(HashMap<String, Object> params);
	
    List<ModuleTwo> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);

	List<Map<String, String>> getDataBase();

	List<Map<String, String>> getTableName();

	List<ModuleTwo> getIllegalChar();
	
	void editIllegalChar(HashMap<String, Object> params);
	
}
