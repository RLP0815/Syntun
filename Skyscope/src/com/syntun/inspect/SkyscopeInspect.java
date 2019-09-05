package com.syntun.inspect;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syntun.entity.PricePromotionInfo;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConnectSql138;
import com.syntun.etl.tools.ConnectSql22;
import com.syntun.etl.tools.ConnectSql53;
import com.syntun.etl.tools.ConnectSql60;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData60;
import com.syntun.etl.tools.InsertData138;
import com.syntun.etl.tools.InsertDataSqlServer;
import com.syntun.util.SyntunEmail;

/*
 * 价格巡检（所有用户）
 */
public class SkyscopeInspect {

	/**
	 * 结果sql集合
	 */
	public static List<String> etlResult = new ArrayList<String>();
	/**
	 * 雀巢结果sql集合
	 */
	public static List<String> etlResultNestle = new ArrayList<String>();
	/**
	 * 结果sql集合
	 */
	public static List<String> mysqlEtlResult = new ArrayList<String>();
	/**
	 * 雀巢结果sql集合
	 */
	public static List<String> mysqlEtlResultNestle = new ArrayList<String>();
	/**
	 * 结果sql集合-伊利
	 */
	public static List<String> etlResultYili = new ArrayList<String>();
	/**
	 * 错误集合
	 */
	public static List<String> errResult = new ArrayList<String>();
	/**
	 * 价格小于1的结果sql集合
	 */
	public static List<String> priceLess = new ArrayList<String>();
	/**
	 * 下架产品结果sql集合
	 */
	public static List<String> xiajiaTatal = new ArrayList<String>();
	/**
	 * 天猫库存为0结果sql集合
	 */
	public static List<String> tmallStockXiajia = new ArrayList<String>();
	/**
	 * 未处理折扣结果sql集合
	 */
	public static List<String> ZKsql = new ArrayList<String>();
	/**
	 * 未匹配促销结果sql集合
	 */
	public static List<String> promotionCompareInfo = new ArrayList<String>();
	/**
	 * 未处理折扣结果sql集合
	 */
	public static List<String> MJsql = new ArrayList<String>();
	/**
	 * 未处理折扣结果sql集合
	 */
	public static List<String> MJNsql = new ArrayList<String>();
	/**
	 * 未处理折扣结果sql集合
	 */
	public static List<String> MZsql = new ArrayList<String>();
	/**
	 * 未处理折扣结果sql集合
	 */
	public static List<String> MZNsql = new ArrayList<String>();

	public static void main(String[] args) {
		
	}

