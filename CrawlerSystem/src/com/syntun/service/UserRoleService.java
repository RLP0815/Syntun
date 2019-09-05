package com.syntun.service;

import java.util.HashMap;
import java.util.List;
import com.syntun.entity.UserRole;


/**
 * 用户角色关联
 */
public interface UserRoleService {
    int deleteByPrimaryKey(Integer id);
    
    int delUserid(Integer userid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> queryUserRole(int userid);//根据userid查询所属角色
    
    List<UserRole> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<UserRole> getAllList(HashMap<String, Object> params);
	
    List<UserRole> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);

}
