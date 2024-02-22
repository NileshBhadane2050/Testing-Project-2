package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;

public class RegisterTest extends Base {

	// Variable
	// Declaration of WebDriver variable[driver] to interact with the browser
	public WebDriver driver;
	RegisterPage registerPage; 
	AccountSuccessPage accountSuccessPage;

	// Methods
	public RegisterTest () {
		super();
	}
	@BeforeMethod
	public void setup() {
        driver = initializeBrowserAndOpenApplicatiOnURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
        accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));  		
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {

		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));		
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {

		
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regaring duplicate email address is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")), "Privacy Policy Warning message is not displayed");
		Assert.assertTrue(registerPage.retrieveFirstNameWarning().contains(dataProp.getProperty("firstNameWarning")),"First Name Warning Message is not Displayed");
		Assert.assertTrue(registerPage.retrieveLastNameWarning().contains(dataProp.getProperty("lastNameWarning")), "Last Name Warning Message is not Displayed");
		Assert.assertTrue(registerPage.retrieveEmailWarning().contains(dataProp.getProperty("emailWarning")),"Email Warning Message is not Displayed");
		Assert.assertTrue(registerPage.retrieveTelephoneWarning().contains(dataProp.getProperty("telephoneWarning")),"Telephone Warning Message is not Displayed");
		Assert.assertTrue(registerPage.retrievePasswordWarning().contains(dataProp.getProperty("passwordWarning")),"Password Warning Message is not Displayed");

	}

}
