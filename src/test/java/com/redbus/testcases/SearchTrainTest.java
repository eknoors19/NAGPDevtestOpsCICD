package com.redbus.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.redbus.base.BaseClass;
import com.redbus.pageobjects.RedBusLandingPage;
import com.redbus.pageobjects.SearchTrainPage;
import com.redbus.pageobjects.SearchTrainResultPage;
import com.redbus.utility.Utilities;

public class SearchTrainTest extends BaseClass {
	
	public WebDriver driver;
	RedBusLandingPage homePage;
	SearchTrainPage sthomepage;
	SearchTrainResultPage redrailsearchresultpage;
	
	public SearchTrainTest() {
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
	public void landOnRedRail() throws Exception
	{
		sthomepage= homePage.navigateOnTrainTickets();
		String currentLogoText= sthomepage.getRedRailLogoText();
		Assert.assertEquals(currentLogoText,dataProp.getProperty("redRailLogoText"));
	}
	
	@Test(priority=2)
	public void searchTrainWithoutDate() throws Exception
	{
		sthomepage= homePage.navigateOnTrainTickets();
		sthomepage.enterSource(dataProp.getProperty("trainSource"));
		Thread.sleep(5000);
		sthomepage.enterDestination(dataProp.getProperty("trainDestination"));
		//sthomepage.selectDate("2024-06-20");
		sthomepage.clickFreeCancellation();
		redrailsearchresultpage= sthomepage.clickSearchTrain();
		Assert.assertTrue(redrailsearchresultpage.isTrainsDisplayed(), "No Trains Found");
		
	}
	
	@Test(priority=3)
	public void searchLiveTrainstatus() throws Exception
	{
		sthomepage= homePage.navigateOnTrainTickets();
		sthomepage.selectRBLiveTrainStatus();
		sthomepage.fillTrainNumber(dataProp.getProperty("trainNumber"));
		redrailsearchresultpage= sthomepage.clickCheckStatus();
		Assert.assertTrue(redrailsearchresultpage.islastStatusUpdated(), "No Status Found");
	
	} 
	

}
