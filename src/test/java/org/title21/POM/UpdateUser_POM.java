package org.title21.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.title21.utility.BaseClass;

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

	@FindBy(xpath="//a[text()='Password']")
	WebElement passwordTab;

	@FindBy(css=".btn-success")
	WebElement iAgreeButton;

	@FindBy(xpath="//*[@id='bootstrap-duallistbox-nonselected-list_dualListItemList[]']/option[1]")
	WebElement AvailableFirstElement;

	@FindBy(xpath=".//*[@id='t21-workarea']/div/div/div[2]/div[2]/div/div[3]/div/div/form/div/input")
	WebElement filterTextBox;

	@FindBy(xpath=".//*[@id='t21-workarea']/div/div/div[2]/div[2]/div/div[3]/div/div/form/div/span[1]/button")
	WebElement goFilterButton;

	@FindBy(xpath=".//*[@id='t21-workarea']/div/div/div[2]/div[2]/div/table/tbody/tr/td[6]/a[2]/span")
	WebElement userEditButton;

	@FindBy(xpath=".//*[@id='default-modal']/div/form/div/div[2]/div[1]/ul/li[2]/a")
	WebElement editBoxPasswordTab;

	@FindBy(id="User_Lockout")
	WebElement editBoxLockCheckbox;

	@FindBy(xpath=".//*[@id='default-modal']/div/form/div/div[3]/button[2]")
	WebElement editBoxConfirmButton;

	@FindBy(xpath=".//*[@id='dialog-form']/div/div/div[3]/button")
	WebElement editBoxCloseButton;

	public WebElement AvailableFirst_Element()
	{
		return AvailableFirstElement;
	}

	public WebElement iAgree_Button()
	{
		return iAgreeButton;
	}

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

	public WebElement filterText()
	{
		return filterTextBox;
	}

	public WebElement goButton()
	{
		return goFilterButton;
	}

	public WebElement editButton()
	{
		return userEditButton;
	}

	public WebElement passwordTab()
	{
		return editBoxPasswordTab;
	}

	public WebElement lockCheckbox()
	{
		return editBoxLockCheckbox;
	}

	public WebElement confirmButton()
	{
		return editBoxConfirmButton;
	}

	public WebElement closeButton()
	{
		return editBoxCloseButton;
	}

	public Select getLocationforFilter(){

		Select selectObj=new Select(locationDrodpdownforFilter);
		return selectObj;
	}
	
	public void unlockUser(String username)
	{
		filterText().sendKeys(username);
		BaseClass.sleep(3);
		goButton().click();
		BaseClass.sleep(3);
		editButton().click();
		BaseClass.sleep(3);
		passwordTab().click();
		BaseClass.sleep(3);
		lockCheckbox().click();
		BaseClass.sleep(3);
		confirmButton().click();
		BaseClass.sleep(3);
		closeButton().click();
		BaseClass.sleep(3);
	}

	public boolean verifyIAgreePresence()
	{	
		List<WebElement> errormessage = driver.findElements(By.cssSelector(".btn-success"));
		int size = errormessage.size();
		if(size != 0)
		{
			return true;
		}
		else
		{			
			return false;
		}

	}
}
