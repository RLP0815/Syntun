package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.server.ServerClassify;

/**
 * @author wangdong
 */
@Repository("serverClassifyDao")
public interface ServerClassifyDao {

	int insert(ServerClassify o);

	int delete(int id);

	int update(ServerClassify o);

	public List<ServerClassify> findAll();

	public List<ServerClassify> findAllDel(Map map);

	public ServerClassify findone(String id);

	public List<ServerClassify> find(Map params);

	public int getTotal(Map params);
}
