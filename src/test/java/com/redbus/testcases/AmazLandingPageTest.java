package com.redbus.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redbus.base.BaseClass;
import com.redbus.pageobjects.AmazLandingPage;
import com.redbus.pageobjects.RedBusLandingPage;

public class AmazLandingPageTest extends BaseClass
{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(AmazLandingPageTest.class);
	AmazLandingPage homePage;
	
	public AmazLandingPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new AmazLandingPage(driver);
	}
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		log.info("Verifying Homepage Title");
		String currentTitle= homePage.getHomePageTitle();
		System.out.println("Amazon Home page Title is: "+ currentTitle);
		log.info("Amazon Home page Title is: "+ currentTitle);
		Assert.assertEquals(currentTitle,"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		
	}
}
