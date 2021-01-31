package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	ExtentReports report = new ExtentReports(System.getProperty("user.dir")
			+ "\\test-output");
	ExtentTest logger;
	
	public BaseTest(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.property");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void initialization(ITestContext ctx) throws Exception {
		
			// Initialize web driver
				String browserName = ctx.getCurrentXmlTest().getParameter("browser");
				driver = WebDriverSetup.getDriver(ctx);
				if (driver == null) {
					try {
						throw new Exception("unable to fetch browser for: " + browserName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				logger = report.startTest("Verify Flipkart Search");
				//reportLogger("Verify Flipkart Search");
	}
	
//	protected void reportLogger(String message) {
//        Logs.info(message);
//        Reporter.log(message);
//    }
	
}
