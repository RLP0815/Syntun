package com.syntun.entity;

/**
 * 
 */
public class PromoCompare {
    //主键ID
	private int id;
	//促销名称对应的促销ID
	private String promotionType;
    //平台ID
	private int platformId;
    //平台名称
	private String platformName;
    //促销名称
	private String promotionTypeName;
    //促销标准名称
	private String promotionStandard;
    //促销名称对应的促销ID
	private int promotionId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
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
	public String getPromotionTypeName() {
		return promotionTypeName;
	}
	public void setPromotionTypeName(String promotionTypeName) {
		this.promotionTypeName = promotionTypeName;
	}
	public String getPromotionStandard() {
		return promotionStandard;
	}
	public void setPromotionStandard(String promotionStandard) {
		this.promotionStandard = promotionStandard;
	}
	public int getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
    
    
    
}