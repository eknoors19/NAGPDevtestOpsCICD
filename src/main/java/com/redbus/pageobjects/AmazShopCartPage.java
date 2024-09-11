package com.redbus.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class AmazShopCartPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using="//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//span[@class='a-size-medium a-color-base a-text-normal'][contains(text(),'Samsung Galaxy S24 Ultra 5G AI Smartphone (Titaniu')]")
	WebElement Lbl_ProductDesc;
	
	@FindBy(how = How.XPATH, using="//button[@id='a-autoid-1-announce']")
	WebElement Btn_AddToCart;
	
	@FindBy(how = How.XPATH, using="//span[@class='nav-cart-icon nav-sprite']")
	WebElement Btn_NavigateToCart;
	
	@FindBy(how = How.XPATH, using="//span[@class='a-truncate-cut'][contains(text(),'Samsung Galaxy S24 Ultra 5G AI Smartphone (Titaniu')]")
	WebElement Lbl_ShopCartDesc;
	
	public AmazShopCartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProductDesc()
	{
		String text = Lbl_ProductDesc.getText();
		return text;
	}
	
	public String getShopCartDesc()
	{
		String text = Lbl_ShopCartDesc.getText();
		return text;
	}
	
	
	
}
