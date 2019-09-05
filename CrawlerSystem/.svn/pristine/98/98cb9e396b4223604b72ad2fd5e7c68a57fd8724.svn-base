package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144InitUrlListDao;
import com.syntun.entity.Crawler144InitUrlList;
import com.syntun.service.Crawler144InitUrlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144InitUrlListService")
public class Crawler144InitUrlListServiceImpl implements Crawler144InitUrlListService{

    @Resource(name = "crawler144InitUrlListDao")
    private Crawler144InitUrlListDao crawler144InitUrlListDao;

    @Override
    public List<Crawler144InitUrlList> login() {
        return crawler144InitUrlListDao.selectCrawler144InitUrlList();
    }
    
    @Override
   	public List<Crawler144InitUrlList> selectRecord(HashMap<String, Object> params) {
   		return crawler144InitUrlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144InitUrlListDao.getCount(params);
	}
    
    @Override
	public List<Crawler144InitUrlList> getAllList(HashMap<String, Object> params) {
		return crawler144InitUrlListDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144InitUrlList> getList(HashMap<String, Object> params) {
		return crawler144InitUrlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144InitUrlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144InitUrlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144InitUrlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144InitUrlListDao.delAllRecord(delList);
		
	}


}
