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
import com.syntun.etl.tools.GetMD5;

/**
 * 销量map阶段处理类，主要功能是提取评论相关的字段，还有就是将评论按评论日期输出到reduce
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommentResultMap6W {

	public static List<String> getField = new ArrayList<String>();
	public static List<String> getField1 = new ArrayList<String>();

	static {
		getField.add("user_name");
		getField.add("product_comment_time");
		getField.add("product_comment_score");
		getField.add("product_comment_content");
		getField.add("product_id");
		getField.add("platform_id");
		
		getField1.add("user_level");
		getField1.add("product_source");
		getField1.add("table_name");
		getField1.add("shop_id");
		getField1.add("get_date");
		getField1.add("product_price");
		getField1.add("product_name");
		getField1.add("sort_name");
		getField1.add("pinpai_name");
		getField1.add("category_id");
		getField1.add("brand_id");
		
	}
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static String inEncode = "UTF-8";

	public static void map(String platFormId, Map<String, String> map, Context context) throws IOException {
		// 抓取日期
		String date = map.get("get_date");
		String opertionProductId = map.get("product_id");
		if (opertionProductId == null || opertionProductId.equals("null")) {
			return;
		}
		
		StringBuffer val = new StringBuffer();
		int i = 0;
		for (String filed : getField) {
			String str = map.get(filed);
			if(filed.equals("product_comment_score")){
				str = str.length()>5?"null":str;
			}
			if(filed.equals("product_comment_content")){
				str = "null";
			}
			
			if (i == 0 && str != null) {
				val.append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				i = 1;
			} else if (str != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		String unique = GetMD5.GetMD5Code(val.toString());
		// 增加唯一标示
		val.append(Parameter.LIESPLITE).append("unique").append(Parameter.ZHIPLITE).append(GetMD5.GetMD5Code(val.toString()));
		
		for (String filed : getField1) {
			String str = map.get(filed);
			if (str != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		if (date == null) {
			return;
		}
		val.append(Parameter.LIESPLITE).append("syntun").append(Parameter.ZHIPLITE).append("commentResult");

		try {
			context.write(new Text(unique),
					new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}