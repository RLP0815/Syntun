package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144InitUrlListDao;
import com.syntun.entity.CrawlerTmall144InitUrlList;
import com.syntun.service.CrawlerTmall144InitUrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144InitUrlListService")
public class CrawlerTmall144InitUrlListServiceImpl implements CrawlerTmall144InitUrlListService{

    @Resource(name = "crawlerTmall144InitUrlListDao")
    private CrawlerTmall144InitUrlListDao crawlerTmall144InitUrlListDao;

    @Override
    public List<CrawlerTmall144InitUrlList> login() {
        return crawlerTmall144InitUrlListDao.selectCrawlerTmall144InitUrlList();
    }
    
    @Override
   	public List<CrawlerTmall144InitUrlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144InitUrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144InitUrlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144InitUrlList> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144InitUrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144InitUrlList> getList(HashMap<String, Object> params) {
		return crawlerTmall144InitUrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144InitUrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144InitUrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144InitUrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144InitUrlListDao.delAllRecord(delList);
		
	}


}
