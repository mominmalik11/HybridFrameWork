package com.tutorialninjas.qa.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v118.network.model.Request.ReferrerPolicy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Tutorialsninja.qa.TestBase.TestBase;

public class SearchProductTest extends TestBase {
	
	public SearchProductTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public ChromeOptions options;
	
	
	@BeforeMethod
	public void loginSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
	}
	
	@Test(priority = 1)
	public void verifyValidProduct() {
		driver.findElement(By.cssSelector("#search > input")).sendKeys(dataprop.getProperty("validProduct"));
		driver.findElement(By.cssSelector("#search > span > button")).click();
		Assert.assertTrue((driver.findElement(By.cssSelector("#content > div:nth-child(8) > div > div > div:nth-child(2) > div.button-group > button:nth-child(1) > span"))).isDisplayed());
		
		
	}
	@Test(priority = 2)
	public void verifyInvalidProduct() {
		driver.findElement(By.cssSelector("#search > input")).sendKeys(dataprop.getProperty("invalidProduct"));
		driver.findElement(By.cssSelector("#search > span > button")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#content > p:nth-child(7)")).isDisplayed());
		
		
	}
	
	@Test(priority = 3)
	public void verifyNoProduct() {
		
		driver.findElement(By.cssSelector("#search > span > button")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#content > p:nth-child(7)")).isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();

}

}
