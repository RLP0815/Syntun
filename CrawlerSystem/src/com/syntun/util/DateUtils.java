package com.syntun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 获取当前时间，5分钟之前的时间
	 */
	public static String getBefore5Time() {
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -5);// 5分钟之前的时间
		Date beforeD = beforeTime.getTime();
		String before5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);
		return before5;
	}

	/**
	 * 根据一个时间，获取距离当前分钟数
	 */
	public static long getTimeMinute(String time) {

		String format = "yyyy-MM-dd HH:mm:ss";// 日期格式
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar dateOne = Calendar.getInstance();
		Calendar dateTwo = Calendar.getInstance();
		dateOne.setTime(new Date());// 设置为当前系统时间
		dateTwo.setTime(date);// 获取数据库中的时间
		long timeOne = dateOne.getTimeInMillis();
		long timeTwo = dateTwo.getTimeInMillis();
		long minute = (timeOne - timeTwo) / (1000 * 60);// 转化minute
		return minute;
	}

	// 字符串反转
	public static String reverse1(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	// 小于5分钟
	public static boolean get5(String startdate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start = null;
		try {
			start = sdf.parse(startdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long cha = System.currentTimeMillis() - start.getTime();
		if (cha <= 300000) {
			return false; // 说明小于5分钟
		} else {
			return true;  // 大于5分钟
		}
	}
	
	public static String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
	}

	public static void main(String[] args) {

		System.out.println(get5("2019-07-05 11:12:11"));

	}

}
