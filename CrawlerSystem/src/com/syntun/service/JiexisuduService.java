package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.Jiexisudu;

public interface JiexisuduService {
	
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