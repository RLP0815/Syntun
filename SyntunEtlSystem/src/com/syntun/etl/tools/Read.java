package com.syntun.etl.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/*
 * 读取服务器文件
 */
public class Read {
	public static void main(String[] args) {
		readTxtFile("/data/comment_user_xf/000000_0");
	}
	public static void readTxtFile(String filePath){
		   
        try {
                String encoding="utf-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    int i = 0;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	System.out.println(lineTxt.split("\\\001")[0]);
                    	i++;
                    	if(i == 100){
                    		return;
                    	}
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
}
