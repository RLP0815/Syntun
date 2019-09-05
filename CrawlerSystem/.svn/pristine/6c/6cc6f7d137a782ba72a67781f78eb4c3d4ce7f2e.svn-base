package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144Url302ListDao;
import com.syntun.entity.CrawlerCommentJD144Url302List;
import com.syntun.service.CrawlerCommentJD144Url302ListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144Url302ListService")
public class CrawlerCommentJD144Url302ListServiceImpl implements CrawlerCommentJD144Url302ListService{

    @Resource(name = "crawlerCommentJD144Url302ListDao")
    private CrawlerCommentJD144Url302ListDao crawlerCommentJD144Url302ListDao;

    @Override
    public List<CrawlerCommentJD144Url302List> login() {
        return crawlerCommentJD144Url302ListDao.selectCrawlerCommentJD144Url302List();
    }
    
    @Override
   	public List<CrawlerCommentJD144Url302List> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144Url302ListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144Url302ListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144Url302List> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144Url302ListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144Url302List> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144Url302ListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144Url302ListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144Url302ListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144Url302ListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144Url302ListDao.delAllRecord(delList);
		
	}


}
