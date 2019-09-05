package com.syntun.inspect;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConnectSql138;
import com.syntun.etl.tools.ConnectSql53;
import com.syntun.etl.tools.ConnectSql60;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData138;

/*
 * 中间结果输出
 * 平台补数
 */

public class HalfPriceResult {

	/**
	 * 补充价格数据sql集合
	 */
	public static List<String> AmazonPriceAll = new ArrayList<String>();
	/**
	 * 补充促销数据sql集合
	 */
	//public static List<String> AmazonPromotionAll = new ArrayList<String>();
	/**
	 * 中间结果价格数据sql集合
	 */
	public static List<String> PriceProductList = new ArrayList<String>();
	/**
	 * 中间结果促销数据sql集合
	 */
	public static List<String> PricePromotionInfo = new ArrayList<String>();
	
	public static void main(String[] args) {
//		String strInput = "每300减吉金30";
//        String regEx = "[^0-9]";//匹配指定范围内的数字
//        //Pattern是一个正则表达式经编译后的表现模式
//        Pattern p = Pattern.compile(regEx);
//        // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
//        Matcher m = p.matcher(strInput);
//        //将输入的字符串中非数字部分用空格取代并存入一个字符串，中间多个空格替换为一个空格
//        String string = m.replaceAll(" ").trim().replaceAll( "\\s+", " " );
//        //以空格为分割符在讲数字存入一个字符串数组中
//        String[] strArr = string.split(" ");
//        //遍历数组转换数据类型输出
//        for(String s:strArr){
//            System.out.println(Integer.parseInt(s));
//        }
		
		String  str="每300减30";  
		String s = "\\d+(\\.\\d+)?";

		Pattern  pattern=Pattern.compile(s);  
		Matcher  ma=pattern.matcher(str);  
		List<String> li = new ArrayList<String>(); 
		while(ma.find()){  
			System.out.println(ma.group()); 
	        li.add(ma.group());
		}
		System.out.println(li.get(2));
	}
	
