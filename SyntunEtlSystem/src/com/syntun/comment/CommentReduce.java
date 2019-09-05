package com.syntun.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.io.Text;

/**
 * 处理评论相关的reduce类，主要功能，统计评论数量，好中差数量，平均得分
 * 
 * @author ren
 * 
 */
public class CommentReduce {
	
	public static List<HashMap<String, String>> reduce(Text key, List<HashMap<String, String>> values, 
			HashMap<String, String> priceMap, HashMap<String, String> sourceMap)
			throws IOException {

		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		String date = shareValues[1];
		String opertionProductId = shareValues[2];
		String platFormId = shareValues[3];
//		String shopId = shareValues[4];
		
		List<HashMap<String, String>> valMapList = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> map : values) {
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("user_name", map.get("user_name"));
			m.put("user_level", map.get("user_level"));
			m.put("product_comment_time", map.get("product_comment_time"));
			m.put("product_comment_score", map.get("product_comment_score"));
			m.put("product_comment_content", map.get("product_comment_content"));
			m.put("company_name", map.get("company_name"));
			m.put("product_source", map.get("product_source"));
			m.put("table_name", map.get("table_name"));
			m.put("product_id", opertionProductId);
			m.put("platform_id", platFormId);
			m.put("get_date", date);
			m.put("shop_id", priceMap.get("shop_id"));
			m.put("product_price", priceMap.get("price"));
			m.put("product_name", priceMap.get("product_name"));
			m.put("sort_name", priceMap.get("sort_name"));
			m.put("pinpai_name", priceMap.get("pinpai_name"));
			m.put("category_id", priceMap.get("category_id"));
			m.put("brand_id", priceMap.get("brand_id"));
			m.put("product_source", sourceMap.get("product_source"));
			
			valMapList.add(m);
		}
		
		if (valMapList.size() > 0) {
			return valMapList;
		}else{
			return null;
		}
	}
}
