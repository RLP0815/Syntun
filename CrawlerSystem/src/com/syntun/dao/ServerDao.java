package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Server;

/**
 * 系统日志
 * @author wangdong
 */
@Repository("serverDao")
public interface ServerDao {
	
    int insert(Server o);
    
    int update(Server o);
    
    public List<Server> findAll(Map map);
    
    public List<Server> findAllDel(Map map);
    
    public Server findone(int id);
}
