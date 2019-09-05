package com.syntun.inspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ComputeDaoShouJia {

	public static HashMap<String, Double> reduceDaoShouJia(HashMap<String, Double> priceMap, List<HashMap<String, String>> DSpromotionMap, 
			HashMap<String, String> key, String userId) throws IOException {
		
		HashMap<String, Double> DSpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		
		double promotionTemp = 0;
		double Temp = 0;
		//促销处理
		for (HashMap<String, String> m : DSpromotionMap) {
			// 合生元的“开团提醒”进行计算
			if(m.get("promotion_type_info") != null  && m.get("promotion_type_name") != null){
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
			}
		}
		
		// 核算之后的价格如果高于到手价，并且到手价价格不小于200，则将最总到手价划算为最终合算的价格（苏宁平台）
		if (promotionTemp != 0 && tempPrice > promotionTemp && promotionTemp >= 200) {
			tempPrice = promotionTemp;
		}
		DSpriceMap.put("tempPrice", tempPrice);
		DSpriceMap.put("maxPrice", maxPrice);
		return DSpriceMap;
	}	
}
