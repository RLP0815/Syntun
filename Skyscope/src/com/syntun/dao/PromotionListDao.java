package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PromotionList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("promotionListDao")
public interface PromotionListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionList record);

    int insertSelective(PromotionList record);

    PromotionList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionList record);

    int updateByPrimaryKey(PromotionList record);

    List<PromotionList> selectRecord();
    
    int getCount(HashMap<String, Object> params);
    //查询全部记录
    List<PromotionList> getAllList();
	//查询部分记录
    List<PromotionList> getList(HashMap<String, Object> params);
    //添加记录
    int addRecord(HashMap<String, Object> params);
    //删除记录
    void delRecord(HashMap<String, Object> params);
	//删除相关记录
    void delRelevantRecord(HashMap<String, Object> params);
	//批量删除相关记录
    void delAllRelevantRecord(List<String> delList);
    //批量删除
    void delAllRecord(List<String> delList);
    //编辑记录
	void editRecord(HashMap<String, Object> params);
}
