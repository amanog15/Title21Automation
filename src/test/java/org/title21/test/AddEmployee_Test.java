package org.title21.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.AddEmployee_POM;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class AddEmployee_Test extends BaseClass {
	LoginPage_POM login; 
	LogoutPage_POM logout;
	AddEmployee_POM addEmployeePOM;
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
		BaseClass.getAdministrationPage();
		
		addEmployeePOM=new AddEmployee_POM(driver);
		addEmployeePOM.employees_link().click();
		
		log.info("Now clicking on Add new Link");
		addEmployeePOM.addNewLink().click();
		
		if (addEmployeePOM.getEmployeeFullName().isDisplayed()){
			test.log(LogStatus.PASS, "Add employee popup opened with General TAB opened"+
					test.addScreenCapture(captureScreenShot(driver, "employeepopup")));
		}
						
		log.info("Entering data in form");
				
		addEmployeePOM.location_dropdown().selectByVisibleText(employeeData[1][0]);	
		sleep(2);		
				
		addEmployeePOM.getEmployeeFullName().sendKeys(employeeData[1][1]);
		
		addEmployeePOM.getEmployeeID().sendKeys(employeeData[1][2]);
		
		addEmployeePOM.getsupervisorDropdown().selectByVisibleText(employeeData[1][3]);
		
		addEmployeePOM.getbusinessUnitDropdown().selectByIndex(1);
		
		addEmployeePOM.getDepartmentDropdown().selectByVisibleText(employeeData[1][5]);		
		
		addEmployeePOM.getAddBtn().click();
		
		sleep(6);		
		
		addEmployeePOM.cancel_Btn().click();
		sleep(2);		
		
		logout=new LogoutPage_POM(driver);
		logout.logoutFunction();	
		log.info("logout function.");
		extent.endTest(test);
	}	
		
	@AfterClass
	public void closeBrowserInstance() 
	{		
		driver.close();
	}

	
	
}
