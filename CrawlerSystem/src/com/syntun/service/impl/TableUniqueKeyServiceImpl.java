package com.syntun.service.impl;

import org.springframework.stereotype.Service;

import com.syntun.dao.TableUniqueKeyDao;
import com.syntun.entity.TableUniqueKey;
import com.syntun.service.TableUniqueKeyService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Service("tableUniqueKeyService")
public class TableUniqueKeyServiceImpl implements TableUniqueKeyService{

    @Resource(name = "tableUniqueKeyDao")
    private TableUniqueKeyDao tableUniqueKeyDao;

    @Override
    public List<TableUniqueKey> login() {
        return tableUniqueKeyDao.selectTableUniqueKey();
    }
    
    @Override
   	public List<TableUniqueKey> selectRecord(HashMap<String, Object> params) {
   		return tableUniqueKeyDao.selectRecord(params);
   		
   	}

	@Override
	public int getCount(HashMap<String, Object> params) {
		return tableUniqueKeyDao.getCount(params);
	}
    
    @Override
	public List<TableUniqueKey> getAllList(HashMap<String, Object> params) {
		return tableUniqueKeyDao.getAllList(params);
		
	}
	
    @Override
	public List<TableUniqueKey> getList(HashMap<String, Object> params) {
		return tableUniqueKeyDao.getList(params);
		
	}

	@Override
	public int addRecord(HashMap<String, Object> params) {
		return tableUniqueKeyDao.addRecord(params);
	}

	@Override
	public void delRecord(HashMap<String, Object> params) {
		tableUniqueKeyDao.delRecord(params);
		
	}

	@Override
	public void editRecord(HashMap<String, Object> params) {
		tableUniqueKeyDao.editRecord(params);
		
	}

	@Override
	public void delAllRecord(List<String> delList) {
		tableUniqueKeyDao.delAllRecord(delList);
		
	}

	@Override
	public List<Map<String, String>> getDataBase() {
		return tableUniqueKeyDao.getDataBase();
	}

	@Override
	public List<Map<String, String>> getTableName() {
		return tableUniqueKeyDao.getTableName();
	}


}
