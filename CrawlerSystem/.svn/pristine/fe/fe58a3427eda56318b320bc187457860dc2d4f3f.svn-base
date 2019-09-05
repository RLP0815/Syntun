package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.WebErrorDao;
import com.syntun.entity.WebError;
import com.syntun.service.WebErrorService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("webErrorService")
public class WebErrorServiceImpl implements WebErrorService{

    @Resource(name = "webErrorDao")
    private WebErrorDao webErrorDao;

    @Override
    public List<WebError> login() {
        return webErrorDao.selectWebError();
    }
    
    @Override
   	public List<WebError> selectRecord(HashMap<String, Object> params) {
   		return webErrorDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return webErrorDao.getCount(params);
	}
    
    @Override
	public List<WebError> getAllList(HashMap<String, Object> params) {
		return webErrorDao.getAllList(params);
		
	}
	
    @Override
	public List<WebError> getList(HashMap<String, Object> params) {
		return webErrorDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return webErrorDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		webErrorDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		webErrorDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		webErrorDao.delAllRecord(delList);
		
	}


}
