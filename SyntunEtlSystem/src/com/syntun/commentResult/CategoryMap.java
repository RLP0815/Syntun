package com.syntun.commentResult;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.syntun.entity.Parameter;

/**
 * 销量map阶段处理类，主要功能是提取评论相关的字段，还有就是将评论按评论日期输出到reduce
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CategoryMap {

	public static List<String> getField = new ArrayList<String>();
	public static List<String> getField1 = new ArrayList<String>();
	/*
	 * 这里的数字顺序就是MYSQl数据库mysql://192.168.0.73:3306/syntun_comment_no 
	 * --username wgdata --password syntun-000 --table etl_comment_result_140
	 * 这些表中的字段顺序
	 */
	static {
		getField.add("platform_id");
		getField.add("user_name");
		getField.add("category_id");
		getField.add("product_comment_time");

		getField1.add("platform_name");
		getField1.add("product_id");
		getField1.add("product_price");
		getField1.add("product_comment_score");
		getField1.add("product_source");
		getField1.add("category_name");
		getField1.add("super_category_name");
		getField1.add("hight_category_name");
//		getField1.add("category_name_jiu");
//		getField1.add("hight_category_name_jiu");
//		getField1.add("super_category_name_jiu");
		
	}
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static String inEncode = "UTF-8";

	public static void map(Map<String, String> map, Context context) throws IOException {
		String commentTime = map.get("product_comment_time");
		if(commentTime == null || commentTime.equals("null") 
				|| commentTime.equals("NULL") || commentTime.length()<8){
			return;
		}
		commentTime = commentTime.substring(0, 7);
		
		StringBuffer val = new StringBuffer();
		int i = 0;
		for (String filed : getField) {
			String str = map.get(filed);
			if (i == 0 && str != null) {
				val.append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				i = 1;
			} else if (str != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		String unique = map.get("platform_id") + ":" + map.get("user_name") + ":" + map.get("category_id") + ":" + commentTime;
		
		for (String filed : getField1) {
			String str = map.get(filed);
			if (str != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		
		try {
			context.write(new Text(unique), new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}