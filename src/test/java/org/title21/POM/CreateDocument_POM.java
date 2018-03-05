package org.title21.POM;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.title21.dao.AdminData;
import org.title21.validation.entities.ErrorMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.title21.dao.AdminData;

public class CreateDocument_POM 

{
	public WebDriver driver;
	public WebElement element;
	AdminData adminData=new AdminData();
	static Logger log = Logger.getLogger(CreateDocument_POM.class);
	public CreateDocument_POM (WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=".//*[@id='New']/a")
	WebElement newdoc;
	
	@FindBy(xpath=".//*[@id='Layer_1']")
	WebElement document;
	
	@FindBy(xpath=".//*[@id='Location']")
	WebElement location;
	
	
	@FindBy(xpath=".//*[@id='Cabinet']")
	WebElement Cabinet;
	
	@FindBy(css="#CabinetSection")
	WebElement Section;
	

	@FindBy(css=".form-control.t21-placeholder.valid")
	WebElement search;
	
	@FindBy(css=".t21-inline-block")
	WebElement documentcreationverify;
	
	
	@FindBy(css="#DocCheckOutTo")
	WebElement AutoCheck;
	
	@FindBy(css="#DocumentTitle")
	WebElement DocumentTitle;
	
	@FindBy(xpath="//span[contains(@class,'field-validation-error') and contains(@data-valmsg-for,'DocumentTitle')]")
    WebElement DocumentTitlemsg;
	
	@FindBy(xpath="//span[contains(@class,'field-validation-error') and contains(@data-valmsg-for,'DocAppendix')]")
    WebElement appedixvalmsg;
	
	
	@FindBy(xpath="//span[contains(@class,'field-validation-error') and contains(@data-valmsg-for,'DocChangeSummary')]")
	 WebElement Documentsummarymsg;
	
	@FindBy(css="#DocChangeSummary")
	WebElement DocChangeSummary;
	
	@FindBy(css=".btn.t21-btn-primary.t21-ajax-submit-button.process-btn-click")
	WebElement ConfirmButtonm;
	
	@FindBy(xpath=".//*[@id='documentId']")
	WebElement documentnumber;
	
	@FindBy(css=".btn.btn-default.fa.fa-pencil.t21-ajax-link")
	WebElement editdocumentNO;
	
	@FindBy(css="#DocCounter")
	WebElement Number;
	
	@FindBy(css="#DocAppendix")
	WebElement Appendix;
	
	@FindBy(css=".btn.t21-btn-default.pull-left")
	WebElement Cancel;
	
	public WebElement getDocumentTitle()
	{
		
		return DocumentTitle;			
	}
	
	public WebElement getdocumentcreationverify()
	{
		
		return documentcreationverify;			
	}
	
	public WebElement getdocumentnumber()
	{
		
		return documentnumber;			
	}
	
	
	public WebElement getDocChangeSummary()
	{
		
		return DocChangeSummary;			
	}
	
	public WebElement GeteditdocumentNO()
	{
		
		return editdocumentNO;			
	}
	public WebElement getnewdoc()
	{
		
		return newdoc;			
	}
	
	public WebElement getDocumentTitlemsg()
	{
		
		return DocumentTitlemsg;			
	}
	public WebElement getDocumentsummarymsg()
	{
		
		return Documentsummarymsg;			
	}
	public WebElement getappedixvalmsg()
	{
		
		return appedixvalmsg;			
	}
	
	
public boolean DocumentTitlemsgvalidation(){
		
		element=getDocumentTitlemsg();
		String errorMessage = element.getText();
		boolean isValidationMessagePresent=false;		
		
		if(errorMessage.contains(ErrorMessages.DocumentTitleValidationMessage))
		{
			isValidationMessagePresent=true;
		}else{
			log.error("Validation message for documet  title is not valid.");
		}	
		return isValidationMessagePresent;
	}

public boolean Appedixvalidation(){
	
	element=getappedixvalmsg();
	String errorMessage = element.getText();
	boolean isValidationMessagePresent=false;		
	
	if(errorMessage.contains(ErrorMessages.AppendixValidationMessage))
	{
		isValidationMessagePresent=true;
	}else{
		log.error("Validation message for Appendix is not valid.");
	}	
	return isValidationMessagePresent;
}
public boolean Documentsummarymsgvalidation(){
	
	element=getDocumentsummarymsg();
	String errorMessage = element.getText();
	boolean isValidationMessagePresent=false;		
	
	if(errorMessage.contains(ErrorMessages.DocumentSummaryValidationMessage))
	{
		isValidationMessagePresent=true;
	}else{
		log.error("Validation message for Document summary is not valid  ");
	}	
	return isValidationMessagePresent;
}
	
	
	public WebElement getdocument()
	{
		
		return document;			
	}
	public Select getAutoCheck()
	{		
		Select selectObj=new Select(AutoCheck);
		return selectObj;		
	}
	public Select getSection()
	{		
		Select selectObj=new Select(Section);
		return selectObj;		
	}
	
	
	public Select getlocationDrodown()
	{		
		Select selectObj=new Select(location);
		return selectObj;		
	}
	
	
	public Select getcabinet()
	{		
		Select selectObj=new Select(Cabinet);
		return selectObj;		
	}
	
	
	public Select getnumberappedix()
	{		
		Select selectObj=new Select(Number);
		return selectObj;		
	}
	
	
	public WebElement getConfirmButton()
	{
		
		return ConfirmButtonm;			
	}
	public WebElement Appendix()
	{
		
		return Appendix;			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
