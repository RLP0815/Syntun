package com.syntun.etl.tools;

import java.sql.*;
import java.util.*;

public class ConnectSql53 {
	private static final int CONN_TIME_OUT = 288000;

	public static String sqlName = "shuju";
	public static String sqlPassWord = "shuju";
	public static String sqlDbName = "skyscope";
	public static String sqlHost = "192.168.0.53:3306";
	
	private static String sqlConStr = null;
	private static LinkedList<Connection> liConn = new LinkedList<Connection>();
	private static LinkedList<String> sqlCache = new LinkedList<String>();
	private static LinkedList<Statement> bacthStmt = new LinkedList<Statement>();
	private static int connCreterNum = 0;
	private static int maxConnNum = 20;
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
	 * 获取数据库链接
	 * 
	 * @return
	 * 
	 */
	public static Connection getConn() {
		synchronized (liConn) {
			if (sqlConStr == null) {
				sqlConStr = "jdbc:mysql://" + sqlHost + "/" + sqlDbName + "?user=" + sqlName + "&password="
						+ sqlPassWord
						+ "&seUnicode=true&characterEncoding=UTF8&autoReconnect=true&failOverReadOnly=false&maxReconnects=100";
			}
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
					return getConn();
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
		int s = ConnectSql53.bacthStmt.indexOf(stmt);
		if (s == -1)
			ConnectSql53.bacthStmt.add(stmt);
	}

	public static synchronized void removeStmt(Statement stmt) {
		int s = ConnectSql53.bacthStmt.indexOf(stmt);
		if (s != -1)
			ConnectSql53.bacthStmt.remove(s);
	}

	public static synchronized int exeAllBacth() throws SQLException {
		int sNum = 0;
		while (ConnectSql53.bacthStmt.size() > 0) {
			sNum += ConnectSql53.bacthStmt.remove(0).executeBatch().length;
		}
		return sNum;
	}

	public static synchronized void cacheInserSql(String sql, Statement stmt) {
		if (ConnectSql53.sqlCache.size() >= 50) {
			try {
				for (int i = 0; i < ConnectSql53.sqlCache.size(); i++)
					stmt.addBatch(ConnectSql53.sqlCache.get(i));

				stmt.executeBatch();
				stmt.clearBatch();
				stmt.clearWarnings();
			} catch (Exception e) {
				System.out.println("执行缓存sql错误");
				System.exit(0);
			}
			ConnectSql53.sqlCache.clear();
			ConnectSql53.sqlCache = new LinkedList<String>();
		}
		ConnectSql53.sqlCache.add(sql);

	}
}
