package org.title21.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddNewUser_POM extends AdminCreateDeleteGroups_POM {

	public WebDriver driver;
	public WebElement element;
	public AddNewUser_POM(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@id='set-2']") 
	WebElement users;  
	
	@FindBy(xpath="//a[contains(@href,'AddUser')]")
	WebElement addNewUser;
	
	@FindBy(xpath="//*[text()='Add New User']")
	WebElement AddNewUserPopUpHeader;
	
	////a[text()='General']
	@FindBy(xpath="//a[contains(@href,'tab1')]")
	WebElement general;
	
	@FindBy(xpath="//*[@name='Location']")
	WebElement locationUser;
	
	@FindBy(css="#User_FullName")
	WebElement userFullName;
	
	@FindBy(css="#User_UserName")
	WebElement userName;
	
	@FindBy(xpath="//*[@id='dualListControl']/div/div[1]/input")
	WebElement availableFilter;
	
	@FindBy(css=".btn.moveall")
	WebElement availableButton;
	
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
	WebElement cancelButton;
	
    @FindBy(xpath="//a[contains(@href,'tab2')]")
	WebElement passwordTab;
    
    @FindBy(css="#CheckAuthenticationType")
	WebElement checkAuthenticationType;
    
    @FindBy(css="#new-password-input")
	WebElement newPassword;
    
    @FindBy(css="#new-password-input-button")
	WebElement checkStrength;
    
    @FindBy(xpath="//*[@id='User.ConfirmPassword']")
	WebElement confirmPassword;
    
    @FindBy(xpath="//input[contains(@name,'User.PasswordExpiresEvery')]")
	WebElement passwordExpiresEvery;
    
    @FindBy(xpath="//*[text()='Add']")
	WebElement passwordAddTab;
    
    @FindBy(css=" .btn.t21-btn-default.pull-left")
	WebElement passwordCancelTab;
    
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
    
    @FindBy(xpath="//*[text()='Message']")
    WebElement confirmHeaderMsg;
    
    @FindBy(xpath="//button[text()='Close']")
    WebElement confirmCloseButton;
    
    @FindBy(css=".popover-title")
    WebElement passwordMust;
    
    @FindBy(css=".fa.fa-times")
    WebElement passwordMustClose;
    
    @FindBy(xpath="//*[contains(text(),'Full Name')]")
    WebElement userTableVerifications;
    
    @FindBy(xpath="//*[contains(@class,'t21-pswd-criteria')][1]")
   	WebElement tenCharacters;
    
    @FindBy(xpath="//*[contains(@class,'t21-pswd-criteria')][2]")
   	WebElement strengthLeastOne;
    
    @FindBy(xpath="//*[contains(@class,'t21-pswd-criteria')][3]")
    WebElement containUserID;
    
    @FindBy(xpath="//*[text()='No user found']")
    WebElement noUserFoundResultText;
    
    @FindBy(xpath="//*[text()='Location is required']")
    WebElement locationRequired;
    
    @FindBy(xpath="//span[text()='Full Name is required']")
    WebElement fullNameRequired;
    
    @FindBy(xpath="//span[text()='User Name is required']")
    WebElement userNameRequired;
    
    @FindBy(xpath="//span[text()='Please select at least one group']")
    WebElement pleaseSelectOneGroup;
    
    @FindBy(xpath="//span[text()='Password is required']")
    WebElement passwordRequired;
    
    @FindBy(xpath="//span[text()='Confirm Password is required']")
    WebElement confirmPasswordRequired;
    
    @FindBy(xpath="//span[text()='Password does not match']")
    WebElement passwordDoesNotMatch;
    
    @FindBy(css=".t21-js-user-message-text")
    WebElement empAssignedMsg;
    
    @FindBy(xpath="//button[text()='�']")
    WebElement addNewUserCloseButton;
    
    @FindBy(xpath="//span[text()='Password cannot contain UserId']")
    WebElement passwordCannotContainUserId;
    
    public WebElement passwordCannotContainUserId_ErrorMsg()
    {
    	return passwordCannotContainUserId;
    }
    
    public WebElement addNewUserClose_Button()
    {
    	return addNewUserCloseButton;
    }
    
    public WebElement empAssigned_Msg()
    {
    	return empAssignedMsg;
    }
    
    public WebElement passwordDoesNotMatch_ErrorMsg() 
    {
    	return passwordDoesNotMatch;
    }
    
    public WebElement confirmPasswordRequired_ErrorMsg()
    {
    	return confirmPasswordRequired;
    }
    
    public WebElement passwordRequired_ErrorMsg()
    {
		return passwordRequired;
    }
    
    public WebElement pleaseSelectOneGroup_ErrorMsg()
    {
    	return pleaseSelectOneGroup;
    }
    
    public WebElement userNameRequired_ErrorMsg()
    {
    	return userNameRequired;
    }
    
    public WebElement fullNameRequired_ErrorMsg()
    {
    	return fullNameRequired;
    }
    
    public WebElement locationRequired_ErrorMsg()
    {
    	return locationRequired;
    }
    
    public WebElement userTable_Verifications()
    {
    	return userTableVerifications;
    }
    
    @FindBy(css="#GridLocation")
	WebElement userLocationDropDown;
    
    public Select getUserLocation_DropDown(){
		Select selectObj = new Select(userLocationDropDown);
		return selectObj;
	}
    
    public WebElement containUserID_Msg()
    {
    	return containUserID;
    }
    
    public WebElement strengthLeastOne_Msg()
    {
    	return strengthLeastOne;
    }

    public WebElement tenCharacters_Msg()
    {
    	return tenCharacters;
    }
    
    public WebElement passwordMust_Close() 
    {
    	return passwordMustClose;
    }
    
    public WebElement passwordMust_PopUp()
    {
    	return passwordMust;
    }
    
    public WebElement confirmHeader_Msg()
    {
    	return confirmHeaderMsg;
    }
    
    public WebElement confirmClose_Button()
    {
    	return confirmCloseButton;
    }
    
    public WebElement user_link()
	{
		return users;
	}
    
    public WebElement addNew_User()
	{
		return addNewUser;
	}
    
    public WebElement AddNewUserPopUpHeader()
    {
    	return AddNewUserPopUpHeader;
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
    
    public WebElement locationDropDown_click()
    {
		return locationUser;
    }
    
    public Select userFullName_Dropdown()
   	{
    	Select userFullNameObj = new Select(userFullName);
   		return userFullNameObj;
   	}
    
    public WebElement userFullNameDropDown_click()
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
    
    public WebElement add_GeneralButton()
   	{
   		return addButton;
   	}
    
    public WebElement cancel_GeneralButton()
   	{
   		return cancelButton;
   	}
    
    public WebElement password_tab()
   	{
   		return passwordTab;
   	}
    
    public Select check_AuthenticationType()
   	{
    	Select checkAuthenticationTypeObj = new Select(checkAuthenticationType);
   		 	return checkAuthenticationTypeObj;
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
    
    public WebElement noUserFoundResultText()
    {
    	return noUserFoundResultText;
    }
    
    public boolean verifyAddNewUserPopUpHeader(WebDriver driver){
		
		String alertHeaderText = AddNewUserPopUpHeader().getText();
		
		if(alertHeaderText.equalsIgnoreCase("Add New User"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    
    public boolean verifyNoUserFoundText(WebDriver driver){
		
		String NoUserFoundResultText="";
		
		try 
		{
			NoUserFoundResultText = noUserFoundResultText().getText();
			
		}catch(NoSuchElementException e) {
			
		}
		if(NoUserFoundResultText.equalsIgnoreCase("No user found"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    
    public boolean alertEMPAssignedMsg(WebDriver driver){
		
  		String alertEMPAssignedMsgText="";
  		
  		try 
  		{
  			alertEMPAssignedMsgText = empAssigned_Msg().getText();
  			
  		}catch(NoSuchElementException e) {
  			
  		}
  		if(alertEMPAssignedMsgText.contains("All employees (in the Employee List) have already been assigned a user ID"))
  		{
  			return true;
  		}
  		else
  		{
  			return false;
  		}
  	}
}



