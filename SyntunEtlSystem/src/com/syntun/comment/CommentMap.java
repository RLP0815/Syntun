﻿package com.syntun.comment;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.syntun.entity.Parameter;
import com.syntun.etl.tools.GetMD5;
import com.syntun.etl.tools.SyntunDate;

/**
 * 销量map阶段处理类，主要功能是提取评论相关的字段，还有就是将评论按评论日期输出到reduce
 * 
 * @author tuo
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommentMap {

	public static List<String> getField = new ArrayList<String>();

	static {
		getField.add("product_comment_content");
		getField.add("product_comment_score");
		getField.add("product_comment_time");
		getField.add("table_name");
		getField.add("user_level");
		getField.add("user_name");
		
		//天猫才有
//		getField.add("product_source");
//		getField.add("company_name");
	}
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static String inEncode = "UTF-8";

	public static void map(String platFormId, Map<String, String> map, Context context) throws IOException {
		Configuration conf = context.getConfiguration();
		String getDate = conf.get("date");
		// 抓取日期
		String date = map.get("get_date");
		// 平台名称（数据库中的名称，根据这个名称可以找到相应的平台id）
		// String paltFormName = map.get("table_name").split("\\.")[0];
		int i = 0;
		String opertionProductId = map.get("product_id");
//		String endShopCode = "1111";
//		// 苏宁和亚马逊特殊处理
//		if (map.get("shop_code") != null && !map.get("shop_code").equals("")
//				&& (platFormId.equals("3") || platFormId.equals("10"))) {
//			endShopCode = map.get("shop_code");
//		}
		if (platFormId.equals("3")) {
			opertionProductId = map.get("partNumber_id");
		}
		if (opertionProductId == null || opertionProductId.equals("null")) {
			return;
		}
		
		StringBuffer val = new StringBuffer();
		for (String filed : getField) {
			String str = map.get(filed);
			if(filed.equals("product_comment_content")){
				str = null;
			}
//			if (filed.equals("product_comment_time") && str != null) {
//				try {
//					try {
//						format.parse(str);
//					} catch (ParseException e) {
//						try {
//							str = str.replace("天前", "");
//							if (str.indexOf("个月前") != -1) {
//								str = (Integer.parseInt(str.replace("个月前", "")) * 30) + "";
//							}
//							if (str.indexOf("分钟前") != -1 || str.indexOf("小时前") != -1) {
//								str = date;
//							} else {
//								Integer.parseInt(str);
//								str = SyntunDate.nowJieDate(str, date);
//							}
//						} catch (NumberFormatException e2) {
//
//						}
//					}
//					try {
//						str = DateTools.convertDate(str);
//					} catch (Exception e) {
//						try {
//							str = DateTools.convertDateT(str);
//						} catch (Exception e1) {
//							// return;
//						}
//					}
//					if (str.replace("-", "").length() != 8) {
//						// 日期格式不正确，抛出到异常表，并退出
//						int randomInt = (int) (Math.random() * 1000);
//						val.append(Parameter.LIESPLITE).append("syntun").append(Parameter.ZHIPLITE)
//								.append("commentTime_err").append(Parameter.LIESPLITE).append("err_type_info")
//								.append(Parameter.ZHIPLITE).append("评论表时间出现问题").append(Parameter.LIESPLITE)
//								.append("operation_product_id").append(Parameter.ZHIPLITE).append(opertionProductId)
//								.append(Parameter.LIESPLITE).append("err_focus_information").append(Parameter.ZHIPLITE)
//								.append(date).append(Parameter.LIESPLITE).append("platform_name")
//								.append(Parameter.ZHIPLITE).append(platFormId).append(Parameter.LIESPLITE)
//								.append("date").append(Parameter.ZHIPLITE).append(map.get("get_date"));
//						context.write(new Text(platFormId + opertionProductId + randomInt),
//								new Text(val.toString().getBytes("UTF-8")));
//						return;
//					}
//
//					date = str;
//					if (!SyntunDate.isData(getDate, date)) {
//						return;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			if (i == 0 && str != null) {
				val.append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
				i = 1;
			} else if (map.get(filed) != null) {
				val.append(Parameter.LIESPLITE).append(filed).append(Parameter.ZHIPLITE).append(map.get(filed));
			}
		}
		if (date == null) {
			return;
		}
		// 
		if (!SyntunDate.isData(getDate, date)) {
			return;
		}
		val.append(Parameter.LIESPLITE).append("unique").append(Parameter.ZHIPLITE).append(GetMD5.GetMD5Code(val.toString()));
		val.append(Parameter.LIESPLITE).append("platform_id").append(Parameter.ZHIPLITE).append(platFormId);
		val.append(Parameter.LIESPLITE).append("syntun").append(Parameter.ZHIPLITE).append("comment");
		val.append(Parameter.LIESPLITE).append("new_date").append(Parameter.ZHIPLITE).append(date);

		try {
			Random r = new Random();
			int num = r.nextInt(100);	//andom的nextInt(int n)方法可以生成一个介于0(包含)到n(不包含)之间的整数
			context.write(new Text(opertionProductId + ":" + platFormId + ":" + num),
					new Text(val.toString().getBytes("UTF-8")));
//			context.write(new Text(opertionProductId + ":" + platFormId),
//					new Text(val.toString().getBytes("UTF-8")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}