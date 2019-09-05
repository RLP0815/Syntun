package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144InitUrlListDao;
import com.syntun.entity.CrawlerCommentJD144InitUrlList;
import com.syntun.service.CrawlerCommentJD144InitUrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144InitUrlListService")
public class CrawlerCommentJD144InitUrlListServiceImpl implements CrawlerCommentJD144InitUrlListService{

    @Resource(name = "crawlerCommentJD144InitUrlListDao")
    private CrawlerCommentJD144InitUrlListDao crawlerCommentJD144InitUrlListDao;

    @Override
    public List<CrawlerCommentJD144InitUrlList> login() {
        return crawlerCommentJD144InitUrlListDao.selectCrawlerCommentJD144InitUrlList();
    }
    
    @Override
   	public List<CrawlerCommentJD144InitUrlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144InitUrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144InitUrlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144InitUrlList> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144InitUrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144InitUrlList> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144InitUrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144InitUrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144InitUrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144InitUrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144InitUrlListDao.delAllRecord(delList);
		
	}


}
