package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ModuleThreeDao;
import com.syntun.entity.ModuleThree;
import com.syntun.service.ModuleThreeService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Service("moduleThreeService")
public class ModuleThreeServiceImpl implements ModuleThreeService{

    @Resource(name = "moduleThreeDao")
    private ModuleThreeDao moduleThreeDao;

    @Override
    public List<ModuleThree> login() {
        return moduleThreeDao.selectModuleThree();
    }
    
    @Override
   	public List<ModuleThree> selectRecord(HashMap<String, Object> params) {
   		return moduleThreeDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return moduleThreeDao.getCount(params);
	}
    
    @Override
	public List<ModuleThree> getAllList(HashMap<String, Object> params) {
		return moduleThreeDao.getAllList(params);
		
	}
	
    @Override
	public List<ModuleThree> getList(HashMap<String, Object> params) {
		return moduleThreeDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return moduleThreeDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		moduleThreeDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		moduleThreeDao.editRecord(params);
	}

	@Override
	public void delAllRecord(List<String> delList) {
		moduleThreeDao.delAllRecord(delList);
		
	}

	@Override
	public List<Map<String, String>> getDataBase() {
		return moduleThreeDao.getDataBase();
	}

	@Override
	public List<Map<String, String>> getTableName() {
		return moduleThreeDao.getTableName();
	}

	@Override
	public List<ModuleThree> getIllegalChar() {
		return moduleThreeDao.getIllegalChar();
	}

	@Override
	public void editIllegalChar(HashMap<String, Object> params) {
		moduleThreeDao.editIllegalChar(params);
	}


}
