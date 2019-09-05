package com.syntun.service.impl;

import com.syntun.dao.SkyGroupsDao;
import com.syntun.entity.SkyGroups;
import com.syntun.service.SkyGroupsService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("skyGroupsService")
public class SkyGroupsServiceImpl implements SkyGroupsService{

    @Resource(name = "skyGroupsDao")
    private SkyGroupsDao skyGroupsDao;


	@Override
	public int getCount(HashMap<String, Object> params) {
		return skyGroupsDao.getCount(params);
	}
    
    @Override
	public List<SkyGroups> getAllList() {
		return skyGroupsDao.getAllList();
		
	}
	
    @Override
	public List<SkyGroups> getList(HashMap<String, Object> params) {
		return skyGroupsDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return skyGroupsDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		skyGroupsDao.delRecord(params);
		
	}
	
	@Override
	public void delRecordUser(HashMap<String, Object> params) {
		skyGroupsDao.delRecordUser(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		skyGroupsDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		skyGroupsDao.delAllRecord(delList);
		
	}
	
	@Override
	public void delAllRecordUser(List<String> delList) {
		skyGroupsDao.delAllRecordUser(delList);
		
	}

}
