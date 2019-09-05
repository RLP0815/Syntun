package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.ApiNameDao;
import com.syntun.entity.ApiName;
import com.syntun.service.ApiNameService;
/**
 * 
 */
@Service("apiNameService")
public class ApiNameServiceImpl implements ApiNameService{

    @Resource(name = "apiNameDao")
    private ApiNameDao apiNameDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return apiNameDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ApiName record) {
		return apiNameDao.insert(record);
	}

	@Override
	public void insertByBatch(List<ApiName> p) {
		apiNameDao.insertByBatch(p);
	}

	@Override
	public ApiName findone(int params) {
		return apiNameDao.findone(params);
	}

	@Override
	public int getTotal(Map params) {
		return apiNameDao.getTotal(params);
	}

	@Override
	public List<ApiName> find(Map params) {
		return apiNameDao.find(params);
	}

	@Override
	public List<ApiName> findInfo(Map params) {
		return apiNameDao.findInfo(params);
	}

}
