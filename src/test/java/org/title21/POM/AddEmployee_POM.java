package org.title21.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddEmployee_POM {
	
	public WebDriver driver;
	public WebElement element;
	public AddEmployee_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".dropdown-toggle.t21-nav-bar-dropdown")
	WebElement	administrator;
	
	@FindBy(xpath="//*[@id='Administration']/a")
	WebElement administration;

	@FindBy(css=".selected.t21-padding-5.set-item")
	WebElement employees;

	@FindBy(css=".fa.fa-plus-circle.t21-padding-right-5")
	WebElement addNew;

	@FindBy(css=".form-control#GridLocation")
	WebElement locationTab;

	@FindBy(css=".form-control.t21-placeholder")
	WebElement searchBox;

	@FindBy(xpath="//button[contains(@type,'submit') and contains(@tabindex,'1')]")
	WebElement goButton;

	@FindBy(xpath="//button[contains(@name,'clear')]")
	WebElement crossButton;

	@FindBy(xpath="//a[contains(@href,'#tab1')]")
	WebElement generalTab;

	@FindBy(xpath="//select[contains(@name,'Employee.Location')]")
	WebElement locationEmployee;

	@FindBy(css=".form-control#Employee_FullName")
	WebElement employeeFullName;

	@FindBy(css=".form-control#Employee_EmployeeID")
	WebElement employeeID;

	@FindBy(xpath="//select[contains(@name,'Employee.SupervisorID')]")
	WebElement supervisorDropdown;

	@FindBy(xpath="//select[contains(@name,'Employee.DefaultFirm')]")
	WebElement businessUnit;//=By.xpath("//select[contains(@name,'Employee.DefaultFirm')]");

	@FindBy(xpath="//select[contains(@name,'Employee.Department')]")
	WebElement department;//=By.xpath("//select[contains(@name,'Employee.Department')]");

	@FindBy(css=".form-control#Employee_Address")
	WebElement address;//=By.cssSelector(".form-control#Employee_Address");

	@FindBy(css=".form-control#Employee_City")
	WebElement city;//=By.cssSelector(".form-control#Employee_City");

	@FindBy(css=".form-control#Employee_State")
	WebElement state;//=By.cssSelector(".form-control#Employee_State");

	@FindBy(css=".form-control#Employee_Postal_Code")
	WebElement postal_Code;//=By.cssSelector(".form-control#Employee_Postal_Code");

	@FindBy(css=".form-control#Employee_Country")
	WebElement country;//=By.cssSelector(".form-control#Employee_Country");

	@FindBy(css=".form-control#Employee_Phone")
	WebElement phone;//=By.cssSelector(".form-control#Employee_Phone");

	@FindBy(css=".form-control#Employee_Email")
	WebElement email;//=By.cssSelector(".form-control#Employee_Email");

	@FindBy(xpath="//button[contains(@class,'btn t21-btn-default pull-left')]")
	WebElement cancelButton;//=By.xpath("//button[contains(@class,'btn t21-btn-default pull-left')]");

	@FindBy(xpath="//button[contains(@class,'btn t21-btn-primary t21-ajax-submit-button')]")
	WebElement addButton;//=By.xpath("//button[contains(@class,'btn t21-btn-primary t21-ajax-submit-button')]");

	@FindBy(xpath="//a[contains(@href,'#tab2')]")
	WebElement jobCodesTab;//=By.xpath("//a[contains(@href,'#tab2')]");

	@FindBy(xpath="//a[contains(text(),'Human Resources Clerk')]")
	WebElement jobCodesDropdown;//=By.xpath("//a[contains(text(),'Human Resources Clerk')]");

	@FindBy(xpath="//a[contains(@href,'#tab3')]")
	WebElement otherTab;//=By.xpath("//a[contains(@href,'#tab3')]");

	@FindBy(xpath="//*[@class='collapsible-icon t21-padding-right-5']")
	WebElement trainingLink;//=By.xpath("//*[@class='collapsible-icon t21-padding-right-5']");

	@FindBy(xpath="//*[@id='IsTraining']")
	WebElement uponSaveCheckbox;//=By.xpath("//*[@id='IsTraining']");    

	@FindBy(xpath="//*[@id='collapse-1']/div/div[2]/div/label")
	WebElement employeeSupervisorradioBtn;//=By.xpath("//*[@id='collapse-1']/div/div[2]/div/label");

	@FindBy(xpath="//*[@id='collapse-1']/div/div[3]/div[1]/label")
	WebElement otherSpecificSupervisorradioBtn;//=By.xpath("//*[@id='collapse-1']/div/div[3]/div[1]/label");
    
	@FindBy(xpath="//*[@name='Supervisor']")
	WebElement supervisorOtherTab;//=By.xpath("//*[@name='Supervisor']");

	@FindBy(xpath="//*[@id='IsEmailNotificationTobeSend']")
	WebElement sendEmailNotification;//=By.xpath("//*[@id='IsEmailNotificationTobeSend']");
	
	@FindBy(xpath="//*[@id='Employee_HireDate']")
	WebElement hireDate;//=By.xpath("//*[@id='Employee_HireDate']");

	@FindBy(xpath="//*[@name='Employee.EmploymentType']")
	WebElement employeeType;//=By.xpath("//*[@name='Employee.EmploymentType']");

	@FindBy(xpath="//*[@name='Employee.ForwardItemsTo']")
	WebElement employeeForwardItemsTo;//=By.xpath("//*[@name='Employee.ForwardItemsTo']");

	@FindBy(xpath="//*[@name='Employee.Status']")
	WebElement employeeStatus;//=By.xpath("//*[@name='Employee.Status']");

	@FindBy(xpath="//*[@name='Employee.AltEmployeeID']")
	WebElement altEmployeeID;//=By.xpath("//*[@name='Employee.AltEmployeeID']");

	@FindBy(xpath="//*[@id='CreateUserId']")
	WebElement createUserID;//=By.xpath();     
    
	public WebElement administrator_dropdown()
	{
		//WebElement element=driver.findElement(administrator);
		return administrator;
	}
	public WebElement administration_menu()
	{
		//WebElement element=driver.findElement(administration);
		return administration;
	}
	public WebElement employees_link()
	{
		//WebElement element=driver.findElement(employees);
		return employees;
	}
	
	public WebElement addNewLink()
	{
		//WebElement element=driver.findElement(addNew);
		return addNew;
	}
	
	public Select location_dropdown()
	{
		Select selectObj=new Select(locationTab);
		return selectObj;
	}
	
	public WebElement searchText_Box()
	{
		//WebElement element=driver.findElement(searchBox);
		return searchBox;
	}
	public WebElement go_Button()
	{
		//WebElement element=driver.findElement(goButton);
		return goButton;
	}
	public WebElement employee_DropdownTab()
	{
		//WebElement element=driver.findElement(locationEmployee);
		return locationEmployee;
	}
	public WebElement getEmployeeFullName()
	{
		return employeeFullName;
	}
	
	public WebElement getEmployeeID()
	{
		return employeeID;
	}
	
	public Select getsupervisorDropdown()
	{		
		Select selectObj=new Select(supervisorDropdown);
		return selectObj;		
	}
	
	public Select getbusinessUnitDropdown()
	{
		Select selectObj=new Select(businessUnit);
		return selectObj;		
	}
	
	public Select getDepartmentDropdown()
	{
		Select selectObj=new Select(department);
		return selectObj;			
	}
	
	public WebElement address_textbox()
	{
		return address;
	}
	public WebElement employee_City()
	{
		return city;
	}
	public WebElement employee_State()
	{
		return state;
	}
	public WebElement employee_PostalCode()
	{
		return postal_Code;
	}
	public WebElement employee_Country()
	{
		return country;
	}
	public WebElement employee_Phone()
	{
		return phone;
	}
	public WebElement employee_email()
	{
		return email;
	}
	public WebElement cancel_Btn()
	{
		return cancelButton;
	}
	public WebElement getAddBtn()
	{
		return addButton;
	}
	public WebElement job_Codes()
	{
		return jobCodesTab;
	}
	public WebElement jobCode_Dropdown1()
	{
		//WebElement element=driver.findElement(jobCodesDropdown);
		return jobCodesDropdown;
	}
	public WebElement other_Tab()
	{
		//WebElement element=driver.findElement(otherTab);
		return otherTab;
	}
	public WebElement trainingLink_Tab()
	{
		//WebElement element=driver.findElement(trainingLink);
		return trainingLink;
	}
	public WebElement uponSave_Checkbox()
	{
		//WebElement element=driver.findElement(uponSaveCheckbox);
		return uponSaveCheckbox;
	}
	public WebElement employeeSupervisor_RadioBtn()
	{
		//WebElement element=driver.findElement(employeeSupervisorradioBtn);
		return employeeSupervisorradioBtn;
	}
	public WebElement otherSpecificSupervisor_RadioBtn()
	{
		//WebElement element=driver.findElement(otherSpecificSupervisorradioBtn);
		return otherSpecificSupervisorradioBtn;
	}
	
	
	public WebElement hire_Date()
	{
		//WebElement element=driver.findElement(hireDate);
		return hireDate;
	}
	public WebElement employee_type()
	{
		//WebElement element=driver.findElement(employeeType);
		return employeeType;
	}
	public WebElement employee_ForwardItemsTo()
	{
		//WebElement element=driver.findElement(employeeForwardItemsTo);
		return employeeForwardItemsTo;
	}
	public WebElement employee_Status()
	{
		//WebElement element=driver.findElement(employeeStatus);
		return employeeStatus;
	}
	public WebElement alt_Employee()
	{
		////WebElement element=driver.findElement(altEmployeeID);
		return altEmployeeID;
	}
	public WebElement create_UserID()
	{
		//WebElement element=driver.findElement(createUserID);
		return createUserID;
	}
	public WebElement sendEmail_Notification()
	{
		//WebElement element=driver.findElement(createUserID);
		return sendEmailNotification;
	}
}
