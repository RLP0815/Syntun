package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144WebErrorDao;
import com.syntun.entity.Crawler144WebError;
import com.syntun.service.Crawler144WebErrorService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144WebErrorService")
public class Crawler144WebErrorServiceImpl implements Crawler144WebErrorService{

    @Resource(name = "crawler144WebErrorDao")
    private Crawler144WebErrorDao crawler144WebErrorDao;

    @Override
    public List<Crawler144WebError> login() {
        return crawler144WebErrorDao.selectCrawler144WebError();
    }
    
    @Override
   	public List<Crawler144WebError> selectRecord(HashMap<String, Object> params) {
   		return crawler144WebErrorDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144WebErrorDao.getCount(params);
	}
    
    @Override
	public List<Crawler144WebError> getAllList(HashMap<String, Object> params) {
		return crawler144WebErrorDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144WebError> getList(HashMap<String, Object> params) {
		return crawler144WebErrorDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144WebErrorDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144WebErrorDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144WebErrorDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144WebErrorDao.delAllRecord(delList);
		
	}


}
