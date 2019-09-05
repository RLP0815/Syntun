package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.CrawlerTmall144PurlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawlerTmall144PurlListDao")
public interface CrawlerTmall144PurlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerTmall144PurlList record);

    int insertSelective(CrawlerTmall144PurlList record);

    CrawlerTmall144PurlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlerTmall144PurlList record);

    int updateByPrimaryKey(CrawlerTmall144PurlList record);

    List<CrawlerTmall144PurlList> selectCrawlerTmall144PurlList();
    
    List<CrawlerTmall144PurlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<CrawlerTmall144PurlList> getAllList(HashMap<String, Object> params);
	
    List<CrawlerTmall144PurlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
