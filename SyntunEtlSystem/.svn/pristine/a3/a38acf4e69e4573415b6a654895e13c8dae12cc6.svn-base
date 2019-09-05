package com.syntun.collect.result6W;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.syntun.commentResult.CommentResultMap6W;
import com.syntun.etl.tools.ConvertTools;

public class CollectMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		if (!line.trim().isEmpty()) {
			Configuration conf = context.getConfiguration();
			String replaceStr = conf.get("replaceFiled");
			HashMap<String, String> replaceFiled = (HashMap<String, String>) ConvertTools
					.convertStrToMapNoReplace(replaceStr);
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			String tableName = map.get("table_name");
			if (tableName == null) {
				return;
			}
			
			String platFormId = map.get("platform_id");
			String userName = map.get("user_name");
			String productId = map.get("product_id");
			if (platFormId == null) {
				return;
			}
			if(userName == null || userName.equals("null")){
				return;
			}
			if(productId == null || productId.equals("null") || productId.equals("-1")){
				return;
			}
			//if (tableName.indexOf("product_comment") != -1) {
				CommentResultMap6W.map(platFormId, map, context);
			//}
		}
	}

}