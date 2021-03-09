package com.boydhcl.getProductByID.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector{
	
	private Connection connection;
	
	public DBConnector(String url, String uName, String pass) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, uName, pass);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void close() throws SQLException{
		if(this.connection != null) {
			this.connection.close();
		}
	}
}