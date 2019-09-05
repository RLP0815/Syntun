package com.syntun.etl.tools;

import java.sql.*;
import java.util.*;

public class ConnectSqlServer16 {
	private static final int CONN_TIME_OUT = 288000;
	private static LinkedList<Connection> liConn = new LinkedList<Connection>();
	private static LinkedList<String> sqlCache = new LinkedList<String>();
	private static LinkedList<Statement> bacthStmt = new LinkedList<Statement>();
	private static int connCreterNum = 0;
	private static int maxConnNum = 20;
	// public static String sqlDbName = "syntun_base";
	// public static String sqlHost = "192.168.0.58";
	// public static String driverName =
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
	// public static String sqlConStr = null; // 连接服务器和数据库
	// public static String userName = "liang"; // 默认用户名
	// public static String userPwd = "liangjianqing"; // 密码

	//public static String sqlDbName = "skyscope_temp";
	public static String sqlDbName = "skyscope";
	public static String sqlHost = "192.168.0.16";
	public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
	public static String sqlConStr = null; // 连接服务器和数据库
	public static String userName = "liang"; // 默认用户名
	public static String userPwd = "liangjianqing"; // 密码

	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
				sqlConStr = "jdbc:sqlserver://" + sqlHost + ":1433; DatabaseName=" + sqlDbName;
			}
			Connection conn = null;
			try {
				if (liConn.isEmpty() || liConn.size() == 0) {
					if (connCreterNum < maxConnNum) {
						liConn.add(DriverManager.getConnection(sqlConStr, userName, userPwd));
						connCreaterNumChange(1);
					} else {
						while (liConn.size() == 0 || connCreterNum < maxConnNum) {
							System.out.println("16~~~max connection");
							Thread.sleep(2000);
						}
					}
				}
				conn = liConn.pollFirst();
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

	
	public static Connection getOneConn() {
		sqlConStr = "jdbc:sqlserver://" + sqlHost + ":1433; DatabaseName=" + sqlDbName;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(sqlConStr, userName, userPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getOneConn(String ip, String dataBaseName) {
		sqlConStr = "jdbc:sqlserver://" + ip + ":1433; DatabaseName=" + dataBaseName;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(sqlConStr, userName, userPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
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
					// TODO Auto-generated catch block
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
		int s = ConnectSqlServer16.bacthStmt.indexOf(stmt);
		if (s == -1)
			ConnectSqlServer16.bacthStmt.add(stmt);
	}

	public static synchronized void removeStmt(Statement stmt) {
		int s = ConnectSqlServer16.bacthStmt.indexOf(stmt);
		if (s != -1)
			ConnectSqlServer16.bacthStmt.remove(s);
	}

	public static synchronized int exeAllBacth() throws SQLException {
		int sNum = 0;
		while (ConnectSqlServer16.bacthStmt.size() > 0) {
			sNum += ConnectSqlServer16.bacthStmt.remove(0).executeBatch().length;
		}
		return sNum;
	}

	public static synchronized void cacheInserSql(String sql, Statement stmt) {
		if (ConnectSqlServer16.sqlCache.size() >= 50) {
			try {
				for (int i = 0; i < ConnectSqlServer16.sqlCache.size(); i++)
					stmt.addBatch(ConnectSqlServer16.sqlCache.get(i));

				stmt.executeBatch();
				stmt.clearBatch();
				stmt.clearWarnings();
			} catch (Exception e) {
				System.out.println("执行缓存sql错误");
				System.exit(0);
			}
			ConnectSqlServer16.sqlCache.clear();
			ConnectSqlServer16.sqlCache = new LinkedList<String>();
		}
		ConnectSqlServer16.sqlCache.add(sql);

	}
}
