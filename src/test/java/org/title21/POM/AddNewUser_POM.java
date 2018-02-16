package org.title21.POM;

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
	
	//*[@title='Select all']
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
    
  //*[@id=\"popover530011\"]/div[2]//ol[1]/li[1]
    @FindBy(xpath="//*[contains(@class,'t21-pswd-criteria')][1]")
   	WebElement tenCharacters;
    
  //*[@id=\"popover530011\"]/div[2]//ol[1]/li[2]
    @FindBy(xpath="//*[contains(@class,'t21-pswd-criteria')][2]")
   	WebElement strengthLeastOne;
    
  //*[@id=\"popover530011\"]/div[2]//ol[1]/li[3]
    @FindBy(xpath="//*[contains(@class,'t21-pswd-criteria')][3]")
    WebElement containUserID;
    
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
    
    public Select userFullName_Dopdown()
   	{
    	Select userFullNameObj = new Select(userFullName);
   		return userFullNameObj;
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
}



