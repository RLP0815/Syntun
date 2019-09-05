package com.syntun.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.syntun.etl.tools.BaseDao;
import com.syntun.inspect.HalfPriceResult161;

public class SkyscopeListener161 implements ServletContextListener{

	private SkyscopeThread161 skyscopeThread;  
	private SkyscopeDeleteThread161 skyscopeDeleteThread;
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {  
		if (skyscopeThread != null && skyscopeThread.isInterrupted()) {  
			skyscopeThread.interrupt();  
       } 
		if (skyscopeDeleteThread != null && skyscopeDeleteThread.isInterrupted()) {  
			skyscopeDeleteThread.interrupt();  
       } 
   }  
	
	@Override
	public void contextInitialized(ServletContextEvent e) {  
       String str = null;  
       if (str == null && skyscopeThread == null) {  
    	   skyscopeThread = new SkyscopeThread161();  
    	   skyscopeThread.start(); // servlet 上下文初始化时启动 socket
       }  
       if (str == null && skyscopeDeleteThread == null) {  
    	   skyscopeDeleteThread = new SkyscopeDeleteThread161();  
    	   skyscopeDeleteThread.start(); // servlet 上下文初始化时启动 socket
       }  

   }  

	public static void main(String[] args) {
		new SkyscopeThread161().start();
		new SkyscopeDeleteThread161().start();
    	
	}

}  

/**
* 自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
*
* @author Champion.Wong
*
*/

class SkyscopeThread161 extends Thread {  

