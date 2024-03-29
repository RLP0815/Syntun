package com.syntun.collect.dataLose;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.etl.tools.ConvertTools;
import com.syntun.tableCount.DataLoseMap;

public class dataMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		if (!line.trim().isEmpty()) {
			Configuration conf = context.getConfiguration();
			String tableNameCheck = conf.get("tableName");
			String unique = conf.get("uniqueKey");
			HashMap<String, String> uniqueKeyMap = (HashMap<String, String>) ConvertTools
					.convertStrToMapNoReplace(unique);
			HashMap<String, String> replaceFiled = new HashMap<String, String>();
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			String tableName = map.get("table_name");
			if (tableName == null) {
				return;
			}
			if (!tableName.split("\\.")[1].equals(tableNameCheck)) {
				return;
			}
			
			String uniqueKey = uniqueKeyMap.get(tableName);
			// hadoop数据
			DataLoseMap.map(tableName, uniqueKey, map, context);
			
		}
	}

}