package com.redbus.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=10;
	
	
	
	public static void navigateToLogin(WebDriver driver) throws Exception
	{
		String redBusWindow = driver.getWindowHandle();
		//String accountDropMenu = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS'] .books-desc")).getText();
		//System.out.print(accountDropMenu);
		WebElement accountDropMenu = driver.findElement(By.xpath("//span[text()='Account']"));
		accountDropMenu.click();
		WebElement loginSignUpOption = driver.findElement(By.xpath("//li[@id='user_sign_in_sign_up']/span"));
		loginSignUpOption.click();
		WebElement iframeP= driver.findElement(By.xpath("//iframe[@class='modalIframe']"));
		driver.switchTo().frame(iframeP);
		WebElement iframeS= driver.findElement(By.xpath("//iframe[@title='Sign in with Google Button']"));
		driver.switchTo().frame(iframeS);
		Thread.sleep(3000);
		WebElement signInWithGoogle = driver.findElement(By.xpath("//div[@id='container']//span[text()= 'Sign in with Google']"));
		//WebElement signInWithGoogle = driver.findElement(By.xpath("//div[@id='container']//span[contains(text()= 'Sign in')]"));
		
		signInWithGoogle.click();
		Set<String> windowIds = driver.getWindowHandles();
		
		for(String windowId: windowIds) {
			driver.switchTo().window(windowId);
			if(driver.getCurrentUrl().equals("https://www.redbus.in/")) {}
			else{
				break;
			}
		}	
		
	}
	
	/*
	 * public static String captureScreenshot(WebDriver driver, String testName) {
	 * File srcScreenshot=
	 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); String
	 * destinationScreenshotPath=
	 * System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png"; File
	 * destinationScreenshot = new File (destinationScreenshotPath); try {
	 * FileHandler.copy(srcScreenshot, destinationScreenshot); } catch (IOException
	 * e) { e.printStackTrace(); } return destinationScreenshotPath; }
	 */
	
	

}
