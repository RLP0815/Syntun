package com.syntun.etl.tools;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.syntun.inspect.DataSelection;

public class GetPromotionPrice {

	//获取替换字段，将满减促销中的数据进行替换，使数据进行统一化
	public static List<HashMap<String, String>> promotionReplace = BaseDao.getPromotionReplace();
	//获取满减正则
	public static List<List<HashMap<String, String>>> promotionPattern = BaseDao.getMJPatternList();
	//
	public static List<List<HashMap<String, String>>> promotionP = BaseDao.getPromotionPatternList();
		
	public static List<HashMap<String, String>> promotionSpecial = BaseDao.getTbaleList("promotion_special");

	public static HashMap<String, HashMap<String, String>> PromotionCompute = BaseDao.getPromotionCompute();

	/**
	 * 非满减
	 * 获取促销价和价格方法，根据price_promotion_compute表中记录的数据进行取值例如
	 * 平台：已优惠：price=price：promotion=price+promtion
	 * 类似的数据会记录在表中，根据这些信息进行取值还有最后的结果输出
	 * 
	 * @param tempPrice
	 *            抓取到的价格
	 * @param promotion
	 *            促销内容
	 * @return
	 * @throws NumberFormatException
	 */
	public static HashMap<String, Double> getPrice(double tempPrice, String platFormId,
			HashMap<String, String> promotion) throws NumberFormatException {
		HashMap<String, Double> priceMap = new HashMap<String, Double>();
		HashMap<String, String> computeMap = PromotionCompute
				.get(platFormId + "/" + promotion.get("promotion_type_name"));
		if (computeMap == null || computeMap.size() == 0) {
			return null;
		}
		String computePromotion = (computeMap.get("promotion_price")).replace("price", tempPrice + "")
				.replace("promtion", promotion.get("promotion_type_info"));
		String computePrice = (computeMap.get("price")).replace("price", tempPrice + "").replace("promtion",
				promotion.get("promotion_type_info"));
		priceMap.put("promotion_price", Calculator.sizeyunsuan(computePromotion));
		priceMap.put("price", Calculator.sizeyunsuan(computePrice));
		return priceMap;
	}

	/**
	 * 获取促销价和价格方法，根据price_promotion_compute表中记录的数据进行取值例如
	 * 平台：已优惠：price=price：promotion=price+promtion
	 * 类似的数据会记录在表中，根据这些信息进行取值还有最后的结果和传入的价格进行比较，输出最大价格
	 * 
	 * @param tempPrice
	 *            抓取到的价格
	 * @param promotion
	 *            促销内容
	 * @return
	 * @throws NumberFormatException
	 */
	public static Double getMaxPrice(double tempPrice, HashMap<String, String> promotion) throws NumberFormatException {
		HashMap<String, String> computeMap = PromotionCompute
				.get(promotion.get("platform_id") + "/" + promotion.get("promotion_type_name"));
		if (computeMap == null || computeMap.size() == 0) {
			return null;
		}
		String computePrice = (computeMap.get("price")).replace("price", tempPrice + "").replace("promtion",
				promotion.get("promotion_type_info"));
		if (tempPrice >= Calculator.sizeyunsuan(computePrice)) {
			return tempPrice;
		} else {
			return Calculator.sizeyunsuan(computePrice);
		}
	}

	/**
	 * 替换方法，将满减促销中的数据进行替换，使数据进行统一化
	 * 
	 * @param str
	 *            促销内容
	 * @return
	 */
	public static String replacePromotionInfo(String str) {
		String replacestr = str;
		for (HashMap<String, String> map : promotionReplace) {
			for (String key : map.keySet()) {
				replacestr = replacestr.replace(key, map.get(key));
			}
		}
		return replacestr;
	}

