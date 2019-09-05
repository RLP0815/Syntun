package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PromotionSpecialDao;
import com.syntun.entity.PromotionSpecial;
import com.syntun.service.PromotionSpecialService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("promotionSpecialService")
public class PromotionSpecialServiceImpl implements PromotionSpecialService{

    @Resource(name = "promotionSpecialDao")
    private PromotionSpecialDao promotionSpecialDao;

    @Override
    public List<PromotionSpecial> login() {
        return promotionSpecialDao.selectPromotionSpecial();
    }
    
    @Override
   	public List<PromotionSpecial> selectRecord(HashMap<String, Object> params) {
   		return promotionSpecialDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return promotionSpecialDao.getCount(params);
	}
    
    @Override
	public List<PromotionSpecial> getAllList() {
		return promotionSpecialDao.getAllList();
		
	}
	
    @Override
	public List<PromotionSpecial> getList(HashMap<String, Object> params) {
		return promotionSpecialDao.getList(params);
		
	}

	@Override
	public int addFiled(HashMap<String, Object> params) {
		return promotionSpecialDao.addFiled(params);
	}

	@Override
	public void delFiled(HashMap<String, Object> params) {
		promotionSpecialDao.delFiled(params);
		
	}

	@Override
	public void editFiled(HashMap<String, Object> params) {
		promotionSpecialDao.editFiled(params);
		
	}

	@Override
	public void delAllFiled(List<String> delList) {
		promotionSpecialDao.delAllFiled(delList);
		
	}


}
