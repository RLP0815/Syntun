package com.syntun.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.syntun.zother.CheckProductName;

public class CheckProductNameListener implements ServletContextListener{

	private CheckProductNameThread checkProductNameThread;  
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {  
		if (checkProductNameThread != null && checkProductNameThread.isInterrupted()) {  
			checkProductNameThread.interrupt();  
       }
   }  
	
	@Override
	public void contextInitialized(ServletContextEvent e) {  
       String str = null;  
       if (str == null && checkProductNameThread == null) {  
    	   checkProductNameThread = new CheckProductNameThread();  
    	   checkProductNameThread.start(); // servlet 上下文初始化时启动 socket
       } 

   }  

	public static void main(String[] args) {
		new CheckProductNameThread().start();
    	
	}

}  

/**
* 自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
*
* @author Champion.Wong
*
*/

class CheckProductNameThread extends Thread {  

	public void run() {  
		while (!this.isInterrupted()) {// 线程未中断执行循环
			Date day = new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			SimpleDateFormat dfh = new SimpleDateFormat("HH:mm:ss");
			final String dateStr = df.format(day);
			final String dateStrh = dfh.format(day);
			int hour = Integer.parseInt(dateStrh.split(":")[0]);
			
			try {  
				if(hour == 10){
					System.out.println("=====标准名称检查===时间："+dateStrh);
					Callable<String> call = new Callable<String>() {  
			            public String call(){  
			            	try { 
				            	//开始执行耗时操作  
			            		CheckProductName.getResult();
				                return "线程执行完成.";  
			            	} catch (Exception e) {  
			    				e.printStackTrace(); 
			    				try {
			    					String[] emails = {"liping.ren@syntun.com"};
			    					String text = "标准名称检查出错，请注意核查！ \n " + 
			    							"执行时间：" + dateStrh +" \n " +
			    							"程序执行错误：\n " + e.getMessage();
			    					//System.out.println(text);
			    					SyntunEmail.sendSimpleEmail(emails, "标准名称检查", text, "b", "text");
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
							String text = "标准名称检查超时，请注意核查！ \n " + 
									"检查时间：" + dateStr+" "+dateStrh+"\n " +
									"程序执行错误：\n " + ex.toString();
							//System.out.println(text);
							SyntunEmail.sendSimpleEmail(emails, "标准名称检查", text, "b", "text");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			        } 
			        // 关闭线程池  
			        exec.shutdown();  
			        try {
			        	Date dayA = new Date(); 
						String dateStrA = df.format(dayA);
						System.out.println("===标准名称检查完成==睡23小时===睡觉时间："+dateStrA+" "+dfh.format(dayA));
						Thread.sleep(60000 * 60 * 23); 
			        	
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
					
				}else{
					System.out.println("===标准名称检查完成==睡23小时===睡觉时间："+dateStr+" "+dfh.format(new Date()));
					Thread.sleep(60000 * 60 * 23); 
				}
			} catch (Exception e) {  
				e.printStackTrace(); 
				try {
					String[] emails = {"liping.ren@syntun.com"};
					String text = "标准名称检查出错，请注意核查！ \n " + 
							"检查时间：" + dateStr+" "+dateStrh+"\n " +
							"程序执行错误：\n " + e.getMessage();
					//System.out.println(text);
					SyntunEmail.sendSimpleEmail(emails, "标准名称检查", text, "b", "text");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
