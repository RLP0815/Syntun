package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144UrlStatusDao;
import com.syntun.entity.CrawlerJD144UrlStatus;
import com.syntun.service.CrawlerJD144UrlStatusService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144UrlStatusService")
public class CrawlerJD144UrlStatusServiceImpl implements CrawlerJD144UrlStatusService{

    @Resource(name = "crawlerJD144UrlStatusDao")
    private CrawlerJD144UrlStatusDao crawlerJD144UrlStatusDao;

    @Override
    public List<CrawlerJD144UrlStatus> login() {
        return crawlerJD144UrlStatusDao.selectCrawlerJD144UrlStatus();
    }
    
    @Override
   	public List<CrawlerJD144UrlStatus> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144UrlStatusDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144UrlStatusDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144UrlStatus> getAllList(HashMap<String, Object> params) {
		return crawlerJD144UrlStatusDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144UrlStatus> getList(HashMap<String, Object> params) {
		return crawlerJD144UrlStatusDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144UrlStatusDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144UrlStatusDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144UrlStatusDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144UrlStatusDao.delAllRecord(delList);
		
	}


}
