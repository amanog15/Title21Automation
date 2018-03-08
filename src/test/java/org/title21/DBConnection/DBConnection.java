package org.title21.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.*;

import org.testng.Assert;

public class DBConnection {

	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.);
				//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(getDBConnectionString());
				if (connection != null) {
					System.out.println("Connected");
				}
			} catch (SQLException e) {
				Assert.fail("Error Occurred while Connecting to DB : " + e.getMessage());
			}
		}
		return connection;
	}

	private static String getDBConnectionString() {
		// TODO Auto-generated method stub
		return null; 
		
	}
}




