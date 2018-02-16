package org.title21.test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.AddEmployee_POM;
import org.title21.POM.DashBord_POM;
import org.title21.POM.Delete_Employee_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;


public class Delete_Employee_Test  extends BaseClass {
	LoginPage_POM login; 
	LogoutPage_POM logout;
	Delete_Employee_POM emp;
	DashBord_POM dashboardObj;
	SoftAssert softAssertion=new SoftAssert();
	String className="";
	String employeeID="";
	boolean EmployeePresenceAfterSearch = false;
	static Logger log = Logger.getLogger(Delete_Employee_Test.class);
	
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "delete_employee ", groups = "delete_Employee", priority = 0)
	public void Edit_general_Employee() throws Exception {
		test = extent.startTest("Delete Employee");
		getAdministrationPage(test);
		emp=new Delete_Employee_POM (driver);
		sleep(1);
    	AdminData adminData=new AdminData();
		System.out.print(adminData.getEmployeeName());
		if(emp.EmployeeFilterResult() != null)
		{
		   emp.EmployeeFilterResult().sendKeys(adminData.getEmployeeName());//Remove Employee
			sleep(2);
			emp.EmployeeFilterResutGoButton().click();
			for(int i=1; i<=10; i++ ) {
				sleep(1);
				String Employees = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[1]")).getText();
				sleep(1);
				if(Employees.equalsIgnoreCase(adminData.getEmployeeName()))
				{
				EmployeePresenceAfterSearch = true;
					break;
				}
				
				if(EmployeePresenceAfterSearch) {
					test.log(LogStatus.PASS, "Employee verified."+
							test.addScreenCapture(captureScreenShot(driver, "Employee verified")));
				}else {
					test.log(LogStatus.FAIL, "Unable to verify Employee"+
							test.addScreenCapture(captureScreenShot(driver, "Unable to verify Employee")));
				}
				
			}
			
		}
		
		for(int i=1; i<=10; i++) 
		{
			
			WebElement employee = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[1]"));
			String empName= employee.getText();
			
			if(empName.equalsIgnoreCase(adminData.getEmployeeName())) {
				
				WebElement delete = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]//span[@title='Remove Employee']"));
				test.log(LogStatus.PASS, "Clicked to remove employee");
				delete.click();
				break;
			}
		}
		if(emp.verifyDeleteEmployeePopUp()) 
			
		test.log(LogStatus.PASS, "Verified Delete Employee Pop-Up."+
				test.addScreenCapture(captureScreenShot(driver, "Verified Delete Employee Pop-Up.")));
		sleep(5);
		emp.deleteEmployeePopUpYesButton().click();
		sleep(2);
		emp.verifyDeleteEmployeecConfirmPopUpText();
		sleep(2);
		test.log(LogStatus.PASS, "Verified Delete Employee Confirm Pop-Up"+
				test.addScreenCapture(captureScreenShot(driver, "Verified Delete Employee Confirm Pop-Up.")));
		emp.ConfirmPopUpCloseButton().click();
		
		
		if(emp.verifyDeleteEmployeePopUp()) 					{
			
			test.log(LogStatus.PASS, "Verified Delete Employee Pop-Up."+
					test.addScreenCapture(captureScreenShot(driver, "Verified Delete Employee Pop-Up.")));
			
			emp.deleteEmployeePopUpYesButton().click();
			sleep(2);
			emp.verifyDeleteEmployeecConfirmPopUpText();
			sleep(2);
			test.log(LogStatus.PASS, "Verified Delete Employee Confirm Pop-Up"+
					test.addScreenCapture(captureScreenShot(driver, "Verified Delete Employee Confirm Pop-Up.")));
			emp.ConfirmPopUpCloseButton().click();
			sleep(2);
			System.out.print("1 step");
			if(emp.EmployeeFilterResult() != null)
			{
				emp.EmployeeFilterResult().clear();
				emp.EmployeeFilterResult().sendKeys(adminData.getEmployeeName());
				sleep(3);
				emp.EmployeeFilterResutGoButton().click();
				
				sleep(2);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				if(emp.verifyNoEmployeeFoundText(driver))
				{
					test.log(LogStatus.PASS, "Successfully deleted Employee and verified."+
							test.addScreenCapture(captureScreenShot(driver, "Unable to verify created Employee")));
				}else {
					test.log(LogStatus.FAIL, "Successfully deleted Employee and verified."+
							test.addScreenCapture(captureScreenShot(driver, "Unable to verify created Employee")));
					
				}
			}
		}
			sleep(6);
			
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
	

