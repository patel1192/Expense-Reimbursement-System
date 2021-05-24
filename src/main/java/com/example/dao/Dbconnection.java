package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

	private static final String URL = "jdbc:postgresql://database-1.cbts2hihugke.us-east-2.rds.amazonaws.com:5432/ersdb";
	private static final String username = "ers";
	private static final String password = "password";
	
	public Connection getDbConnection() throws SQLException{
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
			return DriverManager.getConnection(URL, username, password);
		
	}
}
