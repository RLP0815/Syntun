package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Jiexisudu;

@Repository("jiexisuduDao")
public interface JiexisuduDao {
	
    int deleteByPrimaryKey(String sql);

    int insert(Jiexisudu record);

    int insertSelective(Jiexisudu record);

    Jiexisudu selectByPrimaryKey(String sql);

    int updateByPrimaryKeySelective(Jiexisudu record);

    int updateByPrimaryKey(Jiexisudu record);
    
    public List<Jiexisudu> find(Map params);
    
    public int getTotal(Map params);
    
    public Jiexisudu findone(Map params);
}