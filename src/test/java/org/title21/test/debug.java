package org.title21.test;

import org.testng.annotations.Test;
import org.title21.DBConnection.DBConnection;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;

public class debug extends BaseClass{
	
	/*public static void main(String args[]){
		AdminData admindataObj=new AdminData();
		admindataObj.getEmployeeName();		
	}*/
	
	@Test
	public static void test_db() throws ClassNotFoundException{
		
		DBConnection dbconnect=new DBConnection();
		dbconnect.getConnection();
		
	}
	
	
}