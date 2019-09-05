package com.syntun.inspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ComputeNoManjian {

//	//获取促销的计算公式
//	public static HashMap<String, String> promotionTypeMap = null;
//
//	static {
//		HashMap<String, String> filter = new HashMap<String, String>();
//		promotionTypeMap = BaseDao.getTbaleList("price_promotion_compute", "platform_id", "promotion_type_name",
//				"promotion_price", "promotion_price", filter);
//	}
//	
	public static HashMap<String, Double> reduceNoManjian(HashMap<String, Double> priceMap, List<HashMap<String, String>> NMJpromotionMap, 
			HashMap<String, String> key, String userId) throws IOException {
		
		HashMap<String, Double> NMJpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		
		double promotionTemp = 0;
		double Temp = 0;
		//促销处理
		for (HashMap<String, String> m : NMJpromotionMap) {
			// 合生元的“开团提醒”进行计算
			if(m.get("promotion_type_info") != null  && m.get("promotion_type_name") != null){
//				if( (m.get("promotion_type_name").equals("开团提醒") && userId.equals("100693"))
//						|| m.get("promotion_type_name").equals("聚划算")){
					try {
						Temp = Double.parseDouble(m.get("promotion_type_info"));//非满减类的促销信息为数字
						if (promotionTemp == 0){
							promotionTemp = Temp;
						}
						if (promotionTemp != 0 && promotionTemp > Temp){
							promotionTemp = Temp;
						}
					} catch (NumberFormatException e) {
						//System.out.println("非满减类促销，信息转换double出错，错误促销："+m.get("promotion_type_info"));
						continue;
					}
//				}
			}
		}
		
		//return priceMap;
		
		// 如果促销价大于价格，交换促销价和价格
		if (promotionTemp != 0 && promotionTemp < tempPrice) {
			double temProPri = promotionTemp;
			promotionTemp = tempPrice;
			tempPrice = temProPri;
		}
		
		NMJpriceMap.put("tempPrice", tempPrice);
		NMJpriceMap.put("maxPrice", maxPrice);
		return NMJpriceMap;
		
		
//		HashMap<String, Double> NMJpriceMap = new HashMap<String, Double>();
//		double tempPrice = priceMap.get("tempPrice");
//		double maxPrice = priceMap.get("maxPrice");
//		
//		// 错误处理相关
//		String platFormId = key.get("platform_id");
//		String shopId = key.get("shop_id");
//		
//		LinkedList<String> errValList = new LinkedList<String>();
//		LinkedList<String> errFiledList = new LinkedList<String>();
//		errFiledList.add("platform_id");
//		errFiledList.add("operation_product_id");
//		errFiledList.add("get_date");
//		errFiledList.add("err_type_name");
//		errFiledList.add("err_type_info");
//		errFiledList.add("err_focus_information");
//		
//		
//		// 非满减计算出的结果集合
//		List<HashMap<String, Double>> computeEnd = new ArrayList<HashMap<String, Double>>();
//		
//		//促销处理
//		for (HashMap<String, String> m : NMJpromotionMap) {
//			try {
//				Double.parseDouble(m.get("promotion_type_info"));//非满减类的促销信息为数字
//			} catch (NumberFormatException e) {
//				if(promotionTypeMap.containsKey(platFormId + m.get("promotion_type_name"))) {
//					errValList.add(platFormId);
//					errValList.add(key.get("operation_product_id"));
//					errValList.add(key.get("get_date"));
//					errValList.add("promotin_err");
//					errValList.add("是需要计算的非满减促销，但是不能从里面抽取到价格");
//					errValList.add(m.get("promotion_type_name"));
//
//					String sql = DataSelection.insertMysql("err_result_copy", errFiledList, errValList, shopId);
//					SkyscopeInspect.errResult.add(sql);
//					errValList.clear();
//				}
//				continue;
//			}
//			
//			/*
//			 * 用计算公式获取非满减
//			 */
//			HashMap<String, Double> tempMap = GetPromotionPrice.getPrice(tempPrice, platFormId, m);
//			
//			//促销最大值
//			Double tempMaxPrice = GetPromotionPrice.getMaxPrice(maxPrice, m);
//			if (tempMaxPrice != null) {
//				maxPrice = tempMaxPrice;
//			}
//			if (tempMap != null && tempMap.size() > 0) {
//				//非满减计算出的结果集
//				computeEnd.add(tempMap);
//			}
//		}
//		//计算促销用的价格
//		double priceTemp = tempPrice;
//		double promotionTemp = 0;
//		boolean isPromotion = true;
//		// 从多个促销价 团购价 等促销中，找出最低价
//		for (HashMap<String, Double> hsd : computeEnd) {
//			double priceT = hsd.get("price");
//			double promotionT = hsd.get("promotion_price");
//			// 当算出的促销价小于0时跳过。 2015-8-5
//			if (priceT <= 0 || promotionT <= 0) {
//				continue;
//			}
//			if (isPromotion) {
//				isPromotion = false;
//				promotionTemp = promotionT;
//				priceTemp = priceT;
//			} else if (promotionT < promotionTemp) {
//				promotionTemp = promotionT;
//				priceTemp = priceT;
//			}
//		}
//		
//		if (promotionTemp == 0) {
//			promotionTemp = priceTemp;
//		}
//		
//		// 如果促销价大于价格，交换促销价和价格
//		if (promotionTemp > priceTemp) {
//			double temProPri = priceTemp;
//			priceTemp = promotionTemp;
//			promotionTemp = temProPri;
//		}
//		
//		NMJpriceMap.put("tempPrice", promotionTemp);
//		NMJpriceMap.put("maxPrice", maxPrice);
//		return NMJpriceMap;
	}
	
}
