package com.syntun.entity;

public class ApiDoc {

	private int pid;//外键
	private String name;//字段名称
	private String datavalue;//字段值
	private String ismust;//字段是否必填  Y 是，N不是
	private String type;//字段类型
	private String explains;//字段说明
	private String flag;//0：请求参数，1：返回参数
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
	public String getIsmust() {
		return ismust;
	}
	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	
}
