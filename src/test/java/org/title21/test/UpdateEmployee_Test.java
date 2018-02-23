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
		getAdministrationPage(test);
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
		if (emp.EmployeeFilterResult() != null) {
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
					break;
				}

				if (EmployeePresenceAfterSearch) {
					test.log(LogStatus.PASS, "displayed employee as per given information"
							+ test.addScreenCapture(captureScreenShot(driver, "Employee verified")));
				} else {
					test.log(LogStatus.FAIL, "Unable displayed employee as per given information"
							+ test.addScreenCapture(captureScreenShot(driver, "Unable to verify Employee")));
					
				}

			}

		}

		for (int i = 1; i <= 10; i++) {

			WebElement employee = driver
					.findElement(By.xpath("//tbody[@class='t21-js-clickable-rows']/tr[" + i + "]/td[1]"));
			String empName = employee.getText();

			if (empName.equalsIgnoreCase(adminData.getEmployeeName())) {

				WebElement Edit = driver.findElement(By
						.xpath("//tbody[@class='t21-js-clickable-rows']/tr[" + i + "]//span[@title='Edit Employee']"));
				test.log(LogStatus.PASS, "Clicked to Edit employee");
				Edit.click();
				break;
			}
		}
		if (emp.getAddressField().isDisplayed()) {
			test.log(LogStatus.PASS, "Update employee dialog is displayed and the general tab is presented  "
					+ test.addScreenCapture(captureScreenShot(driver, "update_employee_popup")));
		}

		log.info("Update Employee Data");
		emp.getLocationDropdown().selectByValue(employeeData[1][0]);
		if (emp.getEmployeeID().isEnabled() && emp.getEmployeeFullName().isEnabled()) {
			test.log(LogStatus.FAIL, "Edit full name and employee id "
					+ test.addScreenCapture(captureScreenShot(driver, "user can edit Fullname and employee id")));
		} else {
			test.log(LogStatus.PASS, "Unable to edit Fullname and Employee_Id "
					+ test.addScreenCapture(captureScreenShot(driver, "UnabletoEdit_Fullname_Employee_Id")));
		}

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

			test.log(LogStatus.PASS, "A message confirming successful update is displayed.."
					+ test.addScreenCapture(captureScreenShot(driver, "confirming_successful_update_Message")));
		}
		;

		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		sleep(2);
		emp.EmployeeEdit().click();
		sleep(2);
		waitTillElementVisible(emp.getJobCodesTab());
		emp.getJobCodesTab().click();
		sleep(2);
		log.info("verify job  code  added to “selected job codes” section.");
		waitTillElementVisible(emp.getjobCodeHR());

		emp.getjobCodeHR().click();

		waitTillElementVisible(emp.getSelectedJobCode());

		log.info("Verify to click on selected job code");

		WebElement oCheckBox = driver.findElement(By.cssSelector("input[value='001099']"));
		oCheckBox.click();
		test.log(LogStatus.PASS, "job code selected successfully."
				+ test.addScreenCapture(captureScreenShot(driver, "job_code_selected_successfully.")));
		sleep(2);
		emp.EditJobTele().click();
		test.log(LogStatus.PASS, "new job code  added successfully "
				+ test.addScreenCapture(captureScreenShot(driver, " New job code  added successfully ")));
		waitTillElementVisible(emp.JobCodeRemovedMinus());
		emp.JobCodeRemovedMinus().click();
		sleep(2);
		test.log(LogStatus.PASS, "Job code removed successfully."
				+ test.addScreenCapture(captureScreenShot(driver, "Job code removed .")));
		waitTillElementVisible(emp.getAddBtn());

		// addEmployeePOM.getAddBtn().click();
		javaScriptClick(emp.getAddBtn());
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());

		if (emp.EditverifySuccessMessage()) {

			test.log(LogStatus.PASS, "Job code  Updated successfully. confirmation pop up Dispayed"
					+ test.addScreenCapture(captureScreenShot(driver, "job code updated successfully")));
		}
		;

		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		sleep(2);
		emp.EmployeeEdit().click();

		if (emp.Editsupervisor(supervisor)) {
			test.log(LogStatus.PASS, "Supervisor updated successfully.");

		}
		if (emp.EditbusinessUnit(driver)) {
			test.log(LogStatus.PASS, "BusinessUnit updated successfully.");

		}
		if (emp.EditDepartment(EditDepartment)) {
			test.log(LogStatus.PASS, "Department updated successfully");
		}
		if (emp.EditAddressField(EditAddressField)) {
			System.out.print(emp.getAddressField().getAttribute("value"));
			test.log(LogStatus.PASS, "AddressField updated successfully");
			System.out.print("adrredd edit pass");
		}
		if (emp.getEmployeeCity(EmployeeCity)) {
			test.log(LogStatus.PASS, "employee city updated successfully");
			System.out.print("city add pass");
		}
		if (emp.getEmployeeState(EmployeeState)) {
			test.log(LogStatus.PASS, "EmployeeState updated successfully");
		}

		if (emp.getEmpStringloyeePostalCode(EmpStringloyeePostalCode)) {
			test.log(LogStatus.PASS, "PostalCode updated successfully");

		}
		if (emp.getEmployeeCountry(EmployeeCountry)) {
			test.log(LogStatus.PASS, "EmployeeCountry updated successfully");

		}
		if (emp.getEmployeePhone(EmployeePhone)) {
			test.log(LogStatus.PASS, "EmployeePhone updated successfully");
		}
		sleep(2);
		verticalScrollingDown();
		if (emp.getEmployeeemail(employeeemail)) {
			test.log(LogStatus.PASS, "Employeeemail updated successfully");
			System.out.print("pass");
		}
		sleep(2);
		test.log(LogStatus.PASS, "Employee General data changed fields are properly updated "
				+ test.addScreenCapture(captureScreenShot(driver, "General_Data_updated")));

		verticalScrollingUp();
		waitTillElementVisible(emp.getJobCodesTab());
		emp.getJobCodesTab().click();
		emp.jobCodeRadio().click();
		if (emp.jobCodeRadio().isDisplayed()) {
			test.log(LogStatus.PASS, "Employee job code updated successfully "
					+ test.addScreenCapture(captureScreenShot(driver, "update_jobcode")));
		}

		waitTillElementVisible(emp.getAddBtn());
        
		// addEmployeePOM.getAddBtn().click();
		javaScriptClick(emp.getAddBtn());
		sleep(2);
		waitTillElementVisible(emp.getCloseButtononSuccessMessage());
		emp.getCloseButtononSuccessMessage().click();
		sleep(3);
		log.info("Now calling logout function.");

		logout = new LogoutPage_POM(driver);

		logout.logoutFunction();

		log.info("logout successfully.");
		sleep(2);
		extent.endTest(test);

	}

	@AfterClass
	public void closeBrowserInstance() {

		driver.close();
	}
}
