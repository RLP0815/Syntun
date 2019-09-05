package com.syntun.collect.price;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.price.PriceMap;
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
			String platFormStr = conf.get("platform_list");
			String paltFormName = map.get("table_name").split("\\.")[0];
			Map<String, String> platformList = ConvertTools.convertStrToMapNoReplace(platFormStr);
			String platFormId = platformList.get(paltFormName);
			if (platFormId == null) {
				return;
			}
			
			if (tableName.indexOf("promotion_info") != -1 || tableName.indexOf("price") != -1
					|| tableName.indexOf("sort_product") != -1) {
				if (tableName.equals("wgdata_suning.product_price_list")) {
					if (map.get("product_price") == null || map.get("product_price").equals("")) {
						return;
					}
				}
				PriceMap.map(platFormId, map, context);
			}
		}
	}

}