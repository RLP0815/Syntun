package com.syntun.etl.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertDataSqlServer implements Runnable {
	/**
	 * sql集合
	 */
	private List<String> sqlList = null;

	public InsertDataSqlServer(List<String> sqlList) {
		this.sqlList = new ArrayList<String>(sqlList);
		sqlList.clear();
	}

	/**
	 * 批量插入url地址
	 * 
	 * @param values
	 */
	private void excuteSqlList(List<String> values) {
		StringBuffer sqls = new StringBuffer();
		int num = 0;
		Connection conn = ConnectSqlServer13.getConn();
		for (int i = 0; i < values.size(); i++) {
			String line = values.get(i);
			if(line != null){
				line = line.replace(");", ")");
				if (num == 0) {
					sqls.append(line);
				} else {
					sqls.append(",")
							.append(line.substring(line.indexOf("values(") + "values(".length() - 1, line.length()));
				}
				num++;
				if (num >= 1000) {
					exceteSql(sqls.toString(), conn);
					sqls = null;
					sqls = new StringBuffer();
					num = 0;
				}
			}
			
		}
		exceteSql(sqls.toString(), conn);
		
		ConnectSqlServer13.push(conn);
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	private boolean exceteSql(String sql, Connection conn) {
		boolean status = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			status = ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(sql);
		}
		sql = null;
		return status;
	}

	@Override
	public void run() {
		if (sqlList != null && sqlList.size() > 0) {
			excuteSqlList(sqlList);
		}
	}
}
