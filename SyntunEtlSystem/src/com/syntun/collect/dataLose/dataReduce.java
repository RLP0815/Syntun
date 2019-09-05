package com.syntun.collect.dataLose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData132;
import com.syntun.etl.tools.SyntunDate;

public class dataReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;
	/**
	 * 结果sql集合
	 */
	public static List<String> loseResult;

	public static HashMap<String, Set<String>> dataLose = new HashMap<String, Set<String>>();
	
	public static HashMap<String, HashMap<String, Integer>> loseMap = new HashMap<String, HashMap<String, Integer>>();

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		loseResult = new ArrayList<String>();
//		dataLose = new HashMap<String, Set<String>>();
		loseMap = new HashMap<String, HashMap<String, Integer>>();
		mos = new MultipleOutputs<Text, Text>(context);
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		String tableNameCheck = conf.get("tableName");
		String checkDate = conf.get("date");
		String start = SyntunDate.nowJieDate("3", checkDate);
		List<String> list = SyntunDate.getInterDays(start, checkDate);
		for (String key : loseMap.keySet()){
			String uniqueKey = key.split(":")[1];
			String uniqueVal = key.split(":")[2];
			
			for (String dateStr : list) {
				if(!loseMap.get(key).containsKey(dateStr)
						|| loseMap.get(key).get(dateStr) == 0){
					List<String> tableCountFiled = new ArrayList<String>();
					tableCountFiled.add("data_base");
					tableCountFiled.add("table_name");
					tableCountFiled.add("unique_key");
					tableCountFiled.add("unique_val");
					tableCountFiled.add("lose_date");
					tableCountFiled.add("check_date");
					HashMap<String, String> fieldMap = new HashMap<String, String>();
					fieldMap.put("data_base", tableNameCheck.split("\\.")[0]);
					fieldMap.put("table_name", tableNameCheck.split("\\.")[1]);
					fieldMap.put("unique_key", uniqueKey);
					fieldMap.put("unique_val", uniqueVal);
					fieldMap.put("lose_date", dateStr);
					fieldMap.put("check_date", checkDate);
						
					String mysql = ConvertSql.getSql("xitong.table_data_list", tableCountFiled, fieldMap);
					loseResult.add(mysql);
				}
			}
		}
		
		Thread t1 = new Thread(new InsertData132(loseResult));
		t1.start();
		while (t1.isAlive()) {
		}
		
		mos.close();
		super.cleanup(context);
	}

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		
		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		if (shareValues.length < 2) {
			return;
		}
		String tableName = shareValues[0];
		String uniqueKey = shareValues[1];
		String uniqueVal = shareValues[2];
		String date = shareValues[3];
		
		Set<String> set = new HashSet<String>();
		for (Text content : value) {
			// 
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			set.add(line);
		}
		int countNum = set.size();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if(loseMap.containsKey(tableName+":"+uniqueKey+":"+uniqueVal))
			map = loseMap.get(tableName+":"+uniqueKey+":"+uniqueVal);
		map.put(date, countNum);
		loseMap.put(tableName+":"+uniqueKey+":"+uniqueVal, map);
		
	}
}
