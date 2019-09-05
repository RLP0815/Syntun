package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ReplaceFiledDao;
import com.syntun.entity.ReplaceFiled;
import com.syntun.service.ReplaceFiledService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("replaceFiledService")
public class ReplaceFiledServiceImpl implements ReplaceFiledService{

    @Resource(name = "replaceFiledDao")
    private ReplaceFiledDao replaceFiledDao;

    @Override
    public List<ReplaceFiled> login() {
        return replaceFiledDao.selectReplaceFiled();
    }
    
    @Override
   	public List<ReplaceFiled> selectRecord(HashMap<String, Object> params) {
   		return replaceFiledDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return replaceFiledDao.getCount(params);
	}
    
    @Override
	public List<ReplaceFiled> getAllList() {
		return replaceFiledDao.getAllList();
		
	}
	
    @Override
	public List<ReplaceFiled> getList(HashMap<String, Object> params) {
		return replaceFiledDao.getList(params);
		
	}

	@Override
	public int addFiled(HashMap<String, Object> params) {
		return replaceFiledDao.addFiled(params);
	}

	@Override
	public void delFiled(HashMap<String, Object> params) {
		replaceFiledDao.delFiled(params);
		
	}

	@Override
	public void editFiled(HashMap<String, Object> params) {
		replaceFiledDao.editFiled(params);
		
	}

	@Override
	public void delAllFiled(List<String> delList) {
		replaceFiledDao.delAllFiled(delList);
		
	}


}
