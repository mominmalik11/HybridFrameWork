package com.tutorialninjas.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
	
	public WebDriver driver;
	
	//Lets list all the Objects(WebElement) that I see on this page 
	
	@FindBy(linkText = "My Account") // this annotaion @FindBy helps us locate the webelement 
	private WebElement myAccountDropDown;// put private here because you want this webelement only to be used on the homepage
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchTextBoxField;
	
	@FindBy(css = "#search > span > button")// this is a cssSelector 
	private WebElement searchButton;
	
	
	// Now we need to initialize the objects I have defined above by creating a contructor 
	public HomePage(WebDriver driver) {//this initializes the browser
		this.driver = driver; //to stop the compiler from getting confused with the globally declared WebDriver driver
		PageFactory.initElements(driver, this);// this pagefactory class can initialize all the above elements together
		
		//OR you can write
		
		//PageFactory.initElements(driver, this); this being this page either way works 
		}
	//since the above elements are private you no one can access them, only the actions they can perform
	//Actions for each WebElement
	
	public void clickOnMyAccountDropMenu() {
		myAccountDropDown.click();
		
		}
	public LoginPage selectOnLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public void selectOnRegisterOption() {
		registerOption.click();
	}	
	
	public void enterProductDetail(String productNameText) {
		searchTextBoxField.sendKeys(productNameText);
		
		
		
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
		
	}
	
	
	//public void selectSearchBox() {
		//searchTextBoxField.click();
		
	
	//public void selectSearchButton() {
		//searchButton.click();
		
}
	
