package com.Tutorialsninja.qa.TestBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.Utilities.Util;

import dev.failsafe.Timeout;

public class TestBase {

	public WebDriver driver;// these are constuctors as well
	public ChromeOptions options;
	public Properties prop;// after creating construtor move this global class here
	public FileInputStream ip;

	// 2nd properties file connect global code

	public Properties dataprop;
	public FileInputStream ip1;

	// create a constructor in TestBase

	public TestBase() throws Exception {// you want to code the properties file here

		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\tutorialninjas\\qa\\Config\\config.properties");// fileinputstream cannot be
																							// empty, but config.prop
																							// file path
		prop.load(ip);

		// this is how you connect a 2nd propeties file to the TestBase

		dataprop = new Properties();
		ip1 = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\tutorialninja\\qa\\TestData\\systemGeneratedResponseData_And_UserDefinedData.properties");
		dataprop.load(ip1);

	}

	public WebDriver initializeBrowserAndOpenApplication(String browserName) { // overload the method

		if (browserName.equals(prop.getProperty("browser"))) {
			options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);// make sure to enter this AFTER OPTIONS

		} else if (browserName.equals("FireFox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equals("Edge")) {
			driver = new EdgeDriver();

		}
		// we dont like all the seconds listed here so lets change that using the util
		// folder
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.PAGE_LOAD_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.SCRIPT_TIME));// how long the entire code
																						// should be take,
		// if its more than 1000 seconds it will be
		// terminated
		driver.get(prop.getProperty("url"));// utliize the properties code

		return driver;// whats the point of return to driver?
						// you want this method to be used in the login, register, SearchProduct and
						// add2cart tests so this will be the parent class, which
		// always has a return

	}

}
