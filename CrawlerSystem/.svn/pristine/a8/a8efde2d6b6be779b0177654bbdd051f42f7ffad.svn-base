package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ProductUrl;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("productUrlDao")
public interface ProductUrlDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductUrl record);

    int insertSelective(ProductUrl record);

    ProductUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductUrl record);

    int updateByPrimaryKey(ProductUrl record);

    List<ProductUrl> selectProductUrl();
    
    List<ProductUrl> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ProductUrl> getAllList(HashMap<String, Object> params);
	
    List<ProductUrl> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
