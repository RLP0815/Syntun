package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144UrlCookieDao;
import com.syntun.entity.CrawlerJD144UrlCookie;
import com.syntun.service.CrawlerJD144UrlCookieService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144UrlCookieService")
public class CrawlerJD144UrlCookieServiceImpl implements CrawlerJD144UrlCookieService{

    @Resource(name = "crawlerJD144UrlCookieDao")
    private CrawlerJD144UrlCookieDao crawlerJD144UrlCookieDao;

    @Override
    public List<CrawlerJD144UrlCookie> login() {
        return crawlerJD144UrlCookieDao.selectCrawlerJD144UrlCookie();
    }
    
    @Override
   	public List<CrawlerJD144UrlCookie> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144UrlCookieDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144UrlCookieDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144UrlCookie> getAllList(HashMap<String, Object> params) {
		return crawlerJD144UrlCookieDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144UrlCookie> getList(HashMap<String, Object> params) {
		return crawlerJD144UrlCookieDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144UrlCookieDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144UrlCookieDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144UrlCookieDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144UrlCookieDao.delAllRecord(delList);
		
	}


}
