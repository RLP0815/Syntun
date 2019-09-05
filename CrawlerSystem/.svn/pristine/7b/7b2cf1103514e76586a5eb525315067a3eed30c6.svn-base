package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.RoleDao;
import com.syntun.entity.Role;
import com.syntun.service.RoleService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public List<Role> login() {
        return roleDao.selectCateAccount();
    }
    
    @Override
   	public List<Role> selectRecord(HashMap<String, Object> params) {
   		return roleDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return roleDao.getCount(params);
	}
    
    @Override
	public List<Role> getAllList(HashMap<String, Object> params) {
		return roleDao.getAllList(params);
		
	}
	
    @Override
	public List<Role> getList(HashMap<String, Object> params) {
		return roleDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return roleDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		roleDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		roleDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		roleDao.delAllRecord(delList);
		
	}

	@Override
	public List<Role> selectCateAccount() {
		return roleDao.selectCateAccount();
	}

	@Override
	public Role findone(Integer id) {
		return roleDao.findone(id);
	}

	@Override
	public int insert(Role record) {
		return roleDao.insert(record);
	}

	@Override
	public int delete(Integer id) {
		return roleDao.delete(id);
	}

	@Override
	public int update(Role record) {
		return roleDao.update(record);
	}


}
