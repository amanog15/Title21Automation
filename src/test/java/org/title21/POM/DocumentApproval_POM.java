package org.title21.POM;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.title21.validation.entities.ErrorMessages;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DocumentApproval_POM extends CreateDocument_POM {

	public WebDriver driver;
	public WebElement element;
	static Logger log = Logger.getLogger(DocumentApproval_POM.class);
	
	public DocumentApproval_POM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//a[contains(@href,'DocumentApproval')]")
	WebElement documentApprovalTab;
	
	@FindBy(xpath="//a[contains(text(),'On')]")
	WebElement editModeOn;
	
	@FindBy(xpath="//a[contains(text(),'Off')]")
	WebElement editModeOff;
	
	@FindBy(xpath="//a[contains(@href,'AddApproverModal')]")
	WebElement addApproverLink;
		
	@FindBy(xpath="//a[contains(@href,'AddRouteModal')]")
	WebElement signatureRouteLink;
	
	@FindBy(xpath="//select[contains(@data-t21-form-action,'/GetApproverInfo')]")
	WebElement roleDropdown;
	
	@FindBy(xpath="//select[contains(@data-t21-form-action,'/SelectLocation')]")
	WebElement locationDropdowninAddApprover;
	
	@FindBy(xpath="//select[contains(@data-t21-form-action,'/SelectDocRouteApprovers')]")
	WebElement roleNameinAddSignature;
		
	@FindBy(xpath="//select[contains(@name,'SelectedMember')]")
	WebElement  nameinAddApprover;
	
	@FindBy(xpath="//select[contains(@name,'SelectedSequence')]")
	WebElement sequenceinAddApprover;
	
	@FindBy(xpath="//select[contains(@name,'SelectedAllottedDays')]")
	WebElement allottedDaysinAddApprover;
	
	@FindBy(xpath="//table[contains(@class,'t21-table-custom')]")
	WebElement tableRowsinCustomTable;
	
	public WebElement gettableCells(int i,int j){
		
		return tableRowsinCustomTable.findElement(By.xpath("/tbody/tr["+i+"]/[td"+j+"]"));
	}
	
	public WebElement getallottedDaysinAddApprover(){
		
		return allottedDaysinAddApprover;
	}
	
	public WebElement getSequenceinAddApprover(){
		
		return sequenceinAddApprover;
	}
	
	public WebElement getnameinAddApprover(){
		
		return nameinAddApprover;
	}
	
	public WebElement getDocumentApprovaltab(){
		
		return documentApprovalTab;
	}
	
	public WebElement getEditModeOn(){
		
		return editModeOn;
	}
	
	public WebElement getEditModeOff(){
		
		return editModeOff;
	}
	
	public WebElement getAddApproverLink(){
		
		return addApproverLink;
	}
	
	public WebElement getRoleDropdown(){
		
		return roleDropdown;
	}
	
	public WebElement getLocationDropdown(){
		
		return locationDropdowninAddApprover;
	}
	
}
