package com.syntun.inspect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.syntun.entity.PricePromotionInfo;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.SyntunDate;

public class DataSelection {
	//获取替换字段，将满减促销中的数据进行替换，使数据进行统一化
	//public static List<HashMap<String, String>> promotionReplace = BaseDao.getPromotionReplace();
	//获取满减正则
	//public static List<List<HashMap<String, String>>> promotionPattern = BaseDao.getMJPatternList();
	//
	//public static List<List<HashMap<String, String>>> promotionP = BaseDao.getPromotionPatternList();
	
	//促销对照表
//	public static List<HashMap<String, String>> promotionMJ = BaseDaoSqlServer.getpromotionMJList("[skyscope].[dbo].[促销对照表]");
	
	//获取需要计算的促销类型
//	public static HashMap<String, String> promotionCompare = BaseDao.getTbaleList("skyscope_etl.promotion_compare", "platform_id",
//					"promotion_type_name", "promotion_id");

	//获取促销类型
//	public static HashMap<String, String> promotionCompareType = BaseDao.getTbaleList("skyscope_etl.promotion_compare", "platform_id",
//					"promotion_type_name", "promotion_type");
					
	//public static HashMap<String, String> filter = new HashMap<String, String>();
	//获取需要归类的促销类型
	//public static List<HashMap<String, String>> promotionRank = BaseDao.getAllTbaleList("skyscope_etl.promotion_rank", filter);
	
//	public static List<HashMap<String, String>> promotionMZ = BaseDaoSqlServer.getpromotionMJList("[skyscope].[dbo].[折对照表]");
	// 读取结果表的字段列表
	public static List<String> ZKFiledList = BaseDaoSqlServer.getField16("[skyscope].[dbo].[折扣未处理_copy]", "192.168.0.16", "skyscope");
	public static List<String> MZFiledList = BaseDao.getField138("syntun_v2.满折处理");
	public static List<String> MZNFiledList = BaseDao.getField138("syntun_v2.满折未处理");
	public static List<String> MJFiledList = BaseDao.getField138("syntun_v2.满减处理");
	public static List<String> MJNFiledList = BaseDao.getField138("syntun_v2.满减未处理");
	
