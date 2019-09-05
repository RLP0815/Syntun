package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.ProductStateDao;
import com.syntun.entity.ProductState;
import com.syntun.service.ProductStateService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("productStateService")
public class ProductStateServiceImpl implements ProductStateService{

    @Resource(name = "productStateDao")
    private ProductStateDao productStateDao;

    @Override
    public List<ProductState> login() {
        return productStateDao.selectProductState();
    }
    
    @Override
   	public List<ProductState> selectRecord(HashMap<String, Object> params) {
   		return productStateDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return productStateDao.getCount(params);
	}
    
    @Override
	public List<ProductState> getAllList() {
		return productStateDao.getAllList();
		
	}
	
    @Override
	public List<ProductState> getList(HashMap<String, Object> params) {
		return productStateDao.getList(params);
		
	}

	@Override
	public int addFiled(HashMap<String, Object> params) {
		return productStateDao.addFiled(params);
	}

	@Override
	public void delFiled(HashMap<String, Object> params) {
		productStateDao.delFiled(params);
		
	}

	@Override
	public void editFiled(HashMap<String, Object> params) {
		productStateDao.editFiled(params);
		
	}

	@Override
	public void delAllFiled(List<String> delList) {
		productStateDao.delAllFiled(delList);
		
	}


}
