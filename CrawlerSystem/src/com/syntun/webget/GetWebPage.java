﻿package com.syntun.webget;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

import com.syntun.entity.Url302List;
import com.syntun.etl.tools.Base64;
import com.syntun.etl.tools.ConnADSL;
import com.syntun.etl.tools.ConnectSql;
import com.syntun.etl.tools.PatternMatcher;
import com.syntun.etl.tools.PurlFiledList;
import com.syntun.etl.tools.SleepUtil;
import com.syntun.etl.tools.ZipTools;
import com.syntun.wetget.annotation.SetProperty;

/**
 * 
 * 多域页面漫爬抓取类
 * 
 */
@SetProperty(propertyVars = { "maximumPoolSize", "timeOut", "urlMaxResetAcessNum", "resetAdslIOENum", "proxyHost",
		"proxyPort", "proxyUser", "proxyPass", "cookieTime" }, propertyComment = "url请求参数")
public class GetWebPage {
	public static List<PurlFiledList> listPurlFiled = new ArrayList<PurlFiledList>();

	public static int timeOut = 30 * 1000;
	/**
	 * 启动次数
	 */
	private static int startNum = 0;
	/**
	 * 线程池最大线程数量
	 */
	private static int maximumPoolSize = 8;
	public static int cookieTime = 5;

	/**
	 * 是否正在连接ADSL
	 */
	private static boolean isConnAdsl = false;
	/**
	 * 同一地址最大重复访问次数
	 */
	private static int urlMaxResetAcessNum = 2;
	/**
	 * 最大读取长度(2M)
	 */
	final static long MAX_READER_LENGTH = 1000 * 5000;
	// 代理地址
	private static String proxyHost = "192.168.0.200";
	// 代理端口
	private static int proxyPort = 65500;
	// 代理用户名
	public static String proxyUser = "u1";
	// 代理密码
	private static String proxyPass = null;
	private static List<ProxyUser> proxyU = new ArrayList<ProxyUser>();

	/**
	 * 发生几次io异常后重新连接adsl
	 */
	private static int resetAdslIOENum = 10;
	/**
	 * 当前adsl重起之前IO异常次数
	 */
	private static int iOENum = 0;

	/**
	 * 获取cookie
	 */
	public static Map<String, String> cookieMap = new HashMap<String, String>();

	private static int proxyNum = 0;

	/**
	 * 获取https 证书处理
	 */
	// public static List<String> htttpsCfMap = new ArrayList<String>();

	// private static HostnameVerifier defaultHostnameVerifier;
	// private static

	// static {
	// defaultHostnameVerifier = new DefaultHostnameVerifier();
	// hv = new HostnameVerifier() {
	// @Override
	// public boolean verify(String hostname, SSLSession session) {
	// return true;
	// }
	// };
	// }

	// private static class DefaultHostnameVerifier implements HostnameVerifier
	// {
	// public boolean verify(String hostname, SSLSession session) {
	// return false;
	// }
	// }

	/**
	 * 断线，后判断是否重新连接
	 */
	private static synchronized void isOutLine() {
		iOENum++;
		// 是否进行adsl重起
		if (iOENum > resetAdslIOENum)
			iOENum = 0;
		else
			return;
		// 重起adsl
		System.out.println("********:now start reset ADSL conection .......");
		isConnAdsl = true;
		if (proxyPass != null && !proxyPass.equals("")) {
			proxyNum++;
			if (proxyNum == proxyU.size()) {
				proxyNum = 0;
			}
			proxyUser = proxyU.get(proxyNum).getUserName();
			proxyPass = proxyU.get(proxyNum).getUserPwd();
			ConnADSL.connAgent(proxyU.get(proxyNum).getPort());
		} else {
			while (!ConnADSL.connADSL()) {
				SleepUtil.sleep(1000);
			}
		}
		isConnAdsl = false;
		System.out.println("********:end reset ADSL conectioned ......." + proxyUser);
	}

	/**
	 * 单件模式私有构造函数
	 */
	private GetWebPage() {
	}

	public static HashMap<String, List<String>> cookieList = new HashMap<String, List<String>>();

