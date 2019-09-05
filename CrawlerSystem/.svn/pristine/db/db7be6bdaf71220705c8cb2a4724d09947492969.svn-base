package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ModuleTwoDao;
import com.syntun.entity.ModuleTwo;
import com.syntun.service.ModuleTwoService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Service("moduleTwoService")
public class ModuleTwoServiceImpl implements ModuleTwoService{

    @Resource(name = "moduleTwoDao")
    private ModuleTwoDao moduleTwoDao;

    @Override
    public List<ModuleTwo> login() {
        return moduleTwoDao.selectModuleTwo();
    }
    
    @Override
   	public List<ModuleTwo> selectRecord(HashMap<String, Object> params) {
   		return moduleTwoDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return moduleTwoDao.getCount(params);
	}
    
    @Override
	public List<ModuleTwo> getAllList(HashMap<String, Object> params) {
		return moduleTwoDao.getAllList(params);
		
	}
	
    @Override
	public List<ModuleTwo> getList(HashMap<String, Object> params) {
		return moduleTwoDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return moduleTwoDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		moduleTwoDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		moduleTwoDao.editRecord(params);
	}

	@Override
	public void delAllRecord(List<String> delList) {
		moduleTwoDao.delAllRecord(delList);
		
	}

	@Override
	public List<Map<String, String>> getDataBase() {
		return moduleTwoDao.getDataBase();
	}

	@Override
	public List<Map<String, String>> getTableName() {
		return moduleTwoDao.getTableName();
	}

	@Override
	public List<ModuleTwo> getIllegalChar() {
		return moduleTwoDao.getIllegalChar();
	}

	@Override
	public void editIllegalChar(HashMap<String, Object> params) {
		moduleTwoDao.editIllegalChar(params);
	}


}
