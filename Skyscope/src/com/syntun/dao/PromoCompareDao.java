package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PromoCompare;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("promoCompareDao")
public interface PromoCompareDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PromoCompare record);

    int insertSelective(PromoCompare record);

    PromoCompare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromoCompare record);

    int updateByPrimaryKey(PromoCompare record);

    List<PromoCompare> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<PromoCompare> getAllList();
	//查询部分记录
    List<PromoCompare> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
	//删除含有此促销的非满减价格计算记录
    void delPriceRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
