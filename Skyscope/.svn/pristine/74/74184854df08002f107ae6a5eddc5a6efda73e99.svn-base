package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ErrResultDao;
import com.syntun.entity.ErrResult;
import com.syntun.service.ErrResultService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("errResultService")
public class ErrResultServiceImpl implements ErrResultService{

    @Resource(name = "errResultDao")
    private ErrResultDao errResultDao;

    @Override
    public List<ErrResult> login() {
        return errResultDao.selectErrResult();
    }
    
    @Override
   	public List<ErrResult> selectRecord(HashMap<String, Object> params) {
   		return errResultDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return errResultDao.getCount(params);
	}
    
    @Override
	public List<ErrResult> getAllList() {
		return errResultDao.getAllList();
		
	}
	
    @Override
	public List<ErrResult> getList(HashMap<String, Object> params) {
		return errResultDao.getList(params);
		
	}
    
    @Override
	public void delAllRecord() {
    	errResultDao.delAllRecord();
		
	}

}
