package com.tutorialninjas.qa.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Tutorialsninja.qa.TestBase.TestBase;

public class AddToCartTest extends TestBase {
	

	public AddToCartTest() throws Exception {
		super();
	}



	public WebDriver driver;
	public ChromeOptions options;
	
	
	@BeforeMethod
	public void loginSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	
	}
	
	@Test
	public void testCase1() throws Exception {
		driver.findElement(By.cssSelector("#search > input")).sendKeys("HP");
		driver.findElement(By.cssSelector("#search > span > button")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), 'Product Code:Product 21')]")).isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id = 'button-cart']")).click();
		Thread.sleep(3000);
		
		String successfullAddedMessage = driver.findElement(By.xpath("//*[@id=\"product-product\"]/div[1]")).getText(); 
		String successfullExpectedMessageString = dataprop.getProperty("productAddesToCartSuccess");
		Assert.assertTrue(successfullAddedMessage.contains(successfullExpectedMessageString));
		
		driver.findElement(By.xpath("//div[@id = 'cart']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Checkout")).click();
		
	

		
		
		
		
		
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();

}

	
	
	

}
