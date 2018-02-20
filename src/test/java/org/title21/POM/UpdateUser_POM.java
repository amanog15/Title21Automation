package org.title21.POM;

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
	
	public Select getLocationforFilter(){
	
		Select selectObj=new Select(locationDrodpdownforFilter);
		return selectObj;
	}
}
