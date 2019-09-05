package com.syntun.collect.result6W;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

		Configuration conf = context.getConfiguration();
		
		String replaceStr = conf.get("replaceFiled");
		HashMap<String, String> replaceFiled = (HashMap<String, String>) ConvertTools
				.convertStrToMapNoReplace(replaceStr);

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		// 去重
		List<String> uniqueList = new ArrayList<String>();
		HashMap<String, String> Ddate = new HashMap<String, String>();
		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			if (map.get("syntun") == null) {
				return;
			}
			if (map.get("syntun").equals("commentResult")) {
				if(map.get("product_price") != null && !map.get("product_price").equals("null")){
					Ddate.put("product_price", map.get("product_price"));
				}
				if(map.get("brand_id") != null && !map.get("brand_id").equals("null")){
					Ddate.put("brand_id", map.get("shop_id")+"@@"+
											map.get("sort_name")+"@@"+
											map.get("pinpai_name")+"@@"+
											map.get("category_id")+"@@"+
											map.get("brand_id")+"@@"+
											map.get("product_name"));
				}
				// 根据map阶段生产的unique值判断是否已经存在该条数据
				if (uniqueList.contains(map.get("unique"))) {
					continue;
				}
				uniqueList.add(map.get("unique"));
				HashMap<String, String> m1 = new HashMap<String, String>(map);
				dataList.add(m1);
			}
		}
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		if (dataList.size() > 0) {
			HashMap<String, String> map = dataList.get(0);
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("user_name", map.get("user_name"));
			m.put("user_level", map.get("user_level"));
			m.put("product_comment_time", map.get("product_comment_time"));
			m.put("product_comment_score", map.get("product_comment_score"));
			m.put("product_comment_content", map.get("product_comment_content"));
			m.put("product_source", map.get("product_source"));
			m.put("table_name", map.get("table_name"));
			m.put("product_id", map.get("product_id"));
			m.put("platform_id", map.get("platform_id"));
			m.put("get_date", map.get("get_date"));
			if(Ddate.get("product_price") != null){
				m.put("product_price", Ddate.get("product_price"));
			}else{
				m.put("product_price", map.get("product_price"));
			}
			if(Ddate.get("brand_id") != null){
				m.put("shop_id", Ddate.get("brand_id").split("@@")[0]);
				m.put("category_name", Ddate.get("brand_id").split("@@")[1]);
				m.put("brand_name", Ddate.get("brand_id").split("@@")[2]);
				m.put("category_id", Ddate.get("brand_id").split("@@")[3]);
				m.put("brand_id", Ddate.get("brand_id").split("@@")[4]);
				m.put("product_name", Ddate.get("brand_id").split("@@")[5]);
			}else{
				m.put("shop_id", map.get("shop_id"));
				m.put("category_name", map.get("sort_name"));
				m.put("brand_name", map.get("pinpai_name"));
				m.put("category_id", map.get("category_id"));
				m.put("brand_id", map.get("brand_id"));
				m.put("product_name", map.get("product_name"));
			}
			resultList.add(m);
		}
		
		for (int i = 0; i < resultList.size(); i++) {
			HashMap<String, String> m = resultList.get(i);
			StringBuffer val = new StringBuffer();
			val.append("syntun").append(Parameter.ZHIPLITE).append("commentResult");
			for (String k : m.keySet()) {
				val.append(Parameter.LIESPLITE).append(k).append(Parameter.ZHIPLITE).append(m.get(k));
			}
			try {
				context.write(new Text(), new Text(val.toString().getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
