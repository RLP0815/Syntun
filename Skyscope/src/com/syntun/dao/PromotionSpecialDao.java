package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PromotionSpecial;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("promotionSpecialDao")
public interface PromotionSpecialDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionSpecial record);

    int insertSelective(PromotionSpecial record);

    PromotionSpecial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionSpecial record);

    int updateByPrimaryKey(PromotionSpecial record);

    List<PromotionSpecial> selectPromotionSpecial();
    
    List<PromotionSpecial> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<PromotionSpecial> getAllList();
	
    List<PromotionSpecial> getList(HashMap<String, Object> params);

    int addFiled(HashMap<String, Object> params);
    
    void delFiled(HashMap<String, Object> params);
    
    void delAllFiled(List<String> delList);
    
	void editFiled(HashMap<String, Object> params);
}
