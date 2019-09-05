package com.syntun.util;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class RemoteShellExecutor {
	
  private Connection conn;
  /** 远程机器IP */
  private String ip;
  /** 用户名 */
  private String osUsername;
  /** 密码 */
  private String password;
  private String charset = Charset.defaultCharset().toString();

  private static final int TIME_OUT = 1000 * 5 * 60;
  
  private static int connectCount = 0;

  /**
   * 构造函数
   * @param ip
   * @param usr
   * @param pasword
   */
  public RemoteShellExecutor(String ip, String usr, String pasword) {
    this.ip = ip;
    this.osUsername = usr;
    this.password = pasword;
  }


  /**
   * 登录
   * @return
   * @throws IOException
   */
  private boolean login() throws IOException {
    conn = new Connection(ip);
    conn.connect();
    return conn.authenticateWithPassword(osUsername, password);
  }

  /**
   * 执行脚本
   *
   * @param cmds
   * @return
   * @throws Exception
   */
  public Map exec(String cmds,int size) throws Exception {
    connectCount = connectCount+1;
    InputStream stdOut = null;
    InputStream stdErr = null;
    String outStr = "";
    String outErr = "";
    int ret = -1;
    Map m = new HashMap();
    try {
      if (login()) {
        // Open a new {@link Session} on this connection
    	  System.out.println(connectCount+"第几个线程=================="+conn +login());
        Session session = conn.openSession();
        // Execute a command on the remote machine.
        session.execCommand(cmds);

        stdOut = new StreamGobbler(session.getStdout());
        outStr = processStream(stdOut, charset);

        stdErr = new StreamGobbler(session.getStderr());
        outErr = processStream(stdErr, charset);

        session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

        //System.out.println("outStr=" + outStr);
        //System.out.println("outErr=" + outErr);

        ret = session.getExitStatus();
        String[] split = outStr.split("\\n+");
        m.put("outStr", split[0]);//shell返回值
        m.put("outErr", outErr);//shell异常信息
        m.put("ret", ret);//shell执行0成功，失败
      } else {
        throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
      }
    } finally {
      if (conn != null) {
		  conn.close(); 
      }
      IOUtils.closeQuietly(stdOut);
      IOUtils.closeQuietly(stdErr);
    }
    return m;
  }

  /**
   * @param in
   * @param charset
   * @return
   * @throws IOException
   * @throws UnsupportedEncodingException
   */
  private static String processStream(InputStream in, String charset) throws Exception {
    byte[] buf = new byte[1024];
    StringBuilder sb = new StringBuilder();
    while (in.read(buf) != -1) {
      sb.append(new String(buf, charset));
    }
    return sb.toString();
  }
  
  /**
   * 执行脚本
   *
   * @param cmds
   * @return
   * @throws Exception
   */
  public static Map exec(String cmds,String ip,String name,String ps) throws Exception {
	  
    InputStream stdOut = null;
    InputStream stdErr = null;
    String outStr = "";
    String outErr = "";
    int ret = -1;
    Map m = new HashMap();
    
    String charset = Charset.defaultCharset().toString();
    Connection connection = new Connection(ip);
    connection.connect();
    try {
      if (connection.authenticateWithPassword(name, ps)) {
        // Open a new {@link Session} on this connection
        Session session = connection.openSession();
        // Execute a command on the remote machine.
        session.execCommand(cmds);

        stdOut = new StreamGobbler(session.getStdout());
        outStr = processStream(stdOut, charset);

        stdErr = new StreamGobbler(session.getStderr());
        outErr = processStream(stdErr, charset);

        session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

        //System.out.println("outStr=" + outStr);
        //System.out.println("outErr=" + outErr);

        ret = session.getExitStatus();
        String[] split = outStr.split("\\n+");
        m.put("outStr", split[0]);//shell返回值
        m.put("outErr", outErr);//shell异常信息
        m.put("ret", ret);//shell执行0成功，失败
      } else {
        throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
      }
    } finally {
      if (connection != null) {
    	  connection.close(); 
      }
      IOUtils.closeQuietly(stdOut);
      IOUtils.closeQuietly(stdErr);
    }
    return m;
  }

  public static void main(String args[]) throws Exception {
	PropertiesUtil util =PropertiesUtil.getInstance("./config/config.properties", true);
	String ip = util.getPropertyValue("ip");
	String name = util.getPropertyValue("name");
	String password = util.getPropertyValue("password");
    RemoteShellExecutor executor = new RemoteShellExecutor(ip,name,password);
    // 执行*.sh  后面2个参数
    Map map = executor.exec("/home/test/state.sh",1);
    String exec = map.get("ret").toString();
    String outStr = map.get("outStr").toString();
    String[] split = outStr.split("\\n+");
    if(exec.equals("0")){
        System.out.println("正常");
        if("0".equals(split[0])){
        	System.out.println("返回值"+split[0]);
        }
    }else{
    	System.out.println("异常");
    	System.out.println("返回值"+split[0]);
    }

  }
}