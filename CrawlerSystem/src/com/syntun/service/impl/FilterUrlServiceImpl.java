package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.FilterUrlDao;
import com.syntun.entity.FilterUrl;
import com.syntun.service.FilterUrlService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("filterUrlService")
public class FilterUrlServiceImpl implements FilterUrlService{

    @Resource(name = "filterUrlDao")
    private FilterUrlDao filterUrlDao;

    @Override
    public List<FilterUrl> login() {
        return filterUrlDao.selectFilterUrl();
    }
    
    @Override
   	public List<FilterUrl> selectRecord(HashMap<String, Object> params) {
   		return filterUrlDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return filterUrlDao.getCount(params);
	}
    
    @Override
	public List<FilterUrl> getAllList(HashMap<String, Object> params) {
		return filterUrlDao.getAllList(params);
		
	}
	
    @Override
	public List<FilterUrl> getList(HashMap<String, Object> params) {
		return filterUrlDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return filterUrlDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		filterUrlDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		filterUrlDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		filterUrlDao.delAllRecord(delList);
		
	}

	@Override
	public int getKeyValueCount(HashMap<String, Object> params) {
		return filterUrlDao.getKeyValueCount(params);
	}
	
    @Override
	public List<FilterUrl> getKeyValueList(HashMap<String, Object> params) {
		return filterUrlDao.getKeyValueList(params);
		
	}

	@Override
	public int addKeyValueRecord(HashMap<String, Object> params) {
		return filterUrlDao.addKeyValueRecord(params);
	}
}
