package com.syntun.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.Server;
import com.syntun.entity.Servers;
import com.syntun.service.ServerService;
import com.syntun.service.ServersService;
import com.syntun.util.SpringContext;

@ServerEndpoint("/serverWebSocket")
public class ServerWebSocketHandle {


    private Process process;
    private InputStream inputStream;

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
/*        try {
	      	ApplicationContext appCtx = SpringContext.getApplicationContext();
	      	ServerService serverService = (ServerService)appCtx.getBean(ServerService.class);
	      	Map m = new HashMap();
			List<Server> serverInfo = serverService.findAll(m);
			ServersService serversService = (ServersService)appCtx.getBean(ServersService.class);
			List<Servers> s1 = serversService.findAll();
			if(serverInfo.size()>0){
				for(Server s:serverInfo){
					String ip = s.getIp();
					for(Servers sr:s1){
						if(ip.equals(sr.getIp())){
							s.setName(sr.getName());
							s.setPassword(sr.getPassword());
							break;
						}
					}
				}
		        long startTime = System.currentTimeMillis();
				for(Server s:serverInfo){
		            // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
					Thread.sleep(200);
		            TailLogThread thread = new TailLogThread(s, session);
		            thread.start();
				}
		        long endTime = System.currentTimeMillis();
		        System.out.println("单个服务耗时:"+ (endTime-startTime)+"ms");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
	      	ApplicationContext appCtx = SpringContext.getApplicationContext();
	      	ServerService serverService = (ServerService)appCtx.getBean(ServerService.class);
	      	Map m = new HashMap();
	      	if(!"0".equals(message)){
	      		m.put("classify", message);
	      	}
	      	DataSourceContextHolder.setDbType("ds_mop");
			List<Server> serverInfo = serverService.findAll(m);
			ServersService serversService = (ServersService)appCtx.getBean(ServersService.class);
			List<Servers> s1 = serversService.findAll();
			if(serverInfo.size()>0){
				for(Server s:serverInfo){
					String ip = s.getIp();
					for(Servers sr:s1){
						if(ip.equals(sr.getIp())){
							s.setName(sr.getName());
							s.setPassword(sr.getPassword());
							break;
						}
					}
				}
		        long startTime = System.currentTimeMillis();
				for(Server s:serverInfo){
		            // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
					Thread.sleep(100);
		            TailLogThread thread = new TailLogThread(s, session);
		            thread.start();
				}
		        long endTime = System.currentTimeMillis();
		        System.out.println("单个服务耗时:"+ (endTime-startTime)+"ms");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose() {
        try {
            if(inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(process != null)
            process.destroy();
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}