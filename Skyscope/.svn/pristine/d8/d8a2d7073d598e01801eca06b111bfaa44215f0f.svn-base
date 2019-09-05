package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.ErrResult;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("errResultDao")
public interface ErrResultDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrResult record);

    int insertSelective(ErrResult record);

    ErrResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrResult record);

    int updateByPrimaryKey(ErrResult record);

    List<ErrResult> selectErrResult();
    
    List<ErrResult> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<ErrResult> getAllList();
	
    List<ErrResult> getList(HashMap<String, Object> params);
    //批量删除
    void delAllRecord();

}
