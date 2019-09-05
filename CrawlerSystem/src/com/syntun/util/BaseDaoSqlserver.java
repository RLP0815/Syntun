package com.syntun.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;

public class BaseDaoSqlserver {

	public static void insertProductPlatformList(List<HashMap<String, String>> dateList) {
		String tableName = "PRODUCT_PLATFORM_LIST";
		List<String> field = getField(tableName);
		
		String tableNameStatus = "PRODUCT_PLATFORM_LIST_STATUS";
		List<String> fieldStatus = getField(tableNameStatus);
		Connection conn = ConnectSqlServer.getConn();
		try {
			Statement pst = conn.createStatement();
			for (HashMap<String, String> m : dateList) {
				m.put("STATUS", "99");
				m.put("CATEGORY_ID", "498");
				m.put("GET_DATE", m.get("get_date"));
				String sql = getSql(tableName, field, m);
				String sqlStatus = getSql(tableNameStatus, fieldStatus, m);
				try {
					pst.execute(sql);
				} catch (Exception e) {
					System.out.println("重复数据");
				}
				try {
					pst.execute(sqlStatus);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("带状态表的重复数据");
				}
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getSql(String tableName, List<String> filedList, HashMap<String, String> dataMap) {
		StringBuffer sb = new StringBuffer("INSERT INTO " + tableName + "(");
		StringBuffer startVal = new StringBuffer(" values");
		boolean isTrue = true;
		for (int j = 0; j < filedList.size(); j++) {
			String filed = filedList.get(j);
			if (j == 0) {
				sb.append(filed);
			} else {
				sb.append("," + filed);
			}
			String val = null;
			if (dataMap.get(filed) != null) {
				isTrue = false;
				val = StringEscapeUtils.escapeSql(dataMap.get(filed));
			}else{
				val="0";
			}
			if (j == 0) {
				startVal.append("(").append("'" + val + "'");
			} else {
				startVal.append(",").append("'" + val + "'");
			}
		}
//		if (isTrue) {
//			return null;
//		}
		startVal.append(");");
		sb.append(")").append(startVal.toString());
		return sb.toString();
	}

	/**
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField(String tableName) {
		HashMap<String, HashMap<String, String>> map = getTable(tableName);
		List<String> filedList = new ArrayList<String>();
		for (String key : map.keySet()) {
			HashMap<String, String> map1 = map.get(key);
			for (String k1 : map1.keySet()) {
				System.out.println(key + ":" + k1 + ":" + map1.get(k1));
				filedList.add(key);
			}
		}
		return filedList;
	}

	/**
	 * 根据传入的table名称获取该表在SQLServer中的列名和其数据长度
	 * 
	 * @param table
	 * @return 返回tableName<colName，length>
	 */
	public static HashMap<String, HashMap<String, String>> getTable(String tableName) {

		HashMap<String, HashMap<String, String>> tableMap = new HashMap<String, HashMap<String, String>>();
		try {
			Connection conn = ConnectSqlServer.getConn();
			PreparedStatement pstm = conn.prepareStatement(
					"select t.name as tablename,c.name as columnname,ty.name as typename,c.max_length as typelength from"
							+ " sys.columns c inner join sys.tables t on t.object_id=c.object_id inner join sys.types ty "
							+ "on ty.system_type_id=c.system_type_id where t.name='" + tableName
							+ "' order by t.name,c.column_id");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HashMap<String, String> colMap = null;
				if (tableMap.containsKey(rs.getString(1))) {
					colMap = new HashMap<String, String>(tableMap.get(rs.getString(1)));
				} else {
					colMap = new HashMap<String, String>();
				}
				colMap.put(rs.getString(3).replace("nvarchar", "varchar").replace("sysname", "varchar"),
						rs.getString(4));
				tableMap.put(rs.getString(2), colMap);
			}
			ConnectSqlServer.push(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableMap;
	}

}
