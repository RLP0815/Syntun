package com.syntun.entity;

/**
 * 
 */
public class PlatformList {
    //主键ID
	private int id;
    //平台ID
	private int platformId;
    //平台名称
	private String platformName;
    //数据库名称
	private String platformTableName;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getPlatformTableName() {
		return platformTableName;
	}
	public void setPlatformTableName(String platformTableName) {
		this.platformTableName = platformTableName;
	}
    
    
}