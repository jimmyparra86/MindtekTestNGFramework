package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saucedemopages.SauceDemoHomePage;
import pages.saucedemopages.SauceDemoLoginPage;
import utilities.ConfigReader;
import utilities.TestBase;

public class SauceDemoTests extends TestBase {

    @Test(groups = {"regression", "smoke", "saucedemo","login"})
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

    @Test(groups = {"regression", "smoke", "saucedemo","login"})
    public void verifySauceLoginNegative(){
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();

        sauceDemoLoginPage.username.sendKeys(ConfigReader.getProperty("standarUser"));
        sauceDemoLoginPage.password.sendKeys(ConfigReader.getProperty("saucePassword"));
        sauceDemoLoginPage.loginBtn.click();

        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.");


    }

    @Test(groups = {"regression", "smoke", "saucedemo","login"})
    public void verifyPriceHighToLow(){

            driver.get(ConfigReader.getProperty("SauceDemoURL"));
            SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();

            sauceDemoLoginPage.username.sendKeys(ConfigReader.getProperty("standardUser"));
            sauceDemoLoginPage.password.sendKeys(ConfigReader.getProperty("saucePassword"));
            sauceDemoLoginPage.loginBtn.click();

            SauceDemoHomePage sauceDemoHomePage = new SauceDemoHomePage();

            Select sortingDropdown = new Select(sauceDemoHomePage.sortDropDown);
            sortingDropdown.selectByValue("hilo");

        for (int i = 0; i < sauceDemoHomePage.itemPrices.size()-1; i++) {
            double item1 = Double.parseDouble(sauceDemoHomePage.itemPrices.get(i).getText().substring(1));
            double item2 = Double.parseDouble(sauceDemoHomePage.itemPrices.get(i+1).getText().substring(1));

            System.out.println(item1+" is greater then or equal to "+item2);
            Assert.assertTrue(item1 >= item2);
        }




    }


}
