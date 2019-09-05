package com.syntun.collect.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.syntun.comment.CommentReduce;
import com.syntun.entity.Parameter;
import com.syntun.etl.tools.ConvertTools;

public class CollectReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		mos = new MultipleOutputs<Text, Text>(context);
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
//		Thread t1 = new Thread(new InsertData(priceResult));
//		t1.start();
//		
//		while (t1.isAlive()) {
//		}
		
		mos.close();
		super.cleanup(context);
	}

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		if (shareValues.length < 2) {
			return;
		}
		String opertionProductId = shareValues[0];
		String platFormId = shareValues[1];
//		String shopId = "1111";
//		if (shareValues.length == 3) {
//			shopId = shareValues[2];
//		}

		Configuration conf = context.getConfiguration();
		
		String replaceStr = conf.get("replaceFiled");
		HashMap<String, String> replaceFiled = (HashMap<String, String>) ConvertTools
				.convertStrToMapNoReplace(replaceStr);

		HashMap<String, List<HashMap<String, String>>> priceNewData = new HashMap<String, List<HashMap<String, String>>>();
		
		HashMap<String, String> priceMap = new HashMap<String, String>();
		HashMap<String, String> sourceMap = new HashMap<String, String>();
		HashSet<String> dayList = new HashSet<String>();
		// 去重
		List<String> uniqueList = new ArrayList<String>();
		for (Text content : value) {
			// 
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			if (map.get("syntun") == null) {
				return;
			}
			
			if (map.get("syntun").equals("comment")) {
				// 根据map阶段生产的unique值判断是否已经存在该条数据
				if (uniqueList.contains(map.get("unique"))) {
					continue;
				}
				uniqueList.add(map.get("unique"));
				// 
				String nowDate = map.get("new_date");
				HashMap<String, String> m1 = new HashMap<String, String>(map);
				m1.put("get_date", m1.get("new_date"));

				List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
				if (priceNewData.containsKey(nowDate)) {
					dataList = priceNewData.get(nowDate);
				}
				
				dataList.add(m1);
				priceNewData.put(nowDate, dataList);
				dayList.add(nowDate);
			}else if (map.get("syntun").equals("price")) {
				priceMap = new HashMap<String, String>(map);
			}else if (map.get("syntun").equals("source")) {
				sourceMap = new HashMap<String, String>(map);
			}
		}

		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		if (dayList.size() > 0) {
			for (String date : dayList) {
				key = new Text("11:" + date + ":" + opertionProductId + ":" + platFormId);
				if (priceNewData.get(date) != null) {
					List<HashMap<String, String>> li = priceNewData.get(date);
					List<HashMap<String, String>> mValList = CommentReduce.reduce(key, li, priceMap, sourceMap);
					for (int i = 0; i < mValList.size(); i++) {
						resultList.add(mValList.get(i));
					}
				}
			}
		}

		for (int i = 0; i < resultList.size(); i++) {
			HashMap<String, String> m = resultList.get(i);
			StringBuffer val = new StringBuffer();
			val.append("syntun").append(Parameter.ZHIPLITE).append("comment");
			for (String k : m.keySet()) {
				if (k.equals("table_name")){
					val.append(Parameter.LIESPLITE).append(k).append(":").append(m.get(k));
				}else{
					val.append(Parameter.LIESPLITE).append(k).append(Parameter.ZHIPLITE).append(m.get(k));
				}
			}
			
			try {
				//context.write(new Text(opertionProductId + ":" + platFormId), new Text(val.toString().getBytes("UTF-8")));
				context.write(new Text(), new Text(val.toString().getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
