package com.redbus.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.redbus.utility.Utilities;

public class SearchTrainResultPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='search_tupple_content'])[1]")
	WebElement SR_RedRailTrains;
	
	@FindBy(xpath = "//div[contains(@class,'lts_last_updated')]")
	WebElement Txt_LastUpdated;
	
	public SearchTrainResultPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isTrainsDisplayed() {
		try {
			return Utilities.isElementDisplayed(SR_RedRailTrains);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean islastStatusUpdated()
	{
		if(Txt_LastUpdated.isDisplayed())
			return true;
		else
			return false;
	}
	
}
