package org.title21.test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AdministrationCreateNewGroup_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class CreateGroup_Test extends BaseClass {

	LoginPage_POM login = new LoginPage_POM();
	public LogoutPage_POM logout = new LogoutPage_POM();
	boolean group=false;
	String className="";
	AdministrationCreateNewGroup_POM adminCreateGroup = new AdministrationCreateNewGroup_POM();
	
	
	@BeforeClass
	public void openURL() 
	{
		//browser("Chrome", "https://quantumdev.title21.com");
		
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		//Call Login keyword
		login.login_username(driver).sendKeys(data[0][0]);
		test.log(LogStatus.PASS, "Username Entered");
		login.login_BTN(driver).click();
		test.log(LogStatus.PASS, "Clicked on Login button after entering Username.");
		
		login.login_password(driver).sendKeys(data[1][1]);
		test.log(LogStatus.PASS, "Correct password Entered.");
		login.login_BTN(driver).click();
		test.log(LogStatus.PASS, "Clicked on Login button."+
		test.addScreenCapture(captureScreenShot(driver, "View after Loggedin.")));	
		
	}
	
	@Test(testName = "CreateGroup_admin", groups = "CreateGroup", priority = 0)
	public void CreateGroupInAdmin() 
	{
		test = extent.startTest("CreateGroup_admin");
		
		String GroupsTab = adminCreateGroup.groupsTab(driver).getText();
		
		if(GroupsTab.contains("Groups"))
		{
			adminCreateGroup.groupsTab(driver).click();
			test.log(LogStatus.PASS, "Successfully click on 'Groups tab");
			adminCreateGroup.addGroupAddButton(driver).click();
			test.log(LogStatus.PASS, "Successfully click on 'Add New' link.");
			adminCreateGroup.switchToModalDialog(driver);
			adminCreateGroup.verifyAddGroupPopUp(driver);
			test.log(LogStatus.PASS, "Verify 'Add Group' pop-up.");
			adminCreateGroup.groupLocationDropDownClick(driver).click();
			
			//Select location
			Select SelectObj = new Select(adminCreateGroup.groupLocationDropDownClick(driver));
			SelectObj.selectByVisibleText("Dallas");
			
			String location = adminCreateGroup.groupLocationDropDownClick(driver).getText();
			
			if(location.contains("Dallas"))
			{
				test.log(LogStatus.PASS, "Successfully set 'Dallas' Location.");
			}else{
				test.log(LogStatus.FAIL, "Unable to set 'Dallas' Location.");
			}
			
			//provide group name 
			adminCreateGroup.addGroupTextBox(driver).sendKeys("Test1234");
			
			String GroupName = adminCreateGroup.addGroupTextBox(driver).getText();
			
			if(GroupName.contains("Test1234"))
			{
				test.log(LogStatus.PASS, "Successfully set 'Test1234' group.");
			}else{
				test.log(LogStatus.FAIL, "Unable to set 'Test1234' group.");
			}
			
			adminCreateGroup.acceptToAddGroup(driver);
			
			
			if(adminCreateGroup.groupFilterResult(driver) != null)
			{
				adminCreateGroup.groupFilterResult(driver).click();
				adminCreateGroup.groupFilterResult(driver).sendKeys("Test1234");
				
				List<WebElement> allGroups = (List<WebElement>) adminCreateGroup.listOfGroups(driver);

				for ( WebElement Group: allGroups) 
				{ 
			        if ( Group.getText().contains("Text1234") ) {
			        	group= true;
			        }
			    }
				
				if(group==true)
				{
					test.log(LogStatus.PASS, "Successfully group is created.");
		        }else{
		        	test.log(LogStatus.FAIL, "Successfully group is created.");
		        }
			}
			else
			{
				test.log(LogStatus.PASS, "Unable to find 'Filter Result' text field.");
			}
			
		}else{
			
			test.log(LogStatus.FAIL, "Unable to find 'Groups' tab");
			
		}
		
	}
	
	@AfterClass
	public void closeBrowserInstance() 
	{
		//call Logout keyword
		test = extent.startTest("logoutFunction");
		logout.administratorDropDown(driver).click();
		test.log(LogStatus.PASS, "Clicked on Administrator dropdown after sucessfully login.");
		logout.logoutLink(driver).click();
		test.log(LogStatus.PASS, "Clicked on logout link"+
		test.addScreenCapture(captureScreenShot(driver, "clickonLogoutlink")));	
		
		sleep(2);
		
		if (logout.verifyMessageonModalDialog(driver)){
			test.log(LogStatus.PASS, "Message on Logout alert verified."+
			test.addScreenCapture(captureScreenShot(driver, "Logout Alert")));
		};
		
		logout.logoutButton(driver).click();
		test.log(LogStatus.PASS, "Clicked on logout button"+
		test.addScreenCapture(captureScreenShot(driver, "ClickOnLogoutButton")));
		extent.endTest(test);
		
		
		driver.close();
	}

	
	
}
