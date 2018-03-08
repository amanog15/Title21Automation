package org.title21.POM;
//import java.util.List;

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
	
	@FindBy(xpath=".//*[@id='lock']/a[1]")
	WebElement EditModeOff;
	
	
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
	

	@FindBy(xpath="//span[contains(@class,'field-validation-error') and contains(@data-valmsg-for,'AttachmentFile')]")
	 WebElement UploadSizemsg;
	
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

	@FindBy(css=".btn.btn-default.t21-ajax-link")
	WebElement EditModeON;
	
	@FindBy(css="#DocAppendix")
	WebElement Appendix;
	
	@FindBy(css=".input-group-btn")
	WebElement PlusButtonuploadfile;
	
	@FindBy(xpath=".//*[@id='AttachmentFile']")
	WebElement Brouse;
	
	@FindBy(xpath="//button[contains(@class,'btn t21-btn-primary t21-ajax-submit-button')]")
	WebElement AddButtonupload;
	
	@FindBy(css=".btn.t21-btn-default.pull-left")
	WebElement Cancel;
	
	@FindBy(xpath=".//*[@id='collapse-0']/div/div[1]/div[2]/div[2]/a[2]")
	WebElement pdf;
	
	@FindBy(css=".btn.t21-btn-default")
	WebElement checkinwindowclose;
	
	@FindBy(css=".t21-js-user-message-text")
	WebElement checkinwindowsuccessmsg;
	
	
	
	
	@FindBy(css=".modal-title")
	WebElement uploadpopuptitle;
	
	
	@FindBy(css=".fa.fa-level-up.grid-button-icon")
	WebElement checkin;
	

	@FindBy(xpath=".//*[@id='displaySel']/div[1]/div[3]/div/a/span[2]")//.//*[@id='displaySel']/div[1]/div[3]/div/a/span[2]
	WebElement contextmenu;
	
	@FindBy(css=".btn.t21-btn-primary.t21-ajax-submit-button")
	WebElement checkinbuttonwindow;
	

	@FindBy(css=".btn.t21-btn-default.pull-left")
	WebElement checkincancel;
	
	
	public WebElement getcheckinbuttonwindow()
	{
		
		return checkinbuttonwindow;			
	}
	public WebElement getcheckincancel()
	{
		
		return checkincancel;			
	}
	
	public WebElement getcheckinwindowclose()
	{
		
		return checkinwindowclose;			
	}
	public WebElement getcheckinwindowsuccessmsg()
	{
		
		return checkinwindowsuccessmsg;			
	}
	
	public WebElement getcheckin()
	{
		
		return checkin;	//.//*[@id='displaySel']/div[1]/div[3]/div/ul/li[12]/div/a/span[2]		
	}
	
	public WebElement getEditModeON()
	{
		
		return EditModeON;			
	}
	
	public WebElement getcontextmenu()
	{
		
		return contextmenu;			
	}
	
	public WebElement getpdf()
	{
		
		return pdf;			
	}
	
	public WebElement getCancel()
	{
		
		return Cancel;			
	}
	public WebElement getuploadpopuptitle()
	{
		
		return uploadpopuptitle;			
	}
	
	public WebElement getAddButtonupload()
	{
		
		return AddButtonupload;			
	}
	
	
	public WebElement getBrouse()
	{
		
		return Brouse;			
	}
	
	
	
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

	
	public WebElement getEditModeOff()
	{
		
		return EditModeOff;			
	}
	public WebElement GeteditdocumentNo()
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
	
	public WebElement getUploadSizemsg()
	{
		
		return UploadSizemsg;			
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

public boolean CheckinSuceessmessage(){
	
	element=getcheckinwindowsuccessmsg();
	String errorMessage = element.getText();
	boolean isValidationMessagePresent=false;		
	
	if(errorMessage.contains(ErrorMessages.checkedsuccessfullyMessage))
	{
		isValidationMessagePresent=true;
	}else{
		log.error("Validation message for check in is not valid.");
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

public boolean UploadFileSizeValidation(){
	
	element=getUploadSizemsg();
	String errorMessage = element.getText();
	boolean isValidationMessagePresent=false;		
	
	if(errorMessage.contains(ErrorMessages.FileSizeuploadValidationMessage))
	{
		isValidationMessagePresent=true;
	}else{
		log.error("Validation message for file size is not valid  ");
	}	
	return isValidationMessagePresent;
}
	

public WebElement getdocument()
{
	
	return document;			
}
	
	public WebElement getPlusButtonuploadfile()
	{
		
		return PlusButtonuploadfile;			
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
