package com.tutorialninjas.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement enterEmail;

	@FindBy(id = "input-password")
	private WebElement enterPassword;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement clickLoginButton;
	
	@FindBy(xpath = "//*[@id=\"account-login\"]/div[1]")
	private WebElement warningMessageInvalidCredentials;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String emailText) {// dont forget parameter if youre writing for something to be ebntered

		enterEmail.sendKeys(emailText);

	}

	public void enterPassword(String passwordText) {

		enterPassword.sendKeys(passwordText);

	}

	public AccountPage clickOnLoginButton() {

		clickLoginButton.click();
		return new AccountPage(driver);
}
	
	public AccountPage naviatetoLoginPage(String emailText, String passwordText) {// this class has all three steps in one line of code 
		enterEmail.sendKeys(emailText);
		enterPassword.sendKeys(passwordText);
		clickLoginButton.click();
		return new AccountPage(driver);
		
		
	}
	
	

	public String retrieveEmailPasswordNotMatchingWarningText() {
		String warningText = warningMessageInvalidCredentials.getText();
		return warningText;
		
	}
	
	//so in every loginTest we have three operations are constantly being preformed, enter email, password, and hit login
	//we can simpligy these three into 1 
	//see line 49
	
	

}
