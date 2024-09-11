package com.redbus.pageobjects;

import java.time.Duration;
import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazLandingPage {
	
	WebDriver driver;

	
	
	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	WebElement TXT_SearchText;
	
	@FindBy(how = How.ID, using = "nav-search-submit-button")
	WebElement BTN_SearchBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class='s-suggestion-container']/div[1]")
	WebElement AutoSuggest_Source;
	
		
	public AmazLandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getHomePageTitle()
	{
		
		String title= driver.getTitle();
		return title;
	}
	
	
    
    public void enterSearchItem(String source) {
        TXT_SearchText.sendKeys(source);
        //AutoSuggest_Source.click();
    }
    
    public AmazSearchResultPage clickSearchBtn() {
    	BTN_SearchBtn.click();
    	return new AmazSearchResultPage(driver);
    	
    }
    
    
	
}
