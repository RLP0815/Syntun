package com.syntun.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangdong on 2019/3/12.
 */
public class MysqlUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(MysqlUtil.class);

    private static String DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection(String URL,String USERNAME,String PASSWORD) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }
    /**
     * 获取表中数据
     * @param tableName
     * @return
     */
    public static List<HashMap<String,String>> getTableData(String URL,String USERNAME,String PASSWORD,String tableName,String product_id,List<String> columns,int flag,String sku_shop_id,String platformid) {
        List<HashMap<String,String>> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection(URL,USERNAME,PASSWORD);
        PreparedStatement pStemt = null;
        String tableSql = "";
        
        if(flag == 1){//天猫
        	tableSql = "select * from "+tableName+" where operation_product_id =? and platform_id ='"+platformid+"' ORDER BY get_date DESC,hour DESC,min DESC LIMIT 1";
        }else if(flag == 2){//国美
        	tableSql = "select * from "+tableName+" where operation_product_id =? and sku_id =? and platform_id ='"+platformid+"' ORDER BY get_date DESC,hour DESC,min DESC LIMIT 1";
        }else if(flag == 3){//苏宁
        	tableSql = "select * from "+tableName+" where operation_product_id =? and shop_code =? and platform_id ='"+platformid+"' ORDER BY get_date DESC,hour DESC,min DESC LIMIT 1";
        }
        
        try {
            pStemt = conn.prepareStatement(tableSql);
            pStemt.setString(1, product_id);
            if(flag != 1){
            	pStemt.setString(2,sku_shop_id);
            }
            
            ResultSet rSte = pStemt.executeQuery();
            while (rSte.next()) {
            	HashMap<String,String> m = new HashMap<String,String>();
            	for(String s:columns){
            		String str = rSte.getString(s);
            		m.put(s, str);
            	}
            	columnTypes.add(m);
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }
    
    /**
     * 获取表中所有数据
     * @param tableName
     * @return
     */
    public static List<HashMap<String,String>> getTableDetail(String URL,String USERNAME,String PASSWORD,String tableName,Map<String,String> dataMap,List<String> columns,int flag,String platformid) {
        List<HashMap<String,String>> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection(URL,USERNAME,PASSWORD);
        PreparedStatement pStemt = null;
        String tableSql = "";
        if(flag == 1){//天猫
        	tableSql = "select * from "+tableName+" where operation_product_id =? and platform_id ='"+platformid+"' and get_date =? and hour =? and min =?";
        }else if(flag == 2){//国美
        	tableSql = "select * from "+tableName+" where operation_product_id =? and platform_id ='"+platformid+"' and sku_id =? and get_date =? and hour =? and min =?";
        }else if(flag == 3){//苏宁
        	tableSql = "select * from "+tableName+" where operation_product_id =? and platform_id ='"+platformid+"' and shop_code =? and get_date =? and hour =? and min =?";
        }
        
        try {
            pStemt = conn.prepareStatement(tableSql);
            pStemt.setString(1, dataMap.get("product_id"));
            if(flag == 1){
                pStemt.setString(2, dataMap.get("get_date"));
                pStemt.setString(3, dataMap.get("hour"));
                pStemt.setString(4, dataMap.get("min"));
            }else{
            	pStemt.setString(2, dataMap.get("sku_shop_id"));
                pStemt.setString(3, dataMap.get("get_date"));
                pStemt.setString(4, dataMap.get("hour"));
                pStemt.setString(5, dataMap.get("min"));
            }

            ResultSet rSte = pStemt.executeQuery();
            while (rSte.next()) {
            	HashMap<String,String> m = new HashMap<String,String>();
            	for(String s:columns){
            		String str = rSte.getString(s);
            		m.put(s, str);
            	}
            	columnTypes.add(m);
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }
    public static void main(String[] args) {
    	String url = "jdbc:mysql://192.168.0.60:3306/skyscope?useUnicode=true&amp;characterEncoding=utf8";
    	String username = "wangdong";
    	String password = "wang123";
    	
    	List<String> columns = new ArrayList<>();
    	columns.add("get_date");
    	columns.add("hour");
    	columns.add("min");
    	
    	List<String> pageColumns = new ArrayList<>();
    	pageColumns.add("promotion_type_name");
    	pageColumns.add("promotion_type_info");
    	pageColumns.add("operation_product_id");
    	pageColumns.add("platform_id");
    	pageColumns.add("hour");
    	pageColumns.add("min");
    	pageColumns.add("get_date");
    	pageColumns.add("shop_code");
    	pageColumns.add("sku_id");
    	
    	List<String> pageColumns2 = new ArrayList<>();
    	pageColumns2.add("product_price");
    	pageColumns2.add("promotion_price");
    	pageColumns2.add("operation_product_id");
    	pageColumns2.add("platform_id");
    	pageColumns2.add("hour");
    	pageColumns2.add("min");
    	pageColumns2.add("get_date");
    	pageColumns2.add("shop_code");
    	pageColumns2.add("sku_id");
    	List<HashMap<String,String>> tableData = getTableData(url,username,password,"shop_product_price_list","534827183734",columns,1,"","1");
    	if(tableData.size()>0){
    		HashMap<String,String> dataMap = tableData.get(0);
    		dataMap.put("product_id", "534827183734");
    		List<HashMap<String,String>> tableDetail = getTableDetail(url,username,password,"shop_product_price_list",dataMap,pageColumns2,1,"1");
    		System.out.println(tableDetail);
    	}
    }
}
