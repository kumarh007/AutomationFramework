package googletests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assessment.core.TestBase;
import com.assessment.core.pageObjects.GooglePage;

public class Verify_RetriveGooglePageTop10ResultsAndStoreInFileandDisplay extends TestBase{

	
	public GooglePage  _googlePage=null;

	@Test (dataProvider = "testdata",description="Test Case to verify Google search 10 results and get links in file and display links from files ")
	public void testToverifyGoogleForTop10Results(String searchKeyword) {
		_googlePage = new GooglePage(getDriver());
		_googlePage.googlePagePerformSearchAction(searchKeyword);
		_googlePage.retriveTop10ResultsandStoreInFileandDisplay(searchKeyword);
		
	}
	
	
	@DataProvider(name="testdata")
	public Object[][] getTestData(){
		
		return new Object[][] {{"Belagavi"},{"Bengaluru"},{"Mangaluru"},{"Udupi"},{"Dharwad"}};
		
	}

	
	
	
	
}
