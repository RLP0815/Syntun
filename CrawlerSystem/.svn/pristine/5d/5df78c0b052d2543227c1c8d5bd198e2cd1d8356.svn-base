package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144WebErrorDao;
import com.syntun.entity.CrawlerJD144WebError;
import com.syntun.service.CrawlerJD144WebErrorService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144WebErrorService")
public class CrawlerJD144WebErrorServiceImpl implements CrawlerJD144WebErrorService{

    @Resource(name = "crawlerJD144WebErrorDao")
    private CrawlerJD144WebErrorDao crawlerJD144WebErrorDao;

    @Override
    public List<CrawlerJD144WebError> login() {
        return crawlerJD144WebErrorDao.selectCrawlerJD144WebError();
    }
    
    @Override
   	public List<CrawlerJD144WebError> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144WebErrorDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144WebErrorDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144WebError> getAllList(HashMap<String, Object> params) {
		return crawlerJD144WebErrorDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144WebError> getList(HashMap<String, Object> params) {
		return crawlerJD144WebErrorDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144WebErrorDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144WebErrorDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144WebErrorDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144WebErrorDao.delAllRecord(delList);
		
	}


}
