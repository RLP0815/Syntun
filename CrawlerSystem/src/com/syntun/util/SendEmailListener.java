package com.syntun.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.syntun.entity.TableCountList;
import com.syntun.etl.tools.SyntunDate;
import com.syntun.service.TableCountListService;

public class SendEmailListener implements ServletContextListener{

	private SendEmailThread sendEmailThread;  
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {  
		if (sendEmailThread != null && sendEmailThread.isInterrupted()) {  
			sendEmailThread.interrupt();  
       } 
   }  
	
	@Override
	public void contextInitialized(ServletContextEvent e) {  
       String str = null;  
       if (str == null && sendEmailThread == null) {  
    	   sendEmailThread = new SendEmailThread();  
    	   sendEmailThread.start(); // servlet 上下文初始化时启动 socket
       }  

   }  

	public static void main(String[] args) {
		new SendEmailThread().start();
    	
	}

}  

/**
* 自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
*
* @author Champion.Wong
*
*/

class SendEmailThread extends Thread {  

	public void run() {
		int i = 0;
		while (!this.isInterrupted()) {// 线程未中断执行循环
			Date day = new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			SimpleDateFormat dfh = new SimpleDateFormat("HH:mm:ss");
			final String dateStr = df.format(day);
			//System.out.println(dfh.format(day));
			
			int hour = Integer.parseInt(dfh.format(day).split(":")[0]);
			int min = Integer.parseInt(dfh.format(day).split(":")[1]);
			int second = Integer.parseInt(dfh.format(day).split(":")[2]);
			
			final String hour1 = hour+"";
			
			try {  
				if(i==0 && hour==13){
					System.out.println("=====采集数据统计邮件发送第一次启动睡1分钟===睡觉时间："+dateStr+" "+hour+":"+ min+":"+second);
					i++;
					Thread.sleep(60000); //每隔一分钟
				}
				if(hour == 13){
					System.out.println("=====采集数据统计===发送时间："+dateStr+" "+hour);
					Callable<String> call = new Callable<String>() {  
						TableCountListService tableCountListService = (TableCountListService) SpringContextUtil.getBean("tableCountListService");
						
						public String call(){  
			            	try { 
				            	//开始执行耗时操作  
			            		String getDateStart = "'";
			            		String content = "<table border='1'><tr><th>数据库名</th><th>表名</th>";
			            		List<String> list = SyntunDate.getInterDays(SyntunDate.addDate(dateStr, -5), SyntunDate.addDate(dateStr, -1));
			            		for (String date : list) {
			            			getDateStart = getDateStart + date + "','";
			            			content = content + "<th>" + date + "</th>";
			            		}
			            		getDateStart = getDateStart + "'";
			            		content = content + "</tr>";
			            		
			            		HashMap<String, Object> params = new HashMap<String, Object>();
			            		params.put("dataBase",null);
			            		params.put("tableName",null);
			            		params.put("getDate",getDateStart);
			            		
			            		List<TableCountList> result = tableCountListService.getEmailList(params);
			            		HashMap<String, HashMap<String, Integer>> mapMap = new HashMap<String, HashMap<String, Integer>>();
			            		for (TableCountList tableData : result) {
			            			HashMap<String, Integer> map;
			            			String key = tableData.getDataBase()+"&"+tableData.getTableName();
			            			if(mapMap.containsKey(key)){
			            				map = mapMap.get(key);
			            			}else{
			            				map = new HashMap<String, Integer>();
			            			}
			            			map.put(tableData.getGetDate(), tableData.getCountNum());
			            			mapMap.put(key, map);
			            		}
			            		for (String key : mapMap.keySet()) { 
			            			content = content + "<tr><td>"+key.split("&")[0]+"</td><td>"+key.split("&")[1]+"</td>";
			            			HashMap<String, Integer> map = mapMap.get(key);
			            			for (String date : list) {
			            				int count = map.get(date)==null?0:map.get(date);
			            				content = content + "<td>" + count + "</td>";
			            			}
			            			content = content + "</tr>";
			            		} 
			            		content = content + "</table>";
			            		
			            		String[] emails = {"liping.ren@syntun.com"};
			            		if(WebUtil.getLocalIP().equals("192.168.0.132")){
			            			emails[0] = "haitao.pei@syntun.com";
			            		}
			            		SyntunEmail.sendSimpleEmail(emails, "采集数据统计", content, "b", "html");
			            		return "线程执行完成."; 
			            	} catch (Exception e) {  
			    				e.printStackTrace(); 
			    				try {
			    					//String[] emails = {"xiaoran.dai@syntun.com", "liping.ren@syntun.com"};
			    					String[] emails = {"liping.ren@syntun.com"};
			    					String text = "采集数据统计邮件发送出错，请注意核查！ \n " + 
			    							"发送时间：" + dateStr+" "+hour1+"\n " +
			    							"程序执行错误：\n " + e.getMessage();
			    					//System.out.println(text);
			    					SyntunEmail.sendSimpleEmail(emails, "采集数据统计邮件发送出错", text, "b", "text");
			    				} catch (Exception e1) {
			    					e1.printStackTrace();
			    				}
			    				return "错误"; 
			    			} 
			            }  
			        };  
			        ExecutorService exec = Executors.newFixedThreadPool(1);  
			        Future<String> future = exec.submit(call);  
			        
			        try {  
			        	String obj = future.get(1000 * 60 * 25, TimeUnit.MILLISECONDS); 
			            System.out.println("任务成功返回:" + obj); 
			        } catch (TimeoutException ex) {  
			            System.out.println("处理超时啦....");  
			            System.out.println(ex.toString());
			            try {
							String[] emails = {"liping.ren@syntun.com"};
							String text = "采集数据统计邮件发送超时，请注意核查！ \n " + 
									"执行时间段数据：" + dateStr+" "+hour+"\n " +
									"程序执行错误：\n " + ex.toString();
							//System.out.println(text);
							SyntunEmail.sendSimpleEmail(emails, "采集数据统计邮件发送超时", text, "b", "text");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			        } 
			        // 关闭线程池  
			        exec.shutdown();  
			        try {
			        	Date dayA = new Date(); 
						String dateStrA = df.format(dayA);
						int hourA = Integer.parseInt(dfh.format(dayA).split(":")[0]);
						int minA = Integer.parseInt(dfh.format(dayA).split(":")[1]);
						int secondA = Integer.parseInt(dfh.format(dayA).split(":")[2]);
						System.out.println("===采集数据统计邮件发送完成==睡23个小时===睡觉时间："+dateStrA+" "+hourA+":"+ minA+":"+secondA);
						Thread.sleep(60000*60*23); //每隔23个小时
			        	
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}else{
					System.out.println(WebUtil.getLocalIP());
					System.out.println("=====采集数据统计邮件发送睡5分钟===睡觉时间："+dateStr+" "+hour+":"+ min+":"+second);
					Thread.sleep(60000*5); //每隔五分钟
				}
			} catch (Exception e) {  
				try {
					String mins = min+"";
					String seconds = second+"";
					String[] emails = {"liping.ren@syntun.com"};
					//String[] emails = {"liping.ren@syntun.com"};
					String text = "采集数据统计邮件发送出错，请注意核查！ \n " + 
							"发送数据时间：" + dateStr+" "+hour+":" +mins+":"+seconds+" \n " +
							"程序执行错误：\n " + e.getMessage();
					//System.out.println(text);
					SyntunEmail.sendSimpleEmail(emails, "采集数据统计邮件发送出错", text, "b", "text");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace(); 
			}
		}
	}
}
