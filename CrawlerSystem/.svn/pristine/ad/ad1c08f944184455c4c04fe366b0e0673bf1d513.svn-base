package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ModuleOneDao;
import com.syntun.entity.ModuleOne;
import com.syntun.service.ModuleOneService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Service("moduleOneService")
public class ModuleOneServiceImpl implements ModuleOneService{

    @Resource(name = "moduleOneDao")
    private ModuleOneDao moduleOneDao;

    @Override
    public List<ModuleOne> login() {
        return moduleOneDao.selectModuleOne();
    }
    
    @Override
   	public List<ModuleOne> selectRecord(HashMap<String, Object> params) {
   		return moduleOneDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return moduleOneDao.getCount(params);
	}
    
    @Override
	public List<ModuleOne> getAllList(HashMap<String, Object> params) {
		return moduleOneDao.getAllList(params);
		
	}
	
    @Override
	public List<ModuleOne> getList(HashMap<String, Object> params) {
		return moduleOneDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return moduleOneDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		moduleOneDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		moduleOneDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		moduleOneDao.delAllRecord(delList);
		
	}

	@Override
	public List<Map<String, String>> getDataBase() {
		return moduleOneDao.getDataBase();
	}

	@Override
	public List<Map<String, String>> getTableName() {
		return moduleOneDao.getTableName();
	}


}
