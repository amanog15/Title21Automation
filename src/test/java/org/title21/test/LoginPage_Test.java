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
	@Test(testName = "login_admin", groups = "Logins", priority = 0)
	public void LoginWithInvalidCredentials() throws Exception 
	{
		test = extent.startTest(loginData[0][2]);		
		test.log(LogStatus.PASS, "Opened URL");
		login= new LoginPage_POM(driver);
		login.getLogin_button().click();
		
		sleep(2);
		
		if (login.verifyUserIDValidationMessage(driver)){
			test.log(LogStatus.PASS, "System displays validation message with blank username."+
			test.addScreenCapture(captureScreenShot(driver, "withBlankUsername")));
		}
		
		//login.sendKeys(loginData[0][0]);
		login.getUsername().sendKeys(loginData[0][0]);
		test.log(LogStatus.PASS, "Username Entered");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "Clicked on Login button after entering Username.");
		test.addScreenCapture(captureScreenShot(driver, "AfterEnteringProperUsername"));
		login.getLogin_button().click();
		sleep(2);
		
		if (login.verifyPasswordValidationMessage(driver)){
			test.log(LogStatus.PASS, "System displays validation message with blank password"+
			test.addScreenCapture(captureScreenShot(driver, "MessageWithblankPassword")));
		}
		
		login.getpassword().sendKeys(loginData[0][1]);
		test.log(LogStatus.PASS, "Incorrect Password Entered");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "Clicked on Login Button.");
		
		if (login.verifyPasswordErrorMessage(driver)){			
			test.log(LogStatus.PASS, "Verify error message with incorrect password."+
			test.addScreenCapture(captureScreenShot(driver, "PasswordErrorMessageSuccess")));
		}else{			
			throw new Exception("Password message not matched.");			
		};
		
		extent.endTest(test);
	}	
	
	@Test(testName = "login_admin", groups = "Logins", priority = 1)
	public void LoginValidPassword() 
	{
		test = extent.startTest(loginData[1][2]);
		login.getpassword().sendKeys(loginData[1][1]);
		test.log(LogStatus.PASS, "Correct password Entered.");
		login.getLogin_button().click();
		test.log(LogStatus.PASS, "Clicked on Login button."+
		test.addScreenCapture(captureScreenShot(driver, "View after Loggedin.")));		
		extent.endTest(test);
	}
	@Test(testName = "login_admin", groups = "Logins", priority = 2)
	public void VerifyUserLoggedin() 
	{
		test = extent.startTest("Successful Login with valid credentials.");
		login.getLogin_button().click();
		waitForPageToLoad(driver,4);		
		test.log(LogStatus.PASS, "Verifying DashBord");
		dashboardObj = new DashBord_POM(driver); 
		if (dashboardObj.verifyDashboardPrescence()){;
			test.log(LogStatus.PASS, "DashBord is displayed After Login.");
		};
		
		test.log(LogStatus.PASS,"verifying Administrator's Dashboard header text");
		if (dashboardObj.verifyHeaderStyle()){;
			test.log(LogStatus.PASS,"Sucessfully displayed Administrator's Dashboard header text");
     	};
		extent.endTest(test);
	}
	@AfterClass
	public void closeBrowserInstance() 
	{
		driver.close();
	}

	
	
}
