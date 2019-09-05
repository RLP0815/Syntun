package com.syntun.collect.category;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.commentResult.CategoryMap;
import com.syntun.etl.tools.ConvertTools;

public class CollectMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		if (!line.trim().isEmpty()) {
			Map<String, String> map = ConvertTools.categoryToMap(line);
			if (map == null) {
				return;
			}
			CategoryMap.map(map, context);
		}
	}
}