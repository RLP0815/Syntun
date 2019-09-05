package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PatternListDao;
import com.syntun.entity.PatternList;
import com.syntun.service.PatternListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("patternListService")
public class PatternListServiceImpl implements PatternListService{

    @Resource(name = "patternListDao")
    private PatternListDao patternListDao;

    @Override
    public List<PatternList> login() {
        return patternListDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return patternListDao.getCount(params);
	}

    @Override
	public List<PatternList> getAllList() {
		return patternListDao.getAllList();
		
	}
	
    @Override
	public List<PatternList> getList(HashMap<String, Object> params) {
		return patternListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return patternListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		patternListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		patternListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		patternListDao.delAllRecord(delList);
		
	}


}
