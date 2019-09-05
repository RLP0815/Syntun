package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.DataRrocessingSql;

@Repository("dataRrocessingSqlDao")
public interface DataRrocessingSqlDao {
	
    int deleteByPrimaryKey(String sql);

    int insert(DataRrocessingSql record);

    int insertSelective(DataRrocessingSql record);

    DataRrocessingSql selectByPrimaryKey(String sql);

    int updateByPrimaryKeySelective(DataRrocessingSql record);

    int updateByPrimaryKey(DataRrocessingSql record);
    
    public List<DataRrocessingSql> find(Map params);
    
    public int getTotal(Map params);
}