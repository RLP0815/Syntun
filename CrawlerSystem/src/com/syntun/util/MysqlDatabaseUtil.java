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
public class MysqlDatabaseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(MysqlDatabaseUtil.class);

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USERNAME = "xitong";
    private static String PASSWORD = "xitong001";
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    
/*    public DatabaseUtil(String DRIVER,String URL,String USERNAME,String PASSWORD){
        this.DRIVER = DRIVER;
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }*/

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
    public static Connection getConnection(String URL) {
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
     * 获取数据库下的所有库名
     */
    public static List<String> getDatabaseNames(String URL) {
        List<String> databaseNames = new ArrayList<>();
        Connection conn = getConnection(URL);
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            rs = db.getCatalogs();
            while(rs.next()){
                String string = rs.getString(1);
                databaseNames.add(string);
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return databaseNames;
    }
    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames(String URL) {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection(URL);
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName,String URL) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection(URL);
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName,String URL) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection(URL);
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
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
    public static List<HashMap<String,String>> getTableData(String tableName,String URL,String column,String productId) {
        List<HashMap<String,String>> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection(URL);
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName + " where product_id = ?";
        try {
            pStemt = conn.prepareStatement(tableSql);
            pStemt.setString(1, productId);
            ResultSet rSte = pStemt.executeQuery();
            while (rSte.next()) {
            	String[] sp = column.split(",");
            	HashMap<String,String> m = new HashMap<String,String>();
            	for(String s:sp){
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
     * 获取表中字段的所有注释
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName,String URL) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection(URL);
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }
    public static void main(String[] args) {
       // List<String> tableNames = getTableNames();
        //System.out.println("tableNames:" + tableNames);
/*        for (String tableName : tableNames) {
            System.out.println("ColumnNames:" + getColumnNames(tableName));
            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
            System.out.println("ColumnComments:" + getColumnComments(tableName));
        }*/

        //DatabaseUtil d = new DatabaseUtil(DRIVER,URL,USERNAME,PASSWORD);
/*        System.out.println(getDatabaseNames());
        System.out.println(getTableNames());  
        System.out.println("ColumnTypes:" + getColumnNames("address_list"));*/
    	String tableName = "address_list";
    	String tableName2 = "administrative_division";
		String tableColumn = "county_id,county,province_id,province";
		String tableColumn2 = "county_id,county,province_id,province";
		String relationid = "a.county = b.xian_new";
		String whereid = "a.county = '东城区'";
    }
}
