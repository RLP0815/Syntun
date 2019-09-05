package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerJD144DataTableFiledListDao;
import com.syntun.entity.CrawlerJD144DataTableFiledList;
import com.syntun.service.CrawlerJD144DataTableFiledListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerJD144DataTableFiledListService")
public class CrawlerJD144DataTableFiledListServiceImpl implements CrawlerJD144DataTableFiledListService{

    @Resource(name = "crawlerJD144DataTableFiledListDao")
    private CrawlerJD144DataTableFiledListDao crawlerJD144DataTableFiledListDao;

    @Override
    public List<CrawlerJD144DataTableFiledList> login() {
        return crawlerJD144DataTableFiledListDao.selectCrawlerJD144DataTableFiledList();
    }
    
    @Override
   	public List<CrawlerJD144DataTableFiledList> selectRecord(HashMap<String, Object> params) {
   		return crawlerJD144DataTableFiledListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerJD144DataTableFiledListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerJD144DataTableFiledList> getAllList(HashMap<String, Object> params) {
		return crawlerJD144DataTableFiledListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerJD144DataTableFiledList> getList(HashMap<String, Object> params) {
    	return crawlerJD144DataTableFiledListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerJD144DataTableFiledListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerJD144DataTableFiledListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerJD144DataTableFiledListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerJD144DataTableFiledListDao.delAllRecord(delList);
		
	}


}
