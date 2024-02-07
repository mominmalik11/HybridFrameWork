package com.tutorialninjas.qa.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Tutorialsninja.qa.TestBase.TestBase;
import com.tutorialninjas.qa.Pages.AccountPage;
import com.tutorialninjas.qa.Pages.HomePage;
import com.tutorialninjas.qa.Pages.RegisterPage;
import com.tutorialsninja.qa.Utilities.Util;

public class RegisterTest extends TestBase {

	public RegisterTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public HomePage homePage;
	public RegisterPage registerpage;
	public AccountPage accountsuccesspage;

	

	@BeforeMethod

	public void setup() {

		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));// removed hardcode and replaced with properties file value
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@Test(priority = 1)
	public void verifyRegisterWithMandatoryFields() {

		//driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		registerpage.enterFirstName(dataprop.getProperty("firstName"));// this looks cleaner 
		
		
		//driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		registerpage.enterLastName(dataprop.getProperty("lastName"));
		
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("mobile"));
		
		
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));// removed hardcode and replaced with properties file value
		registerpage.enterPassword(prop.getProperty("validPassword"));
		
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));// removed hardcode and replaced with properties file value
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		
		//driver.findElement(By.xpath("//input[@name = 'agree' ]")).click();
		registerpage.clickOnNewsLetterYesOptionRadioButton();
		
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		accountsuccesspage = new AccountPage(driver);

		registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		
		
		
		String expectedSuccessfulRegisterMessage = driver.findElement(By.xpath("/html/body/div[2]/div/div/p[1]")).getText();
		String actualSuccessfulRegisterMessage = dataprop.getProperty("registerSuccess");
		Assert.assertTrue(expectedSuccessfulRegisterMessage.contains(actualSuccessfulRegisterMessage));
		
	}

	@Test(priority = 2)
	public void verifyRegisterWithAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		;
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("3018799695");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(
				By.xpath("//aside[@id = 'column-right']/preceding::input[@name = 'newsletter' and @value = '1']"))
				.click();
		driver.findElement(By.xpath("//input[@name = 'agree' ]")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedSuccessfulRegisterMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p[1]")).getText();
		String actualSuccessfulRegisterMessage = "Congratulations! Your new account has been successfully created!";
		Assert.assertEquals(expectedSuccessfulRegisterMessage, actualSuccessfulRegisterMessage);

	}

	@Test(priority = 3)
	public void verifyRegisterWithExistingEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		;
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys("3018799695");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(
				By.xpath("//aside[@id = 'column-right']/preceding::input[@name = 'newsletter' and @value = '1']"))
				.click();
		driver.findElement(By.xpath("//input[@name = 'agree' ]")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}
	
	@Test(priority=4)
	public void verifyingRegisterWithNoFields() {
		driver.findElement(By.xpath("//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")).click();
		
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = "Warning: You must agree to the Privacy Policy!";
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-firstname']/following-sibling::div[1]")).getText();
		String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-lastname']/following-sibling::div[1]")).getText();
		String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-email']/following-sibling::div[1]")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-telephone']/following-sibling::div[1]")).getText();
		String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-password']/following-sibling::div[1]")).getText();
		String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
		
			
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
