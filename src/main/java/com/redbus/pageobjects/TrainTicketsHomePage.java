package com.redbus.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TrainTicketsHomePage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'search-btn')]")
	WebElement Btn_searchTrains;
	
	@FindBy(how = How.XPATH, using = "//div[@class='search_tupple_content']")
	WebElement Lbl_srchTrainsResult;
	
	@FindBy(how = How.XPATH, using = "//div[@class='logo']/span")
	WebElement Txt_redRailHeader;
	
	public TrainTicketsHomePage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public String TrainTicketPageTitle()
	{
		
		String title= driver.getTitle();
		return title;
	}
	
	
	

}
