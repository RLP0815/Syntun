package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syntun.entity.HistoryLog;

/**
 * 系统日志查看历史
 * @author wangdong
 */
@Repository("historyLogDao")
public interface HistoryLogDao {
	
    int insert(HistoryLog o);
    
    int delete(Integer id);
    
    public List<HistoryLog> find(Map params);
    
    public int getTotal(Map params);
}
