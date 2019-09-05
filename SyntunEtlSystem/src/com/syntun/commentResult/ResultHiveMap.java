package com.syntun.commentResult;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * 销量map阶段处理类，主要功能是提取评论相关的字段，还有就是将评论按评论日期输出到reduce
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResultHiveMap {

	public static String inEncode = "UTF-8";

	public static void map(Map<String, String> map, Context context, int num) throws IOException {
		StringBuffer val = new StringBuffer();
		String unique = null;
		if(num > 3){
			unique = map.get("0") + "	" + map.get("1") + "	" + map.get("2");
			for (int i=3;i<num;i++) {
				String str = map.get(i+"");
				if (i != num-1) {
					val.append(str).append("	");
				} else if (i == num-1) {
					val.append(str);
				}
			}
		}else{
			unique = map.get("0");
			for (int i=1;i<num;i++) {
				String str = map.get(i+"");
				if (i != num-1) {
					val.append(str).append("	");
				} else if (i == num-1) {
					val.append(str);
				}
			}
		}
		
		try {
			context.write(new Text(unique), new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}