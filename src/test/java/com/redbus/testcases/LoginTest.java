package com.redbus.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.redbus.base.BaseClass;
import com.redbus.pageobjects.RedBusLandingPage;
import com.redbus.utility.Utilities;

public class LoginTest extends BaseClass {
	
	public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		RedBusLandingPage homePage = new RedBusLandingPage(driver);	
	}
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifyLoginWithInValidEmailId() throws Exception
	{	
		Utilities.navigateToLogin(driver); 		
		WebElement emailPhonefield = driver.findElement(By.xpath("//input[@name='identifier']"));
		emailPhonefield.sendKeys("test1622.user@gmail.com");
		
		WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();
		System.out.println("After username Next clicked ");
		
		WebElement wrongUsername = driver.findElement(By.xpath("//div[text()='Couldn’t find your Google Account']"));
		Utilities.isElementDisplayed(wrongUsername);
		Assert.assertTrue(wrongUsername.isDisplayed(),"INValid user Login Text while asking for mob number is not displayed");
		System.out.println("Login with Invalid email id");
	}
	
	@Test(priority=2)
	public void verifyLoginWithInValidPwd() throws Exception
	{
		Utilities.navigateToLogin(driver); 
		WebElement emailPhonefield = driver.findElement(By.xpath("//input[@name='identifier']"));
		emailPhonefield.sendKeys("test162.user@gmail.com");
		
		WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();
		System.out.println("After username Next Clicked ");
		
		System.out.println("Enter the incorrect pwd: ");
		WebElement passwordfield = driver.findElement(By.name("Passwd"));
		passwordfield.sendKeys("qwerty@1234");
		
		WebElement nextPwdButton = driver.findElement(By.xpath("//div[@id='passwordNext']//button"));	
		nextPwdButton.click();
		System.out.println("After Pwd Next Clicked ");
		
		WebElement wrongPwd = driver.findElement(By.xpath("//span[text()='Wrong password. Try again or click Forgot password to reset it.']"));	
		Assert.assertTrue(wrongPwd.isDisplayed(),"Wrong Pwd text is not displayed");
		
		System.out.println("Login with Invalid pwd");
	}
	
	@Test(priority=3,dataProvider = "RedBusLoginData")
	public void verifyLoginWithValidCredentials(String user, String pwd) throws Exception
	{	
		Utilities.navigateToLogin(driver); 	
		WebElement emailPhonefield = driver.findElement(By.xpath("//input[@name='identifier']"));
		emailPhonefield.sendKeys(user);
		
		WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();
		System.out.println("After Username Next Clicked ");
		
		WebElement passwordfield = driver.findElement(By.name("Passwd"));
		passwordfield.sendKeys(pwd);
		System.out.println("Password entered ");
		
		WebElement nextPwdButton = driver.findElement(By.xpath("//div[@id='passwordNext']//button"));	
		nextPwdButton.click();
		System.out.println("After Pwd Next Clicked ");
	
		//WebElement validLoginText= driver.findElement(By.xpath("//div[@class='mobile FC DIB active']/h2"));
		//Assert.assertTrue(validLoginText.isDisplayed(),"Valid Login Text while asking for mob number is not displayed");
		
		System.out.println("Login with valid cred");
	}
	
	@DataProvider
	public Object[][] RedBusLoginData()
	{
		Object[][] data= Utilities.getTestDatafromExcel("LoginTestData");
		return data;	
	}

}
