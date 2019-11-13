package com.quinobo.jack.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBMetaData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3316709889803970490L;
	private List<String> nameList = new ArrayList<String>();

	public List<String> getNameList() {
		return nameList;
	}

	public DBMetaData() {
		super();
	}
	
	public void addColumName(String name) {
		nameList.add(name);
	}
	
	public void setColumName(int column, String name) {
		nameList.set(column, name);
	}
	public int getColumnCount() {
		return nameList.size();
	}
	
	public String getColumnName(int column) {
		return nameList.get(column);
	}
	
	public String[] getColumNames() {
		String[] result = (String[])nameList.toArray();
		return result;
	}
	
	public int getIndex(String name) {
		if(!nameList.contains(name)) {
			return -1;
		} else {
			for (int i = 0; i < nameList.size(); i++) {
				if (nameList.get(i).equals(name)) {
					return 1;
				}
			}
		}
		return -1;
	}

	public boolean equals(Object object) {
		if (!(object instanceof DBMetaData)) {
			return false;
		}
		
		DBMetaData dbMetaData = (DBMetaData) object;
		if (dbMetaData.getColumnCount() != nameList.size()) {
			return false;
		}
		if (!dbMetaData.nameList.equals(nameList)) {
			return false;
		}
		return true;
	}

}
