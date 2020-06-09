package steps;

import application_items.booking.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runners.MethodSorters;
import utilities.booking.TestDataParser;
import web_driver.Driver;
import web_pages.booking.MainPage;
import web_pages.booking.SearchResultsPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web_pages.booking.TrashMailMainPage;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookingJUnit4Test {

    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResultsPage;
    private static TrashMailMainPage trashMailMainPage;
    private static TestData [] testData;
    private static final Logger LOGGER = LogManager.getLogger(BookingJUnit4Test.class);

    @BeforeClass
    public static void startBrowser() throws IOException {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        trashMailMainPage = new TrashMailMainPage(driver);
        testData = TestDataParser.parseJackson();
        LOGGER.info(">>> Browser is started");
    }

    @Test
    public void parisTest() {
        long time = System.currentTimeMillis();
        LOGGER.info(">>> Paris Test execution is started");

        TestData data = testData[0];
        String expensiveHotels = "Prices for the most expensive hotels in %s start from %s BYN per night";
        String cheapestHotels = "The cheapest hotel from the list costs %s BYN per night";

        mainPage.navigateToBooking();
        mainPage.enterDestination(data.getDestination());
        mainPage.enterDates(data.getArrivalInXDays(),data.getDurationOfStay());
        mainPage.enterAccommodationDetails(data.getAdults(),data.getChildren(),data.getRooms());
        mainPage.searchButton.click();

        searchResultsPage.maxPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(searchResultsPage.maxPriceHotelsFilterSelected));

        int highestPriceRange = searchResultsPage.getMaxFilterPrice();
        System.out.println(String.format(expensiveHotels, data.getDestination(), highestPriceRange));

        searchResultsPage.lowestPriceFirst.click();
        Driver.setWaiter().until(ExpectedConditions.visibilityOf(searchResultsPage.lowestPriceFirstSelected));

        int cheapestHotelPerDay = searchResultsPage.getFirstHotelPricePerNight();
        System.out.println(String.format(cheapestHotels, cheapestHotelPerDay));

        assert cheapestHotelPerDay >= highestPriceRange : "The Price of the Hotel is not in the required Price Range!";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
    }

    @Test
    public void moscowTest () {
        long time = System.currentTimeMillis();
        LOGGER.info(">>> Moscow Test execution is started");

        TestData data = testData[1];

        String cheapHotels = "Prices for cheapest hotels in %s are less than %s BYN per night";
        String topHotel = "The top hotel from the list costs %s BYN per night";

        mainPage.navigateToBooking();
        mainPage.enterDestination(data.getDestination());
        mainPage.enterDates(data.getArrivalInXDays(), data.getDurationOfStay());
        mainPage.searchButton.click();
        searchResultsPage.enterAccommodationDetailsUsingActions(data.getAdults(),data.getChildren(),data.getRooms());
        searchResultsPage.searchButton.click();

        searchResultsPage.minPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(searchResultsPage.minPriceHotelsFilterSelected));
        //Driver.setWaiter().until(ExpectedConditions.invisibilityOf(searchResultsPage.overlayCheckMark)); //throws NoSuchElement exception. Why??

        int minPriceRange = searchResultsPage.getMinFilterPrice();
        System.out.println(String.format(cheapHotels, data.getDestination(), minPriceRange));
        int topHotelPrice = searchResultsPage.getFirstHotelPricePerNight();
        System.out.println(String.format(topHotel, topHotelPrice));

        assert topHotelPrice <= minPriceRange : "The Price of the Hotel is not in the required Price Range! ";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
    }

    @Test
    public void osloTest ()  {
        long time = System.currentTimeMillis();
        LOGGER.info(">>> Oslo Test execution is started");

        TestData data = testData[2];

        mainPage.navigateToBooking();
        mainPage.enterDestination(data.getDestination());
        mainPage.enterDates(data.getArrivalInXDays(),data.getDurationOfStay());
        mainPage.enterAccommodationDetails(data.getAdults(),data.getChildren(),data.getRooms());
        mainPage.searchButton.click();

        searchResultsPage.chooseStarRating();

        searchResultsPage.applyStyleTo10Hotel();
        assert searchResultsPage.getColorOf10HotelTitle().equals("rgba(255, 0, 0, 1)") : "The color of the tenth hotel title is not as expected";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
    }

    @Test
    public void generateTrashMail () {
        trashMailMainPage.navigateToTrashMail();
        trashMailMainPage.createNewUser();
    }

    @AfterClass
    public static void stopBrowser() {
        Driver.destroy();
    }
}
