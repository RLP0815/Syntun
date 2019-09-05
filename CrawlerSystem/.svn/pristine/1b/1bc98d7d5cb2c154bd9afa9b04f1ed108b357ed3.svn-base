package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerTmall144PurlListDao;
import com.syntun.entity.CrawlerTmall144PurlList;
import com.syntun.service.CrawlerTmall144PurlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerTmall144PurlListService")
public class CrawlerTmall144PurlListServiceImpl implements CrawlerTmall144PurlListService{

    @Resource(name = "crawlerTmall144PurlListDao")
    private CrawlerTmall144PurlListDao crawlerTmall144PurlListDao;

    @Override
    public List<CrawlerTmall144PurlList> login() {
        return crawlerTmall144PurlListDao.selectCrawlerTmall144PurlList();
    }
    
    @Override
   	public List<CrawlerTmall144PurlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerTmall144PurlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerTmall144PurlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerTmall144PurlList> getAllList(HashMap<String, Object> params) {
		return crawlerTmall144PurlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerTmall144PurlList> getList(HashMap<String, Object> params) {
		return crawlerTmall144PurlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerTmall144PurlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerTmall144PurlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerTmall144PurlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerTmall144PurlListDao.delAllRecord(delList);
		
	}


}
