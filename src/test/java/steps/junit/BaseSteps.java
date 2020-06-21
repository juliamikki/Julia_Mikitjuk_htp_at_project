package steps.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.PathList;
import utilities.PropertiesParser;
import web_driver.Driver;
import web_pages.booking.*;
import java.util.Properties;

public class BaseSteps {

    private static final Logger LOGGER = LogManager.getLogger(BaseSteps.class);
    private static final String LOGIN_BUTTON_XPATH = "//a[contains(@class,'button desk-notif-card')]";
    private static final String EMAIL_BUTTON_XPATH = "//div[@class='desk-notif-card__mail-title']";

    private static WebDriver driver;
    private static BookingMainPage bookingMainPage;
    private static YandexAuthorizationPage yandexAuthorizationPage;
    private static YandexInboxPage yandexInboxPage;
    private static BookingSignInPage bookingSignInPage;
    private static BookingAccountPage bookingAccountPage;
    private static TrashMailMainPage trashMailMainPage;
    private static Properties bookingProp;
    private static Properties yandexProp;
    private static Properties trashMailProp;

    public BaseSteps() {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        bookingMainPage = new BookingMainPage(driver);
        yandexAuthorizationPage = new YandexAuthorizationPage(driver);
        yandexInboxPage = new YandexInboxPage(driver);
        bookingSignInPage = new BookingSignInPage(driver);
        bookingAccountPage = new BookingAccountPage(driver);
        trashMailMainPage = new TrashMailMainPage(driver);
        bookingProp = PropertiesParser.getProperties(PathList.getBookingPropertyPath());
        yandexProp = PropertiesParser.getProperties(PathList.getYandexPropertyPath());
        trashMailProp = PropertiesParser.getProperties(PathList.getTrashMailPropertyPath());
    }

    public void navigateToPage (String url) {
        LOGGER.debug(">>> Navigate to " + url);
        driver.get(url);
    }

    public void logInToBooking() {
        String url = bookingProp.getProperty("URL");
        navigateToPage(url);
        bookingMainPage.signInButton.click();
        bookingSignInPage.signIn();
        //bookingAccountPage.closePopUp.click();
        LOGGER.debug(">>> Signed in to " + url + " successfully!");
    }

    public void logInToYandexInboxWithCreds () {
        String url = yandexProp.getProperty("URL");
        navigateToPage(url);
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
        Driver.switchToNewTab();
        yandexAuthorizationPage.fillInCredentials();
        LOGGER.debug(">>> Signed in to " + url + " successfully!");
    }

    public void logInToYandexInbox () {
        String url = yandexProp.getProperty("URL");
        navigateToPage(url);
        driver.findElement(By.xpath(EMAIL_BUTTON_XPATH)).click();
        Driver.switchToNewTab();
        LOGGER.debug(">>> Signed in to " + url + " successfully!");
    }

    public void createTrashMail() {
        navigateToPage(trashMailProp.getProperty("URL"));
        trashMailMainPage.createNewUser();
        trashMailMainPage.createDisposableAddress();
        logInToYandexInboxWithCreds();
        yandexInboxPage.confirmTrashMail();
        Driver.switchToXTab(yandexInboxPage.getPageOrder());
        yandexInboxPage.deleteLastEmail();
        LOGGER.debug(">>> TrashMail is created successfully!");
    }
}
