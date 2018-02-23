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

	@FindBy(xpath="//button[text()='Close']")
    WebElement ConfirmPopUpCloseButton;
	
	@FindBy(xpath="//*[text()='Message']")
	WebElement deleteEmployeeConfirmPopUpHeaderText;

	@FindBy(xpath="//h5[ starts-with(@class,'t21-padding') and contains (text(),'No employee found')]")
	WebElement noEmployeefoundresulttext;

	@FindBy(xpath="//*[text()='Can't delete this system account.']")
	WebElement ownuserdeletepopup;
	
	@FindBy(xpath="//div[contains(@class,'user-message-text')]")
	WebElement confirmationDeleteMessage;		

	public WebElement  EmployeeFilterResult()
	 {
		 return Employeefilterresult;
	 }
	 
	 public WebElement  EmployeeFilterResutGoButton()
	 {
		return Employeefilterresutgobutton;
	 }
	 public WebElement deleteEmployeePopUpHeaderText()
	 {
		return deleteEmployeePopUpHeaderText;
	 }
	 public WebElement deleteEmployeePopUpYesButton()
	 {		
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
			
			//String DeleteEmployeeHeaderText = deleteEmployeeConfirmPopUpHeaderText.getText();
			String deleteEmployeeMessageText=confirmationDeleteMessage.getText();
			
			if(deleteEmployeeMessageText.equalsIgnoreCase("deleted successfully"))
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
		 return noEmployeefoundresulttext;
	 }
	 
	 public boolean verifyNoEmployeeFoundText(){
			
			String NoEmployeeFoundResultText="";			
			NoEmployeeFoundResultText = noEmployeeFoundResultText().getText();			
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
			
			Delete_system_user = ownuserdeletepopup.getText();
				
			
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
