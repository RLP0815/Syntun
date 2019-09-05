package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144UrlListDao;
import com.syntun.entity.Crawler144UrlList;
import com.syntun.service.Crawler144UrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144UrlListService")
public class Crawler144UrlListServiceImpl implements Crawler144UrlListService{

    @Resource(name = "crawler144UrlListDao")
    private Crawler144UrlListDao crawler144UrlListDao;

    @Override
    public List<Crawler144UrlList> login() {
        return crawler144UrlListDao.selectCrawler144UrlList();
    }
    
    @Override
   	public List<Crawler144UrlList> selectRecord(HashMap<String, Object> params) {
   		return crawler144UrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144UrlListDao.getCount(params);
	}
    
    @Override
	public List<Crawler144UrlList> getAllList(HashMap<String, Object> params) {
		return crawler144UrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144UrlList> getList(HashMap<String, Object> params) {
		return crawler144UrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144UrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144UrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144UrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144UrlListDao.delAllRecord(delList);
		
	}


}
