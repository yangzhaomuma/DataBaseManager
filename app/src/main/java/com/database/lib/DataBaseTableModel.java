package com.database.lib;

import java.util.List;

public class DataBaseTableModel {
    //表名
	private String tableName;
	//主键
	private String primaryKey;
	//表结构
	private List<DataBaseFieldModel> fields;
	
	public List<DataBaseFieldModel> getFields() {
		return fields;
	}
	public void setFields(List<DataBaseFieldModel> fields) {
		this.fields = fields;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	
}
