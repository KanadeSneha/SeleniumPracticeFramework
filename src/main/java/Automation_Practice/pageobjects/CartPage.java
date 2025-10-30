package Automation_Practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Automation_Practice.AbstractClass.AbstractClass;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

public class CartPage extends AbstractClass {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButtonElement;

	public Boolean searchItemInCart(String productName) {

		Boolean cartItemMatchedBoolean = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return cartItemMatchedBoolean;
	}

	public CheckoutPage clickCheckout() {

		checkoutButtonElement.click();
		return new CheckoutPage(driver);

	}

}
