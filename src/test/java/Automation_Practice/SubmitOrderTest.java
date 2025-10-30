package Automation_Practice;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Automation_Practice.pageobjects.CartPage;
import Automation_Practice.pageobjects.CheckoutPage;
import Automation_Practice.pageobjects.LandingPage;
import Automation_Practice.pageobjects.OrderConfirmation;
import Automation_Practice.pageobjects.Productcatalogue;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";

		// login

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		Productcatalogue productcatalogue= landingPage.loginApplition("playwright987@gmail.com", "Learning@123");

		// find zara coat 3
		
		List<WebElement> allProducts = productcatalogue.getProductList();

		WebElement prod = productcatalogue.getProductByName(productName);

		// add to cart
		productcatalogue.addToCart(prod);

		// navigate to cart page
		CartPage cartPage  =productcatalogue.navigateToCarPage();

		// search item in cart
		
		Boolean cartItemMatchedBoolean =  cartPage.searchItemInCart(productName);
		Assert.assertTrue(cartItemMatchedBoolean);


		// click chekout

		CheckoutPage checkoutPage= cartPage.clickCheckout();

		checkoutPage.SelectCountry("India");

		OrderConfirmation orderConfirmation = checkoutPage.PlaceOrder();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("toast-title")));
		
		String orderConfirmedTextString =	orderConfirmation.confirmOrderIsPlaced();
		Assert.assertTrue(orderConfirmedTextString.equalsIgnoreCase("Thankyou for the order."));
		orderConfirmation.closeBrowser();
		
		
	}
}
