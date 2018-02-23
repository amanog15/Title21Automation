package org.title21.test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.title21.POM.UpdateEmployee_POM;
import org.title21.POM.DashBord_POM;
import org.title21.POM.Delete_Employee_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;
import org.title21.utility.FunctionUtils;

import com.relevantcodes.extentreports.LogStatus;

public class UpdateEmployee_Test extends BaseClass {
	LoginPage_POM login;
	LogoutPage_POM logout;
	UpdateEmployee_POM emp;
	UpdateEmployee_POM emp2;
	DashBord_POM dashboardObj;
	SoftAssert softAssertion = new SoftAssert();
	String className = "";
	String employeeID = "";
	String supervisor = "";
	String EditDepartment = "";
	String EditAddressField = "";
	String EmployeeCity = "";
	String EmployeeState = "";
	String EmpStringloyeePostalCode = "";
	String EmployeeCountry = "";
	String EmployeePhone = "";
	String employeeemail = "";

	boolean EmployeePresenceAfterSearch = false;
	static Logger log = Logger.getLogger(UpdateEmployee_Test.class);

	@BeforeClass
	public void openURL() {
		getBrowser();
		className = this.getClass().getName();
		createDirectory(className);
		login = new LoginPage_POM(driver);
		login.loginFunction();
		
	}

	@Test(testName = "update employee ", groups = "update_Employee", priority = 0)
	public void Edit_general_Employee() throws Exception {
		test = extent.startTest("Update  Employee");
		test.log(LogStatus.PASS, "1 Login to the web interface");
		getAdministrationPage(test);
		test.log(LogStatus.PASS, "ER1: Administration Screen is displayed."
				+ test.addScreenCapture(captureScreenShot(driver, " Administration_Screenis_displayed.")));
		emp = new UpdateEmployee_POM(driver);
		AdminData adminData = new AdminData();
		supervisor = employeeData[2][3];
		EditDepartment = employeeData[2][5];
		EditAddressField = employeeData[2][6];
		EmployeeCity = employeeData[2][7];
		EmployeeState = employeeData[2][8];
		EmpStringloyeePostalCode = employeeData[2][9];
		EmployeeCountry = employeeData[2][10];
		EmployeePhone = employeeData[2][11];
		employeeemail = employeeData[2][12];
		System.out.print(adminData.getEmployeeName());
		test.log(LogStatus.PASS, "3. Select Employee link"+"<br/>"
			     +"<b>ER2: Employee records are listed. <b>"+
				test.addScreenCapture(captureScreenShot(driver, "Employeerecords")));
		waitTillElementVisible(emp.EmployeeFilterResutGoButton());
		emp.getGridLocation().selectByVisibleText("Antioch");
		test.log(LogStatus.PASS, "4. Click on location drop-down and select the specific location"+"<br/>"
			     +"<b>ER3: Only Employees of selected location are displayed. <b>"+
				test.addScreenCapture(captureScreenShot(driver, "Employeelocation")));
        sleep(2);
		emp.getGridLocation().selectByVisibleText("Dallas");
		sleep(2);
		emp.EmployeeFilterResult().sendKeys("xxxxxxxxxx");
		emp.EmployeeFilterResutGoButton().click();
		sleep(2);
		System.out.print("out" + emp.getnoemployeemsg().getText());
		if (emp.NoEmpMsg()) {
			test.log(LogStatus.PASS, "5. Enter invalid data in employee field and click on  GO button"+"<br/>"
				     +"<b>ER4: It  show validation messages as No Employee Found <b>"+
					test.addScreenCapture(captureScreenShot(driver, "No_Employee_Found")));
		}
		sleep(2);
		emp.getGridLocation().selectByVisibleText("Dallas");
		emp.EmployeeFilterResult().clear();
		emp.EmployeeFilterResult().sendKeys(adminData.getEmployeeName());// Remove Employee
		sleep(2);
		emp.EmployeeFilterResutGoButton().click();
		for (int i = 1; i <= 10; i++) {
			sleep(1);
			String Employees = driver
					.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr[" + i + "]/td[1]")).getText();
			sleep(1);
			if (Employees.equalsIgnoreCase(adminData.getEmployeeName())) {
				EmployeePresenceAfterSearch = true;
				test.log(LogStatus.PASS, "6. Enter the already existing employee information in in search field  and click on GO button."+"<br/>"
					     +"<b>ER5: It displayed employee as per given information. <b>"+
						test.addScreenCapture(captureScreenShot(driver, "Employee_info")));
				break;
			} 

			

		}

		for (int i = 1; i <= 10; i++) {

			WebElement employee = driver
					.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr[" + i + "]/td[1]"));
			String empName = employee.getText();

			if (empName.equalsIgnoreCase(adminData.getEmployeeName())) {

				WebElement Edit = driver.findElement(By
						.xpath("//tbody[@class='t21-js-clickable-rows']/tr[" + i + "]//span[@title='Edit Employee']"));
				Edit.click();
				test.log(LogStatus.PASS, "7. Click on Edit Employee Button"+"<br/>"
					     +"<b>ER6: Update employee dialog is displayed and the general tab is presented.<b>"+
						test.addScreenCapture(captureScreenShot(driver, "update_emp")));
				break;
			}
		}
		

		log.info("Update Employee Data");
		emp.getsupervisorDropdown().selectByVisibleText(employeeData[2][3]);

		emp.getbusinessUnitDropdown().selectByIndex(1);
		emp.getDepartmentDropdown().selectByVisibleText(employeeData[2][5]);
		System.out.println(emp.getDepartmentDropdown().getFirstSelectedOption().getText());
		emp.getAddressField().clear();

		emp.getAddressField().sendKeys(employeeData[2][6]);
		sleep(1);
		emp.getEmployeeCity().clear();

		emp.getEmployeeCity().sendKeys(employeeData[2][7]);
		emp.getEmployeeState().clear();

		emp.getEmployeeState().sendKeys(employeeData[2][8]);

		emp.getEmployeePostalCode().clear();

		emp.getEmployeePostalCode().sendKeys(employeeData[2][9]);

		emp.getEmployeeCountry().clear();

		emp.getEmployeeCountry().sendKeys(employeeData[2][10]);

		emp.getEmployeePhone().clear();

		emp.getEmployeePhone().sendKeys(employeeData[2][11]);

		emp.getEmployeeemail().clear();

		emp.getEmployeeemail().sendKeys(employeeData[2][12]);
		// emp.getAddBtn().click();
		// javaScriptClick(emp.getAddBtn());
		waitTillElementVisible(emp.getAddBtn());

		// addEmployeePOM.getAddBtn().click();
		javaScriptClick(emp.getAddBtn());
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());

