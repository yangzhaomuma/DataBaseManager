package com.database.lib;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataBaseHandler extends DefaultHandler{

	
	 private String dataBaseName;
     private int dataBaseVersion;
     private List<DataBaseTableModel> tables;
     private DataBaseTableModel table;
     private List<DataBaseFieldModel> fields;
	
	@Override
	public void startDocument() throws SAXException {
		tables=new ArrayList<DataBaseTableModel>();
		fields=new ArrayList<DataBaseFieldModel>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (DataBaseManager.DATABASE.equals(qName)) {
			dataBaseName = attributes.getValue(DataBaseManager.DATABASE_NAME);
			dataBaseVersion = Integer.parseInt(attributes.getValue(DataBaseManager.DATABASE_VERSION));
		} else if (DataBaseManager.TABLE.equals(qName)) {
			table =new DataBaseTableModel();
			String name=attributes.getValue(DataBaseManager.TABLE_NAME);
			String primarykey = attributes.getValue(DataBaseManager.TABLE_PRIMARYKEY);
			table.setTableName(name);
			table.setPrimaryKey(primarykey);
		} else if (DataBaseManager.FIELD.equals(qName)) {
			try {
				String filed = attributes.getValue(DataBaseManager.FIELD_NAME);
				DataBaseFieldModel fieldModel=new DataBaseFieldModel();
				fieldModel.setFieldName(filed);
				fields.add(fieldModel);
				table.setFields(fields);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (DataBaseManager.TABLE.equals(qName)) {
			tables.add(table);
		}
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

	public List<DataBaseTableModel> getTables() {
		return tables;
	}

	public void setTables(List<DataBaseTableModel> tables) {
		this.tables = tables;
	}

	
}
