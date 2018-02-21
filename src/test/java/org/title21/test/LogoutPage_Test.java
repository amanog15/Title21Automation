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
		
		test = extent.startTest("TestCase_WIA_Logout");
		test.log(LogStatus.PASS, "1. Begin Login to the web interface as the Admin.");
		login= new LoginPage_POM(driver);
		login.getUsername().sendKeys("admin");
		test.log(LogStatus.PASS, "1b) Username Entered");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "1c) Clicked on Login button after entering Username.");
		login.getpassword().sendKeys("administrator");
		test.log(LogStatus.PASS, "1d) Password Entered");
		login.getLogin_button().click();
		logout=new LogoutPage_POM(driver);
		logout.getAdmindropdown().click();
		test.log(LogStatus.PASS, "2. Click on Administrator Dropdown at the top");
		logout.getlogoutLink().click();
		
		test.log(LogStatus.PASS, "3. Click on Log out link."+
		test.addScreenCapture(captureScreenShot(driver, "clickonLogoutlink")));	
		
		sleep(2);
		test.log(LogStatus.INFO,"ER 1 – User should be able to navigate Confirm Log out pop up Screen.");
		
		if (logout.verifyMessageonModalDialog(driver)){
			test.log(LogStatus.PASS, "Message on Logout alert verified."+
			test.addScreenCapture(captureScreenShot(driver, "Logout Alert")));
		};
		
		logout.getLogoutButton().click();
		test.log(LogStatus.PASS, "5. Click on Log out button in Confirmation Log out screen"+ 
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
