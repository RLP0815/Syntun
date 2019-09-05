package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.PlatformClassifyDao;
import com.syntun.entity.PlatformClassify;
import com.syntun.entity.WebsiteTable;
import com.syntun.service.PlatformClassifyService;

@Service("platformClassifyService")
public class PlatformClassifyServiceImpl implements PlatformClassifyService{

    @Resource(name = "platformClassifyDao")
    private PlatformClassifyDao platformClassifyDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(PlatformClassify record) {
		return platformClassifyDao.insert(record);
	}

	@Override
	public int insertSelective(PlatformClassify record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PlatformClassify selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(PlatformClassify record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(PlatformClassify record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertByBatch(List<PlatformClassify> p) {
		platformClassifyDao.insertByBatch(p);
	}

	@Override
	public List<PlatformClassify> find(Map params) {
		return platformClassifyDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return platformClassifyDao.getTotal(params);
	}

	@Override
	public List<WebsiteTable> findWebsiteTable(Map params) {
		return platformClassifyDao.findWebsiteTable(params);
	}

	@Override
	public List<String> getBydatabase() {
		return platformClassifyDao.getBydatabase();
	}

	@Override
	public List<String> getBytable(String database) {
		return platformClassifyDao.getBytable(database);
	}

}
