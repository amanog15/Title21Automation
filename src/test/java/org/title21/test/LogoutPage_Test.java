package org.title21.test;

import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class LogoutPage_Test extends BaseClass {
	
	LoginPage_POM login; 
	LogoutPage_POM logout;
	SoftAssert softAssertion=new SoftAssert();
	String className="";
	
	@BeforeClass
	public void openURL() {
		
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
	}

	@Test(testName = "logout_admin", groups = "Logout", priority = 0)
	public void Logout() throws Exception {
		
		test = extent.startTest("logoutfromApplication");
		test.log(LogStatus.PASS, "Opened URL");
		login= new LoginPage_POM(driver);
		login.getUsername().sendKeys("admin");
		test.log(LogStatus.PASS, "Username Entered");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "Clicked on Login button after entering Username.");
		login.getpassword().sendKeys("administrator");
		test.log(LogStatus.PASS, "Password Entered");
		login.getLogin_button().click();
		logout=new LogoutPage_POM(driver);
		logout.getAdmindropdown().click();	
		test.log(LogStatus.PASS, "Clicked on Administrator dropdown after sucessfully login.");
		logout.getlogoutLink().click();
		
		test.log(LogStatus.PASS, "Clicked on logout link"+
		test.addScreenCapture(captureScreenShot(driver, "clickonLogoutlink")));	
		
		sleep(2);
		
		if (logout.verifyMessageonModalDialog(driver)){
			test.log(LogStatus.PASS, "Message on Logout alert verified."+
			test.addScreenCapture(captureScreenShot(driver, "Logout Alert")));
		};
		
		logout.getLogoutButton().click();
		test.log(LogStatus.PASS, "Clicked on logout button"+
		test.addScreenCapture(captureScreenShot(driver, "ClickOnLogoutButton")));
		extent.endTest(test);
	}
	
	public void verifyLogout() throws Exception{
		
		test = extent.startTest("Verifying Logout");
		waitForPageToLoad(driver,3);
		login.getUsername().sendKeys("admin");
		test.log(LogStatus.PASS, "User logout from the application successfully"+
		test.addScreenCapture(captureScreenShot(driver, "ClickOnLogoutButton")));		
	}
	
	@AfterClass
	public void closeBrowserInstance() 
	{
		driver.close();
	}

}
