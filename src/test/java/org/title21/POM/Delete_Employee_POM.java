package org.title21.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.title21.dao.AdminData;

public class Delete_Employee_POM
{
	public WebDriver driver;
	public WebElement element;
	AdminData adminData=new AdminData();
	public Delete_Employee_POM(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-control.t21-placeholder")
	WebElement Employeefilterresult;
	
	@FindBy(css=".modal-title>span")
	WebElement deleteEmployeePopUpHeaderText;
	
	@FindBy(xpath="//button[@type='submit'][@tabindex='1']")
	WebElement Employeefilterresutgobutton;
	
   @FindBy(xpath="//input[@value='Yes']")
   WebElement deleteEmployeePopUpYesButton;

   
	
@FindBy(css=".btn.t21-btn-default.pull-left")
WebElement deleteEmployeePopUpNoButton;
   
@FindBy(xpath="//button[text()='Close']")
WebElement ConfirmPopUpCloseButton;
	
@FindBy(xpath="//*[text()='Message']")
WebElement deleteEmployeeConfirmPopUpHeaderText;

@FindBy(xpath="//*[text()='No Employee found']")
WebElement noEmployeefoundresulttext;


@FindBy(xpath="//*[text()='Can't delete this system account.']")
WebElement ownuserdeletepopup;

	public WebElement  EmployeeFilterResult()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return Employeefilterresult;
	 }
	public WebElement  noemployeebutton()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return  deleteEmployeePopUpNoButton;
	 } 
	
	
	 public WebElement  EmployeeFilterResutGoButton()
	 {
		 //element=driver.findElement(Employeefilterresutgobutton); 
		 return Employeefilterresutgobutton;
	 }
	 public WebElement deleteEmployeePopUpHeaderText()
	 {
		// element=driver.findElement(deleteEmployeePopUpHeaderText);
		 return deleteEmployeePopUpHeaderText;
	 }
	 public WebElement deleteEmployeePopUpYesButton()
	 {
		 //element=driver.findElement(Employeeslink);
		 return deleteEmployeePopUpYesButton;
	 }
	 public boolean verifyDeleteEmployeePopUp(){
			
			String DeleteEmployeeHeaderText = deleteEmployeePopUpHeaderText().getText();
			
			if(DeleteEmployeeHeaderText.equalsIgnoreCase("Delete Employee"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	
	 public boolean verifyDeleteEmployeecConfirmPopUpText(){
			
			String DeleteEmployeeHeaderText = deleteEmployeeConfirmPopUpHeaderText.getText();
			
			if(DeleteEmployeeHeaderText.equalsIgnoreCase("Message"))
			{
				return true;
			}
			else
			{
				return false;
			}
	 }
	 
	 public WebElement ConfirmPopUpCloseButton()
	 {
		 return ConfirmPopUpCloseButton;
	 }
	 
	 public WebElement noEmployeeFoundResultText()
	 {
		 //element=driver.findElement(noEmployeefoundresulttext);
		 return noEmployeefoundresulttext;
	 }
	 public boolean verifyNoEmployeeFoundText(WebDriver driver){
			
			String NoEmployeeFoundResultText="";
			
			try 
			{
				NoEmployeeFoundResultText = noEmployeeFoundResultText().getText();
				
			}catch(NoSuchElementException e) {
				
			}
			if(NoEmployeeFoundResultText.equalsIgnoreCase("No Employee found"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean verifydeleteownuser(WebDriver driver){
			
			String Delete_system_user="";
			
			try 
			{
				Delete_system_user = ownuserdeletepopup.getText();
				
			}catch(NoSuchElementException e) {
				
			}
			if(Delete_system_user.equalsIgnoreCase("Can't delete this system account."))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
}
