package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.PromotionRank;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("promotionRankDao")
public interface PromotionRankDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionRank record);

    int insertSelective(PromotionRank record);

    PromotionRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionRank record);

    int updateByPrimaryKey(PromotionRank record);

    List<PromotionRank> selectPromotionRank();
    
    List<PromotionRank> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<PromotionRank> getAllList();
	
    List<PromotionRank> getList(HashMap<String, Object> params);

    int addFiled(HashMap<String, Object> params);
    
    void delFiled(HashMap<String, Object> params);
    
    void delAllFiled(List<String> delList);
    
	void editFiled(HashMap<String, Object> params);
}
