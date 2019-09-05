package com.syntun.util;

import java.util.UUID;

/**
 * 名  称：UUIDGenerator
 * 描  述：UUID生成器
 */
public class UUIDGenerator {

	/**
	 * 去除"-"间隔符
	 */
	public static String getUUIDoffSpace() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获取UUID
	 */
	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		return s;
	}
	
	/**
	 * 根据参数number 获取number个UUID
	 * @param number
	 */
	public static String[] getUUID(int number) {
		if (number < 1) 
			return null;
		String[] ss = new String[number];
		for (int i = 0; i < ss.length; i++) {
			ss[i] = getUUIDoffSpace();
		}
		return ss;
	}

	public static void main(String[] args)
	{
		System.err.println(UUIDGenerator.getUUIDoffSpace());
	}
}
