package com.syntun.entity;

/**
 * 服务器类
 * @author wangdong
 *
 */
public class Server {

	private String id;
	private String ip;//服务器ip
	private String name;//服务器名称
	private String password;//服务器密码
	private String startpath;//shell启动脚本路径
	private String stoppath;//shell停止脚本路径
	private String statepath;//shell服务状态脚本路径
	private String isdel;//是否删除  0未删除，1已删除
	private String state;//前段显示服务状态   0是停止状态   1是启动状态
	private String exception;
	private String classify;//分类 1：巡检，2：日常
	private String logpath;
	private String createtime;//创建时间
	private String warningstate;//预警状态 0是正常，1已预警。
	private String logstate;//日志状态0是正常，1不正常。
	private String timeoutflag;//是否自动重启 0是，1不是
	private String stoptimeout;//停止超时时间
	private String logfilestate;//日志文件发邮件状态，0未发，1已发。 
	
	public String getLogfilestate() {
		return logfilestate;
	}
	public void setLogfilestate(String logfilestate) {
		this.logfilestate = logfilestate;
	}
	public String getTimeoutflag() {
		return timeoutflag;
	}
	public void setTimeoutflag(String timeoutflag) {
		this.timeoutflag = timeoutflag;
	}
	public String getWarningstate() {
		return warningstate;
	}
	public void setWarningstate(String warningstate) {
		this.warningstate = warningstate;
	}
	public String getLogstate() {
		return logstate;
	}
	public void setLogstate(String logstate) {
		this.logstate = logstate;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getLogpath() {
		return logpath;
	}
	public void setLogpath(String logpath) {
		this.logpath = logpath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getStartpath() {
		return startpath;
	}
	public void setStartpath(String startpath) {
		this.startpath = startpath;
	}
	public String getStoppath() {
		return stoppath;
	}
	public void setStoppath(String stoppath) {
		this.stoppath = stoppath;
	}
	public String getStatepath() {
		return statepath;
	}
	public void setStatepath(String statepath) {
		this.statepath = statepath;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getStoptimeout() {
		return stoptimeout;
	}
	public void setStoptimeout(String stoptimeout) {
		this.stoptimeout = stoptimeout;
	}
	
}
