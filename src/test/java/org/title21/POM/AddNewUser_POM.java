package org.title21.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddNewUser_POM {

	public WebDriver driver;
	public WebElement element;
	public AddNewUser_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".dropdown-toggle.t21-nav-bar-dropdown")
	WebElement	administrator;
	
	@FindBy(xpath="//*[@id='Administration']/a") 
	WebElement administration;
	
	@FindBy(xpath="//*[@id='set-2']/div") 
	WebElement users;  
	
	@FindBy(css=".fa.fa-plus-circle.t21-padding-right-5")
	WebElement	addNewuser;
	
	@FindBy(xpath="//a[contains(@href,'#tab1')]")
	WebElement general;
	
	@FindBy(xpath="//*[@id='tab1']/div[2]/div/select")
	WebElement locationUser;
	
	@FindBy(css=".form-control#User_FullName")
	WebElement	userFullName;
	
	@FindBy(css=".form-control#User_UserName")
	WebElement	userName;
	
	@FindBy(xpath="//*[@id='dualListControl']/div/div[1]/input")
	WebElement availableFilter;
	
	@FindBy(css=".btn.moveall.btn-default")
	WebElement	availableButton;
	
	@FindBy(xpath="//*[@id='bootstrap-duallistbox-nonselected-list_dualListItemList[]']")
	WebElement availableList;
	 
	@FindBy(xpath="//*[@id='dualListControl']/div/div[2]/input")
	WebElement selectedFilter;
	
	@FindBy(xpath="//button[contains(@title,'Remove all')]")
	WebElement selectedButton;
	
	@FindBy(xpath="//*[@id='bootstrap-duallistbox-selected-list_dualListItemList[]']")
	WebElement SelectedList;
	
	
	@FindBy(xpath="//*[@id='default-modal']/div/form/div/div[3]/button[2]")
	WebElement addButton;
	
    @FindBy(css=".btn.t21-btn-default.pull-left")
	WebElement	cancelButton;
	
    @FindBy(xpath="//a[contains(@href,'#tab2')]")
	WebElement passwordTab;
	
    
    @FindBy(css=".form-control#CheckAuthenticationType")
	WebElement	checkAuthenticationType;
    
    @FindBy(css=".form-control#new-password-input")
	WebElement	newPassword;
    
    @FindBy(xpath="//button[contains(@id,'new-password-input-button')]")
	WebElement checkStrength;
    
    @FindBy(xpath="//input[contains(@name,'User.ConfirmPassword')]")
	WebElement confirmPassword;
    
    @FindBy(xpath="//input[contains(@name,'User.PasswordExpiresEvery')]")
	WebElement passwordExpiresEvery;
    
    
    @FindBy(css=".btn.t21-btn-primary.t21-ajax-submit-button")
	WebElement	passwordAddTab;
    
    @FindBy(css=" .btn.t21-btn-default.pull-left")
	WebElement	passwordCancelTab;
    
    
    @FindBy(xpath="input[contains(@id,'EditPassword')]")
	WebElement editPassword;
  
    @FindBy(xpath="//*[@id='User.ForcePswCheck']")
	WebElement passwordExpiresCheck;
    
    @FindBy(xpath="*[@id='User.ForcePswChange']")
   	WebElement passwordForceChange;
    
    @FindBy(xpath="input[contains(@id,'User_Lockout')]")
   	WebElement userAccountLocked;
    
    @FindBy(xpath="input[contains(@id,'User_Disabled')]")
   	WebElement userAccountDisabled;
    

    public WebElement administrator_dropdown()
	{
		
		return administrator;
	}
    
    public WebElement administration_link()
	{
		
		return administration;
	}
    
    
    public WebElement user_link()
	{
		
		return users;
	}
    
    
    public WebElement addNew_User()
	{
		
		return addNewuser;
	}
    
    public WebElement general_tab()
	{
		
		return general;
	}
    
    public Select location_Dropdown()
	{
    	
    		Select locationUserObj = new Select(locationUser);
    		return locationUserObj;		
   	}
    
    public WebElement userFullName_Dopdown()
   	{
   		
   		return userFullName;
   	}
    
    public WebElement username_textbox()
	{
		
		return userName;
	}
    
    
    public WebElement available_Filter()
   	{
   		
   		return availableFilter;
   	}
    
    public WebElement available_Button()
   	{
   		
   		return availableButton;
   	}
    
    public WebElement available_List()
   	{
   		
   		return availableList;
   	}
    
    public WebElement selected_Filter()
   	{
   		
   		return selectedFilter;
   	}
    
    
    
    public WebElement selected_Button()
   	{
   		
   		return selectedButton;
   	}
    
    public WebElement selected_List()
   	{
   		
   		return SelectedList;
   	}
    
    
    public WebElement password_tab()
   	{
   		
   		return passwordTab;
   	}
    
    public WebElement check_AuthenticationType()
   	{
   		
   		return checkAuthenticationType;
   	}
    
    
    public WebElement new_PasswordInput()
   	{
   		
   		return newPassword;
   	}
    
    public WebElement confirm_PasswordInput()
   	{
   		
   		return confirmPassword;
   	}
    
    public WebElement check_StrengthButton()
   	{
   		
   		return checkStrength;
   	}
    
    public WebElement password_ExpiresEveryInput()
   	{
   		
   		return passwordExpiresEvery;
   	}
    
    public WebElement password_ExpiresCheck()
   	{
   		
   		return passwordExpiresCheck;
   	}
    
    public WebElement password_ForceChange()
   	{
   		
   		return passwordForceChange;
   	}
    
    public WebElement user_AccountLocked()
   	{
   		
   		return userAccountLocked;
   	}
    
    
    public WebElement user_AccountDisabled()
   	{
   		
   		return userAccountDisabled;
   	}
    
    public WebElement password_AddTab()
   	{
   		
   		return passwordAddTab;
   	}
    
    public WebElement password_CancelTab()
   	{
   		
   		return passwordCancelTab;
   	}
    
    
      
}



