package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144DataTableFiledListDao;
import com.syntun.entity.CrawlerCommentJD144DataTableFiledList;
import com.syntun.service.CrawlerCommentJD144DataTableFiledListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144DataTableFiledListService")
public class CrawlerCommentJD144DataTableFiledListServiceImpl implements CrawlerCommentJD144DataTableFiledListService{

    @Resource(name = "crawlerCommentJD144DataTableFiledListDao")
    private CrawlerCommentJD144DataTableFiledListDao crawlerCommentJD144DataTableFiledListDao;

    @Override
    public List<CrawlerCommentJD144DataTableFiledList> login() {
        return crawlerCommentJD144DataTableFiledListDao.selectCrawlerCommentJD144DataTableFiledList();
    }
    
    @Override
   	public List<CrawlerCommentJD144DataTableFiledList> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144DataTableFiledListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableFiledListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144DataTableFiledList> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableFiledListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144DataTableFiledList> getList(HashMap<String, Object> params) {
    	return crawlerCommentJD144DataTableFiledListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144DataTableFiledListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144DataTableFiledListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144DataTableFiledListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144DataTableFiledListDao.delAllRecord(delList);
		
	}


}
