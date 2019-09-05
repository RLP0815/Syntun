package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.PromotionReplaceDao;
import com.syntun.entity.PromotionReplace;
import com.syntun.service.PromotionReplaceService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("promotionReplaceService")
public class PromotionReplaceServiceImpl implements PromotionReplaceService{

    @Resource(name = "promotionReplaceDao")
    private PromotionReplaceDao promotionReplaceDao;

    @Override
    public List<PromotionReplace> login() {
        return promotionReplaceDao.selectRecord();
    }

	@Override
	public int getCount(HashMap<String, Object> params) {
		return promotionReplaceDao.getCount(params);
	}

    @Override
	public List<PromotionReplace> getAllList() {
		return promotionReplaceDao.getAllList();
		
	}
	
    @Override
	public List<PromotionReplace> getList(HashMap<String, Object> params) {
		return promotionReplaceDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return promotionReplaceDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		promotionReplaceDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		promotionReplaceDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		promotionReplaceDao.delAllRecord(delList);
		
	}


}
