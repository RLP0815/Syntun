package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.DataRrocessingSqlDao;
import com.syntun.entity.DataRrocessingSql;
import com.syntun.service.DataRrocessingSqlService;

@Service("dataRrocessingSqlService")
public class DataRrocessingSqlServiceImpl implements DataRrocessingSqlService{
	
    @Resource(name = "dataRrocessingSqlDao")
    private DataRrocessingSqlDao dataRrocessingSqlDao;

	@Override
	public int deleteByPrimaryKey(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DataRrocessingSql record) {
		return dataRrocessingSqlDao.insert(record);
	}

	@Override
	public int insertSelective(DataRrocessingSql record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataRrocessingSql selectByPrimaryKey(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DataRrocessingSql record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DataRrocessingSql record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataRrocessingSql> find(Map params) {
		return dataRrocessingSqlDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return dataRrocessingSqlDao.getTotal(params);
	}

}
