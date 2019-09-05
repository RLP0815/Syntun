package com.syntun.collect.tableCount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.syntun.etl.tools.ConvertTools;
import com.syntun.tableCount.TableMap;

public class countMap extends Mapper<LongWritable, Text, Text, Text> {
	private String encode = "UTF-8";

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = new String(value.getBytes(), 0, value.getLength(), encode);
		if (!line.trim().isEmpty()) {
			Configuration conf = context.getConfiguration();
			String unique = conf.get("uniqueKey");
			HashMap<String, String> uniqueKeyMap = (HashMap<String, String>) ConvertTools
					.convertStrToMapNoReplace(unique);
			HashMap<String, String> replaceFiled = new HashMap<String, String>();
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			String tableName = map.get("table_name");
			if (tableName == null) {
				return;
			}
			
			String uniqueKey = uniqueKeyMap.get(tableName);
			List<String> keyList = new ArrayList<String>();
			if(uniqueKey != null){
				for(int i = 0;i<uniqueKey.split(",").length;i++){
					keyList.add(uniqueKey.split(",")[i]);				
				}
			}
			
			// hadoop数据
			TableMap.map(tableName, keyList, map, context);
		}
	}

}