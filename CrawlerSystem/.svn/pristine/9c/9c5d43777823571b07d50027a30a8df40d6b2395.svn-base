package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Url302ListDao;
import com.syntun.entity.Url302List;
import com.syntun.service.Url302ListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("url302ListService")
public class Url302ListServiceImpl implements Url302ListService{

    @Resource(name = "url302ListDao")
    private Url302ListDao url302ListDao;

    @Override
    public List<Url302List> login() {
        return url302ListDao.selectUrl302List();
    }
    
    @Override
   	public List<Url302List> selectRecord(HashMap<String, Object> params) {
   		return url302ListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return url302ListDao.getCount(params);
	}
    
    @Override
	public List<Url302List> getAllList(HashMap<String, Object> params) {
		return url302ListDao.getAllList(params);
		
	}
	
    @Override
	public List<Url302List> getList(HashMap<String, Object> params) {
		return url302ListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return url302ListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		url302ListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		url302ListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		url302ListDao.delAllRecord(delList);
		
	}


}
