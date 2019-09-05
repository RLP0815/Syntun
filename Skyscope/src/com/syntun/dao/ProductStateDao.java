package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ProductState;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("productStateDao")
public interface ProductStateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductState record);

    int insertSelective(ProductState record);

    ProductState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductState record);

    int updateByPrimaryKey(ProductState record);

    List<ProductState> selectProductState();
    
    List<ProductState> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ProductState> getAllList();
	
    List<ProductState> getList(HashMap<String, Object> params);

    int addFiled(HashMap<String, Object> params);
    
    void delFiled(HashMap<String, Object> params);
    
    void delAllFiled(List<String> delList);
    
	void editFiled(HashMap<String, Object> params);
}
