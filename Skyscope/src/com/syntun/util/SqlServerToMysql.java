package com.syntun.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSqlServer;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData73;


public class SqlServerToMysql {

	/**
	 * 补充价格数据sql集合
	 */
	public static List<String> Mysql = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		String a = "815259897,3,null,1061,null,20331,电水壶/热水瓶,家用电器,厨房小电,1.2,0070163191,null,null,null,null,null,null,null,null,null";
		System.out.println(a.split(",").length);
		//HalfResult();
	}
	
	public static void HalfResult() {
		/**
		 * 补充数据sql集合
		 */
		Mysql = new ArrayList<String>();
		
		Connection conn15 = ConnectSqlServer.getConn();
		// 读取补充库中的数据
		List<HashMap<String, String>> shopListData = BaseDaoSqlServer
				.getTbaleAllData("[syntun_base].[dbo].[url_total_list]", conn15);
		
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
		
		ConnectSqlServer.push(conn15);
		
		//价格数据整理
		for (HashMap<String, String> map : shopListData) {
			HashMap<String, String> stockKeyMap = new HashMap<String, String>();
			for (String k : map.keySet()) {
				stockKeyMap.put(k, map.get(k));
			}
			
			String sql = ConvertSql.getSql("syntun_comment_no.url_total_list", fieldList, stockKeyMap);
			Mysql.add(sql);
		}
		
	
		System.out.println("数据条数： "+ Mysql.size() + " ===");
		
		Thread t1 = new Thread(new InsertData73(Mysql));
		t1.start();
		
		boolean isA = true;
		while(isA){
			if(!t1.isAlive()){
				System.out.println("====执行结束====");
				isA = false;
			}
		}
	}
}
