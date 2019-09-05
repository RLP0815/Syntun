package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144UrlStatusDao;
import com.syntun.entity.CrawlerTmall144UrlStatus;
import com.syntun.service.CrawlerTmall144UrlStatusService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144UrlStatusService")
public class CrawlerTmall144UrlStatusServiceImpl implements CrawlerTmall144UrlStatusService{

    @Resource(name = "crawlerTmall144UrlStatusDao")
    private CrawlerTmall144UrlStatusDao crawlerTmall144UrlStatusDao;

    @Override
    public List<CrawlerTmall144UrlStatus> login() {
        return crawlerTmall144UrlStatusDao.selectCrawlerTmall144UrlStatus();
    }
    
    @Override
   	public List<CrawlerTmall144UrlStatus> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144UrlStatusDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144UrlStatusDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144UrlStatus> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144UrlStatusDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144UrlStatus> getList(HashMap<String, Object> params) {
		return crawlerTmall144UrlStatusDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144UrlStatusDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144UrlStatusDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144UrlStatusDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144UrlStatusDao.delAllRecord(delList);
		
	}


}
