package com.syntun.service;

import java.util.List;
import java.util.Map;

import com.syntun.entity.DataRrocessingSql;

public interface DataRrocessingSqlService {
	
    int deleteByPrimaryKey(String sql);

    int insert(DataRrocessingSql record);

    int insertSelective(DataRrocessingSql record);

    DataRrocessingSql selectByPrimaryKey(String sql);

    int updateByPrimaryKeySelective(DataRrocessingSql record);

    int updateByPrimaryKey(DataRrocessingSql record);
    
    List<DataRrocessingSql> find(Map params);
    
    int getTotal(Map params);
}