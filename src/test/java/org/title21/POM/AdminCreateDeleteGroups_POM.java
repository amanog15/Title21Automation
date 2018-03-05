package org.title21.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.title21.utility.BaseClass;
import org.title21.validation.entities.ErrorMessages;

public class AdminCreateDeleteGroups_POM 
{
public WebDriver driver;
public WebElement element;
BaseClass baseClassObj = new BaseClass();

public AdminCreateDeleteGroups_POM(WebDriver driver) {

	this.driver = driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="//a[contains(@href,'GetGroupList')]")
WebElement groupslink;

@FindBy(xpath="//a[contains(@href,'AddUserGroup')]")
WebElement addnewlink;

@FindBy(xpath="//*[text()='Add Group']")
WebElement addgroupheaderlable;

@FindBy(xpath="//select[@class='form-control']")
WebElement grouplocationdropdownclick;

@FindBy(xpath="//select[@class='form-control valid']/option")
WebElement grouplocationdropdownvalue;

@FindBy(css="#Group_Groups")
WebElement addgrouptextbox;

@FindBy(css=".btn.t21-btn-primary.t21-ajax-submit-button")
WebElement addgroupaddbutton;

@FindBy(css=".btn.t21-btn-default.pull-left")
WebElement addgroupcancelbutton;
             
@FindBy(css=".form-control.t21-placeholder")
WebElement groupfilterresult;

@FindBy(xpath="//button[@type='submit'][@tabindex='1']")
WebElement groupfilterresutgobutton;

@FindBy(xpath="//*[text()='No group found']")
WebElement nogroupfoundresulttext;

@FindBy(xpath="//tbody[@class='t21-js-clickable-rows']/tr/td[1]")
WebElement listOfGroups;

@FindBy(xpath="//button[text()='Close']")
WebElement alertCloseButton;

@FindBy(xpath="//h4[text()='Message']")
WebElement alertMsgPopUp;

@FindBy(xpath="This name already exists. Please enter another name.")
WebElement alreadyGroupCreatedErrorMsg;

@FindBy(xpath="//input[@value='Yes']")
WebElement deleteGroupPopUpYesButton;

@FindBy(xpath="//button[text()='Close']")
WebElement ConfirmPopUpCloseButton;

@FindBy(css=".modal-title>span")
WebElement deleteGroupPopUpHeaderText;

@FindBy(xpath="//*[text()='Message']")
WebElement deleteGroupConfirmPopUpHeaderText;
 
 public Select groupLocationDropDownClick(){
	Select selectObj = new Select(grouplocationdropdownclick);
	return selectObj;
 }
 public WebElement groupsTab()
 {
	 return groupslink;
 }
 public WebElement groupAddNewLink()
 {
	 return addnewlink;
 }
 public WebElement addGroupHeaderlable()
 {
	 return addgroupheaderlable;
 }
 
 public WebElement groupLocationDropDownValue()
 {
	 return grouplocationdropdownvalue;
 }
 
 public WebElement addGroupTextBox()
 {
	 return addgrouptextbox;
 }
 
 public WebElement addGroupAddButton()
 {
	 return addgroupaddbutton;
 }
 
 public WebElement addGroupCancelButton()
 {
	 return addgroupcancelbutton;
 }
 
 public WebElement groupFilterResult()
 {
	 return groupfilterresult;
 }
 
 public WebElement groupFilterResutGoButton()
 {
	 return groupfilterresutgobutton;
 }
 
 public WebElement noGroupFoundResultText(){
	 return nogroupfoundresulttext;
 }
 
 public WebElement listOfGroups(){
	 return listOfGroups;
 }
 
 public WebElement alertCloseButton() {
	 
	 return alertCloseButton;
 }
 
 public WebElement alertMsgPopUp() {
	 
	 return alertMsgPopUp;
 }
 
 public WebElement alreadyGroupCreatedErrorMsg() {
	 
	 return alreadyGroupCreatedErrorMsg;
 }
 
 public WebElement deleteGroupPopUpYesButton()
 {
	 return deleteGroupPopUpYesButton;
 }
 
 public WebElement ConfirmPopUpCloseButton()
 {
	 return ConfirmPopUpCloseButton;
 }
 
 public WebElement deleteGroupPopUpHeaderText()
 {
	 return deleteGroupPopUpHeaderText;
 }
 
 public WebElement deleteGroupConfirmPopUpHeaderText()
 {
	 return deleteGroupConfirmPopUpHeaderText;
 }
 
	public boolean verifyAddGroupPopUp(WebDriver driver){
		String AddGroupPopUpHeaderText = addGroupHeaderlable().getText();
		if(AddGroupPopUpHeaderText.equalsIgnoreCase("Add Group"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyNoGroupFoundText(WebDriver driver){
		String NoGroupFoundResultText="";
		try 
		{
			NoGroupFoundResultText = noGroupFoundResultText().getText();
			
		}catch(NoSuchElementException e) {
			
		}
		if(NoGroupFoundResultText.equalsIgnoreCase("No group found"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean verifyAlerPopUp(WebDriver driver){
		
		String alertHeaderText = alertMsgPopUp().getText();
		
		if(alertHeaderText.equalsIgnoreCase("Message"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyalreadyGroupCreatedErrorMsg(WebDriver driver){
		
		baseClassObj.waitForPageToLoad(driver, 10);
		String errorMessage = alreadyGroupCreatedErrorMsg().getText();		
		if(errorMessage.contains(ErrorMessages.groupnamealreadyexist))
		{
			return true;
		}
		else
		{	
			return false;
		}		
	}
	
	public boolean verifyalreadyGroupCreatedErrorMsg1(WebDriver driver){
		
		String errorMessage="";
		try 
		{
			baseClassObj.waitForPageToLoad(driver, 10);
			errorMessage = alreadyGroupCreatedErrorMsg().getText();
			
		}catch(NoSuchElementException e) {
			
		}
		if(errorMessage.contains(ErrorMessages.groupnamealreadyexist))
		{
			return true;
		}
		else
		{	
			return false;
		}		
	}
	
	public boolean verifyDeleteGroupPopUp(){
		
		String DeleteGroupHeaderText = deleteGroupPopUpHeaderText().getText();
		
		if(DeleteGroupHeaderText.equalsIgnoreCase("Delete Group"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyDeleteGroupcConfirmPopUpText(){
		
		String DeleteGroupHeaderText = deleteGroupConfirmPopUpHeaderText.getText();
		
		if(DeleteGroupHeaderText.equalsIgnoreCase("Message"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
