package com.syntun.collect.resultUniq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
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

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMapR(line);
			if (map == null) {
				continue;
			}
			HashMap<String, String> m1 = new HashMap<String, String>(map);
			dataList.add(m1);
			break;
		}

		for (int i = 0; i < dataList.size(); i++) {
			HashMap<String, String> m = dataList.get(i);
	        
			String v = m.get("product_price")+"	"+
					m.get("product_name")+"	"+
					m.get("product_comment_content")+"	"+
					m.get("product_comment_time")+"	"+
					m.get("product_comment_score")+"	"+
					m.get("score")+"	"+
					m.get("platform_id")+"	"+
					m.get("platform_name")+"	"+
					m.get("shop_id")+"	"+
					m.get("shop_name")+"	"+
					m.get("shop_info")+"	"+
					m.get("get_date")+"	"+
					m.get("category_name")+"	"+
					m.get("brand_name")+"	"+
					m.get("category_id")+"	"+
					m.get("brand_id")+"	"+
					m.get("super_category_name")+"	"+
					m.get("hight_category_name")+"	"+
					m.get("product_source");
			
			try {
				context.write(new Text(m.get("id")+"	"+m.get("user_name")+"	"+m.get("user_level")+"	"+m.get("product_id")), new Text(v.getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
