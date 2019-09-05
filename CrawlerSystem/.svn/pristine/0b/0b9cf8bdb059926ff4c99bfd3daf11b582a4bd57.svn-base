package com.syntun.dao;

import org.springframework.stereotype.Repository;

import com.syntun.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Repository("userDao")
public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();
    
    int getCount(HashMap<String, Object> params);
	
    List<User> getList(HashMap<String, Object> params);
}
