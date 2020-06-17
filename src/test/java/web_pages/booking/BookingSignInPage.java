package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.PathList;
import utilities.PropertiesParser;
import web_pages.AbstractPage;

import java.util.Properties;

public class BookingSignInPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(BookingSignInPage.class);
    private final Properties propTrashM = PropertiesParser.getProperties(PathList.getTrashMailPropertyPath());
    private final Properties propBooking = PropertiesParser.getProperties(PathList.getBookingPropertyPath());

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

    public void signIn () {
        email.sendKeys(propTrashM.getProperty("EMAIL"));
        nextButton.click();
        password.sendKeys(propBooking.getProperty("PWD"));
        signInButton.click();
        LOGGER.debug(">>> Credentials are filled in successfully!");
    }
}
