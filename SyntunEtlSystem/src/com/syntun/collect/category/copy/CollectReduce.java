package com.syntun.collect.category.copy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.NullWritable;
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
		String platformId = keyValue.split(":")[0];
		String userName = keyValue.split(":")[1];
		String categoryId = keyValue.split(":")[2];
		String productCommentTime = keyValue.split(":")[3] + "-01";
		
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMapR(line);
			if (map == null) {
				continue;
			}
			HashMap<String, String> m1 = new HashMap<String, String>(map);
			dataList.add(m1);
		}
		double consumePrice = 0;
		double consumeAvg = 0;
		double commentScore = 0;
		double scoreAvg = 0;
		
		String platformName = "";
		String categoryName = "";
		String superCategoryName = "";
		String hightCategoryName = "";
		if(dataList.size() > 0){
			HashMap<String, String> m = dataList.get(0);
			platformName = m.get("platform_name");
			categoryName = m.get("category_name");
			superCategoryName = m.get("super_category_name");
			hightCategoryName = m.get("hight_category_name");
		}
		
		int x = 0;
		for (int i = 0; i < dataList.size(); i++) {
			HashMap<String, String> m = dataList.get(i);
			try {
				Double.parseDouble(m.get("product_price"));
			} catch (Exception e) {
				continue;
			}
			if(Double.parseDouble(m.get("product_price"))>1){
				consumePrice = consumePrice + Double.parseDouble(m.get("product_price"));
				x ++;
			}
		}
		if (x > 0){
			consumeAvg = Double.parseDouble(String.format("%.2f", consumePrice/x));
		}
		consumePrice = Double.parseDouble(String.format("%.2f", consumePrice));
		
		int y = 0;
		for (int j = 0; j < dataList.size(); j++) {
			HashMap<String, String> m = dataList.get(j);
			//天猫，没有评论得分只有商品得分
			if(platformId.equals("5")){
				try {
					Double.parseDouble(m.get("product_source"));
				} catch (Exception e) {
					continue;
				}
				commentScore = commentScore + Double.parseDouble(m.get("product_source"));
				y ++;
			}else{
				try {
					Double.parseDouble(m.get("product_comment_score"));
				} catch (Exception e) {
					continue;
				}
				commentScore = commentScore + Double.parseDouble(m.get("product_comment_score"));
				y ++;
			}
		}
		if (y > 0){
			// 苏宁，百分制转换为5分制
			if(platformId.equals("3")){
				scoreAvg = Double.parseDouble(String.format("%.2f", commentScore/y/20));
			}else{
				scoreAvg = Double.parseDouble(String.format("%.2f", commentScore/y));
			}
		}
		
//		String v = categoryName  + "	" +
//					superCategoryName  + "	" +
//					hightCategoryName + "	" +
//					consumeAvg + "	" +
//					consumePrice + "	" +
//					scoreAvg;
//				 
//		try {
//			context.write(new Text(id+"	"+platformId+"	"+platformName+"	"+userName+"	"+productCommentTime+"	"+categoryId), 
//					new Text(v.getBytes("UTF-8")));
//			id ++;
		
		String v = categoryName  + "	" +
				superCategoryName  + "	" +
				hightCategoryName + "	" +
				consumeAvg + "	" +
				consumePrice + "	" +
				scoreAvg;
		
		try {
			String k = id+"\001"+
					userName+"\001"+
					productCommentTime+"\001"+
					consumePrice+"\001"+
					scoreAvg;
			context.write(new Text(k), new Text(""));
			id ++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
