package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144UrlCookieDao;
import com.syntun.entity.Crawler144UrlCookie;
import com.syntun.service.Crawler144UrlCookieService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144UrlCookieService")
public class Crawler144UrlCookieServiceImpl implements Crawler144UrlCookieService{

    @Resource(name = "crawler144UrlCookieDao")
    private Crawler144UrlCookieDao crawler144UrlCookieDao;

    @Override
    public List<Crawler144UrlCookie> login() {
        return crawler144UrlCookieDao.selectCrawler144UrlCookie();
    }
    
    @Override
   	public List<Crawler144UrlCookie> selectRecord(HashMap<String, Object> params) {
   		return crawler144UrlCookieDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144UrlCookieDao.getCount(params);
	}
    
    @Override
	public List<Crawler144UrlCookie> getAllList(HashMap<String, Object> params) {
		return crawler144UrlCookieDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144UrlCookie> getList(HashMap<String, Object> params) {
		return crawler144UrlCookieDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144UrlCookieDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144UrlCookieDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144UrlCookieDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144UrlCookieDao.delAllRecord(delList);
		
	}


}
