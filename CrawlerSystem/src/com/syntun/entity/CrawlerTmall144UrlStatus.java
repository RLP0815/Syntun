package com.syntun.entity;

/**
 * 
 */
public class CrawlerTmall144UrlStatus {
    //主键ID
	private int id;
	//
    private String addTime;
	//
    private String getTime;
    //
    private String parseTime;
    //
    private int isParseOver;
    //
    private int tryNum;
	//
    private String urlMd5;
    //
    private String insertBatchNum;
    //
    private int sortId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getParseTime() {
		return parseTime;
	}
	public void setParseTime(String parseTime) {
		this.parseTime = parseTime;
	}
	public int getIsParseOver() {
		return isParseOver;
	}
	public void setIsParseOver(int isParseOver) {
		this.isParseOver = isParseOver;
	}
	public int getTryNum() {
		return tryNum;
	}
	public void setTryNum(int tryNum) {
		this.tryNum = tryNum;
	}
	public String getUrlMd5() {
		return urlMd5;
	}
	public void setUrlMd5(String urlMd5) {
		this.urlMd5 = urlMd5;
	}
	public String getInsertBatchNum() {
		return insertBatchNum;
	}
	public void setInsertBatchNum(String insertBatchNum) {
		this.insertBatchNum = insertBatchNum;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
    
}