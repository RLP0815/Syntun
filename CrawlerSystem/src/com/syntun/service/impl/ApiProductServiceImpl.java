package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.ApiProductDao;
import com.syntun.entity.ApiProduct;
import com.syntun.service.ApiProductService;
/**
 * 
 */
@Service("apiProductService")
public class ApiProductServiceImpl implements ApiProductService{

    @Resource(name = "apiProductDao")
    private ApiProductDao apiProductDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return apiProductDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ApiProduct record) {
		return apiProductDao.insert(record);
	}

	@Override
	public void insertByBatch(List<ApiProduct> p) {
		apiProductDao.insertByBatch(p);
	}

	@Override
	public ApiProduct findone(int params) {
		return apiProductDao.findone(params);
	}

	@Override
	public int getTotal(Map params) {
		return apiProductDao.getTotal(params);
	}

	@Override
	public List<ApiProduct> find(Map params) {
		return apiProductDao.find(params);
	}

	@Override
	public List<ApiProduct> findInfo(Map params) {
		return apiProductDao.findInfo(params);
	}

}
