package com.database.lib;

import java.util.List;

public class DataBaseModel {
	 //数据库名称
     private String dataBaseName;
	 //数据库版本
     private int dataBaseVersion;
	 //数据库中表集合
     private List<DataBaseTableModel> tables;
     
	public List<DataBaseTableModel> getTables() {
		return tables;
	}
	public void setTables(List<DataBaseTableModel> tables) {
		this.tables = tables;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public int getDataBaseVersion() {
		return dataBaseVersion;
	}
	public void setDataBaseVersion(int dataBaseVersion) {
		this.dataBaseVersion = dataBaseVersion;
	}

	
     
     
}