	public static PricePromotionInfo reduce(List<HashMap<String, String>> priceD, boolean no,
			HashMap<String, String> key, String[] orderList, List<String> promotionFiledList, 
			String groupId, String userId, HashMap<String, List<HashMap<String, String>>> tmallPromotion, 
			String date, HashMap<String, String> promotionCompareType,
			List<HashMap<String, String>> promotionMJ,
			List<HashMap<String, String>> promotionMZ,
			List<HashMap<String, String>> promotionShouji,
			HashMap<String, String> promotionSuningBoxi) throws IOException {
		
		double shoujiPrice = 0;
		if(promotionShouji != null && promotionShouji.size() != 0){
			for(HashMap<String, String> map : promotionShouji){
				try {
					shoujiPrice = Double.parseDouble(map.get("promotion_type_info"));
				} catch (Exception e) {
					System.out.println("苏宁手机采集价格转换出错，promotion_type_info为："+ map.get("promotion_type_info"));
				}
			}
		}
		// 价格集合
		List<String> priceList = new ArrayList<String>();
		// 促销集合
		List<HashMap<String, String>> promotionMap = new ArrayList<HashMap<String, String>>();
		
		// 内容字符串对象，用于错误时输出，便于人员进行人工识辨
		StringBuffer errsb = new StringBuffer(key.toString());
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
		
		//价格，促销集合
		List<HashMap<String, String>> priceInfo = new ArrayList<HashMap<String, String>>(priceD);
		// 到手价，集合
		List<HashMap<String, String>> DSpromotionMap = new ArrayList<HashMap<String, String>>();
		// 粉丝价，集合
		List<HashMap<String, String>> FSpromotionMap = new ArrayList<HashMap<String, String>>();
		// 会员价，集合
		List<HashMap<String, String>> HYpromotionMap = new ArrayList<HashMap<String, String>>();
		// 定金团，集合，只有一条促销
		HashMap<String, String> DJTpromotionMap = new HashMap<String, String>();
		// 预售集合
		List<HashMap<String, String>> YSpromotionMap = new ArrayList<HashMap<String, String>>();
		// 非满减集合
		List<HashMap<String, String>> NMJpromotionMap = new ArrayList<HashMap<String, String>>();
		// 满减集合
		List<HashMap<String, String>> MJpromotionMap = new ArrayList<HashMap<String, String>>();
		// 立减集合
		List<HashMap<String, String>> LJpromotionMap = new ArrayList<HashMap<String, String>>();
		// 满折集合
		List<HashMap<String, String>> MZpromotionMap = new ArrayList<HashMap<String, String>>();
		// 领券集合
		List<HashMap<String, String>> LQpromotionMap = new ArrayList<HashMap<String, String>>();
		// tmall特殊促销集合
		List<HashMap<String, String>> TmallpromotionMap = new ArrayList<HashMap<String, String>>();
		
		// 促销内容去重依据，将promotion_type_name和promotion_type_info组合放入list中，如果list中有该促销内容就不添加
		List<String> uniquePromotion = new ArrayList<String>();
					
		// 错误处理相关
		for (Map<String, String> map : priceInfo) {
			// try {=
			if (map.get("table_name") != null && map.get("table_name").contains("PRICE_PROMOTION_INFO")) {
				
				//京东平台  shop_name此字段加了优惠券的时间限制
				if (platFormId.equals("1") && map.get("promotion_type_name").equals("领券") 
						&& map.get("shop_name").indexOf(":") != -1) {
					boolean isTimeOut = SyntunDate.isTimeOut(map.get("shop_name"), date);
					if (!isTimeOut)
						continue;
				}
				//促销类型或者促销详情为空的跳过
				if (map.get("promotion_type_info") == null || map.get("promotion_type_info").equals("")
						|| map.get("promotion_type_info").toLowerCase().equals("null")
						|| map.get("promotion_type_name") == null) {
					continue;
				}
				//promotion_type_name为会员价，promotion_type_info 为-1的不计算;
				if (map.get("promotion_type_name").equals("会员价") && map.get("promotion_type_info").equals("-1")) {
					continue;
				}
				
				//promotion_about_info为true跳过;
				if (map.get("promotion_about_info")!= null && map.get("promotion_about_info").equals("true")) {
					continue;
				}
				//Status=0,1,4-1是有效数据（促销），其他数据不做处理
//				if (platFormId.equals("3") && duizhaoData.get(map.get("operation_product_id") + "\001" + map.get("shop_code")) == null) {
//					continue;
//				}
				//苏宁
				if (platFormId.equals("3") && map.get("promotion_type_name").equals("大聚惠")) {
					continue;
				}
				//国美
				if (platFormId.equals("4") && map.get("promotion_type_info").indexOf("满") != -1
						&& map.get("promotion_type_info").indexOf("减") != -1) {
					map.put("promotion_type_name", "满减");
				}
				//京东
				if (platFormId.equals("1") && map.get("promotion_type_name").equals("满减")) {
					String pInfo = map.get("promotion_type_info").replace("即可购买热销商品   需另加", "");
					map.put("promotion_type_info", pInfo);
				}
				
				//判断是否为需要计算的促销类型
				String proType = promotionCompareType
						.get(map.get("platform_id") + "\001" + map.get("promotion_type_name"));
				if (proType != null) {
					//促销集合添加
					promotionMap.add((HashMap<String, String>) map);
					errsb.append(map.get("promotion_type_name")).append("~~~~");
					
				} else {
					HashMap<String, String> filedMap = new HashMap<String, String>();
					
					filedMap.put("promotion_id", map.get("promotion_id"));
					filedMap.put("promotion_type_name", map.get("promotion_type_name"));
					filedMap.put("promotion_type_info", map.get("promotion_type_info"));
					filedMap.put("promotion_about_info", map.get("promotion_about_info"));
					filedMap.put("shop_name", map.get("shop_name"));
					filedMap.put("operation_product_id", map.get("operation_product_id"));
					filedMap.put("product_id", map.get("product_id"));
					filedMap.put("shop_id", map.get("shop_id"));
					filedMap.put("platform_id", map.get("platform_id"));
					filedMap.put("hour", map.get("hour"));
					filedMap.put("min", map.get("min"));
					filedMap.put("get_date", map.get("get_date"));
					filedMap.put("C_ID", map.get("C_ID"));
					filedMap.put("shop_code", map.get("shop_code"));
					filedMap.put("sku_id", map.get("sku_id"));
					filedMap.put("city_id", map.get("city_id"));
					String sql = ConvertSql.getSql("promotion_compare_info_copy", promotionFiledList, filedMap);
					// System.out.println(etlResult.size() + "___" + sql);
					SkyscopeInspect.promotionCompareInfo.add(sql);
					continue;
				}
				
			} else {
				if (map.get("promotion_price") != null && !map.get("promotion_price").equals("")) {
					try {
						String priStr = map.get("promotion_price").replace("￥", "").replace(",", "").replace(" ", "");
						priceList.add(priStr);
					} catch (NumberFormatException e) {
						errValList.add(platFormId);
						errValList.add(key.get("operation_product_id"));
						errValList.add(key.get("get_date"));
						errValList.add("price_err");
						errValList.add("是价格表但是取不到price,后面是获取日期");
						errValList.add(map.get("promotion_price"));
						String sql = insertMysql("err_result_copy", errFiledList, errValList, shopId);
						SkyscopeInspect.errResult.add(sql);
						errValList.clear();
					}
				} else {
					errValList.add(platFormId);
					errValList.add(key.get("operation_product_id"));
					errValList.add(key.get("get_date"));
					errValList.add("price_err");
					errValList.add("是价格表但是取不到price,后面是获取日期");
					errValList.add("");
					String sql = insertMysql("err_result_copy", errFiledList, errValList, shopId);
					SkyscopeInspect.errResult.add(sql);
					errValList.clear();
				}
			}
			
		}
		
		//遍历促销集合
		for (HashMap<String, String> m : promotionMap) {
			if (uniquePromotion.contains(m.get("promotion_type_name") + m.get("promotion_type_info"))) {
				continue;
			}
			String promotionPrice = "";
			//获取促销类型
			String promotionType = promotionCompareType.get(m.get("platform_id") + "\001" + m.get("promotion_type_name"));
			//苏宁平台促销名称是“1”的，可能是“满折”也可能是“满减”。
			if(m.get("platform_id").equals("3") 
					&& m.get("promotion_type_name").equals("1")
					&& m.get("promotion_type_info").indexOf("折") != -1){
				promotionType = "满折";
			}
			//到手价，集合
			if (promotionType != null && promotionType.equals("到手价")) {
				DSpromotionMap.add(m);
			}
			//粉丝价，集合
			if (promotionType != null && promotionType.equals("粉丝价")) {
				FSpromotionMap.add(m);
			}
			//会员价，集合
			if (promotionType != null && promotionType.equals("会员价")) {
				HYpromotionMap.add(m);
			}
			//定金团，集合
			if (promotionType != null && promotionType.equals("定金团")) {
				DJTpromotionMap.putAll(m);
			}
			//预售集合
			if (promotionType != null && promotionType.equals("预售")) {
				YSpromotionMap.add(m);
			}
			//非满减集合
			if (promotionType != null && promotionType.equals("非满减")) {
				//对促销内容进行处理   团购价     10.2fad
				promotionPrice = getLijiePrice(m.get("promotion_type_info"));
				if (!promotionPrice.equals("")) {
					m.put("promotion_type_info", promotionPrice);
				}
				NMJpromotionMap.add(m);
			}
			//立减集合
			if (promotionType != null && promotionType.equals("立减")) {
				LJpromotionMap.add(m);
			}
			//满折集合
			if (promotionType != null && promotionType.equals("满折")) {
				MZpromotionMap.add(m);
			}
			//领券集合
			if (promotionType != null && promotionType.equals("领券")) {
				LQpromotionMap.add(m);
			}
			//满减集合
			if (promotionType != null && promotionType.equals("满减")) {
				MJpromotionMap.add(m);
			}
			//Tmall特殊促销集合
			if (promotionType != null && promotionType.equals("天猫特殊促销")) {
				TmallpromotionMap.add(m);
			}
			uniquePromotion.add(m.get("promotion_type_name") + m.get("promotion_type_info"));
		}
		//添加天猫特殊促销
		if (tmallPromotion.containsKey(shopId)){
			TmallpromotionMap.addAll(tmallPromotion.get(shopId));
		}
		
		/*
		 * 开始价格计算
		 */
		if (priceList.size() > 0) {
			PricePromotionInfo promotionInfo = new PricePromotionInfo();
			//System.out.println(priceInfo);
			// 计算促销用的价格
			double tempPrice = getMinPrice(priceList, false);
			// 零售价取最大
			double maxPrice = getMinPrice(priceList, false);
			
			//传递给各计算模块的集合
			HashMap<String, Double> priceMap = new HashMap<String, Double>();
			priceMap.put("tempPrice", tempPrice);
			priceMap.put("maxPrice", maxPrice);
			
			//伊利客户（101040）；粉丝价，计算
			if(userId.equals("101040") && FSpromotionMap.size() > 0){
				priceMap.putAll(ComputeNoManjian.reduceNoManjian(priceMap, FSpromotionMap, key, userId));
			}
			//博西用户（244）；定价团，计算,promotion_type_info=1049.00:49.00
			if(userId.equals("244") && DJTpromotionMap.size() > 0){
				String info = DJTpromotionMap.get("promotion_type_info");
				if(info != null && info.split(":").length == 2){
					try {
						double tPrice = priceMap.get("tempPrice");
						double mPrice = Double.parseDouble(info.split(":")[0])-Double.parseDouble(info.split(":")[1]);
						priceMap.put("tempPrice", tPrice-mPrice);
					} catch (Exception e) {
						System.out.println("定价团计算出错，促销内容："+info);
					}
				}
			}
			//博西用户（244）；会员价，计算
			if(userId.equals("244") && HYpromotionMap.size() > 0){
				priceMap.putAll(ComputeNoManjian.reduceNoManjian(priceMap, HYpromotionMap, key, userId));
			}
			//预售，计算
			if(YSpromotionMap.size() > 0){
				priceMap.putAll(ComputeYuShou.reduceYuShou(priceMap, YSpromotionMap, key));
			}
			for(String order : orderList){
				if(order.equals("非满减") && NMJpromotionMap.size() > 0){
					priceMap.putAll(ComputeNoManjian.reduceNoManjian(priceMap, NMJpromotionMap, key, userId));
				}
				if(order.equals("满减") && MJpromotionMap.size() > 0){
					priceMap.putAll(ComputeManjian.reduceManjian(priceMap,MJpromotionMap,key,groupId,userId,promotionMJ,promotionSuningBoxi));
				}
				if(order.equals("满折") && MZpromotionMap.size() > 0){
					priceMap.putAll(ComputeManzhe.reduceManzhe(priceMap, MZpromotionMap, key, groupId, userId, promotionMZ));
				}
				if(order.equals("立减") && LJpromotionMap.size() > 0){
					priceMap.putAll(ComputeLijian.reduceLijian(priceMap, LJpromotionMap, key));
				}
				if(order.equals("领券") && LQpromotionMap.size() > 0){
					priceMap.putAll(ComputeLingquan.reduceLingquan(priceMap, LQpromotionMap, key, groupId, userId, promotionMJ));
				}
			}
			//天猫特殊促销
			if(TmallpromotionMap.size() > 0){
				priceMap.putAll(ComputeTmall.reduceTmall(priceMap, TmallpromotionMap, key));
			}
			/*
			 * 如果计算完的苏宁价格比手机价高，则用手机价替换苏宁及算完之后的价格，反之，不做任何处理！！
			 */
			if(shoujiPrice != 0 && priceMap.get("tempPrice") > shoujiPrice){
				priceMap.put("tempPrice", shoujiPrice);
			}
			//博西用户（244）；到手价，计算
			if(userId.equals("244") && DSpromotionMap.size() > 0){
				priceMap.putAll(ComputeDaoShouJia.reduceDaoShouJia(priceMap, DSpromotionMap, key, userId));
			}
			// 促销价格
			double promotionTemp = priceMap.get("tempPrice");
			// 零售价取
			double priceTemp = priceMap.get("maxPrice");
			double priceOff = 1;
			if (promotionTemp == priceTemp) {
				promotionTemp = 0;
			} else {
				priceOff = promotionTemp / priceTemp;
			}
			if (priceOff != 1) {
				promotionInfo.setPriceOff(priceOff);
			}
			if (promotionTemp != 0) {
				promotionInfo.setPromotionPrice(promotionTemp + "");
			}
			promotionInfo.setLingshoujia(priceTemp + "");
			
			return promotionInfo;
		} else {
			errValList.add(platFormId);
			errValList.add(key.get("operation_product_id"));
			errValList.add(key.get("get_date"));
			errValList.add("price_err");
			errValList.add("当天没有抓到一条价格，后面当天抓取到的促销类型");
			errValList.add(errsb.toString());
			String sql = insertMysql("err_result_copy", errFiledList, errValList, shopId);
			SkyscopeInspect.errResult.add(sql);
			errValList.clear();
			return null;
		}
	}
	public static String getLijiePrice(String promotionPrice) {
		try {
			Pattern p = Pattern.compile("([\\d\\.]+)\\D*?");
			Matcher m = p.matcher(promotionPrice);
			if (m.find()) {
				return m.group(1);
			}
		} catch (NullPointerException e) {
			System.out.println("~~~~~~~~~~~~~~~~" + promotionPrice == null + "~~~~~~~~~" + promotionPrice);
		}
		return promotionPrice;
	}
	
