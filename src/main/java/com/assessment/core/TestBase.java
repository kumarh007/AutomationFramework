package com.assessment.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.assessment.core.pageObjects.GooglePage;
import com.assessment.utility.ReadConfig;
public class TestBase {
	
	public  WebDriver _webDriver=null;
	
	public static Logger logger;
	
	public static CommonApi _commonApi;
	
	public static ReadConfig readConfig;
	
	public GooglePage  _googlePage=null;
	
	public static String currentDir = System.getProperty("user.dir");
	public static String fileSeparator =System.getProperty("file.separator");

	@BeforeClass(alwaysRun=true)
	@Parameters({"browserName"})
	public void  init(String browserName) throws InterruptedException {
		logger = Logger.getLogger("AutomationFramework");
		PropertyConfigurator.configure("Log4j.properties");
		readConfig = new ReadConfig();
		launchBrowser(browserName);
		_commonApi = new CommonApi();
				
	}
	
	
	/**
	 * 
	 * @param browserName
	 * @return
	 * @throws InterruptedException 
	 */
	public void launchBrowser(String browserName ) throws InterruptedException {
		String url = readConfig.getApplicationURL();
		_webDriver = DriverFactory.getInstance().getDriver(browserName);
		_webDriver.get(url);
		_webDriver.manage().window().maximize();
		_webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		_webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		logger.info("Launched Browser:"+url);
		
		
	}
	
	
	public WebDriver getDriver(){
		return _webDriver;
	}
	
	
	public  void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(currentDir+ fileSeparator+"Screenshots"+fileSeparator + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	@AfterTest()
	public void tearDown() {
		DriverFactory.closeBrowser();
	}
}
