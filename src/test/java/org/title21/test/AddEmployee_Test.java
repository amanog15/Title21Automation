package org.title21.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.AddEmployee_POM;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.dao.AdminData;
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
	String employeeID="";
	static Logger log = Logger.getLogger(AddEmployee_Test.class);
	AdminData adminData=new AdminData();	
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "AddEmployee", groups = "Employee", priority = 0)
	public void createEmployee() throws Exception 
	{	
		
					
		getAdministrationPage();
		test = extent.startTest("Add Employee");
		
		addEmployeePOM=new AddEmployee_POM(driver);
		addEmployeePOM.employees_link().click();
		
		log.info("Now clicking on Add new Link");
		addEmployeePOM.addNewLink().click();
		
		addEmployeePOM=new AddEmployee_POM(driver);
		
		if (addEmployeePOM.getEmployeeFullName().isDisplayed()){
			test.log(LogStatus.PASS, "Add employee popup opened with General TAB opened"+
					test.addScreenCapture(captureScreenShot(driver, "employeepopup")));
		}
		
		log.info("First checking Validation Messages. without entering in any"
				+ "field, click on Add button.");		
		
		virtualScrolling();
		waitTillElementVisible(addEmployeePOM.getAddBtn());
		log.info("scrolling down to click on Add button.");
		
		//addEmployeePOM.getAddBtn().click();	
		javaScriptClick(addEmployeePOM.getAddBtn());
		
		if (addEmployeePOM.verifyAddEmployeeValidationMessages()){
			test.log(LogStatus.PASS, "All validation messages are present."+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessages"))
					);
		}
		else
		{
			test.log(LogStatus.FAIL, "All validation messages are not present. see the screenshot"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure"))
		     );
		}
				
		log.info("Entering data in form.");
		
		sleep(2);			
		addEmployeePOM.getLocationDropdown().selectByValue(employeeData[1][0]);		
								
		employeeID=employeeData[1][1]+FunctionUtils.generateRandomNumber();
		
		addEmployeePOM.getEmployeeFullName().sendKeys(employeeID);
		
		log.info("String employeeID using getter&Setter so it will be helpful in future implementation.");
		
		adminData.setEmployeeID(employeeID);
		
		addEmployeePOM.getEmployeeID().sendKeys(employeeData[1][2]+FunctionUtils.generateRandomNumber());
		
		addEmployeePOM.getsupervisorDropdown().selectByVisibleText(employeeData[1][3]);
		
		addEmployeePOM.getbusinessUnitDropdown().selectByIndex(1);
		
		addEmployeePOM.getDepartmentDropdown().selectByVisibleText(employeeData[1][5]);	
		
		addEmployeePOM.getAddressField().sendKeys(employeeData[1][6]);
		
		addEmployeePOM.getEmployeeCity().sendKeys(employeeData[1][7]);
		
		addEmployeePOM.getEmployeeState().sendKeys(employeeData[1][8]);
		
		addEmployeePOM.getEmployeePostalCode().sendKeys(employeeData[1][9]);
		
		addEmployeePOM.getEmployeeCountry().sendKeys(employeeData[1][10]);
		
		addEmployeePOM.getEmployeePhone().sendKeys(employeeData[1][11]);
		
		addEmployeePOM.getEmployeeemail().sendKeys(employeeData[1][12]);
		
		test.log(LogStatus.PASS, "All employee data has been entered."+
				test.addScreenCapture(captureScreenShot(driver, "employeeData")));
		
		virtualScrolling();
		waitTillElementVisible(addEmployeePOM.getAddBtn());
				
		//addEmployeePOM.getAddBtn().click();	
		javaScriptClick(addEmployeePOM.getAddBtn());
		
		waitTillElementVisible(addEmployeePOM.getJobCodesTab());
		
		waitTillElementVisible(addEmployeePOM.getjobCodeSeniorTechnologist());
		
		addEmployeePOM.getjobCodeSeniorTechnologist().click();
		
		waitTillElementVisible(addEmployeePOM.getSelectedJobCode());
		
		addEmployeePOM.getAddBtn().click();	
						
		if (addEmployeePOM.verifySuccessMessage()){
			
			test.log(LogStatus.PASS, "Employee created successfully"+
			test.addScreenCapture(captureScreenShot(driver, "Employee added successfully.")));
		};
		
		log.info("employee added successfully.");
		
		waitTillElementVisible(addEmployeePOM.getCloseButtononSuccessMessage());
		
		addEmployeePOM.getCloseButtononSuccessMessage().click();		
		
		sleep(2);
		
		log.info("Now calling logout function.");
		
		logout=new LogoutPage_POM(driver);
			
		logout.logoutFunction();
		
		log.info("logout successfully.");
		
		extent.endTest(test);		
		
	}	
		
	@AfterClass
	public void closeBrowserInstance()
	{		
		driver.close();
	}

	
	
}
