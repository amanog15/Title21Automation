package org.title21.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AdminCreateDeleteGroups_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.Table;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;
public class CreateDeleteGroups_Test extends BaseClass {

	LoginPage_POM login; 
	LogoutPage_POM logout;
	Table groupTable;
	String className="";
	String number="";
	AdminCreateDeleteGroups_POM adminCreateGroup;
	boolean GroupPresence = false;
	boolean GroupPresenceAfterSearch = false;
	
	@BeforeClass
	public void openURL() 
	{
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login=new LoginPage_POM(driver);
		login.loginFunction();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	@Test(testName = "CreateGroup_admin", groups = "CreateGroup", priority = 0)
	public void CreateGroupInAdmin() 
	{
		number = FunctionUtils.generateRandomNumber();
		adminCreateGroup = new AdminCreateDeleteGroups_POM(driver);
		
		test = extent.startTest("CreateDeleteGroups");
		test.log(LogStatus.INFO, "Link to Test case document", "<a href='file:///E:/sameer/Sameer Joshi/Title health solutions/Test case by neosoft/TestCase_WIA_CreateandDeleteGroups.doc'>TestCaseDocument</a>");
		test.log(LogStatus.PASS, "1.Login as a web interface.");
		BaseClass.getAdministrationPage(test);
		
		String GroupsTab = adminCreateGroup.groupsTab().getText();
		
		if(GroupsTab.contains("Groups"))
		{
			adminCreateGroup.groupsTab().click();
			test.log(LogStatus.PASS, "3.Click on 'Groups' tab from the administration list.");
			test.log(LogStatus.PASS, "<b>ER 1- List of Groups with '(+) Add New' link in the Web interface.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "Groups")));
			
			adminCreateGroup.groupAddNewLink().click();
			test.log(LogStatus.PASS, "4.Click on '+ Add New' link.");
			adminCreateGroup.verifyAddGroupPopUp(driver);
			
			sleep(2);
			adminCreateGroup.groupLocationDropDownClick().selectByVisibleText(groupData[1][0]);
			
			adminCreateGroup.addGroupTextBox().sendKeys(groupData[1][1]+number);
			sleep(2);
			test.log(LogStatus.PASS, "<b>ER 2- User select Location with new Group name.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "Add Group")));
			
			adminCreateGroup.addGroupTextBox().click();
			test.log(LogStatus.PASS, "5.Click on 'Add' button");
			
				adminCreateGroup.addGroupAddButton().click();
				sleep(3);
				
				if(adminCreateGroup.verifyAlerPopUp(driver)) 
				{
					test.log(LogStatus.PASS, "<b>ER 3- Verify Message with Group '"+groupData[1][1]+number+"' added successfully with Close button.<b>"+
							test.addScreenCapture(captureScreenShot(driver, "Close alert PopUp.")));
					
					test.log(LogStatus.PASS, "6.Click on 'Close' button. And user should navigate to previous screen (ER 1).");
					
					adminCreateGroup.alertCloseButton().click();
					
				}else {
					test.log(LogStatus.FAIL, "Unable to close alert PopUp."+
							test.addScreenCapture(captureScreenShot(driver, "Unable to close alert PopUp.")));
				}
				
				sleep(2);
				test.log(LogStatus.PASS, "7.Again click on '+ Add New' link.");
				adminCreateGroup.groupAddNewLink().click();
				sleep(2);
				adminCreateGroup.verifyAddGroupPopUp(driver);
				
				test.log(LogStatus.PASS, "8.Select same Location with Group name which is previously created.");
				adminCreateGroup.groupLocationDropDownClick().selectByVisibleText(groupData[1][0]);
				
				adminCreateGroup.addGroupTextBox().sendKeys(groupData[1][1]+number);
				sleep(2);
				
				test.log(LogStatus.PASS, "9.Click on 'Add' button");
				adminCreateGroup.addGroupAddButton().click();
				sleep(2);
				
				if(adminCreateGroup.alreadyGroupCreatedErrorMsg() != null) {
					
					test.log(LogStatus.PASS, "<b>ER 4- Validation massege 'This name already exists. Please enter another name.'<b>"+
							test.addScreenCapture(captureScreenShot(driver, "Group is already created.")));
					adminCreateGroup.addGroupCancelButton().click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Unable to find ER 3- validation massege already created group can't select."+
							test.addScreenCapture(captureScreenShot(driver, "Group is already created.")));
					adminCreateGroup.addGroupAddButton().click();
				}
				
				sleep(2);
				test.log(LogStatus.PASS, "10.Filter the results with newly created group in 'filter results' text field.");
				
				if(adminCreateGroup.groupFilterResult() != null)
				{
					adminCreateGroup.groupFilterResult().click();
					adminCreateGroup.groupFilterResult().sendKeys(groupData[1][1]+number);
					adminCreateGroup.groupFilterResutGoButton().click();
					
					sleep(2);
					groupTable=new Table(driver);
										
					for (WebElement element:groupTable.getColumnData(1)){
						String groups = element.getText();
						sleep(1);
						if(groups.equalsIgnoreCase(groupData[1][1]+number)) {
							GroupPresenceAfterSearch = true;
							break;
						}						
					}
					
					if(GroupPresenceAfterSearch) {
						test.log(LogStatus.PASS, "<b>ER 5- Search newly created group '"+groupData[1][1]+number+"'.<b>"+
								test.addScreenCapture(captureScreenShot(driver, "created group")));
					}else {
						test.log(LogStatus.FAIL, "Unable to verify created group."+
								test.addScreenCapture(captureScreenShot(driver, "Unable to verify created group.")));
					}
					
					//Delete Created group-
					
					sleep(2);
					
					for(int i=1; i<=10; i++) {
						
						WebElement groups = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[1]"));
						String groupName= groups.getText();
						
						if(groupName.equalsIgnoreCase(groupData[1][1]+number)) {
							
							WebElement delete = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]//span[@title='Remove Group']"));
							test.log(LogStatus.PASS, "11.Click on delete icon for group: "+groupData[1][1]+number+".");
							delete.click();
							break;
						}
					}
										
					sleep(2);
					
					if(adminCreateGroup.verifyDeleteGroupPopUp()) 					{
						
						test.log(LogStatus.PASS, "<b>ER 6- Delete group massege with 'Are you sure you want to delete '"+groupData[1][1]+number+"'?' with No and Yes button.<b>"+
								test.addScreenCapture(captureScreenShot(driver, "Verified Delete Group Pop-Up.")));
						
						test.log(LogStatus.PASS, "12.Click on 'Yes' button.");
						adminCreateGroup.deleteGroupPopUpYesButton().click();
						sleep(2);
						adminCreateGroup.verifyDeleteGroupcConfirmPopUpText();
						sleep(2);
						test.log(LogStatus.PASS, "<b>ER 7- Group gets deleted and the successful message is displayed.<b>"+
								test.addScreenCapture(captureScreenShot(driver, "Verified Delete Group Confirm Pop-Up.")));
						
						test.log(LogStatus.PASS, "13.Click on 'Close' button.");
						adminCreateGroup.ConfirmPopUpCloseButton().click();
						sleep(2);
						if(adminCreateGroup.groupFilterResult() != null)
						{
							test.log(LogStatus.PASS, "14.Type deleted Group name like '"+groupData[1][1]+number+"' in the 'filter results' text field.");
							adminCreateGroup.groupFilterResult().clear();
							adminCreateGroup.groupFilterResult().sendKeys(groupData[1][1]+number);
							adminCreateGroup.groupFilterResutGoButton().click();
							
							sleep(2);
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							if(adminCreateGroup.verifyNoGroupFoundText(driver))
							{
								test.log(LogStatus.PASS, "<b>ER 8- No group found for deleted group<b>"+
										test.addScreenCapture(captureScreenShot(driver, "Unable to verify created group")));
							}else {
								
								for(int i=1; i<=10; i++ ) {
									
									System.out.println(i);
									
									try {
										String groups = driver.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr["+i+"]/td[1]")).getText();
										
										if(groups.equalsIgnoreCase(groupData[1][1]+number)) {
											GroupPresenceAfterSearch = true;
											break;
										}
									}catch(NoSuchElementException e) {
										GroupPresenceAfterSearch = false;
									}
								}
								if(GroupPresenceAfterSearch) {
									test.log(LogStatus.FAIL, "Unable to delete existing group: "+groupData[1][1]+number+" "+
											test.addScreenCapture(captureScreenShot(driver, "Unable to delete existing group")));
								}else {
									test.log(LogStatus.PASS, "<b>ER 9- No group found for deleted group.<b>"+
											test.addScreenCapture(captureScreenShot(driver, "Unable to verify created group")));
								}
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "'Unable to verified Delete Group Pop-Up."+
									test.addScreenCapture(captureScreenShot(driver, "Unable to verified Delete Group Pop-Up.")));
						}
						
					}else {
						
						test.log(LogStatus.FAIL, "Unable to find Group Filter Result."+
								test.addScreenCapture(captureScreenShot(driver, "Unable to find Group Filter Result.")));
					}
				}
				else
				{
					test.log(LogStatus.PASS, "Unable to find 'Filter Result' text field."+
							test.addScreenCapture(captureScreenShot(driver, "Unable to find 'Filter Result' text field.")));
				}
			
		}else{
			test.log(LogStatus.FAIL, "Unable to find 'Groups' tab"+
					test.addScreenCapture(captureScreenShot(driver, "Unable to find 'Groups' tab")));
		}
		extent.endTest(test);
	}
	
	@Test(testName = "logout_admin", groups = "Logout", priority = 1)
	public void LogoutFromAdmin() throws Exception 
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