	public static double getMinPrice(List<String> list, boolean isMax) throws IOException {
		double Price = 0;
		for (int i = 0; i < list.size(); i++) {
			double nowPrice = 0;
			String str = list.get(i);
//			if(str.equals("-1")||str.equals("-1.0")||str.equals("-1.00")||str.equals("null")){
//				return Price;
//			}
			try {
				if (str.split("-").length == 2) {
					if (!str.split("-")[0].equals(""))
						nowPrice = getReplaceDouble(str.split("-")[0]);
				} else {
					nowPrice = getReplaceDouble(str.replace(",", ""));
				}
			} catch (Exception e) {
				if (str.split("-").length == 2) {
					if (!str.split("-")[0].equals(""))
						System.out.println("转换出错的字段："+str.split("-")[0]);
				} else {
					System.out.println("转换出错的字段："+str.replace(",", ""));
				}
				e.printStackTrace();
				continue;
			}
			
			if (i == 0) {
				if (str.split("-").length == 2) {
					if (!str.split("-")[0].equals(""))
						Price = getReplaceDouble(str.split("-")[0]);
				} else {
					Price = getReplaceDouble(str.replace(",", ""));
				}
			} else if (isMax) {
				if (nowPrice > Price) {
					Price = nowPrice;
				}
			} else {
				if (nowPrice < Price) {
					Price = nowPrice;
				}
			}
		}
		return Price;
	}
	
