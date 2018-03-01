package org.title21.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DeleteUser_POM extends UpdateUser_POM {
	
	public WebDriver driver;
	public WebElement element;
	
	public DeleteUser_POM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@value='Yes']")
	WebElement deleteUserPopUpYesButton;
		
	@FindBy(css=".btn.t21-btn-default.pull-left")
	WebElement deleteUserPopUpNoButton;
	
	@FindBy(xpath="//*[text()='No user found']")
	WebElement noUserfoundresulttext;	
	
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
		
}
