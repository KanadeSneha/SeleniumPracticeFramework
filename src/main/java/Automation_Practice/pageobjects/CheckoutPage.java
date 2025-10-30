package Automation_Practice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation_Practice.AbstractClass.AbstractClass;

public class CheckoutPage extends AbstractClass {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder*='Country']")
	WebElement countryElement;
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectedCountryElement;

	By results = By.cssSelector(".ta-results");

	@FindBy(css = ".action__submit")
	WebElement action_ButtomElement;

	public void SelectCountry(String country) {
		countryElement.sendKeys(country);
		waitForVisibilityOfElement(results);
		selectedCountryElement.click();
	}

	public OrderConfirmation PlaceOrder() throws InterruptedException {
		waitForInVisibilityOfElement(results);

		waitForclickableElement(action_ButtomElement);

		// Scroll down to make sure the button is visible
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", action_ButtomElement);
		// Optionally wait a bit for smooth scroll
		Thread.sleep(500);
		// Click the button
		action_ButtomElement.click();
		return new OrderConfirmation(driver);

	}

}
