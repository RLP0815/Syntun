package com.syntun.collect.result;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.commentResult.CommentResultMap;
import com.syntun.commentResult.UrlTotalListMap;
import com.syntun.etl.tools.ConvertTools;

public class CollectMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		if (!line.trim().isEmpty()) {
			
			if(line.split(",").length == 20){
				Map<String, String> map = ConvertTools.convertStrToMapUrl(line);
				if (map == null) {
					return;
				}
				UrlTotalListMap.map(map, context);
			}else{
				Map<String, String> map = ConvertTools.convertStrToMap(line);
				if (map == null) {
					return;
				}
				CommentResultMap.map(map, context);
			}
		}
	}
}