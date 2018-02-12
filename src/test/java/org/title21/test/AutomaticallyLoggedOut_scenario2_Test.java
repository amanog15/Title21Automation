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
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;
public class AutomaticallyLoggedOut_scenario2_Test extends BaseClass {

	LoginPage_POM login; 
	LogoutPage_POM logout;
	DashBord_POM dashboardObj;
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
		
		if(automaticallyLoggedOut.continueButton() != null) 
		{
			automaticallyLoggedOut.continueButton().click();
			test.log(LogStatus.PASS, "Successfully clicked on 'Continue Button'."+
					test.addScreenCapture(captureScreenShot(driver, "Continue button")));
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to verified or clicked on 'Continue' button on pop-up."+
					test.addScreenCapture(captureScreenShot(driver, "Continue button")));
		}
		
		if(dashboardObj.verifyDashboardPrescence())
		{
			test.log(LogStatus.PASS, "Successfully verified Dashboard Prescence."+
					test.addScreenCapture(captureScreenShot(driver, "Dashboard")));
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to verified Dashboard Prescence."+
					test.addScreenCapture(captureScreenShot(driver, "Dashboard")));
		}
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
