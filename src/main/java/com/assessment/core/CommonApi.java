package com.assessment.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CommonApi   {

	public static String currentDir = System.getProperty("user.dir");
	public static String fileSeparator =System.getProperty("file.separator");
	
	public static void waitForElement(WebDriver driver ,By byfinder) {
		
		WebDriverWait wb = new WebDriverWait(driver, 15);
		wb.until(ExpectedConditions.visibilityOf(driver.findElement(byfinder)));
	}
	

	
	public  void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(currentDir+ fileSeparator+"Screenshots"+fileSeparator + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
