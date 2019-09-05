package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144WebErrorDao;
import com.syntun.entity.CrawlerTmall144WebError;
import com.syntun.service.CrawlerTmall144WebErrorService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144WebErrorService")
public class CrawlerTmall144WebErrorServiceImpl implements CrawlerTmall144WebErrorService{

    @Resource(name = "crawlerTmall144WebErrorDao")
    private CrawlerTmall144WebErrorDao crawlerTmall144WebErrorDao;

    @Override
    public List<CrawlerTmall144WebError> login() {
        return crawlerTmall144WebErrorDao.selectCrawlerTmall144WebError();
    }
    
    @Override
   	public List<CrawlerTmall144WebError> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144WebErrorDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144WebErrorDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144WebError> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144WebErrorDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144WebError> getList(HashMap<String, Object> params) {
		return crawlerTmall144WebErrorDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144WebErrorDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144WebErrorDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144WebErrorDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144WebErrorDao.delAllRecord(delList);
		
	}


}
