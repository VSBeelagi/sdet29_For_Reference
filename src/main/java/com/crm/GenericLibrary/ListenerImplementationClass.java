package com.crm.GenericLibrary;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import crm.pom.ObjectRepository.BaseClass;

public class ListenerImplementationClass implements ITestListener{

	ExtentReports report;
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		test = report.createTest(MethodName);
		Reporter.log(MethodName + "--- testscript execution started");
	}

	public void onTestSuccess(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.PASS, MethodName+"----->passed");
		Reporter.log(MethodName + "--- testscript execution sucessfull - PASS");
	}

	public void onTestFailure(ITestResult result) {
		 String path = null;
		String MethodName = result.getMethod().getMethodName()+"-";
		Reporter.log(MethodName + "--- TestScript Failed",true);
		
		//Step 1: Configure screenshot name
				String screenshotName = MethodName+new JavaUtility().getSystemDateInFormat();
				System.out.println(screenshotName);
				
		//Step 2: using screenshot method from webDriver Utility
		try {
					
			//new WebDriverUtility().getScreenShot(BaseClass.sDriver, screenshotName);
			EventFiringWebDriver eDriver= new EventFiringWebDriver(BaseClass.sDriver);
			File src = eDriver.getScreenshotAs(OutputType.FILE);
			//String path= System.setProperty("user.dir")+"/ScreenShots/"+screenShotName+"PNG");
			path= ".\\screenshot\\"+screenshotName+".png";
			File dst = new File(path);
			Files.copy(src, dst);
					
					
			} catch (Throwable e) {
					e.printStackTrace();
			}
		
		test.log(Status.FAIL, MethodName+"---->failed");
		//it will capture the exception and log it in the report
		test.log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromPath(path);
		}

	public void onTestSkipped(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, MethodName+"----->skipped");
		//it will capture the exception and log it in the report
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(MethodName + "--- TestScript Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
		//Execution will start here
		/*Configure th report*/
		ExtentSparkReporter htmlReport= new ExtentSparkReporter("./EntentReports/Report"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlReport.config().setDocumentTitle("SDET-30 Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("Selenium execution Report");
		
		report = new ExtentReports();
		
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base-Browser", "Chrome");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("base-url", " https://localhost:8888");
		report.setSystemInfo("Reporter Name", "Vijayalaxmi SB");
		
	}

	public void onFinish(ITestContext context) {
		
		//Consolidate all the parameters and generate the report
		report.flush();
		
	}
	

}
