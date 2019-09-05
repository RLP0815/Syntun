package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.HistoryLogDao;
import com.syntun.entity.HistoryLog;
import com.syntun.service.HistoryLogService;

@Service("historyLogService")
public class HistoryLogServiceImpl implements HistoryLogService{
	
    @Resource(name = "historyLogDao")
    private HistoryLogDao historyLogDao;

	@Override
	public int insert(HistoryLog o) {
		return historyLogDao.insert(o);
	}

	@Override
	public int delete(Integer id) {
		return historyLogDao.delete(id);
	}

	@Override
	public List<HistoryLog> find(Map params) {
		return historyLogDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return historyLogDao.getTotal(params);
	}

}
