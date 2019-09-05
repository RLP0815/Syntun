package com.syntun.collect.price;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;

import com.syntun.entity.PricePromotionInfo;
import com.syntun.price.PriceReduce;

public class CollectReduce1 {

	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> reduce(Text key, List<Map<String, String>> li, Context context) throws IOException, InterruptedException {
		// 跑单个分类结束
		List<Map<String, String>> priceText = new ArrayList<Map<String, String>>();
		
		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		
		String date = shareValues[1];
		String opertionProductId = shareValues[2];
		String platFormId = shareValues[3];
//		String shopId = shareValues[4];
		String tableName = "";
		for (Map<String, String> map : li) {
			if (map.get("syntun")==null){
				continue;
			}
			if (map.get("syntun").equals("price")) {
				if (map.get("table_name") != null && map.get("table_name").indexOf("price") != -1) {
					if (map.get("product_price") != null && map.get("product_price").equals("-1")) {
						continue;
					}
				}
				tableName = map.get("table_name");
				priceText.add(map);

			} 
		}
		PricePromotionInfo p = null;
		
		if (priceText.size() > 0) {
			p = PriceReduce.reduce(key, priceText, context, true);
		}
		
		HashMap<String, String> valMap = new HashMap<String, String>();
		String tempPrice = "";
		if (p != null) {
			if (p.getPromotionPrice() != null) {
				tempPrice = p.getPromotionPrice();
			} else {
				tempPrice = p.getLingshoujia();
			}
			
			valMap.put("product_id", opertionProductId);
			valMap.put("platForm_id", platFormId);
			valMap.put("date", date);
			valMap.put("price", tempPrice);
			valMap.put("shop_id", p.getShopId());
			valMap.put("product_name", p.getProductName());
			valMap.put("sort_name", p.getSortName());
			valMap.put("pinpai_name", p.getPinpaiName());
			valMap.put("category_id", p.getCategoryId());
			valMap.put("brand_id", p.getBrandId());
			valMap.put("table_name", tableName.split("\\.")[0] + ".product_price_comment");
			return valMap;
		}else{
			return null;
		}
		
	}
}
