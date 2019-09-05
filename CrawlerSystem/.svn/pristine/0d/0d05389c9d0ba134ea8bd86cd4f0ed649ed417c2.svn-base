package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144Url302ListDao;
import com.syntun.entity.CrawlerTmall144Url302List;
import com.syntun.service.CrawlerTmall144Url302ListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144Url302ListService")
public class CrawlerTmall144Url302ListServiceImpl implements CrawlerTmall144Url302ListService{

    @Resource(name = "crawlerTmall144Url302ListDao")
    private CrawlerTmall144Url302ListDao crawlerTmall144Url302ListDao;

    @Override
    public List<CrawlerTmall144Url302List> login() {
        return crawlerTmall144Url302ListDao.selectCrawlerTmall144Url302List();
    }
    
    @Override
   	public List<CrawlerTmall144Url302List> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144Url302ListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144Url302ListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144Url302List> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144Url302ListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144Url302List> getList(HashMap<String, Object> params) {
		return crawlerTmall144Url302ListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144Url302ListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144Url302ListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144Url302ListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144Url302ListDao.delAllRecord(delList);
		
	}


}
