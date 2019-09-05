package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144PurlListDao;
import com.syntun.entity.CrawlerJD144PurlList;
import com.syntun.service.CrawlerJD144PurlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144PurlListService")
public class CrawlerJD144PurlListServiceImpl implements CrawlerJD144PurlListService{

    @Resource(name = "crawlerJD144PurlListDao")
    private CrawlerJD144PurlListDao crawlerJD144PurlListDao;

    @Override
    public List<CrawlerJD144PurlList> login() {
        return crawlerJD144PurlListDao.selectCrawlerJD144PurlList();
    }
    
    @Override
   	public List<CrawlerJD144PurlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144PurlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144PurlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144PurlList> getAllList(HashMap<String, Object> params) {
		return crawlerJD144PurlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144PurlList> getList(HashMap<String, Object> params) {
		return crawlerJD144PurlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144PurlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144PurlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144PurlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144PurlListDao.delAllRecord(delList);
		
	}


}
