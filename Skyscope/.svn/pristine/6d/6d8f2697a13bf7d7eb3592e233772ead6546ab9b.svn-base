package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PriceCompute;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("priceComputeDao")
public interface PriceComputeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PriceCompute record);

    int insertSelective(PriceCompute record);
    
    PriceCompute selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(PriceCompute record);

    int updateByPrimaryKey(PriceCompute record);

    List<PriceCompute> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<PriceCompute> getAllList();
	//查询部分记录
    List<PriceCompute> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
