package com.redbus.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redbus.base.BaseClass;
import com.redbus.pageobjects.RedBusLandingPage;

public class LandingPageTest extends BaseClass
{
	public WebDriver driver;
	RedBusLandingPage homePage;
	
	public LandingPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new RedBusLandingPage(driver);
	}
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		String currentTitle= homePage.getHomePageTitle();
		System.out.println("Red Bus Home page Title is: "+ currentTitle);
		Assert.assertEquals(currentTitle,"Online, Easy & Secure Booking, Top Operators - redBus");
		
	}
}
