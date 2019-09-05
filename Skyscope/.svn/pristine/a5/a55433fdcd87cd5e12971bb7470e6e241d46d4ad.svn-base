package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ProductPlatformList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("productPlatformListDao")
public interface ProductPlatformListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPlatformList record);

    int insertSelective(ProductPlatformList record);
    
    ProductPlatformList selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(ProductPlatformList record);

    int updateByPrimaryKey(ProductPlatformList record);

    List<ProductPlatformList> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<ProductPlatformList> getAllList();
	//查询部分记录
    List<ProductPlatformList> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
