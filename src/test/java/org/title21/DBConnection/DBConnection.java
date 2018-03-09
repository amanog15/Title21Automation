package org.title21.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.title21.utility.*;

import org.testng.Assert;

public class DBConnection {

	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(getDBConnectionString());
				if (connection != null) {
					System.out.println("Connected to DB");
				}
			} catch (SQLException e) {
				Assert.fail("Error Occurred while Connecting to DB : " + e.getMessage());
			}
		}
		return connection;
	}

	private static String getDBConnectionString() {
		
		// TODO Auto-generated method stub
		return String.format("jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;", BaseClass.dbServer,
				BaseClass.dbName, BaseClass.dbUsername, BaseClass.dbPassword);
		
	}
}




