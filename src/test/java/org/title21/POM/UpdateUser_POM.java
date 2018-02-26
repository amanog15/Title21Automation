package org.title21.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UpdateUser_POM extends AddNewUser_POM {
	
	public WebDriver driver;
	public WebElement element;
	
	public UpdateUser_POM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@name='GridLocation']")
	WebElement locationDrodpdownforFilter;
		
	@FindBy(css=".t21-margin-top-0>label")
	WebElement editPassword;
	
	@FindBy(xpath="//button[text()='Confirm']")
	WebElement UpdateUserConfirmButton;
	
	@FindBy(xpath="//div[contains(text(),'updated successfully')]")
	WebElement userUpdatedSuccessfully;
	
	//a[text()='Password']
	@FindBy(linkText="Password")
	WebElement passwordTab;
	
	public WebElement password_Tab()
	{
		return passwordTab;
	}
	
	public WebElement userUpdatedSuccessfully_Text()
	{
		return userUpdatedSuccessfully;
	}
	
	public WebElement UpdateUserConfirm_Button()
	{
		return UpdateUserConfirmButton;
	}
	
	public WebElement editPassword_checkBox() 
	{
		return editPassword;
	}
	
	public Select getLocationforFilter(){
	
		Select selectObj=new Select(locationDrodpdownforFilter);
		return selectObj;
	}
	
}
