package com.syntun.commentResult;

import java.io.IOException;
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
public class PriceRangMap {

	public static List<String> getField = new ArrayList<String>();
	/*
	 * 这里的数字顺序就是MYSQl数据库mysql://192.168.0.73:3306/syntun_comment_no 
	 * --username wgdata --password syntun-000 --table etl_comment_result_140
	 * 这些表中的字段顺序
	 */
	static {
		getField.add("sort_name");
		getField.add("product_price");
		getField.add("c_min");
		getField.add("c_max");
		getField.add("number");
		getField.add("total_number");
		
	}
	public static String inEncode = "UTF-8";

	public static void map(Map<String, String> map, Context context) throws IOException {
		String unique = map.get("sort_name");
		if (unique == null || unique.equals("null")) {
			return;
		}
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
		val.append(Parameter.LIESPLITE).append("Syntun").append(Parameter.ZHIPLITE).append("priceRang");
		try {
			for(int num = 0; num < 100; num++){
				context.write(new Text(unique +":"+ num), new Text(val.toString().getBytes("UTF-8")));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}