package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.DataTableDao;
import com.syntun.entity.DataTable;
import com.syntun.service.DataTableService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("dataTableService")
public class DataTableServiceImpl implements DataTableService{

    @Resource(name = "dataTableDao")
    private DataTableDao dataTableDao;

    @Override
    public List<DataTable> login() {
        return dataTableDao.selectDataTable();
    }
    
    @Override
   	public List<DataTable> selectRecord(HashMap<String, Object> params) {
   		return dataTableDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return dataTableDao.getCount(params);
	}
    
    @Override
	public List<DataTable> getAllList(HashMap<String, Object> params) {
		return dataTableDao.getAllList(params);
		
	}
	
    @Override
	public List<DataTable> getList(HashMap<String, Object> params) {
		return dataTableDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return dataTableDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		dataTableDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		dataTableDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		dataTableDao.delAllRecord(delList);
		
	}


}
