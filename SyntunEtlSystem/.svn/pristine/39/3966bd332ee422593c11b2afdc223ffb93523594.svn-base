package com.syntun.collect.comment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.comment.CommentMap;
import com.syntun.comment.SourceMap;
import com.syntun.etl.tools.ConvertTools;
import com.syntun.price.PriceMap2;

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
			String platFormStr = conf.get("platform_list");
			String paltFormName = map.get("table_name").split("\\.")[0];
			Map<String, String> platformList = ConvertTools.convertStrToMapNoReplace(platFormStr);
			String platFormId = platformList.get(paltFormName);
			if (platFormId == null) {
				return;
			}
			// hadoop数据
			if (tableName.indexOf("product_comment") != -1) {
				CommentMap.map(platFormId, map, context);
			}
			if (platFormId.equals("5") && tableName.indexOf("product_source") != -1) {
				SourceMap.map(platFormId, map, context);
			}	
			// 价格中间结果数据
			if (tableName.indexOf("product_price_comment") != -1) {
				PriceMap2.map(platFormId, map, context);
			}
		}
	}

}