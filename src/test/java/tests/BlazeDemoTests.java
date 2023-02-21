package tests;

import pages.BlazeDemoDestWeek;
import pages.BlazeDemoFlightsPage;
import pages.BlazeDemoHomePage;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class BlazeDemoTests extends TestBase {

    @Test
    public void verifyFindFlights(){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));


        String fromCity = "San Diego";
        String toCity = "Rome";
        BlazeDemoHomePage blazeDemoHomePage = new BlazeDemoHomePage();
        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();
        Select fromCityDropdown = new Select(blazeDemoHomePage.fromCity);
        Select toCityDropdown = new Select(blazeDemoHomePage.toCity);
        fromCityDropdown.selectByValue(fromCity);
        toCityDropdown.selectByValue(toCity);
        blazeDemoHomePage.findFlightsBtn.click();

        String expectedFlightText = "Flights from "+fromCity+" to "+toCity+":";

        Assert.assertEquals(blazeDemoFlightsPage.flightsText.getText(),expectedFlightText);
    }

    @Test
    public void verifyDestinationOfTheWeek (){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        BlazeDemoDestWeek blazeDemoDestWeek = new BlazeDemoDestWeek();
        BlazeDemoHomePage blazeDemoHomePage = new BlazeDemoHomePage();
        blazeDemoHomePage.destinationOfTheWeekLink.click();
        String expectedDestinationText = "Destination of the week: Hawaii !";

        Assert.assertEquals(blazeDemoDestWeek.destinationText.getText(),expectedDestinationText);

    }

    @Test
    public void verifyFlightInfo(){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        BlazeDemoHomePage blazeDemoHomePage = new BlazeDemoHomePage();
        blazeDemoHomePage.chooseDestination("San Diego","Rome");

        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();
        blazeDemoFlightsPage.chooseThisFlightBtn.click();

    }

}
