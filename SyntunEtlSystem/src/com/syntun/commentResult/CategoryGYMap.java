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
public class CategoryGYMap {
	public static List<String> getField = new ArrayList<String>();
	/*
	 * 这里的数字顺序就是MYSQl数据库mysql://192.168.0.73:3306/syntun_comment_no 
	 * --username wgdata --password syntun-000 --table etl_comment_result_140
	 * 这些表中的字段顺序
	 */
	static {
		//getField.add("product_id");
		getField.add("platform_id");
		//getField.add("id");
		getField.add("user_name");
		//getField.add("user_level");
		//getField.add("product_price");
		//getField.add("product_name");
		//getField.add("product_comment_content");
		//getField.add("product_comment_time");
		//getField.add("product_comment_score");
		getField.add("platform_name");
		//getField.add("shop_id");
		//getField.add("shop_name");
		//getField.add("shop_info");
		//getField.add("get_date");
		//getField.add("category_name");
		//getField.add("brand_name");
		//getField.add("category_id");
		//getField.add("brand_id");
		//getField.add("super_category_name");
		//getField.add("hight_category_name");
		//getField.add("product_source");
		getField.add("number");
		getField.add("total_number");
		getField.add("grade");
		
	}
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static String inEncode = "UTF-8";

	public static void map(Map<String, String> map, Context context) throws IOException {
		String unique = map.get("user_name") + "@0@" + map.get("platform_id");
		
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
		
		try {
//			Random r = new Random();
//			int num = r.nextInt(50);	//andom的nextInt(int n)方法可以生成一个介于0(包含)到n(不包含)之间的整数
			context.write(new Text(unique),new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}