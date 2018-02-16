package org.title21.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomaticallyLoggedOut_POM
{
	
public WebDriver driver;
public WebElement element;

@FindBy(xpath="//h4[text()='Inactivity Warning']")
 WebElement inactivityWarning;

@FindBy(css=".modal-body>p")
WebElement inactivityWarningMsg;

@FindBy(css=".btn.btn-lg.t21-btn-primary")
WebElement continueButton;

@FindBy(css=".btn.btn-lg.t21-btn-default")
WebElement logOutButton;

@FindBy(xpath="//h1[text()='Timeout']")
 WebElement timeoutLable;

@FindBy(xpath="//p[text()='For security reasons, you have been logged out.']")
WebElement securityReasonsText;

@FindBy(xpath="//a[text()='Click here']")
WebElement clickHereToLogin;


public AutomaticallyLoggedOut_POM(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

 public WebElement inactivityWarning()
 {
	 return inactivityWarning;
 }
 
 public WebElement inactivityWarningMsg()
 {
	 return inactivityWarningMsg;
 }
 
 public WebElement continueButton()
 {
	 return continueButton;
 }
 
 public WebElement logOutButton()
 {
	 return logOutButton;
 }
 
 public WebElement timeoutLable()
 {
	 return timeoutLable;
 }
 public WebElement securityReasonsText()
 {
	 return securityReasonsText; 
 }
 
 public WebElement clickHereToLogin()
 {
	 return clickHereToLogin; 
 }
 
 /*
	 * This method verify text on Administration 
	 * @param WebDriver obj
	 */
	
	public boolean verifyInactivityWarningPopUp(){
		
		String inactivityWarningHeader = inactivityWarning().getText();
		
		if(inactivityWarningHeader.equalsIgnoreCase("Inactivity Warning"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyInactivityWarningMsg(){
		
		String inactivityWarningMsg = inactivityWarningMsg().getText();
		
		if(inactivityWarningMsg.contains("automatically logged"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyTimeoutScreen(){
		
		String timeoutText = timeoutLable().getText();
		
		if(timeoutText.equalsIgnoreCase("Timeout"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean verifySecurityReasonsText(){
	
	String securityReasonsText = securityReasonsText().getText();
	
	if(securityReasonsText.equalsIgnoreCase("For security reasons, you have been logged out."))
	{
		return true;
	}
	else
	{
		return false;
	}
}
 
}
