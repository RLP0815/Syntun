package com.syntun.tableCount;

import java.io.IOException;
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
public class TableMap {

	public static String inEncode = "UTF-8";

	public static void map(String tableName, List<String> getField, Map<String, String> map, Context context) throws IOException {
		//Configuration conf = context.getConfiguration();
		//String getDate = conf.get("date");
		// 抓取日期
		String date = map.get("get_date");
		if (date == null || date.equals("0000-00-00")) {
			return;
		}
		int i = 0;
		StringBuffer val = new StringBuffer();
		for (String filed : getField) {
			String str = map.get(filed);
			if (i == 0 && str != null) {
				val.append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				i = 1;
			} else if (map.get(filed) != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		if(val.toString().equals("")){
			for (String key : map.keySet()) { 
				String str = map.get(key);
				if (i == 0 && str != null) {
					val.append(key).append(Parameter.ZHIPLITE).append(map.get(key));
					i = 1;
				} else if (map.get(key) != null) {
					val.append(Parameter.LIESPLITE).append(key).append(Parameter.ZHIPLITE).append(map.get(key));
				}
			}
		} 

		try {
//			Random r = new Random();
//			int num = r.nextInt(100);
			context.write(new Text(tableName+":"+date),new Text(GetMD5.GetMD5Code(val.toString()).getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}