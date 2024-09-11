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
import com.redbus.pageobjects.HelpPage;
import com.redbus.pageobjects.RedBusLandingPage;
import com.redbus.utility.Utilities;

public class HelpTest extends BaseClass {
	
	public WebDriver driver;
	RedBusLandingPage homePage;
	HelpPage helpPage;
	
	public HelpTest() {
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
	public void landOnHelpWidow()
	{
		String mainWindowHandle= driver.getWindowHandle();
		helpPage= homePage.navigateOnHelpPage();
		
		Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        
        String helpWindowTitle = driver.getTitle();
        System.out.println("HelpWindowTitle: "+ helpWindowTitle);
        if (helpWindowTitle.contains("Care")) {
            System.out.println("Successfully navigated to the Help window.");
            Assert.assertTrue(helpPage.isHelpPgDisplayed(),"Help Care Page is not displayed");
        } else {
            System.out.println("Failed to navigate to the Help window.");
        }
		 driver.close(); driver.switchTo().window(mainWindowHandle);
		
	}
	
	@Test(priority=2)
	public void selectHelpTopicBus()
	{	
		String mainWindowHandle= driver.getWindowHandle();
		helpPage= homePage.navigateOnHelpPage();
		
		Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }       
        String helpWindowTitle = driver.getTitle();
        System.out.println("HelpWindowTitle: "+ helpWindowTitle);
        String expectedFAQHeaderText = "FAQs";
        
        if(helpPage.isHelpPgDisplayed())
        {
	        if (helpWindowTitle.contains("Care")) {
	        	System.out.println("Successfully navigated to the Help window.");
	        		helpPage.clickBusTopic();	        		
	        		if(helpPage.isHelpTopicDisplayed())
		        	{
	        			System.out.println("Help Page Topics displayed");
	        				if(helpPage.clickHelpQuestion(dataProp.getProperty("helpTopicBusQuestion")))
	        				{
	        					System.out.println("Help Question is clicked");
	        					String actualFAQHeaderText= helpPage.retrieveFAQHeaderText();
	        					System.out.println("Actual actualFAQHeaderText "+ actualFAQHeaderText );
	        					Assert.assertEquals(actualFAQHeaderText,expectedFAQHeaderText, "FAQ's header Text is not matching");
	        				}
	        				else
	        					Assert.assertFalse(false, "Question is not clicked");
		        		}	        	
	        } else {
	            System.out.println("Failed to navigate to the Help window.");
	            Assert.assertFalse(false, "Help Topic is not clicked. Please Enter Bus Tickets");
	        }
        }    
	}	
	
	@Test(priority=3)
	public void selectHelpTopicTrain()
	{	
		String mainWindowHandle= driver.getWindowHandle();
		helpPage= homePage.navigateOnHelpPage();
		
		Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }       
        String helpWindowTitle = driver.getTitle();
        System.out.println("HelpWindowTitle: "+ helpWindowTitle);
        String expectedFAQHeaderText = "FAQs";
        
        if(helpPage.isHelpPgDisplayed())
        {
	        if (helpWindowTitle.contains("Care")) {
	        	System.out.println("Successfully navigated to the Help window.");
	        		helpPage.clickTrainTopic();	        		
	        		if(helpPage.isHelpTopicDisplayed())
		        	{
	        			System.out.println("Help Page Topics displayed");
	        				if(helpPage.clickHelpQuestion(dataProp.getProperty("helpTopicTrainQuestion")))
	        				{
	        					System.out.println("Help Question is clicked");
	        					//Assert.assertTrue(helpPage.isFAQHeaderDisplayed(),"FAQ Header is not displayed");
	        					String actualFAQHeaderText= helpPage.retrieveFAQHeaderText();
	        					System.out.println("Actual actualFAQHeaderText "+ actualFAQHeaderText );
	        					Assert.assertEquals(actualFAQHeaderText,expectedFAQHeaderText, "FAQ's header Text is not matching");
	        				}
	        				else
	        					Assert.assertFalse(false, "Question is not clicked");
		        		}	        	
	        } else {
	            System.out.println("Failed to navigate to the Help window.");
	            Assert.assertFalse(false, "Help Topic is not clicked. Please Enter Train Tickets");
	        }
        } 
	}

}
