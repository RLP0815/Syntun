package com.syntun.collect.resultOut;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.commentResult.CommentResultOutMap;
import com.syntun.etl.tools.ConvertTools;
import com.syntun.price.PriceResultOutMap;

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
			
			if (map.get("table_name") == null){
				PriceResultOutMap.map(map, context);
			}
			if (map.get("table_name") != null) {
				CommentResultOutMap.map(map, context);
			}
//			if(map.get("syntun") == null){
//				return;
//			}
//			if (map.get("syntun").equals("priceAvg")){
//				PriceResultOutMap.map(map, context);
//			}
//			if (map.get("syntun").equals("commentResult")) {
//				CommentResultOutMap.map(map, context);
//			}
		}
	}

}