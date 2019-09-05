package com.syntun.etl.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.syntun.etl.tools.ConnectSqlServer;

public class BaseDaoSqlServer {

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<HashMap<String, String>> getTbaleAllData(String tableName, Connection conn) {
        
		List<HashMap<String, String>> mapData = new ArrayList<HashMap<String, String>>();
		//List<String> fieldList = getField(tableName);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("url_id");
		fieldList.add("platform_id");
		fieldList.add("platform_name");
		fieldList.add("brand_id");
		fieldList.add("brand_name");
		fieldList.add("category_id");
		fieldList.add("category_name");
		fieldList.add("super_category_name");
		fieldList.add("hight_category_name");
		fieldList.add("product_name");
		fieldList.add("shop_id");
		fieldList.add("shop_name");
		fieldList.add("shop_info");
		fieldList.add("shop_info_1");
		fieldList.add("super_category_name_11");
		fieldList.add("brand_name_jiu");
		fieldList.add("new_category_name");
		fieldList.add("category_name_jiu");
		fieldList.add("hight_category_name_jiu");
		fieldList.add("super_category_name_jiu");
		System.out.println(fieldList);
		try {
			String sql = " select * from " + tableName + " where 1=1 and platform_id not in ('1','3','5')";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
			
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String keyStr : fieldList) {
					if (rs.getString(keyStr) != null) {
						maps.put(keyStr.toLowerCase(), rs.getString(keyStr));
					}
				}
				
				mapData.add(maps);
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
	/**
	 * 查询一个表所有数据
	 * 
	 * @param tableName
	 * @return 包含所有数据的list<HashMap<String,String>> 集合， HashMap中存储的是当前行数据每列的值
	 */
	public static HashMap<String, String> getTbaleList2(String tableName, String keyStr, Connection conn16) {
		String sb = "('";
		try {
			String sql = "select distinct product_id from [skyscope].[dbo].[SKYSCOPE_PRICE]";
			PreparedStatement pst = conn16.prepareStatement(sql);
			//System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				sb = sb + rs.getString("product_id") + "','";
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSqlServer.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName +
					"where product_id in " + sb + "')");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(keyStr), rs.getString("product_name")+"\002"+rs.getString("brand_id")+"\002"+rs.getString("category_id"));
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
	public static HashMap<String, String> getTbaleList16(String tableName, String keyStr, String value) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = ConnectSqlServer16.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(keyStr), rs.getString(value));
			}
			pst.close();
			ConnectSqlServer16.push(conn);
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
	public static String getStr(String tableName, String valStr) {
		Connection conn = ConnectSqlServer16.getConn();
		StringBuffer sb = new StringBuffer("'0'");
		try {
			PreparedStatement pst = conn.prepareStatement("select DISTINCT(" + valStr + ") from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				sb.append(",'").append(rs.getString(valStr)).append("'");
			}
			pst.close();
			ConnectSqlServer16.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
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
	/**
	 * 
	 */

	public static HashMap<String, String> getShopList(String tableName, String filters, Connection conn) {

		//Connection conn = ConnectSqlServer.getConn();
		String sql = "select shop_id,shop_name from " + tableName + " where 1=1";
		if (filters != null && !filters.equals("")) {
			sql += " and platform_id in (" + filters + ")";
		}
		PreparedStatement ps;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("shop_name") == null) {
					continue;
				}
				map.put(rs.getString("shop_name"), rs.getString("shop_id"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, String> getTbaleData(String tableName,
			LinkedList<String> keyList, String val, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		List<String> productList = getProductIdList("[skyscope].[dbo].[SKYSCOPE_PRICE]", conn);
		StringBuffer product = new StringBuffer("('");
		for (int j = 0; j < productList.size(); j++) {
			product.append(productList.get(j) + "','");
		}
		product.append("')");
		
		try {
			String sql = " select * from " + tableName + " WHERE PRODUCT_ID in " + product.toString();
			//String sql = " select * from " + tableName + " where PLATFORM_ID in ('5','10','47')";
			
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
				map.put(key, rs.getString(val));
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
	public static List<String> getProductIdList(String tableName, Connection conn) {
		List<String> list = new ArrayList<String>();
		try {
			String sql = "SELECT DISTINCT PRODUCT_ID FROM " + tableName;
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("PRODUCT_ID"));
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
	public static HashMap<String, String> getTbaleDataD(String tableName,
			LinkedList<String> keyList, String val, String dateStr, Connection conn) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		List<String> operationProductList = getOperationProductIdList("wgdata_tmall.product_source", "wgdata_jingdong.product_name", dateStr);
		StringBuffer operationProduct = new StringBuffer("('");
		for (int j = 0; j < operationProductList.size(); j++) {
			operationProduct.append(operationProductList.get(j) + "','");
		}
		operationProduct.append("')");
		
		try {
			String sql = " select * from " + tableName + " WHERE OPERATION_PRODUCT_ID in " + operationProduct.toString();
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql.substring(0,600));
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
				map.put(key, rs.getString(val));
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
	public static List<String> getOperationProductIdList(String tableNameA, String tableNameB, String dateStr) {
		String sqlName = "shuju";
		String sqlPassWord = "shuju";
		String sqlDbName = "wgdata_tmall";
		String sqlHost = "192.168.0.112:3306";
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn112 = BaseConnectSql.getConn(sqlHost, sqlDbName, sqlName, sqlPassWord);
		
		List<String> list = new ArrayList<String>();
		try {
			String sql = "SELECT DISTINCT product_id FROM " + tableNameA 
					+ " WHERE get_date = " + "'" + dateStr + "'";
			PreparedStatement pst = conn112.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("product_id"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			String sql = "SELECT DISTINCT product_id FROM " + tableNameB
					+ " WHERE get_date = " + "'" + dateStr + "'";
			PreparedStatement pst = conn112.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("product_id"));
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BaseConnectSql.push(conn112);
		
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
	public static HashMap<String, List<HashMap<String, String>>> getAllTbaleMap(String tableName,
			HashMap<String, String> filter, List<String> keyList) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSqlServer.getConn();
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
			ConnectSqlServer.push(conn);
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
	public static HashMap<String, List<HashMap<String, String>>> getAllTbaleMapMap(String getDate) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		Connection conn = ConnectSqlServer16.getConn();
		try {
			String sql = " select * from skyscope.dbo.[天猫特殊促销] where" + " start_time<='" + getDate + " 00:00:00'"
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
			ConnectSqlServer16.push(conn);
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleMap(String tableName,
			HashMap<String, String> filter, LinkedList<String> keyList) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName);
		Connection conn = ConnectSqlServer.getConn();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " in (" + filter.get(key) + ") ";
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
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
			ConnectSqlServer.push(conn);
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleMap(String tableName,
			HashMap<String, String> filter, LinkedList<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " in (" + filter.get(key) + ") ";
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleMap16(String tableName,
			HashMap<String, String> filter, LinkedList<String> keyList) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName);
		Connection conn = ConnectSqlServer16.getConn();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " in (" + filter.get(key) + ") ";
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
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
			ConnectSqlServer16.push(conn);
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleMap16(String tableName,
			HashMap<String, String> filter, LinkedList<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName, conn);
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " in (" + filter.get(key) + ") ";
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
	public static HashMap<String, String> getShopListData15(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		//System.out.println(fieldList);
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
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, String> getProductData(String tableName, List<String> keyList, Connection conn) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		try {
			String sql = " select * from " + tableName;
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
				mapData.put(key, rs.getString("PRODUCT_NAME"));
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleAllData16(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName, "192.168.0.16", "skyscope");
		//System.out.println(fieldList);
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleAllDataBak16(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName, "192.168.0.16", "skyscope_temp");
		//System.out.println(fieldList);
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
	public static HashMap<String, List<HashMap<String, String>>> getInspectionAllData(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName, conn);
		//System.out.println(fieldList);
		try {
			String sql = "select * from " + tableName + " where PLATFORM_ID in ('1','3','5','10')";
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
	public static HashMap<String, List<HashMap<String, String>>> getTbaleAllData(String tableName,
			HashMap<String, String> filter, List<String> keyList, Connection conn) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName, conn);
		//System.out.println(fieldList);
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
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<String>> getAllTbaleSkuUserListBak(String tableName, HashMap<String, String> filter, Connection conn) {
		HashMap<String, List<String>> mapData = new HashMap<String, List< String>>();
		try {
			String sql = " select * from " + tableName + " where 1=1 ";
			for (String key : filter.keySet()) {
				sql += " and " + key + " ='" + filter.get(key) + "'";
			}
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

	public static void main(String[] args) {
		String tableName = "syntun_v2.Table";
		String tableName16 = "["+tableName.replace("syntun_v2", "syntun_base.dbo").replace(".", "].[")+"]";
		System.out.println(tableName16);
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap<String, List<HashMap<String, String>>> getTbaleMap16Time(String tableName,
			HashMap<String, String> filter, LinkedList<String> keyList) {
		HashMap<String, List<HashMap<String, String>>> mapData = new HashMap<String, List<HashMap<String, String>>>();
		List<String> fieldList = getField16(tableName);
		Connection conn = ConnectSqlServer16.getConn();
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
				li.add(maps);
				mapData.put(key, li);
			}
			pst.close();
			ConnectSqlServer16.push(conn);
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
	public static List<HashMap<String, String>> getTbaleList16(String tableName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField16(tableName);
		Connection conn = ConnectSqlServer16.getConn();
		try {
			System.out.println("select * from " + tableName);
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
	public static List<HashMap<String, String>> getTbaleListAll16(String tableName, String ip, String dataBase) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField16(tableName, ip, dataBase);
		Connection conn = ConnectSqlServer16.getConn();
		try {
			System.out.println("select * from " + tableName);
			PreparedStatement pst = conn.prepareStatement("select * from " + tableName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, String> maps = new HashMap<String, String>();
				for (String key : fieldList) {
					if (rs.getString(key) != null) {
						maps.put(key.toLowerCase(), rs.getString(key));
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
	public static List<HashMap<String, String>> getpromotionMJList(String tableName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField16(tableName, "192.168.0.16", "skyscope");
		Connection conn = ConnectSqlServer16.getConn();
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
			ConnectSqlServer16.push(conn);
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
	public static List<HashMap<String, String>> getTbaleList(String tableName, Connection conn) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> fieldList = getField(tableName);
		//Connection conn = ConnectSqlServer.getConn();
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

		if (tableName.contains("[")) {
			tableName = tableName.split("\\[")[3].replace("]", "");
		}
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

	public static List<String> getField(String tableName, Connection conn) {

		if (tableName.contains("[")) {
			tableName = tableName.split("\\[")[3].replace("]", "");
		}
		HashMap<String, HashMap<String, String>> map = getTable(tableName, conn);
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
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField16(String tableName) {

		if (tableName.contains("[")) {
			tableName = tableName.split("\\[")[3].replace("]", "");
		}
		HashMap<String, HashMap<String, String>> map = getTable16(tableName);

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
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField16(String tableName, Connection conn) {

		if (tableName.contains("[")) {
			tableName = tableName.split("\\[")[3].replace("]", "");
		}
		HashMap<String, HashMap<String, String>> map = getTable16(tableName, conn);
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
	 * 查询表中的所有字段
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	public static List<String> getField16(String tableName, String ip, String dataBase) {

		if (tableName.contains("[")) {
			tableName = tableName.split("\\[")[3].replace("]", "");
		}
		HashMap<String, HashMap<String, String>> map = getTable16(tableName, ip, dataBase);
		List<String> filedList = new ArrayList<String>();
		for (String key : map.keySet()) {
			HashMap<String, String> map1 = map.get(key);
			for (String k1 : map1.keySet()) {
				//System.out.println(key + ":" + k1 + ":" + map1.get(k1));
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
	 * 根据传入的table名称获取该表在SQLServer中的列名和其数据长度
	 * 
	 * @param table
	 * @return 返回tableName<colName，length>
	 */
	public static HashMap<String, HashMap<String, String>> getTable(String tableName, Connection conn) {

		HashMap<String, HashMap<String, String>> tableMap = new HashMap<String, HashMap<String, String>>();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableMap;
	}

	/**
	 * 根据传入的table名称获取该表在SQLServer中的列名和其数据长度
	 * 
	 * @param table
	 * @return 返回tableName<colName，length>
	 */
	public static HashMap<String, HashMap<String, String>> getTable16(String tableName) {

		HashMap<String, HashMap<String, String>> tableMap = new HashMap<String, HashMap<String, String>>();
		try {
			Connection conn = ConnectSqlServer16.getOneConn();
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
			//ConnectSqlServer16.push(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableMap;
	}

	public static HashMap<String, HashMap<String, String>> getTable16(String tableName, String ip,
			String dataBaseName) {

		HashMap<String, HashMap<String, String>> tableMap = new HashMap<String, HashMap<String, String>>();
		try {
			Connection conn = ConnectSqlServer16.getOneConn(ip, dataBaseName);
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
			//ConnectSqlServer16.push(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableMap;
	}

	public static HashMap<String, HashMap<String, String>> getTable16(String tableName, Connection conn) {

		HashMap<String, HashMap<String, String>> tableMap = new HashMap<String, HashMap<String, String>>();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableMap;
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
		boolean isTrun = false;
		try {
			String sql = "TRUNCATE TABLE " + tableName;
			
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			pst.executeUpdate();
			isTrun = true;
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrun;
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
		Connection conn = ConnectSqlServer16.getOneConn("192.168.0.16", "skyscope");
		try {
			// EXEC sp_rename '[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]','PRICE_PRODUCT_LIST_half_copy_20180920';
			// select * into [skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy] from [skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy_20180920] WHERE  1=2
			// DROP TABLE [skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy_20180917]
			
			String name = tableName.substring(18, tableName.length()-1);
			String sql = " EXEC sp_rename '"+tableName+"','"+name+"_"+dateStr+"';"+
				" select * into "+tableName+" from "+tableName.substring(0, tableName.length()-1)+"_"+dateStr+"] WHERE 1=2;"+
				" DROP TABLE "+tableName.substring(0, tableName.length()-1)+"_"+dateStr1+"];";
			//String sql = "DELETE FROM " + tableName + " WHERE get_date < '" + dateStr + "'";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			pst.executeUpdate();
			isTrun = true;
			pst.close();
			ConnectSqlServer16.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrun;
	}
	
	/**
	 * 删除数据
	 * 备份巡检中间结果
	 * @param tableName
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean deleteTbaleData(String tableName, String dateStr1, String dateStr, Connection conn16) {
		boolean isTrun = false;
		Connection conn = ConnectSqlServer16.getOneConn("192.168.0.16", "skyscope");
		try {
			// EXEC sp_rename '[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]','PRICE_PRODUCT_LIST_half_copy_20180920';
			// select * into [skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy] from [skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy_20180920] WHERE  1=2
			// DROP TABLE [skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy_20180917]
			
			String name = tableName.substring(18, tableName.length()-1);
			String sql = " EXEC sp_rename '"+tableName+"','"+name+"_"+dateStr+"';"+
				" select * into "+tableName+" from "+tableName.substring(0, tableName.length()-1)+"_"+dateStr+"] WHERE 1=2;"+
				" DROP TABLE "+tableName.substring(0, tableName.length()-1)+"_"+dateStr1+"];";
			//String sql = "DELETE FROM " + tableName + " WHERE get_date < '" + dateStr + "'";
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			pst.executeUpdate();
			isTrun = true;
			pst.close();
			ConnectSqlServer16.push(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrun;
	}

}
