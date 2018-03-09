package org.title21.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.Assert;
import org.title21.utility.BaseClass;

public class DBConnection extends BaseClass {


	public static Connection getConnection() throws Exception {
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

	private static String getDBConnectionString()
	{

		return String.format("jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;", BaseClass.dbServer,
				BaseClass.dbName, BaseClass.dbUsername, BaseClass.dbPassword);

	}

	public static Connection closeConnection() {
		if (connection != null) {
			try {
				System.out.println("Closing DB Connection");
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return connection;
	}

	public static int getIntDBValue(String dbquery, String cloumnName) throws Exception
	{
		int dbvalue = 0;
		try{
			getConnection();
			String query = dbquery;
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while(rs.next()){
				dbvalue= rs.getInt(cloumnName);	
			}
		}
		finally{
			closeConnection();
		}
		return dbvalue;
	}
}




