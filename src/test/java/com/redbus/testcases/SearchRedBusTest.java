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
import com.redbus.pageobjects.RedBusLandingPage;

public class SearchRedBusTest extends BaseClass {
	
	public WebDriver driver;
	RedBusLandingPage homePage;
	
	public SearchRedBusTest() {
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
	
	 
	@Test(priority=1, dataProvider = "RedBusSearchData")
	public void searchBus(String source, String destination)
	{
		homePage.enterSource(source);
		homePage.enterDestination(destination);
		homePage.selectDate("2024-05-12");
		homePage.clickSearchButton();	
	}
	
	@DataProvider
	public Object[][] RedBusSearchData()
	{
		Object[][] data= Utilities.getTestDatafromExcel("SearchBusTestData");
		return data;	
	}

}
