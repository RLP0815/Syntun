package com.syntun.collect.resultHive;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.commentResult.ResultHiveMap;
import com.syntun.etl.tools.ConvertTools;

public class CollectMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		Configuration conf = context.getConfiguration();
		int num = conf.getInt("num", 0);
		//int num = 6;
		if (!line.trim().isEmpty()) {
			if(line.split("	").length == num){
				System.out.println(num);
				Map<String, String> map = ConvertTools.convertStrToMapHive(line, num);
				if (map == null) {
					return;
				}
				ResultHiveMap.map(map, context, num);
			}else{
				return;
			}
		}
	}
}