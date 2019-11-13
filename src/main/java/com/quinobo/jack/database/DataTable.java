package com.quinobo.jack.database;

import java.util.ArrayList;
import java.util.List;

public class DataTable {

	private List<String[]> records = new ArrayList<String[]>();
	private DBMetaData dbMetaData;
	private List<Integer> indexes = new ArrayList<Integer>();

	public DataTable(DBMetaData dbMetaData) {
		this.dbMetaData = dbMetaData;
	}
	
	public void addRecord(String[] rowData) {
		records.add(rowData);
		createIndex(getRowCount()-1);
	}
	
	public int getRowCount() {
		return records.size();
	}
	
	public void createIndex(int index) {
		indexes.add(index);
	}
	
	public List<String[]> getRecords() {
		return records;
	}

	public DBMetaData getDbMetaData() {
		return dbMetaData;
	}

	public List<Integer> getIndexes() {
		return indexes;
	}
	
}
