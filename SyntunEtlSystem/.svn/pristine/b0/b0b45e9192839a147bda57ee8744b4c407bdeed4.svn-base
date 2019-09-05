package com.syntun.collect.priceOut;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.price.PriceOutMap;
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
			String price = map.get("product_price");
			if (price.equals("null") || price.equals("0") || price.equals("-1")) {
				return;
			}
			String platFormId = map.get("platForm_id");
			if (platFormId == null) {
				return;
			}
			
			PriceOutMap.map(platFormId, map, context);
		}
	}

}