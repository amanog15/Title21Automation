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
import org.title21.utility.FunctionUtils;

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
		getAdministrationPage();
		
		addEmployeePOM=new AddEmployee_POM(driver);
		addEmployeePOM.employees_link().click();
		
		log.info("Now clicking on Add new Link");
		addEmployeePOM.addNewLink().click();
		
		if (addEmployeePOM.getEmployeeFullName().isDisplayed()){
			test.log(LogStatus.PASS, "Add employee popup opened with General TAB opened"+
					test.addScreenCapture(captureScreenShot(driver, "employeepopup")));
		}
		
		log.info("First checking Validation Messages. without entering in any"
				+ "field, click on Add button.");
				
		waitTillElementVisible(addEmployeePOM.getAddBtn());
		
		addEmployeePOM.getAddBtn().click();	
		
		if (addEmployeePOM.verifyAddEmployeeValidationMessages()){
			test.log(LogStatus.PASS, "All validation messages are present."+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessages"))
					);
		}
		else
		{
			test.log(LogStatus.FAIL, "All validation messages are not present. see the screenshot"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessages"))
		            );
		}
				
		log.info("Entering data in form.");
		
		sleep(2);			
		addEmployeePOM.getLocationDropdown().selectByValue(employeeData[1][0]);
								
		addEmployeePOM.getEmployeeFullName().sendKeys(employeeData[1][1]+FunctionUtils.generateRandomNumber());
		
		addEmployeePOM.getEmployeeID().sendKeys(employeeData[1][2]+FunctionUtils.generateRandomNumber());
		
		addEmployeePOM.getsupervisorDropdown().selectByVisibleText(employeeData[1][3]);
		
		addEmployeePOM.getbusinessUnitDropdown().selectByIndex(1);
		
		addEmployeePOM.getDepartmentDropdown().selectByVisibleText(employeeData[1][5]);	
		
		sleep(3);	
		
		addEmployeePOM.getAddBtn().click();	
		
		waitTillElementVisible(addEmployeePOM.getJobCodesTab());
		
		addEmployeePOM.getjobCodeSeniorTechnologist().click();
		
		waitTillElementVisible(addEmployeePOM.getSelectedJobCode());
		
		addEmployeePOM.getAddBtn().click();	
						
		addEmployeePOM.verifySuccessMessage();
		
		addEmployeePOM.getCloseButtononSuccessMessage().click();		
		
		sleep(2);
		
		log.info("Now calling logout function.");
		
		logout=new LogoutPage_POM(driver);
			
		logout.logoutFunction();		
		
		extent.endTest(test);
	}	
		
	@AfterClass
	public void closeBrowserInstance()
	{		
		driver.close();
	}
	
}
