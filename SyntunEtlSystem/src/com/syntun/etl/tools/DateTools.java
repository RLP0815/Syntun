package com.syntun.etl.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTools {

	/**
	 * 统一各个平台的日期格式
	 */
	public static String convertDate(String inData) throws Exception {
		String rs = inData.replaceAll(
				"([0-9]{4})(-|年)([0-9]{1,2})(-|月)([0-9]{1,2})(.*)", "$1-$3-$5");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse(rs);
		String dateString = format.format(date);
		return dateString;
	}

	public static String convertDateT(String inDate) throws ParseException {
		String patternString = "([\\d]{4})(-|年|/)([\\d]{1,2})(-|月|/)([\\d]{1,2})(\\d+)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher match = pattern.matcher(inDate);
		String year = "";
		String day = "";
		String d = "";
		if (match.find()) {
			year = match.group(1);
			day = match.group(3);
			if (Integer.parseInt(match.group(5)) > 31) {
				d = Integer.parseInt(match.group(5)) / 10 + "";
			} else if (match.group(6).length() == 4) {
				d = Integer.parseInt(match.group(5)) / 10 + "";
			} else {
				d = match.group(5);
			}
		}
		String rs = year + "-" + day + "-" + d;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse(rs);
		String dateString = format.format(date);
		return dateString;
	}
	/**
	 * 统一各个平台的日期格式
	 */
	public static String convertDate01(String inData) throws Exception {
		String rs = inData.replaceAll(
				"([0-9]{4})(-|年)([0-9]{1,2})(-|月)([0-9]{1,2})(.*)", "$1-$3-$5");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse(rs);
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return dateString;
	}

	public static String convertDateT01(String inDate) throws ParseException {
		String patternString = "([\\d]{4})(-|年|/)([\\d]{1,2})(-|月|/)([\\d]{1,2})(\\d+)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher match = pattern.matcher(inDate);
		String year = "";
		String day = "";
		String d = "";
		if (match.find()) {
			year = match.group(1);
			day = match.group(3);
			if (Integer.parseInt(match.group(5)) > 31) {
				d = Integer.parseInt(match.group(5)) / 10 + "";
			} else if (match.group(6).length() == 4) {
				d = Integer.parseInt(match.group(5)) / 10 + "";
			} else {
				d = match.group(5);
			}
		}
		String rs = year + "-" + day + "-" + d;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse(rs);
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return dateString;
	}
	
	public static void main(String[] args) throws Exception {
//		String patternString = "([\\d]{4})(-|年|/)([\\d]{1,2})(-|月|/)([\\d]{1,2})";
//		Pattern pattern = Pattern.compile(patternString);
//		Matcher match = pattern.matcher("2011/10/985200");
//		// StringBuffer sb = new StringBuffer();
//		// System.out.println("asdfasd");
//		if (match.find()) {
//			System.out.println("1:" + match.group(1));
//			System.out.println("3:" + match.group(3));
//			System.out.println("5:" + match.group(5));
//		}
//		System.out.println("2011/10/985200".replaceFirst(
//				"/([\\d]{4})(-|年)([\\d]{1,2})(-|月)([\\d]{1,2})(\\d*?)/",
//				"$1$3$5"));
		 System.out.println(convertDateT("2015-1-8112729"));

	}
}
