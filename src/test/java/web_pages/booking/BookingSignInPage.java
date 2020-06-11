package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class BookingSignInPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(BookingSignInPage.class);

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy (xpath = "//form[@class='nw-signin']//*[text() = 'Next']")
    private WebElement nextButton;

    @FindBy (xpath = "//div[@class='nw-password']//input")
    private WebElement password;

    @FindBy (xpath = "//form[@class='nw-signin']//*[text() = 'Sign in']")
    private WebElement signInButton;

    public BookingSignInPage (WebDriver driver) {
        super(driver);
    }

    public void navigateToSignIn () {
        LOGGER.debug(">>> Navigate to booking");
        String url = PropertiesParser.getBookingProperties().getProperty("SIGN_IN");
        driver.get(url);
    }

    public void signIn () {
        LOGGER.debug(">>> Sign in to Booking.com");
        email.sendKeys(PropertiesParser.getTrashMailProperties().getProperty("EMAIL"));
        nextButton.click();
        password.sendKeys(PropertiesParser.getBookingProperties().getProperty("PWD"));
        signInButton.click();
    }
}
