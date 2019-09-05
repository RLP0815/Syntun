package com.syntun.etl.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SyntunDate {
	/**
	 * 获取范围内所有日期值
	 * 
	 * @param startDay
	 *            起始日
	 * @param endDay
	 *            结束日
	 * @return
	 */
	public static ArrayList<String> getInterDays(String startDay, String endDay) {
		Pattern pt = Pattern.compile("[\\d]{4}\\-[\\d]{2}\\-[\\d]{2}");
		Matcher sm = pt.matcher(startDay);
		if (!sm.find()) {
			System.out.println("error:ss");
			System.exit(0);
		}
		Matcher em = pt.matcher(endDay);
		if (!em.find()) {
			System.out.println("error:ss");
			System.exit(0);
		}
		ArrayList<String> result = new ArrayList<String>();
		if (startDay.equals(endDay)) {
			result.add(startDay);
		} else {
			DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTime startDate = DateTime.parse(startDay);
			DateTime endDate = DateTime.parse(endDay);
			Days InterDays = Days.daysBetween(startDate, endDate);

			int days = InterDays.getDays();
			for (int i = 0; i <= days; i++) {
				result.add(startDate.plusDays(i).toString(dtf));
			}
		}
		return result;
	}

	/**
	 * 获取范围内所有日期值
	 * 
	 * @param startDay
	 *            起始日
	 * @param endDay
	 *            结束日
	 * @param date
	 *            不包括日期
	 * @return
	 */
	public static ArrayList<String> getInterDays(String startDay, String endDay, String date) {
		Pattern pt = Pattern.compile("[\\d]{4}\\-[\\d]{2}\\-[\\d]{2}");
		Matcher sm = pt.matcher(startDay);
		if (!sm.find()) {
			System.out.println("error:ss");
			System.exit(0);
		}
		Matcher em = pt.matcher(endDay);
		if (!em.find()) {
			System.out.println("error:ss");
			System.exit(0);
		}
		ArrayList<String> result = new ArrayList<String>();
		if (startDay.equals(endDay)) {
			result.add(startDay);
		} else {
			DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTime startDate = DateTime.parse(addDate(startDay));
			DateTime endDate = DateTime.parse(endDay);
			Days InterDays = Days.daysBetween(startDate, endDate);

			int days = InterDays.getDays();
			for (int i = 0; i <= days; i++) {
				if (!date.equals(startDate.plusDays(i).toString(dtf)))
					result.add(startDate.plusDays(i).toString(dtf));
			}
		}
		return result;
	}

	/**
	 * 给传入日期增加一天
	 * 
	 * @param date
	 * @return
	 */
	public static String addDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(c.getTime());
	}

	/**
	 * 给传入日期增加一天
	 * 
	 * @param date
	 * @return
	 */
	public static String addDate(String date, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, num);
		return sdf.format(c.getTime());
	}

	/**
	 * 给传入getDate日期减date天
	 * 
	 * @return
	 */
	public static String nowJieDate(String date, String getDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int jieDate = Integer.parseInt(date);
		try {
			c.setTime(sdf.parse(getDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, -jieDate);
		return sdf.format(c.getTime());
	}

	/**
	 * 给传入日期减一天
	 * 
	 * @param date
	 * @return
	 */
	public static String jieDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(c.getTime());
	}

	/**
	 * 判断是否date在getDate范围内，包含开始和结束
	 * 
	 * @param getDate
	 *            由_隔开的俩个日期，前为起始日，后围结束日
	 * @param date
	 *            要判断的日期
	 * @return
	 */
	public static boolean isTimeOut(String getDate, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (getDate.indexOf("_") != -1) {
				Date d = sdf.parse(date);
				String start = getDate.split("_")[0];
				String end = getDate.split("_")[1];
				if (sdf.parse(jieDate(start)).before(d) && sdf.parse(addDate(end)).after(d)) {
					return true;
				}
			} else {
				if (date.indexOf(getDate) != -1) {
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断当前数据是否为本次计算需要的数据，getDate，不包括开始字段，包括结束字段
	 * 
	 * @param getDate
	 *            需要的日期范围2013-11-30_2013-12-30，开始日期和结束日期之间用_隔开
	 * @param date
	 *            当前数据日期
	 * @return
	 */
	public static boolean isData(String getDate, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (getDate.indexOf("_") != -1) {
				Date d = sdf.parse(date);
				String[] dateStr = getDate.split("_");
				String start = dateStr[0];
				String end = dateStr[1];
				if (sdf.parse(start).before(d) && sdf.parse(addDate(end)).after(d)) {
					return true;
				}
			} else if (date.equals(getDate)) {
				return true;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断当前数据是否为本次计算需要的数据，getDate，不包括开始字段，包括结束字段
	 * 
	 * @param getDate
	 *            需要的日期范围2013-11-30_2013-12-30，开始日期和结束日期之间用_隔开
	 * @param date
	 *            当前数据日期
	 * @return
	 */
	public static boolean is30Data(String getDate, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (getDate.indexOf("_") != -1) {
				Date d = sdf.parse(addDate(date, 29));
				String start = getDate.split("_")[0];
				String end = getDate.split("_")[1];
				if (sdf.parse(start).before(d) && sdf.parse(addDate(end)).after(d)) {
					return true;
				}
			} else {
				if (date.indexOf(getDate) != -1) {
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取俩日期相差天数，不确定谁大
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getChaDate(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(date1);
			Date d1 = sdf.parse(date2);
			return getDateBetween(d, d1);
		} catch (ParseException e) {

		}
		return -1;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int getDateBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		if ((between_days + "").contains("-")) {
			return -1;
		} else if (between_days > (365 * 10)) {
			return -10;
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days).replace("-", ""));
	}
}
