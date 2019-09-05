package com.syntun.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData60;

public class SortProduct {
	/**
	 * 匹配结果sql集合
	 */
	public static List<String> productResult = new ArrayList<String>();
	/**
	 * 未匹配结果sql集合
	 */
	public static List<String> productCopyResult = new ArrayList<String>();
	
	public static void main(String[] args) {
		/*
		 * 平台：考拉：49；唯品会：45；
		 * 唯品会 platform_id=45 shop_id=88589
		 * 考拉 platform_id=49  shop_id=93470
		 */
		String platformId = "45";
		String dateStr = "2018-06-26";
		String tableName = "PRODUCT_PLATFORM_LIST_STATUS";
		
		SortProduct.getResult(tableName, dateStr, platformId);
		
		
	}
	
	public static void getResult(String tableName, String dateStr, String platformId) {
		// 结果表集合
		HashMap<String, String> sortProduct = new HashMap<String, String>();
		String shopId = "";
		if(platformId.equals("45")){
			shopId = "88589";
			//获取唯品会结果表集合
			sortProduct = BaseDao.getSTbaleList(dateStr);
		}else if(platformId.equals("49")){
			shopId = "93470";
			//获取网易考拉结果表集合
			sortProduct = BaseDao.getSTbaleList1(dateStr);
		}
		//获取配置表集合
		HashMap<String, String> productFilter = BaseDao.getFTbaleList();
		System.out.println(productFilter.size()+"sortProduct_______"+sortProduct.size());

		//获取表格所有字段，匹配结果表和未匹配结果表格式一样
		List<String> productList = BaseDao.getField(tableName);
		productList.remove("id");

		
		for(String key : sortProduct.keySet()){
		  HashMap<String, String> keyMap = new HashMap<String, String>(); 
		  String value = sortProduct.get(key);
		  // System.out.println(key+":"+value);
		   boolean isP = false;
		   if(platformId.equals("49") && (value.contains("2罐") || value.contains("3罐") || value.contains("4罐") 
				   || value.contains("2瓶") || value.contains("3瓶") || value.contains("4瓶")
				   || value.contains("2件") || value.contains("3件") || value.contains("4件"))
				   && !(value.contains("赠2罐") || value.contains("赠3罐") || value.contains("赠4罐") 
					|| value.contains("赠2瓶") || value.contains("赠3瓶") || value.contains("赠4瓶")
					|| value.contains("赠2件") || value.contains("赠3件") || value.contains("赠4件"))){
			   
			   isP = true;
			   //break;
		   }
		   if(!isP){
		   for(String key1 : productFilter.keySet()){
			   boolean status = true;
			   if (productFilter.get(key1).contains("-----")){
				   String[] fil = productFilter.get(key1).split("001/");
				   for (int i=0; i<fil.length; i++){
					   if(fil[i].contains("-----")){
						   String[] fils = fil[i].split("-----"); 
						   if(fils.length == 2){
							   if(!value.contains(fils[0]) && !value.contains(fils[1])){
								   status = false;
								   isP = true;
							   }
						   }else{
							   if(!value.contains(fils[0]) && !value.contains(fils[1])  && !value.contains(fils[2])){
								   status = false;
								   isP = true;
							   }
						   }
					   }else{
						   if(!value.contains(fil[i]) && !fil[i].equals("null")){
							   status = false;
							   isP = true;
						   }
					   }
					   
				   }
				   
			   }else{
				   String[] fil = productFilter.get(key1).split("001/");
				   for (int i=0; i<fil.length; i++){
					   if(!value.contains(fil[i]) && !fil[i].equals("null")){
						   status = false;
						   isP = true;
					   }
				   }
				   
			   }
			   if(status){
				   keyMap.put("PRODUCT_ID", key1.split("001/")[0]);
				   keyMap.put("PRODUCT_NAME", key1.split("001/")[1]);
				   keyMap.put("OLD_PRODUCT_NAME", sortProduct.get(key));
				   keyMap.put("status", "99");
				   keyMap.put("OPERATION_PRODUCT_ID", key);
				   keyMap.put("sku_id", "0");
				   keyMap.put("SHOP_ID", shopId);
				   keyMap.put("PLATFORM_ID", platformId);
				   keyMap.put("get_date", dateStr);
				   String sql = ConvertSql.getSql("PRODUCT_PLATFORM_LIST_STATUS", productList, keyMap);
				   productResult.add(sql);
				   
				   isP = false;
				   break;
			   }
		   }
		   }
		   if(isP){
			   keyMap.put("PRODUCT_ID", key);
			   keyMap.put("PRODUCT_NAME", sortProduct.get(key));
			   keyMap.put("OLD_PRODUCT_NAME", sortProduct.get(key));
			   keyMap.put("status", "99");
			   keyMap.put("OPERATION_PRODUCT_ID", key);
			   keyMap.put("sku_id", "0");
			   keyMap.put("SHOP_ID", shopId);
			   keyMap.put("PLATFORM_ID", platformId);
			   keyMap.put("get_date", dateStr);
			   String sql = ConvertSql.getSql("PRODUCT_PLATFORM_LIST_STATUS_COPY", productList, keyMap);
			   productCopyResult.add(sql);
		   }
		}
	
		Thread t1 = new Thread(new InsertData60(productResult));
		t1.start();
		Thread t2 = new Thread(new InsertData60(productCopyResult));
		t2.start();
		
	}
}
