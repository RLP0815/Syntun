package com.syntun.entity;

/**
 * 数据处理sql类
 */
public class DataRrocessingSql {

    private String sqlname;//数据处理生成sql 不能重复  主键

    private String databasename1; //库名1

    private String databasename2;//库名1

    private String tablename1;//表名1

    private String tablename2;//表名2

    private String tablefield1;//表字段1

    private String tablefield2;//表字段2

    private String relationcondition;//关联条件

    private String wherecondition;//where条件

    private String createtime;//创建时间

    public String getSqlname() {
		return sqlname;
	}

	public void setSqlname(String sqlname) {
		this.sqlname = sqlname;
	}

	public String getDatabasename1() {
        return databasename1;
    }

    public void setDatabasename1(String databasename1) {
        this.databasename1 = databasename1 == null ? null : databasename1.trim();
    }

    public String getDatabasename2() {
        return databasename2;
    }

    public void setDatabasename2(String databasename2) {
        this.databasename2 = databasename2 == null ? null : databasename2.trim();
    }

    public String getTablename1() {
        return tablename1;
    }

    public void setTablename1(String tablename1) {
        this.tablename1 = tablename1 == null ? null : tablename1.trim();
    }

    public String getTablename2() {
        return tablename2;
    }

    public void setTablename2(String tablename2) {
        this.tablename2 = tablename2 == null ? null : tablename2.trim();
    }

    public String getTablefield1() {
        return tablefield1;
    }

    public void setTablefield1(String tablefield1) {
        this.tablefield1 = tablefield1 == null ? null : tablefield1.trim();
    }

    public String getTablefield2() {
        return tablefield2;
    }

    public void setTablefield2(String tablefield2) {
        this.tablefield2 = tablefield2 == null ? null : tablefield2.trim();
    }

    public String getRelationcondition() {
        return relationcondition;
    }

    public void setRelationcondition(String relationcondition) {
        this.relationcondition = relationcondition == null ? null : relationcondition.trim();
    }

    public String getWherecondition() {
        return wherecondition;
    }

    public void setWherecondition(String wherecondition) {
        this.wherecondition = wherecondition == null ? null : wherecondition.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}