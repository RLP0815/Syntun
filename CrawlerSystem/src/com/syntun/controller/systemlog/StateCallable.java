package com.syntun.controller.systemlog;

import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;

import com.syntun.entity.Server;
import com.syntun.entity.Servers;
import com.syntun.service.ServersService;
import com.syntun.service.TAdminService;
import com.syntun.util.Shell;
import com.syntun.util.SpringContext;
/**
 * 启动所有服务器线程
 * Created by wangdong on 2019/2/14.
 */

public class StateCallable implements Callable<List<Server>> {

    /**当前是属于第几段线程**/
    private int pageIndex;
    
    private int size;
    
    private List<Server> list;

    public StateCallable(int pageIndex,List<Server> list,int size){
        this.pageIndex = pageIndex;
        this.list = list;
        this.size = size;
    }

    @Override
    public List<Server> call() throws Exception {
        System.err.println(String.format("pageIndex:%s size:%s",pageIndex,list.size()));
        if(null != list && list.size() >0){
            for(Server s: list){
                try {
/*                    RemoteShellExecutor executor = new RemoteShellExecutor(s.getIp(),s.getName(),s.getPassword());
					Map map = executor.exec(s.getStatepath(),size);
				    String exec = map.get("ret").toString();
				    String outStr = map.get("outStr").toString();*/
                	//System.out.println(s.getIp()+","+s1.getName()+","+s.getPassword());
            		String res = Shell.exec(s.getIp(),s.getName(),s.getPassword(), 22,s.getStatepath());
            		System.out.println(Thread.currentThread().getName()+":ip:"+s.getIp()+":"+res);
				    //脚本执行成功
			    	if(res.equals("0")){
			    		s.setState("0");//停止状态
			    	}else{
			    		s.setState("1");//启动状态
			    	}
                } catch (Exception e) {
                	e.printStackTrace();
                    s.setException(e.getMessage());
                }
            }
        }
        return list;
    }
}