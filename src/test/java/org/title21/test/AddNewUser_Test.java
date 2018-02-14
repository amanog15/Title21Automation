package org.title21.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AddNewUser_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class AddNewUser_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	AddNewUser_POM addNewUserPage;
	String className="";
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "Add New User", groups = "New User", priority = 0)
	public void AddNewUser() throws Exception
	{
		test = extent.startTest("AddNewUser_Test");
		addNewUserPage= new AddNewUser_POM(driver);
		
		getAdministrationPage();	
		
		addNewUserPage.user_link().click();
		test.log(LogStatus.PASS, "Clicked on 'Users'.");
		
		addNewUserPage.addNew_User().click();
		test.log(LogStatus.PASS, "Successfully click on 'Add New' link.");
		
		if(addNewUserPage.verifyAddNewUserPopUpHeader(driver))
		{
			test.log(LogStatus.PASS, "Successfully verified 'Add New User' pop-up header."+
					test.addScreenCapture(captureScreenShot(driver, "Add New User")));
			
			if(addNewUserPage.general_tab() != null) 
			{
				test.log(LogStatus.PASS, "Successfully verified 'general' tab.");

				//read data from excel
				addNewUserPage.location_Dropdown().selectByVisibleText("Dallas");
				test.log(LogStatus.PASS, "Selected 'Dallas' as a location.");
				
				addNewUserPage.userFullName_Dopdown().selectByVisibleText("Martink401");
				test.log(LogStatus.PASS, "Selected 'Martink401' as a full name.");
				
				addNewUserPage.username_textbox().sendKeys("Mart");
				test.log(LogStatus.PASS, "Selected 'Mart' as a user name."+
				test.addScreenCapture(captureScreenShot(driver, "Add New User")));
				
				addNewUserPage.available_Filter().sendKeys("Dallas");
				test.log(LogStatus.PASS, "Selected 'Dallas' for filter.");
				
				String list = addNewUserPage.available_List().getText();
				
				if(list.contains("Dallas"))
				{
					addNewUserPage.available_Button().click();
					test.log(LogStatus.PASS, "Clicked 'Available' button.");
					
					String selectedList = addNewUserPage.selected_List().getText();
					
					if(selectedList.contains("Dallas"))
					{
						test.log(LogStatus.PASS, "Selected list."+
						test.addScreenCapture(captureScreenShot(driver, "Selected list")));
						addNewUserPage.add_GeneralButton().click();
						
						if(addNewUserPage.password_tab() != null)
						{
							test.log(LogStatus.PASS, "Successfully verified 'password' tab.");
							
							addNewUserPage.check_AuthenticationType().selectByVisibleText("Title21");
							test.log(LogStatus.PASS, "Authentication Type:Title21");
							
							addNewUserPage.new_PasswordInput().sendKeys("1234567890");
							test.log(LogStatus.PASS, "New Password:Title21");
							
							addNewUserPage.confirm_PasswordInput().sendKeys("1234567890");
							test.log(LogStatus.PASS, "Confirm Password:Title21"+
							test.addScreenCapture(captureScreenShot(driver, "Selected list")));
							
							addNewUserPage.password_AddTab().click();
							
							if(addNewUserPage.confirmHeader_Msg() != null)
							{
								test.log(LogStatus.PASS, "Successfully verified confirm pop-up"+
										test.addScreenCapture(captureScreenShot(driver, "Confirmed pop-up")));
								addNewUserPage.confirmClose_Button().click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Successfully verified confirm pop-up");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "Unable to verified 'password' tab.");
						}
						
					}
					else
					{
						test.log(LogStatus.FAIL, "Unable to select list.");
					}
				}
				else
				{
					test.log(LogStatus.FAIL, "Unable to clicked 'Available' button.");
					addNewUserPage.cancel_GeneralButton().click();
				}
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to verified 'general' tab");
			}
			
			
		}else {
			test.log(LogStatus.FAIL, "Unable to verified 'Add New User' pop-up header."+
					test.addScreenCapture(captureScreenShot(driver, "Add New User")));
		}
		
		
		
		extent.endTest(test);
	}
	
	@Test(testName = "logout", groups = "Logout", priority = 1)
	public void Logout() throws Exception 
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
