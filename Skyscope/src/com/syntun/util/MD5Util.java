package com.syntun.util;

import java.security.MessageDigest;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Util {
	public static void main(String[] args) {  
		/*
		 * 这里设置成username为salt，进行md5加密。
		 * 那么在用户注册的时候应该把密码进行加密。可以用Md5PasswordEncoder这个类，是springsecurity内置的一个类。
		 */
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();  
        System.out.println( md5.encodePassword("123456", "kane@syntun")); 
		
    }  
   
   //生成MD5  
    public static String getMD5(String message) {  
        String md5 = "";  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象  
            byte[] messageByte = message.getBytes("UTF-8");  
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位  
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return md5;  
    }  
   
     // 二进制转十六进制  
    public static String bytesToHex(byte[] bytes) {  
        StringBuffer hexStr = new StringBuffer();  
        int num;  
        for (int i = 0; i < bytes.length; i++) {  
            num = bytes[i];  
             if(num < 0) {  
                 num += 256;  
            }  
            if(num < 16){  
                hexStr.append("0");  
            }  
            hexStr.append(Integer.toHexString(num));  
        }  
        return hexStr.toString().toUpperCase();  
    } 
}
