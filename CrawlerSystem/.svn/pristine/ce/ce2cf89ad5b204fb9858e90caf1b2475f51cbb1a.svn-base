package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144UrlStatusDao;
import com.syntun.entity.CrawlerCommentJD144UrlStatus;
import com.syntun.service.CrawlerCommentJD144UrlStatusService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144UrlStatusService")
public class CrawlerCommentJD144UrlStatusServiceImpl implements CrawlerCommentJD144UrlStatusService{

    @Resource(name = "crawlerCommentJD144UrlStatusDao")
    private CrawlerCommentJD144UrlStatusDao crawlerCommentJD144UrlStatusDao;

    @Override
    public List<CrawlerCommentJD144UrlStatus> login() {
        return crawlerCommentJD144UrlStatusDao.selectCrawlerCommentJD144UrlStatus();
    }
    
    @Override
   	public List<CrawlerCommentJD144UrlStatus> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144UrlStatusDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlStatusDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144UrlStatus> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlStatusDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144UrlStatus> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlStatusDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlStatusDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144UrlStatusDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144UrlStatusDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144UrlStatusDao.delAllRecord(delList);
		
	}


}
