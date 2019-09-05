package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144Url302ListDao;
import com.syntun.entity.Crawler144Url302List;
import com.syntun.service.Crawler144Url302ListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144Url302ListService")
public class Crawler144Url302ListServiceImpl implements Crawler144Url302ListService{

    @Resource(name = "crawler144Url302ListDao")
    private Crawler144Url302ListDao crawler144Url302ListDao;

    @Override
    public List<Crawler144Url302List> login() {
        return crawler144Url302ListDao.selectCrawler144Url302List();
    }
    
    @Override
   	public List<Crawler144Url302List> selectRecord(HashMap<String, Object> params) {
   		return crawler144Url302ListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144Url302ListDao.getCount(params);
	}
    
    @Override
	public List<Crawler144Url302List> getAllList(HashMap<String, Object> params) {
		return crawler144Url302ListDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144Url302List> getList(HashMap<String, Object> params) {
		return crawler144Url302ListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144Url302ListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144Url302ListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144Url302ListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144Url302ListDao.delAllRecord(delList);
		
	}


}
