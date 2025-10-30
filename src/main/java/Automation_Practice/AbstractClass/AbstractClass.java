package Automation_Practice.AbstractClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {
	
	WebDriver driver;
	
	public AbstractClass(WebDriver driver) {
		this.driver = driver;
		
	}

	public void waitForVisibilityOfElement(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		List<WebElement> allProducts = driver.findElements(findBy);
	}


	public void waitForInVisibilityOfElement(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}


	public WebElement waitForclickableElement(WebElement action_ButtomElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.elementToBeClickable(action_ButtomElement));
		return action_ButtomElement;				}

}
