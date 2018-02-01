package org.title21.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.title21.reporting.ExtentManager;

//import com.framework.selenium.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;
	protected String filePath;
	protected String data[][];
	protected WebDriverWait waitDriver = null;
	
	public String excelFile="";
	public String sheetName="";
	public static String browser="";
	public static String baseUrl="";
	static String imagesDirectory = "";

	@BeforeMethod
	public void beforeMethod() {
		extent = ExtentManager.getReporter(filePath);
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

		extent.flush();
	}

	@BeforeSuite
	@Parameters({"configFile"})
	public void beforeSuite(String configFile) throws Exception {
		
		Properties p=new Properties();
		FileInputStream readconfig=new FileInputStream(configFile);
		p.load(readconfig);
		
		//initializing variables
		/*		
		excelFile = excelFilePath;
		sheetName = sheetName;
		browser=browser;
		baseUrl=baseUrl;
		*/
		browser=p.getProperty("browser");
		baseUrl=p.getProperty("baseUrl");
		excelFile=p.getProperty("excelFilePath");
		sheetName=p.getProperty("sheetName");
		
		String workingDir = System.getProperty("user.dir") + "\\extentReports";
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
		
		filePath = workingDir + "\\index.html";		
		
		data = ExcelData(excelFile, sheetName);
		extent = ExtentManager.getReporter(filePath);	
		
	}

	@AfterSuite
	public void afterSuite() {
		extent.close();
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file://" + filePath);
	}

	public void implicitwait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	public static void createDirectory(String classname) {

		classname = classname.substring(4);
		imagesDirectory = System.getProperty("user.dir") + "\\extentReports" + "\\" + classname;
		File file = new File(imagesDirectory);
		if (!file.exists()) {
			file.mkdir();
		} else {
			System.out.println("Failed to create directory.");
		}
	}

	public static String captureScreenShot(WebDriver driver, String screenshotName) {
		try {
			Calendar calander = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// String workingDir =
			// System.getProperty("user.dir")+"\\extentReports";
			String dest = imagesDirectory + "\\" + screenshotName + "-" + formater.format(calander.getTime()) + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);
			System.out.println("ScreenShot Taken");
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
			extent = ExtentManager.getReporter(filePath);
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			implicitwait(driver);
			driver.get(baseUrl);
			driver.manage().window().maximize();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			extent = ExtentManager.getReporter(filePath);
			System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			implicitwait(driver);
			driver.get(baseUrl);
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			implicitwait(driver);
			driver.get(baseUrl);
		}
	}

	public WebDriver SwitchToFrame() {
		driver.switchTo().frame(0);
		return driver;
	}

	public String[][] ExcelData(String path, String sheetname) throws Exception {
		File excel = new File(path);
		System.out.println(excel);
		
		FileInputStream fis = new FileInputStream(excel);
		System.out.println(fis);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetname);

		int rowNum = ws.getLastRowNum() + 1;
		int colNum = ws.getRow(0).getLastCellNum();

		String[][] data = new String[rowNum][colNum];

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
}