package com.syntun.service.impl;

import com.syntun.controller.systemlog.OperatorLog;
import com.syntun.dao.IUserDao;
import com.syntun.dao.PermissionDao;
import com.syntun.dao.RolePermissionDao;
import com.syntun.dao.SystemLogDao;
import com.syntun.entity.Permission;
import com.syntun.entity.RolePermission;
import com.syntun.entity.User;
import com.syntun.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource(name = "userDao")
    private IUserDao userDao;
    
    //新增
    @Resource(name = "permissionDao")
    private PermissionDao permissionDao;
    //新增
    @Resource(name = "rolePermissionDao")
    private RolePermissionDao rolePermissionDao;
    
    @Resource(name = "systemLogDao")
    private SystemLogDao systemLogDao;

    @Override
    public List<User> getUser() {
        return userDao.selectAllUser();
    }

    //新增
	@Override
	public List<Permission> getPermission() {
		return permissionDao.getPermission();
	}

	@Override
	public List<Permission> getPermissionUserid(String userId) {
		return permissionDao.getPermissionUserid(userId);
	}

	@Override
	public List<Permission> getPermissionUseridUrl(String userId) {
		return permissionDao.getPermissionUseridUrl(userId);
	}

	@Override
	public List<Permission> getPermissionUseridInfo(Map map) {
		return permissionDao.getPermissionUseridInfo(map);
	}

	@Override
	public int deleteByRoidPermission(String roid) {
		return rolePermissionDao.deleteByRoid(roid);
	}

	@Override
	public int insertByRoidPermission(RolePermission record) {
		return rolePermissionDao.insert(record);
	}

	@Override
	public int permissionAdd(Permission p) {
		return permissionDao.insert(p);
	}

	@Override
	public int permissionEdit(Permission p) {
		return permissionDao.updateByPrimaryKey(p);
	}

	@Override
	public Permission permissionFindById(int id) {
		return permissionDao.selectByPrimaryKey(id);
	}

	@Override
	public int permissionDelById(int id) {
		return permissionDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Permission> getPermissionRoleInfo(String roleId) {
		return permissionDao.getPermissionRoleInfo(roleId);
	}

	@Override
	public int addSystemLog(OperatorLog o) {
		return systemLogDao.insert(o);
	}

	@Override
	public List<OperatorLog> find(Map params) {
		return systemLogDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return systemLogDao.getTotal(params);
	}

}
