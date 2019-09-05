package com.syntun.etl.tools;

import java.sql.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectSqlUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConnectSqlUtil.class);
	private static final int CONN_TIME_OUT = 288000;

	public static String sqlName = "wgdata";
	public static String sqlPassWord = "syntun-000";
	public static String sqlDbName = "crawler";
	public static String sqlHost = "192.168.0.144:3306";// 原来124
	
	private static String sqlConStr = null;
	private static LinkedList<Connection> liConn = new LinkedList<Connection>();
	private static LinkedList<String> sqlCache = new LinkedList<String>();
	private static LinkedList<Statement> bacthStmt = new LinkedList<Statement>();
	private static int connCreterNum = 0;
	private static int maxConnNum = 10;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private synchronized static void connCreaterNumChange(int num) {
		System.err.println("+++++++++connCreterNum=" + connCreterNum + " liConnLength = " + liConn.size());
		connCreterNum += num;
		if (connCreterNum < 0)
			connCreterNum = 0;
	}
	/**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection(String URL,String userName,String passWord) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, userName, passWord);
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
	 * 获取数据库链接
	 * 
	 * @return
	 * 
	 */
	public static Connection getConn(String dataIP,String databaseName) {
		synchronized (liConn) {
			//if (sqlConStr == null) {
//				if(dataIP.equals("192.168.0.132")){
//					sqlName = "xitong";
//					sqlPassWord = "xitong001";
//				}
				sqlConStr = "jdbc:mysql://" + dataIP + ":3306/" + databaseName + "?user=" + sqlName + "&password="
						+ sqlPassWord
						+ "&seUnicode=true&characterEncoding=UTF8&autoReconnect=true&failOverReadOnly=false&maxReconnects=100";
			//}
			Connection conn = null;
			try {
				if (liConn.isEmpty() || liConn.size() == 0) {

					if (connCreterNum < maxConnNum) {
						liConn.add(DriverManager.getConnection(sqlConStr));
						connCreaterNumChange(1);
					} else {
						while (liConn.size() == 0 || connCreterNum < maxConnNum) {
							System.out.println("达到最大连接数，休眠等待");
							Thread.sleep(300);
						}
					}
				}
				conn = (Connection) liConn.pollFirst();
				// 判断链接是否有效
				if (conn.isClosed() || !conn.isValid(CONN_TIME_OUT)) {
					connCreterNum--;
					return getConn(dataIP, databaseName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("connection db error!!!");
				System.exit(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return conn;
		}
	}

	public static void push(Connection conn) {
		synchronized (liConn) {
			if (connCreterNum < maxConnNum) {
				try {
					if (!conn.isClosed()) {
						if (!conn.getAutoCommit()) {
							conn.commit();
							conn.setAutoCommit(true);
						}
						conn.clearWarnings();
					} else
						conn = DriverManager.getConnection(sqlConStr);

					liConn.add(conn);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					conn.close();
					connCreaterNumChange(-1);
				} catch (Exception e) {
					conn = null;
				}
			}
		}
	}

	public static synchronized void addStmt(Statement stmt) {
		int s = ConnectSqlUtil.bacthStmt.indexOf(stmt);
		if (s == -1)
			ConnectSqlUtil.bacthStmt.add(stmt);
	}

	public static synchronized void removeStmt(Statement stmt) {
		int s = ConnectSqlUtil.bacthStmt.indexOf(stmt);
		if (s != -1)
			ConnectSqlUtil.bacthStmt.remove(s);
	}

	public static synchronized int exeAllBacth() throws SQLException {
		int sNum = 0;
		while (ConnectSqlUtil.bacthStmt.size() > 0) {
			sNum += ConnectSqlUtil.bacthStmt.remove(0).executeBatch().length;
		}
		return sNum;
	}

	public static synchronized void cacheInserSql(String sql, Statement stmt) {
		if (ConnectSqlUtil.sqlCache.size() >= 50) {
			try {
				for (int i = 0; i < ConnectSqlUtil.sqlCache.size(); i++)
					stmt.addBatch(ConnectSqlUtil.sqlCache.get(i));

				stmt.executeBatch();
				stmt.clearBatch();
				stmt.clearWarnings();
			} catch (Exception e) {
				System.out.println("执行缓存sql错误");
				System.exit(0);
			}
			ConnectSqlUtil.sqlCache.clear();
			ConnectSqlUtil.sqlCache = new LinkedList<String>();
		}
		ConnectSqlUtil.sqlCache.add(sql);

	}
	
	/**
     * 获取数据库下的所有库名
     */
    public static List<String> getDatabaseNames(String dataIP, String userName, String passWord) {
        List<String> databaseNames = new ArrayList<>();
        Connection conn = getConn(dataIP,"crawler");
        //Connection conn = getConnection(URL, userName, passWord);
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
                push(conn);
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return databaseNames;
    }
}
