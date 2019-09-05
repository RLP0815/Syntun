package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.Crawler144PurlList;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("crawler144PurlListDao")
public interface Crawler144PurlListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Crawler144PurlList record);

    int insertSelective(Crawler144PurlList record);

    Crawler144PurlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Crawler144PurlList record);

    int updateByPrimaryKey(Crawler144PurlList record);

    List<Crawler144PurlList> selectCrawler144PurlList();
    
    List<Crawler144PurlList> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<Crawler144PurlList> getAllList(HashMap<String, Object> params);
	
    List<Crawler144PurlList> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
