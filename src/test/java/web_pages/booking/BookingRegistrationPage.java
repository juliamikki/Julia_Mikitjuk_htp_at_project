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
        Properties propTrashM = PropertiesParser.getProperties(PathList.getTrashMailPropertyPath());
        Properties propBooking = PropertiesParser.getProperties(PathList.getBookingPropertyPath());

        email.sendKeys(propTrashM.getProperty("EMAIL"));
        getStartedButton.click();
        password.sendKeys(propBooking.getProperty("PWD"));
        confirmPassword.sendKeys(propBooking.getProperty("PWD"));
        createAccountButton.click();
        LOGGER.debug(">>> New booking account is created successfully!");
    }

}
