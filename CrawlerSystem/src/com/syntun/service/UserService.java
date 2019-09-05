package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.controller.systemlog.OperatorLog;
import com.syntun.entity.Permission;
import com.syntun.entity.RolePermission;
import com.syntun.entity.User;

/**
 * 
 */
public interface UserService {
    public List<User> getUser();

    public List<Permission> getPermission();
    public List<Permission> getPermissionUserid(String userId);
    public List<Permission> getPermissionRoleInfo(String roleId);
    public List<Permission> getPermissionUseridUrl(String userId);
    public List<Permission> getPermissionUseridInfo(Map map);
    
    //根据roid删除角色权限关系表数据
    public int deleteByRoidPermission(String roid);
    //新增角色权限关系表
    public int insertByRoidPermission(RolePermission record);
    
    public int permissionAdd(Permission p);
    public int permissionEdit(Permission p);
    public Permission permissionFindById(int id);
    public int permissionDelById(int id);

    public int addSystemLog(OperatorLog o);
    
    public List<OperatorLog> find(Map params);
    
    public  int getTotal(Map params);
}