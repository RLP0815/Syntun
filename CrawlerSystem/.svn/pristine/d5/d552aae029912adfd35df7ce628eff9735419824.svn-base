package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144WebErrorDao;
import com.syntun.entity.CrawlerCommentJD144WebError;
import com.syntun.service.CrawlerCommentJD144WebErrorService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144WebErrorService")
public class CrawlerCommentJD144WebErrorServiceImpl implements CrawlerCommentJD144WebErrorService{

    @Resource(name = "crawlerCommentJD144WebErrorDao")
    private CrawlerCommentJD144WebErrorDao crawlerCommentJD144WebErrorDao;

    @Override
    public List<CrawlerCommentJD144WebError> login() {
        return crawlerCommentJD144WebErrorDao.selectCrawlerCommentJD144WebError();
    }
    
    @Override
   	public List<CrawlerCommentJD144WebError> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144WebErrorDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144WebErrorDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144WebError> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144WebErrorDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144WebError> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144WebErrorDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144WebErrorDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144WebErrorDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144WebErrorDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144WebErrorDao.delAllRecord(delList);
		
	}


}