		if (emp.EditverifySuccessMessage()) {

			test.log(LogStatus.PASS, "8. Edit the supervisor, Business Unit, department, state and country"+"<br/>"+"9. Click on confirm button. "+"<br/>"
				     +"<b>ER7: A message confirming successful update is displayed <b>"+
					test.addScreenCapture(captureScreenShot(driver, "update_emp")));
		}
		;

		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		sleep(2);
		emp.EmployeeEdit().click();
		sleep(2);
		emp2 = new UpdateEmployee_POM(driver);
		sleep(2);
		if (emp2.Editsupervisor(supervisor)&&emp.EditbusinessUnit(driver)&&emp.EditDepartment(EditDepartment)&&emp.EditAddressField(EditAddressField)&&emp.getEmployeeCity(EmployeeCity)&&emp.getEmployeeState(EmployeeState)) 
{
			test.log(LogStatus.PASS, "10. Again click on edit button."+"<br/>"
				     +"<b>ER8: All changed fields are properly updated. <b>"+
						test.addScreenCapture(captureScreenShot(driver, "update_change")));

		}
		sleep(2);
		waitTillElementVisible(emp.getAddBtn());
		javaScriptClick(emp.getAddBtn());
		sleep(2);
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		sleep(2);
		verticalScrollingUp();
		verticalScrollingUp();
		waitTillElementVisible(emp.EmployeeEdit());
		emp.EmployeeEdit().click();
		if (emp.getEmployeeID().isEnabled() && emp.getEmployeeFullName().isEnabled()) 
		{
			test.log(LogStatus.FAIL, "11.Edit the employee again."+"<br/>"+"12.Try to edit Full Name and Employee ID field ."+"<br/>"
				     +"<b>ER9: User able to edit the Full name and Employee ID field. <b>"+
					test.addScreenCapture(captureScreenShot(driver, "Edit_name_id")));

		} else {
			sleep(2);
			test.log(LogStatus.PASS, "11.Edit the employee again."+"<br/>"+"12.Try to edit Full Name and Employee ID field ."+"<br/>"
				     +"<b>ER9: User not be able to edit the Full name and Employee ID field. <b>"+
					test.addScreenCapture(captureScreenShot(driver, "Edit_name_id")));

		}
		sleep(2);
		waitTillElementVisible(emp2.getAddBtn());

		// addEmployeePOM.getAddBtn().click();
		javaScriptClick(emp2.getAddBtn());
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		String CanceltEmployeePostalCode = "2222";
		String CancelEmployeeCountry = "test";
		String CancelEmployeePhone = "677888888";
		sleep(2);
		verticalScrollingUp();
		verticalScrollingUp();
		sleep(2);
		emp2.EmployeeEdit().click();
		sleep(3);
		//waitTillElementVisible(emp2.getEmployeePostalCode());
		
		emp.getEmployeePostalCode().clear();

		emp.getEmployeePostalCode().sendKeys(CanceltEmployeePostalCode);

