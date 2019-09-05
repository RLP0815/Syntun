package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.DataTableFiledDao;
import com.syntun.entity.DataTableFiled;
import com.syntun.service.DataTableFiledService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("dataTableFiledService")
public class DataTableFiledServiceImpl implements DataTableFiledService{

    @Resource(name = "dataTableFiledDao")
    private DataTableFiledDao dataTableFiledDao;

    @Override
    public List<DataTableFiled> login() {
        return dataTableFiledDao.selectDataTableFiled();
    }
    
    @Override
   	public List<DataTableFiled> selectRecord(HashMap<String, Object> params) {
   		return dataTableFiledDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return dataTableFiledDao.getCount(params);
	}
    
    @Override
	public List<DataTableFiled> getAllList(HashMap<String, Object> params) {
		return dataTableFiledDao.getAllList(params);
		
	}
	
    @Override
	public List<DataTableFiled> getList(HashMap<String, Object> params) {
    	return dataTableFiledDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return dataTableFiledDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		dataTableFiledDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		dataTableFiledDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		dataTableFiledDao.delAllRecord(delList);
		
	}


}