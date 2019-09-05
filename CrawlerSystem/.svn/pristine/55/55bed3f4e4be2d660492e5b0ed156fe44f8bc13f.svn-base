package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144DataTableListDao;
import com.syntun.entity.CrawlerTmall144DataTableList;
import com.syntun.service.CrawlerTmall144DataTableListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144DataTableListService")
public class CrawlerTmall144DataTableListServiceImpl implements CrawlerTmall144DataTableListService{

    @Resource(name = "crawlerTmall144DataTableListDao")
    private CrawlerTmall144DataTableListDao crawlerTmall144DataTableListDao;

    @Override
    public List<CrawlerTmall144DataTableList> login() {
        return crawlerTmall144DataTableListDao.selectCrawlerTmall144DataTable();
    }
    
    @Override
   	public List<CrawlerTmall144DataTableList> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144DataTableListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144DataTableListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144DataTableList> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144DataTableListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144DataTableList> getList(HashMap<String, Object> params) {
		return crawlerTmall144DataTableListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144DataTableListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144DataTableListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144DataTableListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144DataTableListDao.delAllRecord(delList);
		
	}


}
