package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.JiexisuduDao;
import com.syntun.entity.Jiexisudu;
import com.syntun.service.JiexisuduService;

@Service("jiexisuduService")
public class JiexisuduServiceImpl implements JiexisuduService{
	
    @Resource(name = "jiexisuduDao")
    private JiexisuduDao jiexisuduDao;

	@Override
	public int deleteByPrimaryKey(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Jiexisudu record) {
		return jiexisuduDao.insert(record);
	}

	@Override
	public int insertSelective(Jiexisudu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Jiexisudu selectByPrimaryKey(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Jiexisudu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Jiexisudu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Jiexisudu> find(Map params) {
		return jiexisuduDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return jiexisuduDao.getTotal(params);
	}

	@Override
	public Jiexisudu findone(Map params) {
		return jiexisuduDao.findone(params);
	}

}
