package com.redbus.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class SearchBusResultPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'sort-sec clearfix onward')]/div/a")
	WebElement Lst_SortByOptions;
	
	@FindBy(how = How.XPATH, using = "//div[@class='D136_main D136_breadcumb']/ul/li[3]/a/span")
	WebElement Txt_BreadcumbBus;
	
	@FindBy(how = How.XPATH, using = "//div[@class='sort-sec clearfix onward ']/div//span[contains(@class,'busFound')]")
	WebElement Txt_CountBusFound;
	
	@FindBy(how = How.XPATH, using="//div[@class='details']/ul[@class='list-chkbox']/li/input[contains(@id,'bt')]")
	WebElement Lst_BusTypes;
	
	@FindBy(how = How.XPATH, using="//div[@class='error-view oops-page new_oops_container']/img[@class='oopsImage']")
	WebElement Lbl_NoBusFound;
	
	@FindBy(how = How.XPATH, using="//span[@class='f-bold busFound']")
	WebElement Lbl_BusFound;
	
	public SearchBusResultPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void SelectBusType(String bustype)
	{
		List<WebElement> chkboxes = driver.findElements(By.xpath("//div[@class='details']/ul[@class='list-chkbox']/li/input[contains(@id,'bt')]"));
		
		int size= chkboxes.size();
		
		for(int i=0; i < size; i++)
		{
			chkboxes.get(i).click();
		}
	}
	
	public void fillBusPreferences(String busClass) 
	{
		String busTypeText=null;
		
		List<WebElement> busClassRBT = driver.findElements(By.xpath("//div[contains(@class,'sort-sec clearfix onward')]/div/a"));
		for (int i = 0; i < busClassRBT.size(); i++) {
			System.out.println("Sort By Prefer: "+ busClassRBT.get(i).getAttribute("text"));
			
			if (busClassRBT.get(i).getAttribute("text").equalsIgnoreCase(busClass)) 
			{
				System.out.println("Clicking on Bus Sort Type:" + busClassRBT.get(i).getText());
				busTypeText= busClassRBT.get(i).getText();
				busClassRBT.get(i).click();
				
			}
		}
	}
	
	public void sortByPrefernce(String sortByOption)
	{
		String busTypeText=null;
		List<WebElement> bussortByRefer = driver.findElements(By.xpath("//div[@class='details']/ul[@class='list-chkbox']/li/label[contains(@for,'bt') and @class='cbox-label']"));
		for (int i = 0; i < bussortByRefer.size(); i++) {
			
			if (bussortByRefer.get(i).getAttribute("title").equalsIgnoreCase(sortByOption)) 
			{
				System.out.println("Bus Class Type:" + bussortByRefer.get(i).getText());
				System.out.println("Hello To split");
				bussortByRefer.get(i).click();
				
				if(driver.findElement(By.xpath("//div[contains(@class,'sort-sec clearfix onward')]//a[contains(text(),'"+busTypeText+"')]/i[contains(@class,'arrow')]")).isDisplayed())
					busTypeText="true";
				else
					busTypeText="false";
					
				System.out.println("Latest Selection "+ busTypeText);	
			}
		}

	}
	
}
