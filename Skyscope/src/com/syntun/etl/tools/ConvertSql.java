package com.syntun.etl.tools;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

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
	
	public static String getSql(String tableName, List<String> filedList, HashMap<String, String> dataMap) {

		StringBuffer sb = new StringBuffer("INSERT IGNORE INTO " + tableName + "(");
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
				//val = doubleReplace(StringEscapeUtils.escapeSql(dataMap.get(filed)));
				val = dataMap.get(filed);
				int i = val.length()-1<0?0:val.length()-1;
				if(val.substring(i).equals("\\")){
					val = val + "\\";
				}
				if(val.indexOf("'") != -1){
					val = val.replace("'", "\\'");
				}
				if(filed.equals("create_at")){
					val = dataMap.get(filed);
				}
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
	// SqlServer
	public static String getSqlServer(String tableName, List<String> filedList, HashMap<String, String> dataMap) {

		StringBuffer sb = new StringBuffer("INSERT INTO " + tableName + "(");
		StringBuffer startVal = new StringBuffer(" values");
		boolean isTrue = true;
		for (int j = 0; j < filedList.size(); j++) {
			String filed = filedList.get(j);
			if (j == 0) {
				sb.append("[" + filed + "]");
			} else {
				sb.append("," + "[" + filed + "]");
			}
			//String val = null;
			String val = "";
			String filedK = filed.toLowerCase();
			if (dataMap.get(filedK) != null) {
				isTrue = false;
//				val = doubleReplace(StringEscapeUtils.escapeSql(dataMap.get(filedK)));
//				if(filedK.equals("get_data_time"))
//					val = dataMap.get(filedK);
				val = dataMap.get(filedK);
				if(filedK.equals("product_name") && val.length()>90){
					val = val.substring(90);
				}
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

}
