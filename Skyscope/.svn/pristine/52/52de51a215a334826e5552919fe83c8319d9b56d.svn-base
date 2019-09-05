package com.syntun.philips;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.syntun.etl.tools.BaseConnectSql;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSqlServer16;
import com.syntun.etl.tools.ConvertSql;

/*
 * 飞利浦，名称判定
 */
public class NameDecide {

	/**
	 * 名称判定结果sql集合
	 */
	public static List<String> skuNameResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		// 数据日期
		String dateStr = "2018-08-14";
		NameDecide.getResult(dateStr);
		System.exit(0);
	}
	public static void getResult(String dateStr) {
		/**
		 * 店铺得分结果sql集合
		 */
		skuNameResult = new ArrayList<String>();
		
		String sqlName = "shuju";
		String sqlPassWord = "shuju";
		String sqlDbName = "wgdata_tmall";
		//String sqlHost = "192.168.0.112:3306";
		String sqlHost = "192.168.0.61:3306";
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn112 = BaseConnectSql.getConn(sqlHost, sqlDbName, sqlName, sqlPassWord);
		// 16数据库，数据库连接外传，便于读取数据代码复用
		Connection conn16 = ConnectSqlServer16.getConn();
		
		HashMap<String, List<HashMap<String, String>>> operationProudctData = new HashMap<String, List<HashMap<String, String>>>();
		
		// 生成组合主键的列集合
		List<String> keyList = new ArrayList<String>();
		keyList.add("product_id");
		// 读取条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("get_date", dateStr);
		// 抓取天猫平台数据
		HashMap<String, List<HashMap<String, String>>> tmallNameData = BaseDao
				.getAllTbalePriceList("wgdata_tmall.product_source", filter, keyList, conn112);
		
		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("product_id");
		// 读取条件
		HashMap<String, String> filter1 = new HashMap<String, String>();
		filter1.put("get_date", dateStr);
		// 抓取京东平台数据
		HashMap<String, List<HashMap<String, String>>> jingdongNameData = BaseDao
				.getAllTbalePriceList("wgdata_jingdong.product_name", filter1, keyList1, conn112);
		
		//合并原始数据
		operationProudctData.putAll(tmallNameData);
		operationProudctData.putAll(jingdongNameData);
		
		// 生成组合主键的列集合
		List<String> keyList2 = new ArrayList<String>();
		keyList2.add("product_id");
		// 读取条件
		HashMap<String, String> filter2 = new HashMap<String, String>();
		// 抓取名称标准数据
		HashMap<String, List<HashMap<String, String>>> subNameData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[sub_name]", filter2, keyList2, conn16);
		
		// 生成组合主键的列集合
		List<String> keyList3 = new ArrayList<String>();
		keyList3.add("shop_id");
		// 读取条件
		HashMap<String, String> filter3 = new HashMap<String, String>();
		// 抓取店铺标准数据
		HashMap<String, List<HashMap<String, String>>> authorizedShopData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[authorized_shop]", filter3, keyList3, conn16);
		
		// 生成主键的列集合
		LinkedList<String> keyListPP = new LinkedList<String>();
		keyListPP.add("OPERATION_PRODUCT_ID");
		keyListPP.add("PRODUCT_ID");
		keyListPP.add("SHOP_ID");
		keyListPP.add("PLATFORM_ID");
		keyListPP.add("CATEGORY_ID");
		// 读取补充库中的数据
		HashMap<String, String> productPlatform = BaseDaoSqlServer
				.getTbaleDataD("[skyscope].[dbo].[PRODUCT_PLATFORM_LIST]", keyListPP, "SKU_ID", dateStr, conn16);
		
		List<String> nameFiledList = BaseDaoSqlServer.getField16("[syntun_namingjudge].[dbo].[SKU_NAME_TEMP]");
		
		BaseConnectSql.push(conn112);
		ConnectSqlServer16.push(conn16);
		
		for(String k : productPlatform.keySet()){
			HashMap<String, String> nameKeyMap = new HashMap<String, String>();
			//System.out.println(subNameData.get(k.split("\001")[1]));
			String subName = null;
			String subCategory = null;
			if(subNameData.get(k.split("\001")[1]) != null){
				subName = subNameData.get(k.split("\001")[1]).get(0).get("sub_name_nog");
				subCategory = subNameData.get(k.split("\001")[1]).get(0).get("sub_category");
			}else{
				continue;
			}
			
			String region = null;
			String pLevel = null;
			if(authorizedShopData.get(k.split("\001")[2]) != null){
				region = authorizedShopData.get(k.split("\001")[2]).get(0).get("region");
				pLevel = authorizedShopData.get(k.split("\001")[2]).get(0).get("p_level");
			}else{
				continue;
			}
			
			nameKeyMap.put("operation_product_id", k.split("\001")[0]);
			nameKeyMap.put("product_id", k.split("\001")[1]);
			nameKeyMap.put("shop_id", k.split("\001")[2]);
			nameKeyMap.put("platform_id", k.split("\001")[3]);
			nameKeyMap.put("category_id", k.split("\001")[4]);
			nameKeyMap.put("get_date", dateStr);
			nameKeyMap.put("product_name", operationProudctData.get(k.split("\001")[0]).get(0).get("product_name"));
			nameKeyMap.put("sub_name", subName);
			nameKeyMap.put("sub_category", subCategory);
			nameKeyMap.put("region", region);
			nameKeyMap.put("p_level", pLevel);
			
			String sql = ConvertSql.getSqlServer("[syntun_namingjudge].[dbo].[SKU_NAME_TEMP_COPY]", nameFiledList, nameKeyMap);
			//System.out.println(tmallStockXiajia.size() + "___" + sql);
			skuNameResult.add(sql);
		}
		System.out.println("---店铺评分数据条数： "+ skuNameResult.size() + " ===");
//		for(String price : skuNameResult) {
//			System.out.println(price);
//		}
//			
//		Thread t1 = new Thread(new InsertDataSqlServer(skuNameResult));
//		t1.start();
//		while(t1.isAlive()){
//			
//		}
	}
}
