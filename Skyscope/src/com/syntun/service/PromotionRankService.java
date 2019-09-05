package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.PromotionRank;


/**
 * 
 */
public interface PromotionRankService {
    public List<PromotionRank> login();
    
    public List<PromotionRank> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<PromotionRank> getAllList();
    
	public List<PromotionRank> getList(HashMap<String, Object> params);
	
	public int addFiled(HashMap<String, Object> params);

	public void delFiled(HashMap<String, Object> params);

	public void editFiled(HashMap<String, Object> params);

	public void delAllFiled(List<String> delList);

}
