package com.syntun.etl.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.syntun.etl.tools.ConnectSqlServer;

public class BaseDaoSqlServer {
	public static void main(String[] args) {
		getTbaleAllData("[syntun_base].[dbo].[url_total_list]");
	}
	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, HashMap<String, String>> getTbaleAllData(String tableName) {
		List<String> keyList = new ArrayList<String>();
		keyList.add("URL_id");
		keyList.add("platform_id");
		// 读取标准表的用户和产品
        HashMap<String, String> filter = new HashMap<String, String>();
        Connection conn = ConnectSqlServer.getConn();
        
		HashMap<String, HashMap<String, String>> mapData = new HashMap<String, HashMap<String, String>>();
		//List<String> fieldList = getField(tableName);
		List<String> fieldList = new ArrayList<String>();

		fieldList.add("product_name");
		fieldList.add("shop_id");
		fieldList.add("shop_name");
		fieldList.add("shop_info");
		fieldList.add("shop_info_1");
		fieldList.add("category_name");
		fieldList.add("brand_name");
		fieldList.add("category_id");
		fieldList.add("brand_id");
		fieldList.add("platform_name");
		fieldList.add("super_category_name");
		fieldList.add("hight_category_name");
//		fieldList.add("category_name_jiu");
//		fieldList.add("hight_category_name_jiu");
//		fieldList.add("super_category_name_jiu");
		System.out.println(fieldList);
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
				
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr.toLowerCase(), rs.getString(keyStr));
					}
				}
				//maps.put("table_name", tableName);
				mapData.put(key, maps);
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
	public static HashMap<String, String> getTbaleList(String tableName, String keyStr, String value) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(keyStr), rs.getString(value));
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getProductForSku() {
		// SELECT TOP 1000
		// [operation_product_id],[product_id],[shop_id],[platform_id],[update_day]
		// FROM [DS_OLSP].[dbo].[d_test_time]
		// 传入日期 根据日期判断是否出现该id

		Connection conn = ConnectSqlServer.getConn();
		String sql = "select  PLATFORM_ID,OPERATION_PRODUCT_ID,PRODUCT_ID,SHOP_ID,sku_id from PRODUCT_PLATFORM_LIST where PLATFORM_ID='47'";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("PLATFORM_ID") == null) {
					continue;
				}
				map.put(rs.getString("sku_id") + "/" + rs.getString("OPERATION_PRODUCT_ID"),
						rs.getString("PRODUCT_ID"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSqlServer.push(conn);
		}
		return map;
	}
	
	
	// /**
	// * 查询PRODUCT_PLATFORM_LIST表所有category_id 为 BaseDao.getCateguryList()的数据
	// *
	// * @param tableName
	// * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	// */
	// public static Set<String> getProductList() {
	// Set<String> list = new HashSet<String>();
	// Connection conn = ConnectSqlServer.getConn();
	// try {
	// PreparedStatement pst = conn.prepareStatement(
	// "SELECT PRODUCT_ID FROM PRODUCT_PLATFORM_LIST where PRODUCT_ID in (SELECT
	// product_id FROM product_list where category_id in ("
	// + BaseDao.getCateguryList() + "))");
	// ResultSet rs = pst.executeQuery();
	// while (rs.next()) {
	// list.add(rs.getString(1));
	// }
	// rs.close();
	// pst.close();
	// ConnectSqlServer.push(conn);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return list;
	// }

	/**
	 * 
	 */

	public static HashMap<String, String> getProductPlatformList() {
		// SELECT TOP 1000
		// [operation_product_id],[product_id],[shop_id],[platform_id],[update_day]
		// FROM [DS_OLSP].[dbo].[d_test_time]
		// 传入日期 根据日期判断是否出现该id

		Connection conn = ConnectSqlServer.getConn();
		String sql = "select PLATFORM_ID,OPERATION_PRODUCT_ID,PRODUCT_ID,SHOP_ID from PRODUCT_PLATFORM_LIST ";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("PLATFORM_ID") == null) {
					continue;
				}
				if (rs.getString("PLATFORM_ID").equals("3") || rs.getString("PLATFORM_ID").equals("10")) {
					map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("SHOP_ID") + "/"
							+ rs.getString("OPERATION_PRODUCT_ID"), rs.getString("PRODUCT_ID"));
				}
				map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("OPERATION_PRODUCT_ID"),
						rs.getString("PRODUCT_ID") + "/" + rs.getString("SHOP_ID"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSqlServer.push(conn);
		}
		return map;
	}

	public static HashMap<String, String> getCateGoryProductList(String categoryStatus) {
		// SELECT TOP 1000
		// [operation_product_id],[product_id],[shop_id],[platform_id],[update_day]
		// FROM [DS_OLSP].[dbo].[d_test_time]
		// 传入日期 根据日期判断是否出现该id

		Connection conn = ConnectSqlServer.getConn();
		String sql = "select  PLATFORM_ID,OPERATION_PRODUCT_ID,PRODUCT_ID,SHOP_ID from PRODUCT_PLATFORM_LIST where category_id in ("
				+ BaseDao.getCateguryList(categoryStatus) + ")";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("PLATFORM_ID") == null) {
					continue;
				}
				if (rs.getString("PLATFORM_ID").equals("3") || rs.getString("PLATFORM_ID").equals("10")) {
					map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("SHOP_ID") + "/"
							+ rs.getString("OPERATION_PRODUCT_ID"), rs.getString("PRODUCT_ID"));
				}
				map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("OPERATION_PRODUCT_ID"),
						rs.getString("PRODUCT_ID") + "/" + rs.getString("SHOP_ID"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSqlServer.push(conn);
		}
		return map;
	}

	public static HashMap<String, String> getTaobao() {
		Connection conn = ConnectSqlServer.getConn();
		String sql = "select  PLATFORM_ID,OPERATION_PRODUCT_ID,PRODUCT_ID,SHOP_ID,sku_id from PRODUCT_PLATFORM_LIST where PLATFORM_ID='47'";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("PLATFORM_ID") == null) {
					continue;
				}
				map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("OPERATION_PRODUCT_ID") + "/"
						+ rs.getString("sku_id"), rs.getString("PRODUCT_ID") + "/" + rs.getString("SHOP_ID"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSqlServer.push(conn);
		}
		return map;
	}

	public static HashMap<String, String> getCateGoryProductList() {
		// SELECT TOP 1000
		// [operation_product_id],[product_id],[shop_id],[platform_id],[update_day]
		// FROM [DS_OLSP].[dbo].[d_test_time]
		// 传入日期 根据日期判断是否出现该id

		Connection conn = ConnectSqlServer.getConn();
		String sql = "select top 1000  OPERATION_PRODUCT_ID,sku_id from PRODUCT_PLATFORM_LIST  where PLATFORM_ID='47'";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1) + "__" + rs.getString(2));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSqlServer.push(conn);
		}
		return map;
	}
	/**
	 * 
	 */
	public static HashMap<String, String> getProductPlatformList(String categoryId) {
		Connection conn = ConnectSqlServer.getConn();
		String sql = "select  PLATFORM_ID,OPERATION_PRODUCT_ID,PRODUCT_ID,SHOP_ID from PRODUCT_PLATFORM_LIST where CATEGORY_ID in("
				+ categoryId + ")";
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("PLATFORM_ID") == null) {
					continue;
				}
				if (rs.getString("PLATFORM_ID").equals("3") || rs.getString("PLATFORM_ID").equals("10")) {
					map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("SHOP_ID") + "/"
							+ rs.getString("OPERATION_PRODUCT_ID"), rs.getString("PRODUCT_ID"));
				}
				map.put(rs.getString("PLATFORM_ID") + "/" + rs.getString("OPERATION_PRODUCT_ID"),
						rs.getString("PRODUCT_ID") + "/" + rs.getString("SHOP_ID"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSqlServer.push(conn);
		}
		return map;
	}

	/**
	 * 查询PRODUCT_PLATFORM_LIST表所有category_id 为 BaseDao.getCateguryList()的数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<String> getOpertionList() {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT OPERATION_PRODUCT_ID FROM PRODUCT_PLATFORM_LIST where category_id in ("
							+ BaseDao.getCommentCateguryList() + ")");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询PRODUCT_PLATFORM_LIST表所有category_id 为 BaseDao.getCateguryList()的数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static List<String> getCommentProductList() {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT product_id,category_id,brand_id FROM product_list where category_id in ("
							+ BaseDao.getCommentCateguryList() + ")");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("product_id") + "," + rs.getString("category_id") + ","
						+ rs.getString("brand_id"));
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询PRODUCT_PLATFORM_LIST表所有category_id 为 BaseDao.getCateguryList()的数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> haigetCommentProductList(String categoryIds) {
		HashMap<String, String> list = new HashMap<String, String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT product_id,category_id,brand_id FROM product_list where category_id in ("
							+ categoryIds + ")");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.put(rs.getString("product_id"), rs.getString("category_id") + "," + rs.getString("brand_id"));
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询PRODUCT_PLATFORM_LIST表所有category_id 为 BaseDao.getCateguryList()的数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static Set<String> haigetOpertionList(String categoryIds) {
		Set<String> list = new HashSet<String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT OPERATION_PRODUCT_ID FROM PRODUCT_PLATFORM_LIST where category_id in ("
							+ categoryIds + ")");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 这个是查询的mysql 查询hai使用的抽取品类
	 * 
	 */
	public static String getCommentCateguryList() {
		String cateGury = null;
		Connection conn = ConnectSql.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"SELECT category_id FROM `syntun_etl`.`comment_etl_category_status_hai` WHERE category_status=1");
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
	 * 查询PRODUCT_PLATFORM_LIST表所有category_id 为 BaseDao.getCateguryList()的数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getProductListCateGory() {
		HashMap<String, String> list = new HashMap<String, String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT product_id,category_id FROM product_list");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.put(rs.getString("product_id"), rs.getString("category_id"));
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
	public static List<String> getTbaleList(String tableName, String keyStr, String value, String val) {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(keyStr) + ":" + rs.getString(value));
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
		Connection conn = ConnectSqlServer.getConn();
		try {
			String sql = "select * from " + tableName + " where 1=1 ";
			if (filter != null) {
				for (String key : filter.keySet()) {
					if (filter.get(key).indexOf(",") != -1) {
						sql += " and " + key + " in (" + filter.get(key) + ") ";
					} else {
						sql += " and " + key + "='" + filter.get(key) + "' ";
					}
				}
			}
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (map.containsKey(rs.getString(a1))) {
					map.put(rs.getString(a1), map.get(rs.getString(a1)) + ":" + rs.getString(a2) + ","
							+ rs.getString(b1) + "," + rs.getString(b2));
				} else {
					map.put(rs.getString(a1), rs.getString(a2) + "," + rs.getString(b1) + "," + rs.getString(b2));
				}
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表数据
	 * 
	 * @param tableName
	 *            表名称
	 * @param a1
	 *            key1_1
	 * @param a2
	 *            key1_2
	 * @param b1
	 *            val
	 * @return 返回由a1:a2组成key，val为值的map集合
	 */
	public static HashMap<String, String> getTableMap(String tableName, String a1, String a2, String b1) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getString(a2) != null && rs.getString(a1) != null && rs.getString(b1) != null) {
					map.put(rs.getString(a1) + ":" + rs.getString(a2), rs.getString(b1).replace(".0", ""));
				}
			}
			pst.close();
			ConnectSqlServer.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询一个表数据
	 * 
	 * @param tableName
	 *            表名称
	 * @param a1
	 *            key1_1
	 * @param a2
	 *            key1_2
	 * @param b1
	 *            val
	 * @param b2
	 *            val2
	 * @return 返回由a1:a2组成key，val为值的map集合
	 */
	public static HashMap<String, String> getTableMap(String tableName, String a1, String a2, String b1, String b2) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			String sql = "select * from " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getString(a2) != null && rs.getString(a1) != null && rs.getString(b1) != null) {
					map.put(rs.getString(a1) + ":" + rs.getString(a2),
							rs.getString(b1).replace(".0", "") + "/" + rs.getString(b2).replace(".0", ""));
				}
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
		Connection conn = ConnectSqlServer.getConn();
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
			ConnectSqlServer.push(conn);
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
	public static List<String> getCategoryList(String tableName, String categoryId) {
		List<String> list = new ArrayList<String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn
					.prepareStatement("select * from " + tableName + " where category_id='" + categoryId + "'");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("OPERATION_PRODUCT_ID"));
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
	public static List<HashMap<String, String>> getSqlList(String sql, String a, String b) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				maps.put(rs.getString(a), rs.getString(b));
				list.add(maps);
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
	
	/**
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField(String tableName, String ip, String dataBase) {

		if (tableName.contains("[")) {
			tableName = tableName.split("\\[")[3].replace("]", "");
		}
		HashMap<String, HashMap<String, String>> map = getTable(tableName, ip, dataBase);
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
	public static HashMap<String, HashMap<String, String>> getTable(String tableName, String ip,
			String dataBaseName) {

		HashMap<String, HashMap<String, String>> tableMap = new HashMap<String, HashMap<String, String>>();
		try {
			Connection conn = ConnectSqlServer13.getOneConn(ip, dataBaseName);
			PreparedStatement pstm = conn.prepareStatement(
					"select t.name as tablename,c.name as columnname,ty.name as typename,c.max_length as typelength from"
							+ " sys.columns c inner join sys.tables t on t.object_id=c.object_id inner join sys.types ty "
							+ "on ty.system_type_id=c.system_type_id where t.name='" + tableName
							+ "' order by t.name,c.column_id");
			System.out.println(tableName);
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
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableMap;
	}
}
