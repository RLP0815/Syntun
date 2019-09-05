package com.syntun.comment;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.syntun.entity.Parameter;
import com.syntun.etl.tools.SyntunDate;

/**
 * 销量map阶段处理类，主要功能是提取评论相关的字段，还有就是将评论按评论日期输出到reduce
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SourceMap {

	public static List<String> getField = new ArrayList<String>();

	static {

		//天猫才有
		getField.add("product_source");
		//getField.add("company_name");
		getField.add("product_id");
		getField.add("get_date");
		getField.add("get_date_time");
		getField.add("table_name");
		
	}
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static String inEncode = "UTF-8";

	public static void map(String platFormId, Map<String, String> map, Context context) throws IOException {
		Configuration conf = context.getConfiguration();
		String getDate = conf.get("date");
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
			if (i == 0 && str != null) {
				val.append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				i = 1;
			} else if (map.get(filed) != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		if (date == null) {
			return;
		}
		// 
		if (!SyntunDate.isData(getDate, date)) {
			return;
		}
		val.append(Parameter.LIESPLITE).append("platform_id").append(Parameter.ZHIPLITE).append(platFormId);
		val.append(Parameter.LIESPLITE).append("syntun").append(Parameter.ZHIPLITE).append("source");

		try {
			for(int num = 0; num < 100; num++){
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