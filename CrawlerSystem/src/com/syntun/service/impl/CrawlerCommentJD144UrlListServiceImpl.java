package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144UrlListDao;
import com.syntun.entity.CrawlerCommentJD144UrlList;
import com.syntun.service.CrawlerCommentJD144UrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144UrlListService")
public class CrawlerCommentJD144UrlListServiceImpl implements CrawlerCommentJD144UrlListService{

    @Resource(name = "crawlerCommentJD144UrlListDao")
    private CrawlerCommentJD144UrlListDao crawlerCommentJD144UrlListDao;

    @Override
    public List<CrawlerCommentJD144UrlList> login() {
        return crawlerCommentJD144UrlListDao.selectCrawlerCommentJD144UrlList();
    }
    
    @Override
   	public List<CrawlerCommentJD144UrlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144UrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144UrlList> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144UrlList> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144UrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144UrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144UrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144UrlListDao.delAllRecord(delList);
		
	}


}
