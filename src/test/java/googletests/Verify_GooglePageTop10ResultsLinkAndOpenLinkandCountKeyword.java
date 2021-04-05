package googletests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assessment.core.TestBase;
import com.assessment.core.pageObjects.GooglePage;

public class Verify_GooglePageTop10ResultsLinkAndOpenLinkandCountKeyword extends TestBase{


	public GooglePage  _googlePage=null;
	
	
	@Test (dataProvider = "testdata",description="Test Case to Verify Top 10 results of keyword search and open search links and count occurrenc of keyword in new link page")
	public void testToverifyGooglePageTop10ResultsLinkAndOpenLinkandCountKeyword(String searchKeyword) {
		_googlePage = new GooglePage(getDriver());
		_googlePage.googlePagePerformSearchAction(searchKeyword);
		_googlePage.retriveTop10ResultsandOpenLinkCountKeywordOccurence(searchKeyword);
		
	}
	
	
	
	
	@DataProvider(name="testdata")
	public Object[][] getTestData(){
		
		return new Object[][] {{"Oracle"}};
	}

	
	
	
}
