package org.title21.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AdministrationPage_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class AdministratonPage_Test extends BaseClass {

	String className="";
	AdministrationPage_POM administrationPage;
	LoginPage_POM login;
	public LogoutPage_POM logout;

	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "Administration_Page", groups = "AdministrationPage", priority = 0)
	public void AdministrationPage() 
	{
		test = extent.startTest("Administration_Page");
		administrationPage= new AdministrationPage_POM(driver);
		
		String administratorTab = administrationPage.administratorDropDown().getText();
		
		if(administratorTab.contains("Administrator"))
		{
			administrationPage.administratorDropDown().click();
			test.log(LogStatus.PASS, "Successfully click on 'administrator'"+
					test.addScreenCapture(captureScreenShot(driver, "administrator")));
			
			administrationPage.administrationLink().click();
			test.log(LogStatus.PASS, "Successfully click on 'administration' link."+
					test.addScreenCapture(captureScreenShot(driver, "administration' link.")));
			
			if(administrationPage.verifyAdministrationPagePrescence()) {
				test.log(LogStatus.PASS, "Successfully verify 'administration Page' Prescence."+
						test.addScreenCapture(captureScreenShot(driver, "administration Page' Prescence")));
			}else {
				test.log(LogStatus.FAIL, "Unable to verify 'administration Page' Prescence."+
						test.addScreenCapture(captureScreenShot(driver, "administration Page' Prescence")));
			}
			test.addScreenCapture(captureScreenShot(driver, "AfterEnteringProperUsername"));
		}else{
			
			test.log(LogStatus.FAIL, "Unable to find 'Groups' tab");
			
		}
		extent.endTest(test);
	}
	
	@Test(testName = "logout_admin", groups = "Logout", priority = 1)
	public void LogoutFromAdmin() throws Exception 
	{		
		logout=new LogoutPage_POM(driver);
		logout.logoutFunction();		
	}
	
	@AfterClass
	public void closeBrowserInstance() 
	{
		driver.close();
	}
	


}

