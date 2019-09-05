package com.syntun.collect.categoryGrade;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		
		HashMap<String, String> vMap = new HashMap<String, String>();
		vMap.put("1&&3", "低端");
		vMap.put("2&&3", "中等");
		vMap.put("3&&3", "高端");
		vMap.put("1&&4", "低端");
		vMap.put("2&&4", "低端");
		vMap.put("3&&4", "中等");
		vMap.put("4&&4", "高端");
		vMap.put("1&&5", "低端");
		vMap.put("2&&5", "偏低端");
		vMap.put("3&&5", "中等");
		vMap.put("4&&5", "偏高端");
		vMap.put("5&&5", "高端");
		vMap.put("1&&6", "低端");
		vMap.put("2&&6", "偏低端");
		vMap.put("3&&6", "中等");
		vMap.put("4&&6", "中等");
		vMap.put("5&&6", "偏高端");
		vMap.put("6&&6", "高端");
		vMap.put("1&&7", "低端");
		vMap.put("2&&7", "偏低端");
		vMap.put("3&&7", "偏低端");
		vMap.put("4&&7", "中等");
		vMap.put("5&&7", "中等");
		vMap.put("6&&7", "偏高端");
		vMap.put("7&&7", "高端");
		vMap.put("1&&8", "低端");
		vMap.put("2&&8", "偏低端");
		vMap.put("3&&8", "偏低端");
		vMap.put("4&&8", "中等");
		vMap.put("5&&8", "中等");
		vMap.put("6&&8", "偏高端");
		vMap.put("7&&8", "偏高端");
		vMap.put("8&&8", "高端");
		
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> priceList = new ArrayList<HashMap<String, String>>();
		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMapR(line);
			if (map == null) {
				continue;
			}
			if (map.get("Syntun").equals("comment")) {
				HashMap<String, String> m1 = new HashMap<String, String>(map);
				dataList.add(m1);
			}
			if (map.get("Syntun").equals("priceRang")) {
				HashMap<String, String> m1 = new HashMap<String, String>(map);
				priceList.add(m1);
			}
		}

		if(priceList.size() > 0 && dataList.size() > 0 ){
			/*
			 * 关联基础表
			 */
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> m = dataList.get(i);
				double productPrice = 0;
				try {
					productPrice = Double.parseDouble(m.get("product_price"));
				} catch (Exception e) {
					continue;
				}
				int number = 0;
				int totalNumber = 0;
				for (int j = 0; j < priceList.size(); j++) {
					HashMap<String, String> priceRang = priceList.get(j);
					double cMin = 0;
					double cMax = 0;
					try {
						cMin = Double.parseDouble(priceRang.get("c_min"));
						cMax = Double.parseDouble(priceRang.get("c_max"));
					} catch (Exception e) {
						continue;
					}
					if(cMin < productPrice && productPrice <= cMax){
						try {
							number = Integer.parseInt(priceRang.get("number"));
							totalNumber = Integer.parseInt(priceRang.get("total_number"));
							break;
						} catch (Exception e) {
							continue;
						}
					}
				}
				if(number==0 && totalNumber==0){
					continue;
				}
				String grade = vMap.get(number+"&&"+totalNumber);
				
				String v = m.get("user_level")+"	"+
							m.get("product_id")+"	"+
							m.get("product_price")+"	"+
							m.get("product_name")+"	"+
							m.get("product_comment_time")+"	"+
							m.get("product_comment_score")+"	"+
							m.get("product_source")+"	"+
							m.get("platform_id")+"	"+
							m.get("platform_name")+"	"+
							m.get("shop_id")+"	"+
							m.get("shop_name")+"	"+
							m.get("shop_info")+"	"+
							m.get("shop_info_1")+"	"+
							m.get("category_name")+"	"+
							m.get("brand_name")+"	"+
							m.get("category_id")+"	"+
							m.get("brand_id")+"	"+
							m.get("super_category_name")+"	"+
							m.get("hight_category_name")+"	"+
							m.get("get_date")+"	"+
							number+"	"+
							totalNumber+"	"+
							grade;
			
				try {
					// id、user_name
					context.write(new Text(id+"	"+m.get("user_name")), new Text(v.getBytes("UTF-8")));
					id++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else{
			return;
		}
		
	}
}