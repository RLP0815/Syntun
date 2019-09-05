package com.syntun.service;

import java.util.HashMap;
import java.util.List;
import com.syntun.entity.Role;


/**
 * 
 */
public interface RoleService {
    public List<Role> login();
    
    public List<Role> selectRecord(HashMap<String, Object> params);
    
	public int getCount(HashMap<String, Object> params);
    
    public List<Role> getAllList(HashMap<String, Object> params);
    
	public List<Role> getList(HashMap<String, Object> params);
	
	public int addRecord(HashMap<String, Object> params);

	public void delRecord(HashMap<String, Object> params);

	public void editRecord(HashMap<String, Object> params);

	public void delAllRecord(List<String> delList);
	
	List<Role> selectCateAccount();
	
	Role findone(Integer id);
	
	public int insert(Role record);
	public int delete(Integer id);
	public int update(Role record);

}
