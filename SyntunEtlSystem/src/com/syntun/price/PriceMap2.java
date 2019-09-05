package com.syntun.price;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * 
 * 
 * @author ren
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PriceMap2 {
	
	public static List<String> getField = new ArrayList<String>();
	static {
		getField.add("product_id");
		getField.add("platForm_id");
		getField.add("shop_id");
		getField.add("table_name");
		getField.add("price");
		getField.add("shop_id");
		getField.add("date");
		getField.add("product_name");
		getField.add("sort_name");
		getField.add("pinpai_name");
		getField.add("category_id");
		getField.add("brand_id");
	}
	
	public static String inEncode = "UTF-8";
	public static void map(String platFormId, Map<String, String> map, Context context) throws IOException {

		String lieSplit = "\001";
		String zhiSplit = "\002";
		//Configuration conf = context.getConfiguration();
		StringBuffer val = new StringBuffer();
		
		String opertionProductId = map.get("product_id");
//		String endShopCode = map.get("shop_id");
		if (opertionProductId == null || opertionProductId.equals("null")) {
			return;
		}
		
		int i = 0;
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
		val.append(lieSplit).append("syntun").append(zhiSplit).append("price");
		try {
			for(int num = 0; num < 100; num ++){
				context.write(new Text(opertionProductId + ":" + platFormId + ":" + num),
						new Text(val.toString().getBytes("UTF-8")));
			}
//			context.write(new Text(opertionProductId + ":" + platFormId),
//					new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}