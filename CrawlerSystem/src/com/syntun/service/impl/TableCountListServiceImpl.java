package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.TableCountListDao;
import com.syntun.entity.TableCountList;
import com.syntun.service.TableCountListService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Service("tableCountListService")
public class TableCountListServiceImpl implements TableCountListService{

    @Resource(name = "tableCountListDao")
    private TableCountListDao tableCountListDao;

    @Override
    public List<TableCountList> login() {
        return tableCountListDao.selectTableCountList();
    }
    
    @Override
   	public List<TableCountList> selectRecord(HashMap<String, Object> params) {
   		return tableCountListDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return tableCountListDao.getCount(params);
	}
    
    @Override
	public List<TableCountList> getAllList(HashMap<String, Object> params) {
		return tableCountListDao.getAllList(params);
		
	}
	
    @Override
	public List<TableCountList> getList(HashMap<String, Object> params) {
		return tableCountListDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return tableCountListDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		tableCountListDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		tableCountListDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		tableCountListDao.delAllRecord(delList);
		
	}

	@Override
	public List<TableCountList> getEmailList(HashMap<String, Object> params) {
		return tableCountListDao.getEmailList(params);
	}


}