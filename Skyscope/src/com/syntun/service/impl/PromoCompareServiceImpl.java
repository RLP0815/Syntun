package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PromoCompareDao;
import com.syntun.entity.PromoCompare;
import com.syntun.service.PromoCompareService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("promoCompareService")
public class PromoCompareServiceImpl implements PromoCompareService{

    @Resource(name = "promoCompareDao")
    private PromoCompareDao promoCompareDao;

    @Override
    public List<PromoCompare> login() {
        return promoCompareDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return promoCompareDao.getCount(params);
	}

    @Override
	public List<PromoCompare> getAllList() {
		return promoCompareDao.getAllList();
		
	}
	
    @Override
	public List<PromoCompare> getList(HashMap<String, Object> params) {
		return promoCompareDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return promoCompareDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		promoCompareDao.delPriceRecord(params);
		promoCompareDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		promoCompareDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		promoCompareDao.delAllRecord(delList);
		
	}

	@Override
	public void insert(PromoCompare promoCompare) {
		promoCompareDao.insert(promoCompare);
		
	}


}
