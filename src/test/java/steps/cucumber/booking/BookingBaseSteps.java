package steps.cucumber.booking;

import application_items.booking.TestData;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.booking.TestDataParser;
import web_driver.Driver;
import web_pages.booking.*;

import java.io.IOException;

public class BookingBaseSteps {

    private static final Logger LOGGER = LogManager.getLogger(BookingBaseSteps.class);
    private static WebDriver driver;
    private static BookingMainPage bookingMainPage;
    private static BookingSearchResultsPage bookingSearchResultsPage;
    private static TrashMailMainPage trashMailMainPage;
    private static YandexAuthorizationPage yandexAuthorizationPage;
    private static YandexInboxPage yandexInboxPage;
    private static BookingRegistrationPage bookingRegistrationPage;
    private static BookingSignInPage bookingSignInPage;
    private static TestData[] testData;

    public static WebDriver getDriver() {
        return driver;
    }

    public static BookingMainPage getBookingMainPage() {
        return bookingMainPage;
    }

    public static BookingSearchResultsPage getBookingSearchResultsPage() {
        return bookingSearchResultsPage;
    }

    public static TestData[] getTestData() {
        return testData;
    }

    @Before
    public static void startBrowser() throws IOException {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        bookingMainPage = new BookingMainPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        trashMailMainPage = new TrashMailMainPage(driver);
        yandexAuthorizationPage = new YandexAuthorizationPage(driver);
        yandexInboxPage = new YandexInboxPage(driver);
        bookingRegistrationPage = new BookingRegistrationPage(driver);
        bookingSignInPage = new BookingSignInPage(driver);
        testData = TestDataParser.parseJackson();
        LOGGER.info(">>> Cucumber: Browser is started successfully!");
    }

    @After
    public static void stopBrowser() {
        Driver.destroy();
        LOGGER.info(">>> Cucumber: Browser is stopped successfully!");
    }

}
