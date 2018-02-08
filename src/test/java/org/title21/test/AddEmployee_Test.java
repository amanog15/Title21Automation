package org.title21.test;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class AddEmployee_Test extends BaseClass {
	LoginPage_POM login; 
	LogoutPage_POM logout;
	DashBord_POM dashboardObj;
	SoftAssert softAssertion=new SoftAssert();
	String className="";
	static Logger log = Logger.getLogger(AddEmployee_Test.class);
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "login_admin", groups = "Logins", priority = 0)
	public void LoginWithInvalidCredentials() throws Exception 
	{		
		test = extent.startTest("Add Employee");		
		logout=new LogoutPage_POM(driver);
		logout.logoutFunction();	
		log.info("logout function.");
	}	
		
	@AfterClass
	public void closeBrowserInstance() 
	{		
		driver.close();
	}

	
	
}
