package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;

public class MySQLDatabaseManager implements DatabaseManager{
	@Override
	public Connection getConnection() {
		final String USER_NAME  ="root";
		final String PASSWORD = "1234";
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String JDBC_URL = "jdbc:mysql://localhost:3306/inventory system";
		
		Connection conn = null;
		
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}

}
