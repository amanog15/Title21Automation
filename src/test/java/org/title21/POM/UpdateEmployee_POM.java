package org.title21.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.title21.dao.AdminData;
import org.title21.test.UpdateEmployee_Test;
import org.title21.utility.BaseClass;
import org.title21.validation.entities.ErrorMessages;

public class UpdateEmployee_POM extends AddEmployee_POM 
{
	public WebElement Editelement;
	public UpdateEmployee_POM(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
	}
	UpdateEmployee_Test update_emp_test=new UpdateEmployee_Test();
	
	@FindBy(css=".form-control.t21-placeholder")
	WebElement Employeefilterresult;

	@FindBy(xpath="//button[@type='submit'][@tabindex='1']")
	WebElement Employeefilterresutgobutton;
	
	
	@FindBy(css="input[value='001099']")
	WebElement jobCodeRadio;
	
	@FindBy(xpath=".//*[@id='t21-workarea']/div/div/div[2]/div[2]/div/h5")
	WebElement noemployeemsg;
	
	@FindBy(xpath=".//*[@id='GridLocation']")
	WebElement GridLocation;
	
	@FindBy(css=".t21-js-user-message-text")
	WebElement successMessageText;	
	@FindBy(css=".fa fa-minus-circle")
	WebElement deletejobcodeminus;	

	@FindBy(xpath="//button[text()='Close']")
    WebElement ConfirmPopUpCloseButton;
	
	@FindBy(xpath="//a[contains(text(),'Human Resources Clerk')]")// 01025 - Telerecruiter
	WebElement EditjobCodesDropdown;
	
	
	@FindBy(xpath="//a[contains(text(),'Telerecruiter')]")// 01025 - Telerecruiter
	WebElement EditTelerecruiter;
	
	@FindBy(xpath=".//*[@id='tab2']/div/div[1]/div/table/tbody/tr[3]/td[1]/a/span")
	WebElement JobCode_Removed_minus;
	
	@FindBy(xpath="//*[text()='Message']")
	WebElement deleteEmployeeConfirmPopUpHeaderText;
	
