package com.quinobo.jack.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * ConnectionTest
 * 
 * MySQL DATABASE에 연결하는 클래스
 */
@Service
public class ConnectionTest implements InitializingBean {

	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	@Autowired
	private BasicDataSource dataSource;

	public void afterPropertiesSet() throws SQLException {
		// run the actual test
		connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		stmt = connection.createStatement();
	}

	public ConnectionTest() {
	}

	public int executeUpdate(String sql) {
		int result = 0;
		try {
			if (connection == null) {
				afterPropertiesSet();
			}

			stmt = connection.createStatement();

			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("에러: " + e);
			connectionClose();
		} 
		return result;
	}

	public int excuteSingleQuery(String sql) {
		int result = 0;
		try {
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("에러: " + e);
			connectionClose();
		} 
		return result;
	}

	public DataTable excuteMultipleQuery(String sql) {
		DataTable dataTable = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			java.sql.ResultSetMetaData md = rs.getMetaData();
			DBMetaData dbMetaData = new DBMetaData();
			for (int i = 1; i < md.getColumnCount(); i++) {
				dbMetaData.addColumName(md.getColumnName(i));
			}
			dataTable = new DataTable(dbMetaData);
			while(rs.next()) {
				String[] rowData = new String[md.getColumnCount()];
				for (int col = 1; col <= md.getColumnCount(); col++) {
					if (rs.getObject(col) == null) {
						rowData[col-1] = "";
					} else if(rs.getObject(col) instanceof String) {
						rowData[col-1] = (String) rs.getObject(col);
					} else {
						rowData[col-1] = rs.getObject(col).toString();
					}
				}
				dataTable.addRecord(rowData);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("에러: " + e);
			connectionClose();
		}
		return dataTable;
	}

	private void connectionClose() {
		try {
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void connectCommit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connectionClose();
		}
	}
}
// 참고 사이트 : https://victorydntmd.tistory.com/145