	// public static HashMap<String, String> special = new HashMap<String,
	// String>();
	// static {
	// List<String> list = new ArrayList<String>();
	// list.add("每满(￥|&yen;\\?)?(\\d+)\\S*?(减|-|返|省)(现金|￥|去|&yen;|\\?)?(\\d+)[\\S\\s]*?,2,5");
	// list.add("最高减(\\d+)元,1");
	// list.add("最多可减(\\d+)元,1");
	// promotionPattern.add(list);
	// list = new ArrayList<String>();
	// list.add("满(￥|&yen;\\?)?(\\d+)\\S*?(减|-|返|省)(现金|￥|去|&yen;|\\?)?(\\d+)[\\S\\s]*?,2,5");
	// promotionPattern.add(list);
	// list = new ArrayList<String>();
	// list.add("每(\\d+)直?降(\\d+),1,2");
	// list.add("最高降(\\d+),1");
	// list.add("最高直降(\\d+)元,1");
	// list.add("最高立减(\\d+)元,1");
	// promotionPattern.add(list);
	// list = new ArrayList<String>();
	// list.add("满(\\d+)直?降(\\d+),1,2");
	// promotionPattern.add(list);
	// list = new ArrayList<String>();
	// list.add("(\\d+)直降(\\d+),1,2");
	// promotionPattern.add(list);
	// list = new ArrayList<String>();
	// list.add("(\\d+)降(\\d+),1,2");
	// promotionPattern.add(list);
	// list = new ArrayList<String>();
	// list.add("满(￥|&yen;\\?)?(\\d+)\\S*?(享|打)(\\d*?\\.?\\d*?)折,2,4");
	// promotionPattern.add(list);
	// 特殊处理的值
	// special.put("最高立减(\\d+),1", "4");
	// special.put("直降(\\d+)元,1", "3");
	// special.put("加入购物车直降(\\d+),1", "3");
	// }

	/**
	 * 每满减 满减促销的处理
	 * 
	 * @param promotion
	 *            促销
	 * @return
	 * @throws NumberFormatException
	 *             将促销内容中的价格转换的时候如果出现错误就抛出
	 * @throws UnsupportedEncodingException
	 */
	public static String getPromotionType(HashMap<String, String> promotion)
			throws NumberFormatException, UnsupportedEncodingException {
		String promotionTypeInfo = replacePromotionInfo(promotion.get("promotion_type_info"));
		for (List<HashMap<String, String>> li : promotionP) {
			String patternStr = new String(Base64.decode(li.get(0).get("pattern_str")));
			Pattern p = Pattern.compile(patternStr);
			Matcher m = p.matcher(promotionTypeInfo);
			while (m.find()) {
				return li.get(0).get("replace_promotion_type");
			}
		}
		return "0";
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new String(Base64.decode("5ruhXFMqP+WFg++8jOmAgVxTKj/liLg=")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 每满减 满减促销的处理
	 * 
	 * @param tempPrice
	 *            临时价格
	 * @param promotion
	 *            促销
	 * @param mos
	 * @param key
	 * @return
	 * @throws NumberFormatException
	 *             将促销内容中的价格转换的时候如果出现错误就抛出
	 */
	public static Double getMJPrice(double tempPrice, HashMap<String, String> promotion, HashMap<String,String> key, boolean isErr) {
		double endPrice = 0;
		try {
			// 错误处理相关
			String platFormId = key.get("platForm_id");
			LinkedList<String> errFiledList = new LinkedList<String>();
			errFiledList.add("platform_id");
			errFiledList.add("operation_product_id");
			errFiledList.add("get_date");
			errFiledList.add("err_type_name");
			errFiledList.add("err_type_info");
			errFiledList.add("err_focus_information");
			LinkedList<String> errValList = new LinkedList<String>();
			
			String promotionTypeInfo = replacePromotionInfo(promotion.get("promotion_type_info"));
			boolean isPattern = true;
			boolean isData = true;
			boolean ispromotionSpecial = false;
			double d = 0;
			for (HashMap<String, String> map : promotionSpecial) {
				if (map.get("platform_id").equals(platFormId) && map.get("promotion_info").equals(promotionTypeInfo)) {
					if (!map.get("promotion_first").equals("")) {
						if (tempPrice >= Integer.parseInt(map.get("promotion_first").split(",")[0])) {
							d = tempPrice - Integer.parseInt(map.get("promotion_first").split(",")[1]);
						}
					}
					if (!map.get("promotion_second").equals("")) {
						if (tempPrice >= Integer.parseInt(map.get("promotion_second").split(",")[0])) {
							double d1 = tempPrice - Integer.parseInt(map.get("promotion_second").split(",")[1]);
							if (d1 < d) {
								d = d1;
							}
						}
					}
					ispromotionSpecial = true;
				}
			}
			if (ispromotionSpecial) {
				return d;
			}
			
			//记录一个方式。。人为调控  每满减促销。 满3000 减300。。。满1000 减 100    东西           只卖300       300*4  1200   -2700/10--270
			for (List<HashMap<String, String>> li : promotionPattern) {
				if (!isPattern) {
					return endPrice;
				}
				double maxJPrice = 0;
				// 判断是否是每满的促销
				boolean ismeiMan = false;
				if (li.get(0).get("is_meiman").equals("1")) {
					ismeiMan = true;
					for (int i = 1; i < li.size(); i++) {
						String patternStr = new String(Base64.decode(li.get(i).get("pattern_str")));
						Pattern p = Pattern.compile(patternStr);
						Matcher m = p.matcher(promotionTypeInfo);
						while (m.find()) {
							double temmaxJPrice = Double
									.parseDouble(m.group(Integer.parseInt(li.get(i).get("man_group"))));
							if (temmaxJPrice > maxJPrice) {
								maxJPrice = temmaxJPrice;
							}
						}
					}
				}
				String patternStr = new String(Base64.decode(li.get(0).get("pattern_str")));
				Pattern p = Pattern.compile(patternStr);
				Matcher m = p.matcher(promotionTypeInfo);
				while (m.find()) {
					isData = false;
					double manprice = 0;
					double jieprice = 0;
					manprice = Double.parseDouble(m.group(Integer.parseInt(li.get(0).get("man_group"))));
					jieprice = Double.parseDouble(m.group(Integer.parseInt(li.get(0).get("jie_group"))));
					if (tempPrice < manprice) {
						continue;
					}
					// 如果是每满减
					if (ismeiMan) {
						// 计算出当前减的最大金额
						double temp = (int) (tempPrice / manprice) * jieprice;
						// 判断是否超出最大减的金额，如果超出使用最大减金额
						if (maxJPrice != 0) {
							temp = temp > maxJPrice ? maxJPrice : temp;
						}
						if (endPrice != 0) {
							endPrice = endPrice > (tempPrice - temp) ? (tempPrice - temp) : endPrice;
							isPattern = false;
						} else {
							endPrice = tempPrice - temp;
							isPattern = false;
						}
					} else if (li.get(0).get("is_dazhe").equals("1")) {
						// 计算出当前减的最大金额
						double temp = tempPrice * jieprice / 10;
						// 判断是否超出最大减的金额，如果超出使用最大减金额
						if (endPrice != 0) {
							endPrice = endPrice > temp ? temp : endPrice;
							isPattern = false;
						} else {
							endPrice = temp;
							isPattern = false;
						}
					} else {
						if (endPrice != 0) {
							isPattern = false;
							endPrice = endPrice > (tempPrice - jieprice) ? (tempPrice - jieprice) : endPrice;
						} else {
							isPattern = false;
							endPrice = tempPrice - jieprice;
						}
					}
				}
			}
			if (isData && isErr) {
				errValList.add(platFormId);
				errValList.add(key.get("operation_"));
				errValList.add(key.get("get_date"));
				errValList.add("MJ_err");
				errValList.add("该满减程序不能解析，新增满减内容，或者抓取错误" + promotionPattern.size());
				errValList.add(promotionTypeInfo);
//				String sql = PriceReduce.insertMysql("err_result", errFiledList, errValList, shopId);
//				PriceReduce.errResult.add(sql);
				errValList.clear();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return endPrice;
	}
}