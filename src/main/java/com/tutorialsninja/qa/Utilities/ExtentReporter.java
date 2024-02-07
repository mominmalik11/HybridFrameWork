package com.tutorialsninja.qa.Utilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class  ExtentReporter {
	
	//must add the extent report dependencies into pom.xml file
	
	
	public static ExtentReports generateExtentReport() throws Exception {// make this static!!!!!!!!!!!!!!!!!!!!!!!!!!
		//step 1: add the extent reports in the poms.xml file
		
		//step 2: Create the Object of ExtendReports Class
		ExtentReports extentReport = new ExtentReports();
		
		//Step 3: create the object of file class and pass the path of the .html file in the constructor 
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreport.html");
		
		//Step 4: you have to create an object of ExentSparkReporter Class and pass the path of the file in its constructor 
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		//Step 5: use the sparkreporter reference we can configure a lot of things in extent reports 
		sparkReporter.config().setTheme(Theme.DARK); //makes theme of report dark 
		sparkReporter.config().setReportName("PnT Weekend Framework Extent Report Resullts");// gives the spark report a header
		sparkReporter.config().setDocumentTitle("TutorialsNinja Report Title");// this changes the tabs name of the document up top
		
		
		//Step 6: you need to attach the extent report with the sparkreporter!!!!!!!!!!!!!!!!!!!!!!!!!!
		extentReport.attachReporter(sparkReporter);
		
		//Step 7: Additional information the client might need; System information that can be passed onto the exten reports
			//Step 7.1 Create a properties file and add necessary details of the application 
		
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialninjas\\qa\\Config\\config.properties"); // here you can add whatever they want to know about the application
		// to your properties file 
		
		prop.load(ip);
		
		extentReport.setSystemInfo("application url", prop.getProperty("url"));
		extentReport.setSystemInfo("browser name", prop.getProperty("browser"));
		extentReport.setSystemInfo("valid email", prop.getProperty("validEmail"));
		extentReport.setSystemInfo("valid password", prop.getProperty("validPassword"));
		
		//Step 8: Return extentReport
		return extentReport;
		
	}

}
