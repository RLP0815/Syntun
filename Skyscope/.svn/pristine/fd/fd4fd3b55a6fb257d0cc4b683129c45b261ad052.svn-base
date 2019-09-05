package com.syntun.inspect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.syntun.etl.tools.ConvertSql;

public class ComputeManjian {

	public static HashMap<String, Double> reduceManjian(HashMap<String, Double> priceMap, List<HashMap<String, String>> MJpromotionMap, 
			HashMap<String, String> key, String groupId, String userId, List<HashMap<String, String>> promotionMJ,
			HashMap<String, String> promotionSuningBoxi) throws IOException {
		HashMap<String, Double> MJpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		
		//计算促销用的价格
		double promotionTemp = 0;
		HashMap<String, String> promotionMap = new HashMap<String, String>();
		HashMap<String, String> promotionMapMax = new HashMap<String, String>();
		HashMap<String, String> promotionMapMin = new HashMap<String, String>();

		double man = 0;
		double jian = 0;
		double manMax = 0;
		double jianMax = 0;
		double manMin = 0;
		double jianMin = 0;
		// 从非促销价的促销内容中计算促销价
		for (HashMap<String, String> m : MJpromotionMap) {
			//String promotionTypeInfo = replacePromotionInfo(promotion.get("promotion_type_info"));
			String promotionTypeInfo = m.get("promotion_type_info");
			String operationProductId = m.get("operation_product_id");
			String shopCode = m.get("shop_code");
			double jianTop = 0;
			String promotionAboutInfo = promotionSuningBoxi.get(promotionTypeInfo+"\001"+operationProductId+"\001"+shopCode);
			if(promotionAboutInfo != null){
				try {
					jianTop = Double.parseDouble(promotionAboutInfo);
				} catch (Exception e) {
					jianTop = 0;
				}
			}
			boolean ifRun = true;
			for (HashMap<String, String> map : promotionMJ) {
				if (map.get("promotion_type_info") != null 
						&& map.get("promotion_type_info").equals(promotionTypeInfo)
						&& (jianTop == 0 || Double.parseDouble(map.get("满")) <= jianTop)) {
					ifRun = false;
					// 符合满减的情况
					if(tempPrice >= Double.parseDouble(map.get("满")) && man == 0){
						man = Double.parseDouble(map.get("满"));
						jian = Double.parseDouble(map.get("减"));
						promotionMap.put("man", map.get("满"));
						promotionMap.put("jian", map.get("减"));
					}
					if(man != 0 && tempPrice >= Double.parseDouble(map.get("满")) && Double.parseDouble(map.get("减")) > jian){
						man = Double.parseDouble(map.get("满"));
						jian = Double.parseDouble(map.get("减"));
						promotionMap.put("man", map.get("满"));
						promotionMap.put("jian", map.get("减"));
					}
					
					if(manMax == 0){
						manMax = Double.parseDouble(map.get("满"));
						jianMax = Double.parseDouble(map.get("减"));
						promotionMapMax.put("man", map.get("满"));
						promotionMapMax.put("jian", map.get("减"));
					}
					if(manMax != 0 && Double.parseDouble(map.get("减")) > jianMax){
						manMax = Double.parseDouble(map.get("满"));
						jianMax = Double.parseDouble(map.get("减"));
						promotionMapMax.put("man", map.get("满"));
						promotionMapMax.put("jian", map.get("减"));
					}
					
					if(manMin == 0){
						manMin = Double.parseDouble(map.get("满"));
						jianMin = Double.parseDouble(map.get("减"));
						promotionMapMin.put("man", map.get("满"));
						promotionMapMin.put("jian", map.get("减"));
					}
					if(manMin != 0 && Double.parseDouble(map.get("减")) < jianMin){
						manMin = Double.parseDouble(map.get("满"));
						jianMin = Double.parseDouble(map.get("减"));
						promotionMapMin.put("man", map.get("满"));
						promotionMapMin.put("jian", map.get("减"));
					}
				}
			}
			/*
			 * 如果此满减促销没有出现在“促销对照表”中,处理
			 * 满减规则
			 * 减：[u'减',u'降',u'用',u'省',u':',u'-',u',']  #入1条
			 * 满：[u'每',u'每满',u'每多买']  #入30条
			 * 满：[u'满',u'够']
			 */
			HashMap<String, String> runMapMJ = new HashMap<String, String>();
			HashMap<String, String> runMap = new HashMap<String, String>();
			if(ifRun && promotionTypeInfo != null 
				&& (promotionTypeInfo.indexOf("每")!=-1||promotionTypeInfo.indexOf("满")!=-1||promotionTypeInfo.indexOf("够")!=-1||promotionTypeInfo.indexOf("每多买")!=-1)
				&& (promotionTypeInfo.indexOf("减")!=-1||promotionTypeInfo.indexOf("降")!=-1||promotionTypeInfo.indexOf("用")!=-1||promotionTypeInfo.indexOf("省")!=-1)){
				//促销处理
				
				String s = "\\d+(\\.\\d+)?";
				Pattern  pattern=Pattern.compile(s);  
				Matcher  ma=pattern.matcher(promotionTypeInfo);  
				List<String> li = new ArrayList<String>(); 
				while(ma.find()){  
			        li.add(ma.group());
				}
				runMapMJ.put("promotion_type_info", promotionTypeInfo);
				runMapMJ.put("shop_id", m.get("shop_id"));
				if(li.size() == 2){
					for(int i=1; i<31; i++){
						runMapMJ.put("满", Double.parseDouble(li.get(0))*i+"");
						runMapMJ.put("减", Double.parseDouble(li.get(1))*i+"");
						String mysql = ConvertSql.getSql("syntun_v2.满减处理", DataSelection.MJFiledList, runMapMJ);
						SkyscopeInspect.MJsql.add(mysql);
					}
				}else if(li.size()%2 == 0){
					for(int i=0; i<li.size()/2; i++){
						runMapMJ.put("满", Double.parseDouble(li.get(0+2*i))+"");
						runMapMJ.put("减", Double.parseDouble(li.get(1+2*i))+"");
						String mysql = ConvertSql.getSql("syntun_v2.满减处理", DataSelection.MJFiledList, runMapMJ);
						SkyscopeInspect.MJsql.add(mysql);
					}
				}else{
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
				}
				
			}else if(ifRun && promotionTypeInfo != null 
				&& (promotionTypeInfo.indexOf(":")!=-1||promotionTypeInfo.indexOf("：")!=-1)){
				try {
					int i = promotionTypeInfo.replace("：", ":").indexOf(":")+1;
					String j = promotionTypeInfo.charAt(i)+"";
					Integer.parseInt(j);
				} catch (Exception e) {
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
					continue;
				}
				//促销处理
				String s = "\\d+(\\.\\d+)?";
				Pattern  pattern=Pattern.compile(s);  
				Matcher  ma=pattern.matcher(promotionTypeInfo);  
				List<String> li = new ArrayList<String>(); 
				while(ma.find()){  
			        li.add(ma.group());
				}
				if(li.size() == 2){
					runMapMJ.put("promotion_type_info", promotionTypeInfo);
					runMapMJ.put("shop_id", m.get("shop_id"));
					runMapMJ.put("满", Double.parseDouble(li.get(0))+"");
					runMapMJ.put("减", Double.parseDouble(li.get(1))+"");
					String mysql = ConvertSql.getSql("syntun_v2.满减处理", DataSelection.MJFiledList, runMapMJ);
					SkyscopeInspect.MJsql.add(mysql);
				}else{
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
				}
				
			}else if(ifRun && promotionTypeInfo != null 
				&& (promotionTypeInfo.indexOf(",")!=-1||promotionTypeInfo.indexOf("，")!=-1)){
				try {
					int i = promotionTypeInfo.replace("，", ",").indexOf(",")+1;
					String j = promotionTypeInfo.charAt(i)+"";
					Integer.parseInt(j);
				} catch (Exception e) {
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
					continue;
				}
				//促销处理
				String s = "\\d+(\\.\\d+)?";
				Pattern  pattern=Pattern.compile(s);  
				Matcher  ma=pattern.matcher(promotionTypeInfo);  
				List<String> li = new ArrayList<String>(); 
				while(ma.find()){  
			        li.add(ma.group());
				}
				if(li.size() == 2){
					runMapMJ.put("promotion_type_info", promotionTypeInfo);
					runMapMJ.put("shop_id", m.get("shop_id"));
					runMapMJ.put("满", Double.parseDouble(li.get(0))+"");
					runMapMJ.put("减", Double.parseDouble(li.get(1))+"");
					String mysql = ConvertSql.getSql("syntun_v2.满减处理", DataSelection.MJFiledList, runMapMJ);
					SkyscopeInspect.MJsql.add(mysql);
				}else{
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
				}
				
			}else if(ifRun && promotionTypeInfo != null 
				&& promotionTypeInfo.indexOf("-")!=-1){
				try {
					int i = promotionTypeInfo.indexOf("-")+1;
					String j = promotionTypeInfo.charAt(i)+"";
					Integer.parseInt(j);
				} catch (Exception e) {
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
					continue;
				}
				//促销处理
				String s = "\\d+(\\.\\d+)?";
				Pattern  pattern=Pattern.compile(s);  
				Matcher  ma=pattern.matcher(promotionTypeInfo);  
				List<String> li = new ArrayList<String>(); 
				while(ma.find()){  
			        li.add(ma.group());
				}
				if(li.size() == 2){
					runMapMJ.put("promotion_type_info", promotionTypeInfo);
					runMapMJ.put("shop_id", m.get("shop_id"));
					runMapMJ.put("满", Double.parseDouble(li.get(0))+"");
					runMapMJ.put("减", Double.parseDouble(li.get(1))+"");
					String mysql = ConvertSql.getSql("syntun_v2.满减处理", DataSelection.MJFiledList, runMapMJ);
					SkyscopeInspect.MJsql.add(mysql);
				}else{
					runMap.put("promotion_id", m.get("promotion_id"));
					runMap.put("product_id", m.get("product_id"));
					runMap.put("operation_id", m.get("operation_product_id"));
					runMap.put("promotion_type_name", m.get("promotion_type_name"));
					runMap.put("promotion_type_info", m.get("promotion_type_info"));
					runMap.put("get_data_date", m.get("get_date"));
					runMap.put("get_data_time", m.get("get_date") + " " + 
							(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
							":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
					runMap.put("shop_name", m.get("shop_name"));
					runMap.put("min", m.get("min"));
					runMap.put("hour_1", m.get("hour"));
					runMap.put("shop_name_1", m.get("shop_name"));
					runMap.put("shop_code", m.get("shop_code"));
					runMap.put("sku_id", m.get("sku_id"));
					runMap.put("city_id", m.get("city_id"));
					
					String mysql = ConvertSql.getSql("syntun_v2.满减未处理", DataSelection.MJNFiledList, runMap);
					SkyscopeInspect.MJNsql.add(mysql);
				}
			}
		}
		
		if(promotionMap.size() != 0){
			promotionTemp = tempPrice - Double.parseDouble(promotionMap.get("jian"));
		}
		
		// 合生元客户不符合满减情况，最大减
		if((userId.equals("100693") || groupId.equals("5")) && 
				promotionMap.size() == 0 && promotionMapMax.size() != 0){
			int a = (int) (Double.parseDouble(promotionMapMax.get("man"))/tempPrice);
			if((Double.parseDouble(promotionMapMax.get("man"))/tempPrice) > a){
				a = a + 1;
			}
			promotionTemp = tempPrice - Double.parseDouble(String.format("%.2f", Double.parseDouble(promotionMapMax.get("jian"))/a));
		}
		
		// 客户不符合满减情况，最小减
//		if(promotionMap.size() == 0 && userId.equals("100693") && promotionMapMin.size() != 0){
//			int a = (int) (Double.parseDouble(promotionMapMin.get("man"))/tempPrice);
//			if((Double.parseDouble(promotionMapMax.get("man"))/tempPrice) > a){
//				a = a + 1;
//			}
//			promotionTemp = tempPrice - Double.parseDouble(String.format("%.2f", Double.parseDouble(promotionMapMin.get("jian"))/a));
//		}
		
		// 如果促销价大于价格，交换促销价和价格
		if (promotionTemp == 0) {
			promotionTemp = tempPrice;
		}
		
		MJpriceMap.put("tempPrice", promotionTemp);
		MJpriceMap.put("maxPrice", maxPrice);
		return MJpriceMap;
	}
	
	/*
	 * 满减计算
	 */
	public static Double getMJPrice(double tempPrice, HashMap<String, String> promotion, HashMap<String,String> key,
			String userId, List<HashMap<String, String>> promotionMJ) {
		double endPrice = 0;
		//String platFormId = key.get("platForm_id");
		
		//String promotionTypeInfo = replacePromotionInfo(promotion.get("promotion_type_info"));
		String promotionTypeInfo =promotion.get("promotion_type_info");
		double man = 0;
		double jian = 0;
		double manMax = 0;
		double jianMax = 0;
		double manMin = 0;
		double jianMin = 0;
		boolean isFirst = true;
		for (HashMap<String, String> map : promotionMJ) {
			
			if (map.get("promotion_type_info") != null && map.get("promotion_type_info").equals(promotionTypeInfo)) {
				// 符合满减的情况
				if(isFirst && tempPrice >= Double.parseDouble(map.get("满"))
						&& man == 0){
					isFirst = false;
					man = Double.parseDouble(map.get("满"));
					jian = Double.parseDouble(map.get("减"));
				}
				if(!isFirst && tempPrice >= Double.parseDouble(map.get("满"))
						&& Double.parseDouble(map.get("满")) > man){
					isFirst = false;
					man = Double.parseDouble(map.get("满"));
					jian = Double.parseDouble(map.get("减"));
				}
				
				// 合生元客户不符合满减情况，最大减
				if(userId.equals("100693") && tempPrice < Double.parseDouble(map.get("满"))
						&& manMax == 0){
					manMax = Double.parseDouble(map.get("满"));
					jianMax = Double.parseDouble(map.get("减"));
				}
				if(userId.equals("100693") && tempPrice < Double.parseDouble(map.get("满"))
						&& Double.parseDouble(map.get("满")) > manMax){
					manMax = Double.parseDouble(map.get("满"));
					jianMax = Double.parseDouble(map.get("减"));
				}
				// 其他客户不符合满减的情况，最小减
				if(tempPrice < Double.parseDouble(map.get("满")) 
						&& manMin == 0){
					manMin = Double.parseDouble(map.get("满"));
					jianMin = Double.parseDouble(map.get("减"));
				}
				if(tempPrice < Double.parseDouble(map.get("满"))
						&& Double.parseDouble(map.get("满")) < manMin){
					manMin = Double.parseDouble(map.get("满"));
					jianMin = Double.parseDouble(map.get("减"));
				}
			}
			
		}
		endPrice = tempPrice;
		// 合生元客户最大减
		if(isFirst && userId.equals("100693") && manMax != 0){
			int a = (int) Math.round(manMax/tempPrice);
			endPrice = tempPrice - Double.parseDouble(String.format("%.2f", jianMax/a));
		}
		// 其他客户最小减
		if(isFirst && !userId.equals("100693") && manMin != 0){
			int a = (int) Math.round(manMin/tempPrice);
			endPrice = tempPrice - Double.parseDouble(String.format("%.2f", jianMin/a));
		}
		// 符合满减条件	
		if(!isFirst){
			endPrice = tempPrice - jian;
		}
		
		return endPrice;
	}
	
	/**
	 * 替换方法，将满减促销中的数据进行替换，使数据进行统一化
	 * 
	 * @param str
	 *            促销内容
	 * @return
	 */
//	public static String replacePromotionInfo(String str) {
//		String replacestr = str;
//		for (HashMap<String, String> map : DataSelection.promotionReplace) {
//			for (String key : map.keySet()) {
//				replacestr = replacestr.replace(key, map.get(key));
//			}
//		}
//		return replacestr;
//	}
	
}
