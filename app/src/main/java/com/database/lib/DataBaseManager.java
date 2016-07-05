package com.database.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

	private Context context;
	private DataBaseHelper mHelper;
	private SQLiteDatabase db;
	
	public static final String DATABASE="Database";
	public static final String TABLE="table";
	public static final String FIELD="field";
	
	public static final String DATABASE_NAME="name";
	public static final String DATABASE_VERSION="version";
	public static final String TABLE_NAME="name";
	public static final String TABLE_PRIMARYKEY="primaryKey";
	public static final String FIELD_NAME="name";
	
	
	private DataBaseManager(){}
	private volatile static DataBaseManager instance;
	public static DataBaseManager getInstance(){
		if(instance==null){
			synchronized (DataBaseManager.class) {
				instance=new DataBaseManager();
			}
		}
		return instance;
	}
	
	public void init(Context context){
		this.context=context;
		
		try {
			DataBaseModel dataBaseModel=getDataBase("database.xml");
			mHelper=new DataBaseHelper(context, dataBaseModel);
			db=mHelper.getWritableDatabase();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 将XML文件解析为DataBaseModle
	 * @param xmlFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
     * @throws IOException
     */
	private DataBaseModel getDataBase(String xmlFile) throws ParserConfigurationException, SAXException, IOException{
		//读取xml文件
		InputStream in=context.getResources().getAssets().open(xmlFile);
		//SAX解析
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser= factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		//DefaultHandler配合SAX解析xml内容
		DataBaseHandler mHandler=new DataBaseHandler();
		reader.setContentHandler(mHandler);
		reader.parse(new InputSource(new InputStreamReader(in, "UTF-8")));
		//将解析得到的内容导致到model中
		DataBaseModel model=new DataBaseModel();
		model.setDataBaseName(mHandler.getDataBaseName());
		model.setDataBaseVersion(mHandler.getDataBaseVersion());
		model.setTables(mHandler.getTables());
		return model;
	}

	/**
	 * 增
	 * @param table
	 * @param values
     * @return
     */
	public long insertData(String table,ContentValues values){
		return 0;
	}

	/**
	 * 查
	 * @param sql
     */
	public void selectData(String sql){
	    
	}

	/**
	 * 改
	 * @param sql
     */
	public void modifyData(String sql){

	}

	/**
	 * 删
	 * @param sql
     */
	public void deleteData(String sql){

	}


}
