package com.syntun.service.impl;

import com.syntun.dao.SkyUsersDao;
import com.syntun.entity.SkyUsers;
import com.syntun.service.SkyUsersService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("skyUsersService")
public class SkyUsersServiceImpl implements SkyUsersService{

    @Resource(name = "skyUsersDao")
    private SkyUsersDao skyUsersDao;


	@Override
	public int getCount(HashMap<String, Object> params) {
		return skyUsersDao.getCount(params);
	}
    
    @Override
	public List<SkyUsers> getAllList() {
		return skyUsersDao.getAllList();
		
	}
	
    @Override
	public List<SkyUsers> getList(HashMap<String, Object> params) {
		return skyUsersDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return skyUsersDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		skyUsersDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		skyUsersDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		skyUsersDao.delAllRecord(delList);
		
	}
	
	@Override
	public int getHkCount(HashMap<String, Object> params) {
		return skyUsersDao.getHkCount(params);
	}
	
    @Override
	public List<SkyUsers> getHkList(HashMap<String, Object> params) {
		return skyUsersDao.getHkList(params);
		
	}

	@Override
	public int addHkRecord(HashMap<String, Object> params) {
		return skyUsersDao.addHkRecord(params);
	}

	@Override
	public void delHkRecord(HashMap<String, Object> params) {
		skyUsersDao.delHkRecord(params);
		
	}

	@Override
	public void delAllHkRecord(List<String> delList) {
		skyUsersDao.delAllHkRecord(delList);
		
	}
	
	@Override
	public int getNoHkCount(HashMap<String, Object> params) {
		return skyUsersDao.getNoHkCount(params);
	}
	
    @Override
	public List<SkyUsers> getNoHkList(HashMap<String, Object> params) {
		return skyUsersDao.getNoHkList(params);
		
	}

}
