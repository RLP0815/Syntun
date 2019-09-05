package com.syntun.collect.priceOut;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
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

		Configuration conf = context.getConfiguration();
		String year = conf.get("year");
		String replaceStr = conf.get("replaceFiled");
		HashMap<String, String> replaceFiled = (HashMap<String, String>) ConvertTools
				.convertStrToMapNoReplace(replaceStr);

		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> m = ConvertTools.convertStrToMap(line, replaceFiled);
			
			String prodectId =  m.get("product_id").replace("	", " ");
			if(prodectId.length() > 50)
				prodectId = prodectId.substring(0, 50);
			String prodectName =  m.get("product_name").replace("	", " ");
			if(prodectName.length() > 200)
				prodectName = prodectName.substring(0, 200);
			String sortName = m.get("sort_name").replace("	", " ");
			if(sortName.length() > 100)
				sortName = sortName.substring(0, 100);
			String pinpaiName = m.get("pinpai_name").replace("	", " ");
			if(pinpaiName.length() > 100)
				pinpaiName = pinpaiName.substring(0, 100);
			String categoryId = m.get("category_id").replace("	", " ");
			if(categoryId.length() > 20)
				categoryId = categoryId.substring(0, 20);
			String brandId = m.get("brand_id").replace("	", " ");
			if(brandId.length() > 20)
				brandId = brandId.substring(0, 20);
			
			String v = m.get("product_price")+"	"+
					prodectName+"	"+
					m.get("shop_id")+"	"+
					sortName+"	"+
					pinpaiName+"	"+
					categoryId+"	"+
					brandId+"	"+
					year;
			try {
				context.write(new Text("0"+"	"+m.get("platForm_id")+"	"+prodectId), new Text(v.toString().getBytes("UTF-8")));
				//context.write(new Text(), new Text(sql.toString().getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
