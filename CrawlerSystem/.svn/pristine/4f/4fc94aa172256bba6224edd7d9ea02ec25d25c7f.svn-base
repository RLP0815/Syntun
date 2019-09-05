package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ReplaceStrDao;
import com.syntun.entity.ReplaceStr;
import com.syntun.service.ReplaceStrService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("replaceStrService")
public class ReplaceStrServiceImpl implements ReplaceStrService{

    @Resource(name = "replaceStrDao")
    private ReplaceStrDao replaceStrDao;

    @Override
    public List<ReplaceStr> login() {
        return replaceStrDao.selectReplaceStr();
    }
    
    @Override
   	public List<ReplaceStr> selectRecord(HashMap<String, Object> params) {
   		return replaceStrDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return replaceStrDao.getCount(params);
	}
    
    @Override
	public List<ReplaceStr> getAllList(HashMap<String, Object> params) {
		return replaceStrDao.getAllList(params);
		
	}
	
    @Override
	public List<ReplaceStr> getList(HashMap<String, Object> params) {
		return replaceStrDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return replaceStrDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		replaceStrDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		replaceStrDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		replaceStrDao.delAllRecord(delList);
		
	}


}
