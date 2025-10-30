package Automation_Practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation_Practice.AbstractClass.AbstractClass;

public class Productcatalogue extends AbstractClass {

	WebDriver driver;

	public Productcatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> allProducts;
	@FindBy(css="[routerlink*='cart']")
	WebElement cartLinkElement;
	

	By productsBy = By.cssSelector(".mb-3");
By toastcontainer = By.cssSelector("#toast-container");
By spinner = By.cssSelector(".ng animating");
	public List<WebElement> getProductList() {

		waitForVisibilityOfElement(productsBy);
		return allProducts;

	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod =  allProducts.stream()
		.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
		.orElse(null);
		return 		prod;
	}

	public void addToCart(WebElement prod) {
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForVisibilityOfElement(productsBy);
		waitForVisibilityOfElement(toastcontainer);
		waitForInVisibilityOfElement(spinner);
		
	}
	
	public CartPage navigateToCarPage() {
		cartLinkElement.click();
		return  new CartPage(driver);
		
	}

}
