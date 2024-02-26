package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends LoginPage {

	public LogoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    WebDriver driver;
    @FindBy(linkText = "My Account")
    private WebElement Account;

    public void clickOnMyAccount() {
        Account.click();
    }
    
    @FindBy(linkText = "Logout")
    private WebElement Logout;

    public void clickOnLogout() {
    	Logout.click();
    }
    
    @FindBy(xpath ="//p[contains(text(),'You have been logged off your account. It is now s')]")
    private WebElement actulLogoutMessage;
    
    public String retriveLogoutMessage() {
	    
    	String actualLogoutMessage = actulLogoutMessage.getText();
    	return actualLogoutMessage;
    	
    }
    
    
    
    
}