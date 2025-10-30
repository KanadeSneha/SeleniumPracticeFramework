package Automation_Practice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.nio.channels.SelectableChannel;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Locale.IsoCountryCode;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocatorsPractice {

	public static void main(String[] args) {
		WebDriverManager.chromiumdriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
				
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		///implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//input[@value=\"radio3\"]")).click();
		driver.findElement(By.id("autocomplete")).sendKeys("Ind");
		
		////////Explicit wait
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@id='ui-id-1']"))));
		
	List<WebElement> allcountriesElements	= driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/div"));
	
	
    WebElement desiredcountry  =  allcountriesElements.stream()
    		.filter(country -> country.getText().equals("India")).findFirst().orElse(null);
    
    desiredcountry.click();
    
   WebElement dropdownElement  =  driver.findElement(By.id("dropdown-class-example"));
   Select select = new Select(dropdownElement);
   select.selectByVisibleText("Option2");
   
///////window handles
   String currentWIndowHandlElement = driver.getWindowHandle();
   driver.findElement(By.xpath("//button[@id='openwindow']")).click();
   driver.findElement(By.xpath("//a[@id='opentab']")).click();
   Set<String> allwindowSet= driver.getWindowHandles();
 System.out.println(allwindowSet.size());
   for(String windowHandle : allwindowSet)
   {
   driver.switchTo().window(windowHandle);
   System.out.println(driver.getTitle());
   }
   
   driver.switchTo().window(currentWIndowHandlElement);
   
   driver.findElement(By.id("alertbtn")).click();
   
   ////alerts
  String alretString= driver.switchTo().alert().getText();
   
   Assert.assertEquals(alretString, "Hello , share this practice page and share your knowledge");
   
   driver.switchTo().alert().accept();
   
  Assert.assertEquals(true, driver.findElement(By.cssSelector("#displayed-text")).isDisplayed());
  driver.findElement(By.cssSelector("#hide-textbox")).click();
  Assert.assertEquals(false, driver.findElement(By.cssSelector("#displayed-text")).isDisplayed());
   
  
 List<WebElement> tabElements =  driver.findElements(By.cssSelector(".tableFixHead tbody tr"));
 int sum=0;
 String rowsum;
 for(WebElement tElement: tabElements) {
	 rowsum = tElement.findElement(By.xpath("td[4]")).getText();
	 sum = sum+ (Integer.parseInt(rowsum));
 }
 System.out.println(sum);
 String capturedString =  driver.findElement(By.xpath("//div[@class='totalAmount']")).getText();

 int totalFromPage = Integer.parseInt(capturedString.replaceAll("[^0-9]", ""));
 System.out.println("Extracted total: " + totalFromPage);
 
 Assert.assertEquals(sum, totalFromPage);
 
 
 ////iframes
 driver.switchTo().frame("courses-iframe");
 driver.findElement(By.xpath("//legend[normalize-space()='iFrame Example']")).click();
 System.out.println(driver .getTitle());
 
 
/////Action class
	Actions actions = new Actions(driver);
	
	actions.doubleClick().build().perform();
   driver.quit();
   
   
	}
	

	
	///dataprovider
	@DataProvider(name="login")
	public Object[][] getdata() {
		
		Object[][] dataObjects = new Object[2][2];
		dataObjects[0][0] = "username1";
		dataObjects[0][1] = "password1";
		dataObjects[1][0] = "username2";
		dataObjects[1][1] = "password2";
		return dataObjects;
	}
	
	@Test(dataProvider = "login")
	public void login() {
		
	}
	
	
	

}
