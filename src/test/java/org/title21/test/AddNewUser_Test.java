package org.title21.test;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AddNewUser_POM;
import org.title21.POM.AdministrationPage_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class AddNewUser_Test extends BaseClass{
	LoginPage_POM login; 
	String className="";
	AddNewUser_POM addNewUserPage;
	//int[][] userData = new int[3][5]; 
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "AddNewUser_Page", groups = "AddNewUser", priority = 0)
	public void AddNewUser() 
	{
		test = extent.startTest("AddNewUser_Page");
		addNewUserPage= new AddNewUser_POM(driver);
		
		String administratorTab = addNewUserPage.administrator_dropdown().getText();
		
		if(administratorTab.contains("Administrator"))
		{
			addNewUserPage.administrator_dropdown().click();
			test.log(LogStatus.PASS, "Successfully click on 'administrator'"+
					test.addScreenCapture(captureScreenShot(driver, "administrator")));
			
			addNewUserPage.administration_link().click();
			test.log(LogStatus.PASS, "Successfully click on 'administration' link."+
					test.addScreenCapture(captureScreenShot(driver, "administration' link.")));
			
			addNewUserPage.user_link().click();
			test.log(LogStatus.PASS, "Successfully click on 'user' link."+
					test.addScreenCapture(captureScreenShot(driver, "user' link.")));
			
			addNewUserPage.addNew_User().click();
			test.log(LogStatus.PASS, "Successfully click on 'Add New user' link."+
					test.addScreenCapture(captureScreenShot(driver, "Add New user' link.")));
			
			addNewUserPage.general_tab().click();
			test.log(LogStatus.PASS, "Successfully click on 'User General tab' link."+
					test.addScreenCapture(captureScreenShot(driver, "User General tab' link.")));
			
			addNewUserPage.location_Dropdown().selectByValue(userData[1][0]);
		   test.log(LogStatus.PASS, "Successfully click on 'User General tab' link."+
					test.addScreenCapture(captureScreenShot(driver, "User General tab' link.")));
			
		   
			
		
			
	/*	if(administrationPage.verifyAdministrationPagePrescence()) {
				test.log(LogStatus.PASS, "Successfully verify 'administration Page' Prescence."+
						test.addScreenCapture(captureScreenShot(driver, "administration Page' Prescence")));
			}else {
				test.log(LogStatus.FAIL, "Unable to verify 'administration Page' Prescence."+
						test.addScreenCapture(captureScreenShot(driver, "administration Page' Prescence")));
			}
		*/
			test.addScreenCapture(captureScreenShot(driver, "AfterEnteringProperUsername"));
		}else{
			
			test.log(LogStatus.FAIL, "Unable to find 'Groups' tab");
			
		}
		extent.endTest(test);
	}
	
	
	
}
