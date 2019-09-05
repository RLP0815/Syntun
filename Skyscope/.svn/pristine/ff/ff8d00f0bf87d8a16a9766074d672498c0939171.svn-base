package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.EditRemark;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("editRemarkDao")
public interface EditRemarkDao {
    int deleteByPrimaryKey(Integer id);

    int insert(EditRemark record);

    int insertSelective(EditRemark record);

    EditRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EditRemark record);

    int updateByPrimaryKey(EditRemark record);

    List<EditRemark> selectEditRemark();
    
    List<EditRemark> selectRecord(HashMap<String, Object> params);
    
    int getCount(HashMap<String, Object> params);
    
    List<EditRemark> getAllList(HashMap<String, Object> params);
	
    List<EditRemark> getList(HashMap<String, Object> params);

    int addRecord(HashMap<String, Object> params);
    
    void delRecord(HashMap<String, Object> params);
    
    void delAllRecord(List<String> delList);
    
	void editRecord(HashMap<String, Object> params);
	
}
