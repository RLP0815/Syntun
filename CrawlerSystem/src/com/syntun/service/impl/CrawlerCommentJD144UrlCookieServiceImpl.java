package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144UrlCookieDao;
import com.syntun.entity.CrawlerCommentJD144UrlCookie;
import com.syntun.service.CrawlerCommentJD144UrlCookieService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144UrlCookieService")
public class CrawlerCommentJD144UrlCookieServiceImpl implements CrawlerCommentJD144UrlCookieService{

    @Resource(name = "crawlerCommentJD144UrlCookieDao")
    private CrawlerCommentJD144UrlCookieDao crawlerCommentJD144UrlCookieDao;

    @Override
    public List<CrawlerCommentJD144UrlCookie> login() {
        return crawlerCommentJD144UrlCookieDao.selectCrawlerCommentJD144UrlCookie();
    }
    
    @Override
   	public List<CrawlerCommentJD144UrlCookie> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144UrlCookieDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlCookieDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144UrlCookie> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlCookieDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144UrlCookie> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlCookieDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlCookieDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144UrlCookieDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144UrlCookieDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144UrlCookieDao.delAllRecord(delList);
		
	}


}
