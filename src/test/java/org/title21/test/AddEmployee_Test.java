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
	
	@Test(testName = "AddEmployee", groups = "Employee")
	public void createEmployee() throws Exception 
	{	
		test = extent.startTest("Add Employee");	
		test.log(LogStatus.INFO, "Link to Test case document", "<a href='file:///E:/sameer/Sameer Joshi/Title health solutions/Test case by neosoft/TestCase-WIA-Add Employee.doc'>TestCaseDocument</a>");
		test.log(LogStatus.PASS,"1.	Login to the web interface.");
		getAdministrationPage(test);		
		
		test.log(LogStatus.PASS,"<b>ER1: Administration Screen is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "AdministrationScreen")));
		
		addEmployeePOM=new AddEmployee_POM(driver);
		addEmployeePOM.employees_link().click();
		
		test.log(LogStatus.PASS,"3. Select Employee link.<br/>"
				+ "<b>ER2: Employee records are listed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "EmployeeRecordsareListed")));
		
		log.info("Now clicking on Add new Link.");
		addEmployeePOM.addNewLink().click();
		test.log(LogStatus.PASS,"4.	Click on Add new button.");
		addEmployeePOM=new AddEmployee_POM(driver);
		
		waitTillElementVisible(addEmployeePOM.getEmployeeFullName());
		test.log(LogStatus.PASS, "<b>ER3: Add new employee dialog is presented.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "AddNewEmployeeDialog")));
						
		log.info("First checking Validation Messages. without entering in any"
				+ "field, click on Add button.");		
		
		verticalScrollingDown();
		waitTillElementVisible(addEmployeePOM.getAddBtn());
		log.info("scrolling down to click on Add button.");
		
		//addEmployeePOM.getAddBtn().click();	
		
		javaScriptClick(addEmployeePOM.getAddBtn());
		test.log(LogStatus.PASS,"5.	Click on Add Button without entering data in it.");
		test.log(LogStatus.PASS,"ER4: It displays validation messages as 'Location is required', 'Full Name is required',"+
				"'Employee ID is required', 'Business Unit is required', 'Department is required'."+
				test.addScreenCapture(captureScreenShot(driver, "ValidationMessages")));
				
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
		
		test.log(LogStatus.PASS, "6. Select location from Location dropdown.");
								
		employeeFullName=employeeData[1][1]+FunctionUtils.generateRandomNumber();
		
		addEmployeePOM.getEmployeeFullName().sendKeys(employeeFullName);
				
		log.info("setting employeeFullName using setters method so it will be helpful afterwards.");
		adminData.setEmployeeName(employeeFullName);
		
		test.log(LogStatus.PASS, "7. Enter the text data in full name field.");
		
		employeeID=employeeData[1][2]+FunctionUtils.generateRandomNumber();
		
		//addEmployeePOM.getEmployeeID().sendKeys(employeeData[1][2]+FunctionUtils.generateRandomNumber());
		
		addEmployeePOM.getEmployeeID().sendKeys(employeeID);
		
		log.info("setting employeeID using setters method so it will be helpful afterwards.");
		adminData.setEmployeeID(employeeID);
		
		test.log(LogStatus.PASS, "8. Enter the data in the Employee ID field.");
		
		addEmployeePOM.getsupervisorDropdown().selectByVisibleText(employeeData[1][3]);
				
		test.log(LogStatus.PASS, "9. Click on supervisor dropdown field.");
		
		addEmployeePOM.getbusinessUnitDropdown().selectByIndex(1);
		
		test.log(LogStatus.PASS, "10. Select the Business Unit dropdown.");
		
		test.log(LogStatus.PASS, "11. Select department from Department dropdown.");
				
		addEmployeePOM.getDepartmentDropdown().selectByVisibleText(employeeData[1][5]);	
		
		test.log(LogStatus.PASS, "<b>ER5: User should be able to add data for all mandatory fields.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "EnterMandatoryFields")));			
		
		addEmployeePOM.getAddressField().sendKeys(employeeData[1][6]);
		
		addEmployeePOM.getEmployeeCity().sendKeys(employeeData[1][7]);
		
		addEmployeePOM.getEmployeeState().sendKeys(employeeData[1][8]);
		
		addEmployeePOM.getEmployeePostalCode().sendKeys(employeeData[1][9]);
		
		addEmployeePOM.getEmployeeCountry().sendKeys(employeeData[1][10]);
		
		addEmployeePOM.getEmployeePhone().sendKeys(employeeData[1][11]);
		
		addEmployeePOM.getEmployeeemail().sendKeys(employeeData[1][12]);
		
		test.log(LogStatus.PASS, "12. Enter the data in other non-mandatory fields, (Address, City, State, Postal Code, Country, Phone, Email etc.).");
		
		test.log(LogStatus.PASS, "<b>ER6: User should able to add data for other Fields.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "employeeData")));
		
		verticalScrollingDown();
		
		waitTillElementVisible(addEmployeePOM.getAddBtn());
		
		test.log(LogStatus.PASS, "13. After entering all data in different fields from the General tab,"
		+"click on add button from the same screen."); 
						
		//addEmployeePOM.getAddBtn().click();	
		javaScriptClick(addEmployeePOM.getAddBtn());		
		
		waitTillElementVisible(addEmployeePOM.getJobCodesTab());
		
		
		//addEmployeePOM=new AddEmployee_POM(driver);
		
		test.log(LogStatus.PASS, "<b>ER7: User should be navigated to Job Codes Screen "
				+ "and job codes list should be visible.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "jobcodeList")));
			
		waitTillElementVisible(addEmployeePOM.getJobCodeVPHumanResource());
				
		addEmployeePOM.getJobCodeVPHumanResource().click();	
		
		test.log(LogStatus.PASS, "14. Select Job codes dropdown."); 
		
		waitTillElementVisible(addEmployeePOM.getSelectedJobCode());
		
		test.log(LogStatus.PASS, "ER8: It should be visible in 'selected job codes' section.  "+
				test.addScreenCapture(captureScreenShot(driver, "SelectedJobCodeList")));
		
		addEmployeePOM.getAddBtn().click();	
		
		test.log(LogStatus.PASS, "15. Click on add button from job code screen after selecting job codes.");
		
		waitTillElementVisible(addEmployeePOM.getCloseButtononSuccessMessage());
						
		if (addEmployeePOM.verifySuccessMessage()){
			
			test.log(LogStatus.PASS, "Employee created successfully."+
			test.addScreenCapture(captureScreenShot(driver, "Employee added successfully.")));
		};
		
		log.info("employee added successfully.");
		
		waitTillElementVisible(addEmployeePOM.getCloseButtononSuccessMessage());
		
		test.log(LogStatus.PASS, "<b>ER9: The employee should get added successfully and A message confirming successfully added should get displayed.<b>");
		
		addEmployeePOM.getCloseButtononSuccessMessage().click();	
		
		waitTillElementVisible(addEmployeePOM.getFilterTextBox());		
		
		test.log(LogStatus.PASS, "16. Go to employee list and click on search filter");
		
		addEmployeePOM.getFilterTextBox().sendKeys(employeeFullName);
		
		test.log(LogStatus.PASS, "17. Enter the added employee name.");
		
		addEmployeePOM=new AddEmployee_POM(driver);
		
		verticalScrollingUp();		
		
		test.log(LogStatus.PASS, "18. Click on go button.");
		
		javaScriptClick(addEmployeePOM.getGoButton());
						
		sleep(3);
		
		searchRecordInTable();
		
		test.log(LogStatus.PASS, "<b>ER10: The created employee record is displayed.<b>");
		
		addEmployeePOM.addNewLink().click();	
		
		test.log(LogStatus.PASS, "19. Click on add new link.");
		
		waitTillElementVisible(addEmployeePOM.getEmployeeID());
		
		test.log(LogStatus.PASS, "20. Enter already existing 'Full Name'");
		
		addEmployeePOM.getEmployeeFullName().sendKeys(adminData.getEmployeeName());
		
		sleep(1);
		
		addEmployeePOM.getEmployeeFullName().sendKeys(Keys.TAB);							
		
		if (addEmployeePOM.verifyUniqueEmployeeFullName()){
			
			test.log(LogStatus.PASS, "<b> ER11: A validation message 'Full Name already exists' should be displayed.<b>"+
			test.addScreenCapture(captureScreenShot(driver, "employeeName already exists.")));
		}else{			
			test.log(LogStatus.FAIL,"Not checking for duplicate employeeFullName"+
		test.addScreenCapture(captureScreenShot(driver, "FailedforDuplicateEmployeeFullName")));
		}
				
		test.log(LogStatus.PASS, "21. Enter already existing 'Employee ID'.");
		
		addEmployeePOM.getEmployeeID().sendKeys(adminData.getEmployeeID());		
		
		sleep(1);
		
		addEmployeePOM.getEmployeeID().sendKeys(Keys.TAB);
		
		if (addEmployeePOM.verifyUniqueEmployeeID()){
			
			test.log(LogStatus.PASS, "<b> ER12:A validation message 'EmployeeID already exists' should be displayed.<b>"+
					test.addScreenCapture(captureScreenShot(driver, "employeeID already exists.")));
		}else{			
			test.log(LogStatus.FAIL,"FailedforDuplicateEmployeeID");
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
				//test.log(LogStatus.PASS, "record found in the Search result."+
					//	test.addScreenCapture(captureScreenShot(driver, "foundRecord")));
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
