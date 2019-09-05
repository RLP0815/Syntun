package com.syntun.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syntun.dao.ServersDao;
import com.syntun.entity.Servers;
import com.syntun.service.ServersService;

/**
 * 
 */
@Service("serversService")
public class ServersServiceImpl implements ServersService{

    @Resource(name = "serversDao")
    private ServersDao serversDao;

	@Override
	public List<Servers> findAll() {
		return serversDao.findAll();
	}

	@Override
	public List<Servers> findAllDel(Map map) {
		return serversDao.findAllDel(map);
	}

	@Override
	public int insert(Servers s) {
		return serversDao.insert(s);
	}

	@Override
	public int update(Servers s) {
		return serversDao.update(s);
	}

	@Override
	public Servers findone(String id) {
		return serversDao.findone(id);
	}
    
    

}
