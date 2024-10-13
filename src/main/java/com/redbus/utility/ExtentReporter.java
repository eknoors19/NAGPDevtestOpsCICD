package com.redbus.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() throws IOException
	{
		ExtentReports extentReport = new ExtentReports();
		Date d=new Date();
        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
        System.out.println(" File Name: "+fileName);
        
        String userDirectory = System.getProperty("user.dir");
		String srcDirPath = userDirectory +"//ExtentReports//CurrentTestResults"; 
		String destDirPath = userDirectory + "//ExtentReports//ArchivedTestResults";
		  
		File src = new File(srcDirPath); 
		File dest = new File(destDirPath); 
		  if(!src.exists())
		  {
			  src.mkdir();
		  }
		  if(src.exists() && src.isDirectory()) 
		  { 
			  if (!dest.exists()) 
			  { dest.mkdir(); }
			  File[] content = src.listFiles(); 
				
			  for (int i = 0; i < content.length; i++) {
				    String name = content[i].getName(); 
				    File destDir = new File(destDirPath + "//" + name); 
				    
				    // Capture the boolean return value
				    boolean isRenamed = content[i].renameTo(destDir);
				    
				    // Check if renaming was successful
				    if (isRenamed) {
				        System.out.println("Successfully renamed: " + name);
				    } else {
				        System.out.println("Failed to rename: " + name);
				        // Handle the failure case here (e.g., logging, retry logic, etc.)
				    }
				}

		  }
		 
	    File extentReportFile = new File(userDirectory+ "\\ExtentReports\\CurrentTestResults\\extentReport_"+fileName);
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("REDBUS Test Automation Results");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:sss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(userDirectory+"\\src\\main\\java\\com\\redbus\\config\\config.properties");
		
		FileInputStream fisConfigProp =null;
		try {
			 fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			
		}
		finally
        {
            System.out.println("finally block executed");
            if (fisConfigProp != null)
            	fisConfigProp.close();
        }
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
	

}
