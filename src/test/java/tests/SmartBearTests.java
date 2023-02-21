package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;



public class SmartBearTests extends TestBase {

    @Test
    public void verifySmartBearLogin(){
        driver.get(ConfigReader.getProperty("SmartBearURL"));
        WebElement usernameInput =  driver.findElement(By.id("ctl00_MainContent_username"));
        WebElement passwordInput = driver.findElement(By.id("ctl00_MainContent_password"));

        usernameInput.sendKeys(ConfigReader.getProperty("smartbearUsername"));
        passwordInput.sendKeys(ConfigReader.getProperty("smartbearPassword")+ Keys.ENTER);

        Assert.assertEquals(driver.getTitle(),"Web Orders");

    }
}