	public static void getResult(String dateStr, String hour, String min, Date day1, String email, 
			HashMap<String, List<HashMap<String, String>>> priceData, 
			HashMap<String, List<HashMap<String, String>>> promotionData) {
		/**
		 * 结果sql集合
		 */
		etlResult = new ArrayList<String>();
		/**
		 * 雀巢结果sql集合
		 */
		etlResultNestle = new ArrayList<String>();
		/**
		 * 结果sql集合
		 */
		mysqlEtlResult = new ArrayList<String>();
		/**
		 * 雀巢结果sql集合
		 */
		mysqlEtlResultNestle = new ArrayList<String>();
		/**
		 * 结果sql集合-伊利
		 */
		etlResultYili = new ArrayList<String>();
		/**
		 * 错误集合
		 */
		errResult = new ArrayList<String>();
		/**
		 * 价格小于1的结果sql集合
		 */
		priceLess = new ArrayList<String>();
		/**
		 * 下架产品结果sql集合
		 */
		xiajiaTatal = new ArrayList<String>();
		/**
		 * 天猫库存为0结果sql集合
		 */
		tmallStockXiajia = new ArrayList<String>();
		/**
		 * 未处理折扣结果sql集合、、[skyscope].[dbo].[折扣未处理_copy]
		 */
		ZKsql = new ArrayList<String>();
		/**
		 * 未匹配促销结果sql集合、、promotion_compare_info
		 */
		promotionCompareInfo = new ArrayList<String>();
		/**
		 * 满减处理结果sql集合
		 */
		MJsql = new ArrayList<String>();
		/**
		 * 满减未结果sql集合
		 */
		MJNsql = new ArrayList<String>();
		/**
		 * 满折处理结果sql集合
		 */
		MZsql = new ArrayList<String>();
		/**
		 * 满折未处理结果sql集合
		 */
		MZNsql = new ArrayList<String>();
		
		// 数据抓取时间
		String getDate = dateStr + " " + (hour.length() == 1 ? ("0" + hour) : hour) + ":"
				+ (min.equals("15") ? "00" : "30") + ":00";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		long millionSeconds = 0;
		try {
			millionSeconds = df.parse(getDate).getTime()/1000;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}//秒
		
		// 138数据库，数据库连接外传，便于读取数据代码复用
		Connection conn138 = ConnectSql138.getConn();
		// 60数据库，数据库连接外传，便于读取数据代码复用
		Connection conn60 = ConnectSql60.getConn();
		// 53数据库，数据库连接外传，便于读取数据代码复用
		Connection conn53 = ConnectSql53.getConn();
		// 16数据库，数据库连接外传，便于读取数据代码复用
		//Connection conn16 = ConnectSqlServer16.getConn();
		
		Connection conn22 = ConnectSql22.getConn();
		
		HashMap<String, String> promotionCompareType = BaseDao.getTbaleList("skyscope_etl.promotion_compare", "platform_id",
				"promotion_type_name", "promotion_type");
//		List<HashMap<String, String>> promotionMJ = BaseDaoSqlServer.getpromotionMJList("[skyscope].[dbo].[促销对照表]");
//		List<HashMap<String, String>> promotionMZ = BaseDaoSqlServer.getpromotionMJList("[skyscope].[dbo].[折对照表]");
		List<HashMap<String, String>> promotionMJ = BaseDao.getpromotionMJList("syntun_v2.促销对照表", conn138);
		List<HashMap<String, String>> promotionMZ = BaseDao.getpromotionMJList("syntun_v2.折对照表", conn138);
		
		List<HashMap<String, String>> promotionMJ22 = BaseDao.getpromotionMJList22("promotion.promotion_finish", conn22);
		promotionMJ.addAll(promotionMJ22);
		ConnectSql22.push(conn22);
		/*
		 * 先删除结果数据
		 */
//		// 删除价格小于1的结果
//		boolean isPriceLess = BaseDaoSqlServer.truncateTbaleAllData("[skyscope_temp].[dbo].[price_less_than_one_copy]", conn16);
//		// 删除下架产品结果
//		boolean isXiajiaTatal = BaseDaoSqlServer.truncateTbaleAllData("[skyscope_temp].[dbo].[XIAJIABIAO_TOTAL_COPY]", conn16);
//		// 删除结果表
//		boolean isPatrolList = BaseDaoSqlServer.truncateTbaleAllData("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", conn16);
//		// 删除天猫库存为0结果
//		boolean isTmallXiajia = BaseDao.truncateTbaleAllData("skyscope.tmall_stock_xiajia_copy", conn60);
//		// 删除价格结果
//		//boolean isPrice = BaseDao.truncateTbaleAllData("skyscope_etl.price_result_copy", conn60);
//		// 删除错误结果
//		boolean isErr = BaseDao.truncateTbaleAllData("skyscope_etl.err_result_copy", conn60);
//		// 删除未匹配促销结果
//		boolean isPromotion = BaseDao.truncateTbaleAllData("skyscope_etl.promotion_compare_info_copy", conn60);
//		// 删除未处理折扣结果
//		boolean isCopy = BaseDaoSqlServer.truncateTbaleAllData("[skyscope].[dbo].[折扣未处理_copy]", conn16);
//		
//		if(!isPriceLess || !isXiajiaTatal || !isPatrolList || !isTmallXiajia || !isErr || !isPromotion || !isCopy){
//			ConnectSql60.push(conn60);
//			ConnectSqlServer16.push(conn16);
//			return;
//		}
		//天猫特殊促销
		HashMap<String, List<HashMap<String, String>>> tmallPromotion = BaseDao.getAllTbaleMapMap(dateStr, conn138);
		
		HashMap<String, String> filterG = new HashMap<String, String>();
		// 读取用户组
		List<HashMap<String, String>> skyGroups = BaseDao.getAllTbaleList("skyscope_etl.sky_groups", filterG, conn60);

		// 生成主键的列集合
		List<String> keyListU = new ArrayList<String>();
		keyListU.add("group_id");
		// 读取用户条件
		HashMap<String, String> filterU = new HashMap<String, String>();
		// 读取用户列表
		HashMap<String, List<HashMap<String, String>>> skyUsers = BaseDao.getAllTbalePriceList("skyscope_etl.sky_users",
				filterU, keyListU, conn60);

		// 存储用户对应的product_id列表
//		HashMap<String, List<String>> skuUserList = BaseDaoSqlServer.getAllTbaleSkuUserList("[skyscope].[dbo].[SKYSCOPE_PRICE]",conn16);
		HashMap<String, List<String>> skuUserList = BaseDao.getAllTbaleSkuUserList("syntun_v2.SKYSCOPE_PRICE",conn138);
		// 生成主键的列集合
		List<String> keyListUP = new ArrayList<String>();
		keyListUP.add("C_ID");
		keyListUP.add("PRODUCT_ID");
		keyListUP.add("SHOP_ID");
		keyListUP.add("CITY_ID");
		keyListUP.add("type");
		keyListUP.add("SON_TYPE");
		// 读取标准表的用户和产品
		HashMap<String, String> filterUP = new HashMap<String, String>();
		// 1,表示价格巡检；；；2,表示市场监测
//		filterUP.put("SON_TYPE", "1");
//		filterUP.put("SON_TYPE", "2");
		// 读取用户和产品
//		HashMap<String, List<HashMap<String, String>>> userProduct = BaseDaoSqlServer
//				.getTbaleAllData16("[skyscope].[dbo].[SKYSCOPE_PRICE]", filterUP, keyListUP, conn16);
		HashMap<String, List<HashMap<String, String>>> userProduct = BaseDao
				.getTbaleAllData("syntun_v2.SKYSCOPE_PRICE", filterUP, keyListUP, conn138);

		// 生成组合主键的列集合
		List<String> keyList1 = new ArrayList<String>();
		keyList1.add("PLATFORM_ID");
		keyList1.add("SHOP_ID");
		keyList1.add("OPERATION_PRODUCT_ID");	// 单品地址
		keyList1.add("SKU_ID");					// 单品唯一标示
		keyList1.add("CITY_ID");
		keyList1.add("TYPE");
		keyList1.add("PRODUCT_ID");
		keyList1.add("SHOP_CODE");
		
		// 读取价格条件
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("get_date", dateStr);
		filter.put("hour", hour);
		filter.put("min", min);
		// 读取价格
//		HashMap<String, List<HashMap<String, String>>> priceData = BaseDaoSqlServer
//				.getTbaleAllData16("[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]", filter, keyList1, conn16);
		if(priceData.size() == 0){
//			priceData = BaseDaoSqlServer
//					.getTbaleAllData16("[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]", filter, keyList1, conn16);
			priceData = BaseDao.getTbaleAllData("syntun_v2.PRICE_PRODUCT_LIST_HALF", filter, keyList1, conn138);
		}
		
		// 读取促销
		List<String> keyList = new ArrayList<String>();
		keyList.add("platform_id");
		keyList.add("shop_id");
		keyList.add("operation_product_id");
		// 读取促销
//		HashMap<String, List<HashMap<String, String>>> promotionData =  BaseDaoSqlServer
//				.getTbaleAllData16("[skyscope].[dbo].[PRICE_PROMOTION_INFO_half_copy]", filter, keyList, conn16);
		if(promotionData.size() == 0){
//			promotionData = BaseDaoSqlServer
//					.getTbaleAllData16("[skyscope].[dbo].[PRICE_PROMOTION_INFO_half_copy]", filter, keyList, conn16);
			promotionData = BaseDao.getTbaleAllData("syntun_v2.PRICE_PROMOTION_INFO_half", filter, keyList, conn138);
		}
		
		// 生成主键的列集合
		List<String> keyListph = new ArrayList<String>();
		keyListph.add("product_id");
		keyListph.add("shop_id");
//		HashMap<String, String> philipsShopImportant = BaseDaoSqlServer
//				.getProductShopList("[skyscope].[dbo].[philips_shop_important]", keyListph, "SKU", conn16);
		HashMap<String, String> philipsShopImportant = BaseDao
				.getProductShopList("syntun_v2.philips_shop_important", keyListph, "SKU", conn138);
		// 雀巢折扣表
		List<String> keyListNestle = new ArrayList<String>();
		keyListNestle.add("product_id");
//		HashMap<String, String> nestleDiscount = BaseDaoSqlServer
//						.getProductShopList("[skyscope].[dbo].[雀巢折扣]", keyListNestle, "Discount", conn16);
		HashMap<String, String> nestleDiscount = BaseDao
				.getProductShopList("syntun_v2.雀巢折扣", keyListNestle, "Discount", conn138);

		// 生成主键的列集合
//		List<String> keyListxa = new ArrayList<String>();
//		keyListxa.add("product_id");
//		keyListxa.add("shop_id");
//		HashMap<String, String> xinanyiImportShop = BaseDaoSqlServer
//				.getProductShopList("[skyscope].[dbo].[xinanyi_import_shop_sku]", keyListxa, "sku_name", conn16);

//		// 生成组合主键的列集合
//		List<String> keyListD = new ArrayList<String>();
//		keyListD.add("operation_product_id");
//		keyListD.add("shop_code");
//		// 读取苏宁对照表
//		HashMap<String, List<HashMap<String, String>>> duizhaoData = BaseDao
//				.getAllDuizhaoList("skyscope.suning_duizhao", filter, keyListD, conn60);

		// 读取下架
		List<String> keyListXJ = new ArrayList<String>();
		keyListXJ.add("platform_id");
		keyListXJ.add("shop_id");
		keyListXJ.add("operation_product_id");
		keyListXJ.add("city_id");
		// 读取下架数据
		HashMap<String, List<HashMap<String, String>>> xiajiaData = BaseDao.getAllTbalePriceList("skyscope.xiajiabiao",filter, keyListXJ, conn60);
		// 读取下架数据53
		HashMap<String, List<HashMap<String, String>>> xiajiaData53 = BaseDao.getAllTbalePriceList("skyscope.xiajiabiao",filter, keyListXJ, conn53);
		/*
		 * 60.skyscope.promotion_shouji--（抽取时仅抽取sku_id中包含operation_product_id的数据）(抽取是过滤了)
		 *  该表采集的为手机端的价格，处理完的结果与该表做匹配，（条件：operation_product_id，shop_id，get_date，hour，min），匹配上的结果作比较，
		 *  如果计算完的苏宁价格比手机价高，则用手机价替换苏宁及算完之后的价格，反之，不做任何处理！！
		 */
		// 苏宁采集的手机端价格
		HashMap<String, List<HashMap<String, String>>> promotionShoujiData = BaseDao.getAllTbalePriceList("skyscope.promotion_shouji",filter, keyList, conn60);
		List<String> keyListSB = new ArrayList<String>();
		keyListSB.add("promotion_type_info");
		keyListSB.add("operation_product_id");
		keyListSB.add("shop_code");
		HashMap<String, String> promotionSuningBoxi = BaseDao.getPromotionSuningBoxi("skyscope.promotion_info_suningboxi",filter, keyList, conn60);
		// 读取促销
		List<String> keyListst = new ArrayList<String>();
		keyListst.add("platform_id");
		keyListst.add("shop_id");
		keyListst.add("operation_product_id");
		keyListst.add("sku_id");
		keyListst.add("city_id");
		// 读取库存数据
		HashMap<String, List<HashMap<String, String>>> stockData = BaseDao
				.getAllTbalePriceList("skyscope.shop_product_Stock_list", filter, keyListst, conn60);

		// 价格小于1的数据集合
		//HashMap<String, List<HashMap<String, String>>> lessData = new HashMap<String, List<HashMap<String, String>>>();

		// 生成主键的列集合
		List<String> keyListpr = new ArrayList<String>();
		//keyListpr.add("SHOP_ID");
		keyListpr.add("PLATFORM_ID");
		keyListpr.add("OPERATION_PRODUCT_ID");
		keyListpr.add("SKU_ID");
		//keyListpr.add("PRODUCT_ID");
		// 
//		HashMap<String, String> productPlatformList = BaseDaoSqlServer
//				.getProductTbaleList("[syntun_base].[dbo].[PRODUCT_PLATFORM_LIST]", keyListpr, "PRODUCT_ID", conn16);
		HashMap<String, String> productPlatformList = BaseDao
				.getProductTbaleList("syntun_v2.PRODUCT_PLATFORM_LIST", keyListpr, "PRODUCT_ID", conn138);
		
		// 读取product_list数据 ,此表中含有brand_id
//		HashMap<String, String> productList = BaseDaoSqlServer.getTbaleList2("[syntun_base].[dbo].[product_list]", "product_id", conn16);
		HashMap<String, String> productList = BaseDao.getTbaleList2("syntun_v2.PRODUCT_LIST", "PRODUCT_ID", conn138);
		// 读取shop_list数据
//		HashMap<String, String> shopList = BaseDaoSqlServer.getTbaleList("[syntun_base].[dbo].[shop_list]", "shop_id", "shop_name");
		HashMap<String, String> shopList = BaseDao.getTbaleList("syntun_v2.SHOP_LIST", "SHOP_ID", "SHOP_NAME", conn138);
		// 读取platform_list数据
		//HashMap<String, String> platformList = BaseDaoSqlServer.getTbaleList("[syntun_base].[dbo].[platform_list]", "platform_id", "platform_name");
		// 读取category_list数据
//		HashMap<String, String> categoryList = BaseDaoSqlServer.getTbaleList("[syntun_base].[dbo].[category_list]", "category_id", "category_name");
		HashMap<String, String> categoryList = BaseDao.getTbaleList("syntun_v2.CATEGORY_LIST", "CATEGORY_ID", "CATEGORY_NAME", conn138);
		// 读取brand_list数据
//		HashMap<String, String> brandList = BaseDaoSqlServer.getTbaleList("[syntun_base].[dbo].[brand_list]", "BRAND_ID", "BRAND_NAME");
		HashMap<String, String> brandList = BaseDao.getTbaleList("syntun_v2.BRAND_LIST", "BRAND_ID", "BRAND_NAME", conn138);
		
		// 需要全球购的用户
		HashMap<String, String> needHkUsers = BaseDao.getUsersMap("skyscope_etl.need_hk_users", conn60);
		
		// 不满足满减条件的，用最大减计算的用户
		//HashMap<String, String> maxJianUsers = BaseDao.getUsersMap("skyscope_etl.max_jian_users", conn60);
				
		// 读取结果表的字段列表
		//List<String> lessFiledList = BaseDaoSqlServer.getField16("[skyscope_temp].[dbo].[price_less_than_one_copy]", "192.168.0.16", "skyscope_temp");
		List<String> lessFiledList = BaseDao.getField("syntun_v2.PRICE_LESS_THAN_ONE", conn138);
		// 读取结果表的字段列表
		//List<String> xiajiaFiledList = BaseDaoSqlServer.getField16("[skyscope_temp].[dbo].[xiajiabiao_total_copy]", "192.168.0.16", "skyscope_temp");
		List<String> xiajiaFiledList = BaseDao.getField("syntun_v2.XIAJIABIAO_TOTAL", conn138);
		// 读取天猫下架表的字段列表
		List<String> tmallFiledList = BaseDao.getField("skyscope.tmall_stock_xiajia_copy", conn60);
		tmallFiledList.remove("id");
		
		List<String> priceFiledList = BaseDao.getField("skyscope_online.patrol_inspection_price_list_copy", conn138);
		priceFiledList.remove("id");
		List<String> nestleFiledList = BaseDao.getFieldLowerCase("skyscope_online.PATROL_INSPECTION_LIST_HALF_NESTLE", conn138);
		nestleFiledList.remove("id");
		
		HashMap<String, String> filterS = new HashMap<String, String>();
		// 获取需要归类的促销类型
		List<HashMap<String, String>> productState = BaseDao.getAllTbaleList("skyscope_etl.product_state", filterS, conn60);
		
		HashMap<String, String> platformMap = BaseDao.getListPlat("skyscope_etl.platform_list", "platform_id", "platform_name", conn60);
		// 读取结果表的字段列表
		List<String> fieldList = BaseDaoSqlServer.getField16("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", "192.168.0.16", "skyscope_temp");
		// 读取结果表的字段列表
		List<String> fieldListNestle = BaseDaoSqlServer.getField16("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_NESTLE_COPY]", "192.168.0.16", "skyscope_temp");
		// 未匹配促销表的字段列表
		List<String> promotionFiledList = BaseDao.getField("skyscope_etl.promotion_compare_info", conn60);
		
		ConnectSql138.push(conn138);
		ConnectSql60.push(conn60);
		ConnectSql53.push(conn53);
//		ConnectSqlServer16.push(conn16);
		
//		System.out.println("===价格巡检数据条数:"+ priceData.size() + "===");
//		System.out.println("===促销巡检数据条数:"+ promotionData.size() + "===");
		
		//53下架和60下架进行合并
		for (String k : xiajiaData53.keySet()) {
			if (!xiajiaData.containsKey(k)) {
				xiajiaData.put(k, xiajiaData53.get(k));
			}
		}
		
		// 读取下架数据
		HashMap<String, List<HashMap<String, String>>> xiajiaDataYH = new HashMap<String, List<HashMap<String, String>>>();
		// 复制京东（1）数据作为一号店（7）的下架数据
		for (String k : xiajiaData.keySet()) {

			if (k.split("\001")[0].equals("1")) {
				String key = "7" + "\001" + k.split("\001")[1];
				// 产品价格列表
				List<HashMap<String, String>> xiajiaD = xiajiaData.get(k);
				List<HashMap<String, String>> yihaoD = new ArrayList<HashMap<String, String>>();
				for (HashMap<String, String> xiajiaMap : xiajiaD) {
					HashMap<String, String> yihaoM = xiajiaMap;
					yihaoM.put("platform_id", "7");
					yihaoD.add(yihaoM);
				}
				xiajiaDataYH.put(key, yihaoD);
			}
		}
		
		if(xiajiaDataYH != null && xiajiaDataYH.size() != 0){
			xiajiaData.putAll(xiajiaDataYH);
		}

		for (String ks : stockData.keySet()) {
			// 产品库存
			List<HashMap<String, String>> stock = stockData.get(ks);
			// 库存等于0key
			HashMap<String, String> stockKeyMap = new HashMap<String, String>();
			if (stock.size() == 1) {
				for (HashMap<String, String> stockMap : stock) {
					//List<HashMap<String, String>> liS = new ArrayList<HashMap<String, String>>();
					if (!stockMap.get("sku_id").equals("0") && stockMap.get("Stock").equals("0") && stockMap.get("platform_id").equals("5")) {
						// stockKeyMap.put("id", "");
						stockKeyMap.put("operation_product_id", stockMap.get("operation_product_id"));
						stockKeyMap.put("error_connect", "天猫平台库存为0的");
						stockKeyMap.put("platform_id", stockMap.get("platform_id"));
						stockKeyMap.put("get_data_time", getDate);
						stockKeyMap.put("hour", stockMap.get("hour"));
						stockKeyMap.put("min", stockMap.get("min"));
						stockKeyMap.put("get_date", stockMap.get("get_date"));
						stockKeyMap.put("shop_id", stockMap.get("shop_id"));
						stockKeyMap.put("product_id", stockMap.get("product_id"));
						stockKeyMap.put("shop_code", stockMap.get("shop_code"));
						stockKeyMap.put("city_id",
								stockMap.get("city_id").equals("-1") ? "2" : stockMap.get("city_id"));

						//liS.add(stockKeyMap);
						//lessData.put(promotionKey, liS);

						String sql = ConvertSql.getSql("skyscope.tmall_stock_xiajia_copy", tmallFiledList, stockKeyMap);
						//System.out.println(tmallStockXiajia.size() + "___" + sql);
						tmallStockXiajia.add(sql);

						break;
					}
				}
			}else if (stock.size() > 1) {
				for (HashMap<String, String> stockMap : stock) {
					//List<HashMap<String, String>> liS = new ArrayList<HashMap<String, String>>();
					if (stockMap.get("Stock").equals("0") && stockMap.get("platform_id").equals("5")
							&& !stockMap.get("sku_id").equals("0")) {
						// stockKeyMap.put("id", "");
						stockKeyMap.put("operation_product_id", stockMap.get("operation_product_id"));
						stockKeyMap.put("error_connect", "天猫平台库存为0的");
						stockKeyMap.put("platform_id", stockMap.get("platform_id"));
						stockKeyMap.put("get_data_time", getDate);
						stockKeyMap.put("hour", stockMap.get("hour"));
						stockKeyMap.put("min", stockMap.get("min"));
						stockKeyMap.put("get_date", stockMap.get("get_date"));
						stockKeyMap.put("shop_id", stockMap.get("shop_id"));
						stockKeyMap.put("product_id", stockMap.get("product_id"));
						stockKeyMap.put("shop_code", stockMap.get("shop_code"));
						stockKeyMap.put("city_id",
								stockMap.get("city_id").equals("-1") ? "2" : stockMap.get("city_id"));

						//liS.add(stockKeyMap);
						//lessData.put(promotionKey, liS);

						String sql = ConvertSql.getSql("skyscope.tmall_stock_xiajia_copy", tmallFiledList, stockKeyMap);
						//System.out.println(tmallStockXiajia.size() + "___" + sql);
						tmallStockXiajia.add(sql);

						break;
					}
				}
			}
		}
		
		String[] orderList = new String[5];
		String groupId = null;
		
		List<String> userLists = new ArrayList<>();
		for (HashMap<String, String> groupMap : skyGroups) {
			
			orderList = groupMap.get("inspect").split("-->");
			groupId = groupMap.get("id");
			// 用户列表
			List<HashMap<String, String>> userList = skyUsers.get(groupId);
			if (null == userList || userList.size() == 0) {
				continue;
			}
			
			for (HashMap<String, String> user : userList) {
				List<String> nowRunSkuList = new ArrayList<>();
				
				String userId = user.get("user_id");
				if (skuUserList.containsKey(user.get("user_id"))) {
					nowRunSkuList.addAll(skuUserList.get(user.get("user_id")));
				}
				
				if (nowRunSkuList != null && nowRunSkuList.size() > 0) {
					userLists.add(userId);
				}else{
					continue;
				}
				
				//List<HashMap<String, String>> boxiData = new ArrayList<HashMap<String, String>>();
				HashMap<String, List<HashMap<String, String>>> boxiData1 = new HashMap<String, List<HashMap<String, String>>>();
				HashMap<String, List<HashMap<String, String>>> boxiData2 = new HashMap<String, List<HashMap<String, String>>>();
				HashMap<String, List<HashMap<String, String>>> boxiData3 = new HashMap<String, List<HashMap<String, String>>>();
				
				// 组合放入流程，。同一主键产品循环处理
				for (String k : priceData.keySet()) {
					String promotionKey = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2];
					String xiajiaKey = k.split("\001")[0] + "\001" + k.split("\001")[1] + "\001" + k.split("\001")[2] + "\001" + k.split("\001")[4];
					// 产品价格列表
					List<HashMap<String, String>> priceD = priceData.get(k);
					// 价格小于1key
					HashMap<String, String> lessKeyMap = new HashMap<String, String>();

					// String price = "";
					// 产品ID
					String productId = k.split("\001")[6];
					String platformId = k.split("\001")[0];
					String shopId = k.split("\001")[1];
					String cityId = k.split("\001")[4];
					String type = k.split("\001")[5];
					
					String productIdBase = productId;
					//如果不存在基础表则抛出
					if(platformId.equals("4") || platformId.equals("5") || platformId.equals("44") || platformId.equals("47")){
						if(!productPlatformList.containsKey(k.split("\001")[0]+"\001"+k.split("\001")[2]+"\001"+k.split("\001")[3])
								&& !productPlatformList.containsKey(k.split("\001")[0]+"\001"+k.split("\001")[2]+"\001"+"0")){
							continue;
						}else{
							if(!k.split("\001")[3].equals("0") && productPlatformList.containsKey(k.split("\001")[0]+"\001"+k.split("\001")[2]+"\001"+k.split("\001")[3])){
								productIdBase = productPlatformList.get(k.split("\001")[0]+"\001"+k.split("\001")[2]+"\001"+k.split("\001")[3]);
							}
						}
					}
					
					boolean isType1 = false;
					boolean isType2 = false;
					// 产品不在标准价格表
					if(userProduct.containsKey(userId+"\001"+productId+"\001"+shopId+"\001"+cityId+"\001"+type+"\001"+"1")){
						isType1 = true;
					}
					if(userProduct.containsKey(userId+"\001"+productId+"\001"+shopId+"\001"+cityId+"\001"+type+"\001"+"2")){
						isType2 = true;
					}
					if(!isType1 && !isType2){
						continue;
					}
					
					if(productList.get(productId) == null && productList.get(productIdBase) == null){
						continue;
					}
					if(productList.get(productId) == null && productList.get(productIdBase) != null){
						productId = productIdBase;
					}
					
					if (platformId.equals("5") && stockData.get(k.split("\001")[0]+"\001"+k.split("\001")[1]+"\001"+k.split("\001")[2]+
							"\001"+k.split("\001")[3]+"\001"+k.split("\001")[4]) != null && stockData.get(k.split("\001")[0]+"\001"+k.split("\001")[1]+"\001"+k.split("\001")[2]+
							"\001"+k.split("\001")[3]+"\001"+k.split("\001")[4]).size() > 0){
						List<HashMap<String, String>> stockList = stockData.get(k.split("\001")[0]+"\001"+k.split("\001")[1]+"\001"+k.split("\001")[2]+
								"\001"+k.split("\001")[3]+"\001"+k.split("\001")[4]);
						String Stocks = "";
						
						int i = 0;
						for (HashMap<String, String> stockMap : stockList){
							if (i == 0){
								Stocks = stockMap.get("Stock");
							}
							if (i > 0 && stockMap.get("Stock").equals("0")){
								Stocks = stockMap.get("Stock");
							}
							i++;
						}
						
						if (!k.split("\001")[3].equals("0") && Stocks.equals("0")) {
							continue;
						}
					}
					// 新安怡客户关联16上skyscope. PHILIPS_SHOP_IMPORTANT，条件：product_id,shop_id.（只需要）：关联上的输出
					if(userId.equals("100794")){
						if(!philipsShopImportant.containsKey(productId+"\001"+shopId)){
							//break;
							continue;
						}
					}
					// 飞利浦客户关联16上skyscope. PHILIPS_SHOP_IMPORTANT，条件：product_id,shop_id；（只需要）：关联上的输出
					if(userId.equals("419")){
						if(!philipsShopImportant.containsKey(productId+"\001"+shopId)){
							//break;//结束整个循环体
							continue;//结束单次循环体
						}
					}
					
//					// 3M用户和飞利浦竞品用户和同仁堂用户，不需要手机端的，过滤掉
//					if(userId.equals("100175") || userId.equals("100777") || userId.equals("100282")){
//						if(type.equals("2")){
//							continue;
//						}
//					}
//					
//					// 新安怡用户和飞利浦用户，不需要PC端的，过滤掉
//					if(userId.equals("100794") || userId.equals("419")){
//						if(type.equals("1")){
//							continue;
//						}
//					}
					
					if (!nowRunSkuList.contains(productId)) {
						continue;
					}
					
					boolean isReturn = false;
					for (HashMap<String, String> priceMap : priceD) {
						// 是否需要全球购的，不需要全球购的则不输出
						if (!needHkUsers.containsKey(userId)){
							if (priceMap.get("c_id") !=null && priceMap.get("c_id").equals("hk")){
								isReturn = true;
							}
						}
						
						if (priceMap.get("promotion_price") != null && priceMap.get("promotion_price").indexOf("-") != -1){
							priceMap.put("promotion_price", priceMap.get("product_price"));
						}

						//List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
						// 处理亚马逊的价格 2,198.00
						if (priceMap.get("product_price") != null && priceMap.get("product_price").indexOf(",") != -1 
								&& priceMap.get("platform_id") != null && priceMap.get("platform_id").equals("10")) {
							priceMap.put("product_price", priceMap.get("product_price").replace(",", ""));

						}

						if (priceMap.get("product_price")!=null && priceMap.get("product_price").equals("")
								&& !priceMap.get("product_price").equals("null")) {
							continue;

						}
						//如果价格不是数字，则此记录不需要；
						try {
							if (priceMap.get("product_price")!=null 
									&& !priceMap.get("product_price").equals("null")
									&& priceMap.get("product_price").indexOf("-") == -1){
								Double.parseDouble(priceMap.get("product_price"));
							} 
						} catch (Exception e) {
							continue;
						}
						
						if (priceMap.get("product_price")!=null 
								&& !priceMap.get("product_price").equals("null")
								&& priceMap.get("product_price").indexOf("-") == -1
								&& Double.parseDouble(priceMap.get("product_price")) <= 1
								//&& (priceMap.get("paltform_id") != null && priceMap.get("paltform_id").equals("3"))
								) {
							// lessKeyMap.put("id", priceMap.get("id"));
							lessKeyMap.put("operation_product_id", priceMap.get("operation_product_id"));
							lessKeyMap.put("error_connect", "价格小于1");
							lessKeyMap.put("platform_id", priceMap.get("platform_id"));
							lessKeyMap.put("get_data_time", getDate);
							lessKeyMap.put("hour", priceMap.get("hour"));
							lessKeyMap.put("min", priceMap.get("min"));
							lessKeyMap.put("get_date", priceMap.get("get_date"));
							lessKeyMap.put("shop_id", priceMap.get("shop_id"));
							lessKeyMap.put("product_id", priceMap.get("product_id"));
							lessKeyMap.put("shop_code", priceMap.get("shop_code"));
							lessKeyMap.put("city_id", priceMap.get("city_id"));

							//li.add(lessKeyMap);
							//lessData.put(promotionKey, li);
							//String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PRICE_LESS_THAN_ONE_COPY]", lessFiledList, lessKeyMap);
							String mysql = ConvertSql.getSql("syntun_v2.PRICE_LESS_THAN_ONE", lessFiledList, lessKeyMap);
							priceLess.add(mysql);

							isReturn = true;
						}

						if (priceMap.get("product_price")!=null && !priceMap.get("product_price").equals("")
								&& (priceMap.get("promotion_price")==null 
									|| priceMap.get("promotion_price").equals("")
									|| priceMap.get("promotion_price").equals("-1") 
									|| priceMap.get("promotion_price").equals("0.00")
									|| priceMap.get("promotion_price").equals("null"))) {
							priceMap.put("promotion_price", priceMap.get("product_price"));
						}
						
						if(priceMap.get("promotion_price") == null
								||priceMap.get("promotion_price").equals("-1")
								||priceMap.get("promotion_price").equals("-1.0")
								||priceMap.get("promotion_price").equals("-1.00")
								||priceMap.get("promotion_price").equals("null")
								||priceMap.get("promotion_price").indexOf("?") != -1
								||priceMap.get("promotion_price").indexOf("？") != -1 ){
							priceMap.put("promotion_price", "-1");
						}
						
						// 产品下架
						List<HashMap<String, String>> xiajai = null;
						if (xiajiaData.containsKey(xiajiaKey)) {
							xiajai = xiajiaData.get(xiajiaKey);
						} else {
							xiajai = new ArrayList<HashMap<String, String>>();
						}
						if (lessKeyMap.size() != 0) {
							xiajai.add(lessKeyMap);
						}

						if (xiajai != null && xiajai.size() != 0) {
							boolean isBreak = false;
							for (HashMap<String, String> xiajaiMap : xiajai) {
								for (Map<String, String> state : productState) {
									//if (!state.get("error_connect").equals("")) {
									if (state.get("platform_id").equals("0")) {
										if (xiajaiMap.get("error_connect").equals(state.get("error_connect"))) {
											priceMap.put("states", state.get("state_code")); // 无货
											isBreak = true;
											break;
										}
									} else {
										if (xiajaiMap.get("error_connect").equals(state.get("error_connect"))
												&& xiajaiMap.get("platform_id").equals(state.get("platform_id"))) {
											priceMap.put("states", state.get("state_code")); // 无货
											isBreak = true;
											break;
										}
									}
									//} else {
										//priceMap.put("states", state.get("state_code")); // 下架
									//}
								}
							}
							if(!isBreak){
								priceMap.put("states", "1"); // 下架
							}
						} else {
							priceMap.put("states", "99"); // 其他
						}
						
						if(priceMap.get("promotion_price").equals("-1")){
							isReturn = true;
							//break;
						}else{
							isReturn = false;
						}
						
						//过滤下架数据
						if (priceMap.get("states").equals("1")) {
							isReturn = true;
							//break;
						}else{
							isReturn = false;
						}
						//波西客户，无货也过滤
//						if (userId.equals("244") && priceMap.get("states").equals("2")) {
//							isReturn = true;
//						}
					}
					
					if (isReturn) {
						continue;
					}
					
					HashMap<String, String> keyMap = new HashMap<String, String>();
					
					keyMap.put("product_id", productId);
					keyMap.put("operation_product_id", k.split("\001")[2]);
					keyMap.put("platform_id", k.split("\001")[0]);
					keyMap.put("shop_id", k.split("\001")[1]);
					keyMap.put("sku_id", k.split("\001")[3].equals("def") ? "0" : k.split("\001")[3]);
					keyMap.put("get_date", getDate);

					//String shopCode = priceD.get(0).get("shop_code");
					List<HashMap<String, String>> promotion = promotionData.get(promotionKey);
					if (promotion != null && promotion.size() != 0) {
						priceD.addAll(promotion);
					}
					List<HashMap<String, String>> promotionShouji = promotionShoujiData.get(promotionKey);
					try {
						
						PricePromotionInfo pm = DataSelection.reduce(priceD, true, keyMap, orderList, promotionFiledList, groupId, userId, tmallPromotion, dateStr,
								promotionCompareType, promotionMJ, promotionMZ, promotionShouji, promotionSuningBoxi);
						//System.out.println("========" + pm);
						if (pm == null) {
							// System.out.println("no result-----" + k.split("\001")[2] + "___" + k.split("\001")[0]);
							continue;
						}
						
						String standardPrice = "0";
						if(userProduct.get(userId+"\001"+productId+"\001"+shopId+"\001"+cityId+"\001"+type+"\001"+"1")!= null){
							standardPrice = userProduct.get(userId+"\001"+productId+"\001"+shopId+"\001"+cityId+"\001"+type+"\001"+"1")
									.get(0).get("product_price");
						}
						
						String stock = "0";
						if(stockData.get(keyMap.get("platform_id")+"\001"+shopId+"\001"+keyMap.get("operation_product_id")+"\001"+keyMap.get("sku_id")+"\001"+cityId)!=null){
							List<HashMap<String, String>> stockList = stockData.get(keyMap.get("platform_id")+"\001"+shopId+"\001"+keyMap.get("operation_product_id")+"\001"+keyMap.get("sku_id")+"\001"+cityId);
							if(stockList.size() > 0){
								int i = 0;
								for (HashMap<String, String> stockMap : stockList){
									if (i == 0){
										stock = stockMap.get("Stock");
									}
									if (i > 0 && stockMap.get("Stock").equals("0")){
										stock = stockMap.get("Stock");
									}
									i++;
								}
							}
						}
						//System.out.println(userProduct.get(userId + "\001" + productId));
						String skuId = keyMap.get("sku_id");
						if(keyMap.get("sku_id").equals("def")||keyMap.get("sku_id")==null||keyMap.get("sku_id").equals("")||keyMap.get("sku_id").equals("null")){
							skuId = "0";
						}
						String shopName = shopList.get(shopId) == null?"":shopList.get(shopId);
						shopName = shopName.replace("'", "'+CHAR(39)+'");
						// 价格巡检
						if(isType1){
							HashMap<String, String> fieldMap = new HashMap<String, String>();
							fieldMap.put("product_id", productId);
							fieldMap.put("product_name", productList.get(productId).split("\002")[0]);
							fieldMap.put("platform_id", keyMap.get("platform_id"));
							fieldMap.put("platform_name", 
									platformMap.get(keyMap.get("platform_id")) == null? "":platformMap.get(keyMap.get("platform_id")));
							fieldMap.put("shop_id", keyMap.get("shop_id"));
							fieldMap.put("shop_name", shopName);
							fieldMap.put("sku_id", skuId);
							fieldMap.put("son_type", "1");
							fieldMap.put("url_id", keyMap.get("operation_product_id"));
							fieldMap.put("uid", userId);
							fieldMap.put("standard_price", standardPrice);
							String violationPrice = pm.getPromotionPrice();
							if (violationPrice == null) {
								violationPrice = pm.getLingshoujia();
							}
							fieldMap.put("product_price", pm.getLingshoujia());
							fieldMap.put("violation_price", violationPrice);
							
							fieldMap.put("violation", 
									Double.parseDouble(violationPrice) < Double.parseDouble(standardPrice)
									?"1":"0");
							fieldMap.put("violation_type_id", "1");
							fieldMap.put("create_at", getDate);
							fieldMap.put("category_id", productList.get(productId).split("\002")[2]);
							fieldMap.put("category_name", categoryList.get(productList.get(productId).split("\002")[2]));
							fieldMap.put("brand_id", productList.get(productId).split("\002")[1]);
							fieldMap.put("brand_name", brandList.get(productList.get(productId).split("\002")[1]));
							fieldMap.put("stock", stock);
							fieldMap.put("city_id", cityId);
							fieldMap.put("type", type);
							
							//String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", fieldList, fieldMap);
							//etlResult.add(sql);
							
							if(userId.equals("244")){
								String boxiKey = keyMap.get("operation_product_id") + "\001" +
										keyMap.get("platform_id") + "\001" +
										skuId + "\001" +
										keyMap.get("shop_id") + "\001" + "1";
								if(cityId.equals("2")){
									List<HashMap<String, String>> li = null;
									if (boxiData1.containsKey(boxiKey)) {
										li = boxiData1.get(boxiKey);
									} else {
										li = new ArrayList<HashMap<String, String>>();
									}
									li.add(fieldMap);
									boxiData1.put(boxiKey, li);
								}
								if(cityId.equals("10")){
									List<HashMap<String, String>> li = null;
									if (boxiData2.containsKey(boxiKey)) {
										li = boxiData2.get(boxiKey);
									} else {
										li = new ArrayList<HashMap<String, String>>();
									}
									li.add(fieldMap);
									boxiData2.put(boxiKey, li);
								}
								if(cityId.equals("232")){
									List<HashMap<String, String>> li = null;
									if (boxiData3.containsKey(boxiKey)) {
										li = boxiData3.get(boxiKey);
									} else {
										li = new ArrayList<HashMap<String, String>>();
									}
									li.add(fieldMap);
									boxiData3.put(boxiKey, li);
								}
							}else{
								String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", fieldList, fieldMap);
								etlResult.add(sql);

								fieldMap.put("create_at", millionSeconds+"");
								String mysql = ConvertSql.getSql("skyscope_online.patrol_inspection_price_list_copy", priceFiledList, fieldMap);
								mysqlEtlResult.add(mysql);
							}
							// 伊利
							if(userId.equals("100737")){
								fieldMap.put("create_at", getDate);
								String sqlyili = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_yili_copy]", fieldList, fieldMap);
								// System.out.println(etlResult.size() + "___" + sql);
								etlResultYili.add(sqlyili);
							}
							// 雀巢
							if(userId.equals("100981")){
								fieldMap.put("create_at", getDate.replace('-', '.'));
								fieldMap.put("min_standard_price", 
										Double.parseDouble(standardPrice)*Double.parseDouble(nestleDiscount.get(productId))+"");
								fieldMap.put("min_violation", 
										Double.parseDouble(violationPrice) < Double.parseDouble(standardPrice)*Double.parseDouble(nestleDiscount.get(productId))
										?"1":"0");
								fieldMap.put("city_name", "北京");
								fieldMap.put("html", "https://item.jd.com/"+keyMap.get("operation_product_id")+".html");
								
								String sqlNestle = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_NESTLE_COPY]", fieldListNestle, fieldMap);
								// System.out.println(etlResult.size() + "___" + sql);
								etlResultNestle.add(sqlNestle);

								String mysqlNestle = ConvertSql.getSql("skyscope_online.PATROL_INSPECTION_LIST_HALF_NESTLE", nestleFiledList, fieldMap);
								// System.out.println(etlResult.size() + "___" + sql);
								mysqlEtlResultNestle.add(mysqlNestle);
							}
						}
						// 市场监测
						if(isType2){
							HashMap<String, String> fieldMap = new HashMap<String, String>();
							fieldMap.put("product_id", productId);
							fieldMap.put("product_name", productList.get(productId).split("\002")[0]);
							fieldMap.put("platform_id", keyMap.get("platform_id"));
							fieldMap.put("platform_name", 
									platformMap.get(keyMap.get("platform_id")) == null? "":platformMap.get(keyMap.get("platform_id")));
							fieldMap.put("shop_id", keyMap.get("shop_id"));
							fieldMap.put("shop_name", shopName);
							fieldMap.put("sku_id", skuId);
							fieldMap.put("son_type", "2");
							fieldMap.put("url_id", keyMap.get("operation_product_id"));
							fieldMap.put("uid", userId);
							fieldMap.put("standard_price", "0.00");
							String violationPrice = pm.getPromotionPrice();
							if (violationPrice == null) {
								violationPrice = pm.getLingshoujia();
							}
							fieldMap.put("product_price", pm.getLingshoujia());
							fieldMap.put("violation_price", violationPrice);
							fieldMap.put("violation","0");
							fieldMap.put("violation_type_id", "1");
							fieldMap.put("create_at", getDate);
							fieldMap.put("category_id", productList.get(productId).split("\002")[2]);
							fieldMap.put("category_name", categoryList.get(productList.get(productId).split("\002")[2]));
							fieldMap.put("brand_id", productList.get(productId).split("\002")[1]);
							fieldMap.put("brand_name", brandList.get(productList.get(productId).split("\002")[1]));
							fieldMap.put("stock", stock);
							fieldMap.put("city_id", cityId);
							fieldMap.put("type", type);
							
							//String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", fieldList, fieldMap);
							//etlResult.add(sql);
							
							if(userId.equals("244")){
								String boxiKey = keyMap.get("operation_product_id") + "\001" +
										keyMap.get("platform_id") + "\001" +
										skuId + "\001" +
										keyMap.get("shop_id") + "\001" + "2";
								if(cityId.equals("2")){
									List<HashMap<String, String>> li = null;
									if (boxiData1.containsKey(boxiKey)) {
										li = boxiData1.get(boxiKey);
									} else {
										li = new ArrayList<HashMap<String, String>>();
									}
									li.add(fieldMap);
									boxiData1.put(boxiKey, li);
								}
								if(cityId.equals("10")){
									List<HashMap<String, String>> li = null;
									if (boxiData2.containsKey(boxiKey)) {
										li = boxiData2.get(boxiKey);
									} else {
										li = new ArrayList<HashMap<String, String>>();
									}
									li.add(fieldMap);
									boxiData2.put(boxiKey, li);
								}
								if(cityId.equals("232")){
									List<HashMap<String, String>> li = null;
									if (boxiData3.containsKey(boxiKey)) {
										li = boxiData3.get(boxiKey);
									} else {
										li = new ArrayList<HashMap<String, String>>();
									}
									li.add(fieldMap);
									boxiData3.put(boxiKey, li);
								}
							}else{
								String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", fieldList, fieldMap);
								etlResult.add(sql);

								fieldMap.put("create_at", millionSeconds+"");
								String mysql = ConvertSql.getSql("skyscope_online.patrol_inspection_price_list_copy", priceFiledList, fieldMap);
								mysqlEtlResult.add(mysql);
							}
							// 伊利
							if(userId.equals("100737")){
								fieldMap.put("create_at", getDate);
								String sqlyili = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_yili_copy]", fieldList, fieldMap);
								// System.out.println(etlResult.size() + "___" + sql);
								etlResultYili.add(sqlyili);
							}
							// 雀巢
							if(userId.equals("100981")){
								fieldMap.put("create_at", getDate.replace('-', '.'));
								fieldMap.put("min_standard_price", 
										Double.parseDouble(standardPrice)*Double.parseDouble(nestleDiscount.get(productId))+"");
								fieldMap.put("min_violation", 
										Double.parseDouble(violationPrice) < Double.parseDouble(standardPrice)*Double.parseDouble(nestleDiscount.get(productId))
										?"1":"0");
								fieldMap.put("city_name", "北京");
								fieldMap.put("html", "https://item.jd.com/"+keyMap.get("operation_product_id")+".html");
								
								String sqlNestle = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_NESTLE_COPY]", fieldListNestle, fieldMap);
								// System.out.println(etlResult.size() + "___" + sql);
								etlResultNestle.add(sqlNestle);
								
								String mysqlNestle = ConvertSql.getSql("skyscope_online.PATROL_INSPECTION_LIST_HALF_NESTLE", nestleFiledList, fieldMap);
								// System.out.println(etlResult.size() + "___" + sql);
								mysqlEtlResultNestle.add(mysqlNestle);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				// 博西客户循环遍历
				for (String k : boxiData2.keySet()) {
					if(!boxiData1.containsKey(k)){
						List<HashMap<String, String>> boxiD2 = boxiData2.get(k);
						boxiData1.put(k, boxiD2);
					}
				}
				for (String k : boxiData3.keySet()) {
					if(!boxiData1.containsKey(k)){
						List<HashMap<String, String>> boxiD3 = boxiData3.get(k);
						boxiData1.put(k, boxiD3);
					}
				}
				for (String k : boxiData1.keySet()) {
					List<HashMap<String, String>> boxiD1 = boxiData1.get(k);
					for (HashMap<String, String> boxiMap1 : boxiD1) {
						String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[PATROL_INSPECTION_LIST_HALF_COPY]", fieldList, boxiMap1);
						etlResult.add(sql);

						boxiMap1.put("create_at", millionSeconds+"");
						String mysql = ConvertSql.getSql("skyscope_online.patrol_inspection_price_list_copy", priceFiledList, boxiMap1);
						mysqlEtlResult.add(mysql);
					}
				}
			}
		}
		// xiajai_tatal中补充数据
		for (String k : xiajiaData.keySet()) {
			List<HashMap<String, String>> xiajiaD = xiajiaData.get(k);
			if (xiajiaD != null && xiajiaD.size() != 0) {
				for (HashMap<String, String> xiajiaMap : xiajiaD) {
					//String sql = ConvertSql.getSqlServer("[skyscope_temp].[dbo].[XIAJIABIAO_TOTAL_COPY]", xiajiaFiledList, xiajiaMap);
					String mysql = ConvertSql.getSql("syntun_v2.XIAJIABIAO_TOTAL", xiajiaFiledList, xiajiaMap);
					xiajiaTatal.add(mysql);
					break;
				}
			}
		}
		
//		for(String price : etlResult) {
//			System.out.println(price);
//		}
		
