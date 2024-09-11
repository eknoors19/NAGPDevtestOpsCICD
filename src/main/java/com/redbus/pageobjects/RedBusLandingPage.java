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

public class RedBusLandingPage {
	
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//label[@for='src']/../div/text")
	WebElement EDT_fromLocation;
	
	@FindBy(how = How.ID, using = "src")
	WebElement ED_fromLocation;
	
	@FindBy(how = How.ID, using = "dest")
	WebElement ED_DestLocation;
	
	@FindBy(how = How.XPATH, using = "//label[@for='dest']/../div/text")
	WebElement EDT_DestLocation;
	
	@FindBy(how = How.XPATH, using = "//ul[@class='rb_verticals']/li[2]/span[@class='name_rb_vertical' or contains(text(),'Train Tickets')]")
	WebElement LNK_TrainTicket;
	
	@FindBy(how = How.ID, using = "search_button")
	WebElement BTN_SearchBuses;
	
	@FindBy(how = How.XPATH, using = "//div[@id='autoSuggestContainer']//ul/li[1]")
	WebElement AutoSuggest_Source;
	
	@FindBy(how = How.XPATH, using = "//div[@id='autoSuggestContainer']//ul/li[1]")
	WebElement AutoSuggest_Destination;
	
	@FindBy(how = How.CLASS_NAME, using = "labelCalendarContainer")
	WebElement EDT_Date;

	@FindBy(xpath = " //div[contains(@class,'DayNavigator__CalendarHeader')]/div[3]/*[local-name()='svg' and @data-name='Layer 1']")
	WebElement BTN_dateNextButton;

	@FindBy(xpath = " //div[contains(@class,'DayNavigator__CalendarHeader')]/div[1]/*[local-name()='svg' and @data-name='Layer 1']")
	WebElement BTN_datePreviousButton;
	
	@FindBy(xpath = "//div[contains(@class,'DayNavigator__IconBlock')][2]")
	WebElement Label_monthYear;
	
	@FindBy(xpath="//div[@class='rb_main_secondary_item  link']/span[text()='Help']")
	WebElement Lnk_Help;
	
	
	
	public RedBusLandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public SearchTrainPage navigateOnTrainTickets() throws Exception
	{
		LNK_TrainTicket.click();
		Thread.sleep(2000);
		return new SearchTrainPage(driver);
	}
	
	public HelpPage navigateOnHelpPage()
	{
			Lnk_Help.click();
			return new HelpPage(driver);
	}
	
	public String getHomePageTitle()
	{
		
		String title= driver.getTitle();
		return title;
	}
	
	public void enterSource(String source) {
        ED_fromLocation.sendKeys(source);
        AutoSuggest_Source.click();
    }
	
	public void enterDestination(String destination) {
		ED_DestLocation.sendKeys(destination);
        AutoSuggest_Destination.click();
    }
	

    public void selectDate(String date) {
        EDT_Date.click();

        String[] dateParts = date.split("-");
        int targetYear = Integer.parseInt(dateParts[0]);
        System.out.println("Target Year: "+ targetYear);
        int targetMonth = Integer.parseInt(dateParts[1]);
        System.out.println("Target Month: "+ targetMonth);
        int targetDay = Integer.parseInt(dateParts[2]);
        System.out.println("Target Day: "+ targetDay);
        
        String currentMonthYear = Label_monthYear.getText();
        System.out.println("Current Month Year "+ currentMonthYear);
        
        String currentMonth = currentMonthYear.split(" ")[0];
        String currentYears = currentMonthYear.split(" ")[1];
        
        System.out.println("Current Month "+currentMonth);    
        
        currentMonth = getMonthNumber(currentMonth);
        System.out.println("Current Month in Number format "+currentMonth);
        
        System.out.println("Target Month "+targetMonth);
        
        System.out.println("Current Years "+currentYears);
        int newlineIndex = currentYears.indexOf("\n");
        String firstLine = (newlineIndex != -1) ? currentYears.substring(0, newlineIndex) : currentYears;

        int currentYear = Integer.valueOf(firstLine);
        
        System.out.println("Current Year "+currentYear);
        
        int monthDifference = (targetYear - currentYear) * 12 + (targetMonth - Integer.parseInt(currentMonth));
        
        System.out.println("Month Difference "+monthDifference);
        
        if (monthDifference > 0) {
            for (int i = 0; i < monthDifference; i++) {
            	BTN_dateNextButton.click();
            }
        } else if (monthDifference < 0) {
            for (int i = 0; i < Math.abs(monthDifference); i++) {
            	BTN_datePreviousButton.click();
            }
        }
        String targetDayXPath = String.format("//div[contains(@class,'DatePicker__MainBlock')]/div[3]/div/span/div[contains(@class,'DayTiles__CalendarDaysBlock')]/span[text()='%d']", targetDay);
        driver.findElement(By.xpath(targetDayXPath)).click();
    }
    

    public SearchBusPage clickSearchButton() {
    	BTN_SearchBuses.click();
    	return new SearchBusPage(driver);
    	
    }
    
    public static String getMonthNumber(String input2)
    {
    	switch(input2) {
        case "january":
        case "Jan":
            input2 = "1";
        break;

        case "febuary":
        case "Feb":
            input2 = "2";
        break;

        case "march":
        case "Mar":
            input2 = "3";
        break;

        case "april":
        case "Apr":
            input2 = "4";
        break;
        
        case "may":
        case "May":
            input2 = "5";
        break;

        case "june":
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
