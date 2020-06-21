package steps.junit;

import application_items.booking.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import utilities.PathList;
import utilities.PropertiesParser;
import utilities.booking.TestDataParser;
import web_driver.Driver;
import web_pages.booking.*;
import org.junit.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.Properties;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookingHotelsJUnit4Test {

    private static final Logger LOGGER = LogManager.getLogger(BookingHotelsJUnit4Test.class);
    private static WebDriver driver;
    private static BookingMainPage bookingMainPage;
    private static BookingSearchResultsPage bookingSearchResultsPage;
    private static TestData [] testData;
    private static Properties bookingProp;
    private static BaseSteps baseSteps;

    @BeforeClass
    public static void startBrowser() throws IOException {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        LOGGER.info(">>> Browser is started");

        baseSteps = new BaseSteps();
        bookingMainPage = new BookingMainPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        testData = TestDataParser.parseJackson();
        bookingProp = PropertiesParser.getProperties(PathList.getBookingPropertyPath());
    }

    @Test
    public void parisTest() {
        LOGGER.info(">>> Paris Test execution is started!");
        long time = System.currentTimeMillis();

        TestData data = testData[0];
        String expensiveHotels = "Prices for the most expensive hotels in %s start from %s BYN per night";
        String cheapestHotels = "The cheapest hotel from the list costs %s BYN per night";

        baseSteps.navigateToPage(bookingProp.getProperty("URL"));
        bookingMainPage.enterDestination(data.getDestination());
        bookingMainPage.enterDates(data.getArrivalInXDays(),data.getDurationOfStay());
        bookingMainPage.enterAccommodationDetails(data.getAdults(),data.getChildren(),data.getRooms());
        bookingMainPage.searchButton.click();

        bookingSearchResultsPage.maxPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(bookingSearchResultsPage.maxPriceHotelsFilterSelected));

        int highestPriceRange = bookingSearchResultsPage.getMaxFilterPrice();
        System.out.println(String.format(expensiveHotels, data.getDestination(), highestPriceRange));

        bookingSearchResultsPage.lowestPriceFirst.click();
        Driver.setWaiter().until(ExpectedConditions.visibilityOf(bookingSearchResultsPage.lowestPriceFirstSelected));

        int cheapestHotelPerDay = bookingSearchResultsPage.getFirstHotelPricePerNight(data.getDurationOfStay());
        System.out.println(String.format(cheapestHotels, cheapestHotelPerDay));

        assert cheapestHotelPerDay >= highestPriceRange : "The Price of the Hotel is not in the required Price Range!";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
        LOGGER.info(">>> Paris Test execution is finished!");
    }

    @Test
    public void moscowTest () {
        LOGGER.info(">>> Moscow Test execution is started!");
        long time = System.currentTimeMillis();

        TestData data = testData[1];
        String cheapHotels = "Prices for cheapest hotels in %s are less than %s BYN per night";
        String topHotel = "The top hotel from the list costs %s BYN per night";

        baseSteps.navigateToPage(bookingProp.getProperty("URL"));
        bookingMainPage.enterDestination(data.getDestination());
        bookingMainPage.enterDates(data.getArrivalInXDays(), data.getDurationOfStay());
        bookingMainPage.searchButton.click();
        bookingSearchResultsPage.enterAccommodationDetailsUsingActions(data.getAdults(),data.getChildren(),data.getRooms());
        bookingSearchResultsPage.searchButton.click();

        bookingSearchResultsPage.minPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(bookingSearchResultsPage.minPriceHotelsFilterSelected));

        int minPriceRange = bookingSearchResultsPage.getMinFilterPrice();
        System.out.println(String.format(cheapHotels, data.getDestination(), minPriceRange));
        int topHotelPrice = bookingSearchResultsPage.getFirstHotelPricePerNight(data.getDurationOfStay());
        System.out.println(String.format(topHotel, topHotelPrice));

        assert topHotelPrice <= minPriceRange : "The Price of the Hotel is not in the required Price Range! ";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
        LOGGER.info(">>> Moscow Test execution is finished!");
    }


    @Test
    public void osloTest ()  {
        LOGGER.info(">>> Oslo Test execution is started!");
        long time = System.currentTimeMillis();

        TestData data = testData[2];
        baseSteps.navigateToPage(bookingProp.getProperty("URL"));
        bookingMainPage.enterDestination(data.getDestination());
        bookingMainPage.enterDates(data.getArrivalInXDays(),data.getDurationOfStay());
        bookingMainPage.enterAccommodationDetails(data.getAdults(),data.getChildren(),data.getRooms());
        bookingMainPage.searchButton.click();

        bookingSearchResultsPage.chooseStarRating();

        bookingSearchResultsPage.applyStyleTo10Hotel();
        assert bookingSearchResultsPage.getColorOf10HotelTitle().equals("rgba(255, 0, 0, 1)") : "The color of the tenth hotel title is not as expected";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
        LOGGER.info(">>> Oslo Test execution is finished!");
    }

    @AfterClass
    public static void stopBrowser() {
        Driver.destroy();
        LOGGER.info(">>> Browser is stopped!");
    }
}

