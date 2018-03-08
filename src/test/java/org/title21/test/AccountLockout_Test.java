package org.title21.test;

import org.testng.annotations.Test;
import org.title21.POM.AddNewUser_POM;
import org.title21.POM.AdministrationPage_POM;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.UpdateUser_POM;
import org.title21.utility.BaseClass;
import org.title21.validation.entities.ErrorMessages;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;

public class AccountLockout_Test extends BaseClass
{
	LoginPage_POM login; 
	LogoutPage_POM logout;
	AdministrationPage_POM adminpage;
	AddNewUser_POM adduser;
	UpdateUser_POM updateuser;
	DashBord_POM dashboardObj;
	String className="";

	@Test(testName = "AccountLockOut", groups = "LockOut", priority = 0)
	public void accountLockout()
	{
		test = extent.startTest("Account Lockout");
		test.log(LogStatus.PASS,"1.	Enter the URL in the browser to bring up the web interface login page.");
		login.loginUser(loginData[2][0], loginData[2][1]);

		if (login.verifyPasswordErrorMessagePresence()==true)
		{
			login.getUsername().clear();
			login.loginFunction();
			logout.getAdmindropdown().click();	
			sleep(1);
			adminpage.administrationLink().click();
			sleep(1);
			adduser.user_link().click();
			sleep(2);
			updateuser.unlockUser(loginData[2][0]);
			logout.logoutFunction();
			login.loginUser(loginData[2][0], loginData[2][1]);

			updateuser.iAgree_Button().click();
		}
		else
		{
			if (updateuser.verifyIAgreePresence()==true)
			{
				updateuser.iAgree_Button().click();
			}		
		}
		
		test.log(LogStatus.PASS,"2.	Enter a valid test username and password");
		test.log(LogStatus.PASS,"<b>ER1: User is able to login with the correct username and password.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "SuccessfulLogin")));

		logout.logoutFunction();
		test.log(LogStatus.PASS,"3.	Log out of the web interface");

		login.getUsername().sendKeys(loginData[2][0]);
		login.getLogin_button().sendKeys(Keys.RETURN);
		test.log(LogStatus.PASS,"4.	Enter the test username with an incorrect password eight times consecutively");
		int invalidLoginCounter=1;

		for (char i = 'a'; i <'i'; i++)
		{
			login.getpassword().sendKeys(loginData[3][1]);
			login.getLogin_button().sendKeys(Keys.RETURN);
			test.log(LogStatus.PASS,"4"+i+" Invalid Login No: "+invalidLoginCounter);			
			assertEquals(login.getPasswordErrorMessage().getText(), ErrorMessages.passworderrormessages);
			invalidLoginCounter++;			
		}

		test.log(LogStatus.PASS,"<b>ER2: On entering the incorrect password eight times the user is locked out and asked to contact the administrator.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "AccountLocked")));

		login.getpassword().sendKeys(loginData[2][1]);
		login.getLogin_button().sendKeys(Keys.RETURN);
		test.log(LogStatus.PASS,"5.	Login again, but now with the correct password.");
		assertEquals(login.getPasswordErrorMessage().getText(), ErrorMessages.passworderrormessages);
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
		adminpage = new AdministrationPage_POM(driver);
		adduser = new AddNewUser_POM(driver);
		updateuser = new UpdateUser_POM(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass()
	{
		driver.close();
	}
}