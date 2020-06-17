package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_pages.AbstractPage;

public class BookingAccountPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(BookingAccountPage.class);

    @FindBy(id = "logo_no_globe_new_logo")
    public WebElement bookingLogo;

    @FindBy(xpath = "//*[@data-id='currency_selector']")
    public WebElement chooseCurrency;

    @FindBy(xpath = "//*[@data-id='language_selector']")
    public WebElement selectLanguage ;

    @FindBy(xpath = "//*[@data-id='notifications']")
    public WebElement viewNotifications;

    @FindBy(xpath = "//*[contains(@class,'uc_helpcenter')]")
    public WebElement customerServiceHelpCenter;

    @FindBy(id = "add_property_topbar")
    public WebElement propertyList;

    @FindBy(id = "current_account")
    public WebElement yourAccount;

    @FindBy(xpath = "//*[contains(@data-et-click,'accommodation')]")
    public WebElement stays;

    @FindBy(xpath = "//*[contains(@data-et-click,'flights')]")
    public WebElement flights;

    @FindBy(xpath = "//*[contains(@data-et-click,'rentalcars')]")
    public WebElement carRentals;

    @FindBy(xpath = "//*[contains(@data-decider-header,'attractions')]")
    public WebElement attractions;

    @FindBy(xpath = "//*[contains(@data-decider-header,'rideways')]")
    public WebElement airportTaxis;

    @FindBy(xpath = "//*[@class='modal-mask-closeBtn']")
    public WebElement closePopUp;

    @FindBy(how= How.XPATH, using="//a[contains(text(), 'My Dashboard')]")
    private WebElement myDashboard;

    @FindBy(how= How.XPATH, using="//a[contains(text(), 'My wish lists')]")
    private WebElement myWishList;

    @FindBy(how= How.XPATH, using="//*[@data-bui-ref='carousel-item'][1]//h1")
    public WebElement firstFavoriteHotel;

    @FindBy(how= How.XPATH, using="//*[@data-bui-ref='carousel-item'][2]//h1")
    public WebElement secondFavoriteHotel;

    @FindBy(how= How.XPATH, using="//*[@class='email-confirm-banner__header']")
    public WebElement emailConfirmBanner;

    public BookingAccountPage (WebDriver driver) {
        super(driver);
    }

    public void goToMyDashboard () {
        yourAccount.click();
        myDashboard.click();
        LOGGER.debug(">>> My Dashboard is opened successfully!");
    }

    public void goToMyNextTripList () {
        yourAccount.click();
        myWishList.click();
        LOGGER.debug(">>> My Next Trip List is opened successfully!");
    }

    public Boolean isEmailConfirmBannerPresented () {
        Boolean condition = false;
        try {
            condition = emailConfirmBanner.isDisplayed();
        } catch (NoSuchElementException e) {
            return condition;
        }
        LOGGER.debug(">>> Checked if email confirmation banner is presented successfully!");
        return condition;
    }

}
