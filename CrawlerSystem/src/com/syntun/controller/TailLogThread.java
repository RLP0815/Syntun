package com.syntun.controller;

import javax.websocket.Session;

import com.syntun.entity.Server;
import com.syntun.util.Shell;

import net.sf.json.JSONObject;

public class TailLogThread extends Thread {

	 Server s;
     Session session;

    public TailLogThread(Server server, Session session) {
        this.s = server;
        this.session = session;
    }

    @Override
    public synchronized void run() {
        String line = "";
        try {
	    	JSONObject json = JSONObject.fromObject(s);//将java对象转换为json对象
	    	line = json.toString();//将json对象转换为字符串
            // 实时通过WebSocket发送给客户端，异步请求
            session.getAsyncRemote().sendText(line);
        	
/*    		String res = Shell.exec(s.getIp(),s.getName(),s.getPassword(), 22,s.getStatepath());
    		System.out.println(Thread.currentThread().getName()+":ip:"+s.getIp()+":"+res);
		    //脚本执行成功
	    	if(res.equals("0")){
	    		s.setState("0");//停止状态
	    	}else{
	    		s.setState("1");//启动状态
	    	}
	    	JSONObject json = JSONObject.fromObject(s);//将java对象转换为json对象
	    	line = json.toString();//将json对象转换为字符串
            // 实时通过WebSocket发送给客户端，异步请求
            session.getAsyncRemote().sendText(line);
	    	// 实时通过WebSocket发送给客户端，同步请求
            //session.getBasicRemote().sendText(line);
*/        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}