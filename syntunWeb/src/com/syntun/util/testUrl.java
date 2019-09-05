package com.syntun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testUrl {
	public static void main(String[] args) throws Exception{
		Date day = new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println("程序执行开始时间："+df.format(day));
		URL url = new URL(
				"http://api.syntun.com.cn/patrol/violation/price/list?page=-1&&access_token=dd62af8d1c898f8afc38c92acd29e895&user_id=244&start_time=1560907800&end_time=1560907800");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.connect();
		//InputStream inputStream = urlConnection.getInputStream();
		//String responseStr = ConvertToString(inputStream);
		//System.out.println(responseStr);
		Date dayEnd = new Date();    
		System.out.println("程序执行开始时间："+df.format(dayEnd));
		
		System.out.println("耗时："+(dayEnd.getTime() - day.getTime())+"毫秒");

	}

	public static String ConvertToString(InputStream inputStream) {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStreamReader.close();
				inputStream.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
}