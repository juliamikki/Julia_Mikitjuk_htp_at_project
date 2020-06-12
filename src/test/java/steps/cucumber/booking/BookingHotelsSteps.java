package steps.cucumber.booking;

import application_items.booking.TestData;
import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web_driver.Driver;
import web_pages.booking.*;

public class BookingHotelsSteps {

    private static final String EXPENSIVE_HOTELS_MSG = "Prices for the most expensive hotels in %s start from %s BYN per night";
    private static final String CHEAPEST_HOTEL_MSG = "The cheapest hotel from the list costs %s BYN per night";
    private static final String CHEAP_HOTELS_MSG = "Prices for cheapest hotels in %s are less than %s BYN per night";
    private static final String TOP_HOTEL_MSG = "The top hotel from the list costs %s BYN per night";

    private static WebDriver driver;
    private static BookingMainPage bookingMainPage;
    private static BookingSearchResultsPage bookingSearchResultsPage;
    private static TestData [] testData;
    private static TestData data;
    private int highestPriceRange;
    private int cheapestHotelPerDay;
    private int minPriceRange;
    private int topHotelPrice;

    public BookingHotelsSteps() {
        driver = BaseSteps.getDriver();
        bookingMainPage = BaseSteps.getBookingMainPage();
        bookingSearchResultsPage = BaseSteps.getBookingSearchResultsPage();
        testData = BaseSteps.getTestData();
    }

    @Given("I start test execution with {int} test data")
    public void iStartTestExecution (int testDataOrder) {
        data = testData[testDataOrder];
        bookingMainPage.navigateToBooking();
    }

    @When ("I enter destination {string} with arrival in {int} days and {int} nights accommodation")
    public void iEnterDestinationAndDates (String destination, int arrivalInXDays , int durationOfStay) {
        bookingMainPage.enterDestination(destination);
        bookingMainPage.enterDates(arrivalInXDays, durationOfStay);
    }

    @And ("I enter accommodation details: {int} adults, {int} children, {int} rooms - on main page")
    public void iEnterAccommodationDetails (int adults, int children, int rooms) {
        bookingMainPage.enterAccommodationDetails(adults, children, rooms);
    }

    @And ("I enter accommodation details: {int} adults, {int} children, {int} rooms - using actions")
    public void iEnterAccommodationDetailsUsingActions (int adults, int children, int rooms) {
        bookingSearchResultsPage.enterAccommodationDetailsUsingActions(adults, children, rooms);
    }

    @And ("I do search on main page")
    public void iDoSearchOnMain () {
        bookingMainPage.searchButton.click();
    }

    @And ("I do search on search page")
    public void iDoSearchOnSecondary () {
        bookingSearchResultsPage.searchButton.click();
    }

    @And ("I apply maximum price hotel filter")
    public void iApplyMaxPriceFilter () {
        bookingSearchResultsPage.maxPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(bookingSearchResultsPage.maxPriceHotelsFilterSelected));
    }

    @And ("I apply minimum price hotel filter")
    public void iApplyMinPriceFilter () {
        bookingSearchResultsPage.minPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(bookingSearchResultsPage.minPriceHotelsFilterSelected));
    }

    @And ("I apply star rating filter")
    public void iApplyStarRatingFilter () {
        bookingSearchResultsPage.chooseStarRating();
    }

    @And ("I apply ascending price sorting")
    public void iApplyPriceSortingAsc () {
        bookingSearchResultsPage.lowestPriceFirst.click();
        Driver.setWaiter().until(ExpectedConditions.visibilityOf(bookingSearchResultsPage.lowestPriceFirstSelected));
    }

    @And ("I identify and print cheapest hotel per night price and price per night from highest budget hotels")
    public void iIdentifyAndPrintPricesToCompareMaxBudget () {
        highestPriceRange = bookingSearchResultsPage.getMaxFilterPrice();
        System.out.println(String.format(EXPENSIVE_HOTELS_MSG, data.getDestination(), highestPriceRange));
        Driver.setWaiter().until(ExpectedConditions.visibilityOf(bookingSearchResultsPage.lowestPriceFirstSelected));
        cheapestHotelPerDay = bookingSearchResultsPage.getFirstHotelPricePerNight();
        System.out.println(String.format(CHEAPEST_HOTEL_MSG, cheapestHotelPerDay));
    }

    @And ("I identify and print first hotel per night price and price per night from lowest budget hotels")
    public void iIdentifyAndPrintPricesToCompareMinBudget () {
        minPriceRange = bookingSearchResultsPage.getMinFilterPrice();
        System.out.println(String.format(CHEAP_HOTELS_MSG, data.getDestination(), minPriceRange));
        topHotelPrice = bookingSearchResultsPage.getFirstHotelPricePerNight();
        System.out.println(String.format(TOP_HOTEL_MSG, topHotelPrice));
    }

    @And ("I apply styles to tenth hotel")
    public void applyStyles () {
        bookingSearchResultsPage.applyStyleTo10Hotel();
    }

    @Then ("I verify cheapest hotel per night price >= minimum price per night from highest budget hotels")
    public void iVerifyCheapestHotelPriceGreaterThanMinPrice () {
        assert cheapestHotelPerDay >= highestPriceRange : "The Price of the Hotel is not in the required Price Range!";
    }

    @Then ("I verify top hotel per night price <= maximum price per night from lowest budget hotels")
    public void iVerifyTopHotelPriceLessThanMinPrice () {
        assert topHotelPrice <= minPriceRange : "The Price of the Hotel is not in the required Price Range! ";
    }

    @Then ("I verify styles are applied")
    public void iVerifyStyles() {
        assert bookingSearchResultsPage.getColorOf10HotelTitle().equals("rgba(255, 0, 0, 1)") : "The color of the tenth hotel title is not as expected";
    }





}
