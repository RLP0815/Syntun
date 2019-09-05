package com.syntun.entity;

/**
 * 接口主类
 * @author wangdong
 */
public class ApiName {

	private int id;
	private String apiname;//接口名称
	private String apiaddress;//接口地址
	private String backformat;//返回格式
	private String reqmode;//请求方式
	private String apiremarks;//接口备注
	private String reqdemo;//请求示例
	
	
	public String getReqdemo() {
		return reqdemo;
	}
	public void setReqdemo(String reqdemo) {
		this.reqdemo = reqdemo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApiname() {
		return apiname;
	}
	public void setApiname(String apiname) {
		this.apiname = apiname;
	}
	public String getApiaddress() {
		return apiaddress;
	}
	public void setApiaddress(String apiaddress) {
		this.apiaddress = apiaddress;
	}
	public String getBackformat() {
		return backformat;
	}
	public void setBackformat(String backformat) {
		this.backformat = backformat;
	}
	public String getReqmode() {
		return reqmode;
	}
	public void setReqmode(String reqmode) {
		this.reqmode = reqmode;
	}
	public String getApiremarks() {
		return apiremarks;
	}
	public void setApiremarks(String apiremarks) {
		this.apiremarks = apiremarks;
	}
	
}
