package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.UrlCookieDao;
import com.syntun.entity.UrlCookie;
import com.syntun.service.UrlCookieService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("urlCookieService")
public class UrlCookieServiceImpl implements UrlCookieService{

    @Resource(name = "urlCookieDao")
    private UrlCookieDao urlCookieDao;

    @Override
    public List<UrlCookie> login() {
        return urlCookieDao.selectUrlCookie();
    }
    
    @Override
   	public List<UrlCookie> selectRecord(HashMap<String, Object> params) {
   		return urlCookieDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return urlCookieDao.getCount(params);
	}
    
    @Override
	public List<UrlCookie> getAllList(HashMap<String, Object> params) {
		return urlCookieDao.getAllList(params);
		
	}
	
    @Override
	public List<UrlCookie> getList(HashMap<String, Object> params) {
		return urlCookieDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return urlCookieDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		urlCookieDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		urlCookieDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		urlCookieDao.delAllRecord(delList);
		
	}


}
