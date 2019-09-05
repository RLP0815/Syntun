package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.Server;

/**
 * 服务器管理类
 */
public interface ServerService {
	
	public List<Server> findAll(Map map);
	public List<Server> findAllDel(Map map);
	public int insert(Server s);
	public int update(Server s);
	public Server findone(int id);
}
