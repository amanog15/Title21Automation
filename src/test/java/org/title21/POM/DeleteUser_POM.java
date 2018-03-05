package org.title21.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteUser_POM extends UpdateUser_POM {
	
	public WebDriver driver;
	public WebElement element;
	
	public DeleteUser_POM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//span[text()='Delete User']")
	WebElement deleteUserPopupHeader;
	
	@FindBy(xpath="//p[contains(text(),'Are you sure you want to delete')]")
	WebElement deleteUserPopUpMsg;
	
	@FindBy(xpath="//div[contains(text(),'The main administrator must perform this function')]")
	WebElement adminDeleteWarningMsg;
	
	@FindBy(xpath="//input[@value='Yes']")
	WebElement deleteUserPopUpYesButton;
		
	@FindBy(css=".btn.t21-btn-default.pull-left")
	WebElement deleteUserPopUpNoButton;
	
	@FindBy(xpath="//*[text()='No user found']")
	WebElement noUserfoundresulttext;	
	
	public WebElement adminDeleteWarning_Msg()
	{
		return adminDeleteWarningMsg;
	}
	
	public WebElement getDeleteUserpopupYesButton()
	{
		return deleteUserPopUpYesButton;
	}
	
	public WebElement getDeleteUserpopupNoButton()
	{
		return deleteUserPopUpNoButton;
	}
	
	public WebElement getnoUserfoundMessage()
	{
		return noUserfoundresulttext;
	}
	
	public WebElement deleteUserPopupHeader_Text()
	{
		return deleteUserPopupHeader;
	}
	
	public WebElement deleteUserPopUp_Msg()
	{
		return deleteUserPopUpMsg;
	}
	
	public boolean verifyNoUserFoundText(WebDriver driver){
		String NoUserFoundResultText="";
		try 
		{
			NoUserFoundResultText = getnoUserfoundMessage().getText();
			
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
}
