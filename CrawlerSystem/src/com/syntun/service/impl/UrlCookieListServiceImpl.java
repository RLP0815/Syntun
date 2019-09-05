package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.UrlCookieListDao;
import com.syntun.entity.UrlCookieList;
import com.syntun.service.UrlCookieListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("urlCookieListService")
public class UrlCookieListServiceImpl implements UrlCookieListService{

    @Resource(name = "urlCookieListDao")
    private UrlCookieListDao urlCookieListDao;

    @Override
    public List<UrlCookieList> login() {
        return urlCookieListDao.selectUrlCookieList();
    }
    
    @Override
   	public List<UrlCookieList> selectRecord(HashMap<String, Object> params) {
   		return urlCookieListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return urlCookieListDao.getCount(params);
	}
    
    @Override
	public List<UrlCookieList> getAllList(HashMap<String, Object> params) {
		return urlCookieListDao.getAllList(params);
		
	}
	
    @Override
	public List<UrlCookieList> getList(HashMap<String, Object> params) {
		return urlCookieListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return urlCookieListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		urlCookieListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		urlCookieListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		urlCookieListDao.delAllRecord(delList);
		
	}


}