	public static void HalfResult(String dateStr, String hour, String min, String email) {
		/**
		 * 补充数据sql集合
		 */
		AmazonPriceAll = new ArrayList<String>();
		/**
		 * 补充促销数据sql集合
		 */
		//AmazonPromotionAll = new ArrayList<String>();
		/**
		 * 中间结果价格数据sql集合
		 */
		PriceProductList = new ArrayList<String>();
		/**
		 * 中间结果促销数据sql集合
		 */
		PricePromotionInfo = new ArrayList<String>();
		// 138数据库，数据库连接外传，便于读取数据代码复用
		Connection conn138 = ConnectSql138.getConn();
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn60 = ConnectSql60.getConn();
		// 53数据库，数据库连接外传，便于读取数据代码复用
		Connection conn53 = ConnectSql53.getConn();
		// 16数据库，数据库连接外传，便于读取数据代码复用
		//Connection conn16 = ConnectSqlServer16.getConn();
		//Connection conn15 = ConnectSqlServer.getConn();
		
		Date day1 = new Date(); 
		
		// 生成组合主键的列集合
		List<String> keyList = new ArrayList<String>();
		keyList.add("operation_product_id");
		keyList.add("platform_id");
		keyList.add("shop_id");
		keyList.add("city_id");
		keyList.add("sku_id");
		keyList.add("shop_code");
		
		// 读取价格条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("get_date", dateStr);
		filter.put("hour", hour);
		filter.put("min", min);
		// 读取价格
		HashMap<String, List<HashMap<String, String>>> priceData = BaseDao
				.getAllTbalePriceList("skyscope.shop_product_price_list", filter, keyList, conn60);
		// 读取价格，60上手机端
		HashMap<String, List<HashMap<String, String>>> priceDataMB = BaseDao
				.getAllTbalePriceList("skyscope.shop_product_price_list_mb", filter, keyList, conn60);
		// 读取价格，53上手机端
		HashMap<String, List<HashMap<String, String>>> priceData53 = BaseDao
				.getAllTbalePriceList("skyscope.shop_product_price_list", filter, keyList, conn53);
		
		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("operation_product_id");
		keyList1.add("platform_id");
		keyList1.add("shop_id");
		keyList1.add("city_id");
		// 读取促销
		HashMap<String, List<HashMap<String, String>>> promotionData = BaseDao
				.getAllTbalePriceList("skyscope.promotion_info", filter, keyList1, conn60);
		
		// 生成组合主键的列集合
		List<String> keyList2 = new ArrayList<String>();
		keyList2.add("OPERATION_PRODUCT_ID");
		keyList2.add("SKU_ID");
		// 读取标准产品名称，原来是在60mysql数据库中
		//HashMap<String, String> productInfo = BaseDao.getProductInfo("skyscope.product_info_temp", keyList2, "PRODUCT_ID", conn60);
		HashMap<String, String> productInfo = BaseDao.getProductInfo("skyscope.PRODUCT_PLATFORM_LIST", keyList2, "PRODUCT_ID", conn60);
		// 生成组合主键的列集合
		List<String> keyList3 = new ArrayList<String>();
		keyList3.add("OPERATION_PRODUCT_ID");
		// 读取标准产品名称
		//HashMap<String, String> productInfo0 = BaseDao.getProductInfo0("skyscope.product_info_temp", keyList3, "PRODUCT_ID", conn60);
		HashMap<String, String> productInfo0 = BaseDao.getProductInfo0("skyscope.PRODUCT_PLATFORM_LIST", keyList3, "PRODUCT_ID", conn60);
		// 生成主键的列集合
		List<String> keyListUP = new ArrayList<String>();
		keyListUP.add("OPERATION_PRODUCT_ID");
		keyListUP.add("PLATFORM_ID");
		keyListUP.add("SHOP_ID");
		keyListUP.add("CITY_ID");
		keyListUP.add("SKU_ID");
		keyListUP.add("TYPE");
		keyListUP.add("SHOP_CODE");
		// 读取标准表的用户和产品
		HashMap<String, String> filterUP = new HashMap<String, String>();

		// 读取补充库中的数据
//		HashMap<String, List<HashMap<String, String>>> amazonPriceData = BaseDaoSqlServer
//				.getTbaleAllData16("[skyscope].[dbo].[AMAZON_PRICE_ALL_copy]", filterUP, keyListUP, conn16);
		HashMap<String, List<HashMap<String, String>>> amazonPriceData = BaseDao
				.getTbaleAllData("syntun_v2.AMAZON_PRICE_ALL", filterUP, keyListUP, conn138);
		
		System.out.println("亚马逊补数查询结果数据条数：" + amazonPriceData.size());

		// 生成主键的列集合
		List<String> keyListSL = new ArrayList<String>();
		keyListSL.add("platform_id");
		keyListSL.add("shop_type");
		// 读取标准表的用户和产品
		HashMap<String, String> filterSL = new HashMap<String, String>();
		filterSL.put("platform_id", "10");
		// 读取补充库中的数据
//		HashMap<String, String> shopListData = BaseDaoSqlServer
//				.getShopListData15("[syntun_base].[dbo].[shop_list]", filterSL, keyListSL, conn15);
		HashMap<String, String> shopListData = BaseDao
				.getShopListData("syntun_v2.SHOP_LIST", filterSL, keyListSL, conn138);
				
		// 生成主键的列集合，，促销数据现在不补（保留）
//		List<String> keyListAP = new ArrayList<String>();
//		keyListAP.add("operation_product_id");
//		keyListAP.add("platform_id");
//		keyListAP.add("shop_id");
//		keyListAP.add("city_id");
//		// 读取补充库中的数据，，关联，，促销补数（勿删）
//		HashMap<String, List<HashMap<String, String>>> amazonPromotionData = BaseDaoSqlServer
//				.getTbaleAllData16("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", filterUP, keyListAP, conn16);
		
		// 读取价格结果表的字段列表
		//List<String> amazonFiledList = BaseDaoSqlServer.getField16("[skyscope].[dbo].[AMAZON_PRICE_ALL_copy]", "192.168.0.16", "skyscope");
		List<String> amazonFiledList = BaseDao.getField("syntun_v2.AMAZON_PRICE_ALL", conn138);
		// 读取促销结果表的字段列表
		//List<String> amazonFiledListP = BaseDaoSqlServer.getField16("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", "192.168.0.16", "skyscope");
		
		// 读取价格结果表的字段列表
		//List<String> priceProductFiledList = BaseDaoSqlServer.getField16("[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]", "192.168.0.16", "skyscope");
		List<String> priceProductFiledList = BaseDao.getField("syntun_v2.PRICE_PRODUCT_LIST_HALF", conn138);
		// 读取促销结果表的字段列表
		//List<String> pricePromotionFiledList = BaseDaoSqlServer.getField16("[skyscope].[dbo].[PRICE_PROMOTION_INFO_half_copy]", "192.168.0.16", "skyscope");
		List<String> pricePromotionFiledList = BaseDao.getField("syntun_v2.PRICE_PROMOTION_INFO_half", conn138);
		
		try {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmm"); 
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateStr1 = df1.format(new Date(df.parse(dateStr+" "+hour+":"+min).getTime() - (long)3*30*60*1000));
	    	String dateStr2 = df1.format(new Date(df.parse(dateStr+" "+hour+":"+min).getTime()));
	    	// 删除价格结果
			//BaseDaoSqlServer.deleteTbaleData("[skyscope].[dbo].[AMAZON_PRICE_ALL_copy]", dateStr1, dateStr2, conn16);
	    	BaseDao.deleteTbaleData("syntun_v2.AMAZON_PRICE_ALL", dateStr1, dateStr2);
			// 删除促销结果
			//BaseDaoSqlServer.deleteTbaleData("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", dateStr1, dateStr2, conn16);
			//BaseDao.deleteTbaleData("syntun_v2.AMAZON_PROMOTION_ALL", dateStr1, dateStr2);
		} catch (Exception e) {
			// 删除补充库中的价格数据
			//BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[AMAZON_PRICE_ALL_copy]", conn16);
			//BaseDao.truncateTbaleAllData("syntun_v2.AMAZON_PRICE_ALL", conn138);
			// 删除补充库中的促销数据
			//BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", conn16);
		}
    	
//		// 删除补充库中的价格数据
//		BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[AMAZON_PRICE_ALL_copy]", conn16);
//		// 删除补充库中的促销数据
//		BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", conn16);
//		// 删除价格结果
//		boolean ccc = BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]", conn16);
//		// 删除促销结果
//		boolean ddd = BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[PRICE_PROMOTION_INFO_half_copy]", conn16);
		ConnectSql138.push(conn138);
		ConnectSql60.push(conn60);
		ConnectSql53.push(conn53);
		//ConnectSqlServer16.push(conn16);
		//ConnectSqlServer.push(conn15);
		
		HashMap<String, List<HashMap<String, String>>> otherPriceData = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> allPriceData = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> controlPriceData = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> addPriceData = 
				new HashMap<String, List<HashMap<String, String>>>();
		//HashMap<String, List<HashMap<String, String>>> ifAddPriceData = 
		//		new HashMap<String, List<HashMap<String, String>>>();
		
		HashMap<String, List<HashMap<String, String>>> otherPromotionData = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> allPromotionData = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> addPromotionData = 
				new HashMap<String, List<HashMap<String, String>>>();
		
		HashMap<String, List<HashMap<String, String>>> priceDataAll = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> promotionDataAll = 
				new HashMap<String, List<HashMap<String, String>>>();
		
		//价格数据整理
		for (String k : priceData.keySet()) {
			if(k.split("\001")[1].equals("5") || k.split("\001")[1].equals("10") || k.split("\001")[1].equals("47")){
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "1" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceData.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					// 国美、天猫、淘宝，匹配上输出，匹配不上过滤
					if(priceMap.get("platform_id").equals("5") || priceMap.get("platform_id").equals("47")){
						if(productInfo.containsKey(priceMap.get("operation_product_id")+"\001"+skuId)){
							priceMap.put("product_id", productInfo.get(priceMap.get("operation_product_id")+"\001"+skuId));
						}else if(productInfo0.containsKey(priceMap.get("operation_product_id"))){
							priceMap.put("product_id", productInfo0.get(priceMap.get("operation_product_id")));
						}else{
							continue;
						}
					}
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "1");
					li.add(priceMap);
				}
				allPriceData.put(key, li);
			}else if(k.split("\001")[1].equals("50")){
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "2" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceData.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "2");
					li.add(priceMap);
				}
				otherPriceData.put(key, li);
			}else{
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "1" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceData.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					// 国美、天猫、淘宝，匹配上输出，匹配不上过滤
					if(priceMap.get("platform_id").equals("4")){
						if(productInfo.containsKey(priceMap.get("operation_product_id")+"\001"+skuId)){
							priceMap.put("product_id", productInfo.get(priceMap.get("operation_product_id")+"\001"+skuId));
						}else if(productInfo0.containsKey(priceMap.get("operation_product_id"))){
							priceMap.put("product_id", productInfo0.get(priceMap.get("operation_product_id")));
						}else{
							continue;
						}
					}
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "1");
					li.add(priceMap);
				}
				otherPriceData.put(key, li);
			}
		}
		for (String k : priceDataMB.keySet()) {
			if(k.split("\001")[1].equals("5") || k.split("\001")[1].equals("10") || k.split("\001")[1].equals("47")){
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "2" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceDataMB.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					// 国美、天猫、淘宝，匹配上输出，匹配不上过滤
					if(priceMap.get("platform_id").equals("5") || priceMap.get("platform_id").equals("47")){
						if(productInfo.containsKey(priceMap.get("operation_product_id")+"\001"+skuId)){
							priceMap.put("product_id", productInfo.get(priceMap.get("operation_product_id")+"\001"+skuId));
						}else if(productInfo0.containsKey(priceMap.get("operation_product_id"))){
							priceMap.put("product_id", productInfo0.get(priceMap.get("operation_product_id")));
						}else{
							continue;
						}
					}
					
					if (!priceMap.get("mobile_price").equals("-1")){
						priceMap.put("promotion_price", priceMap.get("mobile_price"));
					}
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "2");
					li.add(priceMap);
				}
				allPriceData.put(key, li);
			}else{
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "2" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceDataMB.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					// 国美、天猫、淘宝，匹配上输出，匹配不上过滤
					if(priceMap.get("platform_id").equals("4")){
						if(productInfo.containsKey(priceMap.get("operation_product_id")+"\001"+skuId)){
							priceMap.put("product_id", productInfo.get(priceMap.get("operation_product_id")+"\001"+skuId));
						}else if(productInfo0.containsKey(priceMap.get("operation_product_id"))){
							priceMap.put("product_id", productInfo0.get(priceMap.get("operation_product_id")));
						}else{
							continue;
						}
					}
					if (!priceMap.get("mobile_price").equals("-1")){
						priceMap.put("promotion_price", priceMap.get("mobile_price"));
					}
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "2");
					li.add(priceMap);
				}
				otherPriceData.put(key, li);
			}
		}
		for (String k : priceData53.keySet()) {
			if(k.split("\001")[1].equals("5") || k.split("\001")[1].equals("10") || k.split("\001")[1].equals("47")){
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "2" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceData53.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					// 国美、天猫、淘宝，匹配上输出，匹配不上过滤
					if(priceMap.get("platform_id").equals("5") || priceMap.get("platform_id").equals("47")){
						if(productInfo.containsKey(priceMap.get("operation_product_id")+"\001"+skuId)){
							priceMap.put("product_id", productInfo.get(priceMap.get("operation_product_id")+"\001"+skuId));
						}else if(productInfo0.containsKey(priceMap.get("operation_product_id"))){
							priceMap.put("product_id", productInfo0.get(priceMap.get("operation_product_id")));
						}else{
							continue;
						}
					}
					if (!priceMap.get("mobile_price").equals("-1")){
						priceMap.put("promotion_price", priceMap.get("mobile_price"));
					}
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "2");
					li.add(priceMap);
				}
				allPriceData.put(key, li);
			}else{
				String shopCode = k.split("\001")[5].equals("无") ? "-1" : k.split("\001")[5];
				String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
				if(skuId==null||skuId.equals("")||skuId.equals("null")){
					skuId = "0";
				}
				String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
							k.split("\001")[3] + "\001" + skuId + "\001" + "2" + "\001" + shopCode;
				// 产品价格列表
				List<HashMap<String, String>> priceD = priceData53.get(k);
				List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> priceMap : priceD) {
					// 国美、天猫、淘宝，匹配上输出，匹配不上过滤
					if(priceMap.get("platform_id").equals("4")){
						if(productInfo.containsKey(priceMap.get("operation_product_id")+"\001"+skuId)){
							priceMap.put("product_id", productInfo.get(priceMap.get("operation_product_id")+"\001"+skuId));
						}else if(productInfo0.containsKey(priceMap.get("operation_product_id"))){
							priceMap.put("product_id", productInfo0.get(priceMap.get("operation_product_id")));
						}else{
							continue;
						}
					}
					if (!priceMap.get("mobile_price").equals("-1")){
						priceMap.put("promotion_price", priceMap.get("mobile_price"));
					}
					priceMap.put("shop_code", priceMap.get("shop_code").equals("无")?"-1":priceMap.get("shop_code"));
					priceMap.put("sku_id", skuId);
					priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));
					priceMap.put("type", "2");
					li.add(priceMap);
				}
				otherPriceData.put(key, li);
			}
		}
		//对照数据整理
		for (String k : amazonPriceData.keySet()) {
			String shopCode = k.split("\001")[6].equals("无") ? "-1" : k.split("\001")[6];
			String skuId = k.split("\001")[4].equals("def") ? "0" : k.split("\001")[4];
			if(skuId==null||skuId.equals("")||skuId.equals("null")){
				skuId = "0";
			}
			String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" +
						k.split("\001")[3] + "\001" + skuId + "\001" + k.split("\001")[5] + "\001" + shopCode;
			// 产品价格列表
			List<HashMap<String, String>> priceD = amazonPriceData.get(k);
//			if(priceD.size()>1){
//				System.out.println("重复数据条数："+priceD.size()+"；主键："+k);
//			}
			List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
			for (HashMap<String, String> priceMap : priceD) {
				priceMap.put("SHOP_CODE", priceMap.get("SHOP_CODE")!=null&&priceMap.get("SHOP_CODE").equals("无")?"-1":priceMap.get("SHOP_CODE"));
				priceMap.put("SKU_ID", skuId);
				priceMap.put("PRODUCT_PRICE", priceMap.get("PRODUCT_PRICE")!=null?priceMap.get("PRODUCT_PRICE").replace(",", ""):"");
				li.add(priceMap);
			}
			controlPriceData.put(key, li);
		}
		
		//关联
		for (String k : controlPriceData.keySet()) {
			//String key = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + 
			//		k.split("\001")[2] + "\001" + k.split("\001")[3];
			if(!allPriceData.containsKey(k)){
				addPriceData.put(k, controlPriceData.get(k));
				//ifAddPriceData.put(key, controlPriceData.get(k));
			}
		}
		HashMap<String, String> amazonFiledMap = new HashMap<String, String>();
		HashMap<String, String> priceProductFiledMap = new HashMap<String, String>();
		// 价格结果数据
		for (String k : allPriceData.keySet()) {
			// 产品价格列表
			List<HashMap<String, String>> priceD = allPriceData.get(k);
			for (HashMap<String, String> priceMap : priceD) {
				// 平台“拼多多”，不需要PC端数据
				if(priceMap.get("platform_id").equals("50") && priceMap.get("type").equals("1")){
					continue;
				}
				
				String shopName = priceMap.get("shop_name");
				if(shopName != null && shopName.indexOf("（") != -1)
					shopName = shopName.split("（")[0];
				
				amazonFiledMap.put("id", priceMap.get("id"));
				amazonFiledMap.put("shop_name", shopName);
				amazonFiledMap.put("product_price", priceMap.get("product_price"));
				amazonFiledMap.put("promotion_price", priceMap.get("promotion_price"));
				amazonFiledMap.put("product_id", priceMap.get("product_id"));
				amazonFiledMap.put("operation_product_id", priceMap.get("operation_product_id"));
				amazonFiledMap.put("shop_id", priceMap.get("shop_id"));
				amazonFiledMap.put("platform_id", priceMap.get("platform_id"));
				amazonFiledMap.put("hour", priceMap.get("hour"));
				amazonFiledMap.put("min", priceMap.get("min"));
				amazonFiledMap.put("get_date", priceMap.get("get_date"));
				amazonFiledMap.put("c_id", priceMap.get("C_ID"));
				amazonFiledMap.put("shop_code", priceMap.get("shop_code"));
				amazonFiledMap.put("sku_id", priceMap.get("sku_id"));
				amazonFiledMap.put("city_id", priceMap.get("city_id"));
				amazonFiledMap.put("type", priceMap.get("type"));
				
				String sql = ConvertSql.getSql("syntun_v2.AMAZON_PRICE_ALL", amazonFiledList, amazonFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				AmazonPriceAll.add(sql);
				
				priceProductFiledMap.put("id", priceMap.get("id"));
				priceProductFiledMap.put("shop_name", shopName);
				priceProductFiledMap.put("product_price", priceMap.get("product_price"));
				priceProductFiledMap.put("promotion_price", priceMap.get("promotion_price"));
				priceProductFiledMap.put("product_id", priceMap.get("product_id"));
				priceProductFiledMap.put("operation_product_id", priceMap.get("operation_product_id"));
				priceProductFiledMap.put("shop_id", priceMap.get("shop_id"));
				priceProductFiledMap.put("platform_id", priceMap.get("platform_id"));
				priceProductFiledMap.put("hour", priceMap.get("hour"));
				priceProductFiledMap.put("min", priceMap.get("min"));
				priceProductFiledMap.put("get_date", priceMap.get("get_date"));
				priceProductFiledMap.put("c_id", priceMap.get("C_ID"));
				priceProductFiledMap.put("shop_code", priceMap.get("shop_code"));
				priceProductFiledMap.put("sku_id", priceMap.get("sku_id"));
				priceProductFiledMap.put("city_id", priceMap.get("city_id"));
				priceProductFiledMap.put("type", priceMap.get("type"));
				
				String sql1 = ConvertSql.getSql("syntun_v2.PRICE_PRODUCT_LIST_HALF", priceProductFiledList, priceProductFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				PriceProductList.add(sql1);
				
				String skuId = priceMap.get("sku_id");
//				if(priceMap.get("sku_id")==null||priceMap.get("sku_id").equals("")||priceMap.get("sku_id").equals("null")){
//					skuId = "0";
//				}
				String shopId = priceMap.get("shop_id");
				if(shopListData.containsKey(priceMap.get("platform_id")+"\001"+priceMap.get("shop_code"))){
					shopId = shopListData.get(priceMap.get("platform_id")+"\001"+priceMap.get("shop_code"));
				}
				String priceKey1 = priceMap.get("platform_id") + "\001" +
						shopId + "\001" +
						priceMap.get("operation_product_id") + "\001" +
						skuId + "\001" +
						priceMap.get("city_id") + "\001" +
						priceMap.get("type") + "\001" +
						priceMap.get("product_id") + "\001" +
						priceMap.get("shop_code");
				List<HashMap<String, String>> li1 = null;
				if (priceDataAll.containsKey(priceKey1)) {
					li1 = priceDataAll.get(priceKey1);
				} else {
					li1 = new ArrayList<HashMap<String, String>>();
				}
				priceProductFiledMap.put("table_name", "PRICE_PRODUCT_LIST");
				
				HashMap<String, String> m=new HashMap<String, String>(priceProductFiledMap);
				li1.add(m);
				priceDataAll.put(priceKey1, li1);
			}
		}
		// 其他价格结果数据
		for (String k : otherPriceData.keySet()) {
			// 产品价格列表
			List<HashMap<String, String>> priceD = otherPriceData.get(k);
			for (HashMap<String, String> priceMap : priceD) {
				// 平台“拼多多”，不需要PC端数据
				if(priceMap.get("platform_id").equals("50") && priceMap.get("type").equals("1")){
					continue;
				}
				
				String shopName = priceMap.get("shop_name");
				if(shopName != null && shopName.indexOf("（") != -1)
					shopName = shopName.split("（")[0];
			
				priceProductFiledMap.put("id", priceMap.get("id"));
				priceProductFiledMap.put("shop_name", shopName);
				priceProductFiledMap.put("product_price", priceMap.get("product_price"));
				priceProductFiledMap.put("promotion_price", priceMap.get("promotion_price"));
				priceProductFiledMap.put("product_id", priceMap.get("product_id"));
				priceProductFiledMap.put("operation_product_id", priceMap.get("operation_product_id"));
				priceProductFiledMap.put("shop_id", priceMap.get("shop_id"));
				priceProductFiledMap.put("platform_id", priceMap.get("platform_id"));
				priceProductFiledMap.put("hour", priceMap.get("hour"));
				priceProductFiledMap.put("min", priceMap.get("min"));
				priceProductFiledMap.put("get_date", priceMap.get("get_date"));
				priceProductFiledMap.put("c_id", priceMap.get("C_ID"));
				priceProductFiledMap.put("shop_code", priceMap.get("shop_code"));
				priceProductFiledMap.put("sku_id", priceMap.get("sku_id"));
				priceProductFiledMap.put("city_id", priceMap.get("city_id"));
				priceProductFiledMap.put("type", priceMap.get("type"));
				
				String sql1 = ConvertSql.getSql("syntun_v2.PRICE_PRODUCT_LIST_HALF", priceProductFiledList, priceProductFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				PriceProductList.add(sql1);
				
				String skuId = priceMap.get("sku_id");
//				if(priceMap.get("sku_id")==null||priceMap.get("sku_id").equals("")||priceMap.get("sku_id").equals("null")){
//					skuId = "0";
//				}
				String shopId = priceMap.get("shop_id");
				if(shopListData.containsKey(priceMap.get("platform_id")+"\001"+priceMap.get("shop_code"))){
					shopId = shopListData.get(priceMap.get("platform_id")+"\001"+priceMap.get("shop_code"));
				}
				String priceKey2 = priceMap.get("platform_id") + "\001" +
						shopId + "\001" +
						priceMap.get("operation_product_id") + "\001" +
						skuId + "\001" +
						priceMap.get("city_id") + "\001" +
						priceMap.get("type") + "\001" +
						priceMap.get("product_id") + "\001" +
						priceMap.get("shop_code");
				List<HashMap<String, String>> li2 = null;
				if (priceDataAll.containsKey(priceKey2)) {
					li2 = priceDataAll.get(priceKey2);
				} else {
					li2 = new ArrayList<HashMap<String, String>>();
				}
				priceProductFiledMap.put("table_name", "PRICE_PRODUCT_LIST");
				HashMap<String, String> m=new HashMap<String, String>(priceProductFiledMap);
				li2.add(m);
				priceDataAll.put(priceKey2, li2);
			}
		}
		// 价格未匹配结果
		for (String k : addPriceData.keySet()) {
			// 产品价格列表
			List<HashMap<String, String>> priceD = addPriceData.get(k);
			for (HashMap<String, String> priceMap : priceD) {
				
				String shopName = priceMap.get("shop_name");
				if(shopName != null && shopName.indexOf("（") != -1)
					shopName = shopName.split("（")[0];
				
				amazonFiledMap.put("id", priceMap.get("id"));
				amazonFiledMap.put("shop_name", shopName);
				amazonFiledMap.put("product_price", priceMap.get("product_price"));
				amazonFiledMap.put("promotion_price", priceMap.get("promotion_price"));
				amazonFiledMap.put("product_id", priceMap.get("product_id"));
				amazonFiledMap.put("operation_product_id", priceMap.get("operation_product_id"));
				amazonFiledMap.put("shop_id", priceMap.get("shop_id"));
				amazonFiledMap.put("platform_id", priceMap.get("platform_id"));
//				amazonFiledMap.put("hour", priceMap.get("hour"));
//				amazonFiledMap.put("min", priceMap.get("min"));
//				amazonFiledMap.put("get_date", priceMap.get("get_date"));
				amazonFiledMap.put("hour", hour);
				amazonFiledMap.put("min", min);
				amazonFiledMap.put("get_date", dateStr);
				amazonFiledMap.put("c_id", priceMap.get("c_id"));
				amazonFiledMap.put("shop_code", priceMap.get("shop_code"));
				amazonFiledMap.put("sku_id", priceMap.get("sku_id"));
				amazonFiledMap.put("city_id", priceMap.get("city_id"));
				amazonFiledMap.put("type", priceMap.get("type"));
				
				String sql = ConvertSql.getSql("syntun_v2.AMAZON_PRICE_ALL", amazonFiledList, amazonFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				AmazonPriceAll.add(sql);
				
				priceProductFiledMap.put("id", priceMap.get("id"));
				priceProductFiledMap.put("shop_name", shopName);
				priceProductFiledMap.put("product_price", priceMap.get("product_price"));
				priceProductFiledMap.put("promotion_price", priceMap.get("promotion_price"));
				priceProductFiledMap.put("product_id", priceMap.get("product_id"));
				priceProductFiledMap.put("operation_product_id", priceMap.get("operation_product_id"));
				priceProductFiledMap.put("shop_id", priceMap.get("shop_id"));
				priceProductFiledMap.put("platform_id", priceMap.get("platform_id"));
//				priceProductFiledMap.put("hour", priceMap.get("hour"));
//				priceProductFiledMap.put("min", priceMap.get("min"));
//				priceProductFiledMap.put("get_date", priceMap.get("get_date"));
				priceProductFiledMap.put("hour", hour);
				priceProductFiledMap.put("min", min);
				priceProductFiledMap.put("get_date", dateStr);
				priceProductFiledMap.put("c_id", priceMap.get("c_id"));
				priceProductFiledMap.put("shop_code", priceMap.get("shop_code"));
				priceProductFiledMap.put("sku_id", priceMap.get("sku_id"));
				priceProductFiledMap.put("city_id", priceMap.get("city_id"));
				priceProductFiledMap.put("type", priceMap.get("type"));
				
				String sql1 = ConvertSql.getSql("syntun_v2.PRICE_PRODUCT_LIST_HALF", priceProductFiledList, priceProductFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				PriceProductList.add(sql1);
				
				String skuId = priceMap.get("sku_id");
//				if(priceMap.get("sku_id")==null||priceMap.get("sku_id").equals("")||priceMap.get("sku_id").equals("null")){
//					skuId = "0";
//				}
				String shopId = priceMap.get("shop_id");
				if(shopListData.containsKey(priceMap.get("platform_id")+"\001"+priceMap.get("shop_code"))){
					shopId = shopListData.get(priceMap.get("platform_id")+"\001"+priceMap.get("shop_code"));
				}
				String priceKey3 = priceMap.get("platform_id") + "\001" +
						shopId + "\001" +
						priceMap.get("operation_product_id") + "\001" +
						skuId + "\001" +
						priceMap.get("city_id") + "\001" +
						priceMap.get("type") + "\001" +
						priceMap.get("product_id") + "\001" +
						priceMap.get("shop_code");
				List<HashMap<String, String>> li3 = null;
				if (priceDataAll.containsKey(priceKey3)) {
					li3 = priceDataAll.get(priceKey3);
				} else {
					li3 = new ArrayList<HashMap<String, String>>();
				}
				priceProductFiledMap.put("table_name", "PRICE_PRODUCT_LIST");
				HashMap<String, String> m=new HashMap<String, String>(priceProductFiledMap);
				li3.add(m);
				priceDataAll.put(priceKey3, li3);
			}
		}
		
		//促销数据整理 
		for (String k : promotionData.keySet()) {
			if(k.split("\001")[1].equals("4") || k.split("\001")[1].equals("5") || k.split("\001")[1].equals("47")){
				allPromotionData.put(k, promotionData.get(k));
			}else{
				otherPromotionData.put(k, promotionData.get(k));
			}
		}
		
//		HashMap<String, String> amazonFiledMapP = new HashMap<String, String>();
		HashMap<String, String> pricePromotionFiledMap = new HashMap<String, String>();
		// 促销结果数据
		for (String k : allPromotionData.keySet()) {
			// 产品促销列表
			List<HashMap<String, String>> promotionD = allPromotionData.get(k);
			for (HashMap<String, String> promotionMap : promotionD) {
				
				String shopName = promotionMap.get("shop_name");
				if(shopName != null && shopName.indexOf("（") != -1)
					shopName = shopName.split("（")[0];
				String promotionTypeInfo = promotionMap.get("promotion_type_info");
				if(promotionTypeInfo != null && promotionTypeInfo.indexOf("'") != -1)
					promotionTypeInfo = promotionTypeInfo.replace("'", "");
				
//				amazonFiledMapP.put("promotion_id", promotionMap.get("promotion_id"));
//				amazonFiledMapP.put("promotion_type_name", promotionMap.get("promotion_type_name"));
//				amazonFiledMapP.put("promotion_type_info", promotionTypeInfo);
//				amazonFiledMapP.put("promotion_about_info", promotionMap.get("promotion_about_info"));
//				amazonFiledMapP.put("shop_name", shopName);
//				amazonFiledMapP.put("operation_product_id", promotionMap.get("operation_product_id"));
//				amazonFiledMapP.put("product_id", promotionMap.get("product_id"));
//				amazonFiledMapP.put("shop_id", promotionMap.get("shop_id"));
//				amazonFiledMapP.put("platform_id", promotionMap.get("platform_id"));
//				amazonFiledMapP.put("hour", promotionMap.get("hour"));
//				amazonFiledMapP.put("min", promotionMap.get("min"));
//				amazonFiledMapP.put("get_date", promotionMap.get("get_date"));
//				amazonFiledMapP.put("c_id", promotionMap.get("c_id"));
//				amazonFiledMapP.put("shop_code", promotionMap.get("shop_code"));
//				amazonFiledMapP.put("sku_id", promotionMap.get("sku_id"));
//				amazonFiledMapP.put("city_id", promotionMap.get("city_id"));
//				
//				String sql = ConvertSql.getSqlServer("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", amazonFiledListP, amazonFiledMapP);
//				System.out.println(xiajiaTatal.size() + "___" + sql);
//				AmazonPromotionAll.add(sql);
				
				pricePromotionFiledMap.put("promotion_id", promotionMap.get("promotion_id"));
				pricePromotionFiledMap.put("promotion_type_name", promotionMap.get("promotion_type_name"));
				pricePromotionFiledMap.put("promotion_type_info", promotionTypeInfo);
				pricePromotionFiledMap.put("promotion_about_info", promotionMap.get("promotion_about_info"));
				pricePromotionFiledMap.put("shop_name", shopName);
				pricePromotionFiledMap.put("operation_product_id", promotionMap.get("operation_product_id"));
				pricePromotionFiledMap.put("product_id", promotionMap.get("product_id"));
				pricePromotionFiledMap.put("shop_id", promotionMap.get("shop_id"));
				pricePromotionFiledMap.put("platform_id", promotionMap.get("platform_id"));
				pricePromotionFiledMap.put("hour", promotionMap.get("hour"));
				pricePromotionFiledMap.put("min", promotionMap.get("min"));
				pricePromotionFiledMap.put("get_date", promotionMap.get("get_date"));
				pricePromotionFiledMap.put("c_id", promotionMap.get("c_id"));
				pricePromotionFiledMap.put("shop_code", promotionMap.get("shop_code"));
				pricePromotionFiledMap.put("sku_id", promotionMap.get("sku_id"));
				pricePromotionFiledMap.put("city_id", promotionMap.get("city_id"));
				
				String sql1 = ConvertSql.getSql("syntun_v2.PRICE_PROMOTION_INFO_half", pricePromotionFiledList, pricePromotionFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				PricePromotionInfo.add(sql1);
				
				String promotionKey1 = promotionMap.get("platform_id") + "\001" +
						promotionMap.get("shop_id") + "\001" +
						promotionMap.get("operation_product_id");
				List<HashMap<String, String>> li1 = null;
				if (promotionDataAll.containsKey(promotionKey1)) {
					li1 = promotionDataAll.get(promotionKey1);
				} else {
					li1 = new ArrayList<HashMap<String, String>>();
				}
				pricePromotionFiledMap.put("table_name", "PRICE_PROMOTION_INFO");
				HashMap<String, String> m=new HashMap<String, String>(pricePromotionFiledMap);
				li1.add(m);
				promotionDataAll.put(promotionKey1, li1);
			}
		}
		for (String k : otherPromotionData.keySet()) {
			// 产品促销列表
			List<HashMap<String, String>> promotionD = otherPromotionData.get(k);
			for (HashMap<String, String> promotionMap : promotionD) {
				
				String shopName = promotionMap.get("shop_name");
				if(shopName != null && shopName.indexOf("（") != -1)
					shopName = shopName.split("（")[0];
				String promotionTypeInfo = promotionMap.get("promotion_type_info");
				if(promotionTypeInfo != null && promotionTypeInfo.indexOf("'") != -1)
					promotionTypeInfo = promotionTypeInfo.replace("'", "");
				
				pricePromotionFiledMap.put("promotion_id", promotionMap.get("promotion_id"));
				pricePromotionFiledMap.put("promotion_type_name", promotionMap.get("promotion_type_name"));
				pricePromotionFiledMap.put("promotion_type_info", promotionTypeInfo);
				pricePromotionFiledMap.put("promotion_about_info", promotionMap.get("promotion_about_info"));
				pricePromotionFiledMap.put("shop_name", shopName);
				pricePromotionFiledMap.put("operation_product_id", promotionMap.get("operation_product_id"));
				pricePromotionFiledMap.put("product_id", promotionMap.get("product_id"));
				pricePromotionFiledMap.put("shop_id", promotionMap.get("shop_id"));
				pricePromotionFiledMap.put("platform_id", promotionMap.get("platform_id"));
				pricePromotionFiledMap.put("hour", promotionMap.get("hour"));
				pricePromotionFiledMap.put("min", promotionMap.get("min"));
				pricePromotionFiledMap.put("get_date", promotionMap.get("get_date"));
				pricePromotionFiledMap.put("c_id", promotionMap.get("c_id"));
				pricePromotionFiledMap.put("shop_code", promotionMap.get("shop_code"));
				pricePromotionFiledMap.put("sku_id", promotionMap.get("sku_id"));
				pricePromotionFiledMap.put("city_id", promotionMap.get("city_id"));
				
				String sql1 = ConvertSql.getSql("syntun_v2.PRICE_PROMOTION_INFO_half", pricePromotionFiledList, pricePromotionFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				PricePromotionInfo.add(sql1);
				
				String promotionKey2 = promotionMap.get("platform_id") + "\001" +
						promotionMap.get("shop_id") + "\001" +
						promotionMap.get("operation_product_id");
				List<HashMap<String, String>> li2 = null;
				if (promotionDataAll.containsKey(promotionKey2)) {
					li2 = promotionDataAll.get(promotionKey2);
				} else {
					li2 = new ArrayList<HashMap<String, String>>();
				}
				pricePromotionFiledMap.put("table_name", "PRICE_PROMOTION_INFO");
				HashMap<String, String> m=new HashMap<String, String>(pricePromotionFiledMap);
				li2.add(m);
				promotionDataAll.put(promotionKey2, li2);
			}
		}
		
		//关联，，促销补数（勿删）
//		for (String k : amazonPromotionData.keySet()) {
//			if(!allPromotionData.containsKey(k) && ifAddPriceData.containsKey(k)){
//				addPromotionData.put(k, amazonPromotionData.get(k));
//			}
//		}
				
		// 促销补数
		for (String k : addPromotionData.keySet()) {
			// 产品价格列表
			List<HashMap<String, String>> promotionD = addPromotionData.get(k);
			
			for (HashMap<String, String> promotionMap : promotionD) {
				String shopName = promotionMap.get("shop_name");
				if(shopName != null && shopName.indexOf("（") != -1)
					shopName = shopName.split("（")[0];
				String promotionTypeInfo = promotionMap.get("promotion_type_info");
				if(promotionTypeInfo != null && promotionTypeInfo.indexOf("'") != -1)
					promotionTypeInfo = promotionTypeInfo.replace("'", "");
				
//				amazonFiledMapP.put("promotion_id", promotionMap.get("promotion_id"));
//				amazonFiledMapP.put("promotion_type_name", promotionMap.get("promotion_type_name"));
//				amazonFiledMapP.put("promotion_type_info", promotionTypeInfo);
//				amazonFiledMapP.put("promotion_about_info", promotionMap.get("promotion_about_info"));
//				amazonFiledMapP.put("shop_name", shopName);
//				amazonFiledMapP.put("operation_product_id", promotionMap.get("operation_product_id"));
//				amazonFiledMapP.put("product_id", promotionMap.get("product_id"));
//				amazonFiledMapP.put("shop_id", promotionMap.get("shop_id"));
//				amazonFiledMapP.put("platform_id", promotionMap.get("platform_id"));
//				amazonFiledMapP.put("hour", hour);
//				amazonFiledMapP.put("min", min);
//				amazonFiledMapP.put("get_date", dateStr);
//				amazonFiledMapP.put("c_id", promotionMap.get("c_id"));
//				amazonFiledMapP.put("shop_code", promotionMap.get("shop_code"));
//				amazonFiledMapP.put("sku_id", promotionMap.get("sku_id"));
//				amazonFiledMapP.put("city_id", promotionMap.get("city_id"));
//				
//				String sql = ConvertSql.getSqlServer("[skyscope].[dbo].[AMAZON_PROMOTION_ALL_copy]", amazonFiledListP, amazonFiledMapP);
//				System.out.println(xiajiaTatal.size() + "___" + sql);
//				AmazonPromotionAll.add(sql);
				
				pricePromotionFiledMap.put("promotion_id", promotionMap.get("promotion_id"));
				pricePromotionFiledMap.put("promotion_type_name", promotionMap.get("promotion_type_name"));
				pricePromotionFiledMap.put("promotion_type_info", promotionTypeInfo);
				pricePromotionFiledMap.put("promotion_about_info", promotionMap.get("promotion_about_info"));
				pricePromotionFiledMap.put("shop_name", shopName);
				pricePromotionFiledMap.put("operation_product_id", promotionMap.get("operation_product_id"));
				pricePromotionFiledMap.put("product_id", promotionMap.get("product_id"));
				pricePromotionFiledMap.put("shop_id", promotionMap.get("shop_id"));
				pricePromotionFiledMap.put("platform_id", promotionMap.get("platform_id"));
				pricePromotionFiledMap.put("hour", hour);
				pricePromotionFiledMap.put("min", min);
				pricePromotionFiledMap.put("get_date", dateStr);
				pricePromotionFiledMap.put("c_id", promotionMap.get("c_id"));
				pricePromotionFiledMap.put("shop_code", promotionMap.get("shop_code"));
				pricePromotionFiledMap.put("sku_id", promotionMap.get("sku_id"));
				pricePromotionFiledMap.put("city_id", promotionMap.get("city_id"));
				
				String sql1 = ConvertSql.getSql("syntun_v2.PRICE_PROMOTION_INFO_half", pricePromotionFiledList, pricePromotionFiledMap);
				//System.out.println(xiajiaTatal.size() + "___" + sql);
				PricePromotionInfo.add(sql1);
				
				String promotionKey3 = promotionMap.get("platform_id") + "\001" +
						promotionMap.get("shop_id") + "\001" +
						promotionMap.get("operation_product_id");
				List<HashMap<String, String>> li3 = null;
				if (promotionDataAll.containsKey(promotionKey3)) {
					li3 = promotionDataAll.get(promotionKey3);
				} else {
					li3 = new ArrayList<HashMap<String, String>>();
				}
				pricePromotionFiledMap.put("table_name", "PRICE_PROMOTION_INFO");
				HashMap<String, String> m=new HashMap<String, String>(pricePromotionFiledMap);
				li3.add(m);
				promotionDataAll.put(promotionKey3, li3);
			}
		}
//		int i = 0;
//		for(String price : AmazonPriceAll) {
//			System.out.println(price);
//			i++;
//			if(i == 100)
//				break;
//		}
		
		System.out.println("---补数的价格数据条数： "+ AmazonPriceAll.size() + " ===");
		//System.out.println("---补数的促销数据条数： "+ AmazonPromotionAll.size() + " ===");
		System.out.println("---中间结果的价格数据条数： "+ PriceProductList.size() + " ===");
		System.out.println("---中间结果的促销数据条数： "+ PricePromotionInfo.size() + " ===");
		
		Thread t1 = new Thread(new InsertData138(AmazonPriceAll));
		t1.start();
//		Thread t2 = new Thread(new InsertDataSqlServer(AmazonPromotionAll));
//		t2.start();
		Thread t3 = new Thread(new InsertData138(PriceProductList));
		t3.start();
		Thread t4 = new Thread(new InsertData138(PricePromotionInfo));
		t4.start();
		
		System.out.println("===巡检程序已经被调用===");
		System.out.println("===价格巡检数据条数:"+ priceDataAll.size() + "===");
		System.out.println("===促销巡检数据条数:"+ promotionDataAll.size() + "===");
		SkyscopeInspect.getResult(dateStr, hour, min, day1, email, priceDataAll, promotionDataAll);
		
		// 中间结果插入完成之后再进行计算
//		boolean isAlice = true;
//		while(isAlice){
//			if(!t3.isAlive() && !t4.isAlive()){
//				isAlice = false;
//				System.out.println("===中间结果插入完成！！！===");
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//				Date day = new Date(); 
//				System.out.println("===中间结果插入完成时间： "+df.format(day));
//				HashMap<String, List<HashMap<String, String>>> priceDataN = 
//						new HashMap<String, List<HashMap<String, String>>>();
//				HashMap<String, List<HashMap<String, String>>> promotionDataN = 
//						new HashMap<String, List<HashMap<String, String>>>();
//				SkyscopeInspect.getResult(dateStr, hour, min, day1, email, priceDataN, promotionDataN);
//			}
//		}
	}
}
