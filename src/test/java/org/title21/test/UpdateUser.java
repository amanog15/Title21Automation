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

public class UpdateUser extends BaseClass{
	LoginPage_POM login;
	LogoutPage_POM logout;
	UpdateUser_POM updateUserPage;
	String className="";
	String number="";
	String location="";
	String employeeName="";
	String username="";
	Table searchTable;
	static Logger log = Logger.getLogger(UpdateUser.class);
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
	
	
	@Test(testName = "UpdateUser", groups = "Update User", priority = 0)
	public void UpdateUser() throws Exception
	{		
		test = extent.startTest("UpdateUser");
		updateUserPage= new UpdateUser_POM(driver);		
		
		getAdministrationPage(test);	
		
		updateUserPage.user_link().click();
		log.info("Clicked on Users section.");
		test.log(LogStatus.PASS, "User records Screen is displayed");
				
		updateUserPage.getLocationforFilter().selectByVisibleText(userData[1][0]);
		sleep(2);
				
		verifyLocationInTable();		
		
		updateUserPage.groupFilterResult().click();
		updateUserPage.groupFilterResult().sendKeys(adminData.getEmployeeName());
		updateUserPage.groupFilterResutGoButton().click();
		log.info("Searching latest generated user.");
				
		clickOnEditButton(adminData.getEmployeeName());
		
		test.log(LogStatus.PASS, "");
		
		sleep(3);
	}
	
	private void clickOnEditButton(String employeeFullName) {
		
		// TODO Auto-generated method stub
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(1);				
		
		for (int i=0;i<tableCells.size();i++){
			if (employeeFullName.equalsIgnoreCase(tableCells.get(i).getText()))
			{				
				test.log(LogStatus.FAIL, "Expected location is not present in rowNum: "+i);
				isRecordFound=false;
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
