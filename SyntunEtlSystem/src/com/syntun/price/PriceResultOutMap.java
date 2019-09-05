package com.syntun.price;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * 处理价格相关的map类，主要功能：从price和promotion表中抽取需要的字段
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PriceResultOutMap {
	public static List<String> getField = new ArrayList<String>();

	static {
		
		getField.add("platform_id");
		getField.add("product_id");
		getField.add("product_price");
		getField.add("product_name");
		getField.add("shop_id");
		getField.add("sort_name");
		getField.add("pinpai_name");
		getField.add("category_id");
		getField.add("brand_id");

	}
	
	public static String inEncode = "UTF-8";
	public static void map(Map<String, String> map, Context context) throws IOException {

		String lieSplit = "\001";
		String zhiSplit = "\002";
		
		StringBuffer val = new StringBuffer();
		int i = 0;
		String productId = map.get("product_id");
		String platformId = map.get("platForm_id");
		if (productId == null || productId.equals("null")) {
			return;
		}

		for (String filed : getField) {
			String str = map.get(filed);
			if (i == 0 && str != null) {
				val.append(filed).append(zhiSplit).append(str);
				i = 1;
			} else if (str != null) {
				val.append(lieSplit).append(filed).append(zhiSplit).append(str);
			}
		}
		val.append(lieSplit).append("syntun").append(zhiSplit).append("priceAvg");
		
		try {
			for(int num = 0; num < 4000; num ++){
				context.write(new Text(productId + ":" + platformId + ":" + num),
						new Text(val.toString().getBytes("UTF-8")));
			}
//			context.write(new Text(productId + ":" + platformId),
//					new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}