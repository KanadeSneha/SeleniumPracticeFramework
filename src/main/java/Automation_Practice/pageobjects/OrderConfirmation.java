package Automation_Practice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Automation_Practice.AbstractClass.AbstractClass;

public class OrderConfirmation extends AbstractClass {

	WebDriver driver;

	public OrderConfirmation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement thankyouTextElement;

	public String confirmOrderIsPlaced() {

		String orderConfirmedTextString = thankyouTextElement.getText();

		return orderConfirmedTextString;

	}

	public void closeBrowser() {
		driver.close();
	}
}
