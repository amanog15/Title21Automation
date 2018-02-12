package org.title21.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AdminCreateDeleteGroups_POM;
import org.title21.POM.AutomaticallyLoggedOut_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;
public class AutomaticallyLoggedOut_scenario1_Test extends BaseClass {

	LoginPage_POM login; 
	LogoutPage_POM logout;
	String className="";
	AutomaticallyLoggedOut_POM automaticallyLoggedOut;
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}
	
	@Test(testName = "AutomaticallyLoggedOut", groups = "LogOut", priority = 0)
	public void CreateGroupInAdmin() 
	{
		automaticallyLoggedOut = new AutomaticallyLoggedOut_POM(driver);
		
		if(automaticallyLoggedOut.verifyInactivityWarningPopUp()) 
		{
			test.log(LogStatus.PASS, "Successfully verified Inactivity Warning PopUp."+
					test.addScreenCapture(captureScreenShot(driver, "Inactivity Warning PopUp")));
			
			if(automaticallyLoggedOut.verifyInactivityWarningMsg()) 
			{
				test.log(LogStatus.PASS, "Successfully verified Inactivity Warning pop-up massege."+
						test.addScreenCapture(captureScreenShot(driver, "Inactivity Warning pop-up massege")));
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to verified Inactivity Warning pop-up massege."+
						test.addScreenCapture(captureScreenShot(driver, "Inactivity Warning pop-up massege")));
			}
			
			if(automaticallyLoggedOut.continueButton() != null) 
			{
				test.log(LogStatus.PASS, "Successfully verified 'Continue' button on pop-up."+
						test.addScreenCapture(captureScreenShot(driver, "Continue button")));
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to verified 'Continue' button on pop-up."+
						test.addScreenCapture(captureScreenShot(driver, "Continue button")));
			}
			
			if(automaticallyLoggedOut.logOutButton() != null) 
			{
				test.log(LogStatus.PASS, "Successfully verified 'LogOut' button on pop-up."+
						test.addScreenCapture(captureScreenShot(driver, "LogOut button")));
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to verified 'LogOut' button on pop-up."+
						test.addScreenCapture(captureScreenShot(driver, "LogOut button")));
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to verified Inactivity Warning Pop-Up."+
					test.addScreenCapture(captureScreenShot(driver, "Inactivity Warning PopUp")));
		}
		
	//	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		if(automaticallyLoggedOut.verifyTimeoutScreen())
		{
			test.log(LogStatus.PASS, "Successfully verified 'Timeout Screen' after pop-up dismissed."+
					test.addScreenCapture(captureScreenShot(driver, "Timeout Screen")));
			
			if(automaticallyLoggedOut.verifySecurityReasonsText()) 
			{
				test.log(LogStatus.PASS, "Successfully verified security reasons text."+
						test.addScreenCapture(captureScreenShot(driver, "Security Reasons Text")));
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to verified security reasons text."+
						test.addScreenCapture(captureScreenShot(driver, "Security Reasons Text")));
			}
			
			automaticallyLoggedOut.clickHereToLogin().click();
			test.log(LogStatus.PASS, "Successfully clicked on 'click Here to Login'.");
			
			if(login.getLogin_button() != null) 
			{
				test.log(LogStatus.PASS, "Successfully navigated to Login Screen."+
						test.addScreenCapture(captureScreenShot(driver, "Login Screen")));
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to navigated to Login Screen."+
						test.addScreenCapture(captureScreenShot(driver, "Login Screen")));
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to verified 'Timeout Screen' after pop-up dismissed."+
					test.addScreenCapture(captureScreenShot(driver, "Timeout Screen")));
		}
	}
	
	/*@Test(testName = "logout_admin", groups = "Logout", priority = 1)
	public void LogoutFromAdmin() throws Exception 
	{		
		logout=new LogoutPage_POM(driver);
		logout.logoutFunction();		
	}*/
	
	@AfterClass
	public void closeBrowserInstance() 
	{
		driver.close();
	}
}