	public static void getCook() {
		Connection conn = (Connection) ConnectSql.getConn();
		try {
			Statement stmt = conn.createStatement();
			// 加载地址过滤正则
			String sql = "select * from url_cookie";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String key = rs.getString("url_group");
				cookieMap.put(key, rs.getString("cookie_str"));
			}
			rs.close();
			stmt.close();
			PreparedStatement pst = conn
					.prepareStatement("SELECT `user_name`, `user_pwd`,`port` FROM proxy_user WHERE STATUS=1");
			ResultSet rsP = pst.executeQuery();
			while (rsP.next()) {
				ProxyUser user = new ProxyUser();
				user.setPort(rsP.getInt("port"));
				user.setUserName(rsP.getString("user_name"));
				user.setUserPwd(rsP.getString("user_pwd"));
				proxyU.add(user);
			}
			rsP.close();
			pst.close();
			Statement stmt1 = conn.createStatement();
			// 加载地址过滤正则
			String sql1 = "select * from url_cookie_list";
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				String key = rs1.getString("url_group");
				String[] strs = rs1.getString("sort_id").split(",");
				if (strs.length != 1) {
					for (String str : strs) {
						key = key + "_" + str;
						List<String> list = null;
						if (!cookieList.containsKey(key)) {
							list = new ArrayList<String>();
						} else {
							list = cookieList.get(key);
						}
						list.add(rs1.getString("url_cookie"));
						cookieList.put(key, list);
					}
				} else {
					key = key + "_" + strs[0];
					List<String> list = null;
					if (!cookieList.containsKey(key)) {
						list = new ArrayList<String>();
					} else {
						list = cookieList.get(key);
					}
					list.add(rs1.getString("url_cookie"));
					cookieList.put(key, list);
				}
			}
			rs1.close();
			stmt1.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(0);
		}
		ConnectSql.push(conn);
	}

	public static List<String> url411 = new ArrayList<String>();

	/**
	 * 开始抓取
	 * 
	 * @throws SQLException
	 */
	public static void startGet() {
		getCook();
		getPurlFiledList();
		getUrl302List();
		getUrl411();
		if (GetWebPage.startNum == 0) {
			for (int i = 0; i < GetWebPage.maximumPoolSize; i++) {
				GetWebPage gpc = new GetWebPage();
				new Thread(gpc.new GetPage()).start();
				startNum++;
			}
		}
	}

	public static void getUrl411() {
		// 查询表名称
		String sql = "SELECT url_addr FROM wgdata.url_err411";
		Connection conn = ConnectSql.getConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				url411.add(rs.getString("url_addr"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
		} finally {
			ConnectSql.push(conn);
		}
	}

	public static void getPurlFiledList() {
		// 查询表名称
		String sql = "SELECT `url_group`, `generate_sort_id`, `field_str`, `filed_fun` FROM purl_filed_list";
		Connection conn = ConnectSql.getConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PurlFiledList p = new PurlFiledList();
				p.setFieldStr(rs.getString("field_str"));
				p.setFiledFun(rs.getString("filed_fun"));
				p.setGenerateSortId(rs.getString("generate_sort_id"));
				p.setUrlGroup(rs.getString("url_group"));
				listPurlFiled.add(p);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSql.push(conn);
		}
	}

	public static List<Url302List> listUrl302 = new ArrayList<Url302List>();

	public static void getUrl302List() {
		// 查询表名称
		String sql = "SELECT `url_group`, `sort_id` FROM url302list";
		Connection conn = ConnectSql.getConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Url302List p = new Url302List();
				p.setUrlGroup(rs.getInt("url_group")+"");
				p.setSortId(rs.getString("sort_id"));
				listUrl302.add(p);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectSql.push(conn);
		}
	}

	static HashMap<String, HashMap<String, String>> requestContestMap = new HashMap<String, HashMap<String, String>>();

	public static void getRequestContent() {
		// 查询表名称
		String sql = "SELECT url_group,sort_id,content,replace_filed FROM request_content";
		Connection conn = ConnectSql.getConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> m = new HashMap<String, String>();
				m.put("content", rs.getString("content"));
				m.put("replace_filed", rs.getString("replace_filed"));
				requestContestMap.put(rs.getString("url_group") + "\001" + rs.getString("sort_id"), m);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("没有替换表——————————————————request_content------第一版在112服务器");
		} finally {
			ConnectSql.push(conn);
		}
	}

	/**
	 * 完成请求并取的网页内容的内部类
	 */
	class GetPage implements Runnable {
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			Url ui = null;
			int urlAcessNum = 0;
			while (true) {
				if (ui == null || urlAcessNum >= urlMaxResetAcessNum) {
					ui = UrlSupervise.getUrlInfo();
					urlAcessNum = 0;
				} else
					urlAcessNum++;
				if (ui != null) {
					String key = ui.getPatternUrlId() + "_" + ui.getSortId();
					if (cookieList.containsKey(key) && cookieList.get(key).size() > 0) {
						ui.setCookie(cookieList.get(key).get(0));
					}
					try {
						while (GetWebPage.isConnAdsl)
							SleepUtil.sleep(1000 * 2);
						long bTime = System.currentTimeMillis();
						if (ui.getUrl().contains("detail.m.tmall")) {
							this.getPageInfo(ui, "", "", "");
						} else if (ui.getUrl().contains("kaola.com")) {
							this.getPageInfo(ui, "", "", "");
						} else if (ui.getUrl().contains("amazon")) {
							this.getPageInfo(ui, "", "", "");
						} else if (ui.getUrl().contains("vip.com") || ui.getUrl().contains("www.ele.me/restapi")) {
							this.getPageInfo(ui, "", "", "");
						} else if (ui.getUrl().indexOf("https") == 0 && !ui.getUrl().contains("ele.")) {
							this.getPageInfoHttps(ui);
						} else {
							this.getPageInfo(ui, "", "", "");
						}
						if (ui.getHtmlContent() == null && (ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)) {
							ui = null;
							continue;
						}
						long eTime = System.currentTimeMillis();
						System.out.println(ui.getUrl() + " getTime = " + (eTime - bTime));
						int resultCode = InspectWebPage.getInspectObj().inspectPage(ui);
						if (ui.getUrl().indexOf("mdskip.taobao.com") != -1
								&& ui.getHtmlContent().indexOf("window.location.href=") != -1) {
							continue;
						}
						if (ui.getHtmlContent().indexOf("onSibRequestSuccess({\"rgv587_flag\":\"sm\"") != -1) {
							continue;
						}

						if (resultCode == 1) {
							throw new IOException("可能被封IP!!!");
						} else if (resultCode == 2) {
							Url rUi = new Url(ui.getPreantUrl(), ui);
							if (rUi.getUrl().indexOf("https") == 0 && !ui.getUrl().contains("ele.")) {
								this.getPageInfoHttps(rUi);
							} else {
								this.getPageInfo(rUi, "", "", "");
							}
							continue;
						}

						for (UrlLimit ul : UrlLimitInit.urlLimitList) {
							if (ul.getUrlGroup().equals(ui.getPatternUrlId() + "")) {
								for (String s : ul.getSortIdList()) {
									if (s.equals(ui.getSortId() + "")) {
										ul.updateStatusToTrue();
										System.out.println("parse:" + ul.getUrlGroup() + ":" + s);
										break;
									}
								}
							}
						}
						ui.addData("download_time", new Date().getHours() + "");
						// 判断是否是数据魔方的页面，如果是，从url地址中取出data_time值
						if (ui.getUrl().indexOf("mofang") != -1) {
							Pattern p = Pattern.compile("r1\\:(\\S*?)/");
							Matcher m = p.matcher(ui.getUrl());
							while (m.find()) {
								ui.addData("data_time", m.group(1));
							}
						}
						while (true) {
							if (ResolveWebPage.addContent(ui))
								break;
							else
								SleepUtil.sleep(Math.round(1000 * 0.5));
						}
					} catch (IOException ioe) {
						System.out.println("ioeMesage:" + ioe.getMessage());
						if (!GetWebPage.isConnAdsl)
							GetWebPage.isOutLine();
						System.out.println("获取内容错误 = " + ui.getUrl());
						// if (ui != null && ui.getPatternUrlId() == 500001) {
						// UserDangdang.adduserUrlList(ui);
						// UserDangdang.updateStatusToTrue();
						// ui = null;
						// continue;
						// }
						boolean isLimit = true;
						for (UrlLimit ul : UrlLimitInit.urlLimitList) {
							if (ul.getUrlGroup().equals(ui.getPatternUrlId() + "")) {
								for (String s : ul.getSortIdList()) {
									if (s.equals(ui.getSortId() + "")) {
										isLimit = false;
										ul.updateStatusToTrue();
										ul.getUrlLimitThread().adduserUrlList(ui);
										break;
									}
								}
							}
						}
						if (!isLimit) {
							ui = null;
							continue;
						}
						while (GetWebPage.isConnAdsl)
							SleepUtil.sleep(1000 * 2);
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(0);
					}
					ui = null;
				} else {
					SleepUtil.sleep(1000 * 1);
				}
			}
		}

		/**
		 * 获取页面内容
		 * 
		 * @return
		 * @throws IOException
		 * @throws InterruptedException
		 * @throws Exception
		 */
		public void getPageInfoNewMJ(Url ui) throws IOException, InterruptedException {
			URL url1 = new URL(ui.getUrl());
			boolean isLimit = true;
			if (!isLimit) {
				HashMap<String, String> urlCookie = DnsManager.getURLStr(ui.getUrl(), true);
				String urlStr = urlCookie.get("urlStr");
				System.out.println("延迟加载并且使用ip：" + urlStr);
				ui.setUrl(urlStr);
			}
			URL url = new URL(ui.getUrl());
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.setConnectTimeout(GetWebPage.timeOut);
			uc.setDoOutput(true);
			uc.setUseCaches(false);
			uc.setRequestProperty("authority", url1.getHost());
			uc.setRequestProperty("method", "GET");
			uc.setRequestProperty("path", url.getPath());
			uc.setRequestProperty("scheme", "https");
			uc.setRequestProperty("accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			uc.setRequestProperty("accept-encoding", "gzip, deflate, br");
			uc.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
			uc.setRequestProperty("cache-control", "max-age=0");
			uc.setRequestProperty("cookie",
					"usertrack=3/zHRFm3uhkDCEXNAxJ9Ag==; __kaola_usertrack=20170912184234908626; _da_ntes_uid=20170912184234908626; _ntes_nnid=02f6be77ba9999d6d6ed9c501d0972b3,1505212959140; JSESSIONID-WKL-8IO=KQSOkKjfSt2py5VspshhWJzjZBL%5CsPGKSlKiWnlUP8C%5CopDlyMQtAguNVlifSJHLk7idiPO6aIOhUQus9BgZWRgcrXOHwk6%2BHxXuf0EqCYCIvGLWSd2G2IpwRS%5CzztPl%5CBZcAiZhobBXs2By4cAaqeli%2BsyzY6xyVCZW4s8Nt3JNX7zt%3A1505299359278; _klhtxd_=31; _ga=GA1.2.1678493628.1505212960; __da_ntes_utma=2525167.1098285969.1505452721.1505452721.1505452721.1; davisit=1; __da_ntes_utmfc=");
			uc.setRequestProperty("upgrade-insecure-requests", "1");
			uc.setRequestProperty("user-agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

			uc.setReadTimeout(GetWebPage.timeOut);
			if (uc.getResponseCode() == 302) {
				for (Url302List u : listUrl302) {
					if (ui.getPatternUrlId() == Integer.valueOf(u.getUrlGroup())) {
						String[] strs302 = u.getSortId().split(",");
						System.out.println(strs302[0]);
						for (String str : strs302) {
							if (ui.getSortId() == Integer.valueOf(str) && uc.getHeaderField("location") != null) {
								ui.setUrl(uc.getHeaderField("location"));
								System.out.println("302url:" + uc.getHeaderField("location"));
								getPageInfo(ui, "", "", "");
								return;
							}
						}
					}
				}
				ui.setHtmlContent("请求被跳转,断线重连");
				return;
			}
			if (uc.getResponseCode() == 500 && ui.getUrl().indexOf("pageadword") != -1
					&& (ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)) {
				UrlSupervise.addParseUrlInfo(ui);
				return;
			}
			// 取得字符集编码
			String charSet = null;
			String contentType = uc.getHeaderField("Content-Type");
			ui.setCookie(uc.getHeaderField("Set-Cookie"));
			if (contentType != null && !contentType.equals("")) {
				String[] contentTypeArr = contentType.split(";");
				for (int i = 0; i < contentTypeArr.length; i++) {
					if (contentTypeArr[i].indexOf("charset=") != -1) {
						String[] charSetArr = contentTypeArr[i].split("=");
						charSet = charSetArr[1].trim();
						break;
					}
				}
			}
			uc.getHeaderField("");
			if (charSet == null)
				charSet = ui.getChartSet();
			else
				ui.setChartSet(charSet);
			if ((ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)
					&& ui.getChartSet().toLowerCase().indexOf("gb") != -1 && ui.getSortId() != 1
					&& UrlSupervise.isFirst == 1) {
				charSet = "UTF-8";
				ui.setChartSet("UTF-8");
			} else if ((ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)
					&& ui.getChartSet().toLowerCase().indexOf("UTF-8") != -1 && ui.getSortId() != 1
					&& UrlSupervise.isFirst == 1) {
				charSet = "GBK";
				ui.setChartSet("GBK");
			}
			// 当字符集为GB2312时转成父集GBK
			charSet = charSet.equalsIgnoreCase("GB2312") ? "GBK" : charSet;
			InputStream uis = null;
			try {
				uis = uc.getInputStream();
			} catch (IOException e) {
				ui.setHtmlContent("您访问的网页不存在");
				return;
			}
			InputStreamReader reader;
			StringBuffer htmlStr = new StringBuffer();
			// 检测返回的页面源码是否被压缩
			try {
				if (uc.getHeaderField("Content-Encoding") != null
						&& uc.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip"))
					reader = new InputStreamReader(new GZIPInputStream(uis), charSet);
				else
					reader = new InputStreamReader(uis, charSet);
				int readNum = 0;
				while (true) {
					int b = reader.read();
					if (b == -1 || readNum > MAX_READER_LENGTH)
						break;
					htmlStr.append((char) b);
					readNum++;
				}
				reader.close();

			} catch (EOFException eex) {
				eex.printStackTrace();
			}
			ui.setHtmlContent(htmlStr.toString().replace("\r\n", "").replace("\n", "").replace("\r", "")
					.replaceAll("\\s+", " ").replaceAll("\\\\\\\\\\\\/", "/").replaceAll("\\\\\\\\\\\\\\\"", "\"")
					.replaceAll("\\\\\"", "\"").replaceAll("\\\\/", "\\/"));
		}

		/**
		 * 获取页面内容
		 * 
		 * @return
		 * @throws IOException
		 * @throws InterruptedException
		 * @throws Exception
		 */
		public void getPageInfo(Url ui, String userAgent, String type, String requestProperty) throws IOException, InterruptedException {
			URL url1 = new URL(ui.getUrl());
			boolean isLimit = true;
			for (UrlLimit ul : UrlLimitInit.urlLimitList) {
				if (ul.getUrlGroup().equals(ui.getPatternUrlId() + "")) {
					for (String s : ul.getSortIdList()) {
						if (s.equals(ui.getSortId() + "")) {
							isLimit = false;
							break;
						}
					}
				}
				if (!isLimit && ul.getIsDns() == 0) {
					isLimit = true;
				} else if (!isLimit) {
					System.out.println("延迟加载：" + ui.getUrl());
				}
			}
			if (!isLimit) {
				HashMap<String, String> urlCookie = DnsManager.getURLStr(ui.getUrl(), true);
				String urlStr = urlCookie.get("urlStr");
				System.out.println("延迟加载并且使用ip：" + urlStr);
				ui.setUrl(urlStr);
			}
			URL url = new URL(ui.getUrl());
			HttpURLConnection uc = null;
			if (proxyPass != null && !proxyPass.equals("")) {
				// 指定代理服务器地址和端口
				InetSocketAddress isa = new InetSocketAddress(proxyHost, proxyPort);
				// 创建代理
				Proxy proxy = new Proxy(Proxy.Type.HTTP, isa);
				// 设置登陆到代理服务器的用户名和密码
				Authenticator.setDefault(new MyAuthenticator(proxyUser, proxyPass));
				uc = (HttpURLConnection) url.openConnection(proxy);
			} else {
				uc = (HttpURLConnection) url.openConnection();
			}

			uc.setConnectTimeout(GetWebPage.timeOut);
			uc.setDoOutput(true);
			uc.setUseCaches(false);

			boolean is411 = false;
			for (String u : url411) {
				if (ui.getUrl().indexOf(u) != -1) {
					uc.setRequestMethod("GET");
					is411 = true;
				}
			}
			if (!is411) {
				if (ui.getPatternUrlId() == 989) {
					uc.setRequestMethod("POST");
				} else {
					uc.setRequestMethod("GET");
				}
			}
			uc.setRequestProperty("Host", url1.getHost());
			uc.setRequestProperty("Keep-Alive", "300");
			uc.setRequestProperty("Accept", "*/*");
			if (ui.getPatternUrlId() != 200004 && ui.getSortId() != 151) {
				if (ui.getPreantUrl() != null && !ui.getPreantUrl().equals("") && isLimit)
					uc.setRequestProperty("Referer", ui.getPreantUrl().trim());
			}
			uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			uc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			uc.setRequestProperty("Cache-Control", "max-age=0");
			uc.setRequestProperty("Connection", "keep-alive");
			uc.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; compatible; MSIE 5.0; DigExt) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2194.2 Safari/537.36");
			if (ui.getUrl().contains("kaola")) {
				uc.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
			}
			//如果从前台传入userAgent
			if (!userAgent.equals("")) {
				uc.setRequestProperty("User-Agent",userAgent);
			}
			// 请求参数
			if (requestContestMap.containsKey(ui.getPatternUrlId() + "\001" + ui.getSortId())) {
				HashMap<String, String> contentMap = requestContestMap
						.get(ui.getPatternUrlId() + "\001" + ui.getSortId());
				String requestContent = contentMap.get("content");

				if (contentMap.get("replace_filed") != null && !contentMap.get("replace_filed").equals("null")
						&& ui.getData(contentMap.get("replace_filed")) != null) {
					requestContent = requestContent.replace(contentMap.get("replace_filed"),
							ui.getData(contentMap.get("replace_filed")));
				}

				uc.setRequestProperty("Content-Length", requestContent.length() + "");
				DataOutputStream ds = new DataOutputStream(uc.getOutputStream());
				ds.write(requestContent.getBytes());
				ds.flush();
			}

			String uiCookie = cookieMap.get(ui.getPatternUrlId() + "");
			if (ui.getCookie() != null) {
				uiCookie = ui.getCookie();
			}
			//System.out.println(uiCookie);
			if (uiCookie != null && isLimit) {
				uc.setRequestProperty("Cookie", uiCookie);
			}
			if (ui.getUrl().contains("item.tuhu.cn/Tires")) {
				uc.setRequestProperty("Cookie",
						("defaultCar=0|veid|F+-+11|11-11|||tires".replace("veid", ui.getData("vehicle_id")))
								.replace("tires", ui.getData("tires")));
			}
			
			//前台传递参数进行设置
			if(type!=null && !type.equals("")){
				uc.setRequestMethod(type);
			}
			if(requestProperty!=null && !requestProperty.equals("")){
				for(int i=0;i < requestProperty.split("#_#").length; i++){
					uc.setRequestProperty(requestProperty.split("#_#")[i].split("@_@")[0], 
									requestProperty.split("#_#")[i].split("@_@")[1]);
				}
			}
			
			uc.setReadTimeout(GetWebPage.timeOut);
			if (uc.getResponseCode() == 302) {
				for (Url302List u : listUrl302) {
					if (ui.getPatternUrlId() == Integer.valueOf(u.getUrlGroup())) {
						String[] strs302 = u.getSortId().split(",");
						System.out.println(strs302[0]);
						for (String str : strs302) {
							if (ui.getSortId() == Integer.valueOf(str) && uc.getHeaderField("location") != null) {
								ui.setUrl(uc.getHeaderField("location"));
								System.out.println("302url:" + uc.getHeaderField("location"));
								this.getPageInfo(ui, "", type, requestProperty);
								return;
							}
						}
					}
				}
				throw new IOException("请求被跳转,断线重连");
			}
			if (uc.getResponseCode() == 500 && ui.getUrl().indexOf("pageadword") != -1
					&& (ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)) {
				UrlSupervise.addParseUrlInfo(ui);
				return;
			}
			// 取得字符集编码
			String charSet = null;
			String contentType = uc.getHeaderField("Content-Type");
			// ui.setCookie(uc.getHeaderField("Set-Cookie"));
			if (contentType != null && !contentType.equals("")) {
				String[] contentTypeArr = contentType.split(";");
				for (int i = 0; i < contentTypeArr.length; i++) {
					if (contentTypeArr[i].indexOf("charset=") != -1) {
						String[] charSetArr = contentTypeArr[i].split("=");
						charSet = charSetArr[1].trim();
						break;
					}
				}
			}
			if (charSet == null)
				charSet = ui.getChartSet();
			else
				ui.setChartSet(charSet);
			// if ((ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)
			// && ui.getChartSet().toLowerCase().indexOf("gb") != -1
			// && ui.getSortId() != 1 && UrlSupervise.isFirst == 1) {
			// charSet = "UTF-8";
			// ui.setChartSet("UTF-8");
			// } else if ((ui.getPatternUrlId() == 2 || ui.getPatternUrlId() ==
			// 3)
			// && ui.getChartSet().toLowerCase().indexOf("UTF-8") != -1
			// && ui.getSortId() != 1 && UrlSupervise.isFirst == 1) {
			// charSet = "GBK";
			// ui.setChartSet("GBK");
			// }
			// 当字符集为GB2312时转成父集GBK
			charSet = charSet.equalsIgnoreCase("GB2312") ? "GBK" : charSet;
			InputStream uis = null;
			try {
				uis = uc.getInputStream();
			} catch (IOException e) {

				String dangdang = e.getMessage().replaceAll("\\d+", "下架");
				if (dangdang.equals("http://product.dangdang.com/下架.html")) {
					ui.setHtmlContent("获取不到源代码1" + e.getMessage().replaceAll("\\d+", "下架"));
					System.out.println(e.getMessage() + "______" + dangdang);
					return;
				}

				if (ui.getPatternUrlId() == 100003 && (ui.getSortId() == 68 || ui.getSortId() == 69)) {
					ui.setHtmlContent("--");
					return;
				}
				if (UrlSupervise.isFirst != 0) {
					throw e;
				} else {
					ui.setHtmlContent("您访问的网页不存在");
					return;
				}
			}
			InputStreamReader reader;
			StringBuffer htmlStr = new StringBuffer();
			// 检测返回的页面源码是否被压缩
			try {
				if (uc.getHeaderField("Content-Encoding") != null
						&& uc.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip"))
					reader = new InputStreamReader(new GZIPInputStream(uis), charSet);
				else
					reader = new InputStreamReader(uis, charSet);
				int readNum = 0;
				while (true) {
					int b = reader.read();
					if (b == -1 || readNum > MAX_READER_LENGTH)
						break;
					htmlStr.append((char) b);
					readNum++;
				}
				reader.close();

			} catch (EOFException eex) {
				eex.printStackTrace();
			}
			ui.setHtmlContent(htmlStr.toString().replace("\r\n", "").replace("\n", "").replace("\r", "")
					.replaceAll("\\\\\\\\\\\\/", "/").replaceAll("\\\\\\\\\\\\\\\"", "\"").replaceAll("\\\\\"", "\"")
					.replaceAll("\\\\/", "\\/"));
		}

		/**
		 * 获取页面内容
		 * 
		 * @return
		 * @throws IOException
		 * @throws InterruptedException
		 * @throws Exception
		 */
		public void getPageInfoHttps(Url ui) throws IOException, InterruptedException {
			if (ui.getUrl().indexOf("amazon") != -1) {
				System.out.println("~~~~~~~");
				getPageInfoaaa(ui);
				return;
			}
			System.out.println(
					"https__________________________________________________________________________________________https");
			URL url1 = new URL(ui.getUrl());
			boolean isLimit = true;
			for (UrlLimit ul : UrlLimitInit.urlLimitList) {
				if (ul.getUrlGroup().equals(ui.getPatternUrlId() + "")) {
					for (String s : ul.getSortIdList()) {
						if (s.equals(ui.getSortId() + "")) {
							isLimit = false;
							break;
						}
					}
				}
				if (!isLimit && ul.getIsDns() == 0) {
					isLimit = true;
				} else if (!isLimit) {
					System.out.println("延迟加载：" + ui.getUrl());
				}
			}
			if (!isLimit) {
				HashMap<String, String> urlCookie = DnsManager.getURLStr(ui.getUrl(), true);
				String urlStr = urlCookie.get("urlStr");
				System.out.println("延迟加载并且使用ip：" + urlStr);
				ui.setUrl(urlStr);
			}
			URL url = new URL(ui.getUrl());
			/**
			 * https 证书处理位置
			 */
			// if (htttpsCfMap.contains(ui.getPatternUrlId() + "_" +
			// ui.getSortId())) {
			// try {
			// trustAllHttpsCertificates();
			// } catch (Exception e1) {
			// e1.printStackTrace();
			// }
			// HostnameVerifier hv = new HostnameVerifier() {
			// @Override
			// public boolean verify(String hostname, SSLSession session) {
			// return true;
			// }
			// };
			// HttpsURLConnection.setDefaultHostnameVerifier(hv);
			// } else {
			// HttpsURLConnection
			// .setDefaultHostnameVerifier(defaultHostnameVerifier);
			// }
			/**
			 * https 证书处理结束
			 */
			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();

			// ;
			// if (proxyPass != null && !proxyPass.equals("")) {
			// // 指定代理服务器地址和端口
			// InetSocketAddress isa = new InetSocketAddress(proxyHost,
			// proxyPort);
			// // 创建代理
			// Proxy proxy = new Proxy(Proxy.Type.HTTP, isa);
			// // 设置登陆到代理服务器的用户名和密码
			// Authenticator.setDefault(new MyAuthenticator(proxyUser,
			// proxyPass));
			// uc = (HttpURLConnection) url.openConnection(proxy);
			// } else {
			// uc = (HttpURLConnection) url.openConnection();
			// }

			if (ui.getPatternUrlId() != 301) {
				uc.setHostnameVerifier(new CustomizedHostnameVerifier());
				System.setProperty("https.protocols", "TLSv1,SSLv3,SSLv2Hello");
				uc.setConnectTimeout(GetWebPage.timeOut);
				uc.setDoOutput(true);
				uc.setUseCaches(false);
			}
			boolean is411 = false;
			for (String u : url411) {
				if (ui.getUrl().indexOf(u) != -1) {
					uc.setRequestMethod("GET");
					is411 = true;
				}
			}
			if (!is411) {
				if (ui.getPatternUrlId() == 989) {
					uc.setRequestMethod("POST");
				} else {
					uc.setRequestMethod("GET");
				}
			}
			uc.setRequestProperty("Host", url1.getHost());
			uc.setRequestProperty("Keep-Alive", "300");
			uc.setRequestProperty("Accept", "*/*");

			if (ui.getPreantUrl() != null && !ui.getPreantUrl().equals("") && isLimit)
				uc.setRequestProperty("Referer", ui.getPreantUrl().trim());
			uc.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			uc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			uc.setRequestProperty("Cache-Control", "max-age=0");
			uc.setRequestProperty("Connection", "keep-alive");
			if (ui.getUrl().contains("list.tmall")) {
				uc.setRequestProperty("User-Agent",
						"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
			} else if (ui.getUrl().indexOf("amazon") != -1) {
				uc.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.82 Safari/537.36");
			} else if (ui.getUrl().indexOf("mdskip.taobao.com") != -1) {
				String[] uaset = {
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E) ",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36",
						"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)" };
				uc.setRequestProperty("User-Agent", uaset[(int) (Math.random() * uaset.length)]);
			} else if (ui.getUrl().indexOf("www.suning.com/webapp/wcs/stores/ItemPrice") != -1
					|| ui.getUrl().indexOf("www.suning.com/emall") != -1
					|| ui.getUrl().indexOf("review.suning.com/ajax") != -1) {
				uc.setRequestProperty("User-Agent", "Baiduspider+(+http://www.baidu.com/search/spider.htm)");
			} else if (ui.getPatternUrlId() == 100003) {
				uc.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64; compatible; MSIE 5.0; DigExt) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2194.2 Safari/537.36");
			} else {
				uc.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.76 Safari/537.36");
			}
			String uiCookie = cookieMap.get(ui.getPatternUrlId() + "");
			if (ui.getCookie() != null) {
				uiCookie = ui.getCookie();
			}

			if (ui.getUrl().contains("channel.jd.com/food.html")) {
				uc.setRequestProperty("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
				uc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
				uc.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			}

			// 请求参数
			if (requestContestMap.containsKey(ui.getPatternUrlId() + "\001" + ui.getSortId())) {
				HashMap<String, String> contentMap = requestContestMap
						.get(ui.getPatternUrlId() + "\001" + ui.getSortId());
				String requestContent = contentMap.get("content");

				if (contentMap.get("replace_filed") != null && !contentMap.get("replace_filed").equals("null")
						&& ui.getData(contentMap.get("replace_filed")) != null) {
					requestContent = requestContent.replace(contentMap.get("replace_filed"),
							ui.getData(contentMap.get("replace_filed")));
				}

				uc.setRequestProperty("Content-Length", requestContent.length() + "");
				DataOutputStream ds = new DataOutputStream(uc.getOutputStream());
				ds.write(requestContent.getBytes());
				ds.flush();
			}

			if (uiCookie != null && isLimit) {
				uc.setRequestProperty("Cookie", uiCookie);
			}

			if (ui.getUrl().contains("item.tuhu.cn/Tires") && ui.getData("vehicle_id") != null
					&& ui.getData("tires") != null) {
				uc.setRequestProperty("Cookie",
						("defaultCar=0|veid|F+-+11|11-11|||tires".replace("veid", ui.getData("vehicle_id")))
								.replace("tires", ui.getData("tires")));
			}

			uc.setReadTimeout(GetWebPage.timeOut);

			if (uc.getResponseCode() == 403 && ui.getUrl().contains("review.suning.com/cmmdty_review/")) {
				ui.setHtmlContent("该网友以失效");
				return;
			}
			if (uc.getResponseCode() == 302) {
				for (Url302List u : listUrl302) {
					if (ui.getPatternUrlId() == Integer.valueOf(u.getUrlGroup())) {
						String[] strs302 = u.getSortId().split(",");
						System.out.println(strs302[0]);
						for (String str : strs302) {
							if (ui.getSortId() == Integer.valueOf(str) && uc.getHeaderField("location") != null) {
								ui.setUrl(uc.getHeaderField("location"));
								System.out.println("302url:" + uc.getHeaderField("location"));
								if (ui.getUrl().indexOf("https") == 0) {
									this.getPageInfoHttps(ui);
								} else {
									this.getPageInfo(ui, "", "", "");
								}
								return;
							}
						}
					}
				}
				throw new IOException("请求被跳转,断线重连");
			}
			if (uc.getResponseCode() == 500 && ui.getUrl().indexOf("pageadword") != -1
					&& (ui.getPatternUrlId() == 2 || ui.getPatternUrlId() == 3)) {
				UrlSupervise.addParseUrlInfo(ui);
				return;
			}
			// 取得字符集编码
			String charSet = null;
			String contentType = uc.getHeaderField("Content-Type");
			// ui.setCookie(uc.getHeaderField("Set-Cookie"));
			if (contentType != null && !contentType.equals("")) {
				String[] contentTypeArr = contentType.split(";");
				for (int i = 0; i < contentTypeArr.length; i++) {
					if (contentTypeArr[i].indexOf("charset=") != -1) {
						String[] charSetArr = contentTypeArr[i].split("=");
						charSet = charSetArr[1].trim();
						break;
					}
				}
			}
			if (charSet == null)
				charSet = ui.getChartSet();
			else
				ui.setChartSet(charSet);

			// 当字符集为GB2312时转成父集GBK
			charSet = charSet.equalsIgnoreCase("GB2312") ? "GBK" : charSet;
			if (ui.getPreantUrl() != null && ui.getPreantUrl().contains("channel.jd.com/food.html")
					&& ui.getSortId() != 200) {
				System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD____" + charSet);
				charSet = "UTF-8";
			}

			InputStream uis = null;
			try {
				uis = uc.getInputStream();
			} catch (IOException e) {

				String dangdang = e.getMessage().replaceAll("\\d+", "下架");
				if (dangdang.equals("http://product.dangdang.com/下架.html")) {
					ui.setHtmlContent("获取不到源代码1" + e.getMessage().replaceAll("\\d+", "下架"));
					System.out.println(e.getMessage() + "______" + dangdang);
					return;
				}

				if (ui.getPatternUrlId() == 100003 && (ui.getSortId() == 68 || ui.getSortId() == 69)) {
					ui.setHtmlContent("--");
					return;
				}

				if (UrlSupervise.isFirst != 0) {
					throw e;
				} else {
					ui.setHtmlContent("您访问的网页不存在");
					return;
				}
			}
			InputStreamReader reader;
			StringBuffer htmlStr = new StringBuffer();
			// 检测返回的页面源码是否被压缩
			try {
				if (uc.getHeaderField("Content-Encoding") != null
						&& uc.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip"))
					reader = new InputStreamReader(new GZIPInputStream(uis), charSet);
				else
					reader = new InputStreamReader(uis, charSet);
				int readNum = 0;
				while (true) {
					int b = reader.read();
					if (b == -1 || readNum > MAX_READER_LENGTH)
						break;
					htmlStr.append((char) b);
					readNum++;
				}
				reader.close();

			} catch (EOFException eex) {
				htmlStr.append("");
				System.out.println("url内容解压缩出现问题__" + eex.getMessage());
			}
			ui.setHtmlContent(htmlStr.toString().replace("\r\n", "").replace("\n", "").replace("\r", "")
					.replaceAll("\\\\\\\\\\\\/", "/").replaceAll("\\\\\\\\\\\\\\\"", "\"").replaceAll("\\\\\"", "\"")
					.replaceAll("\\\\/", "\\/"));
		}

	}

	public static String amazonCookie = "session-id=462-4885254-8368118; path=/; domain=.amazon.cn; expires=Mon, 31-Dec-2035 16:00:01 GMT";

	public void getPageInfoaaa(Url ui) throws IOException, InterruptedException {
		URL url1 = new URL(ui.getUrl());
		boolean isLimit = true;
		for (UrlLimit ul : UrlLimitInit.urlLimitList) {
			if (ul.getUrlGroup().equals(ui.getPatternUrlId())) {
				for (String s : ul.getSortIdList()) {
					if (s.equals(ui.getSortId())) {
						isLimit = false;
						break;
					}
				}
			}
			if ((!isLimit) && (ul.getIsDns() == 0))
				isLimit = true;
			else if (!isLimit) {
				System.out.println("延迟加载：" + ui.getUrl());
			}
		}
		if (!isLimit) {
			HashMap<String, String> urlCookie = DnsManager.getURLStr(ui.getUrl(), true);
			String urlStr = (String) urlCookie.get("urlStr");
			System.out.println("延迟加载并且使用ip：" + urlStr);
			ui.setUrl(urlStr);
		}
		URL url = new URL(ui.getUrl());
		HttpURLConnection uc = null;
		uc = (HttpURLConnection) url.openConnection();
		uc.setConnectTimeout(GetWebPage.timeOut);
		uc.setDoOutput(true);
		uc.setUseCaches(false);
		uc.setRequestProperty("Host", url1.getHost());
		uc.setRequestProperty("Keep-Alive", "300");
		uc.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		uc.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		uc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		uc.setRequestProperty("Cache-Control", "max-age=0");
		uc.setRequestProperty("Connection", "keep-alive");
		uc.setRequestProperty("Upgrade-Insecure-Requests", "1");
		uc.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		uc.setRequestMethod("GET");

		String uiCookie = (String) GetWebPage.cookieMap.get(ui.getPatternUrlId());
		if (amazonCookie != null && !amazonCookie.equals("")) {
			ui.setCookie(amazonCookie);
		}
		if (ui.getCookie() != null) {
			uiCookie = ui.getCookie();
		}

		if ((uiCookie != null) && (isLimit)) {
			uc.setRequestProperty("Cookie", uiCookie);
		}
		uc.setReadTimeout(GetWebPage.timeOut);
		System.out.println(uc.getHeaderField("set-cookie"));
		amazonCookie = uc.getHeaderField("set-cookie");
		if (uc.getResponseCode() == 302) {
			for (Url302List u : GetWebPage.listUrl302) {
				if (ui.getPatternUrlId() != Integer.valueOf(u.getUrlGroup()).intValue())
					continue;
				String[] strs302 = u.getSortId().split(",");
				System.out.println(strs302[0]);
				for (String str : strs302) {
					if (ui.getSortId() == Integer.valueOf(str).intValue() && uc.getHeaderField("location") != null) {
						ui.setUrl(uc.getHeaderField("location"));
						System.out.println("302url:" + uc.getHeaderField("location"));
						getPageInfoaaa(ui);
						return;
					}
				}
			}
			throw new IOException("请求被跳转,断线重连");
		}
		if ((uc.getResponseCode() == 500) && (ui.getUrl().indexOf("pageadword") != -1)
				&& ((ui.getPatternUrlId() == 2) || (ui.getPatternUrlId() == 3))) {
			UrlSupervise.addParseUrlInfo(ui);
			return;
		}

		String charSet = "utf-8";
		String contentType = uc.getHeaderField("Content-Type");
		if ((contentType != null) && (!contentType.equals(""))) {
			String[] contentTypeArr = contentType.split(";");
			for (int i = 0; i < contentTypeArr.length; i++) {
				if (contentTypeArr[i].indexOf("charset=") != -1) {
					String[] charSetArr = contentTypeArr[i].split("=");
					charSet = charSetArr[1].trim();
					break;
				}
			}
		}
		if (charSet == null)
			charSet = ui.getChartSet();
		else
			ui.setChartSet(charSet);
		if (((ui.getPatternUrlId() == 2) || (ui.getPatternUrlId() == 3))
				&& (ui.getChartSet().toLowerCase().indexOf("gb") != -1) && (ui.getSortId() != 1)
				&& (UrlSupervise.isFirst == 1)) {
			charSet = "UTF-8";
			ui.setChartSet("UTF-8");
		} else if (((ui.getPatternUrlId() == 2) || (ui.getPatternUrlId() == 3))
				&& (ui.getChartSet().toLowerCase().indexOf("UTF-8") != -1) && (ui.getSortId() != 1)
				&& (UrlSupervise.isFirst == 1)) {
			charSet = "GBK";
			ui.setChartSet("GBK");
		}

		charSet = charSet.equalsIgnoreCase("GB2312") ? "GBK" : charSet;
		InputStream uis = null;
		try {
			uis = uc.getInputStream();
		} catch (IOException e) {
			if (UrlSupervise.isFirst != 0) {
				throw e;
			}
			ui.setHtmlContent("您访问的网页不存在");
			return;
		}

		StringBuffer htmlStr = new StringBuffer();
		try {
			InputStreamReader reader = null;
			if (uc.getHeaderField("Content-Encoding") != null) {
				if (uc.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip")) {
					reader = new InputStreamReader(new GZIPInputStream(uis), charSet);
				}
			} else {
				reader = new InputStreamReader(uis, charSet);
			}
			int readNum = 0;
			while (true) {
				if (reader == null) {
					ui.setHtmlContent("您访问的网页不存在");
					System.out.println("~~~~~~~访问数据失败");
					return;
				}
				int b = reader.read();
				if ((b == -1) || (readNum > 5000000L))
					break;
				htmlStr.append((char) b);
				readNum++;
			}
			reader.close();
		} catch (EOFException eex) {
			eex.printStackTrace();
		}
		ui.setHtmlContent(htmlStr.toString().replace("\r\n", "").replace("\n", "").replace("\r", ""));
	}

	// private static void trustAllHttpsCertificates() throws Exception {
	// javax.net.ssl.TrustManager[] trustAllCerts = new
	// javax.net.ssl.TrustManager[1];
	// javax.net.ssl.TrustManager tm = new miTM();
	// trustAllCerts[0] = tm;
	// javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
	// .getInstance("SSL");
	// sc.init(null, trustAllCerts, null);
	// javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
	// .getSocketFactory());
	// }

	static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

	static class MyAuthenticator extends Authenticator {
		private String user = "";
		private String password = "";

		public MyAuthenticator(String user, String password) {
			this.user = user;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password.toCharArray());
		}
	}

	public static HashMap<String,String> getPage(String url, String urlParent, String cookie, String userAgent,
			 String patternStr1, String group1, String patternStr2, String group2
			 , String type, String requestProperty) throws Exception {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pattern1", Base64.encode(patternStr1.getBytes())+"	"+Base64.encode(patternStr1.getBytes()).length());
		map.put("pattern2", Base64.encode(patternStr2.getBytes())+"	"+Base64.encode(patternStr2.getBytes()).length());
		String out = "";
		int a = 0;
		int b = 0;
		
		Url ui = new Url(url, "", 0);
		ui.setPreantUrl(urlParent);
		if(!cookie.equals("")){
			ui.setCookie(cookie);
		}
		(new GetWebPage().new GetPage()).getPageInfo(ui, userAgent, type, requestProperty);
		String HtmlStr = ui.getHtmlContent();
		//String HtmlStr = "<dt>心　　得：</dt><dd>感觉还可以，就是比~贵点，性价比还是可以的</dd>";
		//System.out.println("源码："+HtmlStr);
		
		String patternStr = patternStr1;
		Pattern testp = Pattern.compile(patternStr);
		Matcher m = testp.matcher(HtmlStr);
		while (m.find()) {
			//可能有多个
			//System.out.println(m.group(1) + m.group(2) + m.group(3));
			String groupStr1 = "";
			for (int j = 0; j < group1.split(",").length; j++) {
				groupStr1 = groupStr1 + m.group(Integer.parseInt(group1.split(",")[j]));
			}
			if(a==0){
				out = out + "一级：" + groupStr1;
			}else{
				out = out + "\n" + "一级：" + groupStr1;
			}
			a ++;
			//System.out.println("一级：" + groupStr1);
			
			String gP = patternStr2;

//			PatternMatcher pm = new PatternMatcher();
//			List<String[]> re = pm.find(gP, groupStr1);
//			for (String[] a : re) {
//				String groupStr2 = "";
//				if(group2.split(",").length > 1){
//					for (int k = 0; k < group2.split(",").length; k++) {
//						groupStr2 = groupStr2 + a[k];
//					}
//				}
//				System.out.println("二级：" + groupStr1);
//			}
			
			Pattern testp2 = Pattern.compile(gP);
			Matcher m2 = testp2.matcher(groupStr1);
			while (m2.find()) {
				String groupStr2 = "";
				for (int j = 0; j < group2.split(",").length; j++) {
					groupStr2 = groupStr2 + m2.group(Integer.parseInt(group2.split(",")[j]));
				}
				b ++;
				out = out + "\n" + "二级：" + groupStr2;
				//System.out.println("二级：" + groupStr2);
			}
		}
		out = out + "\n" + a + "	" + b;
		out = out + "\n" + "\n" + "源码：" + HtmlStr;
		map.put("out", out);
		return map;
	}
	public static void main(String[] args) throws Exception {
//		getPage("https://club.jd.com/review/1560298324-0-1-0.html","","","","<dt>心[^<]*?得：</dt>\\s*?<dd>(.*?)</dd>","0","<dd>(.*?)</dd>","1");
		Url ui = new Url(
				"https://www.amazon.cn/gp/product/B002ZLOR06/ref=ox_sc_mini_detail?ie=UTF8&psc=1&smid=A21M10NYN47JXS",
				"", 0);
		ui.setPreantUrl("https://goods.kaola.com/product/1994327.html");
		// ui.setCookie(
		// "session-id=458-4704148-5143131; ubid-acbcn=460-9790624-8803715;
		// x-wl-uid=1Cz731CHnfFvjKXO5nBb9J4sZZL8z8aVMf+gjyrq8tIkaf9OePQ9lyyfhOr7XVRr6EDT4kOTxa4s=;
		// session-token=\"qAnAze1/ZHgCqouPmHLGJJW4wfPfrxDDBX83NAOJJClp2MWJ8moTfbIm9WA9MLmnYo0AIVbfrJ9CzkbwnXE3pOXBKSo8wG0u8U69RIm3ZuCzRfsQ54WT13vAe+k/wXBUQIn1jCrYPhyHRap8u0d6sYR2X5Qkb7amMlLCR8hThZTRq6KfF1eJ+aaqecUXtstcFKBhPT0qErL6Hhw6uNNYGLe9BmrUcrzLQOPytJ0bLfDQ61kCEvX5MA==\";
		// csm-hit=tb:5XWE504VVC46GCPJAHTV+s-5XWE504VVC46GCPJAHTV|1543468202363&adb:adblk_yes;
		// session-id-time=2082729601l");

		System.out.println("~~~~~~~~~"
				+ ZipTools.unzip("UEsDBBQACAgIAA9LhE0AAAAAAAAAAAAAAAABAAAAMAMAUEsHCAAAAAACAAAAAAAAAA==;") + "___");
		for (int i = 0; i < 1; i++) {
			long start = System.currentTimeMillis();
			try {
				System.out.println("~~~~cookie______________" + ui.getCookie());
				(new GetWebPage().new GetPage()).getPageInfo(ui, "", "", "");
				String HtmlStr = ui.getHtmlContent();
				System.out.println(System.currentTimeMillis() - start + "~~~~~~~~~~~~`");
				System.out.println(HtmlStr);
				System.out.println(ui.getChartSet());
				start = System.nanoTime();// .replace(" ","").replace(" ", "")
				String patternStr = "title\":\"健康乳品冲饮榜([\\S\\s]*?)我的品质生活榜";
				patternStr = new String(Base64.decode("cGljIjoiKFteIl0qPyki"));

				System.out.println(
						"_______" + new String(Base64.decode("ImF0dHJpYnV0ZXMiLio/InZpcnR1YWxfZm9vZF9pZCI6XGQqP1x9")));
				Pattern testp = Pattern.compile(patternStr);

				System.out.println(Base64.encode("priceType\":\"(4-1)\",\"promotionPrice\":\"([^\"]+)\"".getBytes()));

				Matcher m = testp.matcher(HtmlStr);
				while (m.find()) {
					String groupStr = m.group(1);
					System.out.println("aaaaaaaaa=" + groupStr);
					System.out.println();
					String gP = new String(Base64.decode("cGljIjoiKFteIl0qPyki="));
					PatternMatcher pm = new PatternMatcher();
					List<String[]> re = pm.find(gP, groupStr);
					for (String[] a : re) {
						System.out.print(a[1] + "~~~~~~~");
					}

					String gP1 = "itemCat\":\"([^\"]*?)\"";

					List<String[]> re1 = pm.find(gP1, groupStr);
					for (String[] a : re1) {
						System.out.println(a[1]);
					}

					String gP2 = "content\":\"([^\"]*?)\"";

					List<String[]> re2 = pm.find(gP2, groupStr);
					for (String[] a : re2) {
						System.out.println(a[1]);
					}

					System.out.println();
					System.out.println(Base64.encode(patternStr.getBytes()));
					System.out.println(Base64.encode(gP.getBytes()));
					System.out.println(Base64.encode(gP1.getBytes()));
					System.out.println(Base64.encode(gP2.getBytes()));
				}
				System.out.println((System.nanoTime() - start) / 1000000 + "毫秒"); // 结束微秒
				System.out.println("------------------" + i);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// System.exit(0);
			}
			Thread.sleep(31100);
		}
		System.exit(0);
	}
}
