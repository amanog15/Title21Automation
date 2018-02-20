package org.title21.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.AddEmployee_POM;
import org.title21.POM.DashBord_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.Table;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AddEmployee_Test extends BaseClass {
	LoginPage_POM login; 
	LogoutPage_POM logout;
	AddEmployee_POM addEmployeePOM;
	DashBord_POM dashboardObj;
	Table searchTable;
	SoftAssert softAssertion=new SoftAssert();
	String className="";
	String employeeFullName="";
	String employeeID="";
	Boolean isRecordFound=false;
	boolean isValidationMessageProper=true;
	static Logger log = Logger.getLogger(AddEmployee_Test.class);
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
	
	@Test(testName = "AddEmployee", groups = "Employee", priority = 0)
	public void createEmployee() throws Exception 
	{	
		test = extent.startTest("Add Employee");		
		getAdministrationPage(test);		
		
		addEmployeePOM=new AddEmployee_POM(driver);
		addEmployeePOM.employees_link().click();
		
		log.info("Now clicking on Add new Link.");
		addEmployeePOM.addNewLink().click();
		
		addEmployeePOM=new AddEmployee_POM(driver);
		
		waitTillElementVisible(addEmployeePOM.getEmployeeFullName());
				
		test.log(LogStatus.PASS, "Add employee popup opened with General TAB opened"+
					test.addScreenCapture(captureScreenShot(driver, "employeepopup")));
				
		log.info("First checking Validation Messages. without entering in any"
				+ "field, click on Add button.");		
		
		verticalScrollingDown();
		waitTillElementVisible(addEmployeePOM.getAddBtn());
		log.info("scrolling down to click on Add button.");
		
		//addEmployeePOM.getAddBtn().click();	
		
		javaScriptClick(addEmployeePOM.getAddBtn());		
				
		if (!addEmployeePOM.verifyLocationValidationMessage()){	
			test.log(LogStatus.FAIL, "Location validation message is not present. see the screenshot"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure"))
		     );
			isValidationMessageProper=false;
		}
				
		if (!addEmployeePOM.verifyFullNameValidationMessage()){	
		test.log(LogStatus.FAIL, "Full name validation message is not present. see the screenshot"+
				test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure"))
	     );
			isValidationMessageProper=false;
		}
			
		if (!addEmployeePOM.verifyEmployeeIDValidationMessage()){	
			test.log(LogStatus.FAIL, "EmployeeID validation message is not present. see the screenshot"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure"))
		     );
			isValidationMessageProper=false;
		}
		
		if (!addEmployeePOM.verifyBusinessUnitValidationMessage()){	
			test.log(LogStatus.FAIL, "BusinessUnit validation message is not present. see the screenshot"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure"))
		     );
			isValidationMessageProper=false;
		}
		
		if (!addEmployeePOM.verifyDepartmentValidationMessage()){	
			test.log(LogStatus.FAIL, "Department validation message is not present. see the screenshot"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure"))
		     );
			isValidationMessageProper=false;
		}
		
		if (isValidationMessageProper){
			test.log(LogStatus.PASS, "Displays validation messages when no data entered.(General TAB)"+
					test.addScreenCapture(captureScreenShot(driver, "ValidationMessagesFailure")));			
		}
		
		log.info("Entering data in form.");
		
		sleep(2);			
		
		addEmployeePOM.getLocationDropdown().selectByValue(employeeData[1][0]);		
								
		employeeFullName=employeeData[1][1]+FunctionUtils.generateRandomNumber();
		
		addEmployeePOM.getEmployeeFullName().sendKeys(employeeFullName);		
		
		log.info("setting employeeFullName using setters method so it will be helpful afterwards.");
		adminData.setEmployeeName(employeeFullName);		
		
		employeeID=employeeData[1][2]+FunctionUtils.generateRandomNumber();
		
		//addEmployeePOM.getEmployeeID().sendKeys(employeeData[1][2]+FunctionUtils.generateRandomNumber());
		
		addEmployeePOM.getEmployeeID().sendKeys(employeeID);
		
		log.info("setting employeeID using setters method so it will be helpful afterwards.");
		adminData.setEmployeeID(employeeID);		
		
		addEmployeePOM.getsupervisorDropdown().selectByVisibleText(employeeData[1][3]);
		
		addEmployeePOM.getbusinessUnitDropdown().selectByIndex(1);
		
		addEmployeePOM.getDepartmentDropdown().selectByVisibleText(employeeData[1][5]);	
		
		addEmployeePOM.getAddressField().sendKeys(employeeData[1][6]);
		
		addEmployeePOM.getEmployeeCity().sendKeys(employeeData[1][7]);
		
		addEmployeePOM.getEmployeeState().sendKeys(employeeData[1][8]);
		
		addEmployeePOM.getEmployeePostalCode().sendKeys(employeeData[1][9]);
		
		addEmployeePOM.getEmployeeCountry().sendKeys(employeeData[1][10]);
		
		addEmployeePOM.getEmployeePhone().sendKeys(employeeData[1][11]);
		
		addEmployeePOM.getEmployeeemail().sendKeys(employeeData[1][12]);
		
		test.log(LogStatus.PASS, "All employee data has been entered."+
				test.addScreenCapture(captureScreenShot(driver, "employeeData")));
		
		verticalScrollingDown();
		
		waitTillElementVisible(addEmployeePOM.getAddBtn());
				
		//addEmployeePOM.getAddBtn().click();	
		javaScriptClick(addEmployeePOM.getAddBtn());
		
		addEmployeePOM=new AddEmployee_POM(driver);
		
		waitTillElementVisible(addEmployeePOM.getJobCodesTab());
		
		test.log(LogStatus.PASS, "User should be navigated to Job Codes Screen "
				+ "and job codes list should be visible."+
				test.addScreenCapture(captureScreenShot(driver, "jobcodeList")));
			
		waitTillElementVisible(addEmployeePOM.getJobCodeVPHumanResource());
				
		addEmployeePOM.getJobCodeVPHumanResource().click();		
		
		waitTillElementVisible(addEmployeePOM.getSelectedJobCode());
		
		test.log(LogStatus.PASS, "Added job code should be available in job code section."+
				test.addScreenCapture(captureScreenShot(driver, "SelectedJobCodeList")));
		
		addEmployeePOM.getAddBtn().click();	
		
		waitTillElementVisible(addEmployeePOM.getCloseButtononSuccessMessage());
						
		if (addEmployeePOM.verifySuccessMessage()){
			
			test.log(LogStatus.PASS, "Employee created successfully."+
			test.addScreenCapture(captureScreenShot(driver, "Employee added successfully.")));
		};
		
		log.info("employee added successfully.");
		
		waitTillElementVisible(addEmployeePOM.getCloseButtononSuccessMessage());
		
		addEmployeePOM.getCloseButtononSuccessMessage().click();	
		
		waitTillElementVisible(addEmployeePOM.getFilterTextBox());
		
		addEmployeePOM.getFilterTextBox().sendKeys(employeeFullName);
		
		addEmployeePOM=new AddEmployee_POM(driver);
		
		verticalScrollingUp();		
		
		javaScriptClick(addEmployeePOM.getGoButton());
						
		sleep(3);
		
		searchRecordInTable();		
		
		addEmployeePOM.addNewLink().click();			
		
		waitTillElementVisible(addEmployeePOM.getEmployeeID());
		
		addEmployeePOM.getEmployeeFullName().sendKeys(adminData.getEmployeeName());
		
		sleep(1);
		
		addEmployeePOM.getEmployeeFullName().sendKeys(Keys.TAB);							
		
		if (addEmployeePOM.verifyUniqueEmployeeFullName()){
			
			test.log(LogStatus.PASS, "If User enters duplicate employeeName then it's showing already exists"+
			test.addScreenCapture(captureScreenShot(driver, "employeeName already exists.")));
		}else{			
			test.log(LogStatus.FAIL,"Not checking for duplicate employee ID."+
		test.addScreenCapture(captureScreenShot(driver, "employeeID does not exists.")));
		}
				
		addEmployeePOM.getEmployeeID().sendKeys(adminData.getEmployeeID());		
		
		sleep(1);
		
		addEmployeePOM.getEmployeeID().sendKeys(Keys.TAB);
		
		if (addEmployeePOM.verifyUniqueEmployeeID()){
			
			test.log(LogStatus.PASS, "If User enters duplicate employeeID then it's showing employeeID already exists"+
					test.addScreenCapture(captureScreenShot(driver, "employeeID already exists.")));
		}else{			
			test.log(LogStatus.FAIL,"Not checking for duplicate employee ID.");
		}
				
		
		
		addEmployeePOM.cancel_Btn().click();
		
		sleep(2);
		
		log.info("Now calling logout function.");
		
		logout=new LogoutPage_POM(driver);
			
		logout.logoutFunction();
		
		log.info("logout successfully.");
		
		extent.endTest(test);		
	}	
	

	@AfterClass
	public void closeBrowserInstance()
	{		
		driver.close();
	}

	private void searchRecordInTable() {
		
		// TODO Auto-generated method stub
		searchTable=new Table(driver);
		List<WebElement> tableCells=searchTable.gettableCells(1);				
		
		for (int i=0;i<tableCells.size();i++){
			if (employeeFullName.equalsIgnoreCase(tableCells.get(i).getText()))
			{				
				test.log(LogStatus.PASS, "record found in the Search result."+
						test.addScreenCapture(captureScreenShot(driver, "foundRecord")));
				isRecordFound=true;
				break;
			}
		}
		
		if (!isRecordFound){
			test.log(LogStatus.FAIL, "Employee not found in records."+
				test.addScreenCapture(captureScreenShot(driver, "not found record for employee")));			
		}
		
		log.info("No of Visible rows in table are"+searchTable.getNumberOfVisibleRows());		
		log.info("No of rows in table are"+searchTable.getNumberOfRows());
		
		if (searchTable.getNumberOfVisibleRows()==1){
			log.info("one record found for the employee.");
		}
		
	}
	
}
