package org.title21.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

public class AdministrationPage_POM
{
	
public WebDriver driver;
public WebElement element;

@FindBy(css=".dropdown-toggle.t21-nav-bar-dropdown")
 WebElement administratordropdown;

@FindBy(xpath="//a[contains(@href, 'Administration')]")
 WebElement administrationlink;

@FindBy(css=".t21-grid-header-style")
WebElement administrationtext;
 
public AdministrationPage_POM(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

 public WebElement administratorDropDown()
 {
	 return administratordropdown;
	// return element;
 }
 public WebElement administrationLink()
 {
	 return administrationlink;
	 //return element;
 }
 public WebElement administrationText()
 {
	 return administrationtext; 
	 //return element;
 }
 
 /*
	 * 
	 * This method verify text on Administration
	 * 
	 */
	
	public boolean verifyAdministrationPagePrescence(){
		
		String administrationText = administrationText().getText();
		
		if(administrationText.equalsIgnoreCase("Administration"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}	
 
}


