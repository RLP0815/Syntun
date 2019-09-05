package com.syntun.inspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ComputeLingquan {

	public static HashMap<String, Double> reduceLingquan(HashMap<String, Double> priceMap, 
			List<HashMap<String, String>> LQpromotionMap, 
			HashMap<String, String> key, String groupId, String userId,
			List<HashMap<String, String>> promotionMJ) throws IOException {
		HashMap<String, Double> LQpriceMap = new HashMap<String, Double>();
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
		for (HashMap<String, String> m : LQpromotionMap) {
			String promotionTypeInfo = m.get("promotion_type_info");
			for (HashMap<String, String> map : promotionMJ) {
				if (map.get("promotion_type_info") != null && map.get("promotion_type_info").equals(promotionTypeInfo)) {
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
		// 如果促销价大于价格，交换促销价和价格
		if (promotionTemp == 0) {
			promotionTemp = tempPrice;
		}
		
		LQpriceMap.put("tempPrice", promotionTemp);
		LQpriceMap.put("maxPrice", maxPrice);
		return LQpriceMap;
	}
	
	/*
	 * 满减计算
	 */
	public static Double getLQPrice(double tempPrice, HashMap<String, String> promotion, HashMap<String,String> key,
			List<HashMap<String, String>> promotionMJ) {
		double endPrice = 0;
		//String platFormId = key.get("platForm_id");
		
		//String promotionTypeInfo = replacePromotionInfo(promotion.get("promotion_type_info"));
		String promotionTypeInfo = promotion.get("promotion_type_info");
		double man = 0;
		double jian = 0;
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
			}
		}
		endPrice = tempPrice - jian;
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
