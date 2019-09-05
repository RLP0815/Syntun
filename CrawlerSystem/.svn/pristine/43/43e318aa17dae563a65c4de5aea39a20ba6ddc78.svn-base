package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144InitUrlListDao;
import com.syntun.entity.CrawlerJD144InitUrlList;
import com.syntun.service.CrawlerJD144InitUrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144InitUrlListService")
public class CrawlerJD144InitUrlListServiceImpl implements CrawlerJD144InitUrlListService{

    @Resource(name = "crawlerJD144InitUrlListDao")
    private CrawlerJD144InitUrlListDao crawlerJD144InitUrlListDao;

    @Override
    public List<CrawlerJD144InitUrlList> login() {
        return crawlerJD144InitUrlListDao.selectCrawlerJD144InitUrlList();
    }
    
    @Override
   	public List<CrawlerJD144InitUrlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144InitUrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144InitUrlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144InitUrlList> getAllList(HashMap<String, Object> params) {
		return crawlerJD144InitUrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144InitUrlList> getList(HashMap<String, Object> params) {
		return crawlerJD144InitUrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144InitUrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144InitUrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144InitUrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144InitUrlListDao.delAllRecord(delList);
		
	}


}
