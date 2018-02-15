package org.title21.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AddNewUser_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;

public class AddNewUser_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	AddNewUser_POM addNewUserPage;
	String className="";
	String number="";
	
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
		number = FunctionUtils.generateRandomNumber();
		
		getAdministrationPage();	
		
		addNewUserPage.user_link().click();
		test.log(LogStatus.PASS, "Clicked on 'Users'.");
		
		addNewUserPage.addNew_User().click();
		test.log(LogStatus.PASS, "Successfully click on 'Add New' link.");
		
		sleep(3);
		
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
				
				sleep(2);
				
				addNewUserPage.userFullName_Dopdown().selectByVisibleText("Martink401");
				test.log(LogStatus.PASS, "Selected 'Martink401' as a full name.");
				
				addNewUserPage.username_textbox().sendKeys(userData[0][1]);
				test.log(LogStatus.PASS, "Selected 'Mart' as a user name."+
				test.addScreenCapture(captureScreenShot(driver, "Add New User")));
				
				sleep(2);
				addNewUserPage.available_Filter().sendKeys("Dallas");
				test.log(LogStatus.PASS, "Selected 'Dallas' for filter.");
				
				String list = addNewUserPage.available_List().getText();
				
				sleep(2);
				
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
							sleep(3);
							addNewUserPage.check_AuthenticationType().selectByVisibleText("Title21");
							test.log(LogStatus.PASS, "Authentication Type:Title21");
							
							addNewUserPage.new_PasswordInput().sendKeys("1234567890");
							test.log(LogStatus.PASS, "New Password:1234567890");
							
							addNewUserPage.check_StrengthButton().click();
							test.log(LogStatus.PASS, "Clicked to Strength button");
							
							String firstMsgColor = "";
							String secondLineColor = "";
							
							if(addNewUserPage.passwordMust_PopUp() != null) 
							{
								//not Completed 
								firstMsgColor = addNewUserPage.tenCharacters_Msg().getCssValue("color");
								System.out.println(firstMsgColor);
								secondLineColor = addNewUserPage.strengthLeastOne_Msg().getCssValue("color");
								System.out.println(secondLineColor);
								
								if(firstMsgColor.equals(secondLineColor))
								{
									test.log(LogStatus.PASS, "'Contain at least 10 characters.' text is in green color."+
											test.addScreenCapture(captureScreenShot(driver, "Pop Up")));
								}
								else
								{
									test.log(LogStatus.PASS, "Unable to find 'Contain at least 10 characters.' text is in green color."+
											test.addScreenCapture(captureScreenShot(driver, "Pop Up")));
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Unable to find 'Password must' pup-up."+
										test.addScreenCapture(captureScreenShot(driver, "Pop Up")));
							}
							addNewUserPage.confirm_PasswordInput().sendKeys("1234567890");
							test.log(LogStatus.PASS, "Confirm Password:1234567890"+
							test.addScreenCapture(captureScreenShot(driver, "Selected list")));
							
							addNewUserPage.password_AddTab().click();
							addNewUserPage.password_AddTab().click();
							test.log(LogStatus.PASS, "Clicked on 'Add' button.");
							
							sleep(3);
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
		sleep(3);
		logout=new LogoutPage_POM(driver);
		logout.logoutFunction();		
	}
	
	@AfterClass
	public void closeBrowserInstance()
	{		
		driver.close();
	}
		
}
