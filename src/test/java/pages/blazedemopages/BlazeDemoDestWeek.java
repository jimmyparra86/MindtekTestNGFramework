package pages.blazedemopages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazeDemoDestWeek {

    WebDriver driver;

    public BlazeDemoDestWeek (){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(text(),'Destination')]" )//driver.findElement(By.xpath("//div[contains(text(),'Destination')]"
    public WebElement destinationText;
}
