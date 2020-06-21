package steps.junit;

import application_items.booking.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.PathList;
import utilities.PropertiesParser;
import utilities.booking.TestDataParser;
import web_driver.Driver;
import web_pages.booking.*;
import java.io.IOException;
import java.util.Properties;

public class BookingAuthorizedJUnit4Test {

    private static final Logger LOGGER = LogManager.getLogger(BookingAuthorizedJUnit4Test.class);
    private static WebDriver driver;
    private static BookingMainPage bookingMainPage;
    private static BookingSearchResultsPage bookingSearchResultsPage;
    private static BookingRegistrationPage bookingRegistrationPage;
    private static BookingSignInPage bookingSignInPage;
    private static BookingAccountPage bookingAccountPage;
    private static YandexInboxPage yandexInboxPage;
    private static TestData [] testData;
    private static Properties bookingProp;
    private static BaseSteps baseSteps;

    @BeforeClass
    public static void preconditions () throws IOException {

        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        LOGGER.info(">>> Browser is started");

        baseSteps = new BaseSteps();
        bookingMainPage = new BookingMainPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        bookingRegistrationPage = new BookingRegistrationPage(driver);
        bookingSignInPage = new BookingSignInPage(driver);
        bookingAccountPage = new BookingAccountPage(driver);
        yandexInboxPage = new YandexInboxPage(driver);
        testData = TestDataParser.parseJackson();
        bookingProp = PropertiesParser.getProperties(PathList.getBookingPropertyPath());
        baseSteps.createTrashMail();
    }

    @Test
    public void bookingRegistrationTest () {
        LOGGER.info(">>> Booking registration test execution is started!");
        baseSteps.navigateToPage(bookingProp.getProperty("URL"));
        bookingMainPage.registrationButton.click();
        bookingRegistrationPage.createAccount();
        baseSteps.logInToYandexInbox();
        yandexInboxPage.confirmBookingRegistration();
        Driver.switchToNewTab();
        baseSteps.navigateToPage(bookingProp.getProperty("SIGN_IN"));
        bookingSignInPage.signIn();
        bookingAccountPage.goToMyDashboard();

        assert bookingAccountPage.isEmailConfirmBannerPresented().equals(false) : "Booking registration wasn't confirmed!";

        LOGGER.info(">>> Booking registration test is finished!");
    }

    @Test
    public void myNextTripListCreationTest () throws InterruptedException {
        LOGGER.info(">>> My Next Trip creation test execution is started!");
        TestData data = testData[3];
        baseSteps.logInToBooking();
        bookingMainPage.enterDestination(data.getDestination());
        bookingMainPage.enterDates(data.getArrivalInXDays(),data.getDurationOfStay());
        bookingMainPage.enterAccommodationDetails(data.getAdults(),data.getChildren(),data.getRooms());
        bookingMainPage.searchButton.click();
        bookingSearchResultsPage.addFavorites();

        Thread.sleep(2000);
        assert bookingSearchResultsPage.firstHotelHeartIcon.getCssValue("fill").equals("rgb(204, 0, 0)") : "The color of the first hotel heart title is not red";
        assert bookingSearchResultsPage.lastHotelHeartIcon.getCssValue("fill").equals("rgb(204, 0, 0)") : "The color of the last hotel heart title is not red";

        String firstHotelTitle = bookingSearchResultsPage.firstHotelTitle.getText();
        String lastHotelTitle = bookingSearchResultsPage.lastHotelTitle.getText();
        bookingAccountPage.goToMyNextTripList();
        Thread.sleep(2000);

        assert firstHotelTitle.equals(bookingAccountPage.firstFavoriteHotel.getText()) : "First hotel hasn't appeared at My Next Trip list";
        assert lastHotelTitle.equals(bookingAccountPage.secondFavoriteHotel.getText()) : "Last hotel hasn't appeared at My Next Trip list";
        LOGGER.info(">>> My Next Trip creation test execution is finished!");
    }

    @Test
    public void bookingHeaderVerification () {

        LOGGER.info(">>> Booking header verification test execution is started!");
        baseSteps.logInToBooking();
        assert bookingAccountPage.bookingLogo.isDisplayed() : "Booking logo doesn't exist";
        assert bookingAccountPage.chooseCurrency.isDisplayed() : "Choose currency icon doesn't exist";
        assert bookingAccountPage.selectLanguage.isDisplayed() : "Select language icon doesn't exist";
        assert bookingAccountPage.viewNotifications.isDisplayed() : "View notifications icon doesn't exist";
        assert bookingAccountPage.customerServiceHelpCenter.isDisplayed() : "Customer service icon doesn't exist";
        assert bookingAccountPage.propertyList.isDisplayed() : "Property list button doesn't exist";
        assert bookingAccountPage.yourAccount.isDisplayed() : "Your account button doesn't exist";
        assert bookingAccountPage.stays.isDisplayed() : "Stays tab doesn't exist";
        assert bookingAccountPage.flights.isDisplayed() : "Flights tab doesn't exist";
        assert bookingAccountPage.carRentals.isDisplayed() : "Car rentals tab doesn't exist";
        assert bookingAccountPage.attractions.isDisplayed() : "Attractions tab doesn't exist";
        assert bookingAccountPage.airportTaxis.isDisplayed() : "Airport taxis tab doesn't exist";
        LOGGER.info(">>> Booking header verification test execution is finished!");
    }

    @AfterClass
    public static void stopBrowser() {
        Driver.destroy();
        LOGGER.info(">>> Browser is stopped");
    }
}
