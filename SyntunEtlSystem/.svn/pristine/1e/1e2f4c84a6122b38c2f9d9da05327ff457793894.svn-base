package com.syntun.etl.tools;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.syntun.entity.Parameter;

public class ConvertTools {

	public static String convertMapToStr(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (String key : map.keySet()) {
			if (i == 0) {
				sb.append(key).append(Parameter.ZHIPLITE).append(replaceUnicode(map.get(key)));
				i = 1;
			} else {
				sb.append(Parameter.LIESPLITE).append(key).append(Parameter.ZHIPLITE)
						.append(replaceUnicode(map.get(key)));
			}
		}
		return sb.toString();
	}

	public static String convertMapToMaohaoStr(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (String key : map.keySet()) {
			if (i == 0) {
				sb.append(key).append(":").append(map.get(key));
				i = 1;
			} else {
				sb.append("<tuo>").append(key).append(":").append(map.get(key));
			}
		}
		return sb.toString();
	}

	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMapNoReplace(String str) {
		str = str.replace("\r\n", "").replace("\n", "");
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.contains("<tuo>") == true ? str.split("<tuo>") : str.split("\001");
		for (String colVal : strs) {
			if ((colVal.split(":").length == 2 && colVal.indexOf("table_name") != -1) || str.contains("<tuo>")) {
				map.put(colVal.split(":")[0], colVal.split(":")[1]);
			} else if (colVal.split("\002").length == 2) {
				map.put(colVal.split("\002")[0], colVal.substring(colVal.split("\002")[0].length() + 1));
			}
		}
		return map;
	}

