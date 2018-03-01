package org.title21.test;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AddNewUser_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.Table;
import org.title21.POM.UpdateUser_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;

public class DeleteUser_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	UpdateUser_POM updateUserPage;
	String className="";
	String number="";
	String location="";
	String employeeName="";
	String username="";
	Table searchTable;
	static Logger log = Logger.getLogger(DeleteUser_Test.class);
	boolean UserPresenceAfterSearch = false;
	private boolean isRecordFound=true;
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
	
	@Test(testName = "DeleteUser_Test", groups = "Delete User", priority = 0)
	public void UpdateUser() throws Exception
	{		
		test = extent.startTest("DeleteUser");
		test.log(LogStatus.PASS, "1.Login as a web interface.");
		updateUserPage= new UpdateUser_POM(driver);		
		
		getAdministrationPage(test);	
		
		test.log(LogStatus.PASS, "3.Click on Users link ");
		updateUserPage.user_link().click();
		
		test.log(LogStatus.PASS, "<b>ER 1- User records Screen is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "User records Screen")));
			
		test.log(LogStatus.PASS, "4.Click on location drop-down and select the specific location (for eg. "+userData[1][0]+").");
		updateUserPage.getLocationforFilter().selectByVisibleText(userData[1][0]);
		sleep(2);
				
		verifyLocationInTable();		
		test.log(LogStatus.PASS, "<b>ER 2- Only users of selected location are displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Selected Location")));
		
		test.log(LogStatus.PASS, "5.Click on search filter and enter the user's name");
		updateUserPage.groupFilterResult().click();
		updateUserPage.groupFilterResult().sendKeys(adminData.getEmployeeName());
		
		test.log(LogStatus.PASS, "6.Click on Go button");
		updateUserPage.groupFilterResutGoButton().click();
		
		test.log(LogStatus.PASS, "<b>ER 3- User Record as per search is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Record as per search")));
		verifyLocationInTable();
		
		
		test.log(LogStatus.PASS, "7.Click on Edit button.");
		clickOnEditButton(adminData.getEmployeeName());
		
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
			
				String list = updateUserPage.available_List().getText();
				
				sleep(2);
				
				if(!list.isEmpty())
				{
					test.log(LogStatus.PASS, "8.Add one group.");
					updateUserPage.available_Button().click();
					
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
					
					test.log(LogStatus.PASS, "11.Again click on edit button.");
					clickOnEditButton(adminData.getEmployeeName());
					
					sleep(2);
					
					
					test.log(LogStatus.PASS, "11.Again click on edit button.");
					updateUserPage.password_tab().click();
					
					updateUserPage.password_Tab().click();
					
					
					
					
					
					
					
					
					
					
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
		
		sleep(3);
	}
	
	private void clickOnEditButton(String employeeFullName) {
		
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(1);				
		
		for (int i=0;i<tableCells.size();i++){
			if (employeeFullName.equalsIgnoreCase(tableCells.get(i).getText()))
			{				
				WebElement delete = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]//span[@title='Edit User']"));
				test.log(LogStatus.PASS, "Clicked to remove group: "+groupData[1][1]+number+".");
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
			test.log(LogStatus.PASS, "All Rows contains expected locations."+
				test.addScreenCapture(captureScreenShot(driver, "Only users of selected location are displayed.")));			
		}			
		
	}
	
}
