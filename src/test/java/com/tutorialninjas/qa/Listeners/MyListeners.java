package com.tutorialninjas.qa.Listeners;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.Utilities.ExtentReporter;

public class MyListeners implements ITestListener {// you need to implement the listener here first
	
	//to take a screenshot of passes or failures this is what you do
	// call web driver
	
	public WebDriver driver;
	public ExtentReports extentReports;// control shift o 
	public ExtentTest extentTest;// control shift o 
	

	@Override
	public void onStart(ITestContext context) {// if you look this onstart and at the bottom on finish are overloaded with an interfance iTestContext, 
		// the rest are overloaded with iTestResults
		System.out.println("Project Execution Started");
		//you call your extent report here
		try {
			ExtentReporter.generateExtentReport();
		} catch (Exception e) {
			e.printStackTrace();
		}// surround by try-catch block

	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
		//System.out.println(testName + "-------> Started Execution");
		 extentTest = extentReports.createTest(testName);
		 extentTest.log(Status.INFO, testName +"----> Started Execution");
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		//System.out.println(testName + "-------> Executed Succesfully");
		 extentTest.log(Status.PASS, testName +"----> Executed Succesfully");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		
		driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File source = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
		String destination = System.getProperty("user.dir") + "\\test-output\\ScreenShots" + testName+ ".png" ;
		
		
		
		//FileHandler.copy(source,destination);
		
		
		
		extentTest.addScreenCaptureFromBase64String(destination);
		//System.out.println(testName + "-------> Execution Failed bruh");
		extentTest.log(Status.FAIL, testName +"----> Execution Failed bruh");
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + "-------> Execution Skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("Project Execution Ended.");
		extentReports.flush();//YOU MUST DO THIS
		

	}// you need to implement the listener here first
	// then go to testNG xml file and use it to use the implemented and unimplemented listeners
	// in testNG xml file theres a tag you have to add under the suit line called <listeners>

}
