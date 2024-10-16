package com.redbus.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.redbus.utility.ExtentReporter;


public class Listeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		try {
			extentReport=ExtentReporter.generateExtentReport();
		} catch (IOException e) {
		
			
		}
		System.out.println("Execution of Project Test Started");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest= extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, testName + " started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + " successfully executed");
		
	}

	//@Override
	/*
	 * public void onTestFailure(ITestResult result) { String testName =
	 * result.getName();
	 * 
	 * System.out.println("ScreenShot Taken"); //@SuppressWarnings("unused")
	 * //WebDriver driver=null; try { WebDriver driver =
	 * (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").
	 * get(result.getInstance()); } catch (IllegalArgumentException |
	 * IllegalAccessException | NoSuchFieldException | SecurityException e) {
	 * 
	 * }
	 * 
	 * 
	 * extentTest.log(Status.INFO, result.getThrowable());
	 * extentTest.log(Status.FAIL, testName + " got failed");
	 * System.out.println(testName + " got failed");
	 * System.out.println("Reason for Failure: "+result.getThrowable()); }
	 */

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		System.out.println("Execution of Project Test Finished");
	}

}