	@FindBy(xpath="//tbody[@class='t21-js-clickable-rows']/tr[\"+1+\"]//span[@title='Edit Employee']")
	WebElement Edit;
	
	
	public WebElement  EditJobTele()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return EditTelerecruiter;
	 }
	public WebElement  getnoemployeemsg()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return noemployeemsg;
	 }
	public WebElement  EmployeeEdit()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return Edit;
	 }
	public WebElement  jobCodeRadio()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return jobCodeRadio;
	 }
	public WebElement  EmployeeFilterResult()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return Employeefilterresult;
	 }
	 public WebElement  EmployeeFilterResutGoButton()
	 {
		 //element=driver.findElement(Employeefilterresutgobutton); 
		 return Employeefilterresutgobutton;
	 }
	 public WebElement  getDeletejobcodeminus()
	 {
		 //element=driver.findElement(Employeefilterresult); 
		 return deletejobcodeminus;
	 }
	public WebElement getEditSuccessMessage(){
		
		return successMessageText;
	}
	public WebElement getjobCodeHR()
	{
		return EditjobCodesDropdown;
	}
	 public WebElement ConfirmPopUpCloseButton()
	 {
		return ConfirmPopUpCloseButton;
	 }
	 public WebElement JobCodeRemovedMinus()
	 {
		return JobCode_Removed_minus;
	 }
	 
	 public boolean EditverifySuccessMessage() 
	 {
			
		 Editelement=getEditSuccessMessage();
			String errorMessage = Editelement.getText();
			boolean isSuccessMessagePresent=false;		
			
			if(errorMessage.contains(ErrorMessages.EditEmployeeSuccessMessage))
			{
				isSuccessMessagePresent=true;			
			}
			return isSuccessMessagePresent;		
		}
	 
	 
	 public boolean EditbusinessUnit(WebDriver driver){
			
		 String	EditbusinessUnit="";
			
			try 
			{
				EditbusinessUnit =getbusinessUnitDropdown().getFirstSelectedOption().getText();
				
			}catch(NoSuchElementException e) {
				
			}
			if(EditbusinessUnit.equalsIgnoreCase("Synergen Life Sciences"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean Editsupervisor(String Supervisor){
			
		 String	Editsupervisor="";
			
			try 
			{
				Editsupervisor =getsupervisorDropdown().getFirstSelectedOption().getText();
				
			}catch(NoSuchElementException e) {
				
			}
			if(Editsupervisor.equalsIgnoreCase(Supervisor))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean EditDepartment(String Department){
			
		 String	EditDepartment="";
			
			try 
			{
				EditDepartment =getDepartmentDropdown().getFirstSelectedOption().getText();
				
			}catch(NoSuchElementException e) {
				
			}
			if(EditDepartment.equalsIgnoreCase(Department))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean NoEmpMsg(){
			
		 String	noempmsg="";
			
			try 
			{
				noempmsg =getnoemployeemsg().getText();
				
			}catch(NoSuchElementException e) {
				
			}
			if(noempmsg.equalsIgnoreCase("No employee found"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean  EditAddressField(String AddressField){
			
		 String	 EditAddressField="";
			
			
				 EditAddressField =getAddressField().getAttribute("value");
				 System.out.print(EditAddressField);
				 
			
			if( EditAddressField.equalsIgnoreCase(AddressField))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 
	 
	 public Select getGridLocation()
		{		
			Select selectgrid=new Select(GridLocation);
			return selectgrid;		
		}
		
	 public boolean  getEmployeeCity(String EmployeeCity){
			
		 String	 getEmployeeCity="";
			
			try 
			{
				getEmployeeCity =getEmployeeCity().getAttribute("value");
				 
			}catch(NoSuchElementException e) {
				
			}
			if( getEmployeeCity.equalsIgnoreCase(EmployeeCity))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean  getEmployeeState(String EmployeeState){
			
		 String	 getEmployeeState="";
			
			try 
			{
				getEmployeeState =getEmployeeState().getAttribute("value");
				 
			}catch(NoSuchElementException e) {
				
			}
			if( getEmployeeState.equalsIgnoreCase(EmployeeState))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 
	 public boolean  getEmpStringloyeePostalCode(String EmpStringloyeePostalCode){
			
		 String	 getEmpStringloyeePostalCode="";
			
			try 
			{
				getEmpStringloyeePostalCode =getEmployeePostalCode().getText();
				 
			}catch(NoSuchElementException e) {
				
			}
			if( getEmpStringloyeePostalCode.equalsIgnoreCase(EmpStringloyeePostalCode))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 
	 public boolean  getEmployeeCountry(String EmployeeCountry){
			
		 String	 getEmployeeCountry="";
			
			try 
			{
				getEmployeeCountry =getEmployeeCountry().getAttribute("value");
				 
			}catch(NoSuchElementException e) {
				
			}
			if( getEmployeeCountry.equalsIgnoreCase(EmployeeCountry))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean  getEmployeePhone(String EmployeePhone){
			
		 String	 getEmployeePhone="";
			
			try 
			{
				getEmployeePhone =getEmployeePhone().getAttribute("value");
				 
			}catch(NoSuchElementException e) {
				
			}
			if( getEmployeePhone.equalsIgnoreCase(EmployeePhone))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 public boolean  getEmployeeemail(String  employeeemail)
	 {
			
		 String	 getEmployeeemail="";
			
			try 
			{
				getEmployeeemail =getEmployeeemail().getAttribute("value");
				 
			}catch(NoSuchElementException e) {
				
			}
			if( getEmployeeemail.equalsIgnoreCase(employeeemail))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	 
	 
	 
	 
	 
	 
	 
}
