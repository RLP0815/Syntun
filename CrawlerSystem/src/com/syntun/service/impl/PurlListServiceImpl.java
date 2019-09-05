package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PurlListDao;
import com.syntun.entity.PurlList;
import com.syntun.service.PurlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("purlListService")
public class PurlListServiceImpl implements PurlListService{

    @Resource(name = "purlListDao")
    private PurlListDao purlListDao;

    @Override
    public List<PurlList> login() {
        return purlListDao.selectPurlList();
    }
    
    @Override
   	public List<PurlList> selectRecord(HashMap<String, Object> params) {
   		return purlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return purlListDao.getCount(params);
	}
    
    @Override
	public List<PurlList> getAllList(HashMap<String, Object> params) {
		return purlListDao.getAllList(params);
		
	}
	
    @Override
	public List<PurlList> getList(HashMap<String, Object> params) {
		return purlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return purlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		purlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		purlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		purlListDao.delAllRecord(delList);
		
	}


}
