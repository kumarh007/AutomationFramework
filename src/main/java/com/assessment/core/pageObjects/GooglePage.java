package com.assessment.core.pageObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.assessment.core.CommonApi;
import com.assessment.utility.FileUtility;

public class GooglePage {

	WebDriver driver;

	@FindBy(name = "q")
	private WebElement googleInputBox;

	@FindBy(xpath = "(//input[@name='btnK'])[1]")
	private WebElement googleSearchBtn;

	@FindBy(xpath = "//div[@class='g']/descendant::h3/ancestor::a")
	private List<WebElement> alist;

	public GooglePage(WebDriver _webDriver) {
		this.driver = _webDriver;
		PageFactory.initElements(_webDriver, this);
	}

	/**
	 * googlePagePerformSearchAction method used to perform google page search
	 * action.
	 * 
	 * @param searchKeyWord String type used as search word to be searched
	 */
	public void googlePagePerformSearchAction(String searchKeyWord) {
		
		WebDriverWait wwait = new WebDriverWait(driver, 20);
		wwait.until(ExpectedConditions.elementToBeClickable(googleInputBox));
		googleInputBox.clear();
		googleInputBox.sendKeys(searchKeyWord);
		googleInputBox.sendKeys(Keys.RETURN);
	}

	/**
	 * retriveTop10ResultsandStoreInFileandDisplay method to retrive top 10 results
	 * and store in file and display file contents 
	 * @param searchKeyword String type used as search word to be searched
	 */
	public void retriveTop10ResultsandStoreInFileandDisplay(String searchKeyword) {

		List<String> dataArray = retriveLinks();
		FileUtility.writeToFile(searchKeyword, dataArray);
		FileUtility.ReadFromFile(searchKeyword);

	}

	/**
	 * retriveTop10ResultsandOpenLinkCountKeywordOccurence iterates through all href
	 * link and opens all herf link in sequence and and counts all occurrence of
	 * search words
	 * 
	 * @param searchKeyword searchKeyword String type used as search word to be searched
	 */
	public void retriveTop10ResultsandOpenLinkCountKeywordOccurence(String searchKeyword) {
		
		List<String> dataArray = retriveLinks();
		int occurrence = 0;
		HashMap<String, Integer> hashmapOccurrence = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			dataArray.add(alist.get(i).getAttribute("href"));
			String url = alist.get(i).getAttribute("href");
			String currentWindow = driver.getWindowHandle();
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get(url);
			System.out.print("\nWindow Tile after switch: " + driver.getTitle());
			occurrence = driver.findElements(By.xpath("//*[contains(text(),'" + searchKeyword + "')]")).size();
			hashmapOccurrence.put(url, occurrence);
			driver.close();
			driver.switchTo().window(currentWindow);

		}

		System.out.print("Number of Occurrence Of Keywords in New Links:\n ");
		Iterator hmIterator = hashmapOccurrence.entrySet().iterator();

		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			int count = ((int) mapElement.getValue());
			String message ="In Links Page : " + mapElement.getKey() + " |Keyword  Occurred  :" + count;
			System.out.println(message);
			Reporter.log(message);
		}

	}

	/**
	 * retriveLinks method used to fetch all links of top 10 search
	 * @return ArrayList of href attribute
	 */
	public List<String> retriveLinks() {

		List<String> dataArray = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			dataArray.add(alist.get(i).getAttribute("href"));
		}

		return dataArray;

	}

}
