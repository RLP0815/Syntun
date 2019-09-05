package com.syntun.dao;

import org.springframework.stereotype.Repository;
import com.syntun.entity.Role;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("roleDao")
public interface RoleDao {
	Role findone(Integer id);
    
    int insert(Role record);
    int delete(Integer id);
    int update(Role record);

    int insertSelective(Role record);

    int updateByPrimaryKeySelective(Role record);

    List<Role> selectCateAccount();
    
    List<Role> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Role> getAllList(HashMap<String, Object> params);
	
    List<Role> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
