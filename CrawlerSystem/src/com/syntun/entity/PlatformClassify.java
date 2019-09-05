package com.syntun.entity;

/**
 * 平台分类
 */
public class PlatformClassify {

    private Integer id;

    private String platformid;//平台id

    private String platformname;//平台name

    private String onecategoryid;//一级分类id

    private String onecategoryname;//一级分类name

    private String twocategoryid;//二级分类id

    private String twocategoryname;//二级分类name

    private String threecategoryid;//三级分类id

    private String threecategoryname;//三级分类name
    
    private String threecategoryurl;//三级分类url

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatformid() {
        return platformid;
    }

    public void setPlatformid(String platformid) {
        this.platformid = platformid == null ? null : platformid.trim();
    }

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname == null ? null : platformname.trim();
    }

    public String getOnecategoryid() {
        return onecategoryid;
    }

    public void setOnecategoryid(String onecategoryid) {
        this.onecategoryid = onecategoryid == null ? null : onecategoryid.trim();
    }

    public String getOnecategoryname() {
        return onecategoryname;
    }

    public void setOnecategoryname(String onecategoryname) {
        this.onecategoryname = onecategoryname == null ? null : onecategoryname.trim();
    }

    public String getTwocategoryid() {
        return twocategoryid;
    }

    public void setTwocategoryid(String twocategoryid) {
        this.twocategoryid = twocategoryid == null ? null : twocategoryid.trim();
    }

    public String getTwocategoryname() {
        return twocategoryname;
    }

    public void setTwocategoryname(String twocategoryname) {
        this.twocategoryname = twocategoryname == null ? null : twocategoryname.trim();
    }

    public String getThreecategoryid() {
        return threecategoryid;
    }

    public void setThreecategoryid(String threecategoryid) {
        this.threecategoryid = threecategoryid == null ? null : threecategoryid.trim();
    }

    public String getThreecategoryname() {
        return threecategoryname;
    }

    public void setThreecategoryname(String threecategoryname) {
        this.threecategoryname = threecategoryname == null ? null : threecategoryname.trim();
    }

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getThreecategoryurl() {
		return threecategoryurl;
	}

	public void setThreecategoryurl(String threecategoryurl) {
		this.threecategoryurl = threecategoryurl;
	}
	
}