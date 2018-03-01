package org.title21.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DeleteUser_POM extends AddNewUser_POM {
	
	public WebDriver driver;
	public WebElement element;
	
	public DeleteUser_POM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@name='GridLocation']")
	WebElement locationDrodpdownforFilter;
		
	@FindBy(xpath="//span[text()='Delete User']")
	WebElement deleteUserPopupHeader;
	
	@FindBy(xpath="//p[contains(text(),'Are you sure you want to delete')]")
	WebElement deleteUserPopUpMsg;
	
	public WebElement deleteUserPopupHeader_Text()
	{
		return deleteUserPopupHeader;
	}
	
	public WebElement deleteUserPopUp_Msg()
	{
		return deleteUserPopUpMsg;
	}
	
	
	
	public Select getLocationforFilter(){
	
		Select selectObj=new Select(locationDrodpdownforFilter);
		return selectObj;
	}
	
}
