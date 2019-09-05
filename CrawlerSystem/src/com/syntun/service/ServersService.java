package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.Servers;

/**
 * 服务器管理类
 */
public interface ServersService {
	
	public List<Servers> findAll();
	public List<Servers> findAllDel(Map map);
	public int insert(Servers s);
	public int update(Servers s);
	public Servers findone(String id);
}
