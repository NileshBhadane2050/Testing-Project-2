package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LogoutPage;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;

public class LogoutTest extends Base {
	
	//Methods
	public LogoutTest() {
		super();
		
	}

	//Variable
	//Declaration of WebDriver variable[driver] to interact with the browser
	public WebDriver driver;
	LogoutPage logoutPage;
	
	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicatiOnURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		logoutPage = homePage.selectLogoutOption();

	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {

		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String Email, String Password) {

		logoutPage.enterEmailAddress(Email);
		logoutPage.enterPassword(Password);
		logoutPage.clickOnLoginButton();
		logoutPage.clickOnMyAccount();
		logoutPage.clickOnLogout();
		String actualLogoutMessage = logoutPage.retriveLogoutMessage();
	    String expectedlogoutMessage = "You have been logged off your account. It is now safe to leave the computer.";
	    Assert.assertEquals(actualLogoutMessage,expectedlogoutMessage,"Logout message is incorrect");
	}
	
	
	  

}
