package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.CrawlerCommentJD144PurlListDao;
import com.syntun.entity.CrawlerCommentJD144PurlList;
import com.syntun.service.CrawlerCommentJD144PurlListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("crawlerCommentJD144PurlListService")
public class CrawlerCommentJD144PurlListServiceImpl implements CrawlerCommentJD144PurlListService{

    @Resource(name = "crawlerCommentJD144PurlListDao")
    private CrawlerCommentJD144PurlListDao crawlerCommentJD144PurlListDao;

    @Override
    public List<CrawlerCommentJD144PurlList> login() {
        return crawlerCommentJD144PurlListDao.selectCrawlerCommentJD144PurlList();
    }
    
    @Override
   	public List<CrawlerCommentJD144PurlList> selectRecord(HashMap<String, Object> params) {
   		return crawlerCommentJD144PurlListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return crawlerCommentJD144PurlListDao.getCount(params);
	}
    
    @Override
	public List<CrawlerCommentJD144PurlList> getAllList(HashMap<String, Object> params) {
		return crawlerCommentJD144PurlListDao.getAllList(params);
		
	}
	
    @Override
	public List<CrawlerCommentJD144PurlList> getList(HashMap<String, Object> params) {
		return crawlerCommentJD144PurlListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return crawlerCommentJD144PurlListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		crawlerCommentJD144PurlListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		crawlerCommentJD144PurlListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		crawlerCommentJD144PurlListDao.delAllRecord(delList);
		
	}


}
