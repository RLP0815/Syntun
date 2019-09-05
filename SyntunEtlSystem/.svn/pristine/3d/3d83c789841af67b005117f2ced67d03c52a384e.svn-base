package com.syntun.price;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.syntun.entity.Parameter;
import com.syntun.etl.tools.SyntunDate;

/**
 * 处理价格相关的map类，主要功能：从price和promotion表中抽取需要的字段
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PriceMap {
	public static List<String> getField = new ArrayList<String>();

	static {
		getField.add("promotion_type_name");
		getField.add("promotion_type_info");
		
		
		getField.add("table_name");
 
		getField.add("product_price");
		getField.add("promotion_price");
		getField.add("sku_id");
		getField.add("shop_code");
		
		getField.add("product_name");
		getField.add("sort_name");
		getField.add("pinpai_name");
		getField.add("category_id");
		getField.add("brand_id");

	}
	
	public static String inEncode = "UTF-8";
	public static void map(String platFormId, Map<String, String> map, Context context) throws IOException {

		String lieSplit = "\001";
		String zhiSplit = "\002";
		Configuration conf = context.getConfiguration();
		// 平台名称（数据库中的名称，根据这个名称可以找到相应的平台id）
		// String paltFormName = map.get("table_name").split("\\.")[0];
		// 抓取日期
		String date = map.get("get_date");
		// main方法配置的参数中获取传人程序的日期范围
		String getDate = conf.get("date");
		// 起始日期buy_total表，只供第二天补数使用，所以如果是起始日期的数据，不进行当天数据到reduce
		if (!SyntunDate.isData(getDate, date)) {
			return;
		}
		StringBuffer val = new StringBuffer();
		int i = 0;
		String opertionProductId = map.get("product_id");
//		String endShopCode = "";
		if (platFormId.equals("3")) {
			opertionProductId = map.get("partNumber_id");
		}
		if (opertionProductId == null || opertionProductId.equals("null")) {
			return;
		}

		// 苏宁和亚马逊特殊处理
//		if (map.get("shop_code") != null && !map.get("shop_code").equals("")
//				&& (platFormId.equals("3") || platFormId.equals("10"))) {
//			endShopCode = map.get("shop_code");
//		}
		for (String filed : getField) {
			String str = map.get(filed);
			if (filed.equals("promotion_price")) {
				if (str == null || str.equals("-1.00") || str.equals("-1")) {
					str = null;
				}
			}
			if (i == 0 && str != null) {
				val.append(filed).append(zhiSplit).append(str);
				i = 1;
			} else if (str != null) {
				val.append(lieSplit).append(filed).append(zhiSplit).append(str);
			}
		}
		val.append(lieSplit).append("platform_id").append(zhiSplit).append(platFormId);
		// 给数据添加标记，表示此数据用于price计算
		val.append(lieSplit).append("syntun").append(zhiSplit).append("price");
		val.append(Parameter.LIESPLITE).append("new_date").append(Parameter.ZHIPLITE).append(date);
		try {
			context.write(new Text(opertionProductId + ":" + platFormId),
					new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}