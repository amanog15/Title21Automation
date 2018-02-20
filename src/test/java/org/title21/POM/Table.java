package org.title21.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.title21.utility.BaseClass;
import org.title21.validation.entities.ErrorMessages;

public class Table extends BaseClass
{
	public BaseClass baseClassObj=new BaseClass();
	public WebDriver driver;
	public WebElement element;
		
	@FindBy(xpath="//ancestor::table")
	WebElement table;
	
	//@FindAll({@FindBy(xpath="//tr")})
	//List<WebElement> tableRows;
		
	@FindBy(xpath="//*[contains(@class,'t21-js-clickable-rows')]/tr")
	List<WebElement> tableRows; 
		
	@FindBy(tagName = "thead")
	WebElement tableHeader;	
	
	@FindBy(xpath="//span[contains(@class,'pagination-label') and contains(text(),'Previous')]")
	WebElement previous;
	
	@FindBy(xpath="//span[contains(@class,'pagination-label') and contains(text(),'Next')]")
	WebElement next;
	
	public Table(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public WebElement getTable()
	{
		return table;
	}
	
	public List<WebElement> gettableRows(){		
		return table.findElements( By.xpath("//tr"));
	}

	public List<WebElement> gettableCells(){	
		List<WebElement> cells = null;
		for(WebElement row : tableRows) {
		    cells = row.findElements(By.xpath("//td"));
		  }
		return cells;
	}
	
	/*
	 * Following function will find tablecells only in given column.
	 * @param columnNumber
	 */
	
	public List<WebElement> gettableCells(int columnNumber){	
		List<WebElement> cells = null;
		for(WebElement row : tableRows) {
		    cells = row.findElements(By.xpath("//td["+columnNumber+"]"));
		  }
		return cells;
	}
	
	
	
	public WebElement getTableHeader()
	{
		return tableHeader;
	}
	
	public List<WebElement> getColumnHeadings()
	{		
		List<WebElement> columnHeadings = 
				tableHeader.findElements(By.xpath("//tr/th/a"));
		
		return columnHeadings;
	}
	
	public int getNumberOfRows() {
		return tableRows.size();
	}
		
	
	public int getNumberOfVisibleRows() {
		int i = 0;
		for (WebElement row : tableRows) {
			if (row.isDisplayed()) {
				i++;
			}
		}
		return i-1;
	}
	
	
}


