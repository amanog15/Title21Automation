package org.title21.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.Table;
import org.title21.POM.UpdateUser_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class UpdateUser_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	UpdateUser_POM updateUserPage;
	String className="";
	String number="";
	String location="";
	String employeeName="";
	String username="";
	Table searchTable;
	static Logger log = Logger.getLogger(UpdateUser_Test.class);
	boolean UserPresenceAfterSearch = false;
	private boolean isRecordFound=true;
	AdminData adminData=new AdminData();
		
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		logout=new LogoutPage_POM(driver);
		login=new LoginPage_POM(driver);
		login.loginFunction();
	}
	
	@Test(testName = "UpdateUser_Test", groups = "Update User", priority = 0)
	public void UpdateUser() throws Exception
	{		
		test = extent.startTest("UpdateUser");
		test.log(LogStatus.PASS, "1.Login as a web interface.");
		updateUserPage= new UpdateUser_POM(driver);		
		
		getAdministrationPage(test);	
		
		test.log(LogStatus.PASS, "3.Click on Users link ");
		updateUserPage.user_link().click();
		
		test.log(LogStatus.PASS, "<b>ER 1- User records Screen is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "User records Screen")));
		
		test.log(LogStatus.PASS, "4.Click on location drop-down and select the specific location (for eg. "+userData[1][0]+").");
		sleep(2);
		updateUserPage.getLocationforFilter().selectByVisibleText(userData[1][0]);
		sleep(2);
				
		verifyLocationInTable();		
		test.log(LogStatus.PASS, "<b>ER 2- Only users of selected location are displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Selected Location")));
		
		test.log(LogStatus.PASS, "5.Click on search filter and enter the user's name");
		updateUserPage.groupFilterResult().click();
		//updateUserPage.groupFilterResult().sendKeys(adminData.getEmployeeName());
		updateUserPage.groupFilterResult().sendKeys(adminData.getUserName());
		
		test.log(LogStatus.PASS, "6.Click on Go button");
		updateUserPage.groupFilterResutGoButton().click();
	
		sleep(2);
		verifyUserNameInTable();
		
		sleep(3);
		
		test.log(LogStatus.PASS, "7.Click on Edit button.");
		clickOnEditButton(adminData.getUserName());
		sleep(3);
		if(updateUserPage.general_tab() != null) 
		{
			test.log(LogStatus.PASS, "<b>ER 4- Update user screen is displayed.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "Record as per search")));
			if(!updateUserPage.locationDropDown_click().isEnabled())
			{
				test.log(LogStatus.PASS, "<b>ER 5- location,<b>");
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to find location field is disabled."+
				test.addScreenCapture(captureScreenShot(driver, "location field")));
			}
			
			if(!updateUserPage.userFullNameDropDown_click().isEnabled())
			{
				test.log(LogStatus.PASS, "<b>full name,<b>");
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to find full name field is disabled."+
				test.addScreenCapture(captureScreenShot(driver, "full name field")));
			}
			
			if(!updateUserPage.username_textbox().isEnabled())
			{
				test.log(LogStatus.PASS, "<b>and username field are disabled.<b>"+
						test.addScreenCapture(captureScreenShot(driver, "field are disabled")));
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to find username field is disabled."+
				test.addScreenCapture(captureScreenShot(driver, "username field")));
			}
				
				String list = updateUserPage.selected_List().getText();
				
				sleep(2);
				
				if(!list.isEmpty())
				{
					test.log(LogStatus.PASS, "8.Add one group.");
					updateUserPage.AvailableFirst_Element().click();
					
					sleep(2);
					
					String selectedList = updateUserPage.selected_List().getText();
					
					if(!selectedList.isEmpty())
					{
						test.log(LogStatus.PASS, "9.Click on confirm");
						updateUserPage.UpdateUserConfirm_Button().click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Unable to find selected element."+
								test.addScreenCapture(captureScreenShot(driver, "selected element")));
					}
					
					sleep(2);
					if(updateUserPage.userUpdatedSuccessfully_Text() != null)
					{
						test.log(LogStatus.PASS, "<b>ER 6- A message confirming successful update is displayed.<b>"+
								test.addScreenCapture(captureScreenShot(driver, "successful update")));
						
						test.log(LogStatus.PASS, "10.Click on close button");
						updateUserPage.confirmClose_Button().click();
					}
					else
					{
						test.log(LogStatus.FAIL, "<b>Unable to find ER 6- A message confirming successful update is displayed.<b>"+
								test.addScreenCapture(captureScreenShot(driver, "successful update")));
					}
					
					sleep(3);
					test.log(LogStatus.PASS, "11.Again click on edit button.");
					//clickOnEditButton(adminData.getEmployeeName());
					clickOnEditButton(adminData.getUserName());
					
					sleep(2);
					
					test.log(LogStatus.PASS, "12.Check the edit password checkbox.");
					updateUserPage.password_Tab().click();
					
					sleep(2);
					updateUserPage.editPassword_checkBox().click();
					test.log(LogStatus.PASS, "<b>ER 7- The new password and confirm password field should get enabled.<b>"+
							test.addScreenCapture(captureScreenShot(driver, "successful update")));
					
					test.log(LogStatus.PASS, "13.Enter password and confirm password");
						
					sleep(3);
					updateUserPage.new_PasswordInput().sendKeys(userData[1][5]);
					updateUserPage.confirm_PasswordInput().sendKeys(userData[1][5]);
					
					test.log(LogStatus.PASS, "14.Click on confirm");
					updateUserPage.UpdateUserConfirm_Button().click();
					updateUserPage.UpdateUserConfirm_Button().click();
					sleep(2);
					updateUserPage.confirmClose_Button().click();
					
					sleep(3);
					test.log(LogStatus.PASS, "15.Logout from admin");
					logout.logoutFunction();	
					sleep(3);
					login.getUsername().sendKeys(adminData.getUserName());
					//login.getUsername().sendKeys(adminData.getEmployeeName());
					login.getLogin_button().click();
					sleep(2);
					login.getpassword().sendKeys(userData[1][5]);
					login.getLogin_button().click();
					sleep(3);
					test.log(LogStatus.PASS, "16.Login with that user and with updated password");
					verticalScrollingDown();
					sleep(2);
					if(updateUserPage.iAgree_Button() != null) {
						updateUserPage.iAgree_Button().click();
					}
					sleep(4);
					test.log(LogStatus.PASS, "<b>ER 8- User should able to login with new password.<b>"+
							test.addScreenCapture(captureScreenShot(driver, "login with new password")));
					sleep(3);
					logout.logoutFunction();
					test.log(LogStatus.PASS, "17.Logout from user");
					
					sleep(3);
					login.loginFunction();
					test.log(LogStatus.PASS, "18.Login as admin user");
					
					getAdministrationPage(test);
					
					test.log(LogStatus.PASS, "20.Click on user link");
					updateUserPage.user_link().click();
					
					sleep(2);
					test.log(LogStatus.PASS, "21.Search for the user. ");
					test.log(LogStatus.PASS, "22.Again edit user.");
					clickOnEditButton(adminData.getUserName());
					//clickOnEditButton(adminData.getEmployeeName());
					
					if(updateUserPage.password_Tab() != null)
					{
						sleep(2);
						test.log(LogStatus.PASS, "23.Go to password tab");
						updateUserPage.password_Tab().click();
						
						test.log(LogStatus.PASS, "24.Enter password and confirm password");
						sleep(1);
						updateUserPage.editPassword_checkBox().click();	
						sleep(2);
						updateUserPage.new_PasswordInput().sendKeys(userData[1][8]);
						sleep(2);
						updateUserPage.confirm_PasswordInput().sendKeys(userData[1][8]);
						sleep(2);
						test.log(LogStatus.PASS, "25.Click on cancel.");
						updateUserPage.password_CancelTab().click();
						updateUserPage.password_CancelTab().click();
						sleep(2);
						test.log(LogStatus.PASS, "26.Logout from admin");
						logout.logoutFunction();
						sleep(3);
						
						test.log(LogStatus.PASS, "27.Try to Login with that user and with updated password ");
						login.getUsername().sendKeys(adminData.getUserName());
						//login.getUsername().sendKeys(adminData.getEmployeeName());
						login.getLogin_button().click();
						sleep(2);
						login.getpassword().sendKeys(userData[1][8]);
						login.getLogin_button().click();
						sleep(3);
						
						if (login.verifyPasswordErrorMessage()){			
							test.log(LogStatus.PASS, "<b>ER 9- Password and confirm password are unchanged.<b>"+
							test.addScreenCapture(captureScreenShot(driver, "PasswordErrorMessageSuccess")));
						}else{			
							test.log(LogStatus.PASS, "Password message not matched.");
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Unable to clicked on 'password' tab.");
					}
				}
				else
				{
					test.log(LogStatus.FAIL, "Unable to clicked 'Available' button.");
					updateUserPage.cancel_GeneralButton().click();
				}
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to verified 'general' tab");
		}
		extent.endTest(test);
	}
	
	private void clickOnEditButton(String employeeFullName) {
		
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(2);				
		
		for (int i=1;i<=tableCells.size();i++){
			if (employeeFullName.equalsIgnoreCase(tableCells.get(i-1).getText()))
			{				
				WebElement delete = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]//span[@title='Edit User']"));
				delete.click();
				break;
			}
		}
	}
	
	private void verifyLocationInTable() {
		
		// TODO Auto-generated method stub
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(4);				
		
		for (int i=0;i<tableCells.size();i++){
			if (!userData[1][0].equalsIgnoreCase(tableCells.get(i).getText()))
			{				
				test.log(LogStatus.FAIL, "Expected location is not present in rowNum: "+i);
				isRecordFound=false;
			}
		}
		if (isRecordFound){
			test.log(LogStatus.PASS, "All Rows contains expected locations.");
		}			
	}
	
	private void verifyUserNameInTable() {
		
		// TODO Auto-generated method stub
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(2);				
		
		for (int i=0;i<tableCells.size();i++){
			if (tableCells.get(i).getText().equalsIgnoreCase(adminData.getUserName()))
			{				
				test.log(LogStatus.PASS, "<b>ER 3- User Record as per search is displayed.<b>"+
						test.addScreenCapture(captureScreenShot(driver, "Record as per search")));
				isRecordFound=false;
				break;
			}
		}
		if (isRecordFound){
			test.log(LogStatus.FAIL, "Unable to find User Name in table."+
				test.addScreenCapture(captureScreenShot(driver, "Unable to find User Name in table")));			
		}			
	}
	@AfterClass
	public void closeBrowserInstance()
	{		
		driver.close();
	}
}
