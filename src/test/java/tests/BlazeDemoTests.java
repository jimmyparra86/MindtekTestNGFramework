package tests;

import org.testng.annotations.Ignore;
import org.testng.asserts.SoftAssert;
import pages.blazedemopages.BlazeDemoDestWeek;
import pages.blazedemopages.BlazeDemoFlightsPage;
import pages.blazedemopages.BlazeDemoHomePage;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.blazedemopages.FlightInfoPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

public class BlazeDemoTests extends TestBase {

    @Test(groups = {"regression","smoke","blazedemo"})
    public void verifyFindFlights(){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));


        String fromCity = "San Diego";
        String toCity = "Rome";
        BlazeDemoHomePage blazeDemoHomePage = new BlazeDemoHomePage();
        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();
//        Select fromCityDropdown = new Select(blazeDemoHomePage.fromCity);
//        Select toCityDropdown = new Select(blazeDemoHomePage.toCity);
//        fromCityDropdown.selectByValue(fromCity);
//        toCityDropdown.selectByValue(toCity);

        BrowserUtils.selectDropdownByValue(blazeDemoHomePage.fromCity,fromCity);
        BrowserUtils.selectDropdownByValue(blazeDemoHomePage.toCity,toCity);
        blazeDemoHomePage.findFlightsBtn.click();

        String expectedFlightText = "Flights from "+fromCity+" to "+toCity+":";

        Assert.assertEquals(blazeDemoFlightsPage.flightsText.getText(),expectedFlightText);
    }

    @Test(groups = {"regression","blazedemo"})
    public void verifyDestinationOfTheWeek (){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        BlazeDemoDestWeek blazeDemoDestWeek = new BlazeDemoDestWeek();
        BlazeDemoHomePage blazeDemoHomePage = new BlazeDemoHomePage();
        blazeDemoHomePage.destinationOfTheWeekLink.click();
        String expectedDestinationText = "Destination of the week: Hawaii !";

        Assert.assertEquals(blazeDemoDestWeek.destinationText.getText(),expectedDestinationText);

    }
    @Ignore
    @Test(groups = {"regression","blazedemo"})
    public void verifyFlightInfo(){
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));
        BlazeDemoHomePage blazeDemoHomePage = new BlazeDemoHomePage();
        blazeDemoHomePage.chooseDestination("San Diego","Rome");

        BlazeDemoFlightsPage blazeDemoFlightsPage = new BlazeDemoFlightsPage();
        String expectedAirlineNameStr = blazeDemoFlightsPage.airlineName.getText();
        String expectedFlightNumberStr = blazeDemoFlightsPage.flightNumber.getText();
        String expectedPriceStr = blazeDemoFlightsPage.price.getText();
        blazeDemoFlightsPage.chooseThisFlightBtn.click();

        FlightInfoPage flightInfoPage = new FlightInfoPage();
        String actualAirline = flightInfoPage.airlineName.getText();
        String actualFlightNumber = flightInfoPage.flightNumber.getText();
        String actualPrice = flightInfoPage.price.getText();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualAirline.substring(actualAirline.indexOf(":")+2),expectedAirlineNameStr);
        softAssert.assertEquals(actualFlightNumber,expectedFlightNumberStr);
        softAssert.assertEquals(actualPrice,expectedPriceStr);

        softAssert.assertAll();


    }

}
