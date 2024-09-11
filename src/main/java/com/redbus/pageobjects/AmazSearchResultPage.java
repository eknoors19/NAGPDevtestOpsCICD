package com.redbus.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class AmazSearchResultPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using="//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//span[@class='a-size-medium a-color-base a-text-normal'][contains(text(),'Samsung Galaxy S24 Ultra 5G AI Smartphone (Titaniu')]")
	WebElement Lbl_ProductDesc;
	
	@FindBy(how = How.XPATH, using="//button[@id='a-autoid-2-announce']")
	WebElement Btn_AddToCart;
	
	@FindBy(how = How.XPATH, using="//div[@id='nav-cart-count-container']")
	WebElement Btn_NavigateToCart;
	
	public AmazSearchResultPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProductDesc()
	{
		String text = Lbl_ProductDesc.getText();
		return text;
	}
	
	public void clickAddToCartBtn() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Btn_AddToCart);
		Thread.sleep(3000);
		Btn_AddToCart.click();
    	
    }
	
	public AmazShopCartPage clickCartBtn() {
		Btn_NavigateToCart.click();
    	return new AmazShopCartPage(driver);
    	
    }
	
	
	
	
}
