package com.syntun.entity;

/**
 * 
 */
public class EditRemark {
    //主键ID
	private int id;
	//维护表格标识
    private String remarkId;
    //维护表格
    private String remarkName;
    //序号（用于排序）
    private int serialNum;
    //维护表格说明
    private String remark;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemarkId() {
		return remarkId;
	}
	public void setRemarkId(String remarkId) {
		this.remarkId = remarkId;
	}
	
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
    
}