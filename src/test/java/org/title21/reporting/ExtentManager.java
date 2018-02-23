package org.title21.reporting;

import java.io.FileInputStream;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager
{
	private static ExtentReports extent;	

	public synchronized static ExtentReports getReporter(String filePath,String testURL) {
		if (extent == null) {
			extent = new ExtentReports(filePath, true);
			
			extent
			.addSystemInfo("Host Name", "Title21")
			.addSystemInfo("Environment", "QA")
			.addSystemInfo("TestURL", testURL);
		}

		return extent;
	}
}