package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.EditRemarkDao;
import com.syntun.entity.EditRemark;
import com.syntun.service.EditRemarkService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("editRemarkService")
public class EditRemarkServiceImpl implements EditRemarkService{

    @Resource(name = "editRemarkDao")
    private EditRemarkDao editRemarkDao;

    @Override
    public List<EditRemark> login() {
        return editRemarkDao.selectEditRemark();
    }
    
    @Override
   	public List<EditRemark> selectRecord(HashMap<String, Object> params) {
   		return editRemarkDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return editRemarkDao.getCount(params);
	}
    
    @Override
	public List<EditRemark> getAllList(HashMap<String, Object> params) {
		return editRemarkDao.getAllList(params);
		
	}
	
    @Override
	public List<EditRemark> getList(HashMap<String, Object> params) {
    	return editRemarkDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return editRemarkDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		editRemarkDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		editRemarkDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		editRemarkDao.delAllRecord(delList);
		
	}


}
