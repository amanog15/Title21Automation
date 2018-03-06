package org.title21.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.DeleteUser_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.Table;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class DeleteUser_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	DeleteUser_POM deleteUser;
	static Logger log = Logger.getLogger(UpdateUser_Test.class);
	String className="";
	String number="";
	String updatedAdminUsername="";
	boolean GroupPresenceAfterSearch = false;
	Table searchTable;
	
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
		test = extent.startTest("DeleteUser");
		test.log(LogStatus.INFO, "Link to Test case document", "<a href='file:///E:/sameer/Sameer Joshi/Title health solutions/Test case by neosoft/TestCase_WIA_Delete User.doc'>TestCaseDocument</a>");
		test.log(LogStatus.PASS, "1.Login as a web interface.");
		deleteUser=new DeleteUser_POM(driver);
		updatedAdminUsername=adminUsername;
		
		getAdministrationPage(test);	
		
		test.log(LogStatus.PASS, "3.Click on Users link ");
		deleteUser.user_link().click();
		
		test.log(LogStatus.PASS, "<b>ER 1- User records Screen is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "User records Screen")));
		
		test.log(LogStatus.PASS, "4.Click on location drop-down and select the specific location (for eg. "+userData[1][0]+").");
		sleep(2);
		deleteUser.getLocationforFilter().selectByVisibleText(userData[1][0]);
		sleep(2);
				
		verifyLocationInTable();		
		test.log(LogStatus.PASS, "<b>ER 2- Only users of selected location are displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Selected Location")));
		
		test.log(LogStatus.PASS, "5.Click on search filter and enter the user's name");
		deleteUser.groupFilterResult().click();
		deleteUser.groupFilterResult().sendKeys(adminData.getUserName());
		
		test.log(LogStatus.PASS, "6.Click on Go button");
		deleteUser.groupFilterResutGoButton().click();
	    
		sleep(2);
		verifyUserNameInTable(adminData.getUserName());
		if (isRecordFound)
		{
			test.log(LogStatus.FAIL, "Unable to find User Name in table."+
				test.addScreenCapture(captureScreenShot(driver, "Unable to find User Name in table")));			
		}
		else
		{
			test.log(LogStatus.PASS, "<b>ER 3- User Record as per search is displayed.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "Record as per search")));
		}
		
		sleep(3);
		test.log(LogStatus.PASS, "7.Click on Delete Button.");
		clickOnDeleteButton(adminData.getUserName());
		sleep(2);
		
		if(deleteUser.deleteUserPopupHeader_Text() != null)
		{
			test.log(LogStatus.PASS, "<b>ER 4- Delete user popup is displayed.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "Delete user popup")));
			deleteUser.getDeleteUserpopupNoButton().click();
			test.log(LogStatus.PASS, "8. Click on No button.");	
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to find Delete user popup is displayed."+
					test.addScreenCapture(captureScreenShot(driver, "Delete user popup")));
		}
		
		sleep(3);	
		test.log(LogStatus.PASS, "<b>ER 5- User should redirect to 'user screen'. <b>"+
				test.addScreenCapture(captureScreenShot(driver, "DeleteUserNo")));
		
		sleep(2);
		verifyUserNameInTable(adminData.getUserName());
		sleep(2);
		clickOnDeleteButton(adminData.getUserName());
		
		waitTillElementVisible(deleteUser.getDeleteUserpopupYesButton());

		test.log(LogStatus.PASS, "9. Click on yes button.");
		deleteUser.getDeleteUserpopupYesButton().click();
		
		sleep(3);
		if(deleteUser.confirmHeader_Msg() != null)
		{
			test.log(LogStatus.PASS, "<b>ER 6- User get deleted and A message confirming successful deletion of the user is displayed. <b>"+
					test.addScreenCapture(captureScreenShot(driver, "message confirming")));
			deleteUser.confirmClose_Button().click();
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to find User get deleted and A message confirming successful deletion of the user is displayed."+
					test.addScreenCapture(captureScreenShot(driver, "message confirming")));
		}
		
		sleep(3);
		test.log(LogStatus.PASS, "10. Click on user search field.");
		deleteUser.groupFilterResult().clear();
		
		test.log(LogStatus.PASS, "11.Enter the username of deleted user");
		deleteUser.groupFilterResult().sendKeys(adminData.getUserName());
		
		deleteUser.groupFilterResutGoButton().click();
		
		waitTillElementVisible(deleteUser.getnoUserfoundMessage());
				
		sleep(3);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		if(deleteUser.verifyNoUserFoundText(driver))
		{
			test.log(LogStatus.PASS, "<b>ER 7- It should display 'no user found' message. <b>"+
					test.addScreenCapture(captureScreenShot(driver, "no user found")));
		}
		else
		{
			searchTable=new Table(driver);
			List<WebElement> tableCells=searchTable.gettableCells(2);				
			
			for(int i=1; i<=tableCells.size(); i++ ) {
				
				try {
					String Users = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[2]")).getText();
					
					if(Users.equalsIgnoreCase(adminData.getUserName())) {
						GroupPresenceAfterSearch = true;
						break;
					}
				}catch(NoSuchElementException e) {
					GroupPresenceAfterSearch = false;
				}
			}
			if(GroupPresenceAfterSearch) {
				test.log(LogStatus.FAIL, "Unable to find 'no user found' message. "+
						test.addScreenCapture(captureScreenShot(driver, "Unable to delete existing group")));
			}else {
				test.log(LogStatus.PASS, "<b>ER 7- It should display 'no user found' message. <b>"+
						test.addScreenCapture(captureScreenShot(driver, "no user found")));
			}
		}
		test.log(LogStatus.PASS, "12. Go to user list screen.");
		deleteUser.user_link().click();
		waitTillElementVisible(deleteUser.groupFilterResult());
		test.log(LogStatus.PASS, "13.Find admin user which you are using.");
		deleteUser.groupFilterResult().clear();
		deleteUser.groupFilterResult().sendKeys(updatedAdminUsername);
		deleteUser.groupFilterResutGoButton().click();
		sleep(2);
		verifyUserNameInTable(updatedAdminUsername);
		sleep(2);
		test.log(LogStatus.PASS, "14.Click on delete button.");
		clickOnDeleteButton(updatedAdminUsername);
		sleep(2);
		if(deleteUser.adminDeleteWarning_Msg() != null)
		{
			test.log(LogStatus.PASS, "<b>ER 8- It should display 'You can't delete yourself. The main administrator must perform this function.' message.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "adminDeleteWarning_Msg")));
			deleteUser.confirmClose_Button().click();
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to find It should display 'You can't delete yourself. The main administrator must perform this function.' message."+
					test.addScreenCapture(captureScreenShot(driver, "adminDeleteWarning_Msg")));
		}

		extent.endTest(test);
	}
	private void clickOnDeleteButton(String employeeFullName) 
	{
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(2);				
		
		for (int i=1;i<=tableCells.size();i++)
		{
			if (employeeFullName.equalsIgnoreCase(tableCells.get(i-1).getText()))
			{				
				WebElement delete = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]//span[@title='Remove User']"));
				delete.click();
				break;
			}
			else
			{
				test.log(LogStatus.PASS, "Unable to click on 'Remove User'");
			}
		}
	}
	private void verifyLocationInTable() 
	{
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
			test.log(LogStatus.PASS, "4a. All Rows contains expected locations.");
		}			
	}
	private void verifyUserNameInTable(String employeeUserName) 
	{
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(2);				
		for (int i=0;i<tableCells.size();i++){
			if (tableCells.get(i).getText().equalsIgnoreCase(employeeUserName))
			{				
				isRecordFound=false;
				break;
			}
		}
	}
	@AfterClass
	public void closeBrowserInstance()
	{		
		driver.close();
	}
}