	public static Map<String, String> convertStrToMapNoReplace1s(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.contains("<tuo>") == true ? str.split("<tuo>") : str.split("\001");
		for (String colVal : strs) {
			String[] colStr = colVal.split(":");
			if ((colStr.length == 2 && colVal.indexOf("table_name") != -1) || str.contains("<tuo>")) {
				map.put(colStr[0], colStr[1]);
			} else {
				String[] colStr1 = colVal.split(":");
				if (colStr1.length == 2) {
					map.put(colStr1[0], colStr1[1]);
				}
			}
		}
		return map;
	}

	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMap(String str, HashMap<String, String> replaceFiled) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.contains("<tuo>") == true ? str.split("<tuo>") : str.split("\001");
		for (String colVal : strs) {
			if ((colVal.split(":").length == 2 && colVal.indexOf("table_name") != -1) || str.contains("<tuo>")) {
				map.put(getFiled(colVal.split(":")[0], replaceFiled, map), replaceUnicode(colVal.split(":")[1]));
			} else if (colVal.split("\002").length == 2) {
				map.put(getFiled(colVal.split("\002")[0], replaceFiled, map),
						replaceUnicode(colVal.substring(colVal.split("\002")[0].length() + 1)));
			}
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMapHive(String str, int num) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split("\\x001");
		if(strs.length != num){
			return null;
		}
		int i = 0;
		for (String colVal : strs) {
			map.put(i+"", colVal);
			i ++;
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMap(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split("	");
		if(strs.length != 23){
			return null;
		}
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("id");
		fieldList.add("user_name");
		fieldList.add("user_level");
		fieldList.add("product_id");
		fieldList.add("product_price");
		fieldList.add("product_name");
		fieldList.add("product_comment_content");
		fieldList.add("product_comment_time");
		fieldList.add("product_comment_score");
		fieldList.add("score");
		fieldList.add("platform_id");
		fieldList.add("platform_name");
		fieldList.add("shop_id");
		fieldList.add("shop_name");
		fieldList.add("shop_info");
		fieldList.add("get_date");
		fieldList.add("category_name");
		fieldList.add("brand_name");
		fieldList.add("category_id");
		fieldList.add("brand_id");
		fieldList.add("super_category_name");
		fieldList.add("hight_category_name");
		fieldList.add("product_source");
		int i = 0;
		for (String colVal : strs) {
			map.put(fieldList.get(i), colVal);
			i ++;
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMapUrl(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split(",");
		if(strs.length != 20){
			return null;
		}
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("url_id");
		fieldList.add("platform_id");
		fieldList.add("platform_name");
		fieldList.add("brand_id");
		fieldList.add("brand_name");
		fieldList.add("category_id");
		fieldList.add("category_name");
		fieldList.add("super_category_name");
		fieldList.add("hight_category_name");
		fieldList.add("product_name");
		fieldList.add("shop_id");
		fieldList.add("shop_name");
		fieldList.add("shop_info");
		fieldList.add("shop_info_1");
		fieldList.add("super_category_name_11");
		fieldList.add("brand_name_jiu");
		fieldList.add("new_category_name");
		fieldList.add("category_name_jiu");
		fieldList.add("hight_category_name_jiu");
		fieldList.add("super_category_name_jiu");
		int i = 0;
		for (String colVal : strs) {
			map.put(fieldList.get(i), colVal);
			i ++;
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMapPrice(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split(",");
		if(strs.length != 8){
			return null;
		}
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("frist_sort_name");
		fieldList.add("second_sort_name");
		fieldList.add("sort_name");
		fieldList.add("product_price");
		fieldList.add("c_min");
		fieldList.add("c_max");
		fieldList.add("number");
		fieldList.add("total_number");
		int i = 0;
		for (String colVal : strs) {
			map.put(fieldList.get(i), colVal);
			i ++;
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> categoryToMap(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split("	");
		if(strs.length != 22){
			return null;
		}
		List<String> list = new ArrayList<String>();
		list.add("id");
		list.add("user_name");
		list.add("user_level");
		list.add("product_id");
		list.add("product_price");
		list.add("product_name");
		list.add("product_comment_time");
		list.add("product_comment_score");
		list.add("product_source");
		list.add("platform_id");
		list.add("platform_name");
		list.add("shop_id");
		list.add("shop_name");
		list.add("shop_info");
		list.add("shop_info_1");
		list.add("category_name");
		list.add("brand_name");
		list.add("category_id");
		list.add("brand_id");
		list.add("super_category_name");
		list.add("hight_category_name");
		list.add("get_date");
		
		int i = 0;
		for (String colVal : strs) {
			map.put(list.get(i), colVal);
			i ++;
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> categoryToMapGY(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split("	");
		if(strs.length != 25){
			return null;
		}
		List<String> list = new ArrayList<String>();
		list.add("id");
		list.add("user_name");
		list.add("user_level");
		list.add("product_id");
		list.add("product_price");
		list.add("product_name");
		list.add("product_comment_time");
		list.add("product_comment_score");
		list.add("product_source");
		list.add("platform_id");
		list.add("platform_name");
		list.add("shop_id");
		list.add("shop_name");
		list.add("shop_info");
		list.add("shop_info_1");
		list.add("category_name");
		list.add("brand_name");
		list.add("category_id");
		list.add("brand_id");
		list.add("super_category_name");
		list.add("hight_category_name");
		list.add("get_date");
		list.add("number");
		list.add("total_number");
		list.add("grade");
		
		int i = 0;
		for (String colVal : strs) {
			map.put(list.get(i), colVal);
			i ++;
		}
		return map;
	}
	/**
	 * 将一行字符串数据转换为map集合
	 * 
	 * @param str
	 *            hadoop中一行文本
	 * @return
	 */
	public static Map<String, String> convertStrToMapR(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.split("\001");
		for (String colVal : strs) {
			if (colVal.split("\002").length == 2) {
				map.put(colVal.split("\002")[0], colVal.split("\002")[1]);
			}
		}
		return map;
	}

	public static Map<String, String> convertStrToMap1(String str, HashMap<String, String> replaceFiled) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] strs = str.contains("<tuo>") == true ? str.split("<tuo>") : str.split("\001");
		for (String colVal : strs) {
			String[] colStrs = colVal.split(":");
			if ((colStrs.length == 2 && colVal.indexOf("table_name") != -1) || str.contains("<tuo>")) {
				map.put(getFiled(colStrs[0], replaceFiled, map), colStrs[1]);
			} else {
				String[] colStrs1 = colVal.split("\002");
				if (colStrs1.length == 2) {
					map.put(getFiled(colStrs1[0], replaceFiled, map), colStrs1[1]);
				}
			}
		}
		return map;
	}

	/**
	 * 查找字符中是否包含unix编码正则对象
	 */
	private static Pattern replaceUnicodePattern = Pattern.compile("[\\u0000-\\uffff]*?");

	// 去除unicode编码
	public static String replaceUnicode(String colValue) {
		try {
			if (colValue != null && !colValue.equals("")) {
				Matcher m = replaceUnicodePattern.matcher(colValue);
				if (m.find()) {
					try {
						colValue = Base64.decodeUnicode(colValue.replaceAll("\\\\u(?=[0-9a-fA-F]{4})", "&#x"))
								.replace("\\", "");
					} catch (StringIndexOutOfBoundsException e) {
					}
				}
			}
		} catch (Exception e) {
		}
		return colValue;
	}

	// public static String getFiled(String filed,
	// HashMap<String, String> replaceFiled) {
	// if (replaceFiled.containsKey(filed)) {
	// return replaceFiled.get(filed);
	// }
	// return filed;
	// }

	public static String getFiled(String filed, HashMap<String, String> replaceFiled, HashMap<String, String> m) {

		if (replaceFiled.containsKey(filed)) {
			if (!m.containsKey(replaceFiled.get(filed))) {
				return replaceFiled.get(filed);
			}
		}
		return filed;
	}

	public static String getTableUnique(String tempUnique, Map<String, String> map,
			HashMap<String, String> replaceFiled) {
		StringBuffer sb = new StringBuffer();
		if (tempUnique != null && !tempUnique.equals("")) {
			String[] listUnique = tempUnique.split(",");
			for (String field : listUnique) {
				if (replaceFiled.containsKey(field)) {
					field = replaceFiled.get(field);
				}
				if (map.get(field) != null && !map.get(field).equals("")) {
					sb.append(map.get(field));
				} else {
					sb.append("err");
				}
			}
		} else {
			return GetMD5.GetMD5Code("none");
		}
		if (sb.toString().equals("")) {
			return null;
		}
		String unique = GetMD5.GetMD5Code(sb.toString());
		return unique;
	}

	public static String getTableUnique(String tempUnique, Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		if (tempUnique != null && !tempUnique.equals("")) {
			String[] listUnique = tempUnique.split(",");
			for (String field : listUnique) {
				if (map.get(field) != null && !map.get(field).equals("")) {
					sb.append(map.get(field));
				} else {
					sb.append("err");
				}
			}
		} else {
			return GetMD5.GetMD5Code("none");
		}
		if (sb.toString().equals("")) {
			return null;
		}
		String unique = GetMD5.GetMD5Code(sb.toString());
		return unique;
	}

	public static String getTableUniqueNOreplaceFiled(String tempUnique, Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		if (!tempUnique.equals("")) {
			String[] listUnique = tempUnique.split(",");
			for (String field : listUnique) {
				if (map.get(field) != null && !map.get(field).equals("")) {
					sb.append(map.get(field));
				} else {
					sb.append("err");
				}
			}
		} else {
			return GetMD5.GetMD5Code("none");
		}
		if (sb.toString().equals("")) {
			return null;
		}
		String unique = GetMD5.GetMD5Code(sb.toString());
		return unique;
	}

	/**
	 * 小数点保留两位
	 * 
	 * @param value
	 * @return
	 */
	public static String cutDecimalPoint(double value) {
		DecimalFormat df = new DecimalFormat("0.##");
		df.setRoundingMode(RoundingMode.FLOOR);
		return df.format(value);
	}

	public static void main(String[] args) {
		System.out.println(GetMD5.GetMD5Code("923638大***鹤2018-01-07 11:01:33"));
		System.out.println(GetMD5.GetMD5Code("923638j***02018-01-07 13:34:23"));
		System.out.println(GetMD5.GetMD5Code("923638j***72018-01-07 05:41:29"));

		System.out.println(getLijiePrice("1.9"));
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

}
