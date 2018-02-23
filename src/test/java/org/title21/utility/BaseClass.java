package org.title21.utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import java.util.UUID;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.title21.POM.AdministrationPage_POM;
import org.title21.POM.LoginPage_POM;
import org.openqa.selenium.JavascriptExecutor;

import org.title21.reporting.ExtentManager;

//import com.framework.selenium.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.title21.POM.LogoutPage_POM;
import org.title21.dao.AdminData;

public class BaseClass {

	protected static WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected String filePath;
	protected static String loginData[][];
	protected static String groupData[][];
	protected static String userData[][];
	protected static String employeeData[][];
	
	protected String data[][];
	protected WebDriverWait waitDriver = null;
	LoginPage_POM login;
	LogoutPage_POM logout;
	
	public String excelFile="";
	public static String loginSheet="";
	public static String groupSheet="";
	public static String employeeSheet="";
	public static String userSheet="";
	public static String browser="";
	public static String baseUrl="";
	public static String adminUsername="";
	public static String adminPassword="";
	static String imagesDirectory = "";
	
	int pixels=0;

	@BeforeMethod
	public void beforeMethod() {
		extent = ExtentManager.getReporter(filePath,baseUrl);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = captureScreenShot(driver, result.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, image);
			test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed");
		}

		//extent.flush();
	}

	@BeforeSuite
	@Parameters({"configFile"})
	public void beforeSuite(String configFile) throws Exception {
		
		// loading log4j properties.
		PropertyConfigurator.configure("log4j.properties");
		
		Properties p=new Properties();
		FileInputStream readconfig=new FileInputStream(configFile);
		p.load(readconfig);		
		
		browser=p.getProperty("browser");
		baseUrl=p.getProperty("baseUrl");
		excelFile=p.getProperty("excelFilePath");		

		loginSheet=p.getProperty("Loginsheet");
		groupSheet=p.getProperty("Groupsheet");
		userSheet=p.getProperty("UserSheet");
		employeeSheet=p.getProperty("EmployeeSheet");
		
		adminUsername=p.getProperty("adminUsername");
		adminPassword=p.getProperty("adminPassword");
		
		String workingDir = System.getProperty("user.dir") + "\\extentReports";
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
		
		filePath = workingDir + "\\index.html";		

		loginData=ExcelData(excelFile, loginSheet);
		groupData=ExcelData(excelFile, groupSheet);
		userData=ExcelData(excelFile, userSheet);
		employeeData=ExcelData(excelFile, employeeSheet);		
		
		extent = ExtentManager.getReporter(filePath,baseUrl);	
		
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {	
		extent.close();
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file://" + filePath);
	}

	public void implicitwait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	public static void createDirectory(String classname) {

		classname = classname.substring(4);
		imagesDirectory = System.getProperty("user.dir") + "\\extentReports" + "\\" + classname;
		//imagesDirectory = "c:\\Title21Automation" + "\\extentReports" + "\\" + classname;
		File file = new File(imagesDirectory);
		if (!file.exists()) {
			file.mkdir();
		} else {
			System.out.println("Either directory is already prersent or Failed to create directory.");
		}
	}
	
	/*	
	 * This function will take full screenshot of the application.
	 * 
	 */
	
	/*public static String captureScreenShot(WebDriver driver, String screenshotName) {
		try {
			Calendar calander = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
			
			Robot robot = new Robot();
			String format = "jpg";
            String fileName = screenshotName+ "-" + formater.format(calander.getTime());
                        			
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(fileName));
			String dest = imagesDirectory + "\\";
			
			String sourceDirectory=System.getProperty("user.dir");			
			File src=new File(sourceDirectory+"\\"+fileName);			 
			File destination = new File(dest);
			
			FileUtils.copyFile(src, destination);
			
			FileUtils.deleteQuietly(src);   		
			return dest;
		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
			return e.getMessage();
		}
	}
		*/

	public static String captureScreenShot(WebDriver driver, String screenshotName) {
		try {
			Calendar calander = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			// String workingDir =// System.getProperty("user.dir")+"\\extentReports";
			String dest = imagesDirectory + "\\" + screenshotName + "-" + formater.format(calander.getTime()) + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);			
			return dest;
		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	public void waitForPageToLoad(WebDriver driver,int seconds) {
		// sleep(2);
		waitDriver = new WebDriverWait(driver, seconds);
		waitDriver.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				try {
					return ((String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState"))
							.equals("complete");
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public String toString() {
				return "Page load complete";
			}

		});
	}
	
	
	public void getBrowser() {				
		     
		if (browser.equalsIgnoreCase("chrome")) {
			extent = ExtentManager.getReporter(filePath,baseUrl);
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			implicitwait(driver);
			driver.get(baseUrl);
			driver.manage().window().maximize();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			extent = ExtentManager.getReporter(filePath,baseUrl);
			System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			implicitwait(driver);
			driver.get(baseUrl);
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			extent = ExtentManager.getReporter(filePath,baseUrl);
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			implicitwait(driver);
			driver.get(baseUrl);
		}
	}
	
	
	public static void getAdministrationPage(ExtentTest test) {
		
		AdministrationPage_POM administrationPage = new AdministrationPage_POM(driver);
				
		String administratorTab = administrationPage.administratorDropDown().getText();
		
		try
		{		
			
			administrationPage.administratorDropDown().click();
			administrationPage.administrationLink().click();
						
			if(administrationPage.verifyAdministrationPagePrescence()) {				
				test.log(LogStatus.PASS, "2) Click on Administration link from the top right menu.");
			//	test.log(LogStatus.PASS, "1b) Successfully verify 'administration Page' Prescence."+
			//		test.addScreenCapture(captureScreenShot(driver, "administration Page")));
			}else {
				//test.log(LogStatus.FAIL, "Unable to verify 'administration Page' Prescence.");
				Reporter.log("Unable to verify 'administration Page' Prescence.");
			}			
			
		}catch(Exception e){
			
			test.log(LogStatus.FAIL, "Unable to find dropdown for Administration submenu.");			
		}
		
	}
	

	public WebDriver SwitchToFrame() {
		driver.switchTo().frame(0);
		return driver;
	}

	public String[][] ExcelData(String path, String sheetname) throws Exception {
		File excel = new File(path);
		FileInputStream fis = new FileInputStream(excel);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetname);
		
		int rowNum = ws.getLastRowNum()+1;
		int colNum = ws.getRow(0).getLastCellNum();

		data = new String[rowNum][colNum];

		for (int i = 0; i < rowNum; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				data[i][j] = value;
			}
		}

		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();

		wb.close();
		fis.close();
		return data;
	}

	@SuppressWarnings("deprecation")
	private static String cellToString(XSSFCell cell) {
		Object result;
		String strReturn = null;

		if (cell == null) {
			strReturn = "";
		} else {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				result = cell.getNumericCellValue();
				strReturn = result.toString();
				break;

			case Cell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				strReturn = result.toString();
				break;
			default:
				strReturn = null;
			}
		}
		return strReturn;
	}

	public static void sleep(double seconds) {
		try {
			Thread.sleep(Double.valueOf(seconds * 1000).intValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void waitTillElementisInvisible(WebElement element) {	
		
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.invisibilityOf(element));		
	}		
	
	public void waitTillElementVisible(WebElement element) {		
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOf(element));		
	}	
	
	public void waitTillElementClickable(WebElement element) {		
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(element));		
	}	
	
	public void javaScriptClick(WebElement element){
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		js.executeScript("arguments[0].click();",element);			
	}
		
	public void verticalScrollingDown() {		
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		js.executeScript("window.scrollBy(0,500)");		
	}
	
	public void verticalScrollingUp(){
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		js.executeScript("window.scrollBy(0,-800)");		
	}
}