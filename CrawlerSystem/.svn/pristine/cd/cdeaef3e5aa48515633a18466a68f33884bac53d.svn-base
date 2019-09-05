package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144UrlListDao;
import com.syntun.entity.CrawlerJD144UrlList;
import com.syntun.service.CrawlerJD144UrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144UrlListService")
public class CrawlerJD144UrlListServiceImpl implements CrawlerJD144UrlListService{

    @Resource(name = "crawlerJD144UrlListDao")
    private CrawlerJD144UrlListDao crawlerJD144UrlListDao;

    @Override
    public List<CrawlerJD144UrlList> login() {
        return crawlerJD144UrlListDao.selectCrawlerJD144UrlList();
    }
    
    @Override
   	public List<CrawlerJD144UrlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144UrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144UrlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144UrlList> getAllList(HashMap<String, Object> params) {
		return crawlerJD144UrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144UrlList> getList(HashMap<String, Object> params) {
		return crawlerJD144UrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144UrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144UrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144UrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144UrlListDao.delAllRecord(delList);
		
	}


}
