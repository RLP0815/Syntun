package com.syntun.inspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.syntun.etl.tools.BaseDao;

public class ComputeLijian {

	//获取促销的计算公式
	public static HashMap<String, String> promotionTypeMap = null;

	static {
		HashMap<String, String> filter = new HashMap<String, String>();
		promotionTypeMap = BaseDao.getTbaleList("price_promotion_compute", "platform_id", "promotion_type_name",
				"promotion_price", "promotion_price", filter);
	}
		
	public static HashMap<String, Double> reduceLijian(HashMap<String, Double> priceMap, 
			List<HashMap<String, String>> LJpromotionMap, HashMap<String, String> key) throws IOException {
		HashMap<String, Double> LJpriceMap = new HashMap<String, Double>();
		double tempPrice = priceMap.get("tempPrice");
		double maxPrice = priceMap.get("maxPrice");
		
		// 错误处理相关
		String platFormId = key.get("platform_id");
		String shopId = key.get("shop_id");
		
		LinkedList<String> errValList = new LinkedList<String>();
		LinkedList<String> errFiledList = new LinkedList<String>();
		errFiledList.add("platform_id");
		errFiledList.add("operation_product_id");
		errFiledList.add("get_date");
		errFiledList.add("err_type_name");
		errFiledList.add("err_type_info");
		errFiledList.add("err_focus_information");
		
		double jian = 0;
		//促销处理
		for (HashMap<String, String> m : LJpromotionMap) {
			try {
				jian = Double.parseDouble(m.get("promotion_type_info"));//非满减类的促销信息为数字
			} catch (NumberFormatException e) {
				if(promotionTypeMap.containsKey(platFormId + m.get("promotion_type_name"))) {
					errValList.add(platFormId);
					errValList.add(key.get("operation_product_id"));
					errValList.add(key.get("get_date"));
					errValList.add("promotin_err");
					errValList.add("是需要计算的非满减促销，但是不能从里面抽取到价格");
					errValList.add(m.get("promotion_type_name"));

					String sql = DataSelection.insertMysql("err_result_copy", errFiledList, errValList, shopId);
					SkyscopeInspect.errResult.add(sql);
					errValList.clear();
				}
				continue;
			}
			
			tempPrice = tempPrice -jian; 
		}
		
		
		LJpriceMap.put("tempPrice", tempPrice);
		LJpriceMap.put("maxPrice", maxPrice);
		return LJpriceMap;
	}
}
