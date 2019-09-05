package com.syntun.service;

import java.util.HashMap;
import java.util.List;

import com.syntun.entity.PromotionReplace;

/**
 * 
 */
public interface PromotionReplaceService {
    public List<PromotionReplace> login();
    
	public int getCount(HashMap<String, Object> params);
	//查询全部记录
    public List<PromotionReplace> getAllList();
	//查询部分记录
	public List<PromotionReplace> getList(HashMap<String, Object> params);
	//添加记录
	public int addRecord(HashMap<String, Object> params);
	//删除记录
	public void delRecord(HashMap<String, Object> params);
	//编辑记录
	public void editRecord(HashMap<String, Object> params);
	//批量删除
	public void delAllRecord(List<String> delList);
}
