package com.syntun.philips;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSql60;
import com.syntun.etl.tools.ConnectSqlServer;
import com.syntun.etl.tools.ConnectSqlServer16;
import com.syntun.etl.tools.ConvertSql;

public class PromotionSort {
	/**
	 * 	促销整理结果sql集合
	 */
	public static List<String> promotionSortResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		// 数据日期
		String dateStr = "2018-08-22";
		PromotionSort.getResult(dateStr);
		System.exit(0);
	}
	public static void getResult(String dateStr) {
		/**
		 * 促销整理结果sql集合
		 */
		promotionSortResult = new ArrayList<String>();
		
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn60 = ConnectSql60.getConn();
		// 16数据库，数据库连接外传，便于读取数据代码复用
		Connection conn15 = ConnectSqlServer.getConn();
		// 16数据库，数据库连接外传，便于读取数据代码复用
		Connection conn16 = ConnectSqlServer16.getConn();
		
		// 生成组合主键的列集合
		List<String> keyList = new ArrayList<String>();
		//keyList.add("promotion_type_name");
		//keyList.add("promotion_type_info");
		keyList.add("operation_product_id");
		keyList.add("product_id");
		keyList.add("platform_id");
		//keyList.add("shop_name");
		keyList.add("shop_id");
		keyList.add("city_id");
		//keyList.add("sku_id");
		
		// 读取条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("get_date", dateStr);
		filter.put("hour", "10");
		filter.put("min", "45");
		// 读取
		HashMap<String, List<HashMap<String, String>>> promotionData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[PRICE_PROMOTION_INFO_half]", filter, keyList, conn16);
		
		// 生成组合主键的列集合
		List<String> keyList0 = new ArrayList<String>();
		keyList0.add("operation_product_id");
		keyList0.add("product_id");
		keyList0.add("platform_id");
		//keyList.add("shop_name");
		keyList0.add("shop_id");
		keyList0.add("city_id");
		//keyList.add("sku_id");
		// 读取条件
		HashMap<String, String> filter0 = new HashMap<String, String>();
		filter0.put("get_date", dateStr);
		filter0.put("hour", "10");
		filter0.put("min", "45");
		filter0.put("platform_id", "5");
		// 读取
		HashMap<String, List<HashMap<String, String>>> productData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[PRICE_PRODUCT_LIST_half]", filter0, keyList0, conn16);
				
		
		String filters = "'3','10'";
		// 读取
		HashMap<String, String> shopData = BaseDaoSqlServer.
				getShopList("[syntun_base].[dbo].[shop_list]", filters, conn15);
						
		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("URL_ID");
		keyList1.add("PRODUCT_ID");
		keyList1.add("PLATFORM_ID");
		keyList1.add("SHOP_ID");
		keyList1.add("city_id");
		
		// 读取条件
		HashMap<String, String> filter1 = new HashMap<String, String>();
		filter1.put("CREATE_AT", dateStr + " 10:30:00");
		filter1.put("UID", "419");
		// 读取
		HashMap<String, List<HashMap<String, String>>> patrolData = BaseDaoSqlServer
				.getInspectionAllData("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF]", filter1, keyList1, conn16);
		
		// 生成组合主键的列集合
		List<String> keyList2 = new ArrayList<String>();
		keyList2.add("shop_id");
		// 读取价格条件
		HashMap<String, String> filter2 = new HashMap<String, String>();
		// 读取标准店铺
		HashMap<String, List<HashMap<String, String>>> authorizedData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[authorized_shop]", filter2, keyList2, conn16);
		
		HashMap<String, String> promotionReplaceMap = BaseDao.getPromotionReplace("skyscope_etl.promotion_replace", conn60);
				
		List<String> promotionFiledList = BaseDaoSqlServer.getField16("PROMOTION_TYPE_TEMP_DAY");
		
		ConnectSql60.push(conn60);
		ConnectSqlServer.push(conn15);
		ConnectSqlServer16.push(conn16);
		
		HashMap<String, List<HashMap<String, String>>> mergeData = new HashMap<String, List<HashMap<String, String>>>();
		
		List<HashMap<String, String>> mergeList = new ArrayList<HashMap<String, String>>();
		for (String k : promotionData.keySet()) {
			
			String platformId = k.split("\001")[2];

			if (platformId.equals("3") || platformId.equals("10")){
				for(HashMap<String, String> promotionMap : promotionData.get(k)){
					String shopName = promotionMap.get("shop_name");
					if (shopName != null && shopName.equals("苏宁")){
						k = k.split("\001")[0] + "\001" + 
								k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" + 
								shopData.get("苏宁自营") + "\001" + k.split("\001")[4];
						promotionMap.put("shop_name", "苏宁自营");
						promotionMap.put("shop_id", shopData.get("苏宁自营"));
					}else if(shopData.containsKey(shopName)){
						k = k.split("\001")[0] + "\001" + 
								k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" + 
								shopData.get(shopName) + "\001" + k.split("\001")[4];
						promotionMap.put("shop_id", shopData.get(shopName));
					}
					mergeList.add(promotionMap);
				}
				mergeData.put(k, mergeList);
			}else if (platformId.equals("1") || platformId.equals("5")){
				for(HashMap<String, String> promotionMap : promotionData.get(k)){
					mergeList.add(promotionMap);
				}
				mergeData.put(k, mergeList);
			}
		}
		
		for (String k : productData.keySet()) {
			String platformId = k.split("\001")[2];
			if (platformId.equals("5")){
				for(HashMap<String, String> productMap : productData.get(k)){
					String shopName = productMap.get("shop_name");
					if (shopName != null && !shopName.equals("无")){
						mergeList.add(productMap);
					}
				}
				mergeData.put(k, mergeList);
			}
		}
		
		for (String k : patrolData.keySet()) {
			String shopId = k.split("\001")[3];
			if (mergeData.containsKey(k)){
				if(authorizedData.containsKey(shopId)){
					for(HashMap<String, String> patrolMap : patrolData.get(k)){
						//String pronotionTypeName = k.split("\001")[0];
						String pronotionTypeName = mergeData.get(k).get(0).get("promotion_type_name");
						if(promotionReplaceMap.containsKey(pronotionTypeName)){
							pronotionTypeName = promotionReplaceMap.get(pronotionTypeName);
						}
						
						HashMap<String, String> promotionKeyMap = new HashMap<String, String>();
						
						promotionKeyMap.put("监测日期", dateStr);
						promotionKeyMap.put("监测时间", "10:30:00");
						promotionKeyMap.put("网站名称", patrolMap.get("platform_name"));
						promotionKeyMap.put("店铺名称", patrolMap.get("shop_name"));
						promotionKeyMap.put("region", authorizedData.get(shopId).get(0).get("region"));
						promotionKeyMap.put("p_level", authorizedData.get(shopId).get(0).get("p_level"));
						promotionKeyMap.put("是否授权或自营", "Y");
						promotionKeyMap.put("品类", patrolMap.get("category_name"));
						promotionKeyMap.put("型号", patrolMap.get("product_name"));
						promotionKeyMap.put("violation_price", patrolMap.get("violation_price"));
						promotionKeyMap.put("availability", "Y");
						promotionKeyMap.put("shop_id", shopId);
						promotionKeyMap.put("product_id", patrolMap.get("product_id"));
						promotionKeyMap.put("url_id", patrolMap.get("url_id"));
						
						if(pronotionTypeName != null){
							promotionKeyMap.put(pronotionTypeName, mergeData.get(k).get(0).get("promotion_type_info"));
						}
						
//						promotionKeyMap.put("淘抢购", );
//						promotionKeyMap.put("满减", );
//						promotionKeyMap.put("买赠", );
//						promotionKeyMap.put("返券", );
//						promotionKeyMap.put("积分", );
//						promotionKeyMap.put("换购", );
//						promotionKeyMap.put("淘金币", );
//						promotionKeyMap.put("淘绝对值", );
//						promotionKeyMap.put("团购聚划算", );
						
						promotionKeyMap.put("sku_id", patrolMap.get("sku_id"));
						promotionKeyMap.put("city_id", patrolMap.get("city_id"));
						promotionKeyMap.put("type", patrolMap.get("type"));
						
						String sql = ConvertSql.getSqlServer("[dbo].[PROMOTION_TYPE_TEMP_DAY_COPY]", promotionFiledList, promotionKeyMap);
						//System.out.println(tmallStockXiajia.size() + "___" + sql);
						promotionSortResult.add(sql);
					}
				}
			}
		}

		System.out.println("---促销整理数据条数： "+ promotionSortResult.size() + " ===");
//		for(String price : promotionSortResult) {
//			System.out.println(price);
//		}
			
//		Thread t1 = new Thread(new InsertDataSqlServer(promotionSortResult));
//		t1.start();
//		while(t1.isAlive()){
//			
//		}
		
	}
}
