package com.syntun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.syntun.controller.systemlog.OperatorLog;

/**
 * 系统日志
 * @author wangdong
 */
@Repository("systemLogDao")
public interface SystemLogDao {
	
    int insert(OperatorLog o);
    
    public List<OperatorLog> find(Map params);
    
    public int getTotal(Map params);
}
