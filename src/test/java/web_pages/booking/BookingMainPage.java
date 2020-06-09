package web_pages.booking;

import utilities.booking.DateCalculator;
import utilities.booking.PropertiesParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_pages.AbstractPage;

import java.util.List;
import static web_driver.Driver.executeJS;

public class BookingMainPage extends AbstractPage {

    @FindBy (how=How.ID, using="ss")
    private WebElement destination;

    @FindBy (how=How.CLASS_NAME, using="xp__dates-inner")
    private WebElement dates;

    @FindBy (how=How.CLASS_NAME, using="bui-calendar__control bui-calendar__control--prev")
    private WebElement goToPreviousMonth;

    @FindBy(how= How.ID, using="xp__guests__toggle")
    private WebElement accomodation;

    @FindBy(how=How.CLASS_NAME, using="bui-stepper__input")
    private List<WebElement> accomodationQuantitiesInput;

    @FindBy(how= How.CLASS_NAME, using="sb-searchbox__button")
    public WebElement searchButton;

    @FindBy(how= How.XPATH, using="//*[@class='sign_in_wrapper']/span[contains(text(), 'Register')]")
    public WebElement registrationButton;

    public BookingMainPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToBooking() {
        String url = PropertiesParser.getBookingProperties().getProperty("URL_MAIN");
        driver.get(url);
    }

    public void enterDestination (String country) {
        destination.clear();
        destination.sendKeys(country);
    }

    public void enterDates (int arrivalInXDays, int durationOfStay) {

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
        accomodation.click();

        String scriptTemplate = "arguments[0].setAttribute('value', %s);";
        executeJS(String.format(scriptTemplate, adults), accomodationQuantitiesInput.get(0));
        executeJS(String.format(scriptTemplate, children), accomodationQuantitiesInput.get(1));
        executeJS(String.format(scriptTemplate, rooms), accomodationQuantitiesInput.get(2));
    }

}
