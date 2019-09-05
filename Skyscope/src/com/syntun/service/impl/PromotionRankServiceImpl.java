package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PromotionRankDao;
import com.syntun.entity.PromotionRank;
import com.syntun.service.PromotionRankService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("promotionRankService")
public class PromotionRankServiceImpl implements PromotionRankService{

    @Resource(name = "promotionRankDao")
    private PromotionRankDao promotionRankDao;

    @Override
    public List<PromotionRank> login() {
        return promotionRankDao.selectPromotionRank();
    }
    
    @Override
   	public List<PromotionRank> selectRecord(HashMap<String, Object> params) {
   		return promotionRankDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return promotionRankDao.getCount(params);
	}
    
    @Override
	public List<PromotionRank> getAllList() {
		return promotionRankDao.getAllList();
		
	}
	
    @Override
	public List<PromotionRank> getList(HashMap<String, Object> params) {
		return promotionRankDao.getList(params);
		
	}

	@Override
	public int addFiled(HashMap<String, Object> params) {
		return promotionRankDao.addFiled(params);
	}

	@Override
	public void delFiled(HashMap<String, Object> params) {
		promotionRankDao.delFiled(params);
		
	}

	@Override
	public void editFiled(HashMap<String, Object> params) {
		promotionRankDao.editFiled(params);
		
	}

	@Override
	public void delAllFiled(List<String> delList) {
		promotionRankDao.delAllFiled(delList);
		
	}


}
