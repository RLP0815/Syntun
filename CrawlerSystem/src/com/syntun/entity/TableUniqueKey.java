package com.syntun.entity;

/**
 * 
 */
public class TableUniqueKey {
	 //主键ID
	private int id;
	//
    private String dataBase;
	//
    private String tableName;
	//
    private String uniqueKey;

	private int ifCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public int getIfCount() {
		return ifCount;
	}
	public void setIfCount(int ifCount) {
		this.ifCount = ifCount;
	}
    
}