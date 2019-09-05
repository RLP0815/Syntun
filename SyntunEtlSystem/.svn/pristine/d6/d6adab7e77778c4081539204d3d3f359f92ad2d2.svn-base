package com.syntun.commentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.io.Text;

import com.syntun.collect.result.CollectReduce;

/**
 * 处理评论相关的reduce类，主要功能，统计评论数量，好中差数量，平均得分
 * 
 * @author ren
 * 
 */
public class CommentResultReduce {
	
	public static List<HashMap<String, String>> reduce(Text key, List<HashMap<String, String>> values, 
			HashMap<String, String> Ddate)
			throws IOException {

		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		String opertionProductId = shareValues[0];
		String platFormId = shareValues[1];
//		String shopId = shareValues[2];
//		String userName = shareValues[2];
//		String date = shareValues[3];
		
		List<HashMap<String, String>> valMapList = new ArrayList<HashMap<String, String>>();
		//List<String> uniqueList = new ArrayList<String>();
//		if (CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId) != null){
//			for (HashMap<String, String> map : values) {
//				// 根据map阶段生产的unique值判断是否已经存在该条数据
//				//if (uniqueList.contains(map.get("unique"))) {
//				//	continue;
//				//}
//				//uniqueList.add(map.get("unique"));
//				
//				HashMap<String, String> m = new HashMap<String, String>();
//				m.put("user_name", map.get("user_name"));
//				m.put("user_level", map.get("user_level"));
//				m.put("product_comment_time", map.get("product_comment_time"));
//				m.put("product_comment_score", map.get("product_comment_score"));
//				m.put("product_comment_content", map.get("product_comment_content"));
//				//m.put("company_name", map.get("company_name"));
//				m.put("product_source", map.get("product_source"));
//				m.put("table_name", map.get("table_name"));
//				m.put("product_id", opertionProductId);
//				m.put("platform_id", platFormId);
//				m.put("get_date", map.get("get_date"));
//				m.put("product_price", map.get("product_price"));
//				m.put("product_name", map.get("product_name"));
//				m.put("shop_id", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("shop_id"));
//				m.put("category_name", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("category_name"));
//				m.put("brand_name", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("brand_name"));
//				m.put("category_id", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("category_id"));
//				m.put("brand_id", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("brand_id"));
//				m.put("platform_name", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("platform_name"));
//				m.put("shop_name", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("shop_name"));
//				m.put("shop_info", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("shop_info"));
//				m.put("super_category_name", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("super_category_name"));
//				m.put("hight_category_name", CollectReduce.urlTotalData.get(opertionProductId + "\001" + platFormId).get("hight_category_name"));
//				
//				valMapList.add(m);
//			}
//		} else {
			for (HashMap<String, String> map : values) {
				// 根据map阶段生产的uniquen值判断是否已经存在该条数据
				//if (uniqueList.contains(map.get("unique"))) {
				//	continue;
				//}
				//uniqueList.add(map.get("unique"));
				
				HashMap<String, String> m = new HashMap<String, String>();
				m.put("user_name", map.get("user_name"));
				m.put("user_level", map.get("user_level"));
				m.put("product_comment_time", map.get("product_comment_time"));
				m.put("product_comment_score", map.get("product_comment_score"));
				m.put("product_comment_content", map.get("product_comment_content"));
				m.put("product_source", map.get("product_source"));
				m.put("table_name", map.get("table_name"));
				m.put("product_id", opertionProductId);
				m.put("platform_id", platFormId);
				m.put("get_date", map.get("get_date"));
				if(Ddate.get("product_price") != null){
					m.put("product_price", Ddate.get("product_price").split("@@")[0]);
					m.put("product_name", Ddate.get("product_price").split("@@")[1]);
				}else{
					m.put("product_price", "null");
					m.put("product_name", "null");
				}
				if(Ddate.get("brand_id") != null){
					m.put("shop_id", Ddate.get("brand_id").split("@@")[0]);
					m.put("category_name", Ddate.get("brand_id").split("@@")[1]);
					m.put("brand_name", Ddate.get("brand_id").split("@@")[2]);
					m.put("category_id", Ddate.get("brand_id").split("@@")[3]);
					m.put("brand_id", Ddate.get("brand_id").split("@@")[4]);
					m.put("platform_name", Ddate.get("brand_id").split("@@")[5]);
					m.put("shop_name", Ddate.get("brand_id").split("@@")[6]);
					m.put("shop_info", Ddate.get("brand_id").split("@@")[7]);
					m.put("super_category_name", Ddate.get("brand_id").split("@@")[8]);
					m.put("hight_category_name", Ddate.get("brand_id").split("@@")[9]);
				}else{
					m.put("shop_id", "null");
					m.put("category_name", "null");
					m.put("brand_name", "null");
					m.put("category_id", "null");
					m.put("brand_id", "null");
					m.put("platform_name", "null");
					m.put("shop_name", "null");
					m.put("shop_info", "null");
					m.put("super_category_name", "null");
					m.put("hight_category_name", "null");
				}
				valMapList.add(m);
			}
//		}
		
		if (valMapList.size() > 0) {
			return valMapList;
		}else{
			return null;
		}
	}
}
