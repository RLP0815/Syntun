package com.syntun.service.impl;

import com.syntun.dao.TAdminDao;
import com.syntun.entity.TAdmin;
import com.syntun.service.TAdminService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Service("tAdminService")
public class TAdminServiceImpl implements TAdminService{

    @Resource(name = "tAdminDao")
    private TAdminDao tAdminDao;

    @Override
    public List<TAdmin> login() {
        return tAdminDao.selectTAdmin();
    }

	@Override
	public Map getLogin(HashMap<String, Object> params) {
		return tAdminDao.getLogin(params);
		
	}
	
	@Override
	public void changePwd(HashMap<String, Object> params) {
		tAdminDao.changePwd(params);
	}
	
	 @Override
	public List<TAdmin> getAllList(HashMap<String, Object> params) {
		return tAdminDao.getAllList(params);
		
	}
	 
	@Override
	public int addRecord(HashMap<String, Object> params) {
		return tAdminDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		tAdminDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		tAdminDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		tAdminDao.delAllRecord(delList);
		
	}
	
	@Override
	public int update(TAdmin record) {
		return tAdminDao.update(record);
	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return tAdminDao.getCount(params);
	}

	@Override
	public List<TAdmin> getList(HashMap<String, Object> params) {
		return tAdminDao.getList(params);
	}

	@Override
	public int getRoidCount(HashMap<String, Object> params) {
		return tAdminDao.getRoidCount(params);
	}

	@Override
	public List<TAdmin> getRoidList(HashMap<String, Object> params) {
		return tAdminDao.getRoidList(params);
	}

}