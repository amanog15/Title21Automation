package org.title21.test;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.title21.POM.AddEmployee_POM;
import org.title21.POM.CreateDocument_POM;
import org.title21.POM.LoginPage_POM;
import org.title21.POM.LogoutPage_POM;
import org.title21.POM.UpdateEmployee_POM;
import org.title21.dao.AdminData;
import org.title21.utility.BaseClass;

import com.relevantcodes.extentreports.LogStatus;

public class CreateDocument_Test extends BaseClass 
{
	LoginPage_POM login; 
	LogoutPage_POM logout;
	CreateDocument_POM Credoc;
	String className = "";
	static Logger log = Logger.getLogger(CreateDocument_Test.class);
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
	
	@Test(testName = "create  document ", groups = "create_doc", priority = 0)
  public void Create_doc() 
  {
		test = extent.startTest("Update  Employee");
		test.log(LogStatus.PASS, "1 Login to the web interface");
		sleep(2);
		Credoc=new CreateDocument_POM(driver);
		Credoc.getnewdoc().click();
		sleep(2);
		waitTillElementVisible(Credoc.getdocument());
		Credoc.getdocument().click();
		sleep(3);
		test.log(LogStatus.PASS, "1.From the Main menu click on New and select Document "+"<br/>"
			     +"<b>ER1: New document dialog appears. <b>"+
				test.addScreenCapture(captureScreenShot(driver, "Doc_Dialog")));
		sleep(2);
		Credoc.getlocationDrodown().selectByVisibleText("Dallas");
		sleep(2);
		test.log(LogStatus.PASS, "1.Click on Search by location drop-down and select one Location Ensure the document type selected does not have a template associated with it"+"<br/>"
							     +"<b>ER2: document type of selected location is displayed.<b>"+
								test.addScreenCapture(captureScreenShot(driver, "location_list")));
		sleep(2);
		Credoc.getConfirmButton().click();
		sleep(2);
if (Credoc.DocumentTitlemsgvalidation()&& Credoc.Documentsummarymsgvalidation()){
	
	test.log(LogStatus.PASS, "3. Click on create a button without entering document title and Document Change Summary. "+"<br/>"
		     +"<b>ER3: It should show validation message as \"Document title is required\"& " + 
		   " \" Change Summary is required\"\n" + 
		     " <b>"+
			test.addScreenCapture(captureScreenShot(driver, "validationmessagefortitleand")));
};
         
         
         
 }
	@AfterClass
	public void closeBrowserInstance() {

		extent.endTest(test);		

		// driver.close();
	}
}
