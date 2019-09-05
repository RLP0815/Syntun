package com.syntun.collectQC;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConnectSql131;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData132;

public class CollectProductList {
	/**
	 * 结果sql集合
	 */
	//public static List<String> lostResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Please tell me dataBase.tableName and getDate!");
			System.exit(0);
		}
		String tableNameAll = args[0];
		String getDate = args[1];
//		String tableNameAll = "wgdata_suning.comment_total,wgdata_suning.product_price_list";
//		String getDate = "2019-07-30";
		getResult(tableNameAll, getDate);
	}
	public static void getResult(String tableNameAll, String getDate) {
		/**
		 * 结果sql集合
		 */
		List<String> lostResult = new ArrayList<String>();
		tableNameAll = tableNameAll.replace("，", ",");
		Connection conn131 = ConnectSql131.getConn();
		List<String> tableCountFiled = new ArrayList<String>();
		tableCountFiled.add("data_base");
		tableCountFiled.add("table_name");
		tableCountFiled.add("unique_key");
		tableCountFiled.add("unique_val");
		tableCountFiled.add("lose_date");
		tableCountFiled.add("check_date");

		List<String> lostResultBak = new ArrayList<String>();
		Set<String> sortProductList = new HashSet<String>();
		Set<String> productPriceList = new HashSet<String>();
		Set<String> productUrlErr = new HashSet<String>();
		String dataBaseLast = null;
		for(int i = 0; i <tableNameAll.split(",").length; i++){
			String tableName = tableNameAll.split(",")[i];
			System.out.println("开始检查表："+tableName);
			String dataBase = tableName.split("\\.")[0];
			lostResultBak.clear();
			productPriceList.clear();
			if (dataBaseLast==null || (dataBaseLast!=null && !dataBase.equals(dataBaseLast))){
				sortProductList.clear();
				productUrlErr.clear();
			}
			
			HashMap<String, String> filterD = new HashMap<String, String>();
	        filterD.put("lose_date", getDate);
	        filterD.put("data_base", dataBase);
	        filterD.put("table_name", tableName.split("\\.")[1]);
	        BaseDao.deleteTable("xitong.table_data_list", filterD);
	        
			// 读取价格条件
			HashMap<String, String> filter = new HashMap<String, String>();
			List<String> keyList = new ArrayList<String>();
			//不同库匹配唯一条件不同
			if(dataBase.equals("wgdata_BenLaiShenghuo") || dataBase.equals("wgdata_jianyiwang")){
				keyList.add("product_id");
				keyList.add("sku_id");
			}else if(dataBase.equals("wgdata_suning")){
				keyList.add("partNumber_id");
				keyList.add("shop_code");
			}else{
				keyList.add("product_id");
			}
			if (dataBaseLast==null || (dataBaseLast!=null && !dataBase.equals(dataBaseLast))){
				if(dataBase.equals("wgdata_suning")){
					sortProductList = BaseDao.getAllList(dataBase+".sort_product_detail", filter, keyList, conn131);
				}else{
					sortProductList = BaseDao.getAllList(dataBase+".sort_product_list", filter, keyList, conn131);
				}
			}
			
			filter.put("get_date", getDate);
			productPriceList = BaseDao.getAllList(tableName, filter, keyList, conn131);
			
			//Set<String> productUrlErr = new HashSet<String>();
			//部分库需要匹配product_url_err表的值
			if(dataBase.equals("wgdata_gome") || dataBase.equals("wgdata_kaola") || dataBase.equals("wgdata_pinduoduo") || 
			dataBase.equals("wgdata_suning") || dataBase.equals("wgdata_taobao") || dataBase.equals("wgdata_tmall") || 
			dataBase.equals("wgdata_363haoyao") || dataBase.equals("wgdata_jingdong")){
				if (dataBaseLast==null || (dataBaseLast!=null && !dataBase.equals(dataBaseLast))){
					productUrlErr = BaseDao.getAllList(dataBase+".product_url_err", filter, keyList, conn131);
				}
			}
			dataBaseLast = dataBase;
			for(String key : sortProductList){
				if(!productPriceList.contains(key) && !productUrlErr.contains(key)){
					HashMap<String, String> fieldMap = new HashMap<String, String>();
					fieldMap.put("data_base", dataBase);
					fieldMap.put("table_name", tableName.split("\\.")[1]);
					//不同库匹配唯一条件不同
					if(dataBase.equals("wgdata_BenLaiShenghuo") || dataBase.equals("wgdata_jianyiwang")){
						fieldMap.put("unique_key", "product_id,sku_id");
					}else if(dataBase.equals("wgdata_suning")){
						fieldMap.put("unique_key", "partNumber_id,shop_code");
					}else{
						fieldMap.put("unique_key", "product_id");
					}
					fieldMap.put("unique_val", key);
					fieldMap.put("lose_date", getDate);
					fieldMap.put("check_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
						
					String mysql = ConvertSql.getSql("xitong.table_data_list", tableCountFiled, fieldMap);
					lostResultBak.add(mysql);
					if(lostResultBak.size() == 20){
						break;
					}
				}
			}
			lostResult.addAll(lostResultBak);
		}

		ConnectSql131.push(conn131);

		System.out.println("开始插入，总共插入数据条数："+lostResult.size());
		Thread t1 = new Thread(new InsertData132(lostResult));
		t1.start();
		while (t1.isAlive()) {
		}
		System.exit(0);
	}
}
