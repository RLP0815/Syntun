package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PlatformList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("platformListDao")
public interface PlatformListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatformList record);

    int insertSelective(PlatformList record);
    
    PlatformList selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(PlatformList record);

    int updateByPrimaryKey(PlatformList record);

    List<PlatformList> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<PlatformList> getAllList();
	//查询部分记录
    List<PlatformList> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
    //删除与平台相关的促销表记录
    void delPromoRecord(HashMap<String, Object> params);
    //删除与平台相关的非满减价格计算表记录
    void delPriceRecord(HashMap<String, Object> params);
	//批量删除与平台相关的促销表记录
    void delAllPromoRecord(List<String> delList);
	//批量删除与平台相关的非满减价格计算表记录
    void delAllPriceRecord(List<String> delList);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
