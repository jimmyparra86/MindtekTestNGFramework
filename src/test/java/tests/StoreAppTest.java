package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.storeapppages.StoreAppCreateAccPage;
import pages.storeapppages.StoreAppHomePage;
import pages.storeapppages.StoreAppSignInPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import javax.naming.Name;
import java.util.UUID;

public class StoreAppTest extends TestBase {
    /**
     * DataProvider is annotation(position doesn't matter)
     * It is used for Data-Driven Testing (DDT).
     * Needs a unique name
     */

    @DataProvider(name = "createAccountData")
    public static Object[][] testData(){
        return new Object[][]{
                {"Harsh","Patel",BrowserUtils.getRandomEmail(), "Ee12345#"},
                {"Jane","Doe",BrowserUtils.getRandomEmail(),"MyPassword1#"},
                {"Zarif","Orunov",BrowserUtils.getRandomEmail(),"TestPwd123!"}

        };
    }
    String email;
    String password;

    @Test(groups = {"regression", "storeapp","createaccount"}, dataProvider = "createAccountData")
    public void verifyCreateAccount(String firstName, String lastName,String email ,String password){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppHomePage storeAppHomePage = new StoreAppHomePage();
        storeAppHomePage.createAccountLink.click();

        StoreAppCreateAccPage storeAppCreateAccPage=new StoreAppCreateAccPage();
        storeAppCreateAccPage.firstNameInput.sendKeys(firstName);
        storeAppCreateAccPage.lastNameInput.sendKeys(lastName);

//        String user = "user";
//        UUID uuid = UUID.randomUUID();
//        email = user+uuid+"@gmail.com";    //usera014d847-90da-445f-a9c9-c549c0299402@gmail.com

        this.email=email;
        this.password=password;
        storeAppCreateAccPage.emailInput.sendKeys();
        storeAppCreateAccPage.passwordInput.sendKeys(password);
        storeAppCreateAccPage.passwordConfirmInput.sendKeys(password);

        System.out.println("User email: "+email);
        System.out.println("User password: "+password);

        storeAppCreateAccPage.createAccountBtn.click();
        Assert.assertTrue(driver.getTitle().contains("My Account"));
    }

    @Test(groups = {"regression","storeapp","signIn"}, dependsOnMethods="verifyCreateAccount")
    public void VerifySignIn(){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppHomePage storeAppHomePage = new StoreAppHomePage();
        storeAppHomePage.signInLink.click();

        StoreAppSignInPage storeAppSignInPage = new StoreAppSignInPage();
        storeAppSignInPage.emailInput.sendKeys(email);
        storeAppSignInPage.passwordInput.sendKeys(password);
        storeAppSignInPage.signInBtn.click();
        Assert.assertTrue(driver.getTitle().contains("Home Page"));


    }

}
