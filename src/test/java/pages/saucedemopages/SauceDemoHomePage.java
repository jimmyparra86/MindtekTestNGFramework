package pages.saucedemopages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SauceDemoHomePage {

    WebDriver driver;

    public SauceDemoHomePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement sortDropDown;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> itemPrices;


}
