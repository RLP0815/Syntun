package com.syntun.collect.result;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import com.syntun.etl.tools.ConvertTools;
import com.syntun.etl.tools.DateTools;
import com.syntun.etl.tools.SyntunDate;

public class CollectReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;
	
//	public static HashMap<String, HashMap<String, String>> urlTotalData;
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
//		urlTotalData = BaseDaoSqlServer
//				.getTbaleAllData("[syntun_base].[dbo].[url_total_list]");
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
//		String keyValue = new String(key.getBytes(), 0, key.getLength(), "UTF-8");
		
//		if (!urlTotalData.containsKey(keyV)) {
//			return;
//		}
		HashMap<String, String> urlTotal = new HashMap<String, String>();
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> urlList = new ArrayList<HashMap<String, String>>();
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
			if (map.get("Syntun").equals("urlList")) {
				HashMap<String, String> m1 = new HashMap<String, String>(map);
				urlList.add(m1);
			}
		}

		if(urlList.size() > 0 && dataList.size() > 0){
			urlTotal = urlList.get(0);
		}else{
			return;
		/*
		 * 生成未匹配的url	
		 */
//			if (dataList.size()>0) {
//				HashMap<String, String> m = dataList.get(0);
//				String v = m.get("user_level")+"	"+					//user_level
//						m.get("product_id")+"	"+					//product_id
//						m.get("product_price")+"	"+					//product_price
//						m.get("product_name")+"	"+
//						"null"+"	"+
//						m.get("product_comment_time")+"	"+					//product_comment_time
//						m.get("product_comment_score")+"	"+					//product_comment_score
//						m.get("score")+"	"+
//						m.get("platform_id")+"	"+					//platform_id
//						m.get("platform_name")+"	"+
//						m.get("shop_id")+"	"+
//						m.get("shop_name")+"	"+
//						m.get("shop_info")+"	"+
//						m.get("get_date")+"	"+
//						m.get("category_name")+"	"+
//						m.get("brand_name")+"	"+
//						m.get("category_id")+"	"+
//						m.get("brand_id")+"	"+
//						m.get("super_category_name")+"	"+
//						m.get("hight_category_name")+"	"+
//						m.get("product_source");					//product_source
//			
//				try {
//					// id、user_name
//					context.write(new Text(m.get("0")+"	"+m.get("1")), new Text(v.getBytes("UTF-8")));
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
		/*
		 * 关联基础表，生成月份数据
		 */
		for (int i = 0; i < dataList.size(); i++) {
			HashMap<String, String> m = dataList.get(i);
			// product_comment_time
			String commentTime = m.get("product_comment_time");
			// 抓取日期get_date
			String date = m.get("get_date");
			if(date == null || date.equals("null") || date.equals("NULL")){
				continue;
			}
			if(commentTime == null || commentTime.equals("null") || commentTime.equals("NULL")){
				commentTime = date;
			}
			try {
				format.parse(commentTime);
			} catch (ParseException e) {
				try {
					commentTime = commentTime.replace("天前", "");
					if (commentTime.indexOf("个月前") != -1) {
						commentTime = (Integer.parseInt(commentTime.replace("个月前", "")) * 30) + "";
					}
					if (commentTime.indexOf("分钟前") != -1 || commentTime.indexOf("小时前") != -1) {
						commentTime = date;
					} else {
						Integer.parseInt(commentTime);
						commentTime = SyntunDate.nowJieDate(commentTime, date);
					}
				} catch (NumberFormatException e2) {
					commentTime = date;
				}
			}
			if(!commentTime.substring(0, 2).equals("20")){
				commentTime = date;
			}
			try {
				commentTime = DateTools.convertDate(commentTime);
			} catch (Exception e) {
				try {
					commentTime = DateTools.convertDateT(commentTime);
				} catch (Exception e1) {
					continue;
				}
			}
			if(!commentTime.substring(0, 2).equals("20")){
				commentTime = date;
			}

			String v = m.get("user_level")+"	"+
					m.get("product_id")+"	"+
					m.get("product_price")+"	"+
					urlTotal.get("product_name")+"	"+
					commentTime+"	"+
					m.get("product_comment_score")+"	"+
					m.get("product_source")+"	"+
					m.get("platform_id")+"	"+
					urlTotal.get("platform_name")+"	"+
					urlTotal.get("shop_id")+"	"+
					urlTotal.get("shop_name")+"	"+
					urlTotal.get("shop_info")+"	"+
					urlTotal.get("shop_info_1")+"	"+
					urlTotal.get("category_name")+"	"+
					urlTotal.get("brand_name")+"	"+
					urlTotal.get("category_id")+"	"+
					urlTotal.get("brand_id")+"	"+
					urlTotal.get("super_category_name")+"	"+
					urlTotal.get("hight_category_name")+"	"+
					date;			//get_date
		
			try {
				// id、user_name
				context.write(new Text(m.get("id")+"	"+m.get("user_name")), new Text(v.getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
