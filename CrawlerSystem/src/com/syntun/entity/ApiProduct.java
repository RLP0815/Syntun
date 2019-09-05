package com.syntun.entity;

public class ApiProduct {

	private int pid;//外键
	private String name;//产品介绍
	
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
	
}
