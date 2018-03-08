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
	String AppendixNumber="29";
	String Appendix="txt";
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
		test = extent.startTest("Create  Document");
		test.log(LogStatus.PASS, "1 Login to the web interface");
		sleep(2);
		Credoc=new CreateDocument_POM(driver);
		Credoc.getnewdoc().click();
		sleep(2);
		waitTillElementVisible(Credoc.getdocument());
		Credoc.getdocument().click();
		sleep(3);
		//Credoc.getdocumentnumber().getText();
		//System.out.print(Credoc.getdocumentnumber().getText());
		
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
if (Credoc.DocumentTitlemsgvalidation()&& Credoc.Documentsummarymsgvalidation())
{
	
	test.log(LogStatus.PASS, "3. Click on create a button without entering document title and Document Change Summary. "+"<br/>"
		     +"<b>ER3: It should show validation message as \"Document title is required\"& " + 
		   " \" Change Summary is required\"\n" + 
		     " <b>"+
			test.addScreenCapture(captureScreenShot(driver, "validationmessagefortitleand")));
};
 sleep(2);
Credoc.GeteditdocumentNo().click();
sleep(2);
verticalScrollingDown();
Credoc.getConfirmButton().click(); 
if (Credoc. Appedixvalidation())
{
	
	test.log(LogStatus.PASS, " 4.Click on \"document no\" fields edit button. "+"<br/>"+"5.Click on create button."+"<br/>"
			  +"<b>ER4:  Application should display the validation message as Please enter a Document Append <b>"+
				test.addScreenCapture(captureScreenShot(driver, "Doc_Dialog")));	 
};
sleep(2);
Credoc.getnumberappedix().selectByVisibleText(AppendixNumber);
Credoc.Appendix().sendKeys(Appendix);
sleep(2);
//System.out.print("value is"+Credoc.getdocumentnumber().getAttribute("value"));
  String Document_number=Credoc.getdocumentnumber().getAttribute("value");
  if(Document_number.contains(AppendixNumber) &&Document_number.contains(Appendix))
  {
	  
	  test.log(LogStatus.PASS, " 6.	Edit the \"document no\" field "+"<br/>"+"7. Select number from the number drop-down field"+"<br/>"+"8.Enter appendix in appendix field."+"<br/>"
			  +"<b> ER5: Document number should get changed as per number and appendix selected by the user.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "Document_number")));	
  
  }
  Credoc.getDocumentTitle().sendKeys("Test");
  Credoc.getDocChangeSummary().sendKeys("test summary");
  verticalScrollingDown();
  Credoc.getConfirmButton().click();
  sleep(2);
  if(Credoc.getdocumentcreationverify().isDisplayed())
  {
  test.log(LogStatus.PASS,"9. Enter all  mandatory field "+"<br/>"
		  +"<b> ER6: Document should save to Draft cabinet.<b>"+
			test.addScreenCapture(captureScreenShot(driver, "create_document")));	

 }
  sleep(2);
 /* Credoc.getEditModeOff().click();
  test.log(LogStatus.PASS,"10.Turn upper right-hand corner Edit Mode to off. "+"<br/>"
		  +"<b> ER7: All the documents fields should get disabled..<b>"+
			test.addScreenCapture(captureScreenShot(driver, "Edit_Mode_Off")));
  Credoc.getEditModeON().click();*/
  
  Credoc.getPlusButtonuploadfile().click();
  sleep(2);
 /*  Credoc.getBrouse().sendKeys("C:\\Users\\dell\\Desktop\\Title21data\\testing_data\\Sizeval.doc");
   sleep(7);
   Credoc.getAddButtonupload().click();
	//javaScriptClick(Credoc.getAddButtonupload());
	  sleep(6);
if(Credoc.UploadFileSizeValidation())
  {
	  test.log(LogStatus.PASS,"14. Add file with size more than 50 MB  and click on Add  button  "+"<br/>"
			  +"<b> ER6: ER 11 : It should show validation message as \"File size must be less than 50 MB\".<b>"+
				test.addScreenCapture(captureScreenShot(driver, "File_Size")));	
	 
  }*/
  Credoc.getBrouse().sendKeys("C:\\Users\\dell\\Desktop\\Title21data\\testing_data\\doc_creation.txt");
  Credoc.getAddButtonupload().click();
  sleep(2);
  Credoc.getcontextmenu().click();
  sleep(2);
  Credoc.getcheckin().click();
  sleep(2);
  Credoc.getcheckinbuttonwindow().click();
  sleep(2);
  
  if(Credoc.CheckinSuceessmessage())
  {
	  test.log(LogStatus.PASS,"19. Go to Action section and click on Check-In.  "+"<br/>"
			  +"<b>ER 11 :  A Successful message that the document has been checked In is displayed.<b>"+
				test.addScreenCapture(captureScreenShot(driver, "checkinsuccessmessage")));	
}
  Credoc.getcheckincancel().click();
  
  }
  @AfterClass
	public void closeBrowserInstance() 
	{

		extent.endTest(test);
        logout = new LogoutPage_POM(driver);
		logout.logoutFunction();
		log.info("logout successfully."); 
		sleep(2); 
		extent.endTest(test);

		driver.close();
	}
}
