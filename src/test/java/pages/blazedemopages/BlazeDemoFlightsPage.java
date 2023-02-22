package pages.blazedemopages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazeDemoFlightsPage {

    WebDriver driver;

    public BlazeDemoFlightsPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
        // We use PageFactory class to initialize all the elements of the page
    }

    @FindBy (tagName = "h3")    //By.xpath("//h3"))
    public WebElement flightsText;

    @FindBy (xpath = "(//input[@type='submit'])[1]")
    public WebElement chooseThisFlightBtn;

    @FindBy (xpath = "(//td[2])[1]")
    public WebElement flightNumber;

    @FindBy (xpath = "(//td[3])[1]")
    public WebElement airlineName;

    @FindBy (xpath = "(//td[6])[1]")
    public WebElement price;


}
