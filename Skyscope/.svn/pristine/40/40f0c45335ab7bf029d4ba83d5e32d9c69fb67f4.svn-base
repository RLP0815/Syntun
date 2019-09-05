package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PriceComputeDao;
import com.syntun.entity.PriceCompute;
import com.syntun.service.PriceComputeService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("priceComputeService")
public class PriceComputeServiceImpl implements PriceComputeService{

    @Resource(name = "priceComputeDao")
    private PriceComputeDao priceComputeDao;

    @Override
    public List<PriceCompute> login() {
        return priceComputeDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return priceComputeDao.getCount(params);
	}

    @Override
	public List<PriceCompute> getAllList() {
		return priceComputeDao.getAllList();
		
	}
	
    @Override
	public List<PriceCompute> getList(HashMap<String, Object> params) {
		return priceComputeDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return priceComputeDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		priceComputeDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		priceComputeDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		priceComputeDao.delAllRecord(delList);
		
	}


}
