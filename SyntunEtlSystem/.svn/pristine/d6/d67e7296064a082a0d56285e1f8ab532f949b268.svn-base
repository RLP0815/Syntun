package com.syntun.collect.tableCount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData132;

public class countReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;
	/**
	 * 结果sql集合
	 */
	public static List<String> countResult;

	public static HashMap<String, Set<String>> tableCount = new HashMap<String, Set<String>>();
	
	public static HashMap<String, Integer> countMap = new HashMap<String, Integer>();

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		countResult = new ArrayList<String>();
//		tableCount = new HashMap<String, Set<String>>();
//		countMap = new HashMap<String, Integer>();
		mos = new MultipleOutputs<Text, Text>(context);
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		Thread t1 = new Thread(new InsertData132(countResult));
		t1.start();
		while (t1.isAlive()) {
		}
		
//		Configuration conf = context.getConfiguration();
//		String output = "/tmp/TableCountList";
//		FileTools.deleteFile(output,conf);
		
		mos.close();
		super.cleanup(context);
	}

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		
		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		if (shareValues.length < 2) {
			return;
		}
		String tableName = shareValues[0];
		String date = shareValues[1];
		if(date == null || date.equals("0000-00-00")){
			return;
		}
		
//		Configuration conf = context.getConfiguration();
//		String tableCountFiled = conf.get("tableCountFiled");
		
		Set<String> set = new HashSet<String>();
		for (Text content : value) {
			// 
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			set.add(line);
		}
		int countNum = set.size();
		List<String> tableCountFiled = new ArrayList<String>();
		tableCountFiled.add("data_base");
		tableCountFiled.add("table_name");
		tableCountFiled.add("count_num");
		tableCountFiled.add("get_date");
		HashMap<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("data_base", tableName.split("\\.")[0]);
		fieldMap.put("table_name", tableName.split("\\.")[1]);
		fieldMap.put("count_num", countNum+"");
		fieldMap.put("get_date", date);
			
		String mysql = ConvertSql.getSql("xitong.table_count_list", tableCountFiled, fieldMap);
		countResult.add(mysql);
		
//		try {
//			context.write(new Text(), new Text(mysql.getBytes("UTF-8")));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
	}
}
