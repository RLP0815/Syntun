package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ProductUrlDao;
import com.syntun.entity.ProductUrl;
import com.syntun.service.ProductUrlService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("productUrlService")
public class ProductUrlServiceImpl implements ProductUrlService{

    @Resource(name = "productUrlDao")
    private ProductUrlDao productUrlDao;

    @Override
    public List<ProductUrl> login() {
        return productUrlDao.selectProductUrl();
    }
    
    @Override
   	public List<ProductUrl> selectRecord(HashMap<String, Object> params) {
   		return productUrlDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return productUrlDao.getCount(params);
	}
    
    @Override
	public List<ProductUrl> getAllList(HashMap<String, Object> params) {
		return productUrlDao.getAllList(params);
		
	}
	
    @Override
	public List<ProductUrl> getList(HashMap<String, Object> params) {
		return productUrlDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return productUrlDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		productUrlDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		productUrlDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		productUrlDao.delAllRecord(delList);
		
	}


}
