package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PromotionListDao;
import com.syntun.entity.PromotionList;
import com.syntun.service.PromotionListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("promotionListService")
public class PromotionListServiceImpl implements PromotionListService{

    @Resource(name = "promotionListDao")
    private PromotionListDao promotionListDao;

    @Override
    public List<PromotionList> login() {
        return promotionListDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return promotionListDao.getCount(params);
	}

    @Override
	public List<PromotionList> getAllList() {
		return promotionListDao.getAllList();
		
	}
	
    @Override
	public List<PromotionList> getList(HashMap<String, Object> params) {
		return promotionListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return promotionListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		promotionListDao.delRelevantRecord(params);
		promotionListDao.delRecord(params);
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		promotionListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		promotionListDao.delAllRelevantRecord(delList);
		promotionListDao.delAllRecord(delList);
		
	}


}
