package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Role;
import com.syntun.entity.UserRole;

import java.util.HashMap;
import java.util.List;

/**
 * 鐢ㄦ埛瑙掕壊鍏宠仈琛�
 */
@Repository("userRoleDao")
public interface UserRoleDao {
    int deleteByPrimaryKey(Integer id);
    
    int delUserid(Integer userid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> queryUserRole(int userid);
    
    List<UserRole> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<UserRole> getAllList(HashMap<String, Object> params);
	
    List<UserRole> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
