package com.syntun.inspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ComputeYuShou {

	public static HashMap<String, Double> reduceYuShou(HashMap<String, Double> priceMap, List<HashMap<String, String>> YSpromotionMap, 
			HashMap<String, String> key) throws IOException {
		HashMap<String, Double> YSpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		
		// 从非促销价的促销内容中计算促销价
		for (HashMap<String, String> m : YSpromotionMap) {
			double jian = 0;
			try {
				jian = Double.parseDouble(m.get("promotion_about_info"));
			} catch (NumberFormatException e) {
				//System.out.println("预售类促销，信息转换double出错，错误促销："+m.get("promotion_type_info"));
				continue;
			}
			if(jian > 1){
				tempPrice = tempPrice - jian;
			}
		}
		YSpriceMap.put("tempPrice", tempPrice);
		YSpriceMap.put("maxPrice", maxPrice);
		return YSpriceMap;
	}
}
