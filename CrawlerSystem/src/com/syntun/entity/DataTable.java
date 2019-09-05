package com.syntun.entity;

/**
 * 
 */
public class DataTable {
    //主键ID
	private int tableId;
	//
    private String tableName;
    
    private String insertOracleTime;
    
    private String rowkeyFiled;

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getInsertOracleTime() {
		return insertOracleTime;
	}

	public void setInsertOracleTime(String insertOracleTime) {
		this.insertOracleTime = insertOracleTime;
	}

	public String getRowkeyFiled() {
		return rowkeyFiled;
	}

	public void setRowkeyFiled(String rowkeyFiled) {
		this.rowkeyFiled = rowkeyFiled;
	}

}