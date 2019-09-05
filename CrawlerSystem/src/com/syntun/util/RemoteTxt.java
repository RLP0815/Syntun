package com.syntun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 读取和修改服务器文件
 * 
 * @author wangdong 2019-02-21
 */
public class RemoteTxt {

	static PropertiesUtil util = PropertiesUtil.getInstance("./config/config.properties", true);
	public static String ip = util.getPropertyValue("ip");
	public static String usr = util.getPropertyValue("name");
	public static String pwd = util.getPropertyValue("password");
	public static String directory = util.getPropertyValue("directory");// 日志文件存放的路径
	public static String directoryback = util.getPropertyValue("directoryback");// 日志文件备份存放的路径

	public static void main(String[] args) throws UnsupportedEncodingException {
		// writeTxt("1\n" +
		// "2\n" +
		// "3\n" +
		// "4\n" +
		// "5");
		// System.out.println(getEncoding(readTxt()));
		
		//System.out.println(readFileCommand("tail -f /home/test/log/log_test.txt"+));
/*		String str = "/home/test1.txt";
		String[] sp = str.split("\\.");
		String backPath = sp[0]+"back."+sp[1];
		System.out.println(backPath);*/
		
/*		int[] str = {32,43,23,13,5,55};
		quickSort(str, 0, str.length-1);
		System.out.println(Arrays.toString(str));*/
		
/*		String str = "nohup /usr/java/jdk1.8.0_181/bin/java  -Xmx8024M -jar /home/wgdata/wgdata.jar > log/log_wgdata.txt 2>&1 &";
		if(str.startsWith("cd")){
			String a = str.substring(3, str.length());
			System.out.println(a);
		}
		if(str.startsWith("nohup")){
			String[] a = str.split(">");
			System.out.println(a[1].trim().substring(0, a[1].length()-2));
		}*/
		String strData = RemoteTxt.getLogTxt("192.168.0.48","root","SYNTUN@syntun","cat /home/server/start.sh");
		System.out.println(strData);
	}
	   public static void quickSort(int[] arr, int low, int high) {
	        // low,high 为每次处理数组时的首、尾元素索引

	        //当low==high是表示该序列只有一个元素，不必排序了
	        if (low >= high) {
	            return;
	        }
	        // 选出哨兵元素和基准元素。这里左边的哨兵元素也是基准元素
	        int i = low, j = high, base = arr[low];
	        while (i < j) {
	            //右边哨兵从后向前找
	            while (arr[j] >= base && i < j) {
	                j--;
	            }
	            //左边哨兵从前向后找
	            while (arr[i] <= base && i < j) {
	                i++;
	            }
	            swap(arr,i,j);  //交换元素
	        }
	        swap(arr,low,j);  //基准元素与右哨兵交换
	        
	        //递归调用，排序左子集合和右子集合
	        quickSort(arr,low,j-1);  
	        quickSort(arr,j+1,high);

	    }

	    private static void swap(int[] arr, int i, int j) {
	        int tmp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = tmp;
	    }

