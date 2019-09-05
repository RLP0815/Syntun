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
public class PriceAvgMap {
	public static List<String> getField = new ArrayList<String>();

	static {
		
		getField.add("platForm_id");
		getField.add("product_id");
		//计算单年价格时价格取值字段
		getField.add("price");
		//getField.add("product_price");
		getField.add("product_name");
		getField.add("shop_id");
		getField.add("sort_name");
		getField.add("pinpai_name");
		getField.add("category_id");
		getField.add("brand_id");

	}
	
	public static String inEncode = "UTF-8";
	public static void map(String platFormId, Map<String, String> map, Context context) throws IOException {

		String lieSplit = "\001";
		String zhiSplit = "\002";
		
		StringBuffer val = new StringBuffer();
		int i = 0;
		String productId = map.get("product_id");
		if (productId == null || productId.equals("null") || productId.equals("-1")) {
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
		
		// 给数据添加标记，表示此数据用于price计算
		//val.append(lieSplit).append("syntun").append(zhiSplit).append("priceAvg");
		try {
			context.write(new Text(productId + ":" + platFormId),
					new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}