		System.out.println("---计算时间：  ===" + getDate);
		System.out.println("---价格小于1数据条数： "+ priceLess.size() + " ===");
		System.out.println("---下架归类数据条数： "+ xiajiaTatal.size() + " ===");
		System.out.println("---天猫下架数据条数： "+ tmallStockXiajia.size() + " ===");
		System.out.println("---执行结果数据条数： "+ etlResult.size() + " ===");
		System.out.println("---执行结果（伊利）数据条数： "+ etlResultYili.size() + " ===");
		System.out.println("---执行结果（雀巢）数据条数： "+ etlResultNestle.size() + " ===");
		System.out.println("---未处理折扣数据条数： "+ ZKsql.size() + " ===");
		System.out.println("---未匹配的促销数据条数： "+ promotionCompareInfo.size() + " ===");
		System.out.println("---错误数据条数： "+ errResult.size() + " ===");
		System.out.println("---mysql执行结果数据条数： "+ mysqlEtlResult.size() + " ===");
		System.out.println("---mysql执行结果（雀巢）数据条数： "+ mysqlEtlResultNestle.size() + " ===");
		System.out.println("---满减匹配结果，数据条数： "+ MJsql.size() + " ===");
		System.out.println("---满减未匹配结果，数据条数： "+ MJNsql.size() + " ===");
		System.out.println("---满折匹配结果，数据条数： "+ MZsql.size() + " ===");
		System.out.println("---满折未匹配结果，数据条数： "+ MZNsql.size() + " ===");

