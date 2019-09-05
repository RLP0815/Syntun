package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PromotionReplace;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("promotionReplaceDao")
public interface PromotionReplaceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionReplace record);

    int insertSelective(PromotionReplace record);
    
    PromotionReplace selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(PromotionReplace record);

    int updateByPrimaryKey(PromotionReplace record);

    List<PromotionReplace> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<PromotionReplace> getAllList();
	//查询部分记录
    List<PromotionReplace> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
