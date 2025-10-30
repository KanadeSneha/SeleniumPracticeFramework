package Automation_Practice;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	
	@Test(dataProvider = "loginData")
	public void LoginOption(String username, String password){
		WebDriverManager.chromiumdriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("signInBtn")).click();
	
		
	}
	@DataProvider(name="loginData")
	public Object[][] getData(){
	  Object[][] data = new Object[2][2];
	  data[0][0] = "firstusername";
	  data[0][1]= "firstpassword";
	  data[1][0] = "secondusername";
	  data[1][1]= "secondpassword";
	  return data;
	  
	}
	

}
