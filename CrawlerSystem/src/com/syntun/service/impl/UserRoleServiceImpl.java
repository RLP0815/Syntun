package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.UserRoleDao;
import com.syntun.entity.UserRole;
import com.syntun.service.UserRoleService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

    @Resource(name = "userRoleDao")
    private UserRoleDao userRoleDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleDao.insert(record);
	}

	@Override
	public int insertSelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<UserRole> queryUserRole(int userid) {
		return userRoleDao.queryUserRole(userid);
	}

	@Override
	public List<UserRole> selectRecord(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserRole> getAllList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> getList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delUserid(Integer userid) {
		// TODO Auto-generated method stub
		return userRoleDao.delUserid(userid);
	}


}
