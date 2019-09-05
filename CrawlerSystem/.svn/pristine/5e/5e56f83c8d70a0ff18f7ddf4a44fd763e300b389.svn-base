package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Servers;

/**
 * @author wangdong
 */
@Repository("serversDao")
public interface ServersDao {
	
    int insert(Servers o);
    
    int update(Servers o);
    
    public List<Servers> findAll();
    
    public List<Servers> findAllDel(Map map);
    
    public Servers findone(String id);
}
