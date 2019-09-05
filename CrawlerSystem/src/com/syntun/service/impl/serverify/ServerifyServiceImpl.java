package com.syntun.service.impl.serverify;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.syntun.dao.ServerClassifyDao;
import com.syntun.entity.server.ServerClassify;
import com.syntun.service.serviceClassify.ServerClassifyService;

/**
 * 服务器分类service
 */
@Service("serverClassifyService")
public class ServerifyServiceImpl implements ServerClassifyService{

    @Resource(name = "serverClassifyDao")
    private ServerClassifyDao serverClassifyDao;

	@Override
	public int insert(ServerClassify o) {
		return serverClassifyDao.insert(o);
	}

	@Override
	public int delete(int id) {
		return serverClassifyDao.delete(id);
	}

	@Override
	public int update(ServerClassify o) {
		return serverClassifyDao.update(o);
	}

	@Override
	public List<ServerClassify> findAll() {
		return serverClassifyDao.findAll();
	}

	@Override
	public List<ServerClassify> findAllDel(Map map) {
		return null;
	}

	@Override
	public ServerClassify findone(String id) {
		return serverClassifyDao.findone(id);
	}

	@Override
	public List<ServerClassify> find(Map params) {
		return serverClassifyDao.find(params);
	}

	@Override
	public int getTotal(Map params) {
		return serverClassifyDao.getTotal(params);
	}

}
