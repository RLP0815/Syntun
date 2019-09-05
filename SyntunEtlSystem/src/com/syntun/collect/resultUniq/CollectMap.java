package com.syntun.collect.resultUniq;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.syntun.commentResult.CommentResultUniqMap;
import com.syntun.etl.tools.ConvertTools;

public class CollectMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		if (!line.trim().isEmpty()) {
			Map<String, String> map = ConvertTools.convertStrToMap(line);
			if (map == null) {
				return;
			}
			CommentResultUniqMap.map(map, context);
		}
	}

}