		Thread t1 = new Thread(new InsertData138(priceLess));
		t1.start();
		Thread t2 = new Thread(new InsertData138(xiajiaTatal));
		t2.start();
		Thread t3 = new Thread(new InsertData60(tmallStockXiajia));
		t3.start();
		Thread t4 = new Thread(new InsertDataSqlServer(etlResult));
		t4.start();
		Thread t5 = new Thread(new InsertData60(errResult));
		t5.start();
		Thread t6 = new Thread(new InsertData60(promotionCompareInfo));
		t6.start();
		Thread t7 = new Thread(new InsertDataSqlServer(ZKsql));
		t7.start();
		Thread t8 = new Thread(new InsertDataSqlServer(etlResultYili));
		t8.start();
		Thread t9 = new Thread(new InsertDataSqlServer(etlResultNestle));
		t9.start();
		Thread t10 = new Thread(new InsertData138(mysqlEtlResult));
		t10.start();
		Thread t11 = new Thread(new InsertData138(mysqlEtlResultNestle));
		t11.start();
		Thread t12 = new Thread(new InsertData138(MJsql));
		t12.start();
		Thread t13 = new Thread(new InsertData138(MJNsql));
		t13.start();
		Thread t14 = new Thread(new InsertData138(MZsql));
		t14.start();
		Thread t15 = new Thread(new InsertData138(MZNsql));
		t15.start();
		 
