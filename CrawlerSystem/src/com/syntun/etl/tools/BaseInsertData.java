package com.syntun.etl.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.syntun.etl.tools.ConnectSql60;

public class BaseInsertData implements Runnable {
	/**
	 * sql集合
	 */
	private List<String> sqlList = null;

	public BaseInsertData(List<String> sqlList) {
		this.sqlList = new ArrayList<String>(sqlList);
		//System.out.println(sqlList.size());
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
		Connection conn63 = BaseConnectSql.getConn("192.168.0.63:3306","skyscope","wgdata","syntun-000");
		for (int i = 0; i < values.size(); i++) {
			String line = values.get(i);
			line = line.replace(");", ")");
			if (num == 0) {
				sqls.append(line);
			} else {
				sqls.append(",")
						.append(line.substring(line.indexOf("values(") + "values(".length() - 1, line.length()));
			}
			num++;
			if (num >= 2000) {
				exceteSql(sqls.toString(), conn63);
				sqls = null;
				sqls = new StringBuffer();
				num = 0;
			}
		}
		exceteSql(sqls.toString(), conn63);
		
		BaseConnectSql.push(conn63);
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
			System.out.println(sql.substring(0, 1000));
			System.out.println(e);
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
