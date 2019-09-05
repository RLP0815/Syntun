package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ProductPlatformListDao;
import com.syntun.entity.ProductPlatformList;
import com.syntun.service.ProductPlatformListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("productPlatformListService")
public class ProductPlatformListServiceImpl implements ProductPlatformListService{

    @Resource(name = "productPlatformListDao")
    private ProductPlatformListDao productPlatformListDao;

    @Override
    public List<ProductPlatformList> login() {
        return productPlatformListDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return productPlatformListDao.getCount(params);
	}

    @Override
	public List<ProductPlatformList> getAllList() {
		return productPlatformListDao.getAllList();
		
	}
	
    @Override
	public List<ProductPlatformList> getList(HashMap<String, Object> params) {
		return productPlatformListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return productPlatformListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		productPlatformListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		productPlatformListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		productPlatformListDao.delAllRecord(delList);
		
	}


}
