package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144Url302ListDao;
import com.syntun.entity.CrawlerJD144Url302List;
import com.syntun.service.CrawlerJD144Url302ListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144Url302ListService")
public class CrawlerJD144Url302ListServiceImpl implements CrawlerJD144Url302ListService{

    @Resource(name = "crawlerJD144Url302ListDao")
    private CrawlerJD144Url302ListDao crawlerJD144Url302ListDao;

    @Override
    public List<CrawlerJD144Url302List> login() {
        return crawlerJD144Url302ListDao.selectCrawlerJD144Url302List();
    }
    
    @Override
   	public List<CrawlerJD144Url302List> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144Url302ListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144Url302ListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144Url302List> getAllList(HashMap<String, Object> params) {
		return crawlerJD144Url302ListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144Url302List> getList(HashMap<String, Object> params) {
		return crawlerJD144Url302ListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144Url302ListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144Url302ListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144Url302ListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144Url302ListDao.delAllRecord(delList);
		
	}


}
