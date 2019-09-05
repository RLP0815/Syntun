package com.syntun.tableCount;

import java.io.IOException;
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
 * @author ren
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataLoseMap {

	public static String inEncode = "UTF-8";

	public static void map(String tableName, String uniqueKey, Map<String, String> map, Context context) throws IOException {
		//Configuration conf = context.getConfiguration();
		//String getDate = conf.get("date");
		// 抓取日期
		String date = map.get("get_date");
		if (date == null) {
			return;
		}
		List<String> keyList = new ArrayList<String>();
		if(uniqueKey != null){
			for(int i = 0;i<uniqueKey.split(",").length;i++){
				keyList.add(uniqueKey.split(",")[i]);				
			}
			keyList.add("get_date");
		}
		int i = 0;
		StringBuffer val = new StringBuffer();
		StringBuffer uniqueVal = new StringBuffer();
		for (String filed : keyList) {
			String str = map.get(filed);
			if (i == 0 && str != null) {
				val.append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				uniqueVal.append(map.get(filed));
				i = 1;
			} else if (map.get(filed) != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				if(!filed.equals("get_date"))
					uniqueVal.append(Parameter.LIESPLITE).append(map.get(filed));
			}
		}
		
		try {
			context.write(new Text(tableName+":"+uniqueKey+":"+uniqueVal+":"+date),new Text(GetMD5.GetMD5Code(val.toString()).getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}