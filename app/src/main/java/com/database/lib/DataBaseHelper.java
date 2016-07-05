package com.database.lib;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    private DataBaseModel model;
	public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
    
	public DataBaseHelper(Context context, DataBaseModel model) {
		super(context, model.getDataBaseName(), null, model.getDataBaseVersion());
		this.model=model;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		if(model.getTables().size()>0){
			for(DataBaseTableModel tableModel:model.getTables()){
				createTable(db,tableModel);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	private void createTable(SQLiteDatabase db, DataBaseTableModel table) {
		StringBuilder sql = new StringBuilder(" create table if not exists ");
		String tableName = table.getTableName();
		String primaryKey = table.getPrimaryKey();
		List<DataBaseFieldModel> fileds = table.getFields();
		sql.append(tableName);
		sql.append("(");
		int i = 0;
		for (i = 0; i < fileds.size(); i++) {
			if (i == fileds.size() - 1) {
				if (fileds.get(i).getFieldName().equals(primaryKey)) {
					sql.append(fileds.get(i).getFieldName() + " integer primary key autoincrement");
				} else{
					sql.append(fileds.get(i).getFieldName() + " TEXT)");
				}
			} else {
				if (fileds.get(i).getFieldName().equals(primaryKey)) {
					sql.append(fileds.get(i).getFieldName() + " integer primary key autoincrement,");
				} else{
					sql.append(fileds.get(i).getFieldName()  + " TEXT,");
				}
			}

		}
		db.execSQL(sql.toString());
	}
}
