package com.syntun.philips;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.syntun.etl.tools.BaseConnectSql;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSqlServer16;
import com.syntun.etl.tools.ConvertSql;

/*
 * 飞利浦，店铺得分
 */
public class ShopScore {

	/**
	 * 店铺得分结果sql集合
	 */
	public static List<String> shopScoreResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		// 数据日期
		String dateStr = "2018-08-14";
		ShopScore.getResult(dateStr);
		System.exit(0);
	}
	public static void getResult(String dateStr) {
		/**
		 * 店铺得分结果sql集合
		 */
		shopScoreResult = new ArrayList<String>();
		
		String sqlName = "shuju";
		String sqlPassWord = "shuju";
		String sqlDbName = "wgdata_tmall";
		//String sqlHost = "192.168.0.112:3306";
		String sqlHost = "192.168.0.61:3306";
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn112 = BaseConnectSql.getConn(sqlHost, sqlDbName, sqlName, sqlPassWord);
		// 16数据库，数据库连接外传，便于读取数据代码复用
		Connection conn16 = ConnectSqlServer16.getConn();
		
		// 生成组合主键的列集合
		List<String> keyList = new ArrayList<String>();
		keyList.add("shop_code");
		// 读取价格条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("get_date", dateStr);
		// 抓取评分
		HashMap<String, List<HashMap<String, String>>> shopScoreData = BaseDao
				.getAllTbalePriceList("wgdata_tmall.shop_Score_info", filter, keyList, conn112);
		
		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("网页上shop_id");
		// 读取价格条件
		HashMap<String, String> filter1 = new HashMap<String, String>();
		// 读取标准店铺
		HashMap<String, List<HashMap<String, String>>> authorizedData = BaseDaoSqlServer
				.getTbaleAllData16("[skyscope].[dbo].[authorized_shop]", filter1, keyList1, conn16);
		
		List<String> shopFiledList = BaseDaoSqlServer.getField16("SHOP_SCORE_MX_temp");
		
		BaseConnectSql.push(conn112);
		ConnectSqlServer16.push(conn16);
		
		HashMap<String, List<HashMap<String, String>>> shopData = new HashMap<String, List<HashMap<String, String>>>();
		
		for(String k : shopScoreData.keySet()){
			if(authorizedData.containsKey(k)){
				HashMap<String, String> authorizedMap = authorizedData.get(k).get(0);
				for (HashMap<String, String> shopScoreMap : shopScoreData.get(k)) {
					List<HashMap<String, String>> li = null;
					String key = shopScoreMap.get("get_date") + "\001" + authorizedMap.get("网页上shop_id") + "\001" + 
							authorizedMap.get("shop_id") + "\001" + authorizedMap.get("shop_name") + "\001" +
							authorizedMap.get("syntun_shop_name") + "\001" + authorizedMap.get("region") + "\001" +
							authorizedMap.get("p_level") + "\001" + authorizedMap.get("platform_id");
					if (shopData.containsKey(key)) {
						li = shopData.get(key);
					} else {
						li = new ArrayList<HashMap<String, String>>();
					}
					HashMap<String, String> maps = new HashMap<String, String>();
					maps.put("product_score", shopScoreMap.get("product_score"));
					maps.put("service_score", shopScoreMap.get("service_score"));
					maps.put("logistics_score", shopScoreMap.get("logistics_score"));
					li.add(maps);
					shopData.put(key, li);
				}
			}
		}
		
		for(String k : shopData.keySet()){
			double productScore = 0;
			double serviceScore = 0;
			double logisticsScore = 0;
			int size = shopData.get(k).size();
			for (HashMap<String, String> ScoreMap : shopData.get(k)) {
				productScore = productScore + Double.parseDouble(ScoreMap.get("product_score"));
				serviceScore = serviceScore + Double.parseDouble(ScoreMap.get("service_score"));
				logisticsScore = logisticsScore + Double.parseDouble(ScoreMap.get("logistics_score"));
			}
			
			DecimalFormat df=new DecimalFormat(".00");
			HashMap<String, String> shopKeyMap = new HashMap<String, String>();
			
			shopKeyMap.put("监测日期", k.split("\001")[0]);
			shopKeyMap.put("监测时间", "10:30:00");
			shopKeyMap.put("网站名称", "天猫");
			shopKeyMap.put("店铺级别", k.split("\001")[6]);
			shopKeyMap.put("店铺名称", k.split("\001")[3]);
			shopKeyMap.put("region", k.split("\001")[5]);
			shopKeyMap.put("描述相符", df.format(productScore/size));
			shopKeyMap.put("服务相符", df.format(serviceScore/size));
			shopKeyMap.put("物流相符", df.format(logisticsScore/size));
			shopKeyMap.put("shop_id", k.split("\001")[1]);

			String sql = ConvertSql.getSqlServer("[dbo].[SHOP_SCORE_MX_temp_COPY]", shopFiledList, shopKeyMap);
			//System.out.println(tmallStockXiajia.size() + "___" + sql);
			shopScoreResult.add(sql);
		}
		
		System.out.println("---店铺评分数据条数： "+ shopScoreResult.size() + " ===");
		for(String price : shopScoreResult) {
			System.out.println(price);
		}
			
//		Thread t1 = new Thread(new InsertDataSqlServer(shopScoreResult));
//		t1.start();
//		while(t1.isAlive()){
//			
//		}
	}
}
