package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144UrlCookieDao;
import com.syntun.entity.CrawlerTmall144UrlCookie;
import com.syntun.service.CrawlerTmall144UrlCookieService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144UrlCookieService")
public class CrawlerTmall144UrlCookieServiceImpl implements CrawlerTmall144UrlCookieService{

    @Resource(name = "crawlerTmall144UrlCookieDao")
    private CrawlerTmall144UrlCookieDao crawlerTmall144UrlCookieDao;

    @Override
    public List<CrawlerTmall144UrlCookie> login() {
        return crawlerTmall144UrlCookieDao.selectCrawlerTmall144UrlCookie();
    }
    
    @Override
   	public List<CrawlerTmall144UrlCookie> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144UrlCookieDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144UrlCookieDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144UrlCookie> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144UrlCookieDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144UrlCookie> getList(HashMap<String, Object> params) {
		return crawlerTmall144UrlCookieDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144UrlCookieDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144UrlCookieDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144UrlCookieDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144UrlCookieDao.delAllRecord(delList);
		
	}


}
