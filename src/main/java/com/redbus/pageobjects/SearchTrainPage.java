package com.redbus.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.redbus.utility.Utilities;



public class SearchTrainPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//div[@class='logo']/span")
	WebElement Lbl_RedTrainLogoTxt;
	
	@FindBy(how = How.XPATH, using = "//p[text()='Live Train Status']/..//div[contains(@class,'radio-button-inner')]")
	WebElement RB_LiveTrainStatus;
	
	@FindBy(how = How.XPATH, using = "//input[@name='pnrNo']")
	WebElement EDT_TrainNumber;
	
	@FindBy(how = How.XPATH, using = "//div[@class='lts_solr_wrap_main']/div/b")
	WebElement AutoSuggest_TrainNum;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Check Status')]")
	WebElement Btn_CheckStatus;
	 
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'search trains')]")
	WebElement Btn_SearchTrain;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'lts_running_status_wrap')]/div/div[@class='blue_bckgnd']")
	WebElement Txt_CurrentStatus;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@class,' search-label') and @for='src']")
	WebElement Lbl_RRSrcStation;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@class,' search-label') and @for='dst']")
	WebElement Lbl_RRDestStation;
	
	@FindBy(how = How.ID, using = "src")
	WebElement EDT_RRSrcStation;
	
	@FindBy(how = How.XPATH, using = "//div[@class='solr_results_block']/div[1]")
	WebElement AutoSuggest_SrcStation;
	
	@FindBy(how = How.XPATH, using ="(//div[@class='solr_results_block']/div[1]/div[@class='stn_code'])[1]")
	WebElement AutoSuggest_SrcStationOne;
	
	@FindBy(how = How.XPATH, using = "//div/input[@id='dst' and @class='search-input']")
	WebElement EDT_RRDestStation;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='solr_results_block']//div[@class='stn_code'])[1]")
	WebElement AutoSuggest_DestStation;
	
	@FindBy(how = How.XPATH, using = "//div[@class='fc_optin_main_wrap_home']/div[2]")
	WebElement ChkBox_FreeCancel;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'home_calendar')]/img")
	WebElement EDT_RRDate;
	
	@FindBy(xpath = "//div[contains(@class,'sc-gZMcBi fSiRuE')]/div[3]/*[local-name()='svg' and @data-name='Layer 1']")
	WebElement BTN_dateNextRRButton;
	
	@FindBy(xpath = "//div[contains(@class,'sc-gZMcBi fSiRuE')]/div[1]/*[local-name()='svg' and @data-name='Layer 1']")
	WebElement BTN_datePrevRRButton;
	
	@FindBy(xpath = "//div[contains(@class,'sc-gZMcBi fSiRuE')]/div[2]")
	WebElement Label_monthRRYear;
	
	public SearchTrainPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getRedRailLogoText()
	{
		return (Lbl_RedTrainLogoTxt.getText());
	}
	
	public boolean selectRBLiveTrainStatus() throws Exception
	{
		RB_LiveTrainStatus.click();
		Thread.sleep(3000);
		if(RB_LiveTrainStatus.isSelected())
			return true;
		else
			return false;
	}
	
	public void fillTrainNumber(String trainNumber) throws Exception
	{
		EDT_TrainNumber.sendKeys(trainNumber);
		Thread.sleep(6000);
		AutoSuggest_TrainNum.click();	
	}
	
	public SearchTrainResultPage clickSearchTrain()
	{
		if(Btn_SearchTrain.isEnabled())
			Btn_SearchTrain.click();
		
		return new SearchTrainResultPage(driver);
	
	}
	
	public SearchTrainResultPage clickCheckStatus()
	{
		if(Btn_CheckStatus.isEnabled())
			Btn_CheckStatus.click();
		
		return new SearchTrainResultPage(driver);
	}
	
	public void enterSource(String source) throws Exception {
  
		Lbl_RRSrcStation.click();
		Thread.sleep(2000);
		EDT_RRSrcStation.sendKeys(source);
		Thread.sleep(5000);
		AutoSuggest_SrcStationOne.click();
    }
	
	public void enterDestination(String destination) throws InterruptedException {
		  
		Utilities.isElementDisplayed(Lbl_RRDestStation);
		Lbl_RRDestStation.click();
		EDT_RRDestStation.sendKeys(destination);
		Thread.sleep(2000);
		AutoSuggest_DestStation.click();
    }
	
	public void clickFreeCancellation()
	{
		ChkBox_FreeCancel.click();
	}
	
	public void selectDate(String date) throws InterruptedException {
	   	Utilities.isElementDisplayed(EDT_RRDate);
	   	System.out.println("Element displayed or not");
	   	EDT_RRDate.click();
	   	Thread.sleep(5000);
	   	
        String[] dateParts = date.split("-");
        int targetYear = Integer.parseInt(dateParts[0]);
        int targetMonth = Integer.parseInt(dateParts[1]);
        int targetDay = Integer.parseInt(dateParts[2]);
        
        Utilities.waitForElementClickable(driver, "//div[contains(@class,'sc-gZMcBi fSiRuE')]/div[2]");
        String currentMonthYear = Label_monthRRYear.getText();
        System.out.println("Current Month Year "+ currentMonthYear);
        
        String currentMonth = currentMonthYear.split(" ")[0];
        String currentYears = currentMonthYear.split(" ")[1];
        currentMonth = getMonthNumber(currentMonth);
        
        int newlineIndex = currentYears.indexOf("\n");
        String firstLine = (newlineIndex != -1) ? currentYears.substring(0, newlineIndex) : currentYears;
        int currentYear = Integer.valueOf(firstLine);
        int monthDifference = (targetYear - currentYear) * 12 + (targetMonth - Integer.parseInt(currentMonth));
        
        if (monthDifference > 0) {
            for (int i = 0; i < monthDifference; i++) {
            	BTN_dateNextRRButton.click();
            }
        } else if (monthDifference < 0) {
            for (int i = 0; i < Math.abs(monthDifference); i++) {
            	BTN_datePrevRRButton.click();
            }
        }
        String targetDayXPath = String.format("//span[contains(@class,'sc-dnqmqq dbIXmq')]/div/span[text()='%d']", targetDay);
        driver.findElement(By.xpath(targetDayXPath)).click();
	}
	   
	   public static String getMonthNumber(String input2)
	    {
	    	switch(input2) {
	        case "january":
	        case "Jan":
	            input2 = "1";
	        break;

	        case "FEB":
	        case "Feb":
	            input2 = "2";
	        break;

	        case "MAR":
	        case "Mar":
	            input2 = "3";
	        break;

	        case "APR":
	        case "Apr":
	            input2 = "4";
	        break;
	        
	        case "MAY":
	        case "May":
	            input2 = "5";
	        break;

	        case "JUN":
	        case "Jun":
	            input2 = "6";
	        break;

	        case "july":
	        case "Jul":
	            input2 = "7";
	        break;

	        case "august":
	        case "Aug":
	            input2 = "8";
	        break;

	        case "september":
	        case "Sep":
	        case "Sept":
	            input2 = "9";
	        break;

	        case "october":
	        case "Oct":
	            input2 = "10";
	        break;

	        case "november":
	        case "Nov":
	            input2 = "11";
	        break;

	        case "december":
	        case "Dec":
	            input2 = "12";
	        break;
	        }
			return input2;
	    	
	    }
	
}
