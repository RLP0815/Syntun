package com.syntun.collect.resultOut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import com.syntun.etl.tools.ConvertTools;

public class CollectReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;
	/**
	 * 错误sql集合
	 */
//	public static List<String> commentResult = null;
//	public static List<String> filedList = null;
	
//	public static HashMap<String, HashMap<String, String>> urlTotalData;
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
//		urlTotalData = BaseDaoSqlServer
//				.getTbaleAllData("[syntun_base].[dbo].[url_total_list]");
//		commentResult = new ArrayList<String>();
//		filedList = BaseDaoSqlServer.getField("[comment].[dbo].[etl_comment_result]", "192.168.0.20", "comment");
		mos = new MultipleOutputs<Text, Text>(context);
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
//		Thread t1 = new Thread(new InsertDataSqlServer(commentResult));
//		t1.start();
//		while (t1.isAlive()) {
//		}
		mos.close();
		super.cleanup(context);
	}

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
//		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
//		
//		String productId = shareValues[0];
//		String platFormId = shareValues[1];

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
			}else if (map.get("syntun").equals("priceAvg")) {
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
			}
		}
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < dataList.size(); i++) {
			HashMap<String, String> map = dataList.get(i);
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
//			HashMap<String, String> resultKeyMap = new HashMap<String, String>();
//			StringBuffer val = new StringBuffer();
//			val.append("syntun").append(Parameter.ZHIPLITE).append("commentResultOut");
//			for (String k : m.keySet()) {
//				val.append(Parameter.LIESPLITE).append(k).append(Parameter.ZHIPLITE).append(m.get(k));
//				resultKeyMap.put(k, m.get(k));
//			}
//			resultKeyMap.put("score", "0");
//			String sql = ConvertSql.getSql("[comment].[dbo].[etl_comment_result]", filedList, resultKeyMap);
//			commentResult.add(sql);
			
			String commentContent = m.get("product_comment_content");
			if(commentContent != null && !commentContent.equals("null"))
				commentContent = commentContent.replace("	", " ").replace(" ", "");
	        if(StringUtils.isNotBlank(commentContent)){
	        	commentContent = commentContent.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
	        }
	        commentContent = StringEscapeUtils.escapeSql(commentContent);
			
			String commentScore = m.get("product_comment_score").length()>5?"null":m.get("product_comment_score");
			
			String commentTime = m.get("product_comment_time");
			if(commentTime != null && commentTime.length() > 30)
				commentTime = commentTime.substring(0, 30);
			
			String userLevel = m.get("user_level");
			if(userLevel != null && userLevel.length() > 20)
				userLevel = userLevel.substring(0, 20);
			
			String productPrice = m.get("product_price");
			if(productPrice != null && productPrice.length() > 10)
				productPrice = productPrice.substring(0, 10);
			
			String prodectId = m.get("product_id");
			if(prodectId != null && !prodectId.equals("null"))
				prodectId = prodectId.replace("	", " ");
			if(prodectId != null && prodectId.length() > 50)
				prodectId = prodectId.substring(0, 50);
				
			String prodectName =  m.get("product_name");
			if(prodectName != null && !prodectName.equals("null"))
				prodectName = prodectName.replace("	", " ");
			if(prodectName != null && prodectName.length() > 200)
				prodectName = prodectName.substring(0, 200);
			
			String categoryName = m.get("category_name");
			if(categoryName != null && !categoryName.equals("null"))
				categoryName = categoryName.replace("	", " ");
			if(categoryName != null && categoryName.length() > 100)
				categoryName = categoryName.substring(0, 100);
			
			String brandName = m.get("brand_name");
			if(brandName != null && !brandName.equals("null"))
				brandName = brandName.replace("	", " ");
			if(brandName != null && brandName.length() > 100)
				brandName = brandName.substring(0, 100);
			
			String categoryId = m.get("category_id");
			if(categoryId != null && !categoryId.equals("null"))
				categoryId = categoryId.replace("	", " ");
			if(categoryId != null && categoryId.length() > 30)
				categoryId = categoryId.substring(0, 30);
			
			String brandId = m.get("brand_id");
			if(brandId != null && !brandId.equals("null"))
				brandId = brandId.replace("	", " ");
			if(brandId != null && brandId.length() > 30)
				brandId = brandId.substring(0, 30);
			
			String userName = m.get("user_name");
	        if(StringUtils.isNotBlank(userName)){
	        	userName = userName.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
	        }
			String v = userLevel+"	"+
						prodectId+"	"+
						productPrice+"	"+
						prodectName+"	"+
						commentContent+"	"+
						commentTime+"	"+
						commentScore+"	"+
						"0"+"	"+
						m.get("platform_id")+"	"+
						"null"+"	"+
						m.get("shop_id")+"	"+
						"null"+"	"+
						"null"+"	"+
						m.get("get_date")+"	"+
						categoryName+"	"+
						brandName+"	"+
						categoryId+"	"+
						brandId+"	"+
						"null"+"	"+
						"null"+"	"+
						m.get("product_source");
			try {
				context.write(new Text("0"+"	"+userName), new Text(v.toString().getBytes("UTF-8")));
				//context.write(new Text(), new Text(sql.toString().getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
