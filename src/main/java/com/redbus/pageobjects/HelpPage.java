package com.redbus.pageobjects;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.redbus.utility.Utilities;

public class HelpPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//div[@class='red-title' and contains(text(),'redBus')]")
	WebElement Lbl_HelpPage;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='Issue-with-this-booking']")
	WebElement Lbl_HelpTopic;
	
	@FindBy(how = How.XPATH, using = "//div[@class='issue-list-block']/div[2]/div[@class='listitems']")
	WebElement Lst_OtherTopics;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Bus Tickets')]")
	WebElement FAQ_BusHelpTopic;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Train Tickets')]")
	WebElement FAQ_TrainHelpTopic;
	
	@FindBy(how = How.XPATH, using = "//div[@class='faqCard']//div[@class='subrow2']/div")
	WebElement Lst_SelectTopics;
	
	@FindBy(how = How.TAG_NAME, using = "iframe")
	WebElement framesz;
	
	@FindBy(how = How.XPATH, using = "//div[@class='header-with-trip']/div[@class='ripple']")
	WebElement Btn_BackArrow;
	
	@FindBy(how = How.XPATH, using = "//div[@class='listitems']//span[@class='parentText']")
	WebElement Lst_HlpTpicOptions;
	
	@FindBy(how = How.XPATH, using = "//div[@class='questionBackground']//p")
	WebElement Lst_HlpTpicFAQs;
	
	@FindBy(how = How.XPATH, using = "//div[@class='questionListContainer']/div[@class='header']")
	WebElement Lbl_HeaderFAQs;
	
	public HelpPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
 
	public String getTextHelpPageLabel() {
		return Lbl_HelpPage.getText();
	}
	
	public boolean isHelpTopicDisplayed() {
		try {
			return Utilities.isElementPresent(Lbl_HelpTopic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isHelpPgDisplayed() {
		try {
			return Utilities.isElementPresent(Lbl_HelpPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isFAQHeaderDisplayed() {
		try {
			return Utilities.isElementPresent(Lbl_HeaderFAQs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String retrieveFAQHeaderText()
	{
		String FAQHeaderText = Lbl_HeaderFAQs.getText();
		System.out.println("FAQHeaderText: "+FAQHeaderText);
		return FAQHeaderText;
	}
	
	public void clickBusTopic() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().frame(0);
		if(Utilities.isElementPresent(FAQ_BusHelpTopic))
		{
			FAQ_BusHelpTopic.click();		
		}
		
	}
	
	public void clickTrainTopic() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().frame(0);
		if(Utilities.isElementPresent(FAQ_TrainHelpTopic))
		{
			FAQ_TrainHelpTopic.click();		
		}
		
	}

	public boolean clickHelpQuestion(String questionName) {
		Boolean clickStatus= false;
		
		String questionText = questionName;
		System.out.println("Question name is: "+ questionText );
		List<WebElement> questions = driver.findElements(By.xpath("//div[@class='listitems']//span[@class='parentText']"));
		System.out.println("Numbers of Questions: "+ questions.size());
		for (WebElement el : questions) {
			System.out.println("Questions Text: "+ el.getText());
			if (el.getText().equalsIgnoreCase(questionText)) {
				System.out.println("click ques");
				el.click();
				System.out.println(" ques clicked");
				clickStatus= true;
				break;
			}
		}
		return clickStatus;
	}	
	
}
