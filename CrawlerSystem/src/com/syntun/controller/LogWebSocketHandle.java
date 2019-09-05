package com.syntun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.HistoryLog;
import com.syntun.entity.Servers;
import com.syntun.service.HistoryLogService;
import com.syntun.service.ServersService;
import com.syntun.service.UserService;
import com.syntun.util.SpringContext;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;

@ServerEndpoint("/log")
public class LogWebSocketHandle {

    private Process process;
    private InputStream inputStream;

	/**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {

    }
    
     /**
      * 收到客户端消息后调用的方法
      * @param message 客户端发送过来的消息
      * @param session 可选的参数
      */
     @OnMessage
     public void onMessage(String message, Session session) {
        
    	String split[] = message.split(",");
  		Connection conn = null;
  		ch.ethz.ssh2.Session ss = null;
 		String ip = split[0];
 		String cmd = split[1];
 		ApplicationContext appCtx = SpringContext.getApplicationContext();
 		ServersService serversService = (ServersService)appCtx.getBean(ServersService.class);
 		DataSourceContextHolder.setDbType("ds_mop");
 		Servers s = serversService.findone(ip);
 		//保存到历史记录表
 		HistoryLogService historyLogService = (HistoryLogService)appCtx.getBean(HistoryLogService.class);
 		HistoryLog h = new HistoryLog();
 		h.setName(message);
 		h.setType("1");
 		historyLogService.insert(h);
// 		String usr = "root";
// 		String pwd = "Caiji@085&Sp_2018";
         try {
 			// 连接远程服务器
 			conn = new Connection(ip);
 			conn.connect();
 			// 使用用户名和密码登录
 			boolean b = conn.authenticateWithPassword(s.getName(), s.getPassword());
 			if (!b) {
 				throw new IOException("Authentication failed. 连接失败");
 			} else {
 				ss = conn.openSession(); // 打开会话
 				ss.execCommand(cmd); 
 				InputStream is = new StreamGobbler(ss.getStdout());
 				BufferedReader bs = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
 				while (true) {
 					String line = bs.readLine();
 					if (line == null) {
 						break;
 					} else {
// 						str += line + "\r\n";
 						session.getBasicRemote().sendText(line + "<br>");
 					}
 				}
 				bs.close();
 				ss.close();
 				conn.close();
 			}
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose(Session session) {
/*        try {
            if(session != null)
            	//session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}