package com.syntun.price;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;

import com.syntun.entity.PricePromotionInfo;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConvertTools;
import com.syntun.etl.tools.GetPromotionPrice;

public class PriceReduce {
	public static List<HashMap<String, String>> mjType = BaseDao.getTbaleList("syntun_etl.new_MJ_AddType");
	public static List<String> promotionPatlform = BaseDao.getTbaleList("syntun_etl.promotion_platform", "platform_id");

	@SuppressWarnings("rawtypes")
	public static PricePromotionInfo reduce(Text key, List<Map<String, String>> values, Context context, boolean no)
			throws IOException {

		HashMap<String, String> promotionIdMap = new HashMap<String, String>();
		// 价格集合
		List<String> priceList = new ArrayList<String>();
		// 促销集合
		List<HashMap<String, String>> promotionMap = new ArrayList<HashMap<String, String>>();
		// 促销类型集合
		StringBuffer promotionIds = new StringBuffer();
		
		// 错误处理相关
		String[] shareValues = new String(key.getBytes(), 0, key.getLength(), "UTF-8").split(":");
		String platFormId = shareValues[3];
//		String shopId = null;
//		if (shareValues.length > 4) {
//			shopId = shareValues[4];
//		}
		Map<String, String> promotionCompare = ConvertTools
				.convertStrToMapNoReplace(context.getConfiguration().get("promotion_compare"));
		Double yaoPromotionPrice = null;

		String productName = null;
		String sortName = null;
		String pinpaiName = null;
		String categoryId = null;
		String brandId = null;
		String shopId = null;
		
		// 错误处理相关
		for (Map<String, String> map : values) {
			// try {=
			if (map.get("table_name") != null && map.get("table_name").contains("promotion_info")) {
				if (map.get("promotion_type_info") == null || map.get("promotion_type_name") == null) {
					continue;
				}
				if (platFormId.equals("4") && map.get("promotion_type_info").indexOf("满") != -1
						&& map.get("promotion_type_info").indexOf("减") != -1) {
					map.put("promotion_type_name", "满减");
				}

				if (platFormId.equals("1") && map.get("promotion_type_name").equals("满减")) {
					String pInfo = map.get("promotion_type_info").replace("即可购买热销商品   需另加", "");
					map.put("promotion_type_info", pInfo);
				}

				String promotionInfo = promotionCompare.get(map.get("platform_id") + map.get("promotion_type_name"));
				if (promotionInfo != null) {
					
					// 促销内容为空
					if (map.get("promotion_type_info") == null || map.get("promotion_type_info").equals("")
							|| map.get("promotion_type_info").toLowerCase().equals("null")) {
						
						continue;
					}
					
					String promotionId = promotionInfo.split("/")[0];
					// 促销类型去重
					if (promotionIdMap.containsKey(promotionId)) {
						promotionIdMap.put(promotionId,
								promotionIdMap.get(promotionId) + "," + map.get("promotion_type_info"));

					} else {
						promotionIdMap.put(promotionId, map.get("promotion_type_info"));
						if (promotionIds.length() == 0) {
							promotionIds.append(promotionId);
						} else {
							promotionIds.append(",");
							promotionIds.append(promotionId);
						}
					}
				}
				map.remove("table_name");
				promotionMap.add((HashMap<String, String>) map);
			} else if(map.get("table_name") != null && map.get("table_name").indexOf("sort_product") != -1){
				productName = map.get("product_name");
				sortName = map.get("sort_name");
				pinpaiName = map.get("pinpai_name");
				categoryId = map.get("category_id");
				brandId = map.get("brand_id");
				shopId = map.get("shop_code");
			}else {
				if (map.get("product_price") != null && !map.get("product_price").equals("")) {
					try {
						// 182.81 - ￥ 192.40
						String priStr = map.get("product_price").replace("￥", "").replace(",", "").replace(" ", "");
						if (priStr.indexOf("-") != -1) {
							Double.parseDouble(priStr.split("-")[0]);
							Double.parseDouble(priStr.split("-")[1]);
							priceList.add(priStr);
						} else {
							Double.parseDouble(priStr);
							priceList.add(priStr);
						}
						if (map.get("promotion_price") != null && !map.get("promotion_price").equals("无")) {
							yaoPromotionPrice = Double.parseDouble(map.get("promotion_price"));
							HashMap<String, String> newMap = new HashMap<String, String>();
							newMap.put("promotion_type_name", "促销价");
							newMap.put("promotion_type_info", map.get("promotion_price"));
							promotionMap.add(newMap);
						}
					} catch (NumberFormatException e) {
						
					}
				} else {
					
				}
			}
		}

		if (priceList.size() > 0) {
			try {
				PricePromotionInfo promotionInfo = new PricePromotionInfo();
				// 计算促销用的价格
				double tempPrice = getMinPrice(priceList, false);
				// 零售价取最大
				double maxPrice = getMinPrice(priceList, true);
				// 满减集合
				List<HashMap<String, String>> MJpromotionMap = new ArrayList<HashMap<String, String>>();
				// 非满减计算出的结果集合
				List<HashMap<String, Double>> computeEnd = new ArrayList<HashMap<String, Double>>();
				// 将促销内容去重后拿到的集合
				List<HashMap<String, String>> allPromotion = new ArrayList<HashMap<String, String>>();
				// 促销内容去重依据，将promotion_type_name和promotion_type_info组合放入list中，如果list中有该促销内容就不添加
				List<String> uniquePromotion = new ArrayList<String>();
				// 是否有立减促销
				boolean islijian = false;
				// 是否有满减促销
				boolean isMJ = false;
				for (HashMap<String, String> m : promotionMap) {
					if (uniquePromotion.contains(m.get("promotion_type_name") + m.get("promotion_type_info"))) {
						continue;
					}
					String promotionPrice = "";
					if (m.get("promotion_type_name").equals("立减")) {
						islijian = true;
					}
					/**
					 * 增加一个特殊表，用于个别站出现的促销名称不为满减但是其实是满减类促销
					 */
					boolean isMjType = false;
					for (HashMap<String, String> map : mjType) {
						if (platFormId.equals(map.get("platform_id"))
								&& m.get("promotion_type_name").equals(map.get("promotion_type_name"))) {
							isMjType = true;
						}
					}
					if (m.get("promotion_type_name").equals("满减") || isMjType) {
						isMJ = true;
					} else {
						promotionPrice = ConvertTools.getLijiePrice(m.get("promotion_type_info"));
					}
					uniquePromotion.add(m.get("promotion_type_name") + m.get("promotion_type_info"));
					if (!promotionPrice.equals("")) {
						m.put("promotion_type_info", promotionPrice);
					}
					allPromotion.add(m);
				}
				for (HashMap<String, String> m : allPromotion) {
					try {
						Double.parseDouble(m.get("promotion_type_info"));
					} catch (NumberFormatException e) {
						
						continue;
					}
					if (islijian && isMJ && m.get("promotion_type_name").equals("立减")) {
						continue;
					}
					HashMap<String, Double> tempMap = GetPromotionPrice.getPrice(tempPrice, platFormId, m);
					Double tempMaxPrice = GetPromotionPrice.getMaxPrice(maxPrice, m);
					if (tempMaxPrice != null) {
						maxPrice = tempMaxPrice;
					}
					if (tempMap != null && tempMap.size() > 0) {
						computeEnd.add(tempMap);
					}
				}
				double priceTemp = tempPrice;
				double promotionTemp = 0;
				boolean isPromotion = true;
				// 从多个促销价 团购价 等促销中，找出最低价
				for (HashMap<String, Double> hsd : computeEnd) {
					double priceT = hsd.get("price");
					double promotionT = hsd.get("promotion_price");
					// 当算出的促销价小于0时跳过。 2015-8-5
					if (priceT <= 0 || promotionT <= 0) {
						continue;
					}
					if (isPromotion) {
						isPromotion = false;
						promotionTemp = promotionT;
						priceTemp = priceT;
					} else if (promotionT < promotionTemp) {
						promotionTemp = promotionT;
						priceTemp = priceT;
					}
				}
				// 判断是否有（立减）等促销
				if (promotionTemp == 0) {
					promotionTemp = priceTemp;
				}
				double tempPromotion = 0;

				// 从非促销价的促销内容中计算促销价
				for (HashMap<String, String> m : MJpromotionMap) {
					double temp = GetPromotionPrice.getMJPrice(promotionTemp, m, key, true);
					if (tempPromotion == 0) {
						tempPromotion = temp;
					} else {
						if (temp != 0) {
							tempPromotion = tempPromotion > temp ? temp : tempPromotion;
						}
					}
				}
				if (tempPromotion != 0) {
					promotionTemp = tempPromotion;
				}
				// 如果促销价大于价格，交换促销价和价格
				if (promotionTemp > priceTemp) {
					double temProPri = priceTemp;
					priceTemp = promotionTemp;
					promotionTemp = temProPri;
				}
				double priceOff = 1;

				// 药品特殊处理
				if (yaoPromotionPrice != null && promotionTemp == 0) {
					if (promotionIds.length() == 0) {
						promotionIds.append("13");
					} else {
						promotionIds.append(",");
						promotionIds.append("13");
					}
				}

				if (promotionTemp == priceTemp) {
					promotionTemp = 0;
				} else {
					priceOff = promotionTemp / priceTemp;
				}

				if (priceOff != 1) {
					promotionInfo.setPriceOff(priceOff);
				}
				String promtionId = promotionIds.toString();
				if (MJpromotionMap.size() == 0 && isMJ) {
					promtionId.replace("16", "");
				}
				promotionInfo.setPromotionIds(promotionIds.toString());
				if (promotionTemp != 0) {
					promotionInfo.setPromotionPrice(promotionTemp + "");
				}
				promotionInfo.setLingshoujia(maxPrice + "");

				promotionInfo.setProductName(productName);
				promotionInfo.setSortName(sortName);
				promotionInfo.setPinpaiName(pinpaiName);
				promotionInfo.setCategoryId(categoryId);
				promotionInfo.setBrandId(brandId);
				promotionInfo.setShopId(shopId);
				
				return promotionInfo;
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			
			return null;
		}
	}

	public static double getMinPrice(List<String> list, boolean isMax) {
		double Price = 0;
		for (int i = 0; i < list.size(); i++) {
			double nowPrice = 0;
			String str = list.get(i);
			if (str.split("-").length == 2) {
				nowPrice = getReplaceDouble(str.split("-")[0]);
			} else {
				nowPrice = getReplaceDouble(str.replace(",", ""));
			}
			if (i == 0) {
				if (str.split("-").length == 2) {
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

}
