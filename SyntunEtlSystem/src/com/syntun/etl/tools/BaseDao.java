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
	
	public static boolean deleteTable(String tableName, HashMap<String, String> filterMap) {
        Connection conn = ConnectSql132.getConn();
        boolean b = false;
        try {
            String sql = "DELETE FROM " + tableName + " where 1=1";
            for (String key : filterMap.keySet()) {
                sql += " and " + key + "='" + filterMap.get(key) + "' ";
            }
            PreparedStatement pst = conn.prepareStatement(sql);
            System.out.println(sql);
            b = pst.execute();
            pst.close();
            ConnectSql132.push(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return b;
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
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("SHOW FULL FIELDS FROM " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Field"));
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
	public static HashMap<String, String> getTbaleUniqueMap(String tableName) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql132.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String unique = rs.getString("unique_key").replace("`", "").replace("'", "").replace("，", ",");
				map.put(rs.getString("data_base")+"."+rs.getString("table_name"), unique);
			}
			pst.close();
			ConnectSql132.push(conn);
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
		String sql = "select platform_id, promotion_type_name, promotion_price, price from syntun_etl.price_promotion_compute";
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
	 * 查询标准产品库
	 */
	public static HashMap<String, String> getProductPlatformMap() {

		Connection conn = ConnectSql.getConn();
		String sql = "SELECT PRODUCT_ID, category_id, OPERATION_PRODUCT_ID, SHOP_ID, PLATFORM_ID FROM syntun_etl.PRODUCT_PLATFORM_LIST";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("PLATFORM_ID").equals("10") || rs.getString("PLATFORM_ID").equals("3")) {
					map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("OPERATION_PRODUCT_ID") + "/",
							rs.getString("PRODUCT_ID") + "/" + rs.getString("SHOP_ID"));
				} else {

				}
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
	 * 查询一个表所有数据
	 * 
	 * @param categoryStatus
	 * 
	 */
	public static String getCateguryList(String categoryStatus) {
		String cateGury = null;
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"SELECT category_id FROM `syntun_etl`.`" + categoryStatus + "` WHERE category_status=1");

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (cateGury == null) {
					cateGury = rs.getString("category_id");
				} else {
					cateGury = cateGury + "," + rs.getString("category_id");
				}
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cateGury;
	}

	/**
	 * 查询一个表所有数据
	 * 
	 */
	public static String getCommentCateguryList() {
		String cateGury = null;
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"SELECT category_id FROM `syntun_etl`.`comment_etl_category_status` WHERE category_status=1");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (cateGury == null) {
					cateGury = rs.getString("category_id");
				} else {
					cateGury = cateGury + "," + rs.getString("category_id");
				}
			}
			pst.close();
			ConnectSql.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cateGury;
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
			PreparedStatement pst = conn
					.prepareStatement("SELECT * FROM syntun_etl.new_MJ_promotion_replace ORDER BY priority");
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
	 * 查询一个表所有数据sort_product_mohu
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getSTbaleList(String dateStr) {
		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			String sql = "select * from sort_product_mohu where 1=1 ";
			if (dateStr != null) {
				sql += " and get_date ='" + dateStr + "' ";
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//HashMap<String, String> map = new HashMap<String, String>();
				map.put(rs.getString("product_id"), rs.getString("product_name"));
				//list.add(map);
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
				//HashMap<String, String> map = new HashMap<String, String>();
				map.put(rs.getString("product_id")+"001/"+rs.getString("product_name")
						, rs.getString("filter1")+"001/"+rs.getString("filter2")+"001/"+rs.getString("filter3"));
				//list.add(map);
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
	public static HashMap<String, String> getTbaleList(String tableName, String key1, String key2, String val1) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSql.getConn();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(key1) + rs.getString(key2), rs.getString(val1));
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
		Connection conn = ConnectSql.getConn();
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

	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<List<HashMap<String, String>>> getMJPatternList() {
		String tableName = "syntun_etl.new_MJ_pattern_list";
		List<List<HashMap<String, String>>> list = new ArrayList<List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT * FROM syntun_etl.new_MJ_pattern_list ORDER BY priority,pattern_type");
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
		String tableName = "syntun_etl.new_promotion_type_pattern";
		List<List<HashMap<String, String>>> list = new ArrayList<List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT * FROM " + tableName + " ORDER BY priority,pattern_type");
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
				System.out.println(rs.getString(1));
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

}