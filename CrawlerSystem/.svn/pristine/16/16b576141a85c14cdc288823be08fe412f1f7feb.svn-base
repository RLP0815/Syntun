package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144DataTableListDao;
import com.syntun.entity.CrawlerJD144DataTableList;
import com.syntun.service.CrawlerJD144DataTableListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144DataTableListService")
public class CrawlerJD144DataTableListServiceImpl implements CrawlerJD144DataTableListService{

    @Resource(name = "crawlerJD144DataTableListDao")
    private CrawlerJD144DataTableListDao crawlerJD144DataTableListDao;

    @Override
    public List<CrawlerJD144DataTableList> login() {
        return crawlerJD144DataTableListDao.selectCrawlerJD144DataTable();
    }
    
    @Override
   	public List<CrawlerJD144DataTableList> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144DataTableListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144DataTableListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144DataTableList> getAllList(HashMap<String, Object> params) {
		return crawlerJD144DataTableListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144DataTableList> getList(HashMap<String, Object> params) {
		return crawlerJD144DataTableListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144DataTableListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144DataTableListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144DataTableListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144DataTableListDao.delAllRecord(delList);
		
	}


}
