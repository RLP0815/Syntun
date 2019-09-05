package com.syntun.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.syntun.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * 
 */
@Repository("permissionDao")
public interface PermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectAllUser();
    
    public List<Permission> getPermission();
    
    public List<Permission> getPermissionUserid(String userId);
    
    public List<Permission> getPermissionRoleInfo(String roleId);

    public List<Permission> getPermissionUseridUrl(String userId);
    
    public List<Permission> getPermissionUseridInfo(@Param("map") Map map);
}