	public void run() {  
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
			final String min1 = (min>25 && min<30)?"15":"45";
			
			try {  
				if((min>=27 && min<=30) || (min>=57 && min<=59)){
					if(min>=27 && min<=30){
						min = 15;
					}
					if(min>=57 && min<=59){
						min = 45;
					}
					System.out.println("=====补数和巡检程序执行===数据时间："+dateStr+" "+hour+":"+ min+":00");
					//HalfPriceResult.HalfResult(dateStr, hour+"", min+"", "");
					
					Callable<String> call = new Callable<String>() {  
			            public String call(){  
			            	try { 
				            	//开始执行耗时操作  
				            	HalfPriceResult161.HalfResult(dateStr, hour1, min1, "");
				                return "线程执行完成.";  
			            	} catch (Exception e) {  
			    				e.printStackTrace(); 
			    				try {
			    					String mins = (min1+"").equals("15")?"00":"30";
			    					//String[] emails = {"xiaoran.dai@syntun.com", "liping.ren@syntun.com"};
			    					String[] emails = {"liping.ren@syntun.com"};
			    					String text = "巡检流程执行出错，请注意核查！ \n " + 
			    							"执行时间段数据：" + dateStr+" "+hour1+":" +mins+":00 \n " +
			    							"程序执行错误：\n " + e.getMessage();
			    					//System.out.println(text);
			    					SyntunEmail.sendSimpleEmail(emails, "巡检执行出错", text, "b", "text");
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
							String mins = (min+"").equals("15")?"00":"30";
							String[] emails = {"liping.ren@syntun.com"};
							String text = "巡检流程执行超时，请注意核查！ \n " + 
									"执行时间段数据：" + dateStr+" "+hour+":" +mins+":00 \n " +
									"程序执行错误：\n " + ex.toString();
							//System.out.println(text);
							SyntunEmail.sendSimpleEmail(emails, "巡检执行超时", text, "b", "text");
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
						System.out.println("===巡检执行完成==睡三分钟===睡觉时间："+dateStrA+" "+hourA+":"+ minA+":"+secondA);
						Thread.sleep(180000); //每隔三分钟
			        	
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
					
				}else{
					System.out.println("=====巡检程序睡三分钟===睡觉时间："+dateStr+" "+hour+":"+ min+":"+second);
					Thread.sleep(180000); //每隔三分钟
				}
			} catch (Exception e) {  
				e.printStackTrace(); 
				try {
					String mins = (min+"").equals("15")?"00":"30";
					String[] emails = {"xiaoran.dai@syntun.com", "liping.ren@syntun.com"};
					//String[] emails = {"liping.ren@syntun.com"};
					String text = "巡检流程执行出错，请注意核查！ \n " + 
							"执行时间段数据：" + dateStr+" "+hour+":" +mins+":00 \n " +
							"程序执行错误：\n " + e.getMessage();
					//System.out.println(text);
					SyntunEmail.sendSimpleEmail(emails, "巡检执行出错", text, "b", "text");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}  
		}  
	}  
} 

class SkyscopeDeleteThread161 extends Thread {  

	public void run() {  
		while (!this.isInterrupted()) {// 线程未中断执行循环
			Date day = new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			SimpleDateFormat dfh = new SimpleDateFormat("HH:mm:ss");
			final String dateStr = df.format(day);
			
			int hour = Integer.parseInt(dfh.format(day).split(":")[0]);
			int min = Integer.parseInt(dfh.format(day).split(":")[1]);
			int second = Integer.parseInt(dfh.format(day).split(":")[2]);
			  
			if(hour == 23){
				
				System.out.println("=====中间结果备份===备份这个时间之前的所有数据："+dateStr);
				
				Callable<String> call = new Callable<String>() {  
		            public String call(){  
		            	try { 
		            		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd"); 
		            		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			            	String dateStr1 = df1.format(new Date(df2.parse(dateStr).getTime() - (long)3 * 24 * 60 * 60 * 1000));
			            	String dateStr2 = df1.format(new Date(df2.parse(dateStr).getTime()));
//			            	// 删除价格结果
//							BaseDaoSqlServer.deleteTbaleData("[skyscope].[dbo].[PRICE_PRODUCT_LIST_half_copy]", dateStr1, dateStr2);
//							// 删除促销结果
//							BaseDaoSqlServer.deleteTbaleData("[skyscope].[dbo].[PRICE_PROMOTION_INFO_half_copy]", dateStr1, dateStr2);
							// 删除价格结果
			            	BaseDao.deleteTbaleData("syntun_v2.PRICE_PRODUCT_LIST_HALF", dateStr1, dateStr2);
							// 删除促销结果
			            	BaseDao.deleteTbaleData("syntun_v2.PRICE_PROMOTION_INFO_half", dateStr1, dateStr2);
							
			                return "删除执行完成.";  
		            	} catch (Exception e) {  
		    				e.printStackTrace(); 
		    				try {
		    					String[] emails = {"liping.ren@syntun.com"};
		    					String text = "巡检中间表删除出错，请注意核查！ \n " + 
		    							"程序执行错误：\n " + e.getMessage();
		    					//System.out.println(text);
		    					SyntunEmail.sendSimpleEmail(emails, "巡检执行出错", text, "b", "text");
		    				} catch (Exception e1) {
		    					e1.printStackTrace();
		    				}
		    				return "错误"; 
		    			} 
		            	
		            }  
		        };  
		        ExecutorService exec = Executors.newFixedThreadPool(1);  
		       // ExecutorService exec1 = Executors.newSingleThreadExecutor();
		        Future<String> future = exec.submit(call);  
		        
		        try {  
		        	String obj = future.get(1000 * 60, TimeUnit.MILLISECONDS);	//执行时间一分钟
		            System.out.println("任务成功返回:" + obj);  
		        } catch (TimeoutException ex) {  
		            System.out.println("删除超时啦....");  
		            System.out.println(ex.toString());
		            try {
						String[] emails = {"liping.ren@syntun.com"};
						String text = "巡检中间表删除超时，请注意核查！ \n " + 
								"程序执行错误：\n " + ex.toString();
						//System.out.println(text);
						SyntunEmail.sendSimpleEmail(emails, "巡检中间表删除超时", text, "b", "text");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		        } catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} 
		        // 关闭线程池  
		        exec.shutdown();  
		        try {
		        	Date dayA = new Date(); 
					String dateStrA = df.format(dayA);
					int hourA = Integer.parseInt(dfh.format(dayA).split(":")[0]);
					int minA = Integer.parseInt(dfh.format(dayA).split(":")[1]);
					int secondA = Integer.parseInt(dfh.format(dayA).split(":")[2]);
					System.out.println("=====删除执行完毕，睡三天===睡觉时间："+dateStrA+" "+hourA+":"+ minA+":"+secondA);
					Thread.sleep(1000*60*60*24*3);//睡三天
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				
			}else{
				try {
					System.out.println("=====删除程序睡1小时===睡觉时间："+dateStr+" "+hour+":"+ min+":"+second);
					Thread.sleep(1000*60*60);//每隔1个小时
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
       }  
   }  
} 
