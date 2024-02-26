package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tes {

    @Test
    public void Demo() {
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.linkText("My Account")).click();  
        driver.findElement(By.linkText("Login")).click();    
        driver.findElement(By.id("input-email")).sendKeys("ArunMeloni@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Logout")).click();
        String actualLogoutMessage = driver.findElement(By.xpath("//p[contains(text(),'You have been logged off your account. It is now s')]")).getText();
        String expectedlogoutMessage = "You have been logged off your account. It is now safe to leave the computer.";
        Assert.assertEquals(actualLogoutMessage,expectedlogoutMessage,"Logout message is incorrect");
        driver.quit();
    }
}
