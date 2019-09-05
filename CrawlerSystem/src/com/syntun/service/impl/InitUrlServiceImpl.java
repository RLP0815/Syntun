package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.InitUrlDao;
import com.syntun.entity.InitUrl;
import com.syntun.service.InitUrlService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("initUrlService")
public class InitUrlServiceImpl implements InitUrlService{

    @Resource(name = "initUrlDao")
    private InitUrlDao initUrlDao;

    @Override
    public List<InitUrl> login() {
        return initUrlDao.selectInitUrl();
    }
    
    @Override
   	public List<InitUrl> selectRecord(HashMap<String, Object> params) {
   		return initUrlDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return initUrlDao.getCount(params);
	}
    
    @Override
	public List<InitUrl> getAllList(HashMap<String, Object> params) {
		return initUrlDao.getAllList(params);
		
	}
	
    @Override
	public List<InitUrl> getList(HashMap<String, Object> params) {
		return initUrlDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return initUrlDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		initUrlDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		initUrlDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		initUrlDao.delAllRecord(delList);
		
	}


}
