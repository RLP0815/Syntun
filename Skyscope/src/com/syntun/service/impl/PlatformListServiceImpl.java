package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PlatformListDao;
import com.syntun.entity.PlatformList;
import com.syntun.service.PlatformListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("platformListService")
public class PlatformListServiceImpl implements PlatformListService{

    @Resource(name = "platformListDao")
    private PlatformListDao platformListDao;

    @Override
    public List<PlatformList> login() {
        return platformListDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return platformListDao.getCount(params);
	}

    @Override
	public List<PlatformList> getAllList() {
		return platformListDao.getAllList();
		
	}
	
    @Override
	public List<PlatformList> getList(HashMap<String, Object> params) {
		return platformListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return platformListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		platformListDao.delPromoRecord(params);
		platformListDao.delPriceRecord(params);
		platformListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		platformListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		platformListDao.delAllPromoRecord(delList);
		platformListDao.delAllPriceRecord(delList);
		platformListDao.delAllRecord(delList);
		
	}


}
