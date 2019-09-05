package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144UrlStatusDao;
import com.syntun.entity.Crawler144UrlStatus;
import com.syntun.service.Crawler144UrlStatusService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144UrlStatusService")
public class Crawler144UrlStatusServiceImpl implements Crawler144UrlStatusService{

    @Resource(name = "crawler144UrlStatusDao")
    private Crawler144UrlStatusDao crawler144UrlStatusDao;

    @Override
    public List<Crawler144UrlStatus> login() {
        return crawler144UrlStatusDao.selectCrawler144UrlStatus();
    }
    
    @Override
   	public List<Crawler144UrlStatus> selectRecord(HashMap<String, Object> params) {
   		return crawler144UrlStatusDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144UrlStatusDao.getCount(params);
	}
    
    @Override
	public List<Crawler144UrlStatus> getAllList(HashMap<String, Object> params) {
		return crawler144UrlStatusDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144UrlStatus> getList(HashMap<String, Object> params) {
		return crawler144UrlStatusDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144UrlStatusDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144UrlStatusDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144UrlStatusDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144UrlStatusDao.delAllRecord(delList);
		
	}


}
