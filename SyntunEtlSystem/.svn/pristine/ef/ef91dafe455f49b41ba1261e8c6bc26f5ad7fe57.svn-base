package com.syntun.collect.priceAvg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

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
		mos.close();
		super.cleanup(context);
	}

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		if (shareValues.length < 2) {
			return;
		}
		String productId = shareValues[0];
		String platFormId = shareValues[1];

		Configuration conf = context.getConfiguration();
		
		String replaceStr = conf.get("replaceFiled");
		HashMap<String, String> replaceFiled = (HashMap<String, String>) ConvertTools
				.convertStrToMapNoReplace(replaceStr);

		double sumPrice = 0;
		int sum = 0;
		HashMap<String, String> brandMap = new HashMap<String, String>();
		HashMap<String, String> priceMap = new HashMap<String, String>();
		for (Text content : value) {
			//
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			
			if(map.get("brand_id")!=null && !map.get("brand_id").equals("null")){
				brandMap.put("platForm_id", platFormId);
				brandMap.put("product_id", productId);
				brandMap.put("product_name", map.get("product_name"));
				brandMap.put("shop_id", map.get("shop_id"));
				brandMap.put("sort_name", map.get("sort_name"));
				brandMap.put("pinpai_name", map.get("pinpai_name"));
				brandMap.put("category_id", map.get("category_id"));
				brandMap.put("brand_id", map.get("brand_id"));
			}else{
				priceMap.put("platForm_id", platFormId);
				priceMap.put("product_id", productId);
				priceMap.put("product_name", map.get("product_name"));
				priceMap.put("shop_id", map.get("shop_id"));
				priceMap.put("sort_name", map.get("sort_name"));
				priceMap.put("pinpai_name", map.get("pinpai_name"));
				priceMap.put("category_id", map.get("category_id"));
				priceMap.put("brand_id", map.get("brand_id"));
			}
			try {
				//计算单年价格时价格取值字段
				sumPrice= sumPrice + Double.parseDouble(map.get("price"));
				//sumPrice= sumPrice + Double.parseDouble(map.get("product_price"));
				sum = sum + 1;
			} catch (Exception e) {
				
			}
			
		}
		
		String avgPrice = String.format("%.2f", sumPrice/sum);

		if (sumPrice != 0) {
			HashMap<String, String> m = new HashMap<String, String>();
			if(brandMap.size() > 0){
				m.putAll(brandMap);
			}else if(priceMap.size() > 0){
				m.putAll(priceMap);
			}else {
				return;
			}
			StringBuffer val = new StringBuffer();
			val.append("syntun").append(Parameter.ZHIPLITE).append("priceAvg");
			for (String k : m.keySet()) {
				val.append(Parameter.LIESPLITE).append(k).append(Parameter.ZHIPLITE).append(m.get(k));
			}
			val.append(Parameter.LIESPLITE).append("product_price").append(Parameter.ZHIPLITE).append(avgPrice);
			try {
				context.write(new Text(), new Text(val.toString().getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
