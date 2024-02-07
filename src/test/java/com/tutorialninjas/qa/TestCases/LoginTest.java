package com.tutorialninjas.qa.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Tutorialsninja.qa.TestBase.TestBase;
import com.tutorialninja.qa.TestData.ExcelCode;
import com.tutorialninjas.qa.Pages.AccountPage;
import com.tutorialninjas.qa.Pages.HomePage;
import com.tutorialninjas.qa.Pages.LoginPage;
import com.tutorialsninja.qa.Utilities.Util;

public class LoginTest extends TestBase {

	public LoginTest() throws Exception {
		super();
	}

	// verifyLoginWithValidCredentials
	// verifyLoginWithInvalidEmailValidPassword
	// verifyLoginWithValidEmailInvalidPassword
	// verifyLoginWithInvalidCredentials
	// verifyLoginWithNoCredentials

	public WebDriver driver;
	public HomePage homePage;
	public LoginPage loginPage;
	public AccountPage accountPage;
	

	@BeforeMethod

	public void setup() {

		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));// you have to put driver = this this
																					// method returns
		homePage = new HomePage(driver);// cant keep it empty the constructor is overloaded in the homepage
										// class
		// driver, in this case we used the properties file

		homePage.clickOnMyAccountDropMenu();
		// driver.findElement(By.linkText("My Account")).click();

		loginPage = homePage.selectOnLoginOption();
		// driver.findElement(By.linkText("Login")).click();
		// after this gets executed system will redirected to login page
		// it returns you a login page
		// so now you can make an instance of this object
	}

	@Test(priority = 1, dataProvider = "TNLogin", dataProviderClass = ExcelCode.class) // we need to call the name of
																						// the dataProvider AND the
																						// class to use the excel sheet
																						// in different class
	public void verifyLoginWithValidCredentials(String username, String password) {// make sure to add parameters when
																					// using excel sheets
		// create object of loginPage that has initialized WebElements

		
		accountPage = loginPage.naviatetoLoginPage(username, password);
		//loginPage.enterEmail(username);// this is replacing the hard code with
										// something more physically appealing
		// driver.findElement(By.id("input-email")).sendKeys(username);

		//loginPage.enterPassword(password);
		// driver.findElement(By.id("input-password")).sendKeys(password);

		//loginPage.clickOnLoginButton();
		// driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div >
		// form > input")).click();

		/// At this point the system will be redirected to account page and we can simply it now in the code to reflect that
		// to reflect it add accountpage before that 
		accountPage = loginPage.clickOnLoginButton();
		

		//AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditAccount());

		// WebElement editYourAccountInfoLink = driver.findElement(By.linkText("Edit
		// your account information"));
		// WebElement logout =
		// driver.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[13]"));
		// Assert.assertTrue(editYourAccountInfoLink.isDisplayed() &&
		// logout.isDisplayed());

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidEmailValidPassword() {

		accountPage = loginPage.naviatetoLoginPage(Util.emailWithDateTimeStamp(),prop.getProperty("validPassword"));
		
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		//loginPage.enterEmail(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));// removed hard code
		//loginPage.enterPassword(prop.getProperty("validPassword"));																						// "Selenium@123" to
																								// properties file
		//driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
		//String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarningMessage");
		//String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();

		// String actualWarningMessage =
		// driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningText().contains(dataprop.getProperty("emailPasswordNoMatchWarningMessage")));

	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailInvalidPassword() {
		
		accountPage = loginPage.naviatetoLoginPage(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));

		
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));// removed hard code
																							// "seleniumpanda@gmail.com"
																							// to properties file
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
		WebElement warningMessageElement = driver
				.findElement(By.cssSelector("#account-login > div.alert.alert-danger.alert-dismissible"));
		Assert.assertTrue(warningMessageElement.isDisplayed());

	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidCredentials() {

		accountPage = loginPage.naviatetoLoginPage(Util.emailWithDateTimeStamp(), dataprop.getProperty("invalidPassword"));
		
		//loginPage.enterEmail(Util.emailWithDateTimeStamp());
		// driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());

		//loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
		// driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("invalidPassword"));
		//driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();

		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		// WebElement warningMessageElement =
		// driver.findElement(By.cssSelector("#account-login >
		// div.alert.alert-danger.alert-dismissible"));

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {

		driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
		WebElement warningMessageElement = driver
				.findElement(By.cssSelector("#account-login > div.alert.alert-danger.alert-dismissible"));
		Assert.assertTrue(warningMessageElement.isDisplayed());

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
