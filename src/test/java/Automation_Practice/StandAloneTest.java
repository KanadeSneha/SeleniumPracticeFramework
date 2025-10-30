package Automation_Practice;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Automation_Practice.pageobjects.LandingPage;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";

		// login
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage = 	new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("playwright987@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Learning@123");
		driver.findElement(By.id("login")).click();

		// find zara coat 3

		List<WebElement> allProducts = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = allProducts.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		//navigate to cart page
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng animating")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
	//search item in cart
	List<WebElement> cartProducts  = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean cartItemMatchedBoolean =	cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(cartItemMatchedBoolean);
		
		//click chekout
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("India");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		wait.until((ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results"))));	
		
		WebElement action_ButtomElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

		// Scroll down to make sure the button is visible
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", action_ButtomElement);

		// Optionally wait a bit for smooth scroll
		Thread.sleep(500);

		// Click the button
		action_ButtomElement.click();
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("toast-title")));
		
	String orderConfirmedTextString = 	driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirmedTextString.equalsIgnoreCase("Thankyou for the order."));	
		
		driver.close();
	}
}