		boolean isAlice = true;
			
		while(isAlice){
			if(!t1.isAlive() && !t2.isAlive() && !t3.isAlive() && !t4.isAlive() 
					&& !t5.isAlive() && !t6.isAlive() && !t7.isAlive() && !t8.isAlive()
					&& !t9.isAlive() && !t10.isAlive() && !t11.isAlive()
					&& !t12.isAlive() && !t13.isAlive() && !t14.isAlive() && !t15.isAlive() ){
				isAlice = false;
				String userNames = "";
				for(String k : skyUsers.keySet()){
					for(HashMap<String, String> skyUsersmap:skyUsers.get(k)){
						if(userLists.contains(skyUsersmap.get("user_id"))){
							userNames = userNames + skyUsersmap.get("user_name") + "、";
						}
					}
				}
				Date day2 = new Date(); 
				try {
					String mins = min.equals("15")?"00":"30";
					//SyntunEmail syntunEmail = new SyntunEmail();
					//String[] emails = {"xiaoran.dai@syntun.com", "liping.ren@syntun.com"};
					//String[] emails = {"liping.ren@syntun.com"};
					String text = "巡检流程执行完成，请注意核查！ \n " + 
							"执行时间段数据：" + dateStr+" "+hour+":" +mins+":00 \n " +
							"用户：" + userNames + " \n " +
							"执行开始时间：" + df.format(day1) + " \n " +
							"执行结束时间：" + df.format(day2);
					System.out.println(text);
					if(!email.equals("")){
						if(email.equals("liping.ren@syntun.com")){
							String[] emails = {"liping.ren@syntun.com"};	
							SyntunEmail.sendSimpleEmail(emails, "巡检数据执行结果", text, "b", "text");
						}else{
							String[] emails = {email, "liping.ren@syntun.com"};	
							SyntunEmail.sendSimpleEmail(emails, "巡检数据执行结果", text, "b", "text");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}