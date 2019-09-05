package com.syntun.etl.tools;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

import com.syntun.collect.price.CollectReduce;

public class GetPromotionPrice {

	public static List<HashMap<String, String>> promotionSpecial = BaseDao.getTbaleList("syntun_etl.promotion_special");

	public static HashMap<String, HashMap<String, String>> PromotionCompute = BaseDao.getPromotionCompute();

	/**
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
		for (HashMap<String, String> map : CollectReduce.promotionReplace) {
			for (String key : map.keySet()) {
				replacestr = replacestr.replace(key, map.get(key));
			}
		}
		return replacestr;
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
	public static Double getMJPrice(double tempPrice, HashMap<String, String> promotion, Text key, boolean isErr) {
		double endPrice = 0;
		try {
			// 错误处理相关
			String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
//			String shopId = null;
//			if (shareValues.length > 4) {
//				shopId = shareValues[4];
//			}
			String platFormId = shareValues[3];
			LinkedList<String> errFiledList = new LinkedList<String>();
			errFiledList.add("platform_id");
			errFiledList.add("operation_product_id");
			errFiledList.add("get_date");
			errFiledList.add("err_type_name");
			errFiledList.add("err_type_info");
			errFiledList.add("err_focus_information");
			
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
			for (List<HashMap<String, String>> li : CollectReduce.promotionPattern) {
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
				
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return endPrice;
	}
}
