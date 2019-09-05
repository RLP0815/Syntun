package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.ApiDocDao;
import com.syntun.entity.ApiDoc;
import com.syntun.service.ApiDocService;
/**
 * 
 */
@Service("apiDocService")
public class ApiDocServiceImpl implements ApiDocService{

    @Resource(name = "apiDocDao")
    private ApiDocDao apiDocDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return apiDocDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ApiDoc record) {
		return apiDocDao.insert(record);
	}

	@Override
	public void insertByBatch(List<ApiDoc> p) {
		apiDocDao.insertByBatch(p);
	}

	@Override
	public ApiDoc findone(int params) {
		return apiDocDao.findone(params);
	}

	@Override
	public int getTotal(Map params) {
		return apiDocDao.getTotal(params);
	}

	@Override
	public List<ApiDoc> find(Map params) {
		return apiDocDao.find(params);
	}

	@Override
	public List<ApiDoc> findInfo(Map params) {
		return apiDocDao.findInfo(params);
	}

}
