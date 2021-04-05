package com.assessment.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	private static DriverFactory driverFactoryInstance =null;
	
	public String currentDir = System.getProperty("user.dir");
	public String fileSeparator =System.getProperty("file.separator");
	
	private DriverFactory() {
		
	}
	
	public static DriverFactory getInstance() {
		
		if(driverFactoryInstance==null) {
			driverFactoryInstance = new DriverFactory();
		}
		
		return driverFactoryInstance;
	}

	
	private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver(String browserType) throws InterruptedException {
		
		if(threadLocal.get()==null) {
			switch (browserType) {
			
			case "CHROME":
					System.setProperty("webdriver.chrome.driver", currentDir+fileSeparator+"src"+fileSeparator+"test"+fileSeparator+"resources"+fileSeparator+"libraries"+fileSeparator+"chromedriver.exe");
					threadLocal.set(new ChromeDriver());
					Thread.sleep(100);
					break;

			default:
				break;
			}
		}
		
		return threadLocal.get();
	}
	
	public static void closeBrowser() {
		driverFactoryInstance=null;
		if(threadLocal.get()!=null)
			threadLocal.get().close();
	}
	
	public WebDriver getWebDriver() {
			return threadLocal.get();
	}
	
}
