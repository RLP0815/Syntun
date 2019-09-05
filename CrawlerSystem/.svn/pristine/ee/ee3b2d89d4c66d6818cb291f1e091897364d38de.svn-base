package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144PurlListDao;
import com.syntun.entity.Crawler144PurlList;
import com.syntun.service.Crawler144PurlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144PurlListService")
public class Crawler144PurlListServiceImpl implements Crawler144PurlListService{

    @Resource(name = "crawler144PurlListDao")
    private Crawler144PurlListDao crawler144PurlListDao;

    @Override
    public List<Crawler144PurlList> login() {
        return crawler144PurlListDao.selectCrawler144PurlList();
    }
    
    @Override
   	public List<Crawler144PurlList> selectRecord(HashMap<String, Object> params) {
   		return crawler144PurlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144PurlListDao.getCount(params);
	}
    
    @Override
	public List<Crawler144PurlList> getAllList(HashMap<String, Object> params) {
		return crawler144PurlListDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144PurlList> getList(HashMap<String, Object> params) {
		return crawler144PurlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144PurlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144PurlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144PurlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144PurlListDao.delAllRecord(delList);
		
	}


}
