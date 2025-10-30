package Automation_Practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation_Practice.AbstractClass.AbstractClass;

public class LandingPage extends AbstractClass {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement login;

	public Productcatalogue loginApplition(String username, String password) {
		userEmail.sendKeys(username);
		passwordEle.sendKeys(password);
		login.click();
		return new Productcatalogue(driver);
	

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");

	}

}
