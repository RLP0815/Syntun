package com.syntun.entity;


/**
 * 
 */
public class ErrResult {
	//平台ID
    private int platformId;
    //网页ID
    private String operationProductId;
    //获取日期
    private String getDate;
    //错误类型名称
    private String errTypeName;
    //错误小类型
    private String errTypeInfo;
    //具体错误信息
    private String errFocusInformation;
    //店铺ID
    private String shopId;
	
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public String getOperationProductId() {
		return operationProductId;
	}
	public void setOperationProductId(String operationProductId) {
		this.operationProductId = operationProductId;
	}
	public String getGetDate() {
		return getDate;
	}
	public void setGetDate(String getDate) {
		this.getDate = getDate;
	}
	public String getErrTypeName() {
		return errTypeName;
	}
	public void setErrTypeName(String errTypeName) {
		this.errTypeName = errTypeName;
	}
	public String getErrTypeInfo() {
		return errTypeInfo;
	}
	public void setErrTypeInfo(String errTypeInfo) {
		this.errTypeInfo = errTypeInfo;
	}
	public String getErrFocusInformation() {
		return errFocusInformation;
	}
	public void setErrFocusInformation(String errFocusInformation) {
		this.errFocusInformation = errFocusInformation;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
    
    
}