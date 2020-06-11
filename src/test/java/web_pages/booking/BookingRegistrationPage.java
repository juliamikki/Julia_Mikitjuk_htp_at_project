package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class BookingRegistrationPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(BookingRegistrationPage.class);

    @FindBy (xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy (xpath = "//form[@class='nw-register']//*[text() = 'Get started']")
    private WebElement getStartedButton;

    @FindBy (xpath = "//div[@class='nw-password']//input")
    private WebElement password;

    @FindBy (xpath = "//div[@class='nw-confirm-password']//input")
    private WebElement confirmPassword;

    @FindBy (xpath = "//form[@class='nw-register']//*[text() = 'Create account']")
    private WebElement createAccountButton;

    public BookingRegistrationPage (WebDriver driver) {
        super(driver);
    }

    public void createAccount() {
        LOGGER.debug(">>> Create new booking account");
        email.sendKeys(PropertiesParser.getTrashMailProperties().getProperty("EMAIL"));
        getStartedButton.click();
        password.sendKeys(PropertiesParser.getBookingProperties().getProperty("PWD"));
        confirmPassword.sendKeys(PropertiesParser.getBookingProperties().getProperty("PWD"));
        createAccountButton.click();
    }

}