	public static double getReplaceDouble(String priceStr) {
		return Double.parseDouble(
				priceStr.replace(",", "").replace("''''http://union.dangdang.com/transfer/transfer.aspx?from=535", ""));
	}
	
	public static String insertMysql(String tableName, LinkedList<String> fields, LinkedList<String> values,
			String shopId) {
		String sql = "insert into  " + tableName + "(";
		int i = 0;
		for (String field : fields) {
			if (i > 0) {
				sql += ",";
			}
			sql += "`" + field + "`";
			i++;
		}
		if (tableName.equals("syntun_etl.err_result_copy")) {
			sql += ",`shop_id`";
		}
		sql += ") values(";
		i = 0;
		for (String value : values) {
			if (i > 0) {
				sql += ",";
			}
			if (value != null && !value.equals("") && !value.equals("null")) {
				sql += "'" + value + "'";
			} else {
				sql += "NULL";
			}
			i++;
		}
		if (tableName.equals("syntun_etl.err_result_copy")) {
			sql += ",'" + shopId + "'";
		}
		sql += ");";
		return sql;
	}
	
	public static void main(String[] args) {  
		String promotionTypeInfo = "满99元减10元，满400元减40元";
		System.out.println(promotionTypeInfo.equals("满99元减10元，满400元减40元"));
	}
}
