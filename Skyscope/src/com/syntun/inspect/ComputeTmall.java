package com.syntun.inspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ComputeTmall {

	public static HashMap<String, Double> reduceTmall(HashMap<String, Double> priceMap, 
			List<HashMap<String, String>> TmallpromotionMap, HashMap<String, String> key) throws IOException {
		HashMap<String, Double> MZpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		//计算之后的价格
		double promotionTemp = 0;
		
		double man = 0;
		double jian = 0;
		
		for (HashMap<String, String> map : TmallpromotionMap) {
			// 符合满减的情况
			if(tempPrice >= Double.parseDouble(map.get("满")) && man == 0){
				man = Double.parseDouble(map.get("满"));
				jian = Double.parseDouble(map.get("减"));
			}
			if(tempPrice >= Double.parseDouble(map.get("满")) 
					//&& Double.parseDouble(map.get("满")) > man
					&& Double.parseDouble(map.get("减")) > jian){
				man = Integer.parseInt(map.get("满"));
				jian = Integer.parseInt(map.get("减"));
			}
		}
		promotionTemp = tempPrice - jian;
		
		// 如果促销价大于价格，交换促销价和价格
		if (promotionTemp > tempPrice) {
			double temProPri = tempPrice;
			tempPrice = promotionTemp;
			promotionTemp = temProPri;
		}
		
		MZpriceMap.put("tempPrice", promotionTemp);
		MZpriceMap.put("maxPrice", maxPrice);
		return MZpriceMap;
	}
}