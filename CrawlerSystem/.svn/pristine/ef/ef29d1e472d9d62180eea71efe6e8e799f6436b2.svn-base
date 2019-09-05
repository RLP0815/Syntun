package com.syntun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.syntun.dao.JiexisuduDao;
import com.syntun.dao.ServerDao;
import com.syntun.dao.ServersDao;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.Jiexisudu;
import com.syntun.entity.Server;
import com.syntun.entity.Servers;
import com.syntun.service.ServerService;
import com.syntun.util.DateUtils;
import com.syntun.util.RemoteShellExecutor;
import com.syntun.util.Shell;
import com.syntun.util.SyntunEmail;

/**
 * 
 */
@Service("serverService")
public class ServerServiceImpl implements ServerService{

    @Resource(name = "serverDao")
    private ServerDao serverDao;
    
    @Resource(name = "jiexisuduDao")
    private JiexisuduDao jiexisuduDao;
    
    @Resource(name = "serversDao")
    private ServersDao serversDao;

	@Override
	public List<Server> findAll(Map map) {
		return serverDao.findAll(map);
	}

	@Override
	public List<Server> findAllDel(Map map) {
		return serverDao.findAllDel(map);
	}

	@Override
	public int insert(Server s) {
		return serverDao.insert(s);
	}

	@Override
	public int update(Server s) {
		return serverDao.update(s);
	}

	@Override
	public Server findone(int id) {
		return serverDao.findone(id);
	}
    
    /**
     *查询所有服务器进行预警
     * @throws Exception 
     */
    public void getAllWarning() {

        Map result = new HashMap<>();
		try {
	      	DataSourceContextHolder.setDbType("ds_mop");
			List<Server> serverInfo = serverDao.findAll(result);
			long startTime = System.currentTimeMillis();
			StringBuilder sb = new StringBuilder();
			if(serverInfo.size()>0){
				for(Server s:serverInfo){
					String ip = s.getIp();
					String createTime = s.getCreatetime();
					String warningstate = s.getWarningstate();
					long timeMinute = DateUtils.getTimeMinute(createTime);
					//大于30分钟预警
					if(timeMinute>30){
						if(StringUtils.isNotBlank(warningstate)){
							if(warningstate.equals("0")){
								s.setState("0");
								s.setWarningstate("1");
								int update = serverDao.update(s);
								//发邮件
						        sb.append(s.getIp());
						        sb.append(",");
							}
						}
					}else{
						if(StringUtils.isNotBlank(warningstate)){
							if(warningstate.equals("1")){
								s.setWarningstate("0");
								serverDao.update(s);
							}
						}
					}
				}
			}
			if(sb.length()>0){
				//发邮件
				String[] emails = {  "jie.meng@syntun.com" };
		        String cont = "你好："+sb+"服务有问题，请及时查看修复。";
		        //SyntunEmail.sendSimpleEmail(emails, "服务器预警", cont, "b", "text");
			}
	        long endTime = System.currentTimeMillis();
	        System.out.println("耗时================:"+ (endTime-startTime)+"ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     *查询所有服务器日志状态
     * @throws Exception 
     */
    public void getAllLogState() {

        Map result = new HashMap<>();
		try {
	      	DataSourceContextHolder.setDbType("ds_mop");
			List<Server> serverInfo = serverDao.findAll(result);
			long startTime = System.currentTimeMillis();
			if(serverInfo.size()>0){
				for(Server s:serverInfo){
					String ip = s.getIp();
					result.put("ip", ip);
					Jiexisudu findone = jiexisuduDao.findone(result);
					String date_time = findone.getDate_time();
					long timeMinute = DateUtils.getTimeMinute(date_time);
					//大于30分钟预警
					if(timeMinute>10){
						s.setLogstate("1");
						serverDao.update(s);
					}else{
						s.setLogstate("0");
						serverDao.update(s);
					}
				}
			}
	        long endTime = System.currentTimeMillis();
	        System.out.println("耗时================:"+ (endTime-startTime)+"ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     *查询所有服务器停止5分钟自动重启
     * @throws Exception 
     */
    public void getAllStopTimeOutState() {

        Map result = new HashMap<>();
        result.put("timeoutflag", "0");
		try {
	      	DataSourceContextHolder.setDbType("ds_mop");
			List<Server> serverInfo = serverDao.findAll(result);
			if(serverInfo.size()>0){
				for(Server s:serverInfo){
					//判断是否运行
	    			Servers s1 = serversDao.findone(s.getIp());
	        		String res = Shell.exec(s.getIp(),s1.getName(),s1.getPassword(), 22,s.getStatepath());
	        		Server server = new Server();
	        		server.setId(s.getId());
	        		if(StringUtils.isNotBlank(res)){
	        			String stoptimeout = s.getStoptimeout();
	        			//停止状态
				    	if(res.equals("0")){
				    		if(StringUtils.isNotBlank(stoptimeout) && !stoptimeout.equals("0")){
				    			//停止超过5分钟重启
				    			if(DateUtils.get5(stoptimeout)){
				    				RemoteShellExecutor executor = new RemoteShellExecutor(s.getIp(),s1.getName(),s1.getPassword());
				    				Map map = executor.exec(s.getStartpath(),1);
				    			    String exec = map.get("ret").toString();
				    			    if(exec.equals("0")){
				    			    	//更新时间为空
				    			    	server.setStoptimeout("0");
				    			    	serverDao.update(server);
				    			    }
				    			}
				    		}else{
				    			//更新停止时间为现在时间
		    			    	server.setStoptimeout(DateUtils.getDate());
		    			    	serverDao.update(server);
				    		}
				    	}else{
				    		//启动状态
				    		if(StringUtils.isNotBlank(stoptimeout) && !stoptimeout.equals("0")){
				    			//更新时间为空
		    			    	server.setStoptimeout("0");
		    			    	serverDao.update(server);
				    		}
				    	}
	        		}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
