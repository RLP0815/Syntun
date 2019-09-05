package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144DataTableListDao;
import com.syntun.entity.CrawlerCommentJD144DataTableList;
import com.syntun.service.CrawlerCommentJD144DataTableListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144DataTableListService")
public class CrawlerCommentJD144DataTableListServiceImpl implements CrawlerCommentJD144DataTableListService{

    @Resource(name = "crawlerCommentJD144DataTableListDao")
    private CrawlerCommentJD144DataTableListDao crawlerCommentJD144DataTableListDao;

    @Override
    public List<CrawlerCommentJD144DataTableList> login() {
        return crawlerCommentJD144DataTableListDao.selectCrawlerCommentJD144DataTable();
    }
    
    @Override
   	public List<CrawlerCommentJD144DataTableList> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144DataTableListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144DataTableList> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144DataTableList> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144DataTableListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144DataTableListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144DataTableListDao.delAllRecord(delList);
		
	}


}
