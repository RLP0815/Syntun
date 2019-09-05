package com.syntun.controller.systemlog;

public class OperatorLog {

	private String userid;//用户id
	private String username;//用户名称
	private String method;//方法名
	private String beanname;//方法所在的类名
	private String intf;//操作接口名
	private String url;//操作接口地址
	private String requestparam;//操作接口参数
	private String requestip;//操作ip
	private String requesttime;//操作时间
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntf() {
		return intf;
	}
	public void setIntf(String intf) {
		this.intf = intf;
	}
	public String getRequestparam() {
		return requestparam;
	}
	public void setRequestparam(String requestparam) {
		this.requestparam = requestparam;
	}
	public String getBeanname() {
		return beanname;
	}
	public void setBeanname(String beanname) {
		this.beanname = beanname;
	}
	
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	public String getRequestip() {
		return requestip;
	}
	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}
	
}