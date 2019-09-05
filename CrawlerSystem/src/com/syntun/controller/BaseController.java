package com.syntun.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.service.FilterUrlService;
import com.syntun.webget.GetWebPage;
import net.sf.json.JSONObject;

/**
 * 
 */
@Controller
@RequestMapping(value = "/baseController")
public class BaseController {
	@Resource(name = "filterUrlService")
    FilterUrlService filterUrlService;
	
	//final static String path = "D:/out.xls";
	final static String path = "/home/CrawlerSystem/excel/out.xls";
	public static void main(String[] args) {
		
	}

	@RequestMapping(value = "/exportTxt")
	public void exportTxt(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//下载文件
		try {
			// String path = ("C:/Users/rlp/Desktop/out.txt");
			File file = new File(path);
			String filename = file.getName();

			// 取得文件的扩展名ext
			//String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
			String ext = filename.substring(filename.lastIndexOf(".") + 1);

			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length()); // 设置返回的文件类型
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); // 得到向客户端输出二进制数据的对象
			// 根据扩展名声称客户端浏览器mime类型
			if (ext.equals("xls"))
				response.setContentType("application/msexcel");
			else
				response.setContentType("application/octet-stream"); // 设置返回的文件类型
			toClient.write(buffer); // 输出数据
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}
	/*
	 * 生成文件
	 * 参数为文件中写的内容
	 */
	public static void outPutTxt(String out){

		String write = out;
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		// 生成文件
		try {
			outSTr = new FileOutputStream(new File(path));
			Buff = new BufferedOutputStream(outSTr);
			Buff.write(write.getBytes("UTF-8"));
			Buff.flush();
			Buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//Buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/getUrl")
	public void getUrl(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String url = request.getParameter("url");
		String urlParent = request.getParameter("urlParent");
		String cookie = request.getParameter("cookie");
		String userAgent = request.getParameter("userAgent");
		String patternStr1 = request.getParameter("patternStr1");
		String group1 = request.getParameter("group1").replace("，", ",");
		String patternStr2 = request.getParameter("patternStr2");
		String group2 = request.getParameter("group2").replace("，", ",");
		String type = request.getParameter("type");
		String requestProperty = request.getParameter("requestProperty");
		
		JSONObject resObj = new JSONObject();
		try {
			String urlKey1=null,urlValue1=null,urlKey2=null,urlValue2=null,urlKey3=null,urlValue3=null,urlKey4=null,urlValue4=null,urlKey5=null,urlValue5=null;
			String urlKey6=null,urlValue6=null,urlKey7=null,urlValue7=null,urlKey8=null,urlValue8=null,urlKey9=null,urlValue9=null,urlKey10=null,urlValue10=null;
			if(requestProperty!=null && !requestProperty.equals("")){
				for(int i=0;i < requestProperty.split("#_#").length; i++){
					if(i == 0){
						urlKey1 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue1 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 1){
						urlKey2 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue2 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 2){
						urlKey3 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue3 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 3){
						urlKey4 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue4 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 4){
						urlKey5 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue5 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 5){
						urlKey6 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue6 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 6){
						urlKey7 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue7 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 7){
						urlKey8 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue8 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 8){
						urlKey9 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue9 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
					if(i == 9){
						urlKey10 = requestProperty.split("#_#")[i].split("@_@")[0];
						urlValue10 = requestProperty.split("#_#")[i].split("@_@")[1];
					}
				}
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("urlKey1",urlKey1);
			params.put("urlValue1",urlValue1);
			params.put("urlKey2",urlKey2);
			params.put("urlValue2",urlValue2);
			params.put("urlKey3",urlKey3);
			params.put("urlValue3",urlValue3);
			params.put("urlKey4",urlKey4);
			params.put("urlValue4",urlValue4);
			params.put("urlKey5",urlKey5);
			params.put("urlValue5",urlValue5);
			params.put("urlKey6",urlKey6);
			params.put("urlValue6",urlValue6);
			params.put("urlKey7",urlKey7);
			params.put("urlValue7",urlValue7);
			params.put("urlKey8",urlKey8);
			params.put("urlValue8",urlValue8);
			params.put("urlKey9",urlKey9);
			params.put("urlValue9",urlValue9);
			params.put("urlKey10",urlKey10);
			params.put("urlValue10",urlValue10);
			DataSourceContextHolder.setDbType("ds_mop");  
			//int num = filterUrlService.addKeyValueRecord(params);
			
			HashMap<String,String> map = GetWebPage.getPage(url,urlParent,cookie,userAgent,patternStr1,group1,patternStr2,group2,type,requestProperty);
			resObj.put("code", 0);
			resObj.put("msg", "数据抓取成功！");
			resObj.put("pattern1", map.get("pattern1"));
			resObj.put("pattern2", map.get("pattern2"));
			resObj.put("out", map.get("out"));
		} catch (Exception e) {
			resObj.put("code", 1);
			resObj.put("msg", e.toString());
		}
		PrintWriter out=response.getWriter();
		out.println(resObj);
		out.flush();
		out.close();
	}
	
}
