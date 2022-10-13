package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);
		
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/myReport.html"); //specify path of the reports
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project name", "Employee Database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "bhoomi");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case PASSED Is " + result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Case Failed is" + result.getName());
		test.log(Status.FAIL, "Test Case failed is" + result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
	}
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
