package com.syntun.entity;

/**
 * 
 */
public class ProductState {
    //主键ID
	private int id;
	//平台ID
	private int platformId;
	//平台名称
	private String platformName;
	//状态码
	private int stateCode;
	//状态名称
	private String productState;
	//下架描述
	private String errorConnect;
	
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
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public String getProductState() {
		return productState;
	}
	public void setProductState(String productState) {
		this.productState = productState;
	}
	public String getErrorConnect() {
		return errorConnect;
	}
	public void setErrorConnect(String errorConnect) {
		this.errorConnect = errorConnect;
	}


	
}