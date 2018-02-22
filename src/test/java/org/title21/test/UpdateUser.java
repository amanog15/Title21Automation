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
		
		verifyLocationInTable();
		
		clickOnEditButton(adminData.getEmployeeName());
		test.log(LogStatus.PASS, "Successfully clicked on 'Edit Button' for "+adminData.getEmployeeName()+".");
		
		if(updateUserPage.general_tab() != null) 
		{
			test.log(LogStatus.PASS, "Successfully verified 'general' tab.");

			
			if(!updateUserPage.locationDropDown_click().isEnabled())
			{
				test.log(LogStatus.PASS, "location field is disabled.");
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to find location field is disabled."+
				test.addScreenCapture(captureScreenShot(driver, "location field")));
			}
			
			if(!updateUserPage.userFullNameDropDown_click().isEnabled())
			{
				test.log(LogStatus.PASS, "full name field is disabled.");
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to find full name field is disabled."+
				test.addScreenCapture(captureScreenShot(driver, "full name field")));
			}
			
			if(!updateUserPage.username_textbox().isEnabled())
			{
				test.log(LogStatus.PASS, "username field is disabled.");
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
					updateUserPage.available_Button().click();
					
					sleep(2);
					
					String selectedList = updateUserPage.selected_List().getText();
					
					if(!selectedList.isEmpty())
					{
						updateUserPage.UpdateUserConfirm_Button().click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Unable to find selected element."+
								test.addScreenCapture(captureScreenShot(driver, "selected element")));
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
