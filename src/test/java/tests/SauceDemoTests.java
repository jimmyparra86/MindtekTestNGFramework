package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class SauceDemoTests extends TestBase {

    @Test
    public void verifySauceLoginPositive(){
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys(ConfigReader.getProperty("standardUser"));
        passwordInput.sendKeys(ConfigReader.getProperty("saucePassword"));
        loginBtn.click();

        String productsTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();

        Assert.assertEquals(productsTitle,"PRODUCTS");

    }

    @Test
    public void verifySauceLoginNegative(){
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys(ConfigReader.getProperty("lockedOutUser"));
        passwordInput.sendKeys(ConfigReader.getProperty("saucePassword"));
        loginBtn.click();

        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.");


    }

}
