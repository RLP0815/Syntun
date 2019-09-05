package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144DataTableFiledListDao;
import com.syntun.entity.Crawler144DataTableFiledList;
import com.syntun.service.Crawler144DataTableFiledListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144DataTableFiledListService")
public class Crawler144DataTableFiledListServiceImpl implements Crawler144DataTableFiledListService{

    @Resource(name = "crawler144DataTableFiledListDao")
    private Crawler144DataTableFiledListDao crawler144DataTableFiledListDao;

    @Override
    public List<Crawler144DataTableFiledList> login() {
        return crawler144DataTableFiledListDao.selectCrawler144DataTableFiledList();
    }
    
    @Override
   	public List<Crawler144DataTableFiledList> selectRecord(HashMap<String, Object> params) {
   		return crawler144DataTableFiledListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144DataTableFiledListDao.getCount(params);
	}
    
    @Override
	public List<Crawler144DataTableFiledList> getAllList(HashMap<String, Object> params) {
		return crawler144DataTableFiledListDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144DataTableFiledList> getList(HashMap<String, Object> params) {
    	return crawler144DataTableFiledListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144DataTableFiledListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144DataTableFiledListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144DataTableFiledListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144DataTableFiledListDao.delAllRecord(delList);
		
	}


}
