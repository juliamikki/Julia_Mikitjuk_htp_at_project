package steps;

import application_items.booking.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runners.MethodSorters;
import utilities.booking.TestDataParser;
import web_driver.Driver;
import web_pages.booking.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileNotFoundException;
import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookingJUnit4Test {

    private static WebDriver driver;
    private static BookingMainPage bookingMainPage;
    private static BookingSearchResultsPage bookingSearchResultsPage;
    private static TrashMailMainPage trashMailMainPage;
    private static YandexMainPage yandexMainPage;
    private static YandexAuthorizationPage yandexAuthorizationPage;
    private static YandexInboxPage yandexInboxPage;
    private static BookingRegistrationPage bookingRegistrationPage;
    private static BookingSignInPage bookingSignInPage;
    private static TestData [] testData;
    private static final Logger LOGGER = LogManager.getLogger(BookingJUnit4Test.class);

    @BeforeClass
    public static void startBrowser() throws IOException {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        bookingMainPage = new BookingMainPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        bookingSearchResultsPage = new BookingSearchResultsPage(driver);
        trashMailMainPage = new TrashMailMainPage(driver);
        yandexMainPage = new YandexMainPage(driver);
        yandexAuthorizationPage = new YandexAuthorizationPage(driver);
        yandexInboxPage = new YandexInboxPage(driver);
        bookingRegistrationPage = new BookingRegistrationPage(driver);
        bookingSignInPage = new BookingSignInPage(driver);
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

        bookingMainPage.navigateToBooking();
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

        int cheapestHotelPerDay = bookingSearchResultsPage.getFirstHotelPricePerNight();
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

        bookingMainPage.navigateToBooking();
        bookingMainPage.enterDestination(data.getDestination());
        bookingMainPage.enterDates(data.getArrivalInXDays(), data.getDurationOfStay());
        bookingMainPage.searchButton.click();
        bookingSearchResultsPage.enterAccommodationDetailsUsingActions(data.getAdults(),data.getChildren(),data.getRooms());
        bookingSearchResultsPage.searchButton.click();

        bookingSearchResultsPage.minPriceHotelsFilter.click();
        Driver.setWaiter().until(ExpectedConditions.elementToBeSelected(bookingSearchResultsPage.minPriceHotelsFilterSelected));

        int minPriceRange = bookingSearchResultsPage.getMinFilterPrice();
        System.out.println(String.format(cheapHotels, data.getDestination(), minPriceRange));
        int topHotelPrice = bookingSearchResultsPage.getFirstHotelPricePerNight();
        System.out.println(String.format(topHotel, topHotelPrice));

        assert topHotelPrice <= minPriceRange : "The Price of the Hotel is not in the required Price Range! ";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
    }

    @Test
    public void osloTest ()  {
        long time = System.currentTimeMillis();
        LOGGER.info(">>> Oslo Test execution is started");

        TestData data = testData[2];

        bookingMainPage.navigateToBooking();
        bookingMainPage.enterDestination(data.getDestination());
        bookingMainPage.enterDates(data.getArrivalInXDays(),data.getDurationOfStay());
        bookingMainPage.enterAccommodationDetails(data.getAdults(),data.getChildren(),data.getRooms());
        bookingMainPage.searchButton.click();

        bookingSearchResultsPage.chooseStarRating();

        bookingSearchResultsPage.applyStyleTo10Hotel();
        assert bookingSearchResultsPage.getColorOf10HotelTitle().equals("rgba(255, 0, 0, 1)") : "The color of the tenth hotel title is not as expected";

        System.out.println((double) (System.currentTimeMillis() - time)/1000);
    }


    @Test
    public void checkTrashMailIsCreatedTest () throws FileNotFoundException {

        trashMailMainPage.navigateToTrashMail();
        trashMailMainPage.createNewUser();
        trashMailMainPage.createDisposableAddress();
        trashMailMainPage.addDisposableEmailToPropertyFile();
        navigateToInbox();

        assert yandexInboxPage.lastMail.getText().equals("TrashMail Robot") : "The email from dedicated sender wasn't found";

        yandexInboxPage.confirmTrashMail();
        yandexInboxPage.deleteLastEmail();
    }

    @Test
    public void bookingRegistrationTest () throws InterruptedException {
        bookingMainPage.navigateToBooking();
        bookingMainPage.registrationButton.click();
        bookingRegistrationPage.createAccount();
        navigateToInbox();
        yandexInboxPage.confirmBookingRegistration();
        bookingMainPage.navigateToBooking();
        bookingMainPage.signInButton.click();
        bookingSignInPage.signIn();
        bookingMainPage.closePopUp.click();
        bookingMainPage.notifications.click();

        assert bookingMainPage.notificationAlert.getText().equals("You have no new notifications.") : "The email wasn't confirmed";

    }

    public void navigateToInbox () {
        yandexMainPage.navigateToYandex();
        yandexMainPage.navigateToAuthorizationPage();
        Driver.switchToNewTab();
        yandexAuthorizationPage.logInToYandexMail();
    }



    /*@AfterClass
    public static void stopBrowser() {
        Driver.destroy();
    }*/
}
