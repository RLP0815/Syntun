package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.UrlLimitListDao;
import com.syntun.entity.UrlLimitList;
import com.syntun.service.UrlLimitListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("urlLimitListService")
public class UrlLimitListServiceImpl implements UrlLimitListService{

    @Resource(name = "urlLimitListDao")
    private UrlLimitListDao urlLimitListDao;

    @Override
    public List<UrlLimitList> login() {
        return urlLimitListDao.selectUrlLimitList();
    }
    
    @Override
   	public List<UrlLimitList> selectRecord(HashMap<String, Object> params) {
   		return urlLimitListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return urlLimitListDao.getCount(params);
	}
    
    @Override
	public List<UrlLimitList> getAllList(HashMap<String, Object> params) {
		return urlLimitListDao.getAllList(params);
		
	}
	
    @Override
	public List<UrlLimitList> getList(HashMap<String, Object> params) {
		return urlLimitListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return urlLimitListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		urlLimitListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		urlLimitListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		urlLimitListDao.delAllRecord(delList);
		
	}


}
