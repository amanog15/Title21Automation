package org.title21.test;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AddNewUser_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;

public class AddNewUser_scenario1_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	AddNewUser_POM addNewUserPage;
	String className="";
	String number="";
	String location="";
	String employeeName="";
	String username="";
	String firstMsgColor = "";
	String secondLineColor = "";
	
	boolean UserPresenceAfterSearch = false;
	
	AdminData adminData=new AdminData();
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
		
		test = extent.startTest("AddNewUser_Test(Positive Test)");
		addNewUserPage= new AddNewUser_POM(driver);
		number = FunctionUtils.generateRandomNumber();
		
		getAdministrationPage(test);	
		
		addNewUserPage.user_link().click();
		test.log(LogStatus.PASS, "Clicked on 'Users'.");
		
		sleep(3);
		if(addNewUserPage.userTable_Verifications() != null)
		{
			test.log(LogStatus.PASS, "Successfully verified User records Screen is displayed."+
					test.addScreenCapture(captureScreenShot(driver, "records Screen")));
			
			addNewUserPage.getUserLocation_DropDown().selectByVisibleText(userData[1][0]);
			test.log(LogStatus.PASS, "Successfully Location: "+userData[1][0]+" is selected.");
			
			sleep(3);
			for(int i=1; i<=5; i++ ) {
				
				String userLocation = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[4]")).getText();
				sleep(1);
				if(userLocation.equalsIgnoreCase(userData[1][0])) {
					UserPresenceAfterSearch = true;
					break;
				}
			}
			
			if(UserPresenceAfterSearch) {
				test.log(LogStatus.PASS, "Only Employees of selected location: "+userData[1][0]+" are displayed."+
						test.addScreenCapture(captureScreenShot(driver, "group is created and verified")));
			}else {
				test.log(LogStatus.FAIL, "Unable to verified Employees of selected location: "+userData[1][0]+"."+
						test.addScreenCapture(captureScreenShot(driver, "Employee Details table")));
			}
			
			if(addNewUserPage.groupFilterResult() != null)
			{
				addNewUserPage.groupFilterResult().click();
				addNewUserPage.groupFilterResult().sendKeys("aaaa"+number);
				addNewUserPage.groupFilterResutGoButton().click();
				
				if(addNewUserPage.verifyNoUserFoundText(driver))
				{
					test.log(LogStatus.PASS, "Successfully verified 'No user Found' validation messages, when entered invalide user name."+
							test.addScreenCapture(captureScreenShot(driver, "No User Found")));
				}
				else
				{
					test.log(LogStatus.FAIL, "Unable to verified 'No user Found' validation messages."+
							test.addScreenCapture(captureScreenShot(driver, "No User Found")));
				}
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to verified User 'Filter Result' field."+
						test.addScreenCapture(captureScreenShot(driver, "Filter Result field")));
			}
			
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to verified User records Screen."+
					test.addScreenCapture(captureScreenShot(driver, "records Screen")));
		}
		
		
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

				addNewUserPage.location_Dropdown().selectByVisibleText(userData[1][0]);//Dallas
				
				test.log(LogStatus.PASS, "Selected "+userData[1][0]+" as a location.");
				
				sleep(2);
				
				if(addNewUserPage.alertEMPAssignedMsg(driver))
				{
					test.log(LogStatus.FAIL, "All employees (in the Employee List) have already been assigned a user ID"+
							test.addScreenCapture(captureScreenShot(driver, "Assigned User ID")));
					
					addNewUserPage.confirmClose_Button().click();
					sleep(2);
					addNewUserPage.addNewUserClose_Button().click();
					sleep(3);
				}
				else
				{
					addNewUserPage.userFullName_Dopdown().selectByVisibleText(adminData.getEmployeeName());
					test.log(LogStatus.PASS, "Selected "+adminData.getEmployeeName()+" as a full name.");
					
					addNewUserPage.username_textbox().sendKeys(userData[1][2]+number);//Mart
					test.log(LogStatus.PASS, "Selected "+userData[1][2]+number+" as a user name."+
					test.addScreenCapture(captureScreenShot(driver, "Add New User")));
					
					sleep(2);
					addNewUserPage.available_Filter().sendKeys(userData[1][0]);//Dallas
					test.log(LogStatus.PASS, "Selected "+userData[1][0]+" for filter.");
					
					String list = addNewUserPage.available_List().getText();
					
					sleep(2);
					
					if(list.contains(userData[1][0]))//Dallas
					{
						addNewUserPage.available_Button().click();
						test.log(LogStatus.PASS, "Clicked 'Available' button.");
						
						String selectedList = addNewUserPage.selected_List().getText();
						
						if(selectedList.contains(userData[1][0]))//Dallas
						{
							test.log(LogStatus.PASS, "Selected Group name from the list."+
							test.addScreenCapture(captureScreenShot(driver, "Selected list")));
							addNewUserPage.add_GeneralButton().click();
							
							if(addNewUserPage.password_tab() != null)
							{
								test.log(LogStatus.PASS, "Successfully verified 'password' tab.");
								sleep(3);
								addNewUserPage.check_AuthenticationType().selectByVisibleText(userData[1][4]);//Title21
								test.log(LogStatus.PASS, "Authentication Type:"+userData[1][4]+"");
								
								addNewUserPage.new_PasswordInput().sendKeys(userData[1][5]);
								test.log(LogStatus.PASS, "New Password:"+userData[1][5]+"");
								
								addNewUserPage.check_StrengthButton().click();
								test.log(LogStatus.PASS, "Clicked on Strength button");
								
								sleep(3);
								if(addNewUserPage.passwordMust_PopUp() != null) 
								{
									firstMsgColor = addNewUserPage.tenCharacters_Msg().getCssValue("color");
									
									secondLineColor = addNewUserPage.strengthLeastOne_Msg().getCssValue("color");
																	
									if(firstMsgColor.equals(secondLineColor))
									{
										test.log(LogStatus.PASS, "'Contain at least 10 characters.' text is in green color."+
												test.addScreenCapture(captureScreenShot(driver, "Pop Up")));
									}
									else
									{
										test.log(LogStatus.FAIL, "Unable to find 'Contain at least 10 characters.' text is in green color."+
												test.addScreenCapture(captureScreenShot(driver, "Pop Up")));
									}
								}
								else
								{
									test.log(LogStatus.FAIL, "Unable to find 'Password must' pup-up."+
											test.addScreenCapture(captureScreenShot(driver, "Pop Up")));
								}
								
								addNewUserPage.passwordMust_Close().click();
								
								addNewUserPage.confirm_PasswordInput().sendKeys(userData[1][5]);
								
								addNewUserPage.password_AddTab().click();
								test.log(LogStatus.PASS, "Confirm Password:"+userData[1][5]+""+
										test.addScreenCapture(captureScreenShot(driver, "Selected list")));
								sleep(3);
								
								if(addNewUserPage.password_AddTab() != null) 
								{
									addNewUserPage.password_AddTab().click();
								}
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
								
								sleep(3);
								if(addNewUserPage.groupFilterResult() != null)
								{
									addNewUserPage.groupFilterResult().clear();
									addNewUserPage.groupFilterResult().sendKeys(userData[1][2]+number);
									addNewUserPage.groupFilterResutGoButton().click();
									
									sleep(2);
									
									for(int i=1; i<=10; i++ ) {
										
										String groups = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[2]")).getText();
										sleep(1);
										if(groups.equalsIgnoreCase(userData[1][2]+number)) {
											UserPresenceAfterSearch = true;
											break;
										}
									}
									
									if(UserPresenceAfterSearch) {
										test.log(LogStatus.PASS, "Successfully User is created and verified."+
												test.addScreenCapture(captureScreenShot(driver, "group is created and verified")));
									}else {
										test.log(LogStatus.FAIL, "Unable to verify created user."+
												test.addScreenCapture(captureScreenShot(driver, "Unable to verify created user.")));
									}
								}
								else
								{
									test.log(LogStatus.FAIL, "Unable to verify created group."+
											test.addScreenCapture(captureScreenShot(driver, "Unable to find Filter Result text field.")));
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
