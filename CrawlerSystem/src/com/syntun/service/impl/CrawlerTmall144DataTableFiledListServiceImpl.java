package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144DataTableFiledListDao;
import com.syntun.entity.CrawlerTmall144DataTableFiledList;
import com.syntun.service.CrawlerTmall144DataTableFiledListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144DataTableFiledListService")
public class CrawlerTmall144DataTableFiledListServiceImpl implements CrawlerTmall144DataTableFiledListService{

    @Resource(name = "crawlerTmall144DataTableFiledListDao")
    private CrawlerTmall144DataTableFiledListDao crawlerTmall144DataTableFiledListDao;

    @Override
    public List<CrawlerTmall144DataTableFiledList> login() {
        return crawlerTmall144DataTableFiledListDao.selectCrawlerTmall144DataTableFiledList();
    }
    
    @Override
   	public List<CrawlerTmall144DataTableFiledList> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144DataTableFiledListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144DataTableFiledListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144DataTableFiledList> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144DataTableFiledListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144DataTableFiledList> getList(HashMap<String, Object> params) {
    	return crawlerTmall144DataTableFiledListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144DataTableFiledListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144DataTableFiledListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144DataTableFiledListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144DataTableFiledListDao.delAllRecord(delList);
		
	}


}
