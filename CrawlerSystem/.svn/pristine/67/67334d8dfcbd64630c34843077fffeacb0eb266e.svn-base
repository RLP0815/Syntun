package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ModuleThree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Repository("moduleThreeDao")
public interface ModuleThreeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleThree record);

    int insertSelective(ModuleThree record);

    ModuleThree selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleThree record);

    int updateByPrimaryKey(ModuleThree record);

    List<ModuleThree> selectModuleThree();
    
    List<ModuleThree> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ModuleThree> getAllList(HashMap<String, Object> params);
	
    List<ModuleThree> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);

	List<Map<String, String>> getDataBase();

	List<Map<String, String>> getTableName();

	List<ModuleThree> getIllegalChar();
	
	void editIllegalChar(HashMap<String, Object> params);
	
}
