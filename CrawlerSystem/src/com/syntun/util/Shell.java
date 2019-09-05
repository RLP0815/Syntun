package com.syntun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Shell {

    public static String exec(String host, String user, String psw, int port, String command) {
        String result = "";
        Session session = null;
        ChannelExec openChannel = null;
        try {
            JSch jsch = new JSch();
            // getSession()只是创建一个session，需要设置必要的认证信息之后，调用connect()才能建立连接。
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig("userauth.gssapi-with-mic", "no");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.setTimeout(5000);
            session.connect();

            // 调用openChannel(String type)
            // 可以在session上打开指定类型的channel。该channel只是被初始化，使用前需要先调用connect()进行连接。
            //   Channel的类型可以为如下类型：
            //   shell - ChannelShell
            //   exec - ChannelExec
            //   direct-tcpip - ChannelDirectTCPIP
            //   sftp - ChannelSftp
            //   subsystem - ChannelSubsystem
            // 其中，ChannelShell和ChannelExec比较类似，都可以作为执行Shell脚本的Channel类型。它们有一个比较重要的区别：ChannelShell可以看作是执行一个交互式的Shell，而ChannelExec是执行一个Shell脚本。
            openChannel = (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            
            openChannel.connect();

            InputStream in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result += buf;
            }
        } catch (Exception e) {
        	result = "0";
            e.getMessage();
            System.out.println(e.getMessage()+"============================================");
        } finally {
            if (openChannel != null && !openChannel.isClosed()) {
                openChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return result;
    }
	public static void main(String[] args) {
//		PropertiesUtil util =PropertiesUtil.getInstance("./config/config.properties", true);
//		String ip = util.getPropertyValue("ip");
//		String name = util.getPropertyValue("name");
//		String password = util.getPropertyValue("password");
		String res = Shell.exec("192.168.0.124", "root", "syn_hadoop@124", 22, "/home/renliping/tableCountList.sh "+"123");
		System.out.println(res);
		if(res.equals("0")){
			System.out.println("0");
		}else{
			System.out.println("1");
		}
	}
}