package com.syntun.etl.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;



public class BaseDao {

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, String> getUsersMap(String tableName, Connection conn) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				mapData.put(rs.getString("user_id"), rs.getString("User_name"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	
	/**
	 * 查询一个表所有数据sort_product_mohu
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getSTbaleList(String dateStr) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql124.getConn();
		try {
			String sql = "select * from sort_product_mohu where 1=1 ";
			if (dateStr != null) {
				sql += " and get_date ='" + dateStr + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("product_id"), rs.getString("product_name"));
			}
			pst.close();
			ConnectSql124.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询一个表所有数据sort_product_mohu
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getSTbaleList1(String dateStr) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql124.getConn();
		try {
			String sql = "select * from wgdata_kaola.sort_product_mohu where 1=1 ";
			if (dateStr != null) {
				sql += " and get_date ='" + dateStr + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("product_id"), rs.getString("product_name"));
			}
			pst.close();
			ConnectSql124.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询一个表所有数据
	 * 
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getFTbaleList() {
		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			String sql = "select * from product_filter";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("product_id")+"001/"+rs.getString("product_name")+"001/"+rs.getString("id")
						, rs.getString("filter1")+"001/"+rs.getString("filter2")+"001/"+rs.getString("filter3"));
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getPromotionReplace(String tableName, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("old_type"), rs.getString("new_type"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getProductStatus(String tableName, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("platform_id")+"\001"+rs.getString("error_connect"), rs.getString("product_status"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询PRODUCT_PLATFORM_LIST_STATUS所有数据
	 * 
	 * @return 包含所有数据的HashMap<String,String> 集合，key：replace_str！！val：end_str
	 */
	public static List<HashMap<String, String>> getProductList(String dateStr, String platformId) {
		//获取表格所有字段
		List<String> productList = getField("PRODUCT_PLATFORM_LIST_STATUS");
		
		Connection conn = ConnectSql.getConn();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			String sql = "SELECT * FROM PRODUCT_PLATFORM_LIST_STATUS WHERE 1=1 ";
			if (dateStr != null) {
				sql += " and get_date ='" + dateStr + "' ";
			}
			if (platformId != null) {
				sql += " and platform_id ='" + platformId + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for(String pl : productList) {
					map.put(pl, rs.getString(pl));
				}
				list.add(map);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 如果传进来的表已经存在不处理，如果不存在复制表syntun_etl.etl_result
	 * 
	 */
	public static void createEtlResult(String tableName) {
		Connection conn = ConnectSql.getConn();
		String name = tableName.split("\\.")[1];

		String sql = "CREATE TABLE " + tableName
				+ " ( KEY `product_id`( `product_id` ), KEY `get_date`( `get_date` ))ENGINE=MYISAM COLLATE = utf8_general_ci COMMENT = '' SELECT * FROM `syntun_etl`.`etl_result` WHERE 1 = 0; ";
		String checkSql = "SELECT `TABLE_NAME` FROM `INFORMATION_SCHEMA`.`TABLES` WHERE `TABLE_SCHEMA`='syntun_etl' AND `TABLE_NAME`='"
				+ name + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(checkSql);
			if (!ps.executeQuery().next()) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.execute();
				pst.close();
			}
			ps.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询数据库中table数量
	 * 
	 * @param database
	 * @return
	 */
	public static List<String> getTables(String database) {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("SHOW FULL TABLES FROM " + database);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tableName = database + "." + rs.getString(1);
				list.add(tableName);
			}
			rs.close();
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询数据库中table数量
	 * 
	 * @param database
	 * @return
	 */
	public static boolean updateTable(String tableName, HashMap<String, String> setMap,
			HashMap<String, String> filterMap) {
		Connection conn = ConnectSql.getConn();
		boolean b = false;

		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(tableName).append(" SET ");
		String dengyu = "=";
		for (String key : setMap.keySet()) {
			sb.append(key).append(dengyu).append(setMap.get(key));
		}
		sb.append(" where ");
		for (String key : filterMap.keySet()) {
			sb.append(key).append(dengyu).append("'" + filterMap.get(key) + "'");
		}
		// System.out.println(sb.toString());
		try {
			PreparedStatement pst = conn.prepareStatement(sb.toString());
			b = pst.execute();
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 批量执行sql语句
	 * 
	 * @param sqlStrList
	 * @return
	 */
	public static int getTablesSize(String tablesName) {
		Connection conn = ConnectSql.getConn();
		int num = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(1) total FROM " + tablesName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt("total");
			}
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 批量执行sql语句
	 * 
	 * @param sqlStrList
	 * @return
	 */
	public static int[] insertBySqlStr(List<String> sqlStrList) {
		Connection conn = ConnectSql.getConn();
		try {
			Statement st = conn.createStatement();
			for (String sqlStr : sqlStrList) {
				st.addBatch(sqlStr);
			}
			int[] status = st.executeBatch();
			st.close();
			ConnectSql.push(conn);
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int[] status = {};
		return status;
	}

	/**
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField(String tableName) {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSql60.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("SHOW FULL FIELDS FROM " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Field"));
			}
			rs.close();
			pst.close();
			ConnectSql60.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField(String tableName, Connection conn) {
		List<String> list = new ArrayList<String>();
		try {
			System.out.println(tableName);
			PreparedStatement pst = conn.prepareStatement("SHOW FULL FIELDS FROM " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString("Field"));
				list.add(rs.getString("Field").toLowerCase());
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField138(String tableName) {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSql138.getConn();
		try {
			System.out.println(tableName);
			PreparedStatement pst = conn.prepareStatement("SHOW FULL FIELDS FROM " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString("Field"));
				list.add(rs.getString("Field").toLowerCase());
			}
			rs.close();
			pst.close();
			ConnectSql138.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 查询表中的所有字段
	 * 原样查询，没有小写化
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getFieldForm(String tableName, Connection conn) {
		List<String> list = new ArrayList<String>();
		try {
			System.out.println(tableName);
			PreparedStatement pst = conn.prepareStatement("SHOW FULL FIELDS FROM " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString("Field"));
				list.add(rs.getString("Field"));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getFieldLowerCase(String tableName, Connection conn) {
		List<String> list = new ArrayList<String>();
		try {
			System.out.println(tableName);
			PreparedStatement pst = conn.prepareStatement("SHOW FULL FIELDS FROM " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString("Field").toLowerCase());
				list.add(rs.getString("Field").toLowerCase());
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<String> getTbaleList(String tableName, String valStr) {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(valStr));
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static String getStr(String tableName, String valStr) {
		Connection conn = ConnectSql.getConn();
		StringBuffer sb = new StringBuffer("'0'");
		try {
			PreparedStatement pst = conn.prepareStatement("select DISTINCT(" + valStr + ") from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				sb.append(",'").append(rs.getString(valStr)).append("'");
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static String getStr(String tableName, String valStr, Connection conn) {
		StringBuffer sb = new StringBuffer("'0'");
		try {
			PreparedStatement pst = conn.prepareStatement("select DISTINCT(" + valStr + ") from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				sb.append(",'").append(rs.getString(valStr)).append("'");
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList(String tableName, String keyStr, String value) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(keyStr), rs.getString(value));
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, List<String>> getTbaleListMap(String tableName, String keyStr, String value) {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				List<String> l = new ArrayList<String>();
				if (map.containsKey(rs.getString(keyStr))) {
					l = new ArrayList<>(map.get(rs.getString(keyStr)));
				}
				l.add(rs.getString(value));
				map.put(rs.getString(keyStr), l);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * 查询苏宁的shop_info 表
	 */
	public static HashMap<String, String> getSuningShopInfo() {
		String sqlConStr = "jdbc:mysql://192.168.0.51/syntun_etl?user=wgdata&password=syntun-000&seUnicode=true&characterEncoding=UTF8&autoReconnect=true&failOverReadOnly=false&maxReconnects=100";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(sqlConStr);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "select company_name,shop_name,shop_code from shop_info_sean";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals("")) {
					map.put(rs.getString(2), rs.getString(3));
				} else {
					map.put(rs.getString(2), rs.getString(3));
					map.put(rs.getString(1), rs.getString(3));
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询苏宁shop id
	 */
	public static HashMap<String, String> getShopMap() {
		Connection conn = ConnectSql.getConn();
		String sql = "select shop_type,shop_id from shop_list where platform_id in(3,10)  and shop_type is not null";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSql.push(conn);
		}
		return map;
	}

	/**
	 * 
	 */
	public static HashMap<String, String> getProductPlatformList() {
		Connection conn = ConnectSql.getConn();
		String sql = "select  platform_id,operation_product_id,product_id,shop_id from PRODUCT_PLATFORM_LIST";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("platform_id").equals("3") || rs.getString("platform_id").equals("10")) {
					map.put(rs.getString("platform_id") + "/" + rs.getString("shop_id") + "/"
							+ rs.getString("operation_product_id"), rs.getString("product_id"));
				}
				map.put(rs.getString("platform_id") + "/" + rs.getString("operation_product_id"),
						rs.getString("product_id") + "/" + rs.getString("shop_id"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSql.push(conn);
		}
		return map;
	}

	/**
	 * 
	 */
	public static HashMap<String, String> getJdProductPlatformList() {
		Connection conn = ConnectSql.getConn();
		String sql = "select  platform_id,operation_product_id,product_id,shop_id from PRODUCT_PLATFORM_LIST where platform_id='1'";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("platform_id").equals("3") || rs.getString("platform_id").equals("10")) {
					map.put(rs.getString("platform_id") + "/" + rs.getString("shop_id") + "/"
							+ rs.getString("operation_product_id"), rs.getString("product_id"));
				}
				map.put(rs.getString("platform_id") + "/" + rs.getString("operation_product_id"),
						rs.getString("product_id") + "/" + rs.getString("shop_id"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSql.push(conn);
		}
		return map;
	}

	/**
	 * 
	 */
	public static HashMap<String, HashMap<String, String>> getPromotionCompute() {
		Connection conn = ConnectSql.getConn();
		String sql = "select platform_id, promotion_type_name, promotion_price, price from price_promotion_compute";
		PreparedStatement ps;
		HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> m = new HashMap<String, String>();
				m.put("promotion_price", rs.getString("promotion_price"));
				m.put("price", rs.getString("price"));
				map.put(rs.getString("platform_id") + "/" + rs.getString("promotion_type_name"), m);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSql.push(conn);
		}
		return map;
	}

	/**
	 * 查询new_MJ_promotion_replace所有数据
	 * 
	 * @return 包含所有数据的HashMap<String,String> 集合，key：replace_str！！val：end_str
	 */
	public static List<HashMap<String, String>> getPromotionReplace() {
		Connection conn = ConnectSql.getConn();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM new_MJ_promotion_replace ORDER BY priority");
			System.out.println("SELECT * FROM new_MJ_promotion_replace ORDER BY priority");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(rs.getString("replace_str"), rs.getString("end_str"));
				list.add(map);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList(String tableName, String a1, String a2, String b1, String b2,
			HashMap<String, String> filter) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			String sql = "select * from " + tableName + " where 1=1 ";
			if (filter != null) {
				for (String key : filter.keySet()) {
					sql += " and " + key + "='" + filter.get(key) + "' ";
				}
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(a1) + rs.getString(a2), rs.getString(b1) + "," + rs.getString(b2));
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList(String tableName, String a1, String a2, String b1, String b2,
			String sql) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String key = rs.getString(a1) + "\001" + rs.getString(a2);
				if (map.containsKey(key)) {
					continue;
				}
				map.put(key, rs.getString(b1) + "\001" + rs.getString(b2));
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList(String tableName, String a1, String a2, String b1, String b2,
			String sql, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String key = rs.getString(a1) + "\001" + rs.getString(a2);
				if (map.containsKey(key)) {
					continue;
				}
				map.put(key, rs.getString(b1) + "\001" + rs.getString(b2));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList(String tableName, String key1, String key2, String val1) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql60.getConn();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(key1) + "\001" + rs.getString(key2), rs.getString(val1));
			}
			pst.close();
			ConnectSql60.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getListPlat(String tableName, String key,
			String val, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(key), rs.getString(val));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getListSplit(String tableName, String key1, String key2, String val1) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(key1) + ":" + rs.getString(key2), rs.getString(val1));
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<HashMap<String, String>> getTbaleList(String tableName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql60.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						maps.put(key, rs.getString(key));
					}
				}
				list.add(maps);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		/*List<List<HashMap<String, String>>> li = getMJPatternList();
		
		for(List<HashMap<String, String>> lm:li){
			System.out.println("~~~~~~~~~~~~~~~");
			for(HashMap<String, String> m :lm){
				try {
					System.out.println(new String(Base64.decode(m.get("pattern_str"))));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("~~~~~~~~~~~~~~~");

		}
		*/
		
	}
	
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<List<HashMap<String, String>>> getMJPatternList() {
		String tableName = "new_MJ_pattern_list";
		List<List<HashMap<String, String>>> list = new ArrayList<List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT * FROM new_MJ_pattern_list ORDER BY priority,pattern_type");
			System.out.println("SELECT * FROM new_MJ_pattern_list ORDER BY priority,pattern_type");
			ResultSet rs = pst.executeQuery();
			List<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						maps.put(key, rs.getString(key));
					}
				}
				if (list1.size() != 0 && rs.getInt("pattern_type") == 1) {
					list.add(list1);
					list1 = new ArrayList<HashMap<String, String>>();
				}
				list1.add(maps);
			}
			if (list1.size() != 0) {
				list.add(list1);
				list1 = new ArrayList<HashMap<String, String>>();
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	

	public static String getStr(String str) {
		if (str.equals("*")) {
			return "无";
		} else {
			return str;
		}
	}

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<List<HashMap<String, String>>> getPromotionPatternList() {
		String tableName = "new_promotion_type_pattern";
		List<List<HashMap<String, String>>> list = new ArrayList<List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT * FROM " + tableName + " ORDER BY priority,pattern_type");
			System.out.println("SELECT * FROM " + tableName + " ORDER BY priority,pattern_type");
			ResultSet rs = pst.executeQuery();
			List<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						maps.put(key, rs.getString(key));
					}
				}
				if (list1.size() != 0 && rs.getInt("pattern_type") == 1) {
					list.add(list1);
					list1 = new ArrayList<HashMap<String, String>>();
				}
				list1.add(maps);
			}
			if (list1.size() != 0) {
				list.add(list1);
				list1 = new ArrayList<HashMap<String, String>>();
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<HashMap<String, String>> getAllTbaleList(String tableName, HashMap<String, String> filter) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql60.getConn();
		try {
			String sql = "select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr, rs.getString(keyStr));
					}
				}
				list.add(maps);
			}
			pst.close();
			ConnectSql60.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<HashMap<String, String>> getAllTbaleList(String tableName, HashMap<String, String> filter,
			Connection conn) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = "select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr, rs.getString(keyStr));
					}
				}
				list.add(maps);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getAllTbalePriceList(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getFieldForm(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			//60.skyscope.promotion_shouji--（抽取时仅抽取sku_id中包含operation_product_id的数据）
			if(tableName.equals("skyscope.promotion_shouji")){
				sql += " and sku_id LIKE CONCAT('%',operation_product_id,'%')";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)).equals("def")?"0":rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				List<HashMap<String, String>> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList<HashMap<String, String>>();
				}
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr, rs.getString(keyStr).equals("def")?"0":rs.getString(keyStr));
					}
				}
				maps.put("table_name", tableName);
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, String> getPromotionSuningBoxi(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		//List<String> fieldList = getFieldForm(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				mapData.put(key, rs.getString("promotion_about_info"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	/**
	 * 查询一个基础表所有数据
	 * 
	 * @param tableName
	 * @return 
	 */
	public static HashMap<String, HashMap<String, String>> getProductName(String tableName,
			List<String> keyList, String dayStr, Connection conn) {
		HashMap<String, HashMap<String, String>> mapData = new HashMap<String, HashMap<String, String>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = "select * from " + tableName + " where get_date = '"+ dayStr +"' ORDER BY id DESC";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)).equals("def")?"0":rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					try {
						if (rs.getString(keyStr) != null) {
							maps.put(keyStr, rs.getString(keyStr).equals("def")?"0":rs.getString(keyStr));
						}
					} catch (Exception e) {
						if(keyStr.equals("get_data_time")){
							maps.put(keyStr, "1970-01-01 01:01:01");
						}else{
							System.out.println(keyStr);
							System.out.println(maps);
						}
					}
				}
				maps.put("table_name", tableName);
				mapData.put(key, maps);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<String> getRuleValueList(String tableName, Connection conn) {
		List<String> listData = new ArrayList<String>();
		try {
			String sql = " select * from " + tableName + " where rule_field='brand' and rule_status = 0 ";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				listData.add(rs.getString("rule_value").toLowerCase());
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listData;
	}
	/**
	 * 查询一个基础表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的HashMap<String,String> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getProductInfo(String tableName, List<String> keyList, String value, Connection conn60) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			//System.out.println("select * from " + tableName);
			PreparedStatement pst = conn60.prepareStatement("select * from " + tableName + " where PLATFORM_ID in ('4','5','47') and SKU_ID != '0'");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				map.put(key, rs.getString(value));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询一个基础表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的HashMap<String,String> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getProductInfo0(String tableName, List<String> keyList, String value, Connection conn60) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			//System.out.println("select * from " + tableName);
			PreparedStatement pst = conn60.prepareStatement("select * from " + tableName + " where PLATFORM_ID in ('4','5','47') and SKU_ID = '0'");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				map.put(key, rs.getString(value));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getProductStockList(String tableName,
			HashMap<String, String> filter, List<String> keyList, String orderBy, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			sql += " ORDER BY  " + orderBy;
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)).equals("def")?"0":rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				List<HashMap<String, String>> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList<HashMap<String, String>>();
				}
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr, rs.getString(keyStr).equals("def")?"0":rs.getString(keyStr));
					}
				}
				maps.put("table_name", tableName);
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getAllDuizhaoList(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			sql += "and status in ('0','1','4-1')";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				List<HashMap<String, String>> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList<HashMap<String, String>>();
				}
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr, rs.getString(keyStr));
					}
				}
				maps.put("table_name", tableName);
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, HashMap<String, String>> getAllTbaleList(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, HashMap<String, String>> mapData = new HashMap<String, HashMap<String, String>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr.toLowerCase(), rs.getString(keyStr));
					}
				}
				mapData.put(key, maps);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, String> getAllTbalePriceList(String tableName, HashMap<String, String> filter,
			List<String> keyList, String val, Connection conn) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				mapData.put(key, rs.getString(val));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getAllTbalePriceList(String tableName,
			HashMap<String, String> filter) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql.getConn();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + "='" + filter.get(key) + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String key = rs.getString("platform_id") + "\001" + rs.getString("shop_id") + "\001"
						+ rs.getString("operation_product_id") + "\001" + rs.getString("sku_id");
				List<HashMap<String, String>> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList<HashMap<String, String>>();
				}
				System.out.println(rs.getString(1));
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr, rs.getString(keyStr));
					}
				}
				li.add(maps);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}

	public static List<HashMap<String, String>> getTbaleList(String tableName, int startNum, int endNum) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("select * from " + tableName + " LIMIT " + startNum + "," + endNum);
			System.out.println("select * from " + tableName + " LIMIT " + startNum + "," + endNum);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						maps.put(key, rs.getString(key));
					}
				}
				list.add(maps);
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void insertData(String tableName, List<String> filedList, List<HashMap<String, String>> dataMap) {
		StringBuffer sb = new StringBuffer("INSERT IGNORE INTO user_syntun." + tableName + "(");
		int i = 0;
		for (HashMap<String, String> map : dataMap) {
			StringBuffer startVal = new StringBuffer();
			if (i == 0) {
				startVal.append(" VALUES ");
			} else {
				startVal.append(",");

			}
			for (int j = 0; j < filedList.size(); j++) {
				String filed = filedList.get(j);
				if (i == 0 && j == 0) {
					sb.append(filed);
				} else if (i == 0 && j != 0) {
					sb.append("," + filed);
				}
				if (j == 0) {
					startVal.append("(").append("'" + StringEscapeUtils.escapeSql(map.get(filed)) + "'");
				} else {
					startVal.append(",").append("'" + StringEscapeUtils.escapeSql(map.get(filed)) + "'");
				}
			}
			startVal.append(")");
			if (i == 0) {
				sb.append(")");
			}
			sb.append(startVal.toString());
			i = 1;
		}
		if (sb.toString().indexOf("VALUES") == -1) {
			return;
		}
		System.out.println(sb.toString());
		Connection con = ConnectSql.getConn();
		try {
			PreparedStatement ps = con.prepareStatement(sb.toString().replace("\\", "\\\\"));
			ps.execute();
			ConnectSql.push(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean truncateTbaleAllData(String tableName, Connection conn) {
		boolean isTrue = false;
		try {
			String sql = "TRUNCATE TABLE " + tableName;
			
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			pst.executeUpdate();
			isTrue = true;
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrue;
	}
	/**
	 * 删除数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean deleteTbaleData(String tableName, String dateStr1, String dateStr) {
		boolean isTrun = false;
		Connection conn = ConnectSql138.getConn();
		try {
			// ALTER TABLE syntun_v2.AMAZON_PRICE_ALL RENAME TO syntun_v2.AMAZON_PRICE_ALL_20180920
			// CREATE TABLE IF NOT EXISTS syntun_v2.AMAZON_PRICE_ALL (LIKE syntun_v2.AMAZON_PRICE_ALL_20180920)
			// DROP TABLE syntun_v2.AMAZON_PRICE_ALL_20180917
			System.out.println("-----表格数据备份-----"+tableName);
			//String name = tableName.substring(10, tableName.length());
			String sql1 = " ALTER TABLE "+tableName+" RENAME TO "+tableName+"_"+dateStr+";";
			String sql2 = " CREATE TABLE IF NOT EXISTS "+tableName+" (LIKE "+tableName+"_"+dateStr+");";
			String sql3 = " DROP TABLE "+tableName+"_"+dateStr1+";";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			System.out.println(sql1);
			pst1.executeUpdate();
			pst1.close();
			
			PreparedStatement pst2 = conn.prepareStatement(sql2);
			System.out.println(sql2);
			pst2.executeUpdate();
			pst2.close();
			
			PreparedStatement pst3 = conn.prepareStatement(sql3);
			System.out.println(sql3);
			pst3.executeUpdate();
			pst3.close();
			isTrun = true;
			ConnectSql138.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrun;
	}
	/**
	 * 拷贝表格
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static String copyTable(String tableName) {
		String tableNameCopy = tableName;
		Connection conn = ConnectSql138.getConn();
		try {
			String sql2 = " CREATE TABLE IF NOT EXISTS "+tableName+"_copy (LIKE "+tableName+");";
			PreparedStatement pst2 = conn.prepareStatement(sql2);
			System.out.println(sql2);
			pst2.executeUpdate();
			pst2.close();
			
			tableNameCopy = tableName+"_copy";
			ConnectSql138.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableNameCopy;
	}
	/**
	 * 删除数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean deleteTbaleData(String tableName) {
		boolean isTrun = false;
		Connection conn = ConnectSql138.getConn();
		try {
			String sql1 = " DROP TABLE "+tableName+";";
			String sql3 = " ALTER TABLE "+tableName+"_copy RENAME TO "+tableName+";";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			System.out.println(sql1);
			pst1.executeUpdate();
			pst1.close();
			
			PreparedStatement pst3 = conn.prepareStatement(sql3);
			System.out.println(sql3);
			pst3.executeUpdate();
			pst3.close();
			isTrun = true;
			ConnectSql138.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrun;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getTbaleAllData(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " ='" + filter.get(key) + "'";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				List<HashMap<String, String>> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList<HashMap<String, String>>();
				}
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr.toLowerCase(), rs.getString(keyStr));
					}
				}
				maps.put("table_name", tableName);
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, String> getShopListData(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " ='" + filter.get(key) + "'";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				mapData.put(key, rs.getString("shop_id"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<HashMap<String, String>> getpromotionMJList(String tableName, Connection conn) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getFieldForm(tableName, conn);
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						maps.put(key, rs.getString(key));
					}
				}
				list.add(maps);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<HashMap<String, String>> getpromotionMJList22(String tableName, Connection conn) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getFieldForm(tableName, conn);
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						if(key.equals("PROMOTION_TYPE_INFO"))
							maps.put(key.toLowerCase(), rs.getString(key));
						if(key.equals("man"))
							maps.put("满", rs.getString(key));
						if(key.equals("jian"))
							maps.put("减", rs.getString(key));
						else
							maps.put(key, rs.getString(key));
					}
				}
				list.add(maps);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<String>> getAllTbaleSkuUserList(String tableName, Connection conn) {
		HashMap<String, List<String>> mapData = new HashMap<String, List< String>>();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			//遍历结果集
			while (rs.next()) {
				String key = rs.getString("C_ID");
				List< String> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList< String>();
				}
				li.add(rs.getString("PRODUCT_ID"));
				mapData.put(key, li);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
	/**
	 * 查询一个基础表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的HashMap<String,String> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getProductShopList(String tableName, List<String> keyList, String value, Connection conn16) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			//System.out.println("select * from " + tableName);
			PreparedStatement pst = conn16.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				map.put(key, rs.getString(value));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询一个基础表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getProductTbaleList(String tableName, List<String> keyList, String value, Connection conn16) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			System.out.println("select * from " + tableName + " where PLATFORM_ID in ('4','5','44','47')");
			PreparedStatement pst = conn16.prepareStatement("select * from " + tableName + " where PLATFORM_ID in ('4','5','44','47')");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keyList.size(); i++) {
					if (i == 0) {
						sb.append(rs.getString(keyList.get(i)));
					} else {
						sb.append("\001").append(rs.getString(keyList.get(i)));
					}
				}
				String key = sb.toString();
				map.put(key, rs.getString(value));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList2(String tableName, String keyStr, Connection conn) {
		String sb = "('";
		try {
			String sql = "select distinct PRODUCT_ID from syntun_v2.SKYSCOPE_PRICE";
			System.out.println(sql);
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				sb = sb + rs.getString("PRODUCT_ID") + "','";
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		//Connection conn = ConnectSqlServer.getConn();
		try {
			String sql = "select * from " + tableName + " where PRODUCT_ID in " + sb + "')";
			//System.out.println(sql.substring(0, 100));
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(keyStr), rs.getString("PRODUCT_NAME")+"\002"+rs.getString("BRAND_ID")+"\002"+rs.getString("CATEGORY_ID"));
			}
			pst.close();
			//ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList(String tableName, String keyStr, String value, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(keyStr), rs.getString(value));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getAllTbaleMapMap(String getDate, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		try {
			String sql = " select * from syntun_v2.天猫特殊促销 where" + " start_time<='" + getDate + " 00:00:00'"
					+ " and end_time>='" + getDate + " 00:00:00'";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String key = rs.getString("shop_id");
				List<HashMap<String, String>> li = null;
				if (mapData.containsKey(key)) {
					li = mapData.get(key);
				} else {
					li = new ArrayList<HashMap<String, String>>();
				}
				HashMap<String, String> maps = new HashMap<String, String>();
				maps.put("promotion_type_name", "满减");
				maps.put("promotion_type_info", rs.getString("promotion_info"));
				maps.put("满", rs.getString("满"));
				maps.put("减", rs.getString("减"));
				maps.put("end_time", rs.getString("end_time").substring(0, 10));
				maps.put("start_time", rs.getString("start_time").substring(0, 10));
				maps.put("shop_id", rs.getString("shop_id"));
				maps.put("table_name", "wgdata_tmall.promotion_info");
				maps.put("platform_id", "5");
				if (SyntunDate.isTimeOut(maps.get("start_time") + "_" + maps.get("end_time"), getDate)) {
					li.add(maps);
					mapData.put(key, li);
				}
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapData;
	}
}
