package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.Crawler144PatternListJDDao;
import com.syntun.entity.Crawler144PatternListJD;
import com.syntun.service.Crawler144PatternListJDService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawler144PatternListJDService")
public class Crawler144PatternListJDServiceImpl implements Crawler144PatternListJDService{

    @Resource(name = "crawler144PatternListJDDao")
    private Crawler144PatternListJDDao crawler144PatternListJDDao;

    @Override
    public List<Crawler144PatternListJD> login() {
        return crawler144PatternListJDDao.selectCrawler144PatternListJD();
    }
    
    @Override
   	public List<Crawler144PatternListJD> selectRecord(HashMap<String, Object> params) {
   		return crawler144PatternListJDDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawler144PatternListJDDao.getCount(params);
	}
    
    @Override
	public List<Crawler144PatternListJD> getAllList(HashMap<String, Object> params) {
		return crawler144PatternListJDDao.getAllList(params);
		
	}
	
    @Override
	public List<Crawler144PatternListJD> getList(HashMap<String, Object> params) {
		return crawler144PatternListJDDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawler144PatternListJDDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawler144PatternListJDDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawler144PatternListJDDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawler144PatternListJDDao.delAllRecord(delList);
		
	}


}
