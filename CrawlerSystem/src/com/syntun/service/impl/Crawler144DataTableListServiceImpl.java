package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144DataTableListDao;
import com.syntun.entity.Crawler144DataTableList;
import com.syntun.service.Crawler144DataTableListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144DataTableListService")
public class Crawler144DataTableListServiceImpl implements Crawler144DataTableListService{

    @Resource(name = "crawler144DataTableListDao")
    private Crawler144DataTableListDao crawler144DataTableListDao;

    @Override
    public List<Crawler144DataTableList> login() {
        return crawler144DataTableListDao.selectCrawler144DataTable();
    }
    
    @Override
   	public List<Crawler144DataTableList> selectRecord(HashMap<String, Object> params) {
   		return crawler144DataTableListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144DataTableListDao.getCount(params);
	}
    
    @Override
	public List<Crawler144DataTableList> getAllList(HashMap<String, Object> params) {
		return crawler144DataTableListDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144DataTableList> getList(HashMap<String, Object> params) {
		return crawler144DataTableListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144DataTableListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144DataTableListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144DataTableListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144DataTableListDao.delAllRecord(delList);
		
	}


}
