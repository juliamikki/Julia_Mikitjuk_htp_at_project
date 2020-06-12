package steps.junit;

import application_items.booking.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import utilities.booking.TestDataParser;
import web_driver.Driver;
import web_pages.booking.*;
import org.junit.*;
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
    private static BookingAccountPage bookingAccountPage;
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
        bookingAccountPage = new BookingAccountPage(driver);
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
        LOGGER.info(">>> Paris Test execution is finished");
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
        LOGGER.info(">>> Moscow Test execution is finished");
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
        LOGGER.info(">>> Oslo Test execution is finished");
    }


    @Test
    public void trashMailCreationTest() throws FileNotFoundException {
        LOGGER.info(">>> TrashMail Test execution is started");
        trashMailMainPage.navigateToTrashMail();
        trashMailMainPage.createNewUser();
        trashMailMainPage.createDisposableAddress();
        trashMailMainPage.addDisposableEmailToPropertyFile();
        logInToYandexInbox();

        assert yandexInboxPage.emailFromTrashMailBot.getText().equals("TrashMail Robot") : "The email from dedicated sender wasn't found";

        yandexInboxPage.confirmTrashMail();
        Driver.switchToXTab(yandexInboxPage.getPageOrder());
        yandexInboxPage.deleteLastEmail();
    }

    @Test
    public void bookingRegistrationTest () {

        bookingMainPage.navigateToBooking();
        bookingMainPage.registrationButton.click();
        bookingRegistrationPage.createAccount();
        logInToYandexInbox();
        yandexInboxPage.confirmBookingRegistration();
        Driver.switchToNewTab();
        bookingSignInPage.navigateToSignIn();
        bookingSignInPage.signIn();
        bookingAccountPage.goToMyDashboard();

        assert bookingAccountPage.isEmailConfirmBannerPresented().equals(false) : "Booking registration was not confirmed";
    }

    @Test
    public void myNextTripListCreationTest () throws InterruptedException {
        TestData data = testData[3];

        signInToBooking();
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
    }

    @Test
    public void bookingHeaderVerification () {

        signInToBooking();
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
    }

    public void signInToBooking () {
        bookingMainPage.navigateToBooking();
        bookingMainPage.signInButton.click();
        bookingSignInPage.signIn();
        bookingAccountPage.closePopUp.click();
    }

    public void logInToYandexInbox () {
        yandexMainPage.navigateToYandex();
        yandexMainPage.navigateToAuthorizationPage();
        Driver.switchToNewTab();
        yandexAuthorizationPage.logInToYandexMail();
    }


    @AfterClass
    public static void stopBrowser() {
        Driver.destroy();
        LOGGER.info(">>> Browser is stopped");
    }
}
