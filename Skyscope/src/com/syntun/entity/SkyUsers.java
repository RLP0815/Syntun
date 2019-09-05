package com.syntun.entity;

/**
 * 
 */
public class SkyUsers {
    //主键ID
	private Integer id;
    //用户ID
	private String userId;
    //用户名
	private String userName;
    //组名
	private String groupName;
    //组ID
	private Integer groupId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
    
	
}