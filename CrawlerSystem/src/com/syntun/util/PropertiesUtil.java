package com.syntun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 配置文件读取工具类
 */
public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static PropertiesUtil util = new PropertiesUtil();
	private static Properties prop = new Properties();
	private static InputStream is=null;
	public PropertiesUtil() {
		super();
	}
	/**
	 * 初始化配置文件工具类
	 * @param filename 文件名称
	 * @param isClassPath true为classspath路径，false为绝对路径
	 * @return
	 */
	public static PropertiesUtil  getInstance(String filename, boolean isClassPath) {
		try {
			if (isClassPath) {
				is = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
			} else {
				is = new FileInputStream(new File(filename));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return util;
	}
	/**
	 * 获取Property文件配置参数
	 * @param key
	 * @return
	 */
	public String getPropertyValue(String key) {
		try {
			prop.load(is);
			return prop.getProperty(key).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args) {
		PropertiesUtil util =PropertiesUtil.getInstance("./config/config.properties", true);
		System.out.println(util.getPropertyValue("ip"));
	}
}
