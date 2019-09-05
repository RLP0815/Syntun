package com.syntun.etl.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

import com.syntun.entity.Parameter;

public class ConvertSql {

	/*
	 * 判断是否为数字
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是浮点数返回true,否则返回false
	 */
	public static String doubleReplace(String str) {
		Pattern pattern = Pattern.compile("\\d{1,9}\\.\\d{1,2}");
		Matcher mc = pattern.matcher(str);
		if (mc.find()) {
			return mc.group();
		}
		return str;
	}

	public static String convertMapToStr(Map<String, String> map, List<String> filedList) {
		StringBuffer sb = new StringBuffer();
		int i = 0;

		for (String filed : filedList) {
			if (i == 0) {
				sb.append(filed).append("\002").append(map.get(filed) == null ? "null" : doubleReplace(map.get(filed)));
				i = 1;
			} else {
				sb.append("\001").append(filed).append("\002")
						.append(map.get(filed) == null ? "null" : doubleReplace(map.get(filed)));
			}
		}
		return sb.toString();
	}

	public static String convertMapToStr(Map<String, String> map, Set<String> filedList) {
		StringBuffer sb = new StringBuffer();
		int i = 0;

		for (String filed : filedList) {
			if (i == 0) {
				sb.append(filed).append("\002").append(map.get(filed) == null ? "null" : doubleReplace(map.get(filed)));
				i = 1;
			} else {
				sb.append("\001").append(filed).append("\002")
						.append(map.get(filed) == null ? "null" : doubleReplace(map.get(filed)));
			}
		}
		return sb.toString();
	}

	public static String getSql(String tableName, List<String> filedList, HashMap<String, String> dataMap) {

		StringBuffer sb = new StringBuffer("INSERT INTO " + tableName + "(");
		StringBuffer startVal = new StringBuffer(" values");
		boolean isTrue = true;
		for (int j = 0; j < filedList.size(); j++) {
			String filed = filedList.get(j);
			if (j == 0) {
				sb.append(filed);
			} else {
				sb.append("," + filed);
			}
			String val = null;
			if (dataMap.get(filed) != null) {
				isTrue = false;
				val = doubleReplace(StringEscapeUtils.escapeSql(dataMap.get(filed)));
			}
			if (j == 0) {
				startVal.append("(").append("'" + val + "'");
			} else {
				startVal.append(",").append("'" + val + "'");
			}
		}
		if (isTrue) {
			return null;
		}
		startVal.append(");");
		sb.append(")").append(startVal.toString());
		return sb.toString();
	}

	public static String getStr(List<String> filedList, Map<String, String> valMap) {
		HashMap<String, String> m = new HashMap<String, String>();
		boolean isTrue = true;
		for (int j = 0; j < filedList.size(); j++) {
			String filed = filedList.get(j);
			m.put(filed, valMap.get(filed));
			isTrue = false;
		}
		if (isTrue) {
			return null;
		}
		return ConvertTools.convertMapToStr(m);
	}

	public static String getStrNoCol(List<String> filedList, Map<String, String> valMap) {
		boolean isTrue = true;
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (int j = 0; j < filedList.size(); j++) {
			String filed = filedList.get(j);
			String val = valMap.get(filed);

			if (i == 0) {
				sb.append(val);
				i = 1;
			} else {
				sb.append(Parameter.ZHIPLITE).append(val);
			}
			isTrue = false;
		}
		if (isTrue) {
			return null;
		}
		return sb.toString();
	}

}
