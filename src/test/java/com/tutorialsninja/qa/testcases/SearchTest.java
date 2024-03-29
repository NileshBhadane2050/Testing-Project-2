package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;
import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base {
	
	//Variable
		// Declaration of WebDriver variable[driver] to interacts with the browser
		public WebDriver driver;
		SearchPage searchPage;
		//Methods
		public SearchTest () {
			super();
		}
		
		@BeforeMethod
		public void setep() {
			driver = initializeBrowserAndOpenApplicatiOnURL(prop.getProperty("browserName"));
			 searchPage = new SearchPage(driver); // Initialize the searchPage object
		
		}
		
		@AfterMethod
		public void teardown() {
			driver.quit();
		}
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		 HomePage homePage = new HomePage(driver);
		 homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		 searchPage = homePage.clickOnSearchButton(); 
		 Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not Displayed in the Search results");
		
	}
	
	@Test (priority = 2)
	public void verifySearchWithInvalidProduct() {
		 HomePage homePage = new HomePage(driver);
		 homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		 searchPage = homePage.clickOnSearchButton();
		 String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		 Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No Product in search result is not displayed");	
	}
	
	@Test (priority = 3)
	public void verifySearchWithoutAnyProduct() {
		
		 HomePage homePage = new HomePage(driver);
		 searchPage = homePage.clickOnSearchButton();
		 String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		 Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No Product in search result is not displayed");	
	
	}
	@Test (priority = 4)
	public void verifyProductAddedSucessfuly() throws InterruptedException {
		
		 HomePage homePage = new HomePage(driver);
		 homePage.searchForAProduct("HP");
		 homePage.addWishButton();
		 String ExpectedMessagee = "Success: You have added HP LP3065 to your product comparison!\n"+ "×";
		 String ActualMessage = searchPage.retriveWishListAddMessageText();
		 Assert.assertEquals(ActualMessage, ExpectedMessagee,"No wishList Message is shown");	 
	}

}
