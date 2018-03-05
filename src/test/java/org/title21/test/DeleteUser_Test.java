package org.title21.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.DeleteUser_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.Table;
import org.title21.POM.UpdateUser_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;



import com.relevantcodes.extentreports.LogStatus;

public class DeleteUser_Test extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	UpdateUser_POM updateUserPage;
	DeleteUser_POM deleteUser;
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

		logout=new LogoutPage_POM(driver);

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
		sleep(2);

		updateUserPage.getLocationforFilter().selectByVisibleText(userData[1][0]);
		sleep(2);
				
		verifyLocationInTable();		
		test.log(LogStatus.PASS, "<b>ER 2- Only users of selected location are displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Selected Location")));
		
		test.log(LogStatus.PASS, "5.Click on search filter and enter the user's name");
		updateUserPage.groupFilterResult().click();

		updateUserPage.groupFilterResult().sendKeys(adminData.getUserName());
		
		test.log(LogStatus.PASS, "6.Click on Go button");
		updateUserPage.groupFilterResutGoButton().click();
	
		sleep(2);
		verifyUserNameInTable();
		
		sleep(3);
		
		test.log(LogStatus.PASS, "7.Click on Delete Button.");
		clickOnDeleteButton(adminData.getUserName());
		sleep(3);
		test.log(LogStatus.PASS, "<b>ER 4- Delete user popup is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Delete user popup")));

		deleteUser=new DeleteUser_POM(driver);		
		deleteUser.getDeleteUserpopupNoButton().click();
		test.log(LogStatus.PASS, "8. Click on No button.");	
		waitTillElementVisible(updateUserPage.groupFilterResult());
		test.log(LogStatus.PASS, "<b>ER 5- User should redirect to 'user screen'. <b>"+
				test.addScreenCapture(captureScreenShot(driver, "DeleteUserNo")));
						
		clickOnDeleteButton(adminData.getUserName());
		waitTillElementVisible(deleteUser.getDeleteUserpopupYesButton());
		deleteUser.getDeleteUserpopupYesButton().click();		
		extent.endTest(test);
	}
		
	private void clickOnDeleteButton(String userName) {

		
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(2);				
		
		for (int i=1;i<=tableCells.size();i++){

			if (userName.equalsIgnoreCase(tableCells.get(i-1).getText()))

			{	
				WebElement delete = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]//span[@title='Remove User']"));
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



