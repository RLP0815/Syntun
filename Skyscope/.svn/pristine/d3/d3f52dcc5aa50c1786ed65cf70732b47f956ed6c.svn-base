package com.syntun.inspect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.syntun.etl.tools.ConvertSql;

public class ComputeManzhe {
	
	public static HashMap<String, Double> reduceManzhe(HashMap<String, Double> priceMap, 
			List<HashMap<String, String>> MZpromotionMap, HashMap<String, String> key,
			String groupId, String userId, List<HashMap<String, String>> promotionMZ) throws IOException {
		HashMap<String, Double> MZpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		
		double zhiMin = 0;
		double maxMin = 0;
		// 从非促销价的促销内容中计算促销价
		for (HashMap<String, String> m : MZpromotionMap) {
			
			HashMap<String, String> ZKValMap = new HashMap<String, String>();
			String promotionTypeInfo = m.get("promotion_type_info");
			boolean ifRun = true;
			for (HashMap<String, String> map : promotionMZ) {
				if (map.get("promotion_info") != null && map.get("promotion_info").equals(promotionTypeInfo)) {
					ifRun = false;
					if(maxMin == 0){
						maxMin = Double.parseDouble(map.get("max"));
					}else if(maxMin != 0 && maxMin > Double.parseDouble(map.get("max"))){
						maxMin = Double.parseDouble(map.get("max"));
					}
					if(zhiMin == 0){
						zhiMin = Double.parseDouble(map.get("值"));
					}else if(zhiMin != 0 && zhiMin > Double.parseDouble(map.get("值"))){
						zhiMin = Double.parseDouble(map.get("值"));
					}
				}
			}
			HashMap<String, String> runMapMZ = new HashMap<String, String>();
			HashMap<String, String> runMap = new HashMap<String, String>();
			if(ifRun && promotionTypeInfo != null 
				&& promotionTypeInfo.indexOf("件")!=-1
				&& promotionTypeInfo.indexOf("折")!=-1){
				promotionTypeInfo = promotionTypeInfo.replace("一", "1").replace("二", "2").replace("三", "3").replace("四", "4")
						.replace("五", "5").replace("六", "6").replace("七", "7").replace("八", "8").replace("九", "9");
				//促销处理
				String s = "\\d+(\\.\\d+)?";
				Pattern  pattern=Pattern.compile(s);  
				Matcher  ma=pattern.matcher(promotionTypeInfo);  
				List<String> li = new ArrayList<String>(); 
				while(ma.find()){  
			        li.add(ma.group());
				}
				if(li.size() == 2){
					double max = Double.parseDouble(li.get(1))/10;
					runMapMZ.put("promotion_info", promotionTypeInfo);
					runMapMZ.put("值", "1.00");
					runMapMZ.put("max", String.valueOf(max));
					runMapMZ.put("件", li.get(0));
					
					String mysql = ConvertSql.getSql("syntun_v2.满折处理", DataSelection.MZFiledList, runMapMZ);
					SkyscopeInspect.MZsql.add(mysql);
				}else if(li.size()%2 == 0){
					runMapMZ.put("promotion_info", promotionTypeInfo);
					if(li.get(0).equals("1")){
						runMapMZ.put("值", String.valueOf(Double.parseDouble(li.get(1))/10));
					}else{
						runMapMZ.put("值", "1.00");
					}
					runMapMZ.put("max", String.valueOf(Double.parseDouble(li.get(li.size()-1))/10));
					runMapMZ.put("件", String.valueOf(Double.parseDouble(li.get(li.size()-2))));
					
					String mysql = ConvertSql.getSql("syntun_v2.满折处理", DataSelection.MZFiledList, runMapMZ);
					SkyscopeInspect.MZsql.add(mysql);
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
					
					String mysql = ConvertSql.getSql("syntun_v2.满折未处理", DataSelection.MZNFiledList, runMap);
					SkyscopeInspect.MZNsql.add(mysql);
				}
			}
			if(ifRun){
				ZKValMap.put("promotion_id", m.get("promotion_id"));
				ZKValMap.put("product_id", m.get("product_id"));
				ZKValMap.put("operation_id", m.get("operation_product_id"));
				ZKValMap.put("promotion_type_name", m.get("promotion_type_name"));
				ZKValMap.put("promotion_type_info", m.get("promotion_type_info"));
				ZKValMap.put("get_data_date", m.get("get_date"));
				ZKValMap.put("get_data_time", m.get("get_date") + " " + 
						(m.get("hour").length() == 1 ? ("0" + m.get("hour")) : m.get("hour")) + 
						":"+ (m.get("min").equals("15") ? "00" : "30") + ":00");
				//ZKValMap.put("SKU_NAME", promotion.get(""));
				ZKValMap.put("shop_name", m.get("shop_name"));
				ZKValMap.put("min", m.get("min"));
				ZKValMap.put("hour_1", m.get("hour"));
				ZKValMap.put("shop_name_1", m.get("shop_name"));
				ZKValMap.put("shop_code", m.get("shop_code"));
				ZKValMap.put("sku_id", m.get("sku_id"));
				ZKValMap.put("city_id", m.get("city_id"));
				
				String sql = ConvertSql.getSqlServer("[skyscope].[dbo].[折扣未处理_copy]", DataSelection.ZKFiledList, ZKValMap);
				//System.out.println(priceLess.size() + "___" + sql);
				SkyscopeInspect.ZKsql.add(sql);
			}
		}
		//满折的价格
		double temp = tempPrice;
		// 最大折(合生元组计算方式)，合生元100693；伊利100737；伊利液奶100922
		if (groupId.equals("5") || userId.equals("100693") || userId.equals("100737") || userId.equals("100922") || userId.equals("100923") || userId.equals("100924")) {
			if(maxMin != 0){
				temp = Double.parseDouble(String.format("%.2f",tempPrice * maxMin));
			}
		}else {
			if(zhiMin != 0){
				temp = Double.parseDouble(String.format("%.2f",tempPrice * zhiMin));
			}
		}
		MZpriceMap.put("tempPrice", temp);
		MZpriceMap.put("maxPrice", maxPrice);
		return MZpriceMap;
	}
}
