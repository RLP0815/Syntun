package com.syntun.philips;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSql60;
import com.syntun.etl.tools.ConnectSqlServer16;
import com.syntun.etl.tools.ConvertSql;

/*
 * 飞利浦，库存上下架
 */
public class StockStatus {

	/**
	 * 名称判定结果sql集合
	 */
	public static List<String> stockStatusResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		// 数据日期
		String dateStr = "2018-08-20";
		StockStatus.getResult(dateStr);
		System.exit(0);
	}
	public static void getResult(String dateStr) {
		/**
		 * 店铺得分结果sql集合
		 */
		stockStatusResult = new ArrayList<String>();
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn60 = ConnectSql60.getConn();
		// 16数据库，数据库连接外传，便于读取数据代码复用
		Connection conn16 = ConnectSqlServer16.getConn();
		
		// 生成组合主键的列集合
		List<String> keyList = new ArrayList<String>();
		keyList.add("operation_product_id");
		keyList.add("shop_id");
		keyList.add("platform_id");
		// 读取条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("get_date", dateStr);
		filter.put("hour", "10");
		filter.put("min", "45");
		// 抓取产品库存数据
		HashMap<String, List<HashMap<String, String>>> productStockData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[shop_product_Stock_list]", filter, keyList, conn16);
		
		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("OPERATION_PRODUCT_ID");
		keyList1.add("SHOP_ID");
		keyList1.add("PLATFORM_ID");
		keyList1.add("CITY_ID");
		// 读取条件
		HashMap<String, String> filter1 = new HashMap<String, String>();
		filter1.put("GET_DATE", dateStr);
		filter1.put("HOUR", "10");
		filter1.put("MIN", "45");
		// 产品下架数据
		HashMap<String, List<HashMap<String, String>>> xiajiaTotalData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope_temp].[dbo].[XIAJIABIAO_TOTAL]", filter1, keyList1, conn16);
		
		// 生成组合主键的列集合
		List<String> keyList2 = new ArrayList<String>();
		keyList2.add("OPERATION_PRODUCT_ID");
		keyList2.add("SHOP_ID");
		keyList2.add("PLATFORM_ID");
		keyList2.add("CITY_ID");
		// 读取条件
		HashMap<String, String> filter2 = new HashMap<String, String>();
		// 抓取产品标准数据
		HashMap<String, List<HashMap<String, String>>> productListData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope_temp].[dbo].[PRODUCT_PLATFORM_LIST_TEMP]", filter2, keyList2, conn16);
		
		// 生成主键的列集合
		LinkedList<String> keyListPP = new LinkedList<String>();
		keyListPP.add("OPERATION_PRODUCT_ID");
		keyListPP.add("SHOP_ID");
		keyListPP.add("PLATFORM_ID");
		keyListPP.add("PRODUCT_ID");
		//keyListPP.add("CATEGORY_ID");
		// 读取补充库中的数据
		HashMap<String, String> productPlatform = BaseDaoSqlServer
				.getTbaleDataD("[skyscope].[dbo].[PRODUCT_PLATFORM_LIST]", keyListPP, "SKU_ID", dateStr, conn16);
		
		// 读取补充库中的数据
		HashMap<String, String> productStatus = BaseDao.getProductStatus("skyscope_etl.product_status", conn60);
						
		List<String> statusFiledList = BaseDaoSqlServer.getField16("[skyscope_temp].[dbo].[STATUS]");
		
		ConnectSql60.push(conn60);
		ConnectSqlServer16.push(conn16);
		
		HashMap<String, List<HashMap<String, String>>> statusDate = new HashMap<String, List<HashMap<String, String>>>();
		
		for(String k : productStockData.keySet()){
			List<HashMap<String, String>> statusList1 = new ArrayList<HashMap<String, String>>();
			for(HashMap<String, String> productStockMap : productStockData.get(k)){
				if(productStockMap.get("stock") != null && productStockMap.get("stock").equals("0")){
					productStockMap.put("error_connect", "无货");
					if(productStockMap.get("platform_id").equals("0")){
						productStockMap.put("platform_id", "5");
					}
					if(productStockMap.get("sku_id") == null){
						productStockMap.put("sku_id", "0");
					}
					statusList1.add(productStockMap);
				}
			}
			if(statusList1.size()!=0){
				if(k.split("\001")[2].equals("0")){
					k = k.split("\001")[0]+"\001"+k.split("\001")[1]+"\001"+"5";
				}
				statusDate.put(k, statusList1);
			}
			
		}
		
		for(String k : productListData.keySet()){
			String platformId = k.split("\001")[2];
			if(xiajiaTotalData.containsKey(k)
					&& (platformId.equals("1")||platformId.equals("3")||platformId.equals("5")||platformId.equals("10"))){
				HashMap<String, String> xiajiaTotalMap = xiajiaTotalData.get(k).get(0);
				String errorConnect = productStatus.get(xiajiaTotalMap.get("PLATFORM_ID")+"\001"+xiajiaTotalMap.get("ERROR_CONNECT"));
				if(!errorConnect.equals("坏地址") && xiajiaTotalMap.get("ERROR_CONNECT") != null){
					List<HashMap<String, String>> statusList2 = new ArrayList<HashMap<String, String>>();
					for(HashMap<String, String> productListMap : productListData.get(k)){
						productListMap.put("error_connect", errorConnect);
						statusList2.add(productListMap);
					}
					if(statusList2.size()!=0){
						statusDate.put(k, statusList2);
					}
				}
				
			}
		}
		
		for(String k : productPlatform.keySet()){
			String key = k.split("\001")[0]+"\001"+k.split("\001")[1]+"\001"+k.split("\001")[2];
			HashMap<String, String> statusKeyMap = new HashMap<String, String>();
			if(statusDate.containsKey(key)){
				String errorConnect = statusDate.get(key).get(0).get("error_connect");
				
				statusKeyMap.put("operation_product_id", k.split("\001")[0]);
				statusKeyMap.put("shop_id", k.split("\001")[1]);
				statusKeyMap.put("platform_id", k.split("\001")[2]);
				statusKeyMap.put("product_id", k.split("\001")[3]);
				statusKeyMap.put("get_date", dateStr);
				statusKeyMap.put("error_connect", errorConnect);
				statusKeyMap.put("sku_id", productPlatform.get(k));
				
				String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[STATUS]", statusFiledList, statusKeyMap);
				//System.out.println(tmallStockXiajia.size() + "___" + sql);
				stockStatusResult.add(sql);
			}else{
				statusKeyMap.put("operation_product_id", k.split("\001")[0]);
				statusKeyMap.put("shop_id", k.split("\001")[1]);
				statusKeyMap.put("platform_id", k.split("\001")[2]);
				statusKeyMap.put("product_id", k.split("\001")[3]);
				statusKeyMap.put("get_date", dateStr);
				statusKeyMap.put("error_connect", "在架");
				statusKeyMap.put("sku_id", productPlatform.get(k));
				
				String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[STATUS]", statusFiledList, statusKeyMap);
				//System.out.println(tmallStockXiajia.size() + "___" + sql);
				stockStatusResult.add(sql);
			}
			
		}
		
		System.out.println("---库存上下架数据条数： "+ stockStatusResult.size() + " ===");
//		for(String price : stockStatusResult) {
//			System.out.println(price);
//		}
//			
//		Thread t1 = new Thread(new InsertDataSqlServer(stockStatusResult));
//		t1.start();
//		while(t1.isAlive()){
//			
//		}
	}
}
