package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144UrlListDao;
import com.syntun.entity.CrawlerTmall144UrlList;
import com.syntun.service.CrawlerTmall144UrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144UrlListService")
public class CrawlerTmall144UrlListServiceImpl implements CrawlerTmall144UrlListService{

    @Resource(name = "crawlerTmall144UrlListDao")
    private CrawlerTmall144UrlListDao crawlerTmall144UrlListDao;

    @Override
    public List<CrawlerTmall144UrlList> login() {
        return crawlerTmall144UrlListDao.selectCrawlerTmall144UrlList();
    }
    
    @Override
   	public List<CrawlerTmall144UrlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144UrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144UrlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144UrlList> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144UrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144UrlList> getList(HashMap<String, Object> params) {
		return crawlerTmall144UrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144UrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144UrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144UrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144UrlListDao.delAllRecord(delList);
		
	}


}
