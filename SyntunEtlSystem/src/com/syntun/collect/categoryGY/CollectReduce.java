package com.syntun.collect.categoryGY;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import com.syntun.etl.tools.ConvertTools;

public class CollectReduce extends Reducer<Text, Text, Text, Text> {
	static int id = 0;
	private MultipleOutputs<Text, Text> mos;
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		mos = new MultipleOutputs<Text, Text>(context);
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		mos.close();
		super.cleanup(context);
	}
	
	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		String keyValue = new String(key.getBytes(), 0, key.getLength(), "UTF-8");
		if(keyValue.split("@0@").length != 2){
			return;
		}
		String userName = keyValue.split("@0@")[0];
		String platformId = keyValue.split("@0@")[1];
		String platformName = "null";
		
		HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMapR(line);
			if (map == null) {
				continue;
			}
			if(map.get("platform_name") != null && !map.get("platform_name").equals("null") && !map.get("platform_name").equals("NULL")){
				platformName = map.get("platform_name");
			}
			String k = map.get("number")+"&&"+map.get("total_number")+"&&"+map.get("grade");
			if(dataMap.containsKey(k)){
				int val = dataMap.get(k)+1;
				dataMap.put(k, val);
			}else{
				dataMap.put(k, 1);
			}
		}

		int number = 0;
		int totalNumber = 0;
		String grade = null;
		int i = 0;
		int v = 0;
		for(String k : dataMap.keySet()){
			if(k.split("&&").length != 3){
				continue;
			}
			if(i == 0){
				number = Integer.parseInt(k.split("&&")[0]);
				totalNumber = Integer.parseInt(k.split("&&")[1]);
				grade = k.split("&&")[2];
				i = 1;
				v = dataMap.get(k);
			}else if(i == 1 && dataMap.get(k) > v){
				number = Integer.parseInt(k.split("&&")[0]);
				totalNumber = Integer.parseInt(k.split("&&")[1]);
				grade = k.split("&&")[2];
				v = dataMap.get(k);
			}else if(i == 1 && dataMap.get(k) == v){
				if(Integer.parseInt(k.split("&&")[0]) < number){
					number = Integer.parseInt(k.split("&&")[0]);
					totalNumber = Integer.parseInt(k.split("&&")[1]);
					grade = k.split("&&")[2];
					v = dataMap.get(k);
				}
			}
		}
		
		if(number != 0 && totalNumber != 0){
			String va = number +"	"+ totalNumber +"	"+ grade;
	
			try {
				context.write(new Text(id+"	"+platformId+"	"+platformName+"	"+userName), new Text(va.getBytes("UTF-8")));
				id++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}