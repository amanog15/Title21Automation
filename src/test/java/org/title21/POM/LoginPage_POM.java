package org.title21.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.title21.utility.BaseClass;
import org.title21.validation.entities.ErrorMessages;

public class LoginPage_POM extends BaseClass
{
	public BaseClass baseClassObj=new BaseClass();
	public WebDriver driver;
	public WebElement element;
		
	@FindBy(css=".form-control#UserId")
	WebElement username;
	
	@FindBy (css=".form-control#Password")
	WebElement password;
	
	@FindBy (css=".btn.t21-btn-primary")
	WebElement loginButton;
	
	@FindBy (xpath=".//*[@id='login_panel']/form/div[3]/span")
	WebElement passwordErrorMessage;
	
	@FindBy (css=".text-danger#UserId-error")
	WebElement useridValidationMessage;	
	
	@FindBy (css=".text-danger#Password-error")
	WebElement passwordValidationMessage;	
	
	public LoginPage_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getUsername()
	{
		return username;
	}
	
	public WebElement getpassword()
	{
		return password;
	}
	
	public WebElement getLogin_button()
	{
		return loginButton;
	}
	
	public WebElement getPasswordErrorMessage()
	{
		return passwordErrorMessage;
	}
	
	public WebElement getUserIDValidationMessage()
	{
		return useridValidationMessage;
	}
	
	public WebElement getPasswordValidationMessage()
	{
		return passwordValidationMessage;
	}
		
	public boolean verifyPasswordValidationMessage(){
		
		element=getPasswordValidationMessage();
		String errorMessage = element.getText();		
		if(errorMessage.contains(ErrorMessages.messagewithoutPassword))
		{
			baseClassObj.captureScreenShot(driver,"messagewithoutPassword");
			return true;
		}
		else
		{			
			return false;
		}
		
	}
	
	public boolean verifyUserIDValidationMessage(){
		
		element=getUserIDValidationMessage();
		String errorMessage = element.getText();		
		if(errorMessage.contains(ErrorMessages.messagewithoutUsername))
		{
			baseClassObj.captureScreenShot(driver,"messagewithoutUsername");
			return true;
		}
		else
		{	
			return false;
		}		
		
	}
	
	public boolean verifyPasswordErrorMessage()
	{
		element=getPasswordErrorMessage();
		String errorMessage = element.getText();		
		if(errorMessage.contains(ErrorMessages.passworderrormessages))
		{
			baseClassObj.captureScreenShot(driver,"passwordFieldErrorMessage");
			return true;
		}
		else
		{			
			return false;
		}
				
	}

	public void loginFunction(){
		
		getUsername().sendKeys(adminUsername);
		getLogin_button().click();
		getpassword().sendKeys(adminPassword);
		getLogin_button().click();
		sleep(2);
	}

}
