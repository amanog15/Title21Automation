package org.title21.test;

import org.testng.annotations.Test;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;

public class AccountLockout_Test extends BaseClass
{
	LoginPage_POM login; 
	LogoutPage_POM logout;
	DashBord_POM dashboardObj;
	String className="";

	@Test(testName = "AccountLockOut", groups = "LockOut", priority = 0)
	public void accountLockout()
	{
		test = extent.startTest("Account Lockout");
		test.log(LogStatus.PASS,"1.	Enter the URL in the browser to bring up the web interface login page.");
		login.loginUser(loginData[2][0], loginData[2][1]);
		test.log(LogStatus.PASS,"2.	Enter a valid test username and password");
		test.log(LogStatus.PASS,"<b>ER1: User is able to login with the correct username and password.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "SuccessfulLogin")));


		logout.logoutFunction();
		test.log(LogStatus.PASS,"3.	Log out of the web interface");

		login.getUsername().sendKeys(loginData[2][0]);
		login.getLogin_button().sendKeys(Keys.RETURN);
		
		for (int i = 1; i <= 9; i++)
		{
			login.getpassword().sendKeys(loginData[2][2]);
			login.getLogin_button().sendKeys(Keys.RETURN);
			test.log(LogStatus.PASS,"Invalid Login No:"+i);
			assertEquals(driver.findElement(By.xpath(".//*[@id='login_panel']/form/div[3]/span")).getText(),
					"Invalid credentials. Please try again or contact your System Administrator for assistance.");
		}
		test.log(LogStatus.PASS,"4.	Enter the test username with an incorrect password eight times consecutively");
		test.log(LogStatus.PASS,"<b>ER2: On entering the incorrect password eight times the user is locked out and asked to contact the administrator.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "AccountLocked")));
		
		login.getpassword().sendKeys(loginData[2][1]);
		login.getLogin_button().sendKeys(Keys.RETURN);
		test.log(LogStatus.PASS,"5.	Login again, but now with the correct password.");
		test.log(LogStatus.PASS,"<b>ER3: The user remains locked out and cannot login with the correct password.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "UnableToLogin")));
		
		extent.endTest(test);
	}

	@BeforeClass
	public void beforeClass()
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		logout=new LogoutPage_POM(driver);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass()
	{
		driver.close();
	}

}
