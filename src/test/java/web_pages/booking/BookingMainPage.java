package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utilities.PathList;
import utilities.booking.DateCalculator;
import utilities.PropertiesParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_pages.AbstractPage;

import java.util.List;
import static web_driver.Driver.executeJS;

public class BookingMainPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(BookingMainPage.class);

    @FindBy (how=How.ID, using="ss")
    private WebElement destination;

    @FindBy (how=How.CLASS_NAME, using="xp__dates-inner")
    private WebElement dates;

    @FindBy (how=How.CLASS_NAME, using="bui-calendar__control bui-calendar__control--prev")
    private WebElement goToPreviousMonth;

    @FindBy(how= How.ID, using="xp__guests__toggle")
    private WebElement accommodation;

    @FindBy(how=How.CLASS_NAME, using="bui-stepper__input")
    private List<WebElement> accommodationQuantitiesInput;

    @FindBy(how= How.CLASS_NAME, using="sb-searchbox__button")
    public WebElement searchButton;

    @FindBy(how= How.XPATH, using="//*[@class='sign_in_wrapper']/span[contains(text(), 'Register')]")
    public WebElement registrationButton;

    @FindBy(how= How.XPATH, using="//*[@class='sign_in_wrapper']/span[contains(text(), 'Sign in')]")
    public WebElement signInButton;


    public BookingMainPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToBooking() {
        LOGGER.debug(">>> Navigate to booking");
        String url = PropertiesParser.getProperties(PathList.getBookingPropertyPath()).getProperty("URL");
        driver.get(url);
    }

    public void enterDestination (String country) {
        LOGGER.debug(">>> Enter destination country");
        destination.clear();
        destination.sendKeys(country);
    }

    public void enterDates (int arrivalInXDays, int durationOfStay) {
        LOGGER.debug(">>> Enter check-in date and duration of stay");
        dates.click();
        DateCalculator.defineTravelDates(arrivalInXDays, durationOfStay);

        String dateTemplate = "//*[@class='bui-calendar']//*[@data-date='%s']";
        String checkInDateXPath = String.format(dateTemplate, DateCalculator.getCheckInDate());
        String checkOutDateXPath = String.format(dateTemplate, DateCalculator.getCheckOutDate());

        WebElement checkIn = driver.findElement(By.xpath(checkInDateXPath));
        WebElement checkOut = driver.findElement(By.xpath(checkOutDateXPath));
        checkIn.click();
        checkOut.click();
    }

    public void enterAccommodationDetails (int adults, int children, int rooms) {
        LOGGER.debug(">>> Enter accommodation details (adults, children, rooms)");
        accommodation.click();

        String scriptTemplate = "arguments[0].setAttribute('value', %s);";
        executeJS(String.format(scriptTemplate, adults), accommodationQuantitiesInput.get(0));
        executeJS(String.format(scriptTemplate, children), accommodationQuantitiesInput.get(1));
        executeJS(String.format(scriptTemplate, rooms), accommodationQuantitiesInput.get(2));
    }

}
