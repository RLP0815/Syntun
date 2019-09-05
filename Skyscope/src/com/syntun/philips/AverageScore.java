package com.syntun.philips;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.syntun.etl.tools.BaseConnectSql;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSqlServer16;
import com.syntun.etl.tools.ConvertSql;

public class AverageScore {
	/**
	 * 	情感得分结果sql集合
	 */
	public static List<String> averageScoreResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		// 数据日期
		String dateStr = "2018-08-15";
		AverageScore.getResult(dateStr);
		System.exit(0);
	}
	public static void getResult(String dateStr) {
		/**
		 * 情感得分结果sql集合
		 */
		averageScoreResult = new ArrayList<String>();
		
		String sqlName = "shuju";
		String sqlPassWord = "shuju";
		String sqlDbName = "semantic";
		//String sqlHost = "192.168.0.133:3306";
		String sqlHost = "192.168.0.72:3306";
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn133 = BaseConnectSql.getConn(sqlHost, sqlDbName, sqlName, sqlPassWord);
		// 16数据库，数据库连接外传，便于读取数据代码复用
		Connection conn16 = ConnectSqlServer16.getConn();
		
		// 生成组合主键的列集合
		List<String> keyList = new ArrayList<String>();
		keyList.add("URL_ID");
		//keyList.add("CREATE_AT");
		
		// 读取条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("CREATE_AT", dateStr + " 10:30:00");
		filter.put("UID", "419");
		// 读取
		HashMap<String, List<HashMap<String, String>>> patrolData = BaseDaoSqlServer
				.getInspectionAllData("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF]", filter, keyList, conn16);
		
		//System.out.println(patrolData);
		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("platform_id");
		keyList1.add("operation_product_id");
		keyList1.add("PRODCT_COMMENT_DATE");
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		String commentDate = null;
		try {
			commentDate = df.format(new Date(df.parse(dateStr).getTime() - 1 * 24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 读取条件
		HashMap<String, String> filter1 = new HashMap<String, String>();
		filter1.put("PRODCT_COMMENT_DATE", commentDate);
		// 抓取天猫平台数据
		HashMap<String, List<HashMap<String, String>>> commentData = BaseDao
				.getAllTbalePriceList("semantic.comment_qg", filter1, keyList1, conn133);
		
		List<String> averageFiledList = BaseDaoSqlServer.getField16("[skyscope_temp].[dbo].[AVERAGE_SCORE_DAY]");
		
		BaseConnectSql.push(conn133);
		ConnectSqlServer16.push(conn16);
		
		for(String k : commentData.keySet()){
			String platformId = k.split("\001")[0];
			String urlId = k.split("\001")[1];
			//String comDate = k.split("\001")[2];
			if (!patrolData.containsKey(urlId)){
				continue;
			}
			double score = 0;
			for (HashMap<String, String> commentMap : commentData.get(k)) {
				if(commentMap.get("score").indexOf("-") != -1){
					score = score + 0;
				}else{
					score = score + Double.parseDouble(commentMap.get("score"));
				}
			}
			if(score != 0){
				score = score/commentData.get(k).size()*5;
			}
			DecimalFormat dft=new DecimalFormat(".00");
			List<HashMap<String, String>> patorlList = patrolData.get(urlId);
			HashMap<String, String> averageKeyMap = new HashMap<String, String>();
			
			averageKeyMap.put("platform_id", platformId);
			averageKeyMap.put("product_id", patorlList.get(0).get("PRODUCT_ID"));
			averageKeyMap.put("category_id", patorlList.get(0).get("CATEGORY_ID"));
			averageKeyMap.put("product_name", patorlList.get(0).get("PRODUCT_NAME"));
			averageKeyMap.put("score", dft.format(score));
			averageKeyMap.put("category_name", patorlList.get(0).get("CATEGORY_NAME"));
			averageKeyMap.put("platform_name", patorlList.get(0).get("PLATFORM_NAME"));
			averageKeyMap.put("product_comment_score", dft.format(score));
			averageKeyMap.put("shop_id", patorlList.get(0).get("SHOP_ID"));
			averageKeyMap.put("product_comment_date", dateStr);
			averageKeyMap.put("operation_product_id", urlId);
			
			String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[AVERAGE_SCORE_DAY_COPY]", averageFiledList, averageKeyMap);
			//System.out.println(tmallStockXiajia.size() + "___" + sql);
			averageScoreResult.add(sql);
			
		}
		System.out.println("---店铺评分数据条数： "+ averageScoreResult.size() + " ===");
//		for(String price : averageScoreResult) {
//			System.out.println(price);
//		}
//			
//		Thread t1 = new Thread(new InsertDataSqlServer(averageScoreResult));
//		t1.start();
//		while(t1.isAlive()){
//			
//		}
	}
}
