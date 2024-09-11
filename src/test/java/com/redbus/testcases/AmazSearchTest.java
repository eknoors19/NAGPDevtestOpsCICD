package com.redbus.testcases;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.redbus.pageobjects.AmazLandingPage;
import com.redbus.pageobjects.AmazSearchResultPage;
import com.redbus.pageobjects.RedBusLandingPage;
import com.redbus.utility.Utilities;
import com.redbus.pageobjects.RedBusLandingPage;

public class AmazSearchTest extends BaseClass {
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(AmazSearchTest.class);
	AmazLandingPage homePage;
	
	public AmazSearchTest() {
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
	
	 
	@Test(priority=1, dataProvider = "AmazSearchData")
	public void searchProduct(String source, String productDesc) throws InterruptedException
	{
		log.info("Search Product");
		homePage.enterSearchItem(source);
		AmazSearchResultPage searchresultPage = homePage.clickSearchBtn();
		Thread.sleep(2000);
		String actualProductDesc = searchresultPage.getProductDesc();
		System.out.println("Product Description: "+actualProductDesc);
		System.out.println("Expected Product Description: "+productDesc);
		log.info("Expected Product Description: "+productDesc);
		Assert.assertEquals(actualProductDesc, productDesc);
	}
	
	
	@DataProvider
	public Object[][] AmazSearchData()
	{
		Object[][] data= Utilities.getTestDatafromExcel("SearchTestData");
		return data;	
	}

}
