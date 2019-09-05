package com.syntun.collect.price;

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

import com.syntun.collect.price.CollectReduce1;
import com.syntun.entity.Parameter;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConvertTools;

public class CollectReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;

	/**
	 * 用于控制该id当天促销出现了那些类型促销而出现的类
	 */
	public static HashMap<String, String> promotionIdMap = BaseDao.getTbaleList("syntun_etl.promotion_All_id_col",
			"promotion_id", "promotion_col_name");
	/**
	 * 促销替换内容集合
	 */
	public static List<HashMap<String, String>> promotionReplace = BaseDao.getPromotionReplace();
	public static List<List<HashMap<String, String>>> promotionPattern = BaseDao.getMJPatternList();

	/**
	 * 用于将满减中出现的其他促销类型进行替换
	 */
	public static List<List<HashMap<String, String>>> promotionP = BaseDao.getPromotionPatternList();
	public static HashMap<String, String> promotionType = null;
	public static List<String> filedList = null;

	static {
		HashMap<String, String> filter = new HashMap<String, String>();
		promotionType = BaseDao.getTbaleList("syntun_etl.price_promotion_compute", "platform_id", "promotion_type_name",
				"promotion_price", "promotion_price", filter);
	}

	/**
	 * 错误sql集合
	 */
	public static List<String> priceResult = null;

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		priceResult = new ArrayList<String>();
		String etlResultName = conf.get("tableName");
		if (etlResultName == null || etlResultName.equals("null")) {
			etlResultName = "";
		}
		//BaseDao.createEtlResult("syntun_etl.etl_result" + etlResultName);
		filedList = BaseDao.getField("syntun_etl." + etlResultName);
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
		String etlResultName = conf.get("tableName");
		if (etlResultName == null || etlResultName.equals("null")) {
			etlResultName = "";
		}
		String replaceStr = conf.get("replaceFiled");
		HashMap<String, String> replaceFiled = (HashMap<String, String>) ConvertTools
				.convertStrToMapNoReplace(replaceStr);

		HashMap<String, HashMap<String, List<Map<String, String>>>> priceNewData = new HashMap<String, HashMap<String, List<Map<String, String>>>>();
		
		HashSet<String> dayList = new HashSet<String>();
		for (Text content : value) {
			//
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMap(line, replaceFiled);
			if (map.get("syntun") == null) {
				return;
			}
			String nowDate = map.get("new_date");

			if (map.get("syntun").equals("price")) {
				if (map.get("table_name").indexOf("price") != -1) {
					if (map.get("product_price") != null && map.get("product_price").equals("-1")) {
						continue;
					}
				}
				HashMap<String, String> m1 = new HashMap<String, String>(map);
				m1.put("get_date", m1.get("new_date"));

				String skuId = m1.get("sku_id");
				if (skuId == null || skuId.equals("def") || skuId.equals("null") || skuId.equals("NULL")) {
					skuId = "-1";
				}
				
				HashMap<String, List<Map<String, String>>> dataList = new HashMap<String, List<Map<String, String>>>();
				if (priceNewData.containsKey(nowDate)) {
					dataList = priceNewData.get(nowDate);
				}
				List<Map<String, String>> dL = new ArrayList<Map<String, String>>();
				if (dataList.containsKey(skuId)) {
					dL = dataList.get(skuId);
				}
				dL.add(m1);
				dataList.put(skuId, dL);
				priceNewData.put(nowDate, dataList);
				dayList.add(nowDate);
			} else {
				
				
			}
		}

		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();

		if (dayList.size() > 0) {
			for (String date : dayList) {
				key = new Text("11:" + date + ":" + opertionProductId + ":" + platFormId);
				if (priceNewData.get(date) != null) {
					int n = 0;
					for (String skuId : priceNewData.get(date).keySet()) {
						if (n != 0){
							break;
						}
						List<Map<String, String>> li = priceNewData.get(date).get(skuId);
						HashMap<String, String> mVal = CollectReduce1.reduce(key, li, context);
						// 取算出的产品价格
						if (mVal != null) {
							resultList.add(mVal);
						}
						n = 1;
					}
				}
			}
		}

		if (resultList.size() > 0) {
			HashMap<String, String> m = resultList.get(0);
			StringBuffer val = new StringBuffer();
			val.append("syntun").append(Parameter.ZHIPLITE).append("price");
			for (String k : m.keySet()) {
				if (k.equals("table_name")){
					val.append(Parameter.LIESPLITE).append(k).append(":").append(m.get(k));
				}else{
					val.append(Parameter.LIESPLITE).append(k).append(Parameter.ZHIPLITE).append(m.get(k));
				}
			}
			
			try {
				//context.write(new Text(opertionProductId + ":" + platFormId + ":" + shopId), new Text(val.toString().getBytes("UTF-8")));
				context.write(new Text(), new Text(val.toString().getBytes("UTF-8")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		for (int i = 0; i < resultList.size(); i++) {
//			HashMap<String, String> m = resultList.get(i);
//			StringBuffer val = new StringBuffer();
//			val.append("syntun").append(Parameter.ZHIPLITE).append("price");
//			for (String k : m.keySet()) {
//				if (k.equals("table_name")){
//					val.append(Parameter.LIESPLITE).append(k).append(":").append(m.get(k));
//				}else{
//					val.append(Parameter.LIESPLITE).append(k).append(Parameter.ZHIPLITE).append(m.get(k));
//				}
//			}
//			
//			try {
//				//context.write(new Text(opertionProductId + ":" + platFormId + ":" + shopId), new Text(val.toString().getBytes("UTF-8")));
//				context.write(new Text(), new Text(val.toString().getBytes("UTF-8")));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