	/**
	 * > 覆盖追加写数据 >>是追加写数据
	 * 
	 * @param str
	 * @return
	 */
	public static int writeTxt(String str,String ip,String usr,String pwd,String directory) {
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		int i = 0;
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {
				ss = conn.openSession(); // 打开会话
				//String encodeSet = "export LC_CTYPE=en_US.UTF-8;";  
				ss.execCommand("echo \"" + str + "\" > " + directory);
				ss.close();
				conn.close();
				i = 1;
			}
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 读数据
	 * 
	 * @return
	 */
	public static String readTxt(String ip,String usr,String pwd,String cmd) {
		// 创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		// List<String> dataList = new ArrayList<String>(); //数据
		String str = "";
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {

				// 读取文件内容，并复制给UserInfo对象
				ss = conn.openSession(); // 打开会话
				ss.execCommand(cmd); // 读取对应目录下的对应文件
				InputStream is = new StreamGobbler(ss.getStdout());
				BufferedReader bs = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				while (true) {
					String line = bs.readLine();
					if (line == null) {
						break;
					} else {
						// 输出数据
						// System.out.println(line);
						// dataList.add(line);
						str += line + "\r\n";
					}
				}
				bs.close();
				ss.close();
				conn.close();
			}
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 备份数据
	 * 
	 * @return
	 */
	public static int backups(String ip,String usr,String pwd,String directory,String directoryback) {
		// 创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		int i = 0;
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {
				ss = conn.openSession(); // 打开会话
				ss.execCommand("cp " + directory + " " + directoryback);
				ss.close();
				conn.close();
				i = 1;
			}
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 查看字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) { 
		String encode = "UTF-8"; 
		try { 
		if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
		String s = encode; 
		return s; //是的话，返回“GB2312“，以下代码同理
		} 
		} catch (Exception exception) { 
		} 
		encode = "GB2312"; 
		try { 
		if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
		String s1 = encode; 
		return s1; 
		} 
		} catch (Exception exception1) { 
		} 
		encode = "ISO-8859-1"; 
		try { 
		if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
		String s2 = encode; 
		return s2; 
		} 
		} catch (Exception exception2) { 
		} 
		encode = "GBK"; 
		try { 
		if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
		String s3 = encode; 
		return s3; 
		} 
		} catch (Exception exception3) { 
		} 
		return ""; //如果都不是，说明输入的内容不属于常见的编码格式。
	}
	
	/**
	 * 读取文件命令
	 * @return
	 */
	public static String readFileCommand(String command,String ip,String usr,String pwd) {
		// 创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		// List<String> dataList = new ArrayList<String>(); //数据
		String str = "";
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {

				// 读取文件内容，并复制给UserInfo对象
				ss = conn.openSession(); // 打开会话
				ss.execCommand(command); // 读取对应目录下的对应文件
				InputStream is = new StreamGobbler(ss.getStdout());
				BufferedReader bs = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				while (true) {
					String line = bs.readLine();
					if (StringUtils.isBlank(line)) {
						break;
					} else {
						// 输出数据
						// System.out.println(line);
						// dataList.add(line);
						str += line + "\r\n";
					}
				}
				bs.close();
				ss.close();
				conn.close();
			}
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * > 覆盖追加写数据 >>是追加写数据
	 * 
	 * @param str
	 * @return
	 */
	public static int writeFileCommand(String str,String ip,String usr,String pwd) {
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		int i = 0;
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {
				ss = conn.openSession(); // 打开会话
				ss.execCommand(str);
				ss.close();
				conn.close();
				i = 1;
			}
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 获取log服务日志路径
	 */
	public static String getLogTxt(String ip,String usr,String pwd,String cmd) {
		// 创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		// List<String> dataList = new ArrayList<String>(); //数据
		String str = "";
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {

				// 读取文件内容，并复制给UserInfo对象
				ss = conn.openSession(); // 打开会话
				ss.execCommand(cmd); // 读取对应目录下的对应文件
				InputStream is = new StreamGobbler(ss.getStdout());
				BufferedReader bs = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				while (true) {
					String line = bs.readLine();
					if (line == null) {
						break;
					} else {
						// 输出数据
						// System.out.println(line);
						// dataList.add(line);
						//str += line + "\r\n";
						if(line.startsWith("cd")){
							str += line.substring(3, line.length()).trim();
						}
						if(line.startsWith("nohup")){
							if(line.contains(">>")){
								String[] a = line.split(">>");
								str += a[1].trim().substring(0, a[1].length()-2).trim();
							}else{
								String[] a = line.split(">");
								str += a[1].trim().substring(0, a[1].length()-2).trim();
							}
						}
					}
				}
				bs.close();
				ss.close();
				conn.close();
			}
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 执行命令
	 * @return
	 */
	public static String executeCommand(String command,String ip,String usr,String pwd) {
		// 创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
		// 声明连接对象
		Connection conn = null;
		Session ss = null;

		String str = "";
		try {
			// 连接远程服务器
			conn = new Connection(ip);
			conn.connect();
			// 使用用户名和密码登录
			boolean b = conn.authenticateWithPassword(usr, pwd);
			if (!b) {
				throw new IOException("Authentication failed. 连接失败");
			} else {

				// 提交命令
				ss = conn.openSession(); // 打开会话
				ss.execCommand(command); // 读取对应目录下的对应文件
				str = "0";
				// 读取执行日志
//				InputStream is = new StreamGobbler(ss.getStdout());
//				BufferedReader bs = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//				while (true) {
//					String line = bs.readLine();
//					if (StringUtils.isBlank(line)) {
//						break;
//					} else {
//						// 输出数据
//						// System.out.println(line);
//						// dataList.add(line);
//						str += line + "\r\n";
//					}
//				}
//				bs.close();
				ss.close();
				conn.close();
			}
		} catch (IOException e) {
			str = "1";
			System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
			e.printStackTrace();
		}
		return str;
	}
}