		emp.getEmployeeCountry().clear();

		emp.getEmployeeCountry().sendKeys(CancelEmployeeCountry);

		emp.getEmployeePhone().clear();

		emp.getEmployeePhone().sendKeys(CancelEmployeeCountry);
		sleep(2);
		verticalScrollingDown();
		verticalScrollingDown();
		sleep(2);
		javaScriptClick(emp.cancel_Btn());
		sleep(2);
		verticalScrollingUp();
		verticalScrollingUp();
		emp2.EmployeeEdit().click();
		if (emp.getEmpStringloyeePostalCode(CanceltEmployeePostalCode) && emp.getEmployeeCountry(CancelEmployeeCountry)
				&& emp.getEmployeePhone(CancelEmployeePhone)) {

test.log(LogStatus.FAIL, "13.Edit the employee"+"<br/>"+"14.Edit the data "+"<br/>"+"15.Click on the cancel button "+"<br/>"
		     +"<b>ER10: Records are  changed. <b>"+
			test.addScreenCapture(captureScreenShot(driver, "cancel_edit")));
		} else {

         test.log(LogStatus.PASS, "13.Edit the employee"+"<br/>"+"14.Edit the data "+"<br/>"+"15.Click on the cancel button "+"<br/>"
		     +"<b>ER10: Records are  unchanged. <b>"+
			test.addScreenCapture(captureScreenShot(driver, "cancel_edit")));;
		}


		sleep(2);
		waitTillElementVisible(emp.getJobCodesTab());
		emp.getJobCodesTab().click();
		sleep(2);
		if (emp.EditJobTele().isDisplayed()) {
			test.log(LogStatus.PASS, "16. Click on Job Codes Tab in Update Employee Screen."+"<br/>"
				     +"<b>ER11: User should be navigated to Job Codes Screen and job codes list should be visible. <b>"+
					test.addScreenCapture(captureScreenShot(driver, "jobcode_screen")));
		}
		log.info("verify job  code  added to  selected job codes  section.");
		waitTillElementVisible(emp.getjobCodeHR());

		emp.getjobCodeHR().click();
		sleep(2);

		waitTillElementVisible(emp.getSelectedJobCode());

		log.info("Verify to click on selected job code");

		WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='001099']"));
		oCheckBox.click();
        
		test.log(LogStatus.PASS, "17.Select one job code from Select a Job code section."+"<br/>"
		     +"<b>ER12: It should get added to selected job codes section.<b>"+
			test.addScreenCapture(captureScreenShot(driver, "select_jobcode")));
		sleep(2);
		emp.EditJobTele().click();
		/*
		 * test.log(LogStatus.PASS, "new job code  added successfully " +
		 * test.addScreenCapture(captureScreenShot(driver,
		 * " New job code  added successfully ")));
		 */
		sleep(2);
		String addimg = test.addScreenCapture(captureScreenShot(driver, " New_job_code _added_successfully "));
sleep(2);
		emp.JobCodeRemovedMinus().click();
		sleep(2);
		test.log(LogStatus.PASS, "18. Click on minus button from selected job codes section."+"<br/>"+"<br/>"+addimg
			     +"<b>ER13: Job code is removed <b>"+
				test.addScreenCapture(captureScreenShot(driver, "removed_jobcode")));
		waitTillElementVisible(emp.getAddBtn());
            javaScriptClick(emp.getAddBtn()); 
		// addEmployeePOM.getAddBtn().click();

		waitTillElementVisible(emp.getCloseButtononSuccessMessage());

		/*if (emp.EditverifySuccessMessage()) {

			test.log(LogStatus.PASS, "18"
					+ test.addScreenCapture(captureScreenShot(driver, "job_code_removed")));
		}
		;*/
		
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		sleep(2);
		emp2.EmployeeEdit().click();
		//verticalScrollingUp();
		waitTillElementVisible(emp.getJobCodesTab());
		emp.getJobCodesTab().click();
		emp.jobCodeRadio().click();
		if (emp.jobCodeRadio().isDisplayed()) {

test.log(LogStatus.PASS, "18. Click on confirm."+"<br/>"+" 19. Again click on edit button"+"<br/>"
		     +"<b>ER14: Default job code is changed and job code updated  <b>"+
			test.addScreenCapture(captureScreenShot(driver, "jobcode_jobcode")));
		}

		waitTillElementVisible(emp.getAddBtn());

		// addEmployeePOM.getAddBtn().click();
		javaScriptClick(emp.getAddBtn());
		sleep(2);
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();

		log.info("Now calling logout function.");

		/*
		 * logout = new LogoutPage_POM(driver);
		 * 
		 * logout.logoutFunction();
		 * 
		 * log.info("logout successfully."); sleep(2); extent.endTest(test);
		 */

	}

	@AfterClass
	public void closeBrowserInstance() {

		// driver.close();
	}
}
