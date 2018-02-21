package org.title21.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class LoginPage_Test extends BaseClass {
	LoginPage_POM login; 
	DashBord_POM dashboardObj;
	SoftAssert softAssertion=new SoftAssert();
	String className="";
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		
	}
	@Test(testName = "login-to-app", groups = "Logins", priority = 0)
	public void LoginWithInvalidCredentials() throws Exception 
	{
		test = extent.startTest("TestCase-WIA-Login restrictions");		
		test.log(LogStatus.PASS, "1. Enter the URL in the browser to bring up the web interface login page.");
		login= new LoginPage_POM(driver);
		login.getLogin_button().click();
		
		sleep(2);
		
		if (login.verifyUserIDValidationMessage(driver)){
			test.log(LogStatus.PASS, "2. Click on Log in button without a user name."+
			test.addScreenCapture(captureScreenShot(driver, "withBlankUsername")));
		}
		
		//login.sendKeys(loginData[0][0]);
		login.getUsername().sendKeys(loginData[0][0]);
		test.log(LogStatus.PASS, "2a Username Entered");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "3. Enter a valid user name for a user within in the system, click on Log in button");
		test.addScreenCapture(captureScreenShot(driver, "AfterEnteringProperUsername"));
		login.getLogin_button().click();
		sleep(2);
		
		if (login.verifyPasswordValidationMessage(driver)){
			test.log(LogStatus.PASS, "4. Click on Log in button without password"+
			test.addScreenCapture(captureScreenShot(driver, "MessageWithblankPassword")));
		}
		
		login.getpassword().sendKeys(loginData[0][1]);
		test.log(LogStatus.PASS, "ER2: User is informed of missing Password.");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "Clicked on Login Button.");
		
		if (login.verifyPasswordErrorMessage(driver)){			
			test.log(LogStatus.PASS, "5. Enter an incorrect password, and click on Log in button."+
			test.addScreenCapture(captureScreenShot(driver, "PasswordErrorMessageSuccess")));
		}else{			
			throw new Exception("Password message not matched.");			
		};
		
		login.getUsername().clear();
		login.getUsername().sendKeys(loginData[1][0]);
		login.getpassword().sendKeys(loginData[1][1]);
		test.log(LogStatus.PASS, "6. Enter the correct user name and password.");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "6a) Clicked on Login button."+
		test.addScreenCapture(captureScreenShot(driver, "View after Loggedin.")));
		
		login.getLogin_button().click();
		waitForPageToLoad(driver,4);		
		test.log(LogStatus.PASS, "6b) Verifying DashBord after user loggedin.");
		dashboardObj = new DashBord_POM(driver); 
		if (dashboardObj.verifyDashboardPrescence()){;
			test.log(LogStatus.PASS, "6c DashBord is displayed After Login.");
		};
			
		if (dashboardObj.verifyHeaderStyle()){;
			test.log(LogStatus.PASS,"6d) Dashboard header text is displayed means User loggedin successfully.");
     	};
     	
		extent.endTest(test);
	}	
	
	/*@Test(testName = "login_admin", groups = "Logins", priority = 1)
	public void LoginValidPassword() 
	{
		test = extent.startTest(loginData[1][2]);
				
		extent.endTest(test);
	}
	@Test(testName = "login_admin", groups = "Logins", priority = 2)
	public void VerifyUserLoggedin() 
	{
		test = extent.startTest("Successful Login with valid credentials.");
		
		extent.endTest(test);
	}*/
	
	@AfterClass
	public void closeBrowserInstance() 
	{
		driver.close();
	}

	
	
